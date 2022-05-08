package com.frankfurtlin.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.frankfurtlin.mall.model.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.frankfurtlin.mall.model.request.OrderCreateByCartReq;
import com.frankfurtlin.mall.model.request.OrderCreateByProductReq;
import com.frankfurtlin.mall.model.response.OrderDetailRes;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
public interface IOrderService extends IService<Order> {

    /**
     * 通过用户购物车中已勾选的商品构建订单
     * @param orderCreateByCartReq 用户订单个人信息类
     * @return 订单号
     */
    @Transactional(rollbackFor = Exception.class)
    String createByCart(OrderCreateByCartReq orderCreateByCartReq);

    /**
     * 通过商品构建订单
     * @param orderCreateByProductReq 用户订单个人信息类（商品id、商品数量）
     * @return 订单号
     */
    @Transactional(rollbackFor = Exception.class)
    String createByProduct(OrderCreateByProductReq orderCreateByProductReq);

    /**
     * 根据订单状态分页查询订单信息
     * @param status 订单状态
     * @param pageNum 页数
     * @param pageSize 页大小
     * @return 订单分页对象
     */
    Page<Order> listOrder(int status, int pageNum, int pageSize);

    /**
     * 后台管理员根据订单状态分页查询订单信息
     * @param status 订单状态
     * @param pageNum 页数
     * @param pageSize 页大小
     * @return 订单分页对象
     */
    Page<Order> listAllOrder(int status, int pageNum, int pageSize);

    /**
     * 根据订单号查询订单信息
     * @param orderNo 订单号
     * @return 订单信息
     */
    OrderDetailRes detail(String orderNo);

    /**
     * 取消订单
     * @param orderNo 订单号
     */
    void cancel(String orderNo);

    /**
     * 支付订单
     * @param orderNo 订单号
     */
    void pay(String orderNo);

    /**
     * 后台订单发货
     * @param orderNo 订单号
     */
    void sent(String orderNo);

    /**
     * 后台订单送达
     * @param orderNo 订单号
     */
    void deliver(String orderNo);

    /**
     * 前后台订单完结
     * @param orderNo 订单号
     */
    void done(String orderNo);
}
