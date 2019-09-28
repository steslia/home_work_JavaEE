<%--
  Created by IntelliJ IDEA.
  User: Sozin
  Date: 24.09.2019
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Currency</title>
</head>
<body>
    <table border="1">
        <tr>
            <td>Валюта</td>
            <td>Курс</td>
        </tr>
        <c:forEach items="${listCurs}" var="cursList">
            <tr>
                <td><c:out value="${cursList.getNameCurrency()}"/></td>
                <td><c:out value="${cursList.getCurs()}"/></td>
            </tr>
        </c:forEach>
    </table>

    <p><a href="profile.jsp">Exit</a></p>
</body>
</html>
