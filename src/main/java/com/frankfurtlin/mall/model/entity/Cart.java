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
 * 购物车表
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Data
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
    private Date createTime;

    @ApiModelProperty("购物车更新时间")
    private Date updateTime;

}
