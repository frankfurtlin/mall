package com.frankfurtlin.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@ApiModel(value = "Cart对象", description = "购物车表")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("购物车id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("商品数量")
    private Integer quantity;

    @ApiModelProperty("是否选中 0-未选中 1-选中")
    private Integer selected;

    @ApiModelProperty("购物车创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("购物车更新时间")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
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
        return "Cart{" +
            "id=" + id +
            ", userId=" + userId +
            ", productId=" + productId +
            ", quantity=" + quantity +
            ", selected=" + selected +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
