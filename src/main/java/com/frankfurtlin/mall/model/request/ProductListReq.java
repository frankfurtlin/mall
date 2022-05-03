package com.frankfurtlin.mall.model.request;

import com.frankfurtlin.mall.common.OrderByEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/3 11:31
 */
@Data
@ApiModel(value = "ProductListReq对象", description = "商品查询请求对象")
public class ProductListReq {
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
}
