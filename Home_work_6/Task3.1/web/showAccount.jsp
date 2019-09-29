<%--
  Created by IntelliJ IDEA.
  User: Sozin
  Date: 27.09.2019
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Account</title>
</head>
<body>
    <table border="1">
        <tr>
            <td>Номер счета</td>
            <td>Валюта</td>
            <td>Счет в UAH</td>
        </tr>
        <c:forEach items="${accountsList}" var="account">
            <tr>
                <td><c:out value="${account.getId()}"/></td>
                <td><c:out value="${account.getNameCurrency()}"/></td>
                <td><c:out value="${account.getMoney()}"/></td>
            </tr>
        </c:forEach>
    </table>

    <p><a href="profile.jsp">Exit</a></p>
</body>
</html>
