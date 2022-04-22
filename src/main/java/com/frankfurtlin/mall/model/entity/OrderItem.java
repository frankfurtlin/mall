package com.frankfurtlin.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 订单商品表
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
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
    private LocalDateTime createTime;

    @ApiModelProperty("订单商品更新时间")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
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
        return "OrderItem{" +
            "id=" + id +
            ", orderId=" + orderId +
            ", productId=" + productId +
            ", productName=" + productName +
            ", productImg=" + productImg +
            ", unitPrice=" + unitPrice +
            ", quantity=" + quantity +
            ", totalPrice=" + totalPrice +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
