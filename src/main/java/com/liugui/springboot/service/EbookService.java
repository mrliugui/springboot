package com.liugui.springboot.service;



import com.liugui.springboot.req.EbookReq;
import com.liugui.springboot.vo.EbookVo;

import java.util.List;

public interface EbookService {
    List<EbookVo> bookList(EbookReq req);
}
