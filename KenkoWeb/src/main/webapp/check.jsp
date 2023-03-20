<%--
  User: kenko
  Date: 0020 2023/3/20
  Time: 10:10
--%>
<%@ page import="com.akagiyui.web.kenkoweb.entity.Rectangle" %>
<%
  // 获取index.jsp页面传递过来的长和宽参数值
  int length = Integer.parseInt(request.getParameter("length"));
  int width = Integer.parseInt(request.getParameter("width"));

  // 创建Rectangle对象并计算面积
  Rectangle rectangle = new Rectangle(length, width);
  int area = rectangle.getArea();

  // 将面积存储到session中
  session.setAttribute("area", area);

  // 重定向到suc.jsp页面
  response.sendRedirect("suc.jsp");
%>
