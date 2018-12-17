<%--
  Author: Pavel Ravvich.
  Date: 15.10.17.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%--<div>Имя: <c:out value="${requestScope.user.name}"/> </div>--%>
<%--<div>Возраст: <c:out value="${requestScope.user.age}"/> </div>--%>
<div>Имя: <c:out value="${requestScope.patient.pName}"/> </div>
<div>Фамилия: <c:out value="${requestScope.patient.pSurname}"/> </div>
<div>Отчество: <c:out value="${requestScope.patient.pPatronymic}"/> </div>

<br />

<form method="post" action="<c:url value='/update'/>">

    <label>Новое имя: <input type="text" name="pName" /></label><br>
    <label>Новая фамилия: <input type="text" name="pSurname" /></label><br>
    <label>Новое отчество: <input type="text" name="pPatronymic" /></label><br>

    <input type="number" hidden name="id" value="${requestScope.patient.cardId}"/>

    <input type="submit" value="Ok" name="Ok"><br>
</form>
</body>
</html>
