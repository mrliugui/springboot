package com.liugui.springboot.controller;

import com.liugui.springboot.common.ApiResponse;
import com.liugui.springboot.myEnum.ExceptionEnum;
import com.liugui.springboot.req.EbookReq;
import com.liugui.springboot.req.UpdateEbookReq;
import com.liugui.springboot.service.EbookService;
import com.liugui.springboot.vo.PageVo;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/update")
    public ApiResponse update(@RequestBody UpdateEbookReq UpdateEbookReq){
        int i = ebookService.updateSelectiveEbook(UpdateEbookReq);
        if(i!=0){
            return ApiResponse.success();
        }
        else{
            return ApiResponse.error(ExceptionEnum.UPDATE_EXCEPTION);
        }
    }
}
