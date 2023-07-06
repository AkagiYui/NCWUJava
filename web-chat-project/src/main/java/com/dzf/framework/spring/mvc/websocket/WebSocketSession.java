package com.dzf.framework.spring.mvc.websocket;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpHeaders;
import java.security.Principal;
import java.util.Map;

/**
 * @author AkagiYui
 */

public interface WebSocketSession extends Closeable {
    String getId();

    URI getUri();

    HttpHeaders getHandshakeHeaders();

    Map<String, Object> getAttributes();

    Principal getPrincipal();

    InetSocketAddress getLocalAddress();

    InetSocketAddress getRemoteAddress();

    String getAcceptedProtocol();

    void setTextMessageSizeLimit(int messageSizeLimit);

    int getTextMessageSizeLimit();

    void setBinaryMessageSizeLimit(int messageSizeLimit);

    int getBinaryMessageSizeLimit();

    void sendMessage(WebSocketMessage<?> message) throws IOException;

    boolean isOpen();

    @Override
    void close() throws IOException;
}
