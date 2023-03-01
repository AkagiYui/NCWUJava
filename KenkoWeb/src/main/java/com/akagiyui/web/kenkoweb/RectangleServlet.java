package com.akagiyui.web.kenkoweb;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "rectangleServlet", value = "/rectangle-servlet")
public class RectangleServlet extends HttpServlet {
    // 传入长宽，计算面积周长
    public void init() {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // 获取参数
        String width = request.getParameter("width");
        String length = request.getParameter("length");

        // 计算
        float area = Float.parseFloat(width) * Float.parseFloat(length);
        float perimeter = 2 * (Float.parseFloat(width) + Float.parseFloat(length));

        // 输出
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>面积: " + area + "</h1>");
        out.println("<h1>周长: " + perimeter + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
