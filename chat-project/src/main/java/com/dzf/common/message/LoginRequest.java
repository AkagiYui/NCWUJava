package com.dzf.common.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author AkagiYui
 */
@Data
@AllArgsConstructor
public class LoginRequest implements Serializable {
    private String number;
}
