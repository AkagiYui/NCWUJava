package com.dzf.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author AkagiYui
 */
@Data
public class User implements Serializable {
    private String number;
    private String nickname;
    private String gender;
    private String birthday;
    private Integer age;
    private String region;
}
