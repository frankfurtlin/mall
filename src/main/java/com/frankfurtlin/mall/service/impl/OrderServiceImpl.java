package com.frankfurtlin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.frankfurtlin.mall.model.request.OrderCreateByProductReq;
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
        Order order = generateOrder(userId, totalPrice);
        BeanUtils.copyProperties(orderCreateByCartReq, order);
        if(orderMapper.insert(order) != 1){
            throw new MallException(MallExceptionEnum.DATABASE_FAILED);
        }

        // 根据购物车中已勾选物品添加订单物品项
        for(Cart cart : cartList){

            // 根据购物车商品 id 查询商品信息
            Product queryProduct = productMapper.selectById(cart.getProductId());

            // 生成订单项
            OrderItem item = generateOrderItem(order.getId(), queryProduct, cart.getQuantity());

            if(orderItemMapper.insert(item) != 1){
                throw new MallException(MallExceptionEnum.DATABASE_FAILED);
            }
        }

        // 修改商品库存
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createByProduct(OrderCreateByProductReq orderCreateByProductReq){
        Long userId = UserFilter.currentUser.getId();

        Product product = productMapper.selectById(orderCreateByProductReq.getProductId());

        // 当商品不存在时，报出异常
        if(product == null){
            throw new MallException(MallExceptionEnum.PRODUCT_ID_NOT_EXISTED);
        }
        // 判断购物车中商品是否在售
        if(product.getStatus() != Constant.STATUS_SALE){
            throw new MallException(MallExceptionEnum.PRODUCT_NOT_ON_SALE);
        }
        // 判断商品库存是否充足
        if(product.getStock() < orderCreateByProductReq.getQuantity()){
            throw new MallException(MallExceptionEnum.PRODUCT_NOT_ENOUGH);
        }

        int totalPrice = product.getPrice() * orderCreateByProductReq.getQuantity();

        // 插入一条订单项到订单表
        Order order = generateOrder(userId, totalPrice);
        BeanUtils.copyProperties(orderCreateByProductReq, order);
        if(orderMapper.insert(order) != 1){
            throw new MallException(MallExceptionEnum.DATABASE_FAILED);
        }

        // 根据商品添加订单物品项
        OrderItem orderItem = generateOrderItem(order.getId(), product, orderCreateByProductReq.getQuantity());
        if(orderItemMapper.insert(orderItem) != 1){
            throw new MallException(MallExceptionEnum.DATABASE_FAILED);
        }

        // 修改商品库存
        int remain = product.getStock() - orderCreateByProductReq.getQuantity();
        product.setStock(remain);
        productMapper.updateById(product);

        return order.getOrderNo();
    }

    @Override
    public Page<Order> listOrder(int status, int pageNum, int pageSize){
        Long userId = UserFilter.currentUser.getId();

        // 判断订单状态
        if(status < Constant.OrderStatus.CANCEL || status > Constant.OrderStatus.DONE){
            throw new MallException(MallExceptionEnum.ORDER_STATUS_ERROR);
        }

        return orderMapper.selectPage(new Page<>(pageNum, pageSize), new QueryWrapper<Order>().eq("user_id", userId).eq("order_status", status));
    }

    @Override
    public OrderDetailRes detail(String orderNo){
        OrderDetailRes orderDetailRes = new OrderDetailRes();

        Order order = orderMapper.selectOne(new QueryWrapper<Order>().eq("order_no", orderNo));

        // 订单号不存在，报订单不存在异常
        if(order == null){
            throw new MallException(MallExceptionEnum.ORDER_NOT_EXISTED);
        }
        // 订单对应的用户不是当前用户，报订单不属于你异常
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

    /**
     * 生成订单（用户 id、总价格、订单号）
     * @param userId 用户 id
     * @param totalPrice 总价格
     * @return 订单
     */
    private Order generateOrder(Long userId, int totalPrice){
        Order order = new Order();
        order.setOrderNo(OrderCodeFactory.getOrderCode());
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);

        return order;
    }

    /**
     * 生成订单项
     * @param orderId 订单 id
     * @param product 商品信息
     * @param quantity 订单商品选购数量
     * @return 订单项
     */
    private OrderItem generateOrderItem(Long orderId, Product product, int quantity){
        OrderItem item = new OrderItem();

        item.setOrderId(orderId);
        item.setProductId(product.getId());
        item.setProductName(product.getProductName());
        item.setProductImage(product.getProductImage());
        item.setUnitPrice(product.getPrice());
        item.setQuantity(quantity);
        item.setTotalPrice(quantity * product.getPrice());

        return item;
    }

}
