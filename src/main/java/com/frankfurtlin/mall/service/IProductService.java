package com.frankfurtlin.mall.service;

import com.frankfurtlin.mall.model.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.frankfurtlin.mall.model.request.ProductAddReq;
import com.frankfurtlin.mall.model.request.ProductUpdateReq;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
public interface IProductService extends IService<Product> {

    void addProduct(ProductAddReq productAddReq);

    void updateProduct(ProductUpdateReq productUpdateReq);
}
