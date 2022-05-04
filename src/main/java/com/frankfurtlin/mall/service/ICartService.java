package com.frankfurtlin.mall.service;

import com.frankfurtlin.mall.model.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.frankfurtlin.mall.model.response.CartListRes;

import java.util.List;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
public interface ICartService extends IService<Cart> {

    /**
     * 根据用户 id 查询购物车列表
     * @param userId 用户 id
     * @return 购物车列表
     */
    List<CartListRes> listCart(Long userId);

    /**
     * 根据用户id、商品id更改购物车商品数量
     * @param userId 用户 id
     * @param productId 商品id
     * @param count 更改后的商品数量
     * @return 展示给用户的购物车项
     */
    CartListRes updateCart(Long userId, Long productId, int count);

    /**
     * 根据用户id、商品id删除购物车项
     * @param userId 用户 id
     * @param productId 商品 id
     */
    void deleteCart(Long userId, Long productId);

    /**
     * 更改购物车项是否被选中
     * @param userId 用户 id
     * @param productId 商品 id
     * @param selected 是否被选中
     */
    void selectCart(Long userId, Long productId, boolean selected);

    /**
     * 用户全选或全不选购物车
     * @param userId 用户 id
     * @param selected 是否全选
     */
    void selectAllCart(Long userId, boolean selected);
}
