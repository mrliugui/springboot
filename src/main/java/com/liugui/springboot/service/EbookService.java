package com.liugui.springboot.service;



import com.liugui.springboot.req.EbookReq;
import com.liugui.springboot.vo.PageVo;



public interface EbookService {
    PageVo bookList(EbookReq req);
}
