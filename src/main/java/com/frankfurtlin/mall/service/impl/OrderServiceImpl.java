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
import com.frankfurtlin.mall.model.entity.*;
import com.frankfurtlin.mall.mapper.OrderMapper;
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

import java.util.Date;
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
        checkOrderStatus(status);

        return orderMapper.selectPage(new Page<>(pageNum, pageSize), new QueryWrapper<Order>().eq("user_id", userId).eq("order_status", status));
    }

    @Override
    public Page<Order> listAllOrder(int status, int pageNum, int pageSize){

        // 校验订单状态
        checkOrderStatus(status);

        return orderMapper.selectPage(new Page<>(pageNum, pageSize), new QueryWrapper<Order>().eq("order_status", status));
    }

    @Override
    public OrderDetailRes detail(String orderNo){
        OrderDetailRes orderDetailRes = new OrderDetailRes();

        Long userId = UserFilter.currentUser.getId();

        // 校验订单号
        Order order = checkOrderNo(orderNo, userId);

        BeanUtils.copyProperties(order, orderDetailRes);
        orderDetailRes.setOrderItemList(orderItemMapper.selectList(new QueryWrapper<OrderItem>().eq("order_id", order.getId())));

        return orderDetailRes;
    }

    @Override
    public void cancel(String orderNo){

        Long userId = UserFilter.currentUser.getId();

        // 校验订单号
        Order order = checkOrderNo(orderNo, userId);

        // 校验订单状态 未支付、已付款未发货的订单才能取消
        if(order.getOrderStatus() != Constant.OrderStatus.NO_PAY && order.getOrderStatus() != Constant.OrderStatus.HAVE_PAY ){
            throw new MallException(MallExceptionEnum.ORDER_CANNOT_CANCEL);
        }

        // 根据订单 id 查询订单项列表
        List<OrderItem> list = orderItemMapper.selectList(new QueryWrapper<OrderItem>().eq("order_id", order.getId()));

        // 恢复商品库存 删除商品项
        for(OrderItem item : list){
            // 恢复商品库存
            Product product = productMapper.selectById(item.getProductId());
            int remain = product.getStock() + item.getQuantity();
            product.setStock(remain);
            productMapper.updateById(product);

            // 删除订单商品项
            orderItemMapper.deleteById(item.getId());
        }

        // 更改订单状态
        order.setOrderStatus(Constant.OrderStatus.CANCEL);
        orderMapper.updateById(order);
    }

    @Override
    public void pay(String orderNo){

        Long userId = UserFilter.currentUser.getId();

        // 校验订单号
        Order order = checkOrderNo(orderNo, userId);

        // 校验订单状态 未支付的订单才能支付
        if(order.getOrderStatus() != Constant.OrderStatus.NO_PAY){
            throw new MallException(MallExceptionEnum.ORDER_CANNOT_PAY);
        }

        // 更改订单状态
        order.setOrderStatus(Constant.OrderStatus.HAVE_PAY);
        order.setPaymentTime(new Date());
        orderMapper.updateById(order);
    }

    @Override
    public void sent(String orderNo){

        Order order = orderMapper.selectOne(new QueryWrapper<Order>().eq("order_no", orderNo));

        if(order == null){
            throw new MallException(MallExceptionEnum.ORDER_NOT_EXISTED);
        }

        // 校验订单状态 已支付的订单才能发货
        if(order.getOrderStatus() != Constant.OrderStatus.HAVE_PAY){
            throw new MallException(MallExceptionEnum.ORDER_CANNOT_SENT);
        }

        // 更改订单状态
        order.setOrderStatus(Constant.OrderStatus.HAVE_SENT);
        order.setDeliveryTime(new Date());
        orderMapper.updateById(order);
    }

    @Override
    public void deliver(String orderNo){

        Order order = orderMapper.selectOne(new QueryWrapper<Order>().eq("order_no", orderNo));

        if(order == null){
            throw new MallException(MallExceptionEnum.ORDER_NOT_EXISTED);
        }

        // 校验订单状态 已发送的订单才能送达
        if(order.getOrderStatus() != Constant.OrderStatus.HAVE_SENT){
            throw new MallException(MallExceptionEnum.ORDER_CANNOT_DELIVER);
        }

        // 更改订单状态
        order.setOrderStatus(Constant.OrderStatus.HAVE_DELIVERED);
        orderMapper.updateById(order);
    }

    @Override
    public void done(String orderNo){

        User user = UserFilter.currentUser;

        Order order = orderMapper.selectOne(new QueryWrapper<Order>().eq("order_no", orderNo));

        // 订单不存在 报异常
        if(order == null){
            throw new MallException(MallExceptionEnum.ORDER_NOT_EXISTED);
        }

        // 判断订单是否属于该用户
        if(user.getRole() != Constant.ROLE && !order.getUserId().equals(user.getId())){
            throw new MallException(MallExceptionEnum.ORDER_NOT_BELONG_YOU);
        }

        // 校验订单状态 已送达的订单才能完结
        if(order.getOrderStatus() != Constant.OrderStatus.HAVE_DELIVERED){
            throw new MallException(MallExceptionEnum.ORDER_CANNOT_DONE);
        }

        // 更改订单状态
        order.setOrderStatus(Constant.OrderStatus.DONE);
        order.setReceiveTime(new Date());
        orderMapper.updateById(order);
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

    /**
     * 根据用户 id、订单号判断订单号是否有效，订单号是否属于该用户 并返回订单信息
     * @param orderNo 订单号
     * @param userId 用户 id
     * @return 订单信息
     */
    private Order checkOrderNo(String orderNo, Long userId){
        Order order = orderMapper.selectOne(new QueryWrapper<Order>().eq("order_no", orderNo));

        // 订单号不存在，报订单不存在异常
        if(order == null){
            throw new MallException(MallExceptionEnum.ORDER_NOT_EXISTED);
        }

        // 订单对应的用户不是当前用户，报订单不属于你异常
        if(!order.getUserId().equals(userId)){
            throw new MallException(MallExceptionEnum.ORDER_NOT_BELONG_YOU);
        }

        return order;
    }

    /**
     * 校验订单状态
     * @param status 订单状态
     */
    void checkOrderStatus(int status){
        // 判断订单状态
        if(status < Constant.OrderStatus.CANCEL || status > Constant.OrderStatus.DONE){
            throw new MallException(MallExceptionEnum.ORDER_STATUS_ERROR);
        }
    }

}
