import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class TcpServer {
    private static final int PORT = 8888; // 监听端口
    private static final Logger logger = Logger.getLogger(TcpServer.class.getName());

    private ServerSocket serverSocket; // 服务器套接字
    private final ExecutorService threadPool; // 线程池

    public static void main(String[] args) {
        TcpServer server = new TcpServer();
        server.start();
    }

    public TcpServer() {
        threadPool = Executors.newCachedThreadPool(); // 创建线程池
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(PORT); // 创建服务器套接字
            logger.info("Server started on port: " + PORT); // 打印服务器启动信息
            //noinspection InfiniteLoopStatement
            while (true) { // 循环监听
                Socket clientSocket = serverSocket.accept();
                threadPool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            logger.severe("Error starting server: " + e); // 打印错误信息
        } finally {
            close();
        }
    }

    public void close() {
        try {
            serverSocket.close(); // 关闭服务器套接字
            threadPool.shutdown(); // 关闭线程池
        } catch (IOException e) {
            logger.warning("Error closing server: " + e);
        }
    }

    static class ClientHandler implements Runnable {
        private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());

        private final Socket clientSocket;
        private DataInputStream dis; // 输入流
        private DataOutputStream dos; // 输出流

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                dis = new DataInputStream(clientSocket.getInputStream());
                dos = new DataOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {
                logger.warning("Error creating streams: " + e);
            }
        }

        @Override
        public void run() {
            String message;
            try {
                while (true) {
                    message = dis.readUTF();
                    // 打印客户端发送的消息
                    logger.info("Received message from client: " + message);
                    if (message.equals("end")) {
                        break; // 如果客户端发送的消息为"end"，则结束循环
                    }
                    dos.writeUTF(message);
                    logger.info("Sent message to client: " + message);
                }
            } catch (IOException e) {
                logger.warning("Error reading from/writing to client: " + e);
            } finally {
                close();
            }
        }

        public void close() {
            try {
                dis.close();
                dos.close();
                clientSocket.close();
            } catch (IOException e) {
                logger.warning("Error closing streams/socket: " + e);
            }
        }
    }
}
