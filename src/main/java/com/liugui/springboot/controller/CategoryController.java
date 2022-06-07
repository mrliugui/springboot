package com.liugui.springboot.controller;

import com.liugui.springboot.common.ApiResponse;
import com.liugui.springboot.myEnum.ExceptionEnum;
import com.liugui.springboot.req.CategoryReq;
import com.liugui.springboot.req.UpdateCategoryReq;
import com.liugui.springboot.service.CategoryService;
import com.liugui.springboot.vo.PageVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    @GetMapping("/list")
    public ApiResponse getAll(@Valid CategoryReq req){
        PageVo pageVo=categoryService.bookList(req);
        return ApiResponse.success(pageVo);
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody UpdateCategoryReq updateCategoryReq){
        int i = categoryService.updateSelectiveCategory(updateCategoryReq);
        if(i!=0){
            return ApiResponse.success();
        }
        else{
            return ApiResponse.error(ExceptionEnum.UPDATE_EXCEPTION);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable("id")Long id){
        int i = categoryService.deleteById(id);
        if(i!=0){
            return ApiResponse.success();
        }
        else{
            return ApiResponse.error(ExceptionEnum.DELETE_EXCEPTION);
        }
    }
}
