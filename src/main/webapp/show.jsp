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

            <c:forEach items="${table}" var="row"  varStatus="loop">
                <tr>
                    <c:forEach items="${row}" var="element">
                        <td>
                            <input readonly="true" type="text" name="" value="${element}"/></td>
                        </td>
                    </c:forEach>
                <td><a href="edit?table=${tableName}&id=${loop.index+1}"><small>Правка</small></a></td>
                </tr>
             </c:forEach>
         </tbody>
    </table>
    <a href="add?table=${tableName}">Добавить запись</a><br /><br />
    </form>
    <a href="clear?table=${tableName}">Очистить таблицу</a> |
    <a href="exit">Отключить базу</a>
    </body>
    <%@include file="footer.jsp" %>
</html>