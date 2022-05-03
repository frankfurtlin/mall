package com.frankfurtlin.mall.model.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/3 13:55
 * 返回给用户的商品信息类
 */
@ApiModel(value = "ProductListRes对象", description = "返回给用户的商品类")
@Data
public class ProductListRes {

    @ApiModelProperty("商品id")
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
    private Integer price;
}
