package com.dzf.common.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author AkagiYui
 */
@Data
@AllArgsConstructor
public class MessageTransmit implements Serializable {
    private String from;
    private String content;
}
