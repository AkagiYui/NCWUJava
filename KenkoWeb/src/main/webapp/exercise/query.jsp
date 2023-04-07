<%--
  User: AkagiYui
  Date: 0003 2023/4/3
  Time: 11:29
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="com.akagiyui.web.kenkoweb.exercise.Database" %>
<%@ page import="com.akagiyui.web.kenkoweb.exercise.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
  var isQuery = request.getParameter("nickname") != null;
  pageContext.setAttribute("isQuery", isQuery);
  if (isQuery) {
    var nickname = request.getParameter("nickname");
    var db = Database.getInstance();
    var user = db.getUsersByNickname(nickname);
    pageContext.setAttribute("users", user);
  }
%>
<html>
  <head>
    <title>查询用户</title>
  </head>
  <body>
    <h1>查询用户</h1>
    <input type="button" value="返回 list.jsp" onclick="window.location.href='list.jsp'">
    <p>
      设计表单，比如查询姓名，单击查询按钮后，检索数据库表记录，用列表显示结果。
    </p>
    <form action="#" method="get">
      昵称：<input type="text" name="nickname" id="nickname" required
                  value="<%= request.getParameter("nickname") != null ? request.getParameter("nickname") : ""%>">
      <button type="submit" onclick="return check()">查询</button>
    </form>
    <% if (isQuery) { %>
      <jsp:useBean id="users" class="java.util.ArrayList"/>
      <h2>查询结果</h2>
      <% if (!users.isEmpty()) { %>
        <table>
          <tr>
            <td>用户ID</td>
            <td>用户名</td>
            <td>密码</td>
            <td>邮箱</td>
            <td>昵称</td>
          </tr>
          <% for (var user : users) {
            var u = (User) user; %>
            <tr>
              <td><%= u.getId() %></td>
              <td><%= u.getUsername() %></td>
              <td><%= u.getPassword() %></td>
              <td><%= u.getEmail() %></td>
              <td><%= u.getNickname() %></td>
            </tr>
          <% } %>
        </table>
      <% } else { %>
        <p>没有找到用户</p>
      <% } %>
    <% } %>
  </body>
  <script>
    function check() {
      const id = document.getElementById("nickname").value;
      if (id === "") {
        alert("请输入用户昵称");
        return false;
      }
      return true;
    }
  </script>
  <style>
    table {
      border-collapse: collapse;
    }
    td, th {
      border: 1px solid black;
      padding: 5px;
    }
  </style>
  <script src="../static/addHomeButton.js"></script>
  <script src="../static/showCode.js"></script>
  <script>showCode('src/main/webapp/exercise/query.jsp')</script>
</html>
