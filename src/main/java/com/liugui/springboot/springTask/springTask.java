package com.liugui.springboot.springTask;

import com.liugui.springboot.service.EbookService;
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

    @Scheduled(cron ="1/30 * * * * ?")
    public void updateEbookTask(){
        logger.info("现在正在开始图书定时更新");
        Long start = System.currentTimeMillis();
        int i=ebookService.updateEbookDetail();
       logger.info("更新了{}条数据，使用了{}毫秒",i,System.currentTimeMillis()-start);
    }
}
