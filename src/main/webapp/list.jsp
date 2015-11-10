<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@include file="header.jsp" %>
    <body>
        <h2>SQLCmd</h2>
        <hr />
        <%
              Set<String> items = (Set<String>) request.getAttribute ("items");
        %>
        <table border='1'>
        <c:forEach items="${items}" var="item">
            <tr>
            <td><a href="show?table=${item}">${item}</a></td>
            <!--td><a href="insert?table=${item}">Вставить</a></td-->
            <td><a href="clear?table=${item}">Очистить</a></td>
            </tr>
        </c:forEach>
        </table>
    </body>
    <%@include file="footer.jsp" %>
</html>