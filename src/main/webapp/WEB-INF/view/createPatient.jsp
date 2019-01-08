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
    <label>Возраст: <input type="text" name="pAge" /></label><br>
    <label>Пол: <input type="text" name="pSex" /></label><br>
    <label>дата рождения: <input type="text" name="pBirthDate" /></label><br>
    <label>дата прибытия: <input type="text" name="pArrivalDate" /></label><br>
    <br/>
    АДРЕС
    <br/>
    <label>город: <input type="text" name="city" /></label><br>
    <label>улица: <input type="text" name="street" /></label><br>
    <label>номер дома: <input type="text" name="houseNumber" /></label><br>
    <label>номер квартиры: <input type="text" name="flatNumber" /></label><br>


    <input type="submit" value="Ok" name="Ok"><br>
</form>
<form method="get" action="<c:url value='/read'/>">
    <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
    <input type="submit" value="К списку пациентов"/>
</form>
</body>
</html>
