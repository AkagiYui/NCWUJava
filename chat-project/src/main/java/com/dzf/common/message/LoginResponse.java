package com.dzf.common.message;

import com.dzf.server.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author AkagiYui
 */
@Data
@AllArgsConstructor
public class LoginResponse implements Serializable {
    private boolean success;
    private String message;
    private User user;
}
