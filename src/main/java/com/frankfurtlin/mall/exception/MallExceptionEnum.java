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
    INSERT_FAILED(10005, "数据库错误，插入失败"),

    SYSTEM_ERROR(20001, "系统错误");

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
