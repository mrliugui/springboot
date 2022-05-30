package com.liugui.springboot;

import org.omg.CORBA.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class SpringbootApplication {
    private static final Logger LOG = LoggerFactory.getLogger(SpringbootApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        LOG.info("启动成功");
        LOG.info("地址：\thttp://127.0.0.1:{}",((ConfigurableEnvironment) env).getProperty("server.port"));
//        SpringApplication.run(SpringbootApplication.class, args);
    }

}
