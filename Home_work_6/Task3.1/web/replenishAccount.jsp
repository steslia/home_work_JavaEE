<%--
  Created by IntelliJ IDEA.
  User: Sozin
  Date: 27.09.2019
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Replenish Account</title>
</head>
<body>

    <c:if test="${check eq false}">
        <h1>Счет не пополнен</h1>
    </c:if>

    <c:if test="${check eq true}">
        <h1>Счет пополнен</h1>
    </c:if>

    <form action="/replenishAccount" method="POST">

        <p>Выберите счет для пополнения</p>
        <p><select name= "id">
            <option > Выберите счет</option>
            <c:forEach items="${accountsList}" var="account">
                <option value="${account.getId()}"> Номер вашего счета: ${account.getId()}
                        сумма: ${account.getMoney()} валюта: ${account.getCurrency().getName()} </option>
            </c:forEach>
        </select></p>

        <p>Введите количесто денег</p><input type="text" name="money">

        <p><input type="submit" value="Отправить"></p>
    </form>
    <p><a href="/profile.jsp">EXIT</a></p>
</body>
</html>
