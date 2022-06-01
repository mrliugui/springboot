package com.liugui.springboot.controller;

import com.liugui.springboot.req.EbookReq;
import com.liugui.springboot.response.ApiResponse;
import com.liugui.springboot.service.EbookService;
import com.liugui.springboot.vo.EbookVo;
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
    public ApiResponse<List<EbookVo>> getAll( EbookReq req){
        List<EbookVo> ebookVos=ebookService.bookList(req);
        return ApiResponse.success(ebookVos);
    }
}
