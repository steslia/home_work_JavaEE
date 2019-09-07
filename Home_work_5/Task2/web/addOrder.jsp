<%--
  Created by IntelliJ IDEA.
  User: Sozin
  Date: 07.09.2019
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add orders</title>
</head>
<body>

<p>Здраствуйте</p>

<table border="1">
    <c:forEach items="${ordersListProduct}" var="o">
        <tr>
            <td><c:out value="${o.code}"/></td>
            <td><c:out value="${o.name}"/></td>
            <td><c:out value="${o.count}"/></td>
            <td><c:out value="${o.price}"/></td>
        </tr>
    </c:forEach>
</table>

<form action="/addOrder" method="POST">
    <p><select name= "idPerson">
        <option > Выберите клиента</option>
        <c:forEach items="${ordersListPerson}" var="person">
            <option value="${person.id}" > ${person.name} </option>
        </c:forEach>
    </select></p>

    <p><select name = "idCode">
        <option > Выберите товар</option>
        <c:forEach items="${ordersListProduct}" var="product">
            <option value="${product.code}" > ${product.name} </option>
        </c:forEach>
    </select></p>

    <p>Количество товара:</p> <input type="text" name="count">

    <p><input type="submit" value="Отправить"></p>

</form>

    <p><a href="/adminPanel.html">EXIT</a></p>

</body>
</html>
