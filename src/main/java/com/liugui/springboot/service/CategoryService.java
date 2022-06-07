package com.liugui.springboot.service;


import com.liugui.springboot.req.CategoryReq;
import com.liugui.springboot.req.UpdateCategoryReq;
import com.liugui.springboot.vo.PageVo;


public interface CategoryService {
    PageVo bookList(CategoryReq req);
    int updateSelectiveCategory(UpdateCategoryReq updateCategoryReq);
    int deleteById(Long id);
}
