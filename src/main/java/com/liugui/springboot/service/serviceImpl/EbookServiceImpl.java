package com.liugui.springboot.service.serviceImpl;

import com.liugui.springboot.dao.EbookMapper;
import com.liugui.springboot.pojo.Ebook;
import com.liugui.springboot.service.EbookService;
import com.liugui.springboot.util.CopyUtil;
import com.liugui.springboot.vo.EbookVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookServiceImpl implements EbookService {

    @Resource
    private EbookMapper ebookMapper;

    @Override
    public List<EbookVo> bookList(String name)
    {
//        List<EbookVo> ebookVos = new ArrayList<>();
        List<Ebook> ebooks = ebookMapper.searchLike(name);
//        for (Ebook ebook : ebooks) {
//            EbookVo ebookVo = new EbookVo();
//            BeanUtils.copyProperties(ebook,ebookVo);
//            ebookVos.add(ebookVo);
//        }
        List<EbookVo> ebookVos=CopyUtil.copyList(ebooks,EbookVo.class);
        return ebookVos;
    }
}
