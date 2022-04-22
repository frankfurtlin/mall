package com.frankfurtlin.mall.mapper;

import com.frankfurtlin.mall.model.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单商品表 Mapper 接口
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}
