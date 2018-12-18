<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
    <h2>Список бациентов больницы</h2><br />
    <table>
        <tr>
            <td>
                <table align="left" border="1">
                    <tr align="center">
                        <td width="150">Имя</td>
                        <td width="150">Фамилия</td>
                        <td width="150">Отчество</td>
                        <td width="150">Обновить</td>
                        <td width="150">Удалить</td>
                        <td width="150">CardId</td>
                    </tr>
                    <c:forEach var="patient" items="${requestScope.patientsDb}">
                    <tr align="center">
                        <td ><c:out value="${patient.pName}"/></td>
                        <td ><c:out value="${patient.pSurname}"/></td>
                        <td ><c:out value="${patient.pPatronymic}"/></td>
                        <td valign="center">
                            <div>
                            <form method="get" action="<c:url value='/update'/>">
                            <input type="number" hidden name="pCardId" value="${patient.pCardId}" />
                            <input type="submit" value="Редактировать"/>
                            </form>
                            </div>

                        </td>
                        <td valign="center">
                            <div>
                            <form method="post" action="<c:url value='/delete'/>">
                            <input type="number" hidden name="pCardId" value="${patient.pCardId}" />
                            <input type="submit" name="delete" value="Удалить"/>
                            </form>
                            </div>
                        </td>
                        <td valign="center"><c:out value="${patient.pCardId}"/></td>
                    </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <h2>Создание нового пользователя</h2><br />
                <form method="post" action="<c:url value='/add_user'/>">
                    <label><input type="text" name="name"></label>Имя<br>
                    <label><input type="number" name="age"></label>Возраст<br>
                    <input type="submit" value="Ok" name="Ok"><br>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>
