package com.akagiyui.web.kenkoweb.exercise.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String nickname;
}
