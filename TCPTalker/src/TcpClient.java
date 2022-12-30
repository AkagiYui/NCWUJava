import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class TcpClient {
    private static final String HOST = "127.0.0.1"; // 服务器地址
    private static final int PORT = 8888; // 服务器端口
    private static final Logger logger = Logger.getLogger(TcpServer.class.getName());

    private Socket clientSocket; // 客户端套接字

    public static void main(String[] args) {
        TcpClient client = new TcpClient();
        client.start();
    }

    public void start() {
        try {
            clientSocket = new Socket(HOST, PORT);
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

            // 开启读取服务器消息的线程
            new Thread(() -> {
                String message;
                try {
                    while (true) {
                        message = dis.readUTF();
                        logger.info("Received message from server: " + message);
                    }
                } catch (EOFException e) {
                    logger.info("Server closed connection");
                }
                catch (IOException e) {
                    logger.warning("Error reading from server: " + e);
                } finally {
                    close();
                }
            }).start();

            // 循环读取用户输入的消息并发送给服务器
            Scanner scanner = new Scanner(System.in);
            String message;
            //noinspection InfiniteLoopStatement
            while (true) {
                message = scanner.nextLine();
                dos.writeUTF(message);
                logger.info("Sent message to server: " + message);
            }
        } catch (IOException e) {
            logger.severe("Error starting client: " + e);
        } finally {
            close();
        }
    }

    public void close() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            logger.warning("Error closing client: " + e);
        }
    }
}
