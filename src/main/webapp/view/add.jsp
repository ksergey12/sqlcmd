<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@include file="header.jsp" %>
    <body>
    <h3>Добавить запись в таблицу '${tableName}'</h3>
    <hr />
    <form action="add" method="post">
    <input readonly="true" type="text" name="table" value="${tableName}" hidden />
    <table id="table" border="1">
        <tbody>
            <tr bgcolor="yellow">
                <c:forEach items="${tableHeader}" var="element">
                    <th>
                        ${element}
                    </th>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach items="${tableHeader}" var="element">
                    <td>
                        <input type="text" name="${element}" value=""/>
                    </td>
                </c:forEach>
            </tr>
         </tbody>
    </table><br />
    <button type="submit">Добавить запись</button>
    </form>
    </body>
    <%@include file="footer.jsp" %>
</html>