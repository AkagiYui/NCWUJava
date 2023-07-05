package com.akagiyui;

import lombok.Data;

import java.util.List;

/**
 * @author AkagiYui
 */
@Data
public class Message {
    private  String  alert;   //

    private List<String> names;

    private  String  sendMsg;

    private String  from;

    private String  date;

}
