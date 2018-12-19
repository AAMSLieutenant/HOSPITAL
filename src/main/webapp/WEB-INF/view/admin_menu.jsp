<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ADMIN</title>
</head>
<body>

<h1>Hello ADMIN!</h1>
<a href="<c:url value='/logout' />">Logout</a>
<a href="<c:url value='/read' />">Получить список пациентов</a>
</body>
</html>

