package com.frankfurtlin.mall.model.request;

import com.frankfurtlin.mall.common.OrderByEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/3 15:31
 */
@Data
@ApiModel(value = "ProductListReq对象", description = "商品查询请求对象")
public class ProductListAdminReq {
    @NotNull(message = "商品目录id不能为空")
    @ApiModelProperty("商品目录id")
    private Integer categoryId;

    @NotNull(message = "商品当前页面不能为空")
    @ApiModelProperty("商品当前页面")
    private Integer pageNum;

    @NotNull(message = "商品每页数量不能为空")
    @ApiModelProperty("商品每页数量")
    private Integer pageSize;

    @ApiModelProperty("商品排序标准")
    private OrderByEnum orderBy;

    @ApiModelProperty("商品搜索关键词")
    private String key;

    @ApiModelProperty("商品状态 -1-所有状态 0-下架 1-上架")
    @Min(value = -1, message = "商品状态错误")
    @Max(value = 1, message = "商品状态错误")
    private int status;
}
