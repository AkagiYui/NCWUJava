<%--
  User: AkagiYui
  Date: 0007 2023/4/7
  Time: 13:20
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Environment Variables</title>
</head>
<body>
  <% for (var env: System.getenv().entrySet()) { %>
    <p><%= env.getKey() %> = <%= env.getValue() %></p>
  <% } %>
</body>
</html>
