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
@WebServlet(name = "logoutServlet", value = "/logout.do")
public class LogoutServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Staff staff = (Staff)session.getAttribute("staff");
        if (staff != null) {
            session.setAttribute("staff", null);
            session.setAttribute("loggedIn", false);
        }
        response.sendRedirect("login.jsp");
    }
}
