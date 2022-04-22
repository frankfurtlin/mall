package com.frankfurtlin.mall.service.impl;

import com.frankfurtlin.mall.model.entity.Cart;
import com.frankfurtlin.mall.mapper.CartMapper;
import com.frankfurtlin.mall.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

}
