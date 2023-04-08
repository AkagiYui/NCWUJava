package com.akagiyui.web.kenkoweb;

import com.akagiyui.web.kenkoweb.entity.Staff;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author AkagiYui
 */
@WebServlet(name = "changeNicknameServlet", value = "/changeNickname.do")
public class ChangeNicknameServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String targetId = request.getParameter("target");
        HttpSession session = request.getSession();
        try {
            int id = Integer.parseInt(targetId);

            // 检查权限
            Staff currentStaff = (Staff)session.getAttribute("staff");
            if (currentStaff == null) {
                response.sendRedirect("manage.jsp");
                return;
            }
            if (currentStaff.getId() != id && !currentStaff.getIsManager()) {
                response.sendRedirect("manage.jsp");
                return;
            }

            String newNickname = request.getParameter("new");
            Database database = Database.getInstance();
            database.changeNickname(id, newNickname);

            if (currentStaff.getId() == id) {
                currentStaff.setNickname(newNickname);
                session.setAttribute("staff", currentStaff);
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("manage-staff.jsp");
        }
    }
}
