package com.dzf.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author AkagiYui
 */
@Data
public class Friend implements Serializable {
    private String number1;
    private String number2;
}
