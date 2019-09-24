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

    <p><a href="/currency">Show currency</a></p>
    <p><a href="/login?log=exit">Exit</a></p>
</body>
</html>
