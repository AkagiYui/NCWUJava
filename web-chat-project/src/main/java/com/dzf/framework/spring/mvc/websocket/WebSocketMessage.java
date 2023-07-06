package com.dzf.framework.spring.mvc.websocket;

/**
 * @author AkagiYui
 */

public interface WebSocketMessage<T> {
    T getPayload();

    int getPayloadLength();

    boolean isLast();
}
