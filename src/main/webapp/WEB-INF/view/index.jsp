<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>

<h1>Hello from Java Vision!</h1><br />

<%--<h2>Все пользователи</h2><br />--%>

<%--<c:forEach var="user" items="${requestScope.users}">--%>
    <%--<ul>--%>

        <%--<li>Имя: <c:out value="${user.name}"/></li>--%>

        <%--<li>Возраст: <c:out value="${user.age}"/></li>--%>

        <%--<form method="post" action="<c:url value='/delete'/>">--%>
            <%--<input type="number" hidden name="id" value="${user.id}" />--%>
            <%--<input type="submit" name="delete" value="Удалить"/>--%>
        <%--</form>--%>

        <%--<form method="get" action="<c:url value='/update'/>">--%>
            <%--<input type="number" hidden name="id" value="${user.id}" />--%>
            <%--<input type="submit" value="Редактировать"/>--%>
        <%--</form>--%>
    <%--</ul>--%>
    <%--<hr />--%>

<%--</c:forEach>--%>

<p>
    <table align="left" border="1">
        <tr align="center">
            <td width="150">Имя</td>
            <td width="150">Фамилия</td>
            <td width="150">Отчество</td>
            <td width="150">Обновить</td>
            <td width="150">Удалить</td>
            <td width="150">CardId</td>
        </tr>
    <c:forEach var="patient" items="${requestScope.patients}">


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



        <%--<ul>--%>

            <%--<form method="post" action="<c:url value='/delete'/>">--%>
                <%--<input type="number" hidden name="id" value="${user.id}" />--%>
                <%--<input type="submit" name="delete" value="Удалить"/>--%>
            <%--</form>--%>

            <%--<form method="get" action="<c:url value='/update'/>">--%>
                <%--<input type="number" hidden name="id" value="${user.id}" />--%>
                <%--<input type="submit" value="Редактировать"/>--%>
            <%--</form>--%>
        <%--</ul>--%>
        <%--<hr />--%>

    </c:forEach>
    </table>
</p>
<%--<p>--%>
<%--<h2>Создание нового пользователя</h2><br />--%>

<%--<form method="post" action="<c:url value='/add_user'/>">--%>

    <%--<label><input type="text" name="name"></label>Имя<br>--%>

    <%--<label><input type="number" name="age"></label>Возраст<br>--%>

    <%--<input type="submit" value="Ok" name="Ok"><br>--%>
<%--</form>--%>
<%--</p>--%>

</body>
</html>
