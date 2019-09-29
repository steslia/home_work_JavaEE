<%--
  Created by IntelliJ IDEA.
  User: Sozin
  Date: 23.09.2019
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
    <h1>Hi <c:out value="${login}"/></h1>

    <p><a href="/currency">Показать курс валют</a></p>
    <p><a href="/createAccount">Создать счет</a></p>
    <p><a href="/showAccount">Посмотреть счета</a></p>
    <p><a href="/replenishAccount">Пополнить счета</a></p>
    <p><a href="/activity">Переводы</a></p>
    <p><a href="/showActivity">История транзакций</a></p>
    <p><a href="/login?log=exit">Exit</a></p>
</body>
</html>
