package com.liugui.springboot.dao;

import com.liugui.springboot.pojo.Doc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocMapper {
    List<Doc> searchLike(@Param("name") String name);

    List<Doc> searchAllBook();

    List<Doc> searchBookByEbookId(Long ebookId);

    int deleteByPrimaryKey(Long id);

    int insert(Doc record);

    int insertSelective(Doc record);

    Doc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Doc record);

    int updateByPrimaryKey(Doc record);

    int deleteByIds(List<String> list);

    int increaseVoteById(Long id);

    int increaseViewById(Long id);
}