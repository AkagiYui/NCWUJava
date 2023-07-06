package com.akagiyui.websocket;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.SneakyThrows;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WebSocket Handler
 * @author AkagiYui
 */
@ServerEndpoint("/chat")
public class ChatServlet {
    /**
     * 所有连接
     */
    private static final Map<Session, ChatServlet> SOCKETS = new HashMap<>();
    /**
     * JSON 序列化器
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 当前连接
     */
    private Session session;
    /**
     * 用户名
     */
    private String username;

    /**
     * 建立连接
     * @param session 当前连接
     */
    @OnOpen
    public void open(Session session) {
        this.session = session;
        SOCKETS.put(session, this);

        // 获取用户名
        String queryString = session.getQueryString();
        System.out.println(queryString);
        this.username = queryString.substring(queryString.indexOf("=") + 1);

        Message message = new Message();
        message.setAlert(this.username + " 进入聊天室！！");
        message.setNames(getUsernames());

        broadcast(message);
    }

    /**
     * 接收消息
     * @param session 当前连接
     * @param msg 消息
     */
    @OnMessage
    public void receive(Session session, String msg) {
        Message message = new Message();
        message.setMessage(msg);
        message.setFrom(this.username);
        message.setDate(getCurrentTime());

        broadcast(message);
    }

    /**
     * 关闭连接
     * @param session 当前连接
     */
    @OnClose
    public void close(Session session) {
        SOCKETS.remove(session);

        Message message = new Message();
        message.setAlert(this.username + " 退出聊天室！！");
        message.setNames(getUsernames());

        broadcast(message);
    }

    /**
     * 广播消息
     * @param msg 消息
     */
    @SneakyThrows
    public static void broadcast(Object msg) {
        for (ChatServlet chatSocket : SOCKETS.values()) {
            if (chatSocket.session.isOpen()) {
                chatSocket.session.getAsyncRemote().sendText(OBJECT_MAPPER.writeValueAsString(msg));
            }
        }
    }

    /**
     * 获取所有用户名
     * @return 用户名列表
     */
    public static List<String> getUsernames() {
        List<String> usernames = new ArrayList<>();
        for (ChatServlet chatSocket : SOCKETS.values()) {
            usernames.add(chatSocket.username);
        }
        return usernames;
    }

    /**
     * 获取当前时间
     * @return 当前时间
     */
    public static String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
    }
}

