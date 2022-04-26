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
 * 目录表
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Data
@ApiModel(value = "Category对象", description = "商品分类表")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品分类主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商品分类名称")
    private String categoryName;

    @ApiModelProperty("商品分类级别 1-一级目录 2-二级目录")
    private Integer type;

    @ApiModelProperty("商品父分类id，一级商品父分类id为0")
    private Integer parentId;

    @ApiModelProperty("同级商品分类顺序")
    private Integer categoryOrder;

    @ApiModelProperty("商品分类创建时间")
    private Date createTime;

    @ApiModelProperty("商品分类更新时间")
    private Date updateTime;

}
