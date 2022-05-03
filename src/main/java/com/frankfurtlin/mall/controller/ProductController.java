package com.frankfurtlin.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.frankfurtlin.mall.common.ApiRestResponse;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.model.entity.Product;
import com.frankfurtlin.mall.model.request.ProductAddReq;
import com.frankfurtlin.mall.model.request.ProductListReq;
import com.frankfurtlin.mall.model.request.ProductUpdateReq;
import com.frankfurtlin.mall.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/mall/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @ApiOperation("添加商品")
    @PostMapping("/admin/add")
    public ApiRestResponse<?> add(@ApiParam("添加商品实体类") @Valid @RequestBody ProductAddReq productAddReq) {
        iProductService.addProduct(productAddReq);

        return ApiRestResponse.success();
    }

    @ApiOperation("修改商品")
    @PostMapping("/admin/update")
    public ApiRestResponse<?> update(@ApiParam("更新商品实体类") @Valid @RequestBody ProductUpdateReq productUpdateReq) {
        iProductService.updateProduct(productUpdateReq);

        return ApiRestResponse.success();
    }

    @ApiOperation("删除商品")
    @PostMapping("/admin/delete")
    public ApiRestResponse<?> delete(@ApiParam("商品id") @RequestParam Integer id) {
        Product product = iProductService.getById(id);
        if(product == null){
            return ApiRestResponse.error(MallExceptionEnum.PRODUCT_ID_NOT_EXISTED);
        }

        if (!iProductService.removeById(id)) {
            return ApiRestResponse.error(MallExceptionEnum.DATABASE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("批量上下架商品")
    @PostMapping("/admin/batchUpdateSellStatus")
    public ApiRestResponse<?> batchUpdateSellStatus(@ApiParam("商品id数组") @RequestParam Integer[] ids, @ApiParam("商品上下架状态 0-下架 1-上架") @RequestParam Integer status) {
        List<Product> list = new ArrayList<>();

        for (int id : ids){
            Product product = iProductService.getById(id);
            if(product == null){
                return ApiRestResponse.error(MallExceptionEnum.PRODUCT_ID_NOT_EXISTED);
            }
            product.setStatus(status);
            list.add(product);
        }

        if (!iProductService.updateBatchById(list)) {
            return ApiRestResponse.error(MallExceptionEnum.DATABASE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("根据商品目录分页查询商品")
    @PostMapping("/admin/list")
    public ApiRestResponse<?> list(@ApiParam("商品查询实体类") @Valid @RequestBody ProductListReq productListReq) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<Product>().eq("category_id", productListReq.getCategoryId());

        if(productListReq.getOrderBy() != null){
            queryWrapper.orderBy(true, productListReq.getOrderBy().getAsc(), productListReq.getOrderBy().getItem());
        }
        if(productListReq.getKey() != null){
            queryWrapper.like("product_name", productListReq.getKey());
        }

        IPage<Product> productPage = iProductService.page(new Page<>(productListReq.getPageNum(), productListReq.getPageSize()), queryWrapper);

        return ApiRestResponse.success(productPage);
    }

    @ApiOperation("查询商品详情")
    @PostMapping("/admin/details")
    public ApiRestResponse<?> detail(@ApiParam("商品id") @RequestParam Integer id) {
        Product product = iProductService.getById(id);
        if(product == null){
            return ApiRestResponse.error(MallExceptionEnum.PRODUCT_ID_NOT_EXISTED);
        }

        return ApiRestResponse.success(product);
    }

}
