<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <%@include file="header.jsp" %>
    <body>
        <h2>SQLCmd</h2>
        <hr />
        <h3>Существующие команды:</h3>
        <b>help</b><br />
        для вывода помощи на экран<br />
        <br /><b>list</b><br />
        для получения списка всех таблиц подключённой базы<br />
        <br /><b>show?tableName</b><br />
        для получения содержимого таблицы 'tableName'<br />
        <br /><b>clear?tableName</b><br />
        для очистки таблицы 'tableName'<br />
        <br /><b>create?tableName</b><br />
        для создания записи в таблице 'tableName'<br />
        <%@include file="footer.jsp" %>
    </body>
</html>