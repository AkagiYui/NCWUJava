package com.akagiyui.web.kenkoweb;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author AkagiYui
 */
@WebServlet(name = "changePasswordServlet", value = "/changePassword.do")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取用户信息
        String targetId = request.getParameter("target");
        String newPassword = request.getParameter("new");
        try {
            int id = Integer.parseInt(targetId);
            Database database = Database.getInstance();
            database.changePassword(id, newPassword);
        } catch (NumberFormatException e) {
            response.sendRedirect("manage-staff.jsp");
        }
    }
}
