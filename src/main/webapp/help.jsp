<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>SQLCmd</title>
    </head>
    <body>
        <h2>Существующие команды:</h2>
        <hr />
        <b>connect|database|user|password</b><br />
        для подключения к базе данных<br />
        <br /><b>help</b><br />
        для вывода помощи на экран<br />
        <br /><b>log</b><br />
        для вывода истории введённых команд<br />
        <br /><b>show|tableName</b><br />
        для получения содержимого таблицы 'tableName'<br />
        <br /><b>clear|tableName</b><br />
        для очистки всей таблицы<br />
        <br /><b>create|tableName|column1|value1|column2|value2|...|columnN|valueN</b><br />
        для создания записи в таблице<br />
        <br /><b>list</b><br />
        для получения списка всех таблиц подключённой базы<br /><br />
        Вернуться в <a href="menu">Меню</a><br>
    </body>
</html>