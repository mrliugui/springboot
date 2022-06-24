package com.liugui.springboot.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@ServerEndpoint("/ws/{token}")
public class WebSocketServer {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

//    给每一个用户设置一个唯一标识符：token
    private String token;
    private static Map<String, Session> map = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("token")String token){
        map.put(token,session);
        this.token=token;
        logger.info("有新连接,token:{},session id:{},当前连接数:{}",token,session.getId(),map.size());
    }

    @OnClose
    public void onClose(Session session){
        map.remove(this.token);
        logger.info("关闭连接,token:{},session id:{},当前连接数:{}",token,session.getId(),map.size());
    }

    @OnMessage
    public void onMessage(String message){
    logger.info("收到消息：{}，内容:{}",token,message);
    }
    @OnError
    public void onError(Throwable throwable,Session session){
    logger.error("产生错误{}",throwable);
    }

/*
     群发消息
 */

    public void sendInfo(String msg) {
     for(String token:map.keySet()){
         Session session = map.get(token);
         try {
             session.getBasicRemote().sendText(msg);
         } catch (IOException e) {
             logger.error("推送消息失败:{},内容{}",token,msg);
         }
         logger.info("推送消息成功：{},内容：{}",token,msg);
     }
    }
}
