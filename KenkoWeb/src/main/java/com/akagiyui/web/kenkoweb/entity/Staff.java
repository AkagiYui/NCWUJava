package com.akagiyui.web.kenkoweb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AkagiYui
 */

@Data
@NoArgsConstructor
public class Staff {
    Integer id;
    String username;
    String password;
    String email;
    String nickname;
    Boolean isManager;
}
