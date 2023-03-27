package com.akagiyui.web.kenkoweb;

import com.akagiyui.web.kenkoweb.entity.User;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/javaweb";

    static final String USERNAME = "root";
    static final String PASSWORD = "";

    @Getter
    private Connection conn = null;

    @Getter
    private boolean connected = false;

    public Database() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER); // 加载类从而注册驱动
    }

    public boolean connect() {
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            DatabaseMetaData dbMeta = conn.getMetaData();
            System.out.println("当前用户：" + dbMeta.getUserName());
            System.out.println("驱动版本：" + dbMeta.getDriverVersion());

            connected = true;
        } catch (Exception se) {
            connected = false;
            se.printStackTrace();
        }
        return connected;
    }

    public List<User> getAllUsers() throws SQLException {
        var stmt = conn.createStatement();
        var rs = stmt.executeQuery("SELECT * FROM user");
        var users = new ArrayList<User>();
        while (rs.next()) {
            var user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            users.add(user);
        }
        return users;
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
                connected = false;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public Map<String, String> getColumnType(String tableName) throws SQLException {
        var stmt = conn.createStatement();
        var rs = stmt.executeQuery("SELECT * FROM " + tableName);
        var metaData = rs.getMetaData();
        var columnCount = metaData.getColumnCount();
        var columnType = new HashMap<String, String>();
        for (int i = 1; i <= columnCount; i++) {
            columnType.put(metaData.getColumnName(i), metaData.getColumnTypeName(i));
        }
        return columnType;
    }

    public boolean addUser(User user) throws SQLException {
        var stmt = conn.createStatement();
        var sql = "INSERT INTO user (username, password, email) VALUES ('"
                + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getEmail()
                + "')";
        return stmt.executeUpdate(sql) == 1;
    }
}
