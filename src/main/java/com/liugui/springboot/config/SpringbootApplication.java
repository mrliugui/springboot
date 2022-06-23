package com.liugui.springboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;

//因为springbootApplication注解包含了componentScan注解，但是该所包含的注解只是包括该包以下的子包
//该注解也可以扫描多个包，写成一个列表，例子如下{"com.liugui","com.test"}
@ComponentScan("com.liugui")
@SpringBootApplication
@MapperScan(basePackages = "com.liugui.springboot.dao")
@EnableScheduling
public class SpringbootApplication {
    private static final Logger LOG = LoggerFactory.getLogger(SpringbootApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        LOG.info("启动成功");
        LOG.info("地址：\thttp://127.0.0.1:{}",env.getProperty("server.port"));
//        SpringApplication.run(SpringbootApplication.class, args);
    }

}
