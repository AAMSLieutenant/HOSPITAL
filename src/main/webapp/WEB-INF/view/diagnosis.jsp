<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Введите Диагноз</h2>
<br />

<form method="post" action="<c:url value='/diagnosis'/>">

    <label>Диагноз: <textarea style="resize:none" name="diagName" cols="30" rows="10" minlength="10" required></textarea></label><br>
    <input type="submit" value="Зарегистрировать">  <input type="reset" name="сброс"><br>
</form>
</body>
</html>
