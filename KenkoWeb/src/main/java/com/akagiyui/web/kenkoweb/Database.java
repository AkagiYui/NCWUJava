package com.akagiyui.web.kenkoweb;

import com.akagiyui.web.kenkoweb.entity.User;
import com.akagiyui.web.kenkoweb.entity.UserRegister;
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
//    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    public static final String DB_URL = "jdbc:mysql://localhost:3306/javaweb?user=root&password=";

    public static final String JDBC_DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:./data/kenkoweb.db";

    @Getter
    private Connection connection = null;
    private static Database instance;

    private Database() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER); // 加载类从而注册驱动
    }

    public static Database getInstance() {
        if (instance == null) {
            try {
                instance = new Database();
                instance.connect();
                instance.createTables();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    private void createTables() {
        try {
            var stmt = connection.createStatement();
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS user (
                        id INT PRIMARY KEY auto_increment,
                        username VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        nickname VARCHAR(255) NOT NULL
                    )
            """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void connect() throws SQLException {
        connection = DriverManager.getConnection(DB_URL);
        DatabaseMetaData dbMeta = connection.getMetaData();
        System.out.println("当前用户：" + dbMeta.getUserName());
        System.out.println("驱动版本：" + dbMeta.getDriverVersion());
    }

    public Map<String, String> getColumnType(String tableName) throws SQLException {
        var stmt = connection.createStatement();
        var rs = stmt.executeQuery("SELECT * FROM " + tableName);
        var metaData = rs.getMetaData();
        var columnCount = metaData.getColumnCount();
        var columnType = new HashMap<String, String>();
        for (int i = 1; i <= columnCount; i++) {
            columnType.put(metaData.getColumnName(i), metaData.getColumnTypeName(i));
        }
        return columnType;
    }

    public boolean isUsernameExist(String username) {
        try {
            var stmt = connection.createStatement();
            var rs = stmt.executeQuery("SELECT * FROM user WHERE username = '" + username + "'");
            return rs.next();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    private User resultToUser(java.sql.ResultSet rs) throws SQLException {
        var user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setNickname(rs.getString("nickname"));
        return user;
    }

    public List<User> getAllUsers() {
        var users = new ArrayList<User>();
        try {
            var stmt = connection.createStatement();
            var rs = stmt.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                users.add(resultToUser(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return users;
    }

    public boolean addUser(UserRegister user){
        try {
            var stmt = connection.createStatement();
            var sql = "INSERT INTO user (username, password, email, nickname) VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getEmail() + "', '" + user.getNickname() + "')";
            return stmt.executeUpdate(sql) == 1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int id){
        try {
            var stmt = connection.createStatement();
            var sql = "DELETE FROM user WHERE id = " + id;
            return stmt.executeUpdate(sql) == 1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public User getUser(String username, String password) {
        try {
            var stmt = connection.createStatement();
            var rs = stmt.executeQuery("SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'");
            if (rs.next()) {
                return resultToUser(rs);
            }
            return null;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
