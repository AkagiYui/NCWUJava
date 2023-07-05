package com.akagiyui.entity;

import lombok.Data;

/**
 * 用户实体类
 *
 * @author AkagiYui
 */
@Data
public class User {
    /**
     * 学号
     */
    private String number;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别
     */
    private String gender;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 地区
     */
    private String region;
}
