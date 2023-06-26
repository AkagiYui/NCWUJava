package com.dzf.common.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author AkagiYui
 */
@Data
@AllArgsConstructor
public class FriendRequest implements Serializable {
    private String number;
    private boolean delete;
}
