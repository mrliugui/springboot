package com.liugui.springboot.dao;

import com.liugui.springboot.pojo.test;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface testMapper {
    public List<test> search();
}
