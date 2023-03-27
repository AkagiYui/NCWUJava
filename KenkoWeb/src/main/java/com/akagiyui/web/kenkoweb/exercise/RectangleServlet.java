package com.akagiyui.web.kenkoweb.exercise;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "rectangleServlet", value = "/exercise/rectangle")
public class RectangleServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 获取参数
        String width = request.getParameter("width");
        String length = request.getParameter("length");

        // 计算
        try {
            float area = Float.parseFloat(width) * Float.parseFloat(length);
            float perimeter = 2 * (Float.parseFloat(width) + Float.parseFloat(length));

            // 输出
            out.println("<html><body>");
            out.println("<h1>面积: " + area + "</h1>");
            out.println("<h1>周长: " + perimeter + "</h1>");
            out.println("</body></html>");
        } catch (NumberFormatException e) {
            out.println("<html><body>");
            out.println("<h1>输入错误：" + e + "</h1>");

            out.println("</body>");
            out.println("<script src=\"../static/showCode.js\"></script>\n");
            out.println("<script>showCode('src/main/java/com/akagiyui/web/kenkoweb/exercise/RectangleServlet.java')</script>");
            out.println("</html>");
        }
    }
}
