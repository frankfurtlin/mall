package com.frankfurtlin.mall.exception;

/**
 * 异常枚举类
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/23 20:51
 */
public enum MallExceptionEnum {
    /**
     * 用户名为空
     */
    NEED_USER_NAME(10001, "用户名不能为空"),

    /**
     * 密码为空
     */
    NEED_PASSWORD(10002, "密码不能为空"),

    /**
     * 密码小于8位
     */
    PASSWORD_TOO_SHORT(10003, "密码长度不能小于8位");

    Integer code;

    String message;

    MallExceptionEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
