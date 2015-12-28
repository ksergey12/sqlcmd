<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@include file="header.jsp" %>
        <h3>Список таблиц в базе '${database}'</h3>
        <hr />

        <c:choose>
            <c:when test="${not empty items}">
                <ol>
                    <c:forEach items="${items}" var="item">
                        <li><a href="show?table=${item}">${item}</a> [<a href="dropTable?table=${item}">х</a>]</li>
                    </c:forEach>
                </ol>
                <br />
            </c:when>
            <c:otherwise>
                В этой базе нет таблиц.
                <br /><br />
            </c:otherwise>
        </c:choose>
        <a href="createTable">Новая таблица</a>
    </body>
    <%@include file="footer.jsp" %>
</html>