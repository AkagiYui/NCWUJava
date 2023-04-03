<%--
  User: kenko
  Date: 0025 2023/2/25
  Time: 13:16
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>执行SQL语句</title>
    </head>
    <body>
        这个界面用来执行SQL语句，用于临时操作。<br>
        <form action="sql.do" method="post">
            <textarea name="sql" id="sql" cols="100" rows="10"></textarea><br>
            <input type="submit" value="执行">
        </form>
    </body>

    <script src="../static/addHomeButton.js"></script>
</html>
