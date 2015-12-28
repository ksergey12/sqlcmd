<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@include file="header.jsp" %>
    <body>
    <h3>Таблица: '${tableName}'</h3>
    <hr />
    <form action="create" method="post">
    <table id="table" border="1">
        <tbody>
            <tr bgcolor="yellow">
                <c:forEach items="${tableHeader}" var="element">
                    <th>
                        ${element}
                    </th>
                </c:forEach>
            </tr>

            <c:forEach items="${table}" var="row">
                <tr>
                    <c:forEach items="${row}" var="element">
                        <td>
                            <input readonly="true" type="text" name="" value="${element}"/></td>
                        </td>
                    </c:forEach>
                <td><a href="edit?table=${tableName}"><small>Правка</small></a></td>
                </tr>
             </c:forEach>
         </tbody>
    </table>
    </form>
    <br />
    <a href="add?table=${tableName}">Добавить запись</a> | <a href="clear?table=${tableName}">Очистить таблицу</a>
    </body>
    <%@include file="footer.jsp" %>
</html>