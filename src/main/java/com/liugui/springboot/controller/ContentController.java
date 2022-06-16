package com.liugui.springboot.controller;

import com.liugui.springboot.common.ApiResponse;
import com.liugui.springboot.myEnum.ExceptionEnum;
import com.liugui.springboot.pojo.Content;
import com.liugui.springboot.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/content/detail")
    public ApiResponse getDetail(@RequestParam Long id){
        Content content = contentService.getDetail(id);
        if(ObjectUtils.isEmpty(content)){
            return ApiResponse.error(ExceptionEnum.GET_CONTENT_NULL);
        }
        return ApiResponse.success(content);
    }
}
