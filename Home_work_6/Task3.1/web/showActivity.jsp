<%--
  Created by IntelliJ IDEA.
  User: Sozin
  Date: 29.09.2019
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
    <title>Show Activity</title>
</head>
<body>
    <table border="1">
        <tr>
            <td>Получатель</td>
            <td>Сумма</td>
        </tr>
        <c:forEach items="${activityList}" var="activity">
            <tr>
                <td><c:out value="${activity.getRecipient().getUser().getLogin()}"/></td>
                <td><c:out value="${activity.getMoney()}"/></td>
            </tr>
        </c:forEach>
    </table>

    <p><a href="profile.jsp">Exit</a></p>
</body>
</html>
