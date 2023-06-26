package com.dzf.common.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author AkagiYui
 */
@Data
@AllArgsConstructor
public class FriendResponse implements Serializable {
    private boolean success;
    private String message;
    private boolean delete;
}
