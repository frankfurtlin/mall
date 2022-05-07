package com.frankfurtlin.mall.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/4 10:35
 */
@Data
@ApiModel(value = "CartListRes对象", description = "返回给用户的购物车列表类")
public class CartListRes {

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("商品数量")
    private Integer quantity;

    @ApiModelProperty("商品是否选中")
    private Integer selected;

    @ApiModelProperty("商品当前价格")
    private Integer price;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品图片")
    private String productImage;
}
