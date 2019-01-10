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
    <h1>Добро пожаловать</h1>
    <%--<a href="<c:url value='/logout' />">Logout</a>--%>
    <%--<a href="<c:url value='/read' />">Получить список пациентов</a>--%>
    <form method="get" action="<c:url value='/read'/>">
        <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
        <%--<input type="submit" value="Получить список пациентов"/>--%>
        <button type="submit" class="btn btn-primary  btn-md">Получить список пациентов</button>
    </form>
    <form method="get" action="<c:url value='/logout'/>">
        <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
            <button type="submit" class="btn btn-primary  btn-md">Выйти</button>
    </form>
    </div>
</div>
</body>
</html>

