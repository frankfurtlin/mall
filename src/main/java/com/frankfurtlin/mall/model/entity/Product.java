package com.frankfurtlin.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@ApiModel(value = "Product对象", description = "商品表")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("购物车id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品图片")
    private String productImage;

    @ApiModelProperty("商品详情")
    private String productDetail;

    @ApiModelProperty("分类目录id")
    private Integer categoryId;

    @ApiModelProperty("商品价格 单位为分")
    private Long price;

    @ApiModelProperty("商品库存数量")
    private Long stock;

    @ApiModelProperty("商品状态 0-下架 1-上架")
    private Integer status;

    @ApiModelProperty("商品创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("商品更新时间")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return "Product{" +
            "id=" + id +
            ", productName=" + productName +
            ", productImage=" + productImage +
            ", productDetail=" + productDetail +
            ", categoryId=" + categoryId +
            ", price=" + price +
            ", stock=" + stock +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
