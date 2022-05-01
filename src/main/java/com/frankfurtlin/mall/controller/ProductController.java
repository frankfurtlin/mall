package com.frankfurtlin.mall.controller;


import com.frankfurtlin.mall.common.ApiRestResponse;
import com.frankfurtlin.mall.model.request.ProductAddReq;
import com.frankfurtlin.mall.model.request.ProductUpdateReq;
import com.frankfurtlin.mall.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
