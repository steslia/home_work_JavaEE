<%--
  Created by IntelliJ IDEA.
  User: Sozin
  Date: 28.09.2019
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Activity</title>
</head>
<body>
    <c:if test="${check eq false}">
        <h1>Вы не смогли отправить ${money}</h1>
    </c:if>

    <c:if test="${check eq true}">
        <h1>Вы отправили ${senderMoney}</h1>
    </c:if>

    <form action="/activity" method="POST">

        <p>Выберите счет для отправления денег</p>
        <p><select name= "senderAccount">
            <option > Выберите счет</option>
            <c:forEach items="${accountsList}" var="account">
                <option value="${account.getId()}"> Номер вашего счета: ${account.getId()}
                    сумма: ${account.getMoney()} валюта: ${account.getCurrency().getName()} </option>
            </c:forEach>
        </select></p>

        <p>Введите количесто денег</p><input type="text" name="money">

        <p>Введите номер счета получаеля</p><input type="text" name="recipientAccount">

        <p><input type="submit" value="Отправить"></p>
    </form>
    <p><a href="/profile.jsp">EXIT</a></p>
</body>
</html>
