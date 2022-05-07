package com.frankfurtlin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.frankfurtlin.mall.common.Constant;
import com.frankfurtlin.mall.exception.MallException;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.filter.UserFilter;
import com.frankfurtlin.mall.mapper.CartMapper;
import com.frankfurtlin.mall.mapper.OrderItemMapper;
import com.frankfurtlin.mall.mapper.ProductMapper;
import com.frankfurtlin.mall.model.entity.Cart;
import com.frankfurtlin.mall.model.entity.Order;
import com.frankfurtlin.mall.mapper.OrderMapper;
import com.frankfurtlin.mall.model.entity.OrderItem;
import com.frankfurtlin.mall.model.entity.Product;
import com.frankfurtlin.mall.model.request.OrderCreateByCartReq;
import com.frankfurtlin.mall.model.response.OrderDetailRes;
import com.frankfurtlin.mall.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frankfurtlin.mall.utils.OrderCodeFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createByCart(OrderCreateByCartReq orderCreateByCartReq){
        Long userId = UserFilter.currentUser.getId();

        // 查询用户已勾选的购物车
        List<Cart> cartList = findUserCartInSelected(userId);

        // 当用户购物车未勾选商品时，报出异常
        if(cartList.isEmpty()){
            throw new MallException(MallExceptionEnum.PERSON_CART_EMPTY);
        }

        // 统计订单总价格 并判断购物车中商品是否在售 库存是否充足
        int totalPrice = 0;
        for(Cart cart : cartList){
            Product queryProduct = productMapper.selectById(cart.getProductId());
            if(queryProduct.getStatus() != Constant.STATUS_SALE){
                throw new MallException(MallExceptionEnum.PRODUCT_NOT_ON_SALE);
            }
            if(queryProduct.getStock() < cart.getQuantity()){
                throw new MallException(MallExceptionEnum.PRODUCT_NOT_ENOUGH);
            }
            totalPrice += queryProduct.getPrice() * cart.getQuantity();
        }

        // 插入一条订单项到订单表
        Order order = new Order();
        order.setOrderNo(OrderCodeFactory.getOrderCode());
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        BeanUtils.copyProperties(orderCreateByCartReq, order);

        System.out.println(order);

        if(orderMapper.insert(order) != 1){
            throw new MallException(MallExceptionEnum.DATABASE_FAILED);
        }

        // 根据购物车中已勾选物品添加订单物品项
        for(Cart cart : cartList){
            OrderItem item = new OrderItem();

            Product queryProduct = productMapper.selectById(cart.getProductId());

            item.setOrderId(order.getId());
            item.setProductId(queryProduct.getId());
            item.setProductName(queryProduct.getProductName());
            item.setProductImage(queryProduct.getProductImage());
            item.setUnitPrice(queryProduct.getPrice());
            item.setQuantity(cart.getQuantity());
            item.setTotalPrice(item.getQuantity() * item.getUnitPrice());

            if(orderItemMapper.insert(item) != 1){
                throw new MallException(MallExceptionEnum.DATABASE_FAILED);
            }
        }

        // 删除商品库存
        for(Cart cart : cartList){
            Product queryProduct = productMapper.selectById(cart.getProductId());

            int remain = queryProduct.getStock() - cart.getQuantity();
            queryProduct.setStock(remain);

            productMapper.updateById(queryProduct);
        }

        // 删除购物车
        cartMapper.delete(new QueryWrapper<Cart>().eq("user_id", userId).eq("selected", Constant.SELECTED_TRUE));

        return order.getOrderNo();
    }

    @Override
    public OrderDetailRes detail(String orderNo){
        OrderDetailRes orderDetailRes = new OrderDetailRes();

        Order order = orderMapper.selectOne(new QueryWrapper<Order>().eq("order_no", orderNo));

        if(order == null){
            throw new MallException(MallExceptionEnum.ORDER_NOT_EXISTED);
        }
        if(!order.getUserId().equals(UserFilter.currentUser.getId())){
            throw new MallException(MallExceptionEnum.ORDER_NOT_BELONG_YOU);
        }

        BeanUtils.copyProperties(order, orderDetailRes);
        orderDetailRes.setOrderItemList(orderItemMapper.selectList(new QueryWrapper<OrderItem>().eq("order_id", order.getId())));

        return orderDetailRes;
    }

    /**
     * 根据用户 id 查询用户已选择的购物车项
     * @param userId 用户id
     * @return 已选择的购物车项表
     */
    private List<Cart> findUserCartInSelected(Long userId){
        return cartMapper.selectList(new QueryWrapper<Cart>().eq("user_id", userId).eq("selected", Constant.SELECTED_TRUE));
    }

}
