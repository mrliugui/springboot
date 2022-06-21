package com.liugui.springboot.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle){
        logger.info("---------LogInterceptor已经开始————————");
        logger.info("请求地址"+request.getRequestURL()+"请求方法"+request.getMethod());
        logger.info("请求远程地址"+request.getRemoteAddr());
        request.setAttribute("requestStartTime",System.currentTimeMillis());
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView mav){
        long startTime = (long)request.getAttribute("requestStartTime");
        logger.info("--------LogInterceptor已经结束————————"+(System.currentTimeMillis()-startTime)+"ms");
    }

}
