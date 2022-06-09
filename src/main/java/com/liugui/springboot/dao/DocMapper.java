package com.liugui.springboot.dao;

import com.liugui.springboot.pojo.Doc;

public interface DocMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Doc record);

    int insertSelective(Doc record);

    Doc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Doc record);

    int updateByPrimaryKey(Doc record);
}