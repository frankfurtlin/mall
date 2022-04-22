package com.frankfurtlin.mall.service.impl;

import com.frankfurtlin.mall.model.entity.Product;
import com.frankfurtlin.mall.mapper.ProductMapper;
import com.frankfurtlin.mall.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
