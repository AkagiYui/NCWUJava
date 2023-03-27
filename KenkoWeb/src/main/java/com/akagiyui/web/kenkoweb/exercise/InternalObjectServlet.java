package com.akagiyui.web.kenkoweb.exercise;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.List;

@Log
@WebServlet(name = "InternalObjectServlet", value = "/exercise/internal")
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
        out.println("1、掌握JSP内置对象的区别与联系。<br>");
        out.println("2、掌握JSP内置对象相应接口。<br>");
        List<List<String>> table = List.of(
                List.of("对象", "作用", "类型", "文档地址"),
                List.of("request", "请求对象", "HttpServletRequest", "<a href='https://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html' target='_blank'>https://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html</a>"),
                List.of("response", "响应对象", "HttpServletResponse", "<a href='https://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletResponse.html' target='_blank'>https://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletResponse.html</a>"),
                List.of("session", "会话对象", "HttpSession", "<a href='https://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpSession.html' target='_blank'>https://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpSession.html</a>"),
                List.of("application", "应用对象", "ServletContext", "<a href='https://docs.oracle.com/javaee/6/api/javax/servlet/ServletContext.html' target='_blank'>https://docs.oracle.com/javaee/6/api/javax/servlet/ServletContext.html</a>"),
                List.of("out", "输出对象", "JspWriter", "<a href='https://docs.oracle.com/javaee/6/api/javax/servlet/jsp/JspWriter.html' target='_blank'>https://docs.oracle.com/javaee/6/api/javax/servlet/jsp/JspWriter.html</a>"),
                List.of("page", "页面对象", "", ""),
                List.of("config", "配置对象", "ServletConfig", "<a href='https://docs.oracle.com/javaee/6/api/javax/servlet/ServletConfig.html' target='_blank'>https://docs.oracle.com/javaee/6/api/javax/servlet/ServletConfig.html</a>")
        );
        out.println("<table border=\"1\">");
        for (List<String> row : table) {
            out.println("<tr>");
            for (String cell : row) {
                out.println("<td>" + cell + "</td>");
            }
            out.println("</tr>");
        }

        out.println("观察浏览器地址栏，name=后面的即为参数。");
        out.println("<h1>" + name + "</h1>");
        // print session
        out.println("<h1>" + session.getAttribute("name") + "</h1>");
        // print application
        out.println("<h1>" + application.getAttribute("name") + "</h1>");
        // print config servlet name
        out.println("<h1>" + config.getServletName() + "</h1>");

        out.println("</body>");
        out.println("<script src=\"../static/showCode.js\"></script>\n");
        out.println("<script>showCode('src/main/java/com/akagiyui/web/kenkoweb/exercise/InternalObjectServlet.java')</script>");
        out.println("</html>");
    }
}
