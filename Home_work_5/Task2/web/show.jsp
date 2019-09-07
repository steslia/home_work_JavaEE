<%--
  Created by IntelliJ IDEA.
  User: Sozin
  Date: 07.09.2019
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<p></p>
<table border="1">Таблица клиентов
    <tr>
        <td>Номер</td>
        <td>Имя</td>
        <td>Фамилия</td>
        <td>Телефон</td>
    </tr>
    <c:forEach items="${listPerson}" var="personTable">
        <tr>
            <td><c:out value="${personTable.id}"/></td>
            <td><c:out value="${personTable.name}"/></td>
            <td><c:out value="${personTable.surname}"/></td>
            <td><c:out value="${personTable.phone}"/></td>
        </tr>
    </c:forEach>
</table>

<p></p>

<table border="1">Таблица товаров
    <tr>
        <td>Код товара</td>
        <td>Название</td>
        <td>Количество</td>
        <td>Цена</td>
    </tr>
    <c:forEach items="${listProduct}" var="productTable">
        <tr>
            <td><c:out value="${productTable.code}"/></td>
            <td><c:out value="${productTable.name}"/></td>
            <td><c:out value="${productTable.count}"/></td>
            <td><c:out value="${productTable.price}"/></td>
        </tr>
    </c:forEach>
</table>

<p></p>

<table border="1">Таблица заказов
    <tr>
        <td>ID клиента</td>
        <td>Код товара</td>
        <td>Количество</td>
    </tr>
    <c:forEach items="${listOrder}" var="orderTable">
        <tr>
            <td><c:out value="${orderTable.idPerson}"/></td>
            <td><c:out value="${orderTable.idCode}"/></td>
            <td><c:out value="${orderTable.count}"/></td>
        </tr>
    </c:forEach>
</table>
<p><a href="/adminPanel.html">EXIT</a></p>

</body>
</html>
