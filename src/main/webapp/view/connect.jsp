<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@include file="header.jsp" %>
    <body>
    <h2>SQLCmd</h2>
    <hr />
        <form action="connect" method="post">
            <table>
                <tr>
                    <td>База данных</td>
                    <td><input type="text" name="dbname" value="sqlcmd" /></td>
                </tr>
                <tr>
                    <td>Имя пользователя</td>
                    <td><input type="text" name="username" value="postgres" /></td>
                </tr>
                <tr>
                    <td>Пароль</td>
                    <td><input type="password" name="password" value="postgres" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Подключиться" /></td>
                </tr>
            </table>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>