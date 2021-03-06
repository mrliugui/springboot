package com.liugui.springboot.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liugui.springboot.dao.ContentMapper;
import com.liugui.springboot.dao.DocMapper;
import com.liugui.springboot.pojo.Content;
import com.liugui.springboot.pojo.Doc;
import com.liugui.springboot.req.DocReq;
import com.liugui.springboot.req.UpdateDocReq;
import com.liugui.springboot.service.DocService;
import com.liugui.springboot.util.CopyUtil;
import com.liugui.springboot.util.RedisUtil;
import com.liugui.springboot.util.RequestContext;
import com.liugui.springboot.util.SnowFlake;
import com.liugui.springboot.vo.DocVo;
import com.liugui.springboot.vo.PageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.rocketmq.spring.core.RocketMQTemplate;

@Service
public class DocServiceImpl implements DocService {
    private static final Logger logger = LoggerFactory.getLogger(DocServiceImpl.class);
    @Resource
    private DocMapper docMapper;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private WsService wsService;

//    @Resource
//    private RocketMQTemplate rocketMQTemplate;

    @Override
    public PageVo bookList(DocReq req)
    {
//        List<DocVo> docVos = new ArrayList<>();
//        for (Doc doc : docs) {
//            DocVo docVo = new DocVo();
//            BeanUtils.copyProperties(doc,docVo);
//            docVos.add(docVo);
//        }
        List<Doc> docs=null;
        if(req.getPageNum() == 0 && req.getPageSize() == 0) {
            req.setPageNum(1);
            req.setPageSize(5);
        }
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        if(!ObjectUtils.isEmpty(req.getName())){
            docs = docMapper.searchLike(req.getName());
        }else{
            docs = docMapper.searchAllBook();
        }
        PageInfo<Doc> pageInfo = new PageInfo<>(docs);
        logger.info("???????????????{}",pageInfo);
        List<DocVo> docVos=CopyUtil.copyList(docs,DocVo.class);
        PageVo<DocVo> pageVo = new PageVo<>();
        pageVo.setContent(docVos);
        pageVo.setTotal((int)pageInfo.getTotal());
        return pageVo;
    }
    @Override
    public List<DocVo> all(Long id) {
        List<Doc> docs=null;
        docs = docMapper.searchBookByEbookId(id);
        List<DocVo> docVos=CopyUtil.copyList(docs,DocVo.class);
        return docVos;
    }


    @Override
    public int deleteByIds(List<String> ids) {
        int i=docMapper.deleteByIds(ids);
        return i;
    }

    @Override
    public String getContent(Long id) {
        Content content = contentMapper.selectByPrimaryKey(id);
        if(!ObjectUtils.isEmpty(content)){
          docMapper.increaseViewById(id);
        }
        String contentStr  = content.getContent();
        return contentStr;
    }

    @Override
    public void vote(Long id) {
//        docMapper.increaseVoteById(id);
        String ip = RequestContext.getRemoteAddr();
        String key = ip + id;
        Map map = new HashMap();
//        ??????redis?????????????????????????????????????????????????????????
//        if(redisUtil.validateRepeat(ip+id,4*3600*24)){
        if (map.containsKey(key)) {
            docMapper.increaseVoteById(id);
            map.put(key, key);
        } else {
//            throw new BusinessException(ExceptionEnum.HAS_VOTED);

        }
        //??????redis??????????????????????????????????????????????????????webSocket???????????????????????????????????????????????????
        Doc doc = docMapper.selectByPrimaryKey(id);
        String logId = MDC.get("LOG_ID");
        wsService.sendInfo("???" + doc.getName() + "???", logId);
//        rocketMQTemplate.convertAndSend("VOTE","???" + doc.getName() + "???");
    }

    @Override
    public int updateSelectiveDoc(UpdateDocReq updateDocReq){
        if(!ObjectUtils.isEmpty(docMapper.selectByPrimaryKey(updateDocReq.getId()))){
            Doc newBook = CopyUtil.copy(updateDocReq, Doc.class);
            Content newContent = CopyUtil.copy(updateDocReq, Content.class);
            logger.info("???????????????id{}-??????{}",newBook.getId(),newBook.getName());
            int i = docMapper.updateByPrimaryKeySelective(newBook);
            contentMapper.insertSelective(newContent);
            return i;
        }else{
//            long id = UUID.randomUUID().toString().
            long id = snowFlake.nextId();
            updateDocReq.setId(id);
            Doc doc = CopyUtil.copy(updateDocReq, Doc.class);
            int i = docMapper.insertSelective(doc);
            Content content = CopyUtil.copy(updateDocReq, Content.class);
            contentMapper.insertSelective(content);
            return i;
        }
    }

    @Override
    public int deleteById(Long id) {
        int i = docMapper.deleteByPrimaryKey(id);
        return i;
    }
}
