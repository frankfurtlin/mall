package com.frankfurtlin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.frankfurtlin.mall.exception.MallException;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.model.entity.Category;
import com.frankfurtlin.mall.model.entity.Product;
import com.frankfurtlin.mall.mapper.ProductMapper;
import com.frankfurtlin.mall.model.request.CategoryAddReq;
import com.frankfurtlin.mall.model.request.CategoryUpdateReq;
import com.frankfurtlin.mall.model.request.ProductAddReq;
import com.frankfurtlin.mall.model.request.ProductUpdateReq;
import com.frankfurtlin.mall.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addProduct(ProductAddReq productAddReq){
        checkProductNameExisted(productAddReq.getProductName());

        Product product = new Product();
        BeanUtils.copyProperties(productAddReq, product);

        if(productMapper.insert(product) != 1){
            throw new MallException(MallExceptionEnum.DATABASE_FAILED);
        }

    }

    @Override
    public void updateProduct(ProductUpdateReq productUpdateReq){
        checkProductNameExisted(productUpdateReq.getProductName());

        Product product = new Product();
        BeanUtils.copyProperties(productUpdateReq, product);

        System.out.println(product);
        if(productMapper.updateById(product) != 1){
            throw new MallException(MallExceptionEnum.DATABASE_FAILED);
        }
    }

    private void checkProductNameExisted(String productName){
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("product_name", productName);
        if (productMapper.selectOne(productQueryWrapper) != null) {
            throw new MallException(MallExceptionEnum.PRODUCT_NAME_EXISTED);
        }
    }
}
