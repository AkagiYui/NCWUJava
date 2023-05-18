package com.akagiyui.ex4;

import lombok.Data;

/**
 * @author AkagiYui
 */
@Data
public class Account {
    private Integer id;       	// 账户id
    private String username; 	// 用户名
    private Double balance;  	// 账户余额
}
