package com.liugui.springboot.aspect;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class logAspect {
    private static final Logger logger = LoggerFactory.getLogger(logAspect.class);

    @Pointcut("execution(public * com.liugui.*.controller..*Controller.*(..))")
    public void pointController(){}

    @Before("pointController()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();

        logger.info("请求地址{}.{}",request.getRequestURL(),request.getMethod());
        logger.info("请求远程地址{}",request.getRemoteAddr());
        logger.info("类名以及方法{}.{}",className,methodName);
      //       过滤请求参数敏感信息
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for(int n=0;n<args.length;n++){
         if(args[n] instanceof ServletRequest || args[n] instanceof ServletResponse || args[n] instanceof MultipartFile){
             continue;
         }
         arguments[n] = args[n];
        }
        String[] excludeProperties = {"password","file"};
        SimplePropertyPreFilter filter1 = new SimplePropertyPreFilter();
        for(int i=0;i<excludeProperties.length;i++){
            filter1.getExcludes().add(excludeProperties[i]);
        }
        logger.info("请求参数为{}",JSON.toJSONString(arguments,filter1));
    }
    @Around("pointController()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        String[] excludeProperties = {"password","file"};
        SimplePropertyPreFilter filter1 = new SimplePropertyPreFilter();
        for(int i=0;i<excludeProperties.length;i++){
            filter1.getExcludes().add(excludeProperties[i]);
        }
//        PropertyFilter propertyFilter = new PropertyFilter() {
//            @Override
//            public boolean apply(Object o, String name, Object value) {
//                if (name.equals("code")) {
//                    return false; // 排除名为name的字段
//                }
//                if (value.toString().equals("2")) {
//                    return false; // 排除值为2的字段
//                }
//                return true; // 包含该字段或值
//            }
//        };
        logger.info("返回的值为：{}",JSON.toJSONString(result,filter1));
        logger.info("消耗的时间为：{}",System.currentTimeMillis()-startTime);
        return result;
    }

}
