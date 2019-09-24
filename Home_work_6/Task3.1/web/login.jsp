<%--
  Created by IntelliJ IDEA.
  User: Sozin
  Date: 23.09.2019
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

    <c:if test="${check eq false}">
        <h1>Неверный логин или пароль</h1>
    </c:if>

    <form action="/login" method="POST">
        <p>Login:</p><input type="text" name="login">
        <p>Password:</p><input type="password" name="password">
        <br> <input type="submit">
    </form>

    <p><a href="/index.html">EXIT</a></p>

</body>
</html>
