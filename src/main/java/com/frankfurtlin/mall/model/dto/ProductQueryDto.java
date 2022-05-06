package com.frankfurtlin.mall.model.dto;

import com.frankfurtlin.mall.common.OrderByEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/3 16:13
 */
@Data
@ApiModel(value = "ProductQueryDto对象", description = "商品查询条件对象")
public class ProductQueryDto {

    @ApiModelProperty("商品目录id")
    private Integer categoryId;

    @ApiModelProperty("商品当前页面")
    private Integer pageNum;

    @ApiModelProperty("商品每页数量")
    private Integer pageSize;

    @ApiModelProperty("商品排序标准")
    private OrderByEnum orderBy;

    @ApiModelProperty("商品搜索关键词")
    private String key;

    @ApiModelProperty("商品状态 0-下架 1-上架 2-所有状态")
    private Integer status;

    @ApiModelProperty("商品的最小库存")
    private Integer minStock;
}
