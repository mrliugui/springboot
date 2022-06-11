package com.liugui.springboot.controller;

import com.liugui.springboot.common.ApiResponse;
import com.liugui.springboot.myEnum.ExceptionEnum;
import com.liugui.springboot.req.DocReq;
import com.liugui.springboot.req.UpdateDocReq;
import com.liugui.springboot.service.DocService;
import com.liugui.springboot.vo.DocVo;
import com.liugui.springboot.vo.PageVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/doc")
public class DocController {

    @Resource
    private DocService docService;


    @GetMapping("/all")
    public ApiResponse getAll(){
        List<DocVo> docVos = docService.all();
        return ApiResponse.success(docVos);
    }

    @GetMapping("/list")
    public ApiResponse getList(@Valid DocReq req){
        PageVo pageVo=docService.bookList(req);
        return ApiResponse.success(pageVo);
    }

    @PostMapping("/update")
    public ApiResponse update(@RequestBody @Validated UpdateDocReq updateDocReq){
        int i = docService.updateSelectiveDoc(updateDocReq);
        if(i!=0){
            return ApiResponse.success();
        }
        else{
            return ApiResponse.error(ExceptionEnum.UPDATE_EXCEPTION);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable("id")Long id){
        int i = docService.deleteById(id);
        if(i!=0){
            return ApiResponse.success();
        }
        else{
            return ApiResponse.error(ExceptionEnum.DELETE_EXCEPTION);
        }
    }
}
