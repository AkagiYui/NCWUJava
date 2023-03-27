<%--
  User: kenko
  Date: 0020 2023/3/20
  Time: 10:11
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>矩形面积计算结果</title>
    </head>
    <body>
        <h1>矩形面积计算结果</h1>
        <p>矩形的面积是：<%= session.getAttribute("area") %>。</p>
    </body>
    <script src="../static/showCode.js"></script>
    <script>showCode('src/main/webapp/exercise/suc.jsp')</script>
</html>
