package com.frankfurtlin.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 订单商品表
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Data
@TableName("order_item")
@ApiModel(value = "OrderItem对象", description = "订单商品表")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单项目id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("订单商品名称")
    private String productName;

    @ApiModelProperty("订单商品图片")
    private String productImg;

    @ApiModelProperty("订单商品单价(分)--下单时价格")
    private Long unitPrice;

    @ApiModelProperty("订单商品数量")
    private Long quantity;

    @ApiModelProperty("订单商品总价")
    private Long totalPrice;

    @ApiModelProperty("订单商品创建时间")
    private Date createTime;

    @ApiModelProperty("订单商品更新时间")
    private Date updateTime;

}
