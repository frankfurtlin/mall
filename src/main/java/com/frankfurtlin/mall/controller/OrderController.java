package com.frankfurtlin.mall.controller;


import com.frankfurtlin.mall.common.ApiRestResponse;
import com.frankfurtlin.mall.model.request.OrderCreateByCartReq;
import com.frankfurtlin.mall.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/mall/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @ApiOperation("根据购物车创建订单")
    @PostMapping("/createByCart")
    public ApiRestResponse<?> createByCart(@Valid @ApiParam("订单个人信息") @RequestBody OrderCreateByCartReq orderCreateByCartReq){

        return ApiRestResponse.success(iOrderService.createByCart(orderCreateByCartReq));
    }

    @ApiOperation("查询订单详情")
    @PostMapping("/detail")
    public ApiRestResponse<?> detail( @ApiParam("订单号") @RequestParam String orderNo){

        return ApiRestResponse.success(iOrderService.detail(orderNo));
    }

}
