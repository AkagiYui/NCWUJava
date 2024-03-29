<%--
  User: AkagiYui
  Date: 0003 2023/4/3
  Time: 9:31
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.akagiyui.web.kenkoweb.exercise.Database" %>
<%
  var db = Database.getInstance();
  var users = db.getAllUsers();
  pageContext.setAttribute("users", users);
%>
<html>
<head>
    <title>JDBC增删改查</title>
</head>
  <body>
    <h1>JDBC增删改查</h1>
    在MySQL中创建一张表，之后使用JDBC进行增删查改。
    <ol>
      <li>MySQL表设计</li>
      <li>JDBC连接到MySQL</li>
      <li>list.jsp：页面列出表中所有记录。每行记录后面有删除链接，链接中附加id字段，单击后跳转到delete.jsp。另有插入、查询按钮，单击后分别跳转到insert.jsp、query.jsp。</li>
      <li>delete.jsp：从URL中提取id字段，删除数据库表中该ID对应的记录。删除成功后，显示删除成功，之后重定向到list.jsp。</li>
      <li>insert.jsp：设计表单，单击提交按钮后，插入到数据库表中。插入成功后，显示插入成功, 之后重定向到list.jsp。</li>
      <li>query.jsp：设计表单，比如查询姓名，单击查询按钮后，检索数据库表记录，用列表显示结果。</li>
    </ol>
    <p>
      <input type="button" onclick="window.location.reload()" value="刷新" />
      <input type="button" onclick="window.location.href='insert.jsp'" value="插入" />
      <input type="button" onclick="window.location.href='query.jsp'" value="查询" />
    </p>
    <table>
      <tr>
        <td>用户ID</td>
        <td>用户名</td>
        <td>密码</td>
        <td>邮箱</td>
        <td>昵称</td>
        <td>操作</td>
      </tr>
      <c:forEach items="${users}" var="user">
        <tr>
          <td>${user.id}</td>
          <td>${user.username}</td>
          <td>${user.password}</td>
          <td>${user.email}</td>
          <td><a href="javascript:void(0);"
                 onclick="modifyNickname('${user.id}', '${user.nickname}')">${user.nickname}</a></td>
          <td><input type="button" onclick="confirmDelete(${user.id})" value="删除" /></td>
        </tr>
      </c:forEach>
    </table>
  </body>
  <script>
    function confirmDelete(id) {
      if (confirm('确定删除 ID 为 ' + id + ' 的用户吗？')) {
        window.location.href = 'delete.jsp?id=' + id;
      }
    }
    function modifyNickname(id, nickname) {
        const newNickname = prompt('请输入新的昵称', nickname);
        if (newNickname != null) {
          // 使用 fetch 发送 POST 请求
          fetch('update.jsp', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'id=' + id + '&nickname=' + newNickname
          }).then(function (response) {
            if (response.ok) {
              alert('修改成功');
              window.location.reload();
            } else {
              alert('修改失败');
            }
          });
      }
    }
  </script>
  <script src="../static/addHomeButton.js"></script>
  <script src="../static/showCode.js"></script>
  <script>showCode('src/main/webapp/exercise/list.jsp')</script>
  <style>
    table {
      border-collapse: collapse;
    }
    td, th {
      border: 1px solid black;
      padding: 5px;
    }
  </style>
</html>
