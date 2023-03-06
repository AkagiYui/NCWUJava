package com.akagiyui.web.kenkoweb;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "jdbcServlet", value = "/jdbc-basic")
public class JDBCServlet extends HttpServlet {
    Database database;

    @Override
    public void init() {
        try {
            database = new Database();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (!database.connect()) {
            System.out.println("Failed to connect to database");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        var out = response.getWriter();
        out.println("<html>");

        // 设置表格边框
        out.println("<style>table, th, td {border: 1px solid black;}</style>");

        out.println("<body>");

        // 获取表结构
        try {
            var columns = database.getColumnType("user");
            out.println("<h1>user表结构</h1>");
            out.println("<table>");
            out.println("<tr><th>字段名</th><th>字段类型</th></tr>");
            for (var column : columns.entrySet()) {
                out.println("<tr>");
                out.println("<td>" + column.getKey() + "</td>");
                out.println("<td>" + column.getValue() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 获取用户数据
        try {
            var users = database.getAllUsers();
            out.println("<h1>user表数据</h1>");
            out.println("<table>");
            out.println("<tr><th>id</th><th>name</th><th>password</th></tr>");
            for (var user : users) {
                out.println("<tr>");
                out.println("<td>" + user.getId() + "</td>");
                out.println("<td>" + user.getUsername() + "</td>");
                out.println("<td>" + user.getPassword() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 获取数据库基本信息
        try {
            var metaData = database.getConn().getMetaData();
            out.println("<h1>数据库基本信息</h1>");
            // major version
            out.println("major version: " + metaData.getDatabaseMajorVersion() + "<br>");
            // minor version
            out.println("minor version: " + metaData.getDatabaseMinorVersion() + "<br>");
            // product name
            out.println("product name: " + metaData.getDatabaseProductName() + "<br>");
            // product version
            out.println("product version: " + metaData.getDatabaseProductVersion() + "<br>");
            // driver major version
            out.println("driver major version: " + metaData.getDriverMajorVersion() + "<br>");
            // driver minor version
            out.println("driver minor version: " + metaData.getDriverMinorVersion() + "<br>");
            // driver name
            out.println("driver name: " + metaData.getDriverName() + "<br>");
            // driver version
            out.println("driver version: " + metaData.getDriverVersion() + "<br>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        database.disconnect();
    }
}
