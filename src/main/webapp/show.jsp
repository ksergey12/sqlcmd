<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@include file="header.jsp" %>
            <script type="text/javascript">
                function addRow(id){
                var tbody = document.getElementById(id).getElementsByTagName("TBODY")[0];
                var row = document.createElement("TR")
                var td1 = document.createElement("TD")
                td1.appendChild(document.createTextNode("id"))
                var td2 = document.createElement("TD")
                td2.appendChild (document.createTextNode("name"))
                var td3 = document.createElement("TD")
                td3.appendChild (document.createTextNode("password"))
                row.appendChild(td1);
                row.appendChild(td2);
                row.appendChild(td3);
                tbody.appendChild(row);
              }
            </script>
    <body>
    <h2>SQLCmd</h2>
    <hr />
    <h3>Таблица: '${tableName}'</h3>
    <form action="create" method="post">
    <table  id="table" border="1">
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
                                <input type="text" name="value[]" value="${element}"/></td>
                            </td>
                        </c:forEach>
                    </tr>
             </c:forEach>
         </tbody>
    </table>
    <a href="javascript://" onclick="addRow('table');return false;">Добавить строку</a><br /><br />
    <button type="submit">Сохранить изменения</button>
    </form>
    <a href="clear?table=${tableName}">Очистить таблицу</a>
    </body>
    <%@include file="footer.jsp" %>
</html>