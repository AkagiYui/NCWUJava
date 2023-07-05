package com.akagiyui;


import jakarta.websocket.Endpoint;
import jakarta.websocket.server.ServerApplicationConfig;
import jakarta.websocket.server.ServerEndpointConfig;

import java.util.Set;

/**
 * @author AkagiYui
 */

public class SocketConfig implements ServerApplicationConfig {

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {
        System.out.println("实现EndPoint接口的类数量：" + set.size());
        return null;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> ss) {
        // TODO Auto-generated method stub
        System.out.println("endPoint扫描到的数量：" + ss.size());

        return ss;
    }


}
