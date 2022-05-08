package com.frankfurtlin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.frankfurtlin.mall.common.ApiRestResponse;
import com.frankfurtlin.mall.common.Constant;
import com.frankfurtlin.mall.exception.MallException;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.model.dto.ProductQueryDto;
import com.frankfurtlin.mall.model.entity.Product;
import com.frankfurtlin.mall.mapper.ProductMapper;
import com.frankfurtlin.mall.model.request.ProductAddReq;
import com.frankfurtlin.mall.model.request.ProductListAdminReq;
import com.frankfurtlin.mall.model.request.ProductUpdateReq;
import com.frankfurtlin.mall.model.response.ProductListRes;
import com.frankfurtlin.mall.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Product checkProductIdExisted(int id){
        Product product = productMapper.selectById(id);
        if(product == null){
            throw new MallException(MallExceptionEnum.PRODUCT_ID_NOT_EXISTED);
        }
        return product;
    }

    @Override
    public Page<Product> getPage(ProductQueryDto productQueryDto){
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("category_id", productQueryDto.getCategoryId());
        queryWrapper.gt("stock", productQueryDto.getMinStock());

        if(productQueryDto.getStatus() != Constant.ALL_STATUS){
            queryWrapper.eq("status", productQueryDto.getStatus());
        }

        if(productQueryDto.getKey() != null){
            queryWrapper.like("product_name", productQueryDto.getKey());
        }

        if(productQueryDto.getOrderBy() != null){
            queryWrapper.orderBy(true, productQueryDto.getOrderBy().getAsc(), productQueryDto.getOrderBy().getItem());
        }

        return productMapper.selectPage(new Page<>(productQueryDto.getPageNum(), productQueryDto.getPageSize()), queryWrapper);
    }

    /**
     * 判断商品名称是否存在
     * @param productName 商品名称
     */
    private void checkProductNameExisted(String productName){
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("product_name", productName);
        if (productMapper.selectOne(productQueryWrapper) != null) {
            throw new MallException(MallExceptionEnum.PRODUCT_NAME_EXISTED);
        }
    }
}
