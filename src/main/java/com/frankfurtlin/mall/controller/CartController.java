package com.frankfurtlin.mall.controller;

import com.frankfurtlin.mall.common.ApiRestResponse;
import com.frankfurtlin.mall.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Api(tags = "购物车管理")
@RestController
@RequestMapping("/mall/cart")
public class CartController {

    @Autowired
    private ICartService iCartService;

    @ApiOperation("用户查询购物车列表")
    @GetMapping("/list")
    public ApiRestResponse<?> list(){

        return ApiRestResponse.success(iCartService.listCart());
    }

    @ApiOperation("用户新增或更新购物车商品数量")
    @PostMapping("/update")
    public ApiRestResponse<?> update(@ApiParam("商品id") @RequestParam("product_id") Long productId, @ApiParam("添加的数量") @RequestParam int count){

        return ApiRestResponse.success(iCartService.updateCart(productId, count));
    }

    @ApiOperation("用户删除购物车某项商品")
    @PostMapping("/delete")
    public ApiRestResponse<?> delete(@ApiParam("商品id") @RequestParam("product_id") Long productId){

        iCartService.deleteCart(productId);

        return ApiRestResponse.success();
    }

    @ApiOperation("用户更改购物车某项商品选中状态")
    @PostMapping("/select")
    public ApiRestResponse<?> select(@ApiParam("商品id") @RequestParam("product_id") Long productId, @ApiParam("是否选择该商品") @RequestParam boolean selected){

        iCartService.selectCart(productId, selected);

        return ApiRestResponse.success();
    }

    @ApiOperation("用户是否全选购物车")
    @PostMapping("/selectAll")
    public ApiRestResponse<?> selectAll(@ApiParam("是否选择该商品") @RequestParam boolean selected){

        iCartService.selectAllCart(selected);

        return ApiRestResponse.success();
    }
}
