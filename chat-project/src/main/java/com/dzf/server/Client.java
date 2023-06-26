package com.dzf.server;

import com.dzf.common.ClientStatus;
import com.dzf.common.message.*;
import com.dzf.server.entity.User;
import com.dzf.server.mapper.FriendMapper;
import com.dzf.server.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * 客户端连接
 *
 * @author AkagiYui
 */
@Slf4j
public class Client {
    private ClientStatus status;
    private ObjectOutputStream objectOutputStream;
    private User user;

    public Client(Socket socket) {
        this.status = ClientStatus.WAITING_LOGIN;
        new Thread(() -> {
            log.info("客户端连接，IP: {}，端口：{}", socket.getInetAddress().getHostAddress(), socket.getPort());
            try (InputStream inputStream = socket.getInputStream();
                 OutputStream outputStream = socket.getOutputStream()) {
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                objectOutputStream = new ObjectOutputStream(outputStream);
                // 接收消息
                while (!socket.isClosed()) {
                    Object message = objectInputStream.readObject();
                    handleMessage(message);
                }
            } catch (IOException ignored) {
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                log.info("客户端连接断开。");
                Server.CLIENTS.remove(this);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 收到消息处理
     */
    private void handleMessage(Object message) {
        if (message instanceof LoginRequest login) {
            handleLogin(login);
        } else if (message instanceof FriendRequest addFriendRequest) {
            handleFriend(addFriendRequest);
        } else if (message instanceof MessageRequest message1) {
            transmitMessage(message1);
        } else {
            log.info("未知消息：" + message);
        }
    }

    /**
     * 好友聊天消息转发
     */
    private void transmitMessage(MessageRequest message1) {
        if (status != ClientStatus.WAITING_MESSAGE) {
            log.info("未登录，无法转发。");
            return;
        }
        log.info("{} -> {}: {}", user.getNumber(), message1.getTo(), message1.getContent());
        if (!FriendMapper.isFriend(user.getNumber(), message1.getTo())) {
            log.info("非好友关系，无法转发。");
            sendMessage(new MessageResponse(false, "非好友关系，无法转发。"));
            return;
        }
        boolean isOnline = false;
        for (Client client : Server.CLIENTS) {
            if (client.user.getNumber().equals(message1.getTo())) {
                isOnline = true;
                client.sendMessage(new MessageTransmit(user.getNumber(), message1.getContent()));
            }
        }
        if (!isOnline) {
            log.info("对方不在线。");
            sendMessage(new MessageResponse(false, "对方不在线。"));
        }
    }

    /**
     * 好友处理
     */
    private void handleFriend(FriendRequest addFriendRequest) {
        if (status != ClientStatus.WAITING_MESSAGE) {
            log.info("未登录，无法处理好友请求。");
            return;
        }
        if (!addFriendRequest.isDelete()) {
            log.info("{} 请求添加好友：{}", user.getNumber(), addFriendRequest.getNumber());
            if (FriendMapper.addFriend(user.getNumber(), addFriendRequest.getNumber())) {
                log.info("添加好友成功。");
                sendMessage(new FriendResponse(true, "", false));
            } else {
                log.info("添加好友失败。");
                sendMessage(new FriendResponse(false, "", false));
            }
        } else {
            log.info("{} 请求删除好友：{}", user.getNumber(), addFriendRequest.getNumber());
            if (FriendMapper.delFriend(user.getNumber(), addFriendRequest.getNumber())) {
                log.info("删除好友成功。");
                sendMessage(new FriendResponse(true, "", true));
            } else {
                log.info("删除好友失败。");
                sendMessage(new FriendResponse(false, "", true));
            }
        }
    }

    /**
     * 登录处理
     */
    private void handleLogin(LoginRequest login) {
        log.info("请求登录。" + login.getNumber());
        user = UserMapper.getUserByNumber(login.getNumber());
        if (user == null) {
            log.error("学号不存在。");
            sendMessage(new LoginResponse(false, "学号不存在。", null));
        } else {
            log.info("登录成功。{}", user);
            status = ClientStatus.WAITING_MESSAGE;
            sendMessage(new LoginResponse(true, "", user));
        }
    }

    /**
     * 发送消息
     */
    private void sendMessage(Object message) {
        if (objectOutputStream == null) {
            return;
        }
        log.info("发送消息：" + message);
        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
