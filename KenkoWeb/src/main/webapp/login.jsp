<%--
  User: kenko
  Date: 0025 2023/2/25
  Time: 17:00
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>登录</title>
    </head>
    <body>
    <h1>登录</h1>
    目前这个还未布置作业，仅用来检验注册功能是否正常。
    <form action="login.do" method="post">
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
                <td><input type="submit" value="登录" onclick="return validateForm()"></td>
                <td><div id="error-msg"></div></td>
            </tr>
        </table>
    </form>
    </body>
    <script>
        function validateForm() {
            const username = document.getElementsByName('username')[0].value;
            const password = document.getElementsByName('password')[0].value;

            if (username === '') {
                document.getElementById('error-msg').innerHTML = '用户名不能为空';
                return false;
            }
            if (password === '') {
                document.getElementById('error-msg').innerHTML = '密码不能为空';
                return false;
            }

            return true;
        }
    </script>

    <script src="static/addHomeButton.js"></script>
    <script src="static/showCode.js"></script>
    <script>showCode('src/main/webapp/login.jsp')</script>
</html>
