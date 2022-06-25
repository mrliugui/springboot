//package com.liugui.springboot.rocketMQ;
//
//import com.liugui.springboot.websocket.WebSocketServer;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//@Service
//@RocketMQMessageListener(consumerGroup = "default",topic ="VOTE" )
//public class VoteRocketMQ implements RocketMQListener<MessageExt> {
//
//    private static final Logger logger = LoggerFactory.getLogger(VoteRocketMQ.class);
//
//    @Resource
//    private WebSocketServer socketServer;
//
//    @Override
//    public void onMessage(MessageExt messageExt) {
//        byte[] body = messageExt.getBody();
//        String s = new String(body);
//        logger.info("rocketmq取得消息,{}",s);
//        socketServer.sendInfo(s);
//    }
//}
