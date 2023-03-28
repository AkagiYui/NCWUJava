package com.akagiyui.web.kenkoweb.exercise;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/exercise/hello")
public class HelloServlet extends HttpServlet {
    private String message;

    @Override
    public void init() {
        message = "Hello World, AkagiYui!";
        message += "<br>当你看到这个页面的时候，说明环境已成功部署。";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");

        out.println("</body>");
        out.println("<script src=\"../static/addHomeButton.js\"></script>\n");
        out.println("<script src=\"../static/showCode.js\"></script>\n");
        out.println("<script>showCode('src/main/java/com/akagiyui/web/kenkoweb/exercise/HelloServlet.java')</script>");
        out.println("</html>");
    }

    @Override
    public void destroy() {
    }
}
