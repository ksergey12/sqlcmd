<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <%@include file="header.jsp" %>
    <body>
    <h2>SQLCmd</h2>
    <hr />
        <form:form method="POST" action="connect" modelAttribute="connection">
            <table>
                <tr>
                    <td>База данных</td>
                    <td><form:input path="database" id="database" value="sqlcmd" /></td>
                </tr>
                <tr>
                    <td>Имя пользователя</td>
                    <td><form:input path="user" id="user" value="postgres" /></td>
                </tr>
                <tr>
                    <td>Пароль</td>
                    <td><form:password path="password" id="password" value="postgres" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Подключиться" /></td>
                </tr>
            </table>
        </form:form>
        <%@include file="footer.jsp" %>
    </body>
</html>