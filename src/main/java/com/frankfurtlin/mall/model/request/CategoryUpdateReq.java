package com.frankfurtlin.mall.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/27 11:07
 */
@Data
@ApiModel(value = "CategoryUpdateReq对象", description = "更新商品分类请求对象")
public class CategoryUpdateReq {
    @NotNull(message = "商品分类id不能为空")
    @ApiModelProperty("商品分类id")
    private Integer id;

    @NotNull(message = "商品分类名称不能为空")
    @Size(min = 2, max = 5, message = "商品分类名称长度在2-5之间")
    @ApiModelProperty("商品分类名称")
    private String categoryName;

    @NotNull(message = "商品分类级别不能为空")
    @Min(value = 1, message = "商品分类级别不能低于一级")
    @Max(value = 3, message = "商品分类级别不能超过三级")
    @ApiModelProperty("商品分类级别 1-一级目录 2-二级目录")
    private Integer type;

    @NotNull(message = "商品父分类不能为空")
    @ApiModelProperty("商品父分类id，一级商品父分类id为0")
    private Integer parentId;

    @NotNull(message = "商品分类顺序不能为空")
    @ApiModelProperty("同级商品分类顺序")
    private Integer categoryOrder;
}
