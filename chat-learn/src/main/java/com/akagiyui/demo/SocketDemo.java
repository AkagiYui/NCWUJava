package com.akagiyui.demo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author AkagiYui
 */
public class SocketDemo {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080); // 监听端口8080
        while (true) {
            Socket socket = serverSocket.accept(); // 接收客户端请求
            InputStream inputStream = socket.getInputStream(); // 获取输入流
            OutputStream outputStream = socket.getOutputStream(); // 获取输出流

            // 读取HTTP请求内容
            StringBuilder request = new StringBuilder();
            int length = -1;
            byte[] buffer = new byte[1024];
            while ((length = inputStream.read(buffer)) != -1) {
                request.append(new String(buffer, 0, length));
                if (length < buffer.length) break;
            }

            // 解析请求
            String[] requestLines = request.toString().split("\r\n");
            String[] requestParams = requestLines[0].split(" ");
            String method = requestParams[0]; // 请求方式
            String url = requestParams[1]; // 请求URL
            String version = requestParams[2]; // HTTP版本号

            // 处理请求
            if (url.equals("/")) {
                // 跳转到index.html页面
                FileInputStream fis = new FileInputStream("C:\\Users\\jbb\\Desktop\\index.html");
                byte[] fileBytes = fis.readAllBytes();
                String content = new String(fileBytes, "UTF-8");
                String responseHead = "HTTP/1.1 200 OK\r\nContent-Type: text/html;charset=UTF-8\r\nContent-Length: " + content.length() + "\r\n\r\n";
                outputStream.write(responseHead.getBytes("UTF-8"));
                outputStream.write(content.getBytes("UTF-8"));
                fis.close();
            } else if (url.equals("/user")) {
                // 处理/user请求
                String content = "Hello, User!";
                String responseHead = "HTTP/1.1 200 OK\r\nContent-Type: text/html;charset=UTF-8\r\nContent-Length: " + content.length() + "\r\n\r\n";
                outputStream.write(responseHead.getBytes("UTF-8"));
                outputStream.write(content.getBytes("UTF-8"));
            } else {
                // 处理其他请求
                String content = "404 Not Found";
                String responseHead = "HTTP/1.1 404 Not Found\r\nContent-Type: text/plain;charset=UTF-8\r\nContent-Length: " + content.length() + "\r\n\r\n";
                outputStream.write(responseHead.getBytes("UTF-8"));
                outputStream.write(content.getBytes("UTF-8"));
            }

            // 关闭连接
            inputStream.close();
            outputStream.close();
            socket.close();
        }
    }
}
