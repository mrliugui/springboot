package com.liugui.springboot.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liugui.springboot.dao.EbookMapper;
import com.liugui.springboot.pojo.Ebook;
import com.liugui.springboot.req.EbookReq;
import com.liugui.springboot.req.UpdateEbookReq;
import com.liugui.springboot.service.EbookService;
import com.liugui.springboot.util.CopyUtil;
import com.liugui.springboot.util.SnowFlake;
import com.liugui.springboot.vo.EbookVo;
import com.liugui.springboot.vo.PageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookServiceImpl implements EbookService {
    private static final Logger logger = LoggerFactory.getLogger(EbookServiceImpl.class);
    @Resource
    private EbookMapper ebookMapper;


    @Resource
    private SnowFlake snowFlake;

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
        if(req.getPageNum() == 0 && req.getPageSize() == 0) {
            req.setPageNum(1);
            req.setPageSize(5);
        }
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        if(!ObjectUtils.isEmpty(req.getName())){
            ebooks = ebookMapper.searchLike(req.getName());
        }else{
            ebooks = ebookMapper.searchAllBook();
        }
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooks);
        logger.info("分页参数为{}",pageInfo);
        List<EbookVo> ebookVos=CopyUtil.copyList(ebooks,EbookVo.class);
        PageVo<EbookVo> pageVo = new PageVo<>();
        pageVo.setContent(ebookVos);
        pageVo.setTotal((int)pageInfo.getTotal());
        return pageVo;
    }

    @Override
    public int updateSelectiveEbook(UpdateEbookReq updateEbookReq){
        if(!ObjectUtils.isEmpty(ebookMapper.selectByPrimaryKey(updateEbookReq.getId()))){
            Ebook newBook = CopyUtil.copy(updateEbookReq, Ebook.class);
            logger.info("更新的书籍id{}-名称{}",newBook.getId(),newBook.getName());
            int i = ebookMapper.updateByPrimaryKeySelective(newBook);
            return i;
        }else{
//            long id = UUID.randomUUID().toString().
            long id = snowFlake.nextId();
            updateEbookReq.setId(id);
            Ebook ebook = CopyUtil.copy(updateEbookReq, Ebook.class);
            int i = ebookMapper.insertSelective(ebook);
            return i;
        }
    }

    @Override
    public int deleteById(Long id) {
        int i = ebookMapper.deleteByPrimaryKey(id);
        return i;
    }
}
