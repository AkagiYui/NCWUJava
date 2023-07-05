package com.akagiyui;

import com.google.gson.Gson;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Data;

import java.io.IOException;
import java.util.*;

/**
 * @author AkagiYui
 */
@Data
@ServerEndpoint("/chatSocket")
public class ChatSocket {
    private static Set<ChatSocket> sockets = new HashSet<>();
//	private  static  Map<String, ChatSocket>  sMap=new HashMap<String, ChatSocket>();

    private static List<String> names = new ArrayList<>();
    private Session session;
    private String username;
    private Gson gson = new Gson();

    @OnOpen
    public void open(Session session) {
        this.session = session;
        sockets.add(this);

        String queryString = session.getQueryString();
        System.out.println(queryString);
        this.username = queryString.substring(queryString.indexOf("=") + 1);
        names.add(this.username);


        Message message = new Message();
        message.setAlert(this.username + "进入聊天室！！");
        message.setNames(names);

        broadcast(sockets, gson.toJson(message));

    }

    @OnMessage
    public void receive(Session session, String msg) {

        Message message = new Message();
        message.setSendMsg(msg);
        message.setFrom(this.username);
        message.setDate(new Date().toString());

        broadcast(sockets, gson.toJson(message));
    }

    @OnClose
    public void close(Session session) {
        sockets.remove(this);
        names.remove(this.username);

        Message message = new Message();
        message.setAlert(this.username + "退出聊天室！！");
        message.setNames(names);

        broadcast(sockets, gson.toJson(message));
    }

    public void broadcast(Set<ChatSocket> ss, String msg) {

        for (ChatSocket chatSocket : ss) {
            try {
                chatSocket.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

