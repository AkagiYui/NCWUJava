<%--
  User: kenko
  Date: 0028 2023/3/28
  Time: 14:41
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="user" class="com.akagiyui.web.kenkoweb.entity.User" scope="session"/>
<html>
    <head>
        <title>个人信息</title>
    </head>
    <body>
        <%
            String msg = null;
            try {
                msg = (String) session.getAttribute("msg");
            } catch (Exception ignored) {}

            if (msg != null) {
        %>
                <h1>发生错误：<%= msg %></h1>
            <% } else { %>
                <h1>欢迎回来，<%= user.getUsername() %></h1>

            <% } %>
        <button onclick="window.location.href='login.jsp'">返回登录</button>
        <button onclick="window.location.href='index.jsp'">去首页</button>
    </body>

    <script src="static/addHomeButton.js"></script>
    <script src="static/showCode.js"></script>
    <script>showCode('src/main/webapp/info.jsp')</script>
</html>
