package com.frankfurtlin.mall.common;

import com.frankfurtlin.mall.exception.MallException;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/23 20:34
 * 统一应答API（状态码，描述信息，返回数据）
 */
@AllArgsConstructor
@Data
public class ApiRestResponse<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    private static final int OK_CODE = 10000;
    private static final String OK_MESSAGE = "SUCCESS";

    ApiRestResponse(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public static <T> ApiRestResponse<T> success(){
        return new ApiRestResponse<>(OK_CODE, OK_MESSAGE);
    }
    /**
     * 返回成功消息
     * @param result 传入的正确消息数据
     * @param <T> 数据类型
     * @return 正确处理消息对象
     */
    public static <T> ApiRestResponse<T> success(T result){
        ApiRestResponse<T> response = ApiRestResponse.success();
        response.setData(result);
        return response;
    }

    public static <T> ApiRestResponse<T> error(MallException mallException){
        return new ApiRestResponse<>(mallException.getCode(), mallException.getMessage());
    }

    /**
     * 已定义的异常枚举类错误信息
     * @param mallExceptionEnum 异常枚举类
     * @param <T> 应答类型
     * @return 错误应答
     */
    public static <T> ApiRestResponse<T> error(MallExceptionEnum mallExceptionEnum){
        return new ApiRestResponse<>(mallExceptionEnum.getCode(), mallExceptionEnum.getMessage());
    }
}
