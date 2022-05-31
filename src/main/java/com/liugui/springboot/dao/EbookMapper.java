package com.liugui.springboot.dao;

import com.liugui.springboot.pojo.Ebook;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EbookMapper {
    List<Ebook> searchAllBook();

    int deleteByPrimaryKey(Long id);

    int insert(Ebook record);

    int insertSelective(Ebook record);

    Ebook selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ebook record);

    int updateByPrimaryKey(Ebook record);
}