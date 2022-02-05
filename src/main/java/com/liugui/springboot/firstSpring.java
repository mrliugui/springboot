package com.liugui.springboot;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class firstSpring {
    @GetMapping("/request1")
    public String test(@RequestParam(required = false,defaultValue = "0") String hobby) {
        return hobby;
    }


        @GetMapping( "/request/{id}" )
        public Integer test(@PathVariable("id") Integer hobby) {
            return hobby;
        }
    }

