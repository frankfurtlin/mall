package com.frankfurtlin.mall.exception;

import com.frankfurtlin.mall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/24 11:56
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handlerException(Exception e){
        logger.error("Default Exception", e);
        return ApiRestResponse.error(MallExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(MallException.class)
    @ResponseBody
    public Object handlerMallException(MallException e){
        logger.error("MallException", e);
        return ApiRestResponse.error(e);
    }
}
