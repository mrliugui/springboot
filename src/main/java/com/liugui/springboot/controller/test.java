package com.liugui.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
    @PostMapping("/test")
    public String test(String name){
        return "hello world"+name;
    }
}
