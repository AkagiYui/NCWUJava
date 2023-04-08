package com.akagiyui.web.kenkoweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author AkagiYui
 */
@WebServlet(name = "loginServlet", value = "/login.do")
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取用户信息
        var username = request.getParameter("username");
        var password = request.getParameter("password");

        var database = Database.getInstance();
        var session = request.getSession();

        var staff = database.getStaff(username, password);
        if (staff == null) {
            session.setAttribute("loggedIn", false);
            request.getRequestDispatcher("login-fail.jsp").forward(request, response);
        } else {
            session.setAttribute("staff", staff);
            session.setAttribute("loggedIn", true);
            response.sendRedirect("manage.jsp");
        }
    }
}
