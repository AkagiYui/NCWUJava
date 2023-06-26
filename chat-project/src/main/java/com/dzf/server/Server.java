package com.dzf.server;

import com.dzf.common.message.MessageTransmit;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 聊天室 服务端
 *
 * @author AkagiYui
 */
@Slf4j
public class Server {
    /** 所有客户端连接 */
    public static final List<Client> CLIENTS = new ArrayList<>();
    /** 离线消息 {目标用户ID: [消息1，消息2]} */
    public static final Map<String, Queue<MessageTransmit>> OFFLINE_MESSAGES = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        log.info("服务器启动。");

        // 创建服务器Socket
        ServerSocket server = new ServerSocket(3333);
        Thread serverMainThread = new Thread(() -> {
            while (true) {
                try {
                    // 阻塞等待客户端连接
                    Socket client = server.accept();
                    // 处理客户端连接
                    CLIENTS.add(new Client(client));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        serverMainThread.start();
        serverMainThread.join();
    }
}
