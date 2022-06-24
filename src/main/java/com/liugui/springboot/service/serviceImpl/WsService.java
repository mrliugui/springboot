package com.liugui.springboot.service.serviceImpl;

import com.liugui.springboot.websocket.WebSocketServer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
//关于该注解绝对不可以在同一个类方法互相调用，事务调用也是这样的
@Async
public class WsService {

    @Resource
    private WebSocketServer webSocketServer;

    public void sendInfo(String msg,String id){
//        关于与点赞逻辑保持同一个流水号，有利于日志查看
//        MDC.put("LOG_ID",id);
        webSocketServer.sendInfo(msg);
    }
}
