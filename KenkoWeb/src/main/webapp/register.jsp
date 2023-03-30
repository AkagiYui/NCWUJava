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
    <h1>用户注册</h1>
    掌握如何使用JDBC完成对数据的连接操作。
    <form action="register.do" method="post">
        <table>
            <tr>
                <td>用户名：</td>
                <td><label>
                    <input type="text" name="username" >
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
                    <input type="email" name="email">
                </label></td>
            </tr>
            <tr>
                <td><input type="submit" value="注册" onclick="return validateForm()"></td>
                <td><div id="error-msg"></div></td>
            </tr>
        </table>
    </form>
    </body>
    <script>
        function validateForm() {
            const username = document.getElementsByName('username')[0].value;
            const password = document.getElementsByName('password')[0].value;
            const repassword = document.getElementsByName('repassword')[0].value;
            const email = document.getElementsByName('email')[0].value;

            if (username === '') {
                document.getElementById('error-msg').innerHTML = '用户名不能为空';
                return false;
            }
            if (password === '' || repassword === '') {
                document.getElementById('error-msg').innerHTML = '密码不能为空';
                return false;
            }
            if (email === '') {
                document.getElementById('error-msg').innerHTML = '邮箱不能为空';
                return false;
            }
            if (password !== repassword) {
                document.getElementById('error-msg').innerHTML = '确认密码与密码不一致';
                return false;
            }

            return true;
        }
    </script>
    <script src="static/addHomeButton.js"></script>
    <script src="static/showCode.js"></script>
    <script>showCode('src/main/webapp/register.jsp')</script>
</html>
