<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@include file="header.jsp" %>
            <script type="text/javascript">
                function addRow(id){
                var tbody = document.getElementById(id).getElementsByTagName("TBODY")[0];
                var row = document.createElement("TR")
                var td1 = document.createElement("TD")
                var id = document.createElement('input')
                id.type = "text"
                id.name = "name[]"
                var name = document.createElement('input')
                name.type = "text"
                name.name = "name[]"
                var value = document.createElement('input')
                value.type = "text"
                value.name = "name[]"
                td1.appendChild(id)
                var td2 = document.createElement("TD")
                td2.appendChild (name)
                var td3 = document.createElement("TD")
                td3.appendChild (value)
                row.appendChild(td1);
                row.appendChild(td2);
                row.appendChild(td3);
                tbody.appendChild(row);
              }
            </script>
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
                                <input readonly="true" type="text" name="value[]" value="${element}"/></td>
                            </td>
                        </c:forEach>
                    </tr>
             </c:forEach>
         </tbody>
    </table>
    <a href="add?table=${tableName}">Добавить запись</a><br /><br />
    <!--a href="javascript://" onclick="addRow('table');return false;">Добавить строку</a><br /><br /-->
    </form>
    <a href="clear?table=${tableName}">Очистить таблицу</a>
    </body>
    <%@include file="footer.jsp" %>
</html>