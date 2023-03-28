<%--
  User: kenko
  Date: 0025 2023/2/25
  Time: 17:00
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
        <head>
            <title>用户注册</title>
        </head>
        <body>
        设计一个注册用户的页面，内容自定，熟悉html基本语法。
        <form method="post">
            <table>
                <tr>
                    <td>用户名：</td>
                    <td><label>
                        <input type="text" name="username">
                    </label></td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td><label>
                        <input type="password" name="password">
                    </label></td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td><label>
                        <input type="password" name="repassword">
                    </label></td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td><label>
                        <input type="text" name="email">
                    </label></td>
                </tr>
                <tr>
                    <td><input type="submit" value="注册"></td>
                </tr>
            </table>
        </form>
    </body>

    <script src="../static/addHomeButton.js"></script>
    <script src="../static/showCode.js"></script>
    <script>showCode('src/main/webapp/exercise/register.jsp')</script>
</html>
