<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: AkagiYui
  Date: 0007 2023/4/7
  Time: 11:39
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>用户登录</title>
  <script src="https://unpkg.com/jquery@3.6.3/dist/jquery.js"></script>
  <link rel="stylesheet" type="text/css" href="https://unpkg.com/fomantic-ui@2.9.2/dist/semantic.min.css">
  <script src="https://unpkg.com/fomantic-ui@2.9.2/dist/semantic.min.js"></script>
  <script>function login() {
      return $('.ui.form').form('is valid');
  }

  $(document).ready(function () {
      $('.ui.form').form({
          fields: {
              username: {
                  identifier: 'username',
                  rules: [
                      {
                          type: 'empty',
                          prompt: '请输入用户名'
                      }
                  ]
              },
              password: {
                  identifier: 'password',
                  rules: [
                      {
                          type: 'empty',
                          prompt: '请输入密码'
                      }
                  ]
              }
          }
      });
  });
  </script>
</head>
<body style="padding: 20px">
<div class="ui middle aligned center aligned grid">
  <div class="column">
    <h2 class="ui teal header">
        员工信息管理系统
    </h2>
    <form class="ui large form" action="login.do" method="post">
      <div class="ui stacked segment">
        <div class="field">
          <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="username" placeholder="请输入用户名">
          </div>
        </div>
        <div class="field">
          <div class="ui left icon input">
            <i class="lock icon"></i>
            <input type="password" name="password" placeholder="请输入密码">
          </div>
        </div>
        <div class="ui fluid large teal submit button" onclick="login()">登录</div>
        <div class="ui large label" style="margin-top: 10px">测试账号：用户名&nbsp;ddi，密码&nbsp;haha</div>
      </div>
      <div class="ui error message"></div>
    </form>
  </div>
</div>
</body>
<style type="text/css">
    body {
        background-color: #dadada;
    }

    body > .grid {
        height: 100%;
    }

    .image {
        margin-top: -100px;
    }

    .column {
        max-width: 450px;
    }
</style>
</html>
