package com.liugui.springboot.controller;

import com.liugui.springboot.common.ApiResponse;
import com.liugui.springboot.req.EbookReq;
import com.liugui.springboot.service.EbookService;
import com.liugui.springboot.vo.PageVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public ApiResponse getAll( EbookReq req){
        PageVo pageVo=ebookService.bookList(req);
        return ApiResponse.success(pageVo);
    }
}
