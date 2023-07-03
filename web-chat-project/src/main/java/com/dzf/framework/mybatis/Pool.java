package com.dzf.framework.mybatis;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库连接池
 * @author AkagiYui
 */
public class Pool {
    private final String url;
    private final String username;
    private final String password;
    private final String driver;
    private final List<Connection> connectionPool;
    private static final int MAX_CONNECTION = 10;

    public Pool(String url, String username, String password, String driver) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driver = driver;
        this.connectionPool = new ArrayList<>();
    }

    public void start() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        for (int i = 0; i < MAX_CONNECTION; i++) {
            Connection conn = DriverManager.getConnection(url, username, password);
            connectionPool.add(conn);
        }
    }

    public synchronized Connection getConnection() throws SQLException {
        while (connectionPool.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new SQLException("Interrupted while waiting for a connection", e);
            }
        }
        return connectionPool.remove(0);
    }

    public synchronized void releaseConnection(Connection conn) {
        connectionPool.add(conn);
        notify();
    }

    public void shutdown() throws SQLException {
        for (Connection conn : connectionPool) {
            conn.close();
        }
    }

    // todo 保活
}
