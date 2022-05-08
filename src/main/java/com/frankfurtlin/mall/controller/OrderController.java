package com.frankfurtlin.mall.controller;


import com.frankfurtlin.mall.common.ApiRestResponse;
import com.frankfurtlin.mall.model.request.OrderCreateByCartReq;
import com.frankfurtlin.mall.model.request.OrderCreateByProductReq;
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

    @ApiOperation("根据商品创建订单")
    @PostMapping("/createByProduct")
    public ApiRestResponse<?> createByProduct(@Valid @ApiParam("订单个人信息") @RequestBody OrderCreateByProductReq orderCreateByProductReq){

        return ApiRestResponse.success(iOrderService.createByProduct(orderCreateByProductReq));
    }

    @ApiOperation("根据订单状态查询订单列表")
    @PostMapping("/list")
    public ApiRestResponse<?> list(@ApiParam("订单状态") @RequestParam Integer status, @ApiParam("页面数量") @RequestParam Integer pageNum, @ApiParam("页面大小") @RequestParam Integer pageSize ){

        return ApiRestResponse.success(iOrderService.listOrder(status, pageNum, pageSize));
    }

    @ApiOperation("后台查询订单列表")
    @PostMapping("/admin/list")
    public ApiRestResponse<?> listAll(@ApiParam("订单状态") @RequestParam Integer status, @ApiParam("页面数量") @RequestParam Integer pageNum, @ApiParam("页面大小") @RequestParam Integer pageSize ){

        return ApiRestResponse.success(iOrderService.listAllOrder(status, pageNum, pageSize));
    }

    @ApiOperation("查询订单详情")
    @PostMapping("/detail")
    public ApiRestResponse<?> detail( @ApiParam("订单号") @RequestParam String orderNo){

        return ApiRestResponse.success(iOrderService.detail(orderNo));
    }

    @ApiOperation("取消订单")
    @PostMapping("/cancel")
    public ApiRestResponse<?> cancel( @ApiParam("订单号") @RequestParam String orderNo){

        iOrderService.cancel(orderNo);

        return ApiRestResponse.success();
    }

    @ApiOperation("支付订单")
    @PostMapping("/pay")
    public ApiRestResponse<?> pay( @ApiParam("订单号") @RequestParam String orderNo){

        iOrderService.pay(orderNo);

        return ApiRestResponse.success();
    }

    @ApiOperation("订单发货")
    @PostMapping("/admin/sent")
    public ApiRestResponse<?> sent( @ApiParam("订单号") @RequestParam String orderNo){

        iOrderService.sent(orderNo);

        return ApiRestResponse.success();
    }

    @ApiOperation("订单送达")
    @PostMapping("/admin/deliver")
    public ApiRestResponse<?> deliver( @ApiParam("订单号") @RequestParam String orderNo){

        iOrderService.deliver(orderNo);

        return ApiRestResponse.success();
    }

    @ApiOperation("订单完结")
    @PostMapping("/done")
    public ApiRestResponse<?> done( @ApiParam("订单号") @RequestParam String orderNo){

        iOrderService.done(orderNo);

        return ApiRestResponse.success();
    }

}
