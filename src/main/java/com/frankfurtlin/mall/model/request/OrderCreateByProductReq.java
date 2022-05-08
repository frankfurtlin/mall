package com.frankfurtlin.mall.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/8 9:37
 */
@Data
@ApiModel(value = "OrderCreateByProductReq对象", description = "通过商品 id 创建订单的请求对象")
public class OrderCreateByProductReq {

    @NotNull(message = "收货人姓名不能为空")
    @Size(max = 16, message = "收货人姓名长度不能超过16")
    @ApiModelProperty("收货人姓名")
    private String receiveName;

    @NotNull(message = "收货人地址不能为空")
    @Size(max = 64, message = "收货人地址长度不能超过64")
    @ApiModelProperty("收货人地址")
    private String receiveAddress;

    @NotNull(message = "收货人手机号不能为空")
    @Size(min = 11, max = 11, message = "收货人手机号错误")
    @ApiModelProperty("收货人手机号")
    private String receivePhone;

    @NotNull(message = "商品 id 不能为空")
    @ApiModelProperty("商品 id ")
    private Integer productId;

    @NotNull(message = "商品数量不能为空")
    @ApiModelProperty("商品数量")
    private Integer quantity;

    @ApiModelProperty("订单状态 0-已取消 1-未支付 2-已支付 3-已发货 4-已送达 5-已接受")
    private Integer orderStatus = 1;

    @ApiModelProperty("商品邮费")
    private Integer postage = 0;
}