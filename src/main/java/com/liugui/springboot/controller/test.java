package com.liugui.springboot.controller;

import com.liugui.springboot.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

//import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class test {
//    可以使用默认值利用冒号隔开
//    @Value("${name:liugui}")

    @Resource
    private TestService testService;

//    @PostMapping("/test")
//    public String test1(String name){
//        return "hello world"+name;
//    }
    @GetMapping("/test1")
    public List getAll(){
        return testService.getAll();
    }
}
