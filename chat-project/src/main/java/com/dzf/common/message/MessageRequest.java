package com.dzf.common.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author AkagiYui
 */
@Data
@AllArgsConstructor
public class MessageRequest implements Serializable {
    private String to;
    private String content;
}
