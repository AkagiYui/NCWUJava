<%--
  User: AkagiYui
  Date: 0003 2023/4/3
  Time: 9:31
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.akagiyui.web.kenkoweb.exercise.Database" %>
<%
  var db = Database.getInstance();
  db.updateNickname(Integer.parseInt(request.getParameter("id")), request.getParameter("nickname"));
%>
