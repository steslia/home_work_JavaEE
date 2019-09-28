<%--
  Created by IntelliJ IDEA.
  User: Sozin
  Date: 26.09.2019
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create account</title>
</head>
<body>
    <c:if test="${check eq false}">
        <h1>Счет не был создан</h1>
    </c:if>

    <c:if test="${check eq true}">
        <h1>Счет создан</h1>
    </c:if>

    <form action="/createAccount" method="POST">
        <p>Выберите валюту для поточного счета</p>
        <p><select name= "currency">
            <option > Выберите валюту</option>
            <c:forEach items="${currencyList}" var="currency">
                <option value="${currency.getName()}"> ${currency.getName()} </option>
            </c:forEach>
        </select></p>

        <p>Введите количесто денег</p><input type="text" name="money">

        <p><input type="submit" value="Отправить"></p>
    </form>
    <p><a href="/profile.jsp">EXIT</a></p>
</body>
</html>
