package com.liugui.springboot.service.serviceImpl;

import com.liugui.springboot.dao.EbookMapper;
import com.liugui.springboot.pojo.Ebook;
import com.liugui.springboot.req.EbookReq;
import com.liugui.springboot.service.EbookService;
import com.liugui.springboot.util.CopyUtil;
import com.liugui.springboot.vo.EbookVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookServiceImpl implements EbookService {

    @Resource
    private EbookMapper ebookMapper;

    @Override
    public List<EbookVo> bookList(EbookReq req)
    {
//        List<EbookVo> ebookVos = new ArrayList<>();
//        for (Ebook ebook : ebooks) {
//            EbookVo ebookVo = new EbookVo();
//            BeanUtils.copyProperties(ebook,ebookVo);
//            ebookVos.add(ebookVo);
//        }
        List<Ebook> ebooks=null;
        if(!ObjectUtils.isEmpty(req.getName())){
            ebooks = ebookMapper.searchLike(req.getName());
        }
        ebooks = ebookMapper.searchAllBook();
        List<EbookVo> ebookVos=CopyUtil.copyList(ebooks,EbookVo.class);
        return ebookVos;
    }
}
