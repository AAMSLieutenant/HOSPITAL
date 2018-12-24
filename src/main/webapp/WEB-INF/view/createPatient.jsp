<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<br />

<form method="post" action="<c:url value='/create'/>">

    <label>имя: <input type="text" name="pName" /></label><br>
    <label>фамилия: <input type="text" name="pSurname" /></label><br>
    <label>отчество: <input type="text" name="pPatronymic" /></label><br>
    <label>Пол: <input type="text" name="pSex" /></label><br>
    <label>дата рождения: <input type="text" name="pBirthDate" /></label><br>
    <label>дата прибытия: <input type="text" name="pArrivalDate" /></label><br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>
</body>
</html>
