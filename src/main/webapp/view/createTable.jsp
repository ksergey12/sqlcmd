<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@include file="header.jsp" %>
    <body>
    <h3>Создать новую таблицу</h3>
    <hr />
    <form action="createTable" method="post">
        Имя таблицы: <input type="text" name="table" value=""/> <button type="submit">Создать</button>
    </form>
    <%@include file="footer.jsp" %>
</html>