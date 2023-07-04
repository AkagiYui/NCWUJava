package com.dzf.entity;

import lombok.Data;

/**
 * 用户实体类
 *
 * @author AkagiYui
 */
@Data
public class User {
    private String number;
    private String password;
    private String nickname;
    private String gender;
    private String birthday;
    private Integer age;
    private String region;
}
