package com.liugui.springboot.controller;

import com.liugui.springboot.pojo.Ebook;
import com.liugui.springboot.response.ApiResponse;
import com.liugui.springboot.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public ApiResponse<List<Ebook>> getAll(){
        List<Ebook> ebooks =  ebookService.bookList();
        return ApiResponse.success(ebooks);
    }
}
