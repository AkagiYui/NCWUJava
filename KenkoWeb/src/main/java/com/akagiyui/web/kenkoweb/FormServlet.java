package com.akagiyui.web.kenkoweb;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "formServlet", value = "/form")
public class FormServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        var out = response.getWriter();
        out.println("<html><body>");
        out.println("<a href=\"simpleform.jsp\">返回</a>");

        // 列出URL请求参数
        out.println("<h1>URL 请求参数</h1>");
        var query = request.getQueryString();
        if (query != null) {
            var params = query.split("&");
            for (var param : params) {
                var key = param.split("=")[0];
                var value = param.split("=")[1];
                out.println(key + ": " + value + "<br>");
            }
        }

        // 列出form表单数据
        out.println("<h1>form表单数据</h1>");
        var params = request.getParameterMap();
        for (var key : params.keySet()) {
            out.println(key + ": " + request.getParameter(key) + "<br>");
        }

        // 列出HTTP请求头
        out.println("<h1>HTTP 请求头</h1>");
        var headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            var header = headers.nextElement();
            out.println(header + ": " + request.getHeader(header) + "<br>");
        }

        // 列出cookie键值对
        out.println("<h1>Cookies</h1>");
        var cookies = request.getCookies();
        for (var cookie : cookies) {
            out.println(cookie.getName() + ": " + cookie.getValue() + "<br>");
        }

        out.println("</body></html>");
    }
}
