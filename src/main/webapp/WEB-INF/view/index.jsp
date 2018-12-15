<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>

<h1>Hello from Java Vision!</h1><br />

<h2>Все пользователи</h2><br />

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
<table>
<c:forEach var="patient" items="${requestScope.patients}">


        <tr>
            <td>Name: <c:out value="${patient.pName}"/></td>
            <td>surname: <c:out value="${patient.pSurname}"/></td>
            <td>Patronymic: <c:out value="${patient.pPatronymic}"/></td>
            <td>Sex: <c:out value="${patient.pSex}"/></td>
        </tr>



    <%--<ul>--%>

        <%--<li>Name: <c:out value="${patient.pName}"/></li>--%>

        <%--<li>surname: <c:out value="${patient.pSurname}"/></li>--%>

        <%--<li>Patronymic: <c:out value="${patient.pPatronymic}"/></li>--%>

        <%--<li>Sex: <c:out value="${patient.pSex}"/></li>--%>

        <%--&lt;%&ndash;<form method="post" action="<c:url value='/delete'/>">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="number" hidden name="id" value="${user.id}" />&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="submit" name="delete" value="Удалить"/>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</form>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<form method="get" action="<c:url value='/update'/>">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="number" hidden name="id" value="${user.id}" />&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="submit" value="Редактировать"/>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
    <%--</ul>--%>
    <%--<hr />--%>

</c:forEach>
</table>

<h2>Создание нового пользователя</h2><br />

<form method="post" action="<c:url value='/add_user'/>">

    <label><input type="text" name="name"></label>Имя<br>

    <label><input type="number" name="age"></label>Возраст<br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

</body>
</html>
