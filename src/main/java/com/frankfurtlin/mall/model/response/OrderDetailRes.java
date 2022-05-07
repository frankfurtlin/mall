package com.frankfurtlin.mall.model.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.frankfurtlin.mall.model.entity.OrderItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/7 15:12
 */
@Data
@ApiModel(value = "OrderDetailRes对象", description = "返回给用户的订单详情类")
public class OrderDetailRes {

    @ApiModelProperty("订单id")
    private Long id;

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("订单总价格")
    private Integer totalPrice;

    @ApiModelProperty("收货人姓名")
    private String receiveName;

    @ApiModelProperty("收货人地址")
    private String receiveAddress;

    @ApiModelProperty("收货人手机号")
    private String receivePhone;

    @ApiModelProperty("订单状态 0-已取消 1-未支付 2-已支付 3-已发货 4-已送达 5-已接受")
    private Integer orderStatus;

    @ApiModelProperty("订单邮费")
    private Integer postage;

    @ApiModelProperty("支付方式 0-到货付款 1-银行卡付款 2-支付宝付款 3-微信付款")
    private Integer paymentType;

    @ApiModelProperty("订单付款时间")
    private Date paymentTime;

    @ApiModelProperty("订单发货时间")
    private Date deliveryTime;

    @ApiModelProperty("订单结束时间")
    private Date receiveTime;

    @ApiModelProperty("订单创建时间")
    private Date createTime;

    @ApiModelProperty("订单项列表")
    private List<OrderItem> orderItemList;
}
