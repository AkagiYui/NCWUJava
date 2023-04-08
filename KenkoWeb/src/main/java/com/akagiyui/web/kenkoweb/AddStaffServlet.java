package com.akagiyui.web.kenkoweb;

import com.akagiyui.web.kenkoweb.entity.Staff;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author AkagiYui
 */

@WebServlet(name = "addStaffServlet", value = "/addStaff.do")
public class AddStaffServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        Staff staff = new Staff();
        staff.setUsername(username);
        staff.setPassword(password);
        staff.setNickname(nickname);
        staff.setEmail(email);
        staff.setIsManager(false);
        Database database = Database.getInstance();
        database.addStaff(staff);
        response.sendRedirect("manage-staff.jsp");
    }
}
