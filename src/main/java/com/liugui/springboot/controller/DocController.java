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
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/doc")
public class DocController {

    @Resource
    private DocService docService;


       @GetMapping("/all/{ebookId}")
    public ApiResponse getAll(@PathVariable Long ebookId){
        List<DocVo> docVos = docService.all(ebookId);
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
    }  @DeleteMapping("/delete")
    public ApiResponse deleteTreeData(@RequestBody String ids) {
      ids.substring(ids.indexOf(";"),ids.lastIndexOf("}"));
        List<String> list = Arrays.asList(ids);
        int i = docService.deleteByIds(list);
        if(i!=0){
            return ApiResponse.success();
        }
        else{
            return ApiResponse.error(ExceptionEnum.DELETE_EXCEPTION);
        }
    }
    @GetMapping("/content/{id}")
    public ApiResponse getContent(@PathVariable("id")Long id){
        String content = docService.getContent(id);
        if(content != null && !content.equals("")){
            return ApiResponse.success(content);
        }
        else{
            return ApiResponse.error(ExceptionEnum.QUERRY_EXCEPTION);
        }
    }
}
