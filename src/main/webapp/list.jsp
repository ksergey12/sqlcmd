<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@include file="header.jsp" %>
    <body>
        <h2>SQLCmd</h2>
        <hr />
        <h3>Список таблиц в базе '${database}'</h3>
        <ol>
            <c:forEach items="${items}" var="item">
                <li><a href="show?table=${item}">${item}</a></li>
            </c:forEach>
        </ol>
    </body>
    <%@include file="footer.jsp" %>
</html>