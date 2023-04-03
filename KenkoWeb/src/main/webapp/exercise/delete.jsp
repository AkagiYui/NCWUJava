<%--
  User: AkagiYui
  Date: 0003 2023/4/3
  Time: 9:31
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.akagiyui.web.kenkoweb.Database" %>
<%
  var db = Database.getInstance();
  int id;
  try {
      id = Integer.parseInt(request.getParameter("id"));
  } catch (NumberFormatException e) {
      id = -1;
  }
  String result = "删除失败";

  if (id != -1) {
      if (db.deleteUser(id)) {
          result = "删除成功";
      }
  }
  pageContext.setAttribute("result", result);
%>
<html>
  <head>
      <title>删除用户</title>
  </head>
  <body>
    <h1>删除用户</h1>
    <p>${result}</p>
  </body>
  <script>
    window.alert("${result}");
    window.location.href = "list.jsp";
  </script>
  <script src="../static/addHomeButton.js"></script>
  <script src="../static/showCode.js"></script>
  <script>showCode('src/main/webapp/exercise/delete.jsp')</script>
</html>
