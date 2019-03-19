package com.line.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2018/4/13.
 */
@ServerEndpoint(value = "/msgServer")
@Component
public class WebSocketServer {

    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    public static Session session = null;	

    @OnMessage
    public void onMessage(String msg){
        sendMsg(msg);
    }

    @OnOpen
    public void onOpen(Session session) {
    	WebSocketServer.session = session;
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
    	WebSocketServer.session = null;
    }

    public static void sendMsg(String msg) {
        log.info(msg);
        if (session != null) {
            synchronized (session) {
                try {
                    session.getBasicRemote().sendText(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void sendConsoleMsg(String str, String color) {
//        sendMsg(new ConsoleDataObj(str, color, MsgType.CONSOLE_MSG).toString());

    }
}
