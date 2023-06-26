package com.dzf.client;

import com.dzf.common.ClientStatus;
import com.dzf.common.message.*;
import com.dzf.server.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author AkagiYui
 */
@Slf4j
public class Server {
    private ClientStatus status = ClientStatus.WAITING_LOGIN;
    private Socket socket;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private User user;
    private String friendNumber;

    public Server(Socket socket) {
        log.info("已连接到服务器，等待登录，请输入学号。");
        this.socket = socket;
        try {
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 接收消息
        new Thread(() -> {
            InputStream inputStream;
            ObjectInputStream objectInputStream;
            try {
                inputStream = socket.getInputStream();
                objectInputStream = new ObjectInputStream(inputStream);
                while (!socket.isClosed()) {
                    Object message = objectInputStream.readObject();
                    handleMessage(message);
                }
            } catch (SocketException e) {
                log.info("服务器连接已断开。");
                // 退出程序
                System.exit(0);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void handleInput(String input) {
        input = input.trim();
        if ("".equals(input)) {
            return;
        }
        if (status == ClientStatus.WAITING_LOGIN) {
            login(input);
        } else // 判断是否为选择聊天对象
        {
            if (input.startsWith("f ")) {
                String number = input.substring(2).trim();
                if (number.length() == 0) {
                    log.info("请输入聊天对象学号。");
                    return;
                }
                friendNumber = number;
                status = ClientStatus.WAITING_MESSAGE;
            } else if (input.startsWith("a ")) {
                String number = input.substring(2).trim();
                if (number.length() == 0) {
                    log.info("请输入好友学号。");
                    return;
                }
                sendMessage(new FriendRequest(number, false));
            } else if (input.startsWith("d ")) {
                String number = input.substring(2).trim();
                if (number.length() == 0) {
                    log.info("请输入好友学号。");
                    return;
                }
                sendMessage(new FriendRequest(number, true));
            } else if (status == ClientStatus.WAITING_MESSAGE) {
                sendMessage(new MessageRequest(friendNumber, input));
            } else {
                log.warn("请先选择聊天对象。");
            }
        }
    }

    private void login(String number) {
        log.debug("请求登录。" + number);
        sendMessage(new LoginRequest(number));
    }

    private void sendMessage(Object message) {
        log.debug("发送消息：" + message);
        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMessage(Object message) {
        log.debug("收到消息：" + message);
        if (message instanceof LoginResponse loginResponse) {
            if (loginResponse.isSuccess()) {
                status = ClientStatus.CHOOSING_FRIEND;
                user = loginResponse.getUser();
                log.info("登录成功：{}，请输入'f <number>'指定聊天对象。", user.getNickname());
                log.debug(user.toString());
            } else {
                log.info("登录失败：" + loginResponse.getMessage());
            }
        } else if (message instanceof MessageResponse messageResponse) {
            if (!messageResponse.isSuccess()) {
                log.error("发送失败：" + messageResponse.getMessage());
            }
        } else if (message instanceof FriendResponse friendResponse) {
            if (friendResponse.isDelete()) {
                if (friendResponse.isSuccess()) {
                    log.info("删除成功。");
                } else {
                    log.error("删除失败：" + friendResponse.getMessage());
                }
            } else {
                if (friendResponse.isSuccess()) {
                    log.info("添加成功。");
                } else {
                    log.error("添加失败：" + friendResponse.getMessage());
                }
            }
        } else if (message instanceof MessageTransmit messageTransmit) {
            log.info("好友<{}>消息：{}", messageTransmit.getFrom(), messageTransmit.getContent());
        }
        else {
            log.warn("未知消息：" + message);
        }
    }
}
