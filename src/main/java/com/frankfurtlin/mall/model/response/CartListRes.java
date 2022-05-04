package com.frankfurtlin.mall.model.response;

import lombok.Data;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/4 10:35
 */
@Data
public class CartListRes {

    /**
     * 商品 id
     */
    private Long productId;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品是否选中
     */
    private Integer selected;

    /**
     * 商品当前价格
     */
    private Integer price;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品图片
     */
    private String productImage;
}
