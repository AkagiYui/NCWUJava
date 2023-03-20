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
    <form action="rectangle-servlet" method="post">
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
</html>
