<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>SQLCmd</title>
    </head>
    <body>

    <h2>SQLCmd</h2>
    <hr />
        <c:forEach items="${items}" var="item">
            <a href="${item}">${item}</a><br>
        </c:forEach>
    </body>
</html>