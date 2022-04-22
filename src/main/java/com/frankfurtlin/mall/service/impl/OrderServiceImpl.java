package com.frankfurtlin.mall.service.impl;

import com.frankfurtlin.mall.model.entity.Order;
import com.frankfurtlin.mall.mapper.OrderMapper;
import com.frankfurtlin.mall.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
