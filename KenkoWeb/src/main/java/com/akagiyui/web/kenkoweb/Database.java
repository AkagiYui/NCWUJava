package com.akagiyui.web.kenkoweb;

import com.akagiyui.web.kenkoweb.entity.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AkagiYui
 */
public class Database {
    public static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String DB_URL = "jdbc:mysql://localhost:3306/ddijw?user=root&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";

    private static Connection connection = null;
    private static Database instance;

    private Database() throws ClassNotFoundException {
        String driverInEnv = System.getenv("JDBC_DRIVER");
        if (driverInEnv != null) {
            JDBC_DRIVER = driverInEnv;
        }
        String urlInEnv = System.getenv("JDBC_DB_URL");
        if (urlInEnv != null) {
            DB_URL = urlInEnv;
        }
        Class.forName(JDBC_DRIVER); // 加载类从而注册驱动
    }

    public static Database getInstance() {
        if (connection == null) {
            try {
                instance = new Database();
                instance.connect();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    private synchronized void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
        }
    }

    private Staff resultToStaff(ResultSet resultSet) throws SQLException {
        Staff staff = new Staff();
        staff.setId(resultSet.getInt("id"));
        staff.setUsername(resultSet.getString("username"));
        staff.setPassword(resultSet.getString("password"));
        staff.setEmail(resultSet.getString("email"));
        staff.setNickname(resultSet.getString("nickname"));
        staff.setIsManager(resultSet.getBoolean("is_manager"));
        return staff;
    }

    public Staff getStaff(String username, String password) {
        try {
            instance.connect();
            // 预编译
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM staff WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            // 执行查询
            ResultSet resultSet = statement.executeQuery();
            // 处理结果
            if (resultSet.next()) {
                return resultToStaff(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Staff> getAllStaff() {
        try {
            instance.connect();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM staff");
            ResultSet resultSet = statement.executeQuery();
            List<Staff> staffList = new ArrayList<>();
            while (resultSet.next()) {
                staffList.add(resultToStaff(resultSet));
            }
            return staffList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchManager(Integer staffId) {
        try {
            instance.connect();
            PreparedStatement statement = connection.prepareStatement("UPDATE staff SET is_manager = NOT is_manager WHERE id = ?");
            statement.setInt(1, staffId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStaff(Integer staffId) {
        try {
            instance.connect();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM staff WHERE id = ?");
            statement.setInt(1, staffId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Staff> searchStaff(String search) {
        try {
            instance.connect();
            List<Staff> staffList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM staff WHERE username LIKE ? OR nickname LIKE ? OR email LIKE ?");
            statement.setString(1, "%" + search + "%");
            statement.setString(2, "%" + search + "%");
            statement.setString(3, "%" + search + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                staffList.add(resultToStaff(resultSet));
            }
            return staffList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changePassword(int id, String newPassword) {
        try {
            instance.connect();
            PreparedStatement statement = connection.prepareStatement("UPDATE staff SET password = ? WHERE id = ?");
            statement.setString(1, newPassword);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addStaff(Staff staff) {
        try {
            instance.connect();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO staff (username, password, email, nickname, is_manager) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, staff.getUsername());
            statement.setString(2, staff.getPassword());
            statement.setString(3, staff.getEmail());
            statement.setString(4, staff.getNickname());
            statement.setBoolean(5, staff.getIsManager());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeNickname(int id, String newNickname) {
        try {
            instance.connect();
            PreparedStatement statement = connection.prepareStatement("UPDATE staff SET nickname = ? WHERE id = ?");
            statement.setString(1, newNickname);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
