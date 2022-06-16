package com.liugui.springboot.service.serviceImpl;

import com.liugui.springboot.dao.ContentMapper;
import com.liugui.springboot.pojo.Content;
import com.liugui.springboot.service.ContentService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ContentServiceImpl implements ContentService {

    @Resource
    private ContentMapper contentMapper;

    @Override
    public Content getDetail(Long id) {
        Content content = contentMapper.selectByPrimaryKey(id);
        return content;
    }
}
