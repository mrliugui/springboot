package com.liugui.springboot.service;


import com.liugui.springboot.req.EbookReq;
import com.liugui.springboot.req.UpdateEbookReq;
import com.liugui.springboot.vo.PageVo;


public interface EbookService {
    PageVo bookList(EbookReq req);
    int updateSelectiveEbook(UpdateEbookReq updateEbookReq);
    int deleteById(Long id);
}
