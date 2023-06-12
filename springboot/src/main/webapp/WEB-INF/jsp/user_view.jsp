<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<c:if test="${not empty userList}">
    <h1>User List</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${not empty user}">
    <h1>User Details</h1>
    <table>
        <tr>
            <th>ID</th>
            <td>${user.id}</td>
        </tr>
        <tr>
            <th>Name</th>
            <td>${user.name}</td>
        </tr>
        <tr>
            <th>Age</th>
            <td>${user.age}</td>
        </tr>
    </table>
</c:if>

<h1>Create User</h1>
<form action="/users2/" method="post">
    <label for="name">Name:</label>
    <input type="text" name="name" id="name">
    <br>
    <label for="age">Age:</label>
    <input type="number" name="age" id="age">
    <br>
    <input type="submit" value="Create">
</form>

<c:if test="${not empty user}">
    <h1>Edit User</h1>
    <form action="/users2/${user.id}/update" method="post">
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" value="${user.name}">
        <br>
        <label for="age">Age:</label>
        <input type="number" name="age" id="age" value="${user.age}">
        <br>
        <input type="submit" value="Update">
    </form>
</c:if>

<c:if test="${not empty user}">
    <h1>Delete User</h1>
    <form action="/users2/${user.id}/delete" method="post">
        <input type="submit" value="Delete">
    </form>
</c:if>
</body>
</html>
