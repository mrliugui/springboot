package com.liugui.springboot.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class LogFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
     logger.info("------------LogFilter 已开始 ————————");
    logger.info("请求地址:"+request.getRequestURI().toString() +"请求方法"+request.getMethod());
    logger.info("远程请求地址"+request.getRemoteAddr().toString());
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info("-------------log Filter已经结束——————————"+(System.currentTimeMillis()-startTime)+"ms");
    }

    @Override
    public void destroy() {

    }
}
