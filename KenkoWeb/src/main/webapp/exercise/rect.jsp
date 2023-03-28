<%--
  User: kenko
  Date: 0025 2023/2/25
  Time: 13:16
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>计算矩形周长和面积</title>
    </head>
    <body>
        设计一个web应用程序，能够实现输入矩形的2个边后，计算矩形的周长和面积。
        <form action="rectangle" method="post">
            <table>
                <tr>
                    <td>长：</td>
                    <td><label>
                        <input type="text" name="length">
                    </label></td>
                </tr>
                <tr>
                    <td>宽：</td>
                    <td><label>
                        <input type="text" name="width">
                    </label></td>
                </tr>
                <tr>
                    <td><input type="submit" value="计算"></td>
                    <td><input type="reset" value="重置"></td>
                </tr>
            </table>
        </form>
    </body>

    <script src="../static/addHomeButton.js"></script>
    <script src="../static/showCode.js"></script>
    <script>showCode('src/main/webapp/exercise/rect.jsp')</script>
</html>
