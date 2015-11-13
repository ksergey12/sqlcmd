<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@include file="header.jsp" %>
    <body>
    <h3>Добавить запись в таблицу '${tableName}'</h3>
    <hr />
    <form action="add" method="post">
    <input readonly="true" type="text" name="table" value="${tableName}" hidden /></td>
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
                <td>
                    <input type="text" name="id" value="3"/></td>
                </td>
                <td>
                    <input type="text" name="user" value="NewName"/></td>
                </td>
                <td>
                    <input type="text" name="password" value="NewPassword"/></td>
                </td>
            </tr>
         </tbody>
    </table><br />
    <button type="submit">Добавить запись</button>
    </form>
    </body>
    <%@include file="footer.jsp" %>
</html>