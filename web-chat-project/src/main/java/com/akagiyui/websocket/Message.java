package com.akagiyui.websocket;

import lombok.Data;

import java.util.List;

/**
 * WebSocket 消息
 * @author AkagiYui
 */
@Data
public class Message {
    private String  date;

    private  String  alert;
    private List<String> names;

    private  String  message;
    private String  from;
}
