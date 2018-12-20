<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ADMIN</title>
</head>
<body>

<h1>Добро пожаловать, администратор</h1>
<%--<a href="<c:url value='/logout' />">Logout</a>--%>
<%--<a href="<c:url value='/read' />">Получить список пациентов</a>--%>
<form method="get" action="<c:url value='/logout'/>">
    <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
    <input type="submit" value="Выйти"/>
</form>
<form method="get" action="<c:url value='/read'/>">
    <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
    <input type="submit" value="Получить список пациентов"/>
</form>
</body>
</html>

