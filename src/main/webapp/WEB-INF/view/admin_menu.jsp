<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ADMIN</title>
    <head>
        <link rel="stylesheet" href="../../css/bootstrap.min.css">
        <script src="../../js/bootstrap.min.js"></script>
    </head>
</head>
<body>
<br>
<br>
<br>
<br>
<div class="container">
    <div class="form-group col-xs-4">
    </div>

    <div class="form-group col-xs-6">
    <c:set var="login" value="${requestScope.login}"/>
    <h1>Добро пожаловать, ${login}</h1>
        <br>
    <%--<a href="<c:url value='/logout' />">Logout</a>--%>
    <%--<a href="<c:url value='/read' />">Получить список пациентов</a>--%>
        <p>
        <form  method="get" action="<c:url value='/read'/>">
        <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
        <%--<input type="submit" value="Получить список пациентов"/>--%>
        <button type="submit" class="btn btn-primary  btn-md">Список пациентов</button><br>
        <small id="passwordHelp" class="form-text text-muted">Работа с пациентами</small><br>
        </form>
        </p>
        <p>
        <form  method="get" action="<c:url value='/logout'/>">
            <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
                <button type="submit" class="btn btn-primary  btn-danger">Выйти</button><br>
                <small id="passwordHelp" class="form-text text-muted">Выйти из учетной записи</small><br>
        </form>
        </p>


    </div>
</div>
</body>
</html>

