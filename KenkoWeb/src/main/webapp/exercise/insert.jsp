<%--
  User: kenko
  Date: 0025 2023/2/25
  Time: 17:00
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>插入用户</title>
  </head>
  <body>
    <h1>插入用户</h1>
    <input type="button" value="返回 list.jsp" onclick="window.location.href='list.jsp'">
    <p>
      设计表单，单击提交按钮后，插入到数据库表中。插入成功后，显示插入成功, 之后重定向到list.jsp。<br>
      在这里其实就相当于上个实验设计的注册页面，这里不再编写冗余代码。<br>
      如果希望参考上个实验的代码，可以<a href="../register-show.jsp"><b>点击这里</b></a>跳转到上个实验的页面。
    </p>
    <iframe src="../register-show.jsp" width="100%" height="300px"></iframe>
  </body>
  <script src="../static/addHomeButton.js"></script>
  <script src="../static/showCode.js"></script>
  <script>showCode('src/main/webapp/exercise/insert.jsp')</script>
</html>
