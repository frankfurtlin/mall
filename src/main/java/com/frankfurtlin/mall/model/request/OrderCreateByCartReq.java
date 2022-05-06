package com.frankfurtlin.mall.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/6 15:28
 */
@Data
@ApiModel(value = "OrderCreateByCartReq对象", description = "通过购物车已勾选的项创建订单的请求对象")
public class OrderCreateByCartReq {

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
}
