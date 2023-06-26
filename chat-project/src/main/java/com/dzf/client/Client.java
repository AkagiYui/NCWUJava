package com.dzf.client;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * @author AkagiYui
 */
@Slf4j
public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3333;

    public static void main(String[] args) throws IOException {
        Server server;

        try {
            // 实例化客户端
            Socket client = new Socket(HOST, PORT);
            server = new Server(client);

            // Scanner 读取用户输入
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if ("__exit".equals(input)) {
                    break;
                }
                server.handleInput(input);
            }
        } catch (ConnectException e) {
            log.error("服务器未启动。");
        } catch (SocketException e) {
            log.error("服务器已断开。");
        }
        log.info("客户端关闭。");
    }
}
