package com.frankfurtlin.mall.exception;

/**
 * 异常枚举类
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/23 20:51
 */
public enum MallExceptionEnum {
    /**
     * 登录注册异常
     */
    NEED_USER_NAME(10001, "用户名不能为空"),
    NEED_PASSWORD(10002, "密码不能为空"),
    PASSWORD_TOO_SHORT(10003, "密码长度不能小于8位"),
    NAME_EXISTED(10004, "注册失败，用户已存在"),
    USERNAME_PASSWORD_ERROR(10005, "用户名或密码错误"),
    NEED_LOGIN(10006, "用户未登录"),
    NEED_ADMIN(10007, "用户无管理员权限"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR(20001, "系统错误"),
    DATABASE_FAILED(20002, "数据库错误");

    private Integer code;
    private String message;

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
