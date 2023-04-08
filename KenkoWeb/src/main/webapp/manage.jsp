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
  <title>个人信息管理</title>
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
  <script>function saveNickname() {
      const nickname = $("#nickname").val();
      $.ajax({
      url: "changeNickname.do",
      type: "POST",
      data: {
        target: ${staff.id},
          new: nickname
      },
      success: function () {
          location.reload();
      }
    })
  }

  function savePassword() {
      const password = $("input[type=password]").val();
      $.ajax({
      url: "changePassword.do",
      type: "POST",
      data: {
        target: ${staff.id},
          new: password
      },
      success: function () {
          $.toast({
              class: 'success',
              showIcon: false,
              message: '修改成功'
          })
          ;
      }
    })
  }

  </script>
</head>
<body>
<div class="ui pointing menu">
  <div class="header item">员工信息管理系统</div>
  <div class="header item">欢迎回来，${staff.nickname}</div>
</div>
  <div class="ui pointing menu">
    <div class="header item">菜单</div>
    <a class="active item">
      <i class="child icon"></i>
      个人信息
    </a>
    <c:if test="${isManager}">
      <a class="item" href="manage-staff.jsp">
        <i class="users icon" style="visibility: visible;"></i>员工管理</a>
    </c:if>
    <div class="right menu">
      <a  href="index.jsp" class="ui item">
        <i class="home icon" style="visibility: visible;"></i>
        回到首页
      </a>
      <a href="logout.do" class="ui item">
        <i class="log out icon" style="visibility: visible;"></i>
        退出
      </a>
    </div>
  </div>
<%--  个人信息修改--%>
  <div class="ui container">
    <div class="ui segments">
      <div class="ui segment" style=" display: flex; flex-wrap: wrap;">
        <div class="ui labeled input" style="flex:1">
          <div class="ui label" style="min-width: 95px">
            <i class="address card icon"></i>
            用户名
          </div>
          <input type="text" disabled value="${staff.username}">
        </div>
        <button class="ui grey button" style="margin-left: 10px">无法修改</button>
      </div>
      <div class="ui segment" style=" display: flex; flex-wrap: wrap;">
        <div class="ui labeled input" style="flex:1">
          <div class="ui label" style="min-width: 95px">
            <i class="key icon"></i>
            密码
          </div>
          <input type="password">
        </div>
        <button class="ui green button" style="margin-left: 10px" onclick="savePassword()">保存</button>
      </div>
      <div class="ui segment" style=" display: flex; flex-wrap: wrap;">
        <div class="ui labeled input" style="flex:1">
          <div class="ui label" style="min-width: 95px">
            <i class="quote left icon"></i>
            姓名
          </div>
          <input type="text" id="nickname" value="${staff.nickname}">
        </div>
        <button class="ui green button" style="margin-left: 10px" onclick="saveNickname()">保存</button>
      </div>
      <div class="ui segment" style=" display: flex; flex-wrap: wrap;">
        <div class="ui labeled input" style="flex:1">
          <div class="ui label" style="min-width: 95px">
            <i class="envelope icon"></i>
            邮箱
          </div>
          <input type="email" value="${staff.email}">
        </div>
        <button class="ui green button" style="margin-left: 10px">保存</button>
      </div>
    </div>
  </div>
</body>
</html>
