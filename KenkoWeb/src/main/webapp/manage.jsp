<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.akagiyui.web.kenkoweb.entity.Staff" %><%--
  User: AkagiYui
  Date: 0007 2023/4/7
  Time: 17:08
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
  Staff staff = (Staff)session.getAttribute("staff");
  if (staff == null) {
    response.sendRedirect("login.jsp");
    return;
  }
  Boolean isManager = staff.getIsManager();
  pageContext.setAttribute("isManager", isManager);
%>
<html>
<head>
  <title>管理</title>
  <link rel="stylesheet" type="text/css" href="static/table.css">
  <link rel="stylesheet" type="text/css" href="static/menu.css">
  <script src="https://unpkg.com/jquery@3.6.3/dist/jquery.js"></script>
  <link rel="stylesheet" type="text/css" href="https://unpkg.com/fomantic-ui@2.9.2/dist/semantic.min.css">
  <script src="https://unpkg.com/fomantic-ui@2.9.2/dist/semantic.min.js"></script>
  <style>
      body {
          padding: 1em;
      }
      .ui.menu {
          margin: 1em 0;
      }
      .ui.menu:last-child {
          margin-bottom: 110px;
      }
  </style>

  <!--- Example Javascript -->
  <script>
  </script>
</head>
<body>
  <div class="ui pointing menu">
    <div class="header item">欢迎回来，<%= ((Staff)session.getAttribute("staff")).getNickname() %></div>
    <a class="active item">管理</a>
    <c:if test="${isManager}">
      <a class="item" href="manage-staff.jsp">
        <i class="users icon" style="visibility: visible;"></i>员工管理</a>
    </c:if>
    <div class="right menu">
      <div class="ui right aligned category search item">
        <div class="ui transparent icon input">
          <input class="prompt" type="text" placeholder="请输入房间号">
          <i class="search link icon"></i>
        </div>
        <div class="results"></div>
      </div>
      <a  href="index.jsp" class="ui item">
        <i class="home icon" style="visibility: visible;"></i>
        回到首页
      </a>
      <a  href="login.jsp" class="ui item">
        <i class="log out icon" style="visibility: visible;"></i>
        退出
      </a>
    </div>
  </div>

  <table class="ui striped right aligned table">
    <thead>
      <tr>
        <th class="left aligned">Person</th>
        <th>Calories</th>
        <th>Fat</th>
        <th>Protein</th>
      </tr>
    </thead>
    <tbody>
    <tr>
      <td class="left aligned">Rosaline</td>
      <td>5</td>
      <td>35g</td>
      <td>6g</td>
    </tr>
    <tr>
      <td class="left aligned">Barrie</td>
      <td>27</td>
      <td>23g</td>
      <td>28g</td>
    </tr>
    <tr>
      <td class="left aligned">Trinidad</td>
      <td>14</td>
      <td>50g</td>
      <td>7g</td>
    </tr>
    <tr>
      <td class="left aligned">Jaqueline</td>
      <td>31</td>
      <td>30g</td>
      <td>50g</td>
    </tr>
    <tr>
      <td class="left aligned">Tamala</td>
      <td>18</td>
      <td>6g</td>
      <td>13g</td>
    </tr>
    </tbody>
  </table>
</body>
</html>
