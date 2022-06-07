package com.liugui.springboot.dao;

import com.liugui.springboot.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    List<Category> searchLike(@Param("name") String name);

    List<Category> searchAllBook();

    Category selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);

    int insert(Category category);

    int insertSelective(Category record);

    int updateByPrimaryKeySelective(Category category);
}