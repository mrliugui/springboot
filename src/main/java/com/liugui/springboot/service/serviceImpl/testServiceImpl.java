package com.liugui.springboot.service.serviceImpl;

import com.liugui.springboot.dao.testMapper;
import com.liugui.springboot.pojo.test;
import com.liugui.springboot.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class testServiceImpl implements TestService {

    @Resource
    private testMapper testMapper;

    @Override
    public List<test> getAll() {
    List list = testMapper.search();
    return list;
    }
}
