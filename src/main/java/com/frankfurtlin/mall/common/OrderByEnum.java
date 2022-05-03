package com.frankfurtlin.mall.common;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/3 11:42
 * 商品排序实体类
 */
public enum OrderByEnum {
    /**
     * 按价格排序
     */
    PRICE_ASC("price", true),
    PRICE_DESC("price", false),

    /**
     * 按库存排序
     */
    STOCK_ASC("stock", true),
    STOCK_DESC("stock", false);

    /**
     * 排序方式 排序项
     */
    private String item;

    private boolean isAsc;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean getAsc() {
        return isAsc;
    }

    public void setAsc(boolean isAsc) {
        this.isAsc = isAsc;
    }

    OrderByEnum(String item, boolean isAsc) {
        this.item = item;
        this.isAsc = isAsc;
    }
}
