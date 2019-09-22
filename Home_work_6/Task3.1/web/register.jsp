<%--
  Created by IntelliJ IDEA.
  User: Sozin
  Date: 22.09.2019
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

    <c:if test="${check eq false}">
        <h1>Вы не создали акаунт</h1>
    </c:if>

    <c:if test="${check eq true}">
        <h1>Вы создали акаунт</h1>
    </c:if>

    <form action="/register" method="POST">
        <p>Login:</p><input type="text" name="login">
        <p>Password:</p><input type="password" name="password">
        <p>Name:</p><input type="text" name="name">
        <p>Surname:</p><input type="text" name="surname">
        <p>Phone:</p><input type="text" name="phone">
        <br> <input type="submit">
    </form>

    <p><a href="/index.html">EXIT</a></p>

</body>
</html>
