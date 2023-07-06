package com.dzf.framework.spring.mvc.websocket;

/**
 * @author AkagiYui
 */

public interface WebSocketHandler {
    void afterConnectionEstablished(WebSocketSession session) throws Exception;

    void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception;

    void handleTransportError(WebSocketSession session, Throwable exception) throws Exception;

    void afterConnectionClosed(WebSocketSession session) throws Exception;

}
