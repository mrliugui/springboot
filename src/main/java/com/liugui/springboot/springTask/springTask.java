package com.liugui.springboot.springTask;

import com.liugui.springboot.service.EbookService;
import com.liugui.springboot.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class springTask {

    private final static Logger logger = LoggerFactory.getLogger(springTask.class);

    @Resource
    private EbookService ebookService;

    @Resource
    private SnowFlake snowFlake;

    @Scheduled(cron ="1/30 * * * * ?")
    public void updateEbookTask(){
//        因为不经过aop，所以直接自己添加在该地方进行添加自定义流水号，以便于查看日志
//        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
        logger.info("现在正在开始图书定时更新");
        Long start = System.currentTimeMillis();
        int i=ebookService.updateEbookDetail();
       logger.info("更新了{}条数据，使用了{}毫秒",i,System.currentTimeMillis()-start);
    }
}
