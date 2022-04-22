package com.frankfurtlin.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@ApiModel(value = "Order对象", description = "订单表")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("订单号")
    private Long orderNo;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("订单总价格")
    private Long totalPrice;

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
    private LocalDateTime paymentTime;

    @ApiModelProperty("订单发货时间")
    private LocalDateTime deliveryTime;

    @ApiModelProperty("订单结束时间")
    private LocalDateTime receiveTime;

    @ApiModelProperty("订单创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("订单更新时间")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }
    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }
    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }
    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }
    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }
    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    public LocalDateTime getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", orderNo=" + orderNo +
            ", userId=" + userId +
            ", totalPrice=" + totalPrice +
            ", receiveName=" + receiveName +
            ", receiveAddress=" + receiveAddress +
            ", receivePhone=" + receivePhone +
            ", orderStatus=" + orderStatus +
            ", postage=" + postage +
            ", paymentType=" + paymentType +
            ", paymentTime=" + paymentTime +
            ", deliveryTime=" + deliveryTime +
            ", receiveTime=" + receiveTime +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
