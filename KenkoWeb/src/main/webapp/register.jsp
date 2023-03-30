<%--
  User: kenko
  Date: 0025 2023/2/25
  Time: 17:00
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <script src="https://unpkg.com/jquery@3.6.3/dist/jquery.js"></script>
        <link rel="stylesheet" type="text/css" href="https://unpkg.com/fomantic-ui@2.9.2/dist/semantic.min.css">
        <script src="https://unpkg.com/fomantic-ui@2.9.2/dist/semantic.min.js"></script>
        <title>用户注册</title>
    </head>

    <body class="ui container" style="padding: 10px">
    <div class="ui middle aligned center aligned grid">
        <div class="column">
            <h1 class="ui header">用户注册</h1>
            <form class="ui form" action="register.do" method="post">
                <table class="ui celled table">
                    <tr>
                        <td>用户名</td>
                        <td><label>
                            <input type="text" name="username" class="field">
                        </label></td>
                    </tr>
                    <tr>
                        <td>密码</td>
                        <td><label>
                            <input type="password" name="password" class="field">
                        </label></td>
                    </tr>
                    <tr>
                        <td>确认密码</td>
                        <td><label>
                            <input type="password" name="repassword" class="field">
                        </label></td>
                    </tr>
                    <tr>
                        <td>邮箱</td>
                        <td><label>
                            <input type="email" name="email" class="field">
                        </label></td>
                    </tr>
                </table>
                <div class="ui centered grid" style="margin: 0 20px">
                    <button type="button" class="ui button" onclick="window.location.href='login.jsp'">已有账号？立即登录</button>
                    <button type="submit" class="ui primary button" onclick="return validateForm()">注册</button>
                </div>
                <div class="ui message negative" id="error-msg" style="display: none">
                    <i class="close icon"></i>
                    <div class="header">
                        出现错误：
                    </div>
                    <p id="error-msg-text"></p>
                </div>
            </form>
        </div>
    </div>
    </body>

    <script>
        $('.close.icon').click(function() {
            $(this).closest('.message').transition('fade');
        });

        function validateForm() {
            const username = document.getElementsByName('username')[0].value;
            const password = document.getElementsByName('password')[0].value;
            const repassword = document.getElementsByName('repassword')[0].value;
            const email = document.getElementsByName('email')[0].value;

            if (username === '') {
                $('#error-msg-text').text('用户名不能为空');
                $('#error-msg').removeClass('hidden').show();
                return false;
            }
            if (password === '' || repassword === '') {
                $('#error-msg-text').text('密码不能为空');
                $('#error-msg').removeClass('hidden').show();
                return false;
            }
            if (email === '') {
                $('#error-msg-text').text('邮箱不能为空');
                $('#error-msg').removeClass('hidden').show();
                return false;
            }
            if (password !== repassword) {
                $('#error-msg-text').text('确认密码与密码不一致');
                $('#error-msg').removeClass('hidden').show();
                return false;
            }
            return true;
        }
    </script>
</html>
