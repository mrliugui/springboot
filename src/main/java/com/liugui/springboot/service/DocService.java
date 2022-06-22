package com.liugui.springboot.service;


import com.liugui.springboot.req.DocReq;
import com.liugui.springboot.req.UpdateDocReq;
import com.liugui.springboot.vo.DocVo;
import com.liugui.springboot.vo.PageVo;

import java.util.List;


public interface DocService {
    PageVo bookList(DocReq req);
    int updateSelectiveDoc(UpdateDocReq updateDocReq);
    int deleteById(Long id);
    List<DocVo> all(Long id);
    int deleteByIds(List<String> ids);
    String getContent(Long id);
    void vote(Long id);
}
