package com.akagiyui.web.kenkoweb;

import com.akagiyui.web.kenkoweb.entity.UserRegister;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register.do")
public class RegisterServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var database = Database.getInstance();

        // 获取用户信息
        var username = request.getParameter("username");
        var password = request.getParameter("password");
        var email = request.getParameter("email");
        var user = new UserRegister(username, password, email);

        var session = request.getSession();
        session.setAttribute("result", false);
        session.setAttribute("user", user);
        var msg = "";

        // 数据校验
        if (username == null || username.isBlank()) {
            msg = "用户名不能为空";
        }
        else if (password == null || password.isBlank()) {
            msg = "密码不能为空";
        }
        else if (email == null || email.isBlank()) {
            msg = "邮箱不能为空";
        }
        else if (database.isUsernameExist(username)) { // 检查用户名是否已存在
            msg = "用户名已存在";
        }

        // 保存用户信息
        if ("".equals(msg) && database.addUser(user)) {
            session.setAttribute("result", true);
        } else {
            session.setAttribute("msg", msg);
        }

        response.sendRedirect("register-result.jsp"); // 跳转到结果页面
    }
}
