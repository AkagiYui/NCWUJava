<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.akagiyui.web.kenkoweb.entity.Staff" %>
<%@ page import="java.util.List" %>
<%@ page import="com.akagiyui.web.kenkoweb.Database" %><%--
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
  if (!isManager) {
      response.sendRedirect("manage.jsp");
  }

  Database db = Database.getInstance();
  List<Staff> staffList;
  String search = request.getParameter("search");
  if (search != null && !search.equals("")) {
      staffList = db.searchStaff(search);
      pageContext.setAttribute("staffList", staffList);
  } else {
    staffList = db.getAllStaff();
  }
  pageContext.setAttribute("staffList", staffList);
%>
<html>
<head>
  <title>员工管理</title>
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
  <script>function changePassword(id) {
    $.modal({
        title: '修改密码',
        class: 'mini',
        closeIcon: true,
        content: '<div class="ui form">\n' +
            '  <div class="field">\n' +
            '    <label>新密码</label>\n' +
            '    <input type="password" id="newPassword" placeholder="新密码">\n' +
            '  </div>\n' +
            '</div>',
        actions: [{
            text: '确认修改',
            class: 'green',
            click: () => {
                const xhr = new XMLHttpRequest();
                xhr.open("POST", "changePassword.do", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.send("new=" + $('#newPassword').val() + "&target=" + id);
                xhr.onreadystatechange = () => {
                    if (xhr.readyState === 4) {
                        location.reload();
                    }
                }
            }
        }]
    }).modal('show');
  }

  function addStaff() {
    $.modal({
        title: '添加员工',
        class: 'mini',
        closeIcon: true,
        content: '<div class="ui form">\n' +
            '  <div class="field">\n' +
            '    <label>用户名</label>\n' +
            '    <input type="text" id="username" placeholder="用户名">\n' +
            '  </div>\n' +
            '  <div class="field">\n' +
            '    <label>密码</label>\n' +
            '    <input type="password" id="password" placeholder="密码">\n' +
            '  </div>\n' +
            '  <div class="field">\n' +
            '    <label>姓名</label>\n' +
            '    <input type="text" id="nickname" placeholder="姓名">\n' +
            '  </div>\n' +
            '  <div class="field">\n' +
            '    <label>邮箱</label>\n' +
            '    <input type="text" id="email" placeholder="邮箱">\n' +
            '  </div>\n' +
            '</div>',
        actions: [{
            text: '确认添加',
            class: 'green',
            click: () => {
                const xhr = new XMLHttpRequest();
                xhr.open("POST", "addStaff.do", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.send("username=" + $('#username').val() + "&password=" + $('#password').val() + "&nickname=" + $('#nickname').val() + "&email=" + $('#email').val());
                xhr.onreadystatechange = () => {
                    if (xhr.readyState === 4) {
                        location.reload();
                    }
                }
            }
        }]
    }).modal('show');
  }

  function switchManager(id) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "switch.do", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("target=" + id);
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
            location.reload();
        }
    }
  }

  function deleteStaff(id) {
      $.modal({
          title: '警告！！',
          class: 'mini',
          closeIcon: true,
          content: '请再次确认删除，此次操作不可恢复！',
          actions: [{
              text: '确认删除',
              class: 'red',
              click: () => {
                  const xhr = new XMLHttpRequest();
                  xhr.open("POST", "deleteStaff.do", true);
                  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                  xhr.send("target=" + id);
                  xhr.onreadystatechange = () => {
                      if (xhr.readyState === 4) {
                          location.reload();
                      }
                  }
              }
          }]
      }).modal('show');
    }

  function searchStaff(text) {
    if (text === "" || text === null || text === undefined) {
        window.location.href = " manage-staff.jsp";
        return;
    }
    window.location.href = " manage-staff.jsp?search=" + text;
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
    <a class="item" href="manage.jsp">
      <i class="child icon"></i>
      个人信息
    </a>
    <a class="active item">
      <i class="users icon"></i>
      员工管理
    </a>
    <a class="item" onclick="addStaff()">
      <i class="plus icon" style="visibility: visible;"></i>
      添加员工
    </a>
    <div class="right menu">
      <div class="ui right aligned category search item">
        <div class="ui transparent icon input">
          <input class="prompt" id="searchBox" value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>" type="text" placeholder="查询员工">
          <i class="search link icon" onclick="searchStaff($('#searchBox').val())"></i>
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
  <div class="ui menu">
    <div class="header item">员工数量：${staffList.size()}</div>
    <div class="header item">管理员拥有&nbsp;管理员工&nbsp;的权限。</div>
    <div class="header item">点击即可切换用户的“管理员”与“普通用户”的身份。</div>
  </div>
  <table class="ui striped right aligned table">
    <thead>
      <tr>
        <th class="left aligned"><i class="address card icon"></i>用户名</th>
        <th><i class="quote left icon"></i>姓名</th>
        <th><i class="envelope icon"></i>邮箱</th>
        <th><i class="edit icon"></i>操作</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${staffList}" var="staffi">
      <tr>
        <td class="left aligned">${staffi.username}</td>
        <td>${staffi.nickname}</td>
        <td>${staffi.email}</td>
        <td>
          <div class="ui buttons">
            <button style="min-width: 150px" class="ui ${staffi.isManager ? "violet" : "green"} button" id="managerButton${staffi.id}" onclick="switchManager(${staffi.id})">
              <i id="managerIcon${staffi.id}" class="${staffi.isManager ? "accessible" : "user"} icon"></i>
              <c:if test="${staffi.isManager}">
                管理员
              </c:if>
              <c:if test="${!staffi.isManager}">
                普通用户
              </c:if>
            </button>
            <button class="ui orange basic button" onclick="changePassword(${staffi.id})">修改密码</button>
            <button class="ui negative basic button" onclick="deleteStaff(${staffi.id})">删除</button>
          </div>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</body>
</html>
