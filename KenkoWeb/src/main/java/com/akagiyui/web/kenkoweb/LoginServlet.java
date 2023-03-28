package com.akagiyui.web.kenkoweb;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/login.do")
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取用户信息
        var username = request.getParameter("username");
        var password = request.getParameter("password");

        var database = Database.getInstance();
        var session = request.getSession();

        // 数据校验
        var user = database.getUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
        } else {
            session.setAttribute("msg", "用户名或密码错误");
        }

        // 跳转到个人信息页面
        response.sendRedirect("info.jsp");
    }
}
