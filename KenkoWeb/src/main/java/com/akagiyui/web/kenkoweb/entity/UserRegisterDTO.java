package com.akagiyui.web.kenkoweb.entity;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String username;
    private String password;
    private String email;
}
