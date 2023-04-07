<%--
  User: kenko
  Date: 0020 2023/3/20
  Time: 10:10
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.akagiyui.web.kenkoweb.exercise.entity.Rectangle" %>
<!DOCTYPE html>
<html>
    <head>
        <title>计算面积</title>
    </head>
    <body>
        <%
            int length = 0;
            int width = 0;
            String errorMessage = "";

            // 获取index.jsp页面传递过来的长和宽参数值
            try {
                length = Integer.parseInt(request.getParameter("length"));
                width = Integer.parseInt(request.getParameter("width"));
            } catch (NumberFormatException e) {
                errorMessage = e.getMessage();
            }

            // 创建Rectangle对象并计算面积
            Rectangle rectangle = new Rectangle(length, width);
            int area = rectangle.getArea();

            // 将面积存储到session中
            session.setAttribute("area", area);

            // 重定向到suc.jsp页面
            // response.sendRedirect("suc.jsp");
            // 为了展示页面，不使用该方法重定向，改为点击按钮后再跳转到suc.jsp页面
        %>

        <h1>计算面积</h1>
        <% if (errorMessage.isEmpty()) { %>
            <h2>当前收到数据：</h2>
            <p>长：<%= length %></p>
            <p>宽：<%= width %></p>
        <% } else { %>
            <h2>出现异常：</h2>
            <p><%= errorMessage %></p>
        <% } %>
        <input type="button" value="下一步（重定向到 suc.jsp ）" onclick="location.href='suc.jsp'">
    </body>

    <script src="../static/addHomeButton.js"></script>
    <script src="../static/showCode.js"></script>
    <script>showCode('src/main/webapp/exercise/check.jsp')</script>
</html>
