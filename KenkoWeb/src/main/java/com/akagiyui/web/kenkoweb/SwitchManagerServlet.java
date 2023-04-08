package com.akagiyui.web.kenkoweb;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author AkagiYui
 */
@WebServlet(name = "switchManagerServlet", value = "/switch.do")
public class SwitchManagerServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取用户信息
        String targetId = request.getParameter("target");
        try {
            int id = Integer.parseInt(targetId);
            Database database = Database.getInstance();
            database.switchManager(id);
        } catch (NumberFormatException e) {
            response.sendRedirect("manage-staff.jsp");
        }
    }
}
