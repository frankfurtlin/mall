package com.frankfurtlin.mall.common;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/25 10:55
 */
public class Constant {
    /**
     * 最小密码长度
     */
    public static final int LEAST_PASSWORD_LENGTH = 8;

    /**
     * MD5盐值
     */
    public static final String SALT = "oh19k./.-fw4kf=23r2olx4kwo1po20#&^!%@&*h4sjf7jf7bd";

    /**
     * Session中保存的用户信息key值
     */
    public static final String MALL_USER = "MALL_USER";

    /**
     * 管理员角色
     */
    public static final int ROLE = 0;

    /**
     * 商品状态 -1-所有状态 0-未上架 1-已上架
     */
    public static final int ALL_STATUS = -1;
    public static final int STATUS_SALE = 1;

    /**
     * 商品是否被选中
     */
    public static final int SELECTED_TRUE = 1;
    public static final int SELECTED_FALSE = 0;
}
