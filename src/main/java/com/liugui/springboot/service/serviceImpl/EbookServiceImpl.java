package com.liugui.springboot.service.serviceImpl;

import com.liugui.springboot.dao.EbookMapper;
import com.liugui.springboot.pojo.Ebook;
import com.liugui.springboot.service.EbookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookServiceImpl implements EbookService {

    @Resource
    private EbookMapper ebookMapper;

    @Override
    public List<Ebook> bookList()
    {
        return ebookMapper.searchAllBook();
    }
}
