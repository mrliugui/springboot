package com.liugui.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
//    可以使用默认值利用冒号隔开
    @Value("${name:liugui}")
    private String name1;
    @PostMapping("/test")
    public String test(String name){
        System.out.println(name1);
        return "hello world"+name+"  "+name1;
    }
}
