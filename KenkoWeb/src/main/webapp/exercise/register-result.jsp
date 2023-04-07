<%--
  User: kenko
  Date: 0028 2023/3/28
  Time: 14:41
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    boolean result = (boolean) session.getAttribute("result");
%>
<html>
    <head>
        <title>注册<%= result ? "成功": "失败" %></title>
    </head>
    <body>
        <% if (result){ %>
            <jsp:useBean id="user" class="com.akagiyui.web.kenkoweb.exercise.entity.UserRegister" scope="session"/>
            <h1>用户 <jsp:getProperty name="user" property="username"/> 注册成功</h1>
        <% }else{ %>
            <h1>注册失败</h1>
            <% String msg = (String) session.getAttribute("msg");
            if(msg != null){ %>
                <h2><%= msg %></h2>
            <% } %>
        <% } %>
        <button onclick="window.location.href='register.jsp'">返回注册</button>
        <button onclick="window.location.href='login.jsp'">去登录</button>
        <button onclick="window.location.href='../index.jsp'">去首页</button>
    </body>
    <script src="../static/addHomeButton.js"></script>
    <script src="../static/showCode.js"></script>
    <script>showCode('src/main/webapp/exercise/register-result.jsp')</script>
</html>
