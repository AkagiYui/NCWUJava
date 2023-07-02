package com.dzf.framework.tomcat;

/**
 * 土制 HTTP 服务器
 * @author AkagiYui
 */

public class HttpServer {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        BasicHttpServer.startHttpServer();
    }
}
