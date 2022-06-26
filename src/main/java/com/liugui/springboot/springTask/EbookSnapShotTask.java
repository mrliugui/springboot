package com.liugui.springboot.springTask;

import com.liugui.springboot.service.EbookSnapshotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EbookSnapShotTask {

    private final static Logger logger = LoggerFactory.getLogger(EbookSnapShotTask.class);

   @Resource
   private EbookSnapshotService ebookSnapshotService;

    @Scheduled(cron ="5/30 * * * * ?")
    public void updateEbookTask(){
//        因为不经过aop，所以直接自己添加在该地方进行添加自定义流水号，以便于查看日志
//        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
        logger.info("现在开始图书快照");
        Long start = System.currentTimeMillis();
        int i = ebookSnapshotService.updateEbookSnapshot();
        logger.info("更新了{}条数据，使用了{}毫秒",i,System.currentTimeMillis()-start);
    }
}
