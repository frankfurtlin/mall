package com.frankfurtlin.mall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.frankfurtlin.mall.model.dto.ProductQueryDto;
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

    /**
     * 添加商品接口
     * @param productAddReq 商品添加类
     */
    void addProduct(ProductAddReq productAddReq);

    /**
     * 修改商品接口
     * @param productUpdateReq 商品修改类
     */
    void updateProduct(ProductUpdateReq productUpdateReq);

    /**
     * 检测商品id是否存在，并返回查询到的商品
     * @param id 商品id
     * @return 查询到的商品
     */
    Product checkProductIdExisted(int id);

    /**
     * 根据商品请求获取分页对象
     * @param productQueryDto 商品查询条件对象
     * @return 查询后的分页对象
     */
    IPage<Product> getPage(ProductQueryDto productQueryDto);
}
