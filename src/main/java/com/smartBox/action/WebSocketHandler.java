package com.smartBox.action;


import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.security.auth.login.Configuration;

public class WebSocketHandler extends AbstractWebSocketHandler{

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println("开始建立连接");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String key = Controller.authss;
        if (key.equals("no")){
            System.out.println("no link!");
        }else{
            session.sendMessage(new TextMessage(key));
            Controller.authss = "no";
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println("关闭连接");
    }
}
