package com.frankfurtlin.mall.exception;

import com.frankfurtlin.mall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 处理自定义MallException异常
     * @param e MallException异常
     * @return 统一异常返回
     */
    @ExceptionHandler(MallException.class)
    @ResponseBody
    public Object handlerMallException(MallException e){
        logger.error("MallException", e);
        return ApiRestResponse.error(e);
    }

    /**
     * 处理Validation由@Valid引起的MethodArgumentNotValidException异常
     * @param e MethodArgumentNotValidException异常
     * @return 统一异常返回
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        logger.error("MethodArgumentNotValidException", e);
        return handleBindingResult(e.getBindingResult());
    }

    /**
     * 处理【MethodArgumentNotValidException】异常；提取错误信息，构建ApiRestResponse统一返回对象；
     * @param result BindingResult
     * @return 统一异常返回
     */
    private ApiRestResponse<?> handleBindingResult(BindingResult result) {
        List<String> list = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            //遍历所有的错误信息；
            for (ObjectError objectError : allErrors) {
                //提取具体的错误信息；
                String message = objectError.getDefaultMessage();
                //将错误信息，添加到list集合中
                list.add(message);
            }
        }
        if (list.size() == 0) {
            return ApiRestResponse.error(MallExceptionEnum.REQUEST_PARAM_ERROR);
        }
        //根据MethodArgumentNotValidException异常的具体错误信息，构建ApiRestResponse统一返回对象；
        return ApiRestResponse.error(MallExceptionEnum.REQUEST_PARAM_ERROR.getCode(), list.toString());
    }
}
