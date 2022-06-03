package com.liugui.springboot.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liugui.springboot.dao.EbookMapper;
import com.liugui.springboot.pojo.Ebook;
import com.liugui.springboot.req.EbookReq;
import com.liugui.springboot.service.EbookService;
import com.liugui.springboot.util.CopyUtil;
import com.liugui.springboot.vo.EbookVo;
import com.liugui.springboot.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookServiceImpl implements EbookService {

    @Resource
    private EbookMapper ebookMapper;

    @Override
    public PageVo bookList(EbookReq req)
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
        if(req.getPageNum() == 0 && req.getPageSize() == 0){
            req.setPageNum(1);
            req.setPageSize(5);
        }
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        ebooks = ebookMapper.searchAllBook();
        List<EbookVo> ebookVos=CopyUtil.copyList(ebooks,EbookVo.class);
        PageInfo<EbookVo> pageInfo = new PageInfo<>(ebookVos);
        PageVo<EbookVo> pageVo = new PageVo<>();
        pageVo.setContent(ebookVos);
        pageVo.setTotal((int)pageInfo.getTotal());
        return pageVo;
    }
}
