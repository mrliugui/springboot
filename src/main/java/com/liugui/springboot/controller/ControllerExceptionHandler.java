package com.liugui.springboot.controller;

import com.liugui.springboot.common.ApiResponse;
import com.liugui.springboot.myEnum.BusinessException;
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
        logger.warn("参数效验错误:{}",bindException.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        apiResponse.setCode(10003);
        apiResponse.setMsg(bindException.getAllErrors().get(0).getDefaultMessage());
        return apiResponse;
    }
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ApiResponse ValidationException(BusinessException businessException){
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        logger.warn("业务异常:{}",businessException.getMsg());
        apiResponse.setCode((businessException.getCode()).intValue());
        apiResponse.setMsg(businessException.getMsg());
        return apiResponse;
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse ValidationException(Exception e){
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        logger.error("系统异常:",e);
        apiResponse.setCode(500);
        apiResponse.setMsg("系统出现异常，请联系管理员");
        return apiResponse;
    }
}
