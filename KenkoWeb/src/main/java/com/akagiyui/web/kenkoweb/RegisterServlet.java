package com.akagiyui.web.kenkoweb;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var out = response.getWriter();
        // 展示接收信息
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>注册信息</h1>");
        out.println("<p>用户名：" + request.getParameter("username") + "</p>");
        out.println("<p>密码：" + request.getParameter("password") + "</p>");
        out.println("<p>邮箱：" + request.getParameter("email") + "</p>");
        out.println("<br>");
        out.println("<a href=\"register.jsp\">返回注册页面</a>");
        out.println("</body>");
    }

    public void destroy() {
        database.disconnect();
    }
}
