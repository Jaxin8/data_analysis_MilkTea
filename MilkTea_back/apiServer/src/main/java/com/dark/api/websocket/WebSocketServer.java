package com.dark.api.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * websocket交互处理器
 */
@Component
@ServerEndpoint("/ws/{clientId}")   //网页发送/ ws://localhost/ws/110
public class WebSocketServer {

    //存放会话对象
    private static Map<String, Session> sessionMap = new HashMap();
    //KEY       VALUE
    //110                    SESSION对象

    /**
     * 监听建立连接事件
     * @param session
     * @param clientId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("clientId") String clientId) {
        System.out.println("与"+clientId+"客户端建立连接");
        sessionMap.put(clientId, session);


    }

    /**
     * 监听接收消息事件
     * @param message
     * @param clientId
     */
    @OnMessage
    public void onMessage(String message, @PathParam("clientId") String clientId) {
        System.out.println("接收到" + clientId + "客户端的信息:" + message);


        StringBuilder SB = new StringBuilder(message);
        SB.reverse();

        String responseMsg = SB.toString();

        sendToClient(clientId,responseMsg);

    }

    /**
     * 监听断开连接事件
     * @param clientId
     */
    @OnClose
    public void onClose(@PathParam("clientId") String clientId) {
        System.out.println(clientId+"客户端断开连接");
        sessionMap.remove(clientId);
    }

    /**
     * 给指定客户端发送消息
     * @param clientId
     * @param message
     */
    public void sendToClient(String clientId,String message) {
        Session session = sessionMap.get(clientId);
        if(session==null){
            System.out.println("客户端不在线");
        }else{
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
