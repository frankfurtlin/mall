package com.frankfurtlin.mall.service;

import com.frankfurtlin.mall.model.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.frankfurtlin.mall.model.request.OrderCreateByCartReq;
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
}
