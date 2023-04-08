package com.akagiyui.web.kenkoweb.entity;

import lombok.Data;

/**
 * @author AkagiYui
 */

@Data
public class Staff {
    Integer id;
    String username;
    String password;
    String email;
    String nickname;
    Boolean isManager;
}
