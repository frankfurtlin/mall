package com.frankfurtlin.mall.service.impl;

import com.frankfurtlin.mall.model.entity.OrderItem;
import com.frankfurtlin.mall.mapper.OrderItemMapper;
import com.frankfurtlin.mall.service.IOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单商品表 服务实现类
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

}
