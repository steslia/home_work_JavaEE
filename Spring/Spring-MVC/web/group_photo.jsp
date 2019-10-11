<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Photo</title>
</head>
<body>
    <div align="center">

        <div>
            <input type="submit" value="Upload New" onclick="window.location='/';"/>
        </div>

        <form action="/delete_checkbox_photo" method="post">
                <c:forEach items="${photo_id}" var="photo_id">
                    <tr>
                        <td><input type="checkbox" name="deleteList[]" value="${photo_id}" id="checkbox_${photo_id}"/></td>
                        <td><img src="/photo/${photo_id}" width="50px" height="50px"/></td>
                    </tr>
                </c:forEach>

            <p></p><input type="submit" value="Delete Photo"/>
        </form>
    </div>
</body>
</html>
