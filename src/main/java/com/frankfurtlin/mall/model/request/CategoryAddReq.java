package com.frankfurtlin.mall.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/26 10:20
 * 添加商品分类请求对象
 */
@Data
@ApiModel(value = "CategoryAddReq对象", description = "添加商品分类请求对象")
public class CategoryAddReq {
    @ApiModelProperty("商品分类名称")
    private String categoryName;

    @ApiModelProperty("商品分类级别 1-一级目录 2-二级目录")
    private Integer type;

    @ApiModelProperty("商品父分类id，一级商品父分类id为0")
    private Integer parentId;

    @ApiModelProperty("同级商品分类顺序")
    private Integer categoryOrder;
}
