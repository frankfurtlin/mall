package com.frankfurtlin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.frankfurtlin.mall.common.Constant;
import com.frankfurtlin.mall.exception.MallException;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.mapper.ProductMapper;
import com.frankfurtlin.mall.model.entity.Cart;
import com.frankfurtlin.mall.mapper.CartMapper;
import com.frankfurtlin.mall.model.entity.Product;
import com.frankfurtlin.mall.model.response.CartListRes;
import com.frankfurtlin.mall.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<CartListRes> listCart(Long userId){
        List<CartListRes> list = new ArrayList<>();

        List<Cart> carts = cartMapper.selectList(new QueryWrapper<Cart>().eq("user_id", userId));

        for(Cart cart : carts){
            CartListRes cartListRes = new CartListRes();

            BeanUtils.copyProperties(cart, cartListRes);

            copyCartProduct(cart.getProductId(), cartListRes);

            list.add(cartListRes);
        }

        return list;
    }

    @Override
    public CartListRes updateCart(Long userId, Long productId, int count){
        Product product = productMapper.selectById(productId);

        // 商品未在售 报异常
        if(product.getStatus() != Constant.STATUS_SALE){
            throw new MallException(MallExceptionEnum.PRODUCT_NOT_ON_SALE);
        }

        // 商品库存不够 报异常
        if(count > product.getStock()){
            throw new MallException(MallExceptionEnum.PRODUCT_NOT_ENOUGH);
        }

        Cart queryCart = getCartProduct(userId, productId);

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        cart.setQuantity(count);

        // 若用户之前未添加该产品则添加 否则修改
        if(queryCart != null){
            cart.setId(queryCart.getId());

            cartMapper.updateById(cart);
        }
        else{
            cartMapper.insert(cart);
        }

        Cart cart1 = getCartProduct(userId, productId);

        CartListRes res = new CartListRes();
        BeanUtils.copyProperties(cart1, res);

        copyCartProduct(productId, res);

        return res;
    }

    @Override
    public void deleteCart(Long userId, Long productId){
        Cart queryCart = getCartProduct(userId, productId);

        if(queryCart == null){
            throw new MallException(MallExceptionEnum.PRODUCT_NOT_IN_CART);
        }

        if(cartMapper.deleteById(queryCart.getId()) != 1){
            throw new MallException(MallExceptionEnum.DATABASE_FAILED);
        }

    }

    @Override
    public void selectCart(Long userId, Long productId, boolean selected){
        Cart queryCart = getCartProduct(userId, productId);

        if(queryCart == null){
            throw new MallException(MallExceptionEnum.PRODUCT_NOT_IN_CART);
        }

        // 根据状态选择购物车是否被选中
        if(selected){
            queryCart.setSelected(Constant.SELECTED_TRUE);
        }
        else{
            queryCart.setSelected(Constant.SELECTED_FALSE);
        }

        if(cartMapper.updateById(queryCart) != 1){
            throw new MallException(MallExceptionEnum.DATABASE_FAILED);
        }

    }

    @Override
    public void selectAllCart(Long userId, boolean selected){
        List<Cart> carts = cartMapper.selectList(new QueryWrapper<Cart>().eq("user_id", userId));
        for(Cart cart : carts){
            // 根据状态选择购物车是否被选中
            if(selected){
                cart.setSelected(Constant.SELECTED_TRUE);
            }
            else{
                cart.setSelected(Constant.SELECTED_FALSE);
            }

            if(cartMapper.updateById(cart) != 1){
                throw new MallException(MallExceptionEnum.DATABASE_FAILED);
            }
        }


    }

    /**
     * 根据用户id、商品id查询购物车商品信息
     */
    private Cart getCartProduct(Long userId, Long productId){
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("product_id", productId);

        return cartMapper.selectOne(queryWrapper);
    }

    /**
     * 根据商品 id 将查询到的商品信息复制到购物车返回类中
     * @param productId 商品 id
     * @param cartListRes 购物车项返回类
     */
    private void copyCartProduct(Long productId, CartListRes cartListRes){
        Product product = productMapper.selectById(productId);
        if(product == null){
            throw new MallException(MallExceptionEnum.PRODUCT_ID_NOT_EXISTED);
        }

        cartListRes.setPrice(product.getPrice());
        cartListRes.setProductName(product.getProductName());
        cartListRes.setProductImage(product.getProductImage());
    }
}
