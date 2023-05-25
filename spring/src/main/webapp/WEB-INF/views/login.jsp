<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/login">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" /><br />

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" /><br />

    <input type="submit" value="Log In" />
</form>
</body>
</html>
