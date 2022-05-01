package com.frankfurtlin.mall.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/1 19:07
 */
@Data
@ApiModel(value = "ProductAddReq对象", description = "添加商品请求对象")
public class ProductAddReq {

    @NotNull(message = "商品名称不能为空")
    @Size(min = 1, max = 50, message = "商品分类名称长度在1-50之间")
    @ApiModelProperty("商品名称")
    private String productName;

    @NotNull(message = "商品图片地址不能为空")
    @Size(max = 50, message = "商品分类名称长度在50之内")
    @ApiModelProperty("商品图片地址")
    private String productImage;

    @NotNull(message = "商品详情不能为空")
    @Size(max = 255, message = "商品详情长度在255之内")
    @ApiModelProperty("商品分类名称")
    private String productDetail;

    @NotNull(message = "商品分类目录id不能为空")
    @ApiModelProperty("商品分类目录id")
    private Integer categoryId;

    @NotNull(message = "商品价格不能为空")
    @ApiModelProperty("商品价格 单位为分")
    private Long price;

    @NotNull(message = "商品库存不能为空")
    @ApiModelProperty("商品库存数量")
    @Max(value = 10000, message = "商品库存不能超过10000个")
    private Long stock;

    @NotNull(message = "商品状态不能为空")
    @ApiModelProperty("商品状态 0-下架 1-上架")
    private Integer status;

}
