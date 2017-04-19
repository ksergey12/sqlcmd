<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@include file="header.jsp" %>
    <body>
    <h3>История команд пользователя ${userName}</h3>
    <hr />

    <table border="1">
        <c:forEach items="${actions}" var="userAction">
            <tr>
                <td>
                    ${userAction.user}
                </td>
                <td>
                    ${userAction.database}
                </td>
                <td>
                    ${userAction.action}
                </td>
            </tr>
        </c:forEach>
    </table>

    </body>
    <%@include file="footer.jsp" %>
</html>