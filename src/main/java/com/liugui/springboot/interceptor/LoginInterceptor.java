package com.liugui.springboot.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

//    @Resource
//    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle){
        logger.info("---------LoginInterceptor已经开始————————");
        request.setAttribute("requestStartTime",System.currentTimeMillis());
//        在前后端分离的架构上，存在options预检请求，该请求进行放行
        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;
        }
        String path = request.getRequestURL().toString();
        logger.info("请求路径为{}",path);
        String token  = request.getHeader("token");
        logger.info("登录效验开始，token为{}",token);
        if(token==null || "".equals(token)){
            logger.info("token为空，该请求已被拦截");
            response.setStatus(401);
            return false;
        }
//        因为暂时没有redis数据库，所以目前只是进行模拟测试
//        Object o = redisTemplate.opsForValue().get(token);
        String o="194262288922972160";
        if(ObjectUtils.isEmpty(o)){
            logger.warn("该token无效");
            response.setStatus(401);
            return false;
        }else{
            logger.info("登录效验已通过，{}",o);
            return true;
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView mav){
        long startTime = (long)request.getAttribute("requestStartTime");
        logger.info("--------LoginInterceptor已经结束————————"+(System.currentTimeMillis()-startTime)+"ms");
    }

}
