package com.frankfurtlin.mall.exception;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/24 10:50
 * 用户自定义异常类
 */
public class MallException extends RuntimeException{
    private Integer code;
    private String message;

    public MallException(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public MallException(MallExceptionEnum mallExceptionEnum){
        this(mallExceptionEnum.getCode(), mallExceptionEnum.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
