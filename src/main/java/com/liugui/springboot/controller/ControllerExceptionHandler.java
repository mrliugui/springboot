package com.liugui.springboot.controller;

import com.liugui.springboot.common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ApiResponse ValidationException(BindException bindException){
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        logger.warn("参数效验错误"+bindException.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        apiResponse.setCode(10003);
        apiResponse.setMsg(bindException.getAllErrors().get(0).getDefaultMessage());
        return apiResponse;
    }
}
