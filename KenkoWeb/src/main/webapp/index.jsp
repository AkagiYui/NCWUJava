<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String title = "AkagiYui 的 JSP 作业";
    String qqNumber = "1050314133";
    String repository = "https://github.com/AkagiYui/NCWUJava/tree/master/KenkoWeb";

    List<List<String>> workList = new ArrayList<>();
    workList.add(Arrays.asList("环境安装：Hello Servlet", "exercise/hello"));
    workList.add(Arrays.asList("jsp入门：计算矩形周长与面积", "exercise/rect.jsp"));
    workList.add(Arrays.asList("jsp入门：用户注册界面", "exercise/register.jsp"));
    workList.add(Arrays.asList("form表单编写：form表单练习", "exercise/simpleform.jsp"));
    workList.add(Arrays.asList("实验3：JSP内置对象使用", "exercise/internal?name=这是我的名字"));
    workList.add(Arrays.asList("实验4：JSP处理form及URL参数", "exercise/simpleform.jsp"));
    workList.add(Arrays.asList("实验5：JDBC基础", "exercise/jdbc-basic"));
    workList.add(Arrays.asList("作业：用javaBean实现求矩形面积（在本页面下方）", "#用javaBean实现求矩形面积"));
    workList.add(Arrays.asList("实验6：使用JDBC完成用户的注册", "register-show.jsp"));
    workList.add(Arrays.asList("登录（未要求）", "login-show.jsp"));
    pageContext.setAttribute("workList", workList);

    List<List<String>> projectList = new ArrayList<>();
    projectList.add(Arrays.asList("用户注册", "register.jsp"));
//    projectList.add(Arrays.asList("用户登录", "login.jsp"));
    pageContext.setAttribute("projectList", projectList);
%>
<!DOCTYPE html>
<html>
    <head>
        <title><%= title %></title>
    </head>

    <body style="padding: 10px">
        <h1><%= title %></h1>
        <h2><a href="tencent://message/?uin=<%= qqNumber %>">联系我：QQ <%= qqNumber %></a></h2>
        <h2><a href="<%= repository %>">开源地址：<%= repository %></a></h2>

        <div>
            <h1>作业目录</h1>
            <p>
                <c:forEach items="${workList}" var="item" varStatus="status">
                    <a href="${item[1]}">${status.index + 1}. ${item[0]}</a><br>
                </c:forEach>
            </p>

            <p>为了使得参考方便，我在每一页底部添加了本页的代码展示。</p>

            <h1>其它</h1>
            <p>
                <c:forEach items="${projectList}" var="item" varStatus="status">
                    <a href="${item[1]}">${status.index + 1}. ${item[0]}</a><br>
                </c:forEach>
            </p>
        </div>


        <div id="用javaBean实现求矩形面积">
            <h1>作业：用 javaBean 实现求矩形面积</h1>
            1. 在 index.jsp 页面中，设计2个文本框用来输入矩形的长和宽，并将数据提交到 check.jsp 中。<br>
            2. 在 check.jsp 中，用 javaBean 接收 index 页面传过来的长和宽的参数值，并把矩形的面积显示在 suc.jsp 页面上。<br>
            <h2>输入矩形的长和宽</h2>
            <form method="post" action="exercise/check.jsp">
                <label>长：</label><input type="number" name="length"><br><br>
                <label>宽：</label><input type="number" name="width"><br><br>
                <input type="submit" value="计算面积">
            </form>
        </div>
    </body>

    <style>
        #用javaBean实现求矩形面积 {
            border-width: 2px;
            border-style: solid;
            border-color: #000000;
            padding: 10px;
        }
    </style>
    <script src="static/addHomeButton.js"></script>
    <script src="static/showCode.js"></script>
    <script>showCode('src/main/webapp/index.jsp')</script>
</html>
