package com.frankfurtlin.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Data
@ApiModel(value = "Product对象", description = "商品表")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品id")
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
    private Date createTime;

    @ApiModelProperty("商品更新时间")
    private Date updateTime;

}
