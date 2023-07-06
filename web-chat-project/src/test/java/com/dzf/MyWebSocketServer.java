package com.dzf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MyWebSocketServer {
    private static final int PORT = 8080;
    private static final Map<String, Client> clients = new HashMap<>();

    public static void main(String[] args) throws Exception {
        System.out.println("The HTTP server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                Socket socket = listener.accept();
                new HttpHandler(socket).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class HttpHandler extends Thread {
        private final Socket socket;

        public HttpHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream out = socket.getOutputStream();

                String requestLine = in.readLine();
                if (requestLine == null) {
                    return;
                }

                String[] requestParts = requestLine.split(" ");
                if (requestParts.length != 3) {
                    return;
                }

                String method = requestParts[0];
                String path = requestParts[1];
                String protocol = requestParts[2];

                if (!method.equals("GET") || !protocol.equals("HTTP/1.1")) {
                    return;
                }

                if (path.equals("/")) {
                    String sessionId = UUID.randomUUID().toString();
                    clients.put(sessionId, new Client(sessionId, socket));
                    String response = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: text/html\r\n"
                            + "Set-Cookie: session-id=" + sessionId + "\r\n"
                            + "\r\n"
                            + "<html><body>"
                            + "<h1>Welcome to the HTTP server</h1>"
                            + "<p>Your session ID is " + sessionId + "</p>"
                            + "<p><a href=\"/websocket\">Go to WebSocket server</a></p>"
                            + "</body></html>";
                    out.write(response.getBytes());
                    out.flush();
                } else if (path.equals("/websocket")) {
                    String sessionId = getSessionId(in);
                    if (sessionId == null || !clients.containsKey(sessionId)) {
                        String response = "HTTP/1.1 401 Unauthorized\r\n"
                                + "Content-Type: text/plain\r\n"
                                + "\r\n"
                                + "Unauthorized";
                        out.write(response.getBytes());
                        out.flush();
                        return;
                    }

                    String response = "HTTP/1.1 101 Switching Protocols\r\n"
                            + "Upgrade: websocket\r\n"
                            + "Connection: Upgrade\r\n"
                            + "Sec-WebSocket-Accept: " + getWebSocketAcceptHeader(in) + "\r\n"
                            + "\r\n";
                    out.write(response.getBytes());
                    out.flush();

                    String sessionIdHeader = "Session-Id: " + sessionId + "\r\n";
                    Client client = clients.get(sessionId);
                    client.setSocket(socket);
                    client.setOut(new PrintWriter(socket.getOutputStream(), true));
                    client.start();
                }
            } catch (IOException e) {
                System.out.println("Error handling client: " + e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        private String getSessionId(BufferedReader in) throws IOException {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("Cookie:")) {
                    String[] cookieParts = line.split(";");
                    for (String cookiePart : cookieParts) {
                        String[] keyValue = cookiePart.split("=");
                        if (keyValue.length == 2 && keyValue[0].trim().equals("session-id")) {
                            return keyValue[1].trim();
                        }
                    }
                }
                if (line.trim().isEmpty()) {
                    break;
                }
            }
            return null;
        }

        private String getWebSocketAcceptHeader(BufferedReader in) throws IOException, NoSuchAlgorithmException {
            String line;
            String key = null;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("Sec-WebSocket-Key:")) {
                    key = line.substring("Sec-WebSocket-Key:".length()).trim();
                }
                if (line.trim().isEmpty()) {
                    break;
                }
            }
            if (key == null) {
                return null;
            } else {
                String acceptKey = key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
                byte[] sha1 = java.security.MessageDigest.getInstance("SHA-1").digest(acceptKey.getBytes());
                return new String(java.util.Base64.getEncoder().encode(sha1));
            }
        }
    }

    private static class Client extends Thread {
        private final String sessionId;
        private Socket socket;
        private PrintWriter out;

        public Client(String sessionId, Socket socket) {
            this.sessionId = sessionId;
            this.socket = socket;
        }

        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        public void setOut(PrintWriter out) {
            this.out = out;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                byte[] buffer = new byte[1024];
                while (socket.isConnected()) {
                    int length = socket.getInputStream().read(buffer);
                    if (length == -1) {
                        break;
                    }

                    int opcode = buffer[0] & 0x0f;
                    if (opcode == 8) {
                        break;
                    }

                    int payloadLength = buffer[1] & 0x7f;
                    int maskingKeyStart = 2;
                    if (payloadLength == 126) {
                        payloadLength = ((buffer[2] & 0xff) << 8) | (buffer[3] & 0xff);
                        maskingKeyStart = 4;
                    } else if (payloadLength == 127) {
                        payloadLength = ((buffer[2] & 0xff) << 56) | ((buffer[3] & 0xff) << 48)
                                | ((buffer[4] & 0xff) << 40) | ((buffer[5] & 0xff) << 32)
                                | ((buffer[6] & 0xff) << 24) | ((buffer[7] & 0xff) << 16)
                                | ((buffer[8] & 0xff) << 8) | (buffer[9] & 0xff);
                        maskingKeyStart = 10;
                    }

                    byte[] maskingKey = new byte[] {
                            buffer[maskingKeyStart],
                            buffer[maskingKeyStart + 1],
                            buffer[maskingKeyStart + 2],
                            buffer[maskingKeyStart + 3]
                    };

                    byte[] payload = new byte[payloadLength];
                    for (int i = 0; i < payloadLength; i++) {
                        payload[i] = (byte) (buffer[maskingKeyStart + 4 + i] ^ maskingKey[i % 4]);
                    }

                    String message = new String(payload);
                    for (Client client : clients.values()) {
                        if (client != this && client.out != null) {
                            synchronized (client.out) {
                                client.out.println(message);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error handling client: " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket: " + e);
                } finally {
                    clients.remove(sessionId);
                }
            }
        }
    }
}
