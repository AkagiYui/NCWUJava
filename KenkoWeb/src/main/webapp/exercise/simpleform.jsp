<%--
  User: kenko
  Date: 0025 2023/2/25
  Time: 17:13
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>form表单练习</title>
    </head>
    <body>
        掌握form表单的编写。掌握form标签、文本框、密码框、多行文本框、单选框、复选框、下拉列表、提交按钮、重置按钮等的编写。
        <form method="post" action="form?action=information">
            <p>
                <label for="username">用户名：</label>
                <input type="text" name="username" id="username">
            </p>
            <p>
                <label for="password">密码：</label>
                <input type="password" name="password" id="password">
            </p>
            <p>
                <label>性别：</label>
                <input type="radio" id="male" name="gender" value="male" checked><label for="male">男</label>
                <input type="radio" id="female" name="gender" value="female"><label for="female">女</label>
            </p>
            <p>
                <label for="city">所在城市：</label>
                <select name="city" id="city">
                    <option value="bj">北京</option>
                    <option value="sh">上海</option>
                    <option value="gz">广州</option>
                    <option value="sz">深圳</option>
                </select>
            </p>
            <p>
                <label>爱好：</label>
                <input type="checkbox" id="music" name="hobby" value="music" checked><label for="music">音乐</label>
                <input type="checkbox" id="travel" name="hobby" value="travel"><label for="travel">旅游</label>
                <input type="checkbox" id="reading" name="hobby" value="reading"><label for="reading">阅读</label>
            </p>
            <p>
                <label for="intro">个人简介：</label><br>
                <textarea name="intro" id="intro" cols="30" rows="10"></textarea>
            </p>
            <p>
                <input type="submit" value="提交">
                <input type="reset" value="重置">
            </p>
        </form>
    </body>
    <script src="../static/showCode.js"></script>
    <script>showCode('src/main/webapp/exercise/simpleform.jsp')</script>
</html>
