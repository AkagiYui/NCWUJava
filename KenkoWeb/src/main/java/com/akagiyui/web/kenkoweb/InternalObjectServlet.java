package com.akagiyui.web.kenkoweb;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;

import java.io.IOException;

@Log
@WebServlet(name = "InternalObjectServlet", value = "/internal")
public class InternalObjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // config
        var config = getServletConfig();

        // request
        var name = req.getParameter("name");

        // session
        var session = req.getSession();
        session.setAttribute("name", name + "in session");

        // application
        var application = req.getServletContext();
        application.setAttribute("name", name + "in application");

        // response
        resp.setHeader("Content-Type", "text/html");
        // out
        var out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + name + "</h1>");
        // print session
        out.println("<h1>" + session.getAttribute("name") + "</h1>");
        // print application
        out.println("<h1>" + application.getAttribute("name") + "</h1>");
        // print config servlet name
        out.println("<h1>" + config.getServletName() + "</h1>");
        out.println("</body></html>");
    }
}
