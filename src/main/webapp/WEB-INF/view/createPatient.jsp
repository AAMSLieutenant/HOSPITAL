<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<br />

<form method="post" action="<c:url value='/create'/>">

    <label>имя: <input type="text" name="pName" required/></label><br>
    <label>фамилия: <input type="text" name="pSurname" required/></label><br>
    <label>отчество: <input type="text" name="pPatronymic" required/></label><br>
    <label>Возраст: <input type="number" name="pAge" min="1" max="110" required/></label><br>
    <label>Пол:<br>
    <input type="radio" name="pSex" value="male" required>мужской
    <input type="radio" name="pSex" value="female" required>женский</label><br>
    <c:set var="fin" value="${requestScope.fin}"/>
    <label>дата рождения: "${fin}" <input type="date" name="pBirthDate" min="1900-01-01" max="${fin}" required/></label><br>
    <label>дата прибытия: <input type="date" name="pArrivalDate" value="${fin}" readonly/></label><br>
    <br/>
    АДРЕС
    <br/>
    <label>город: <input type="text" name="city" required/></label><br>
    <label>улица: <input type="text" name="street" required/></label><br>
    <label>номер дома: <input type="number" name="houseNumber" min="1"  required/></label><br>
    <label>номер квартиры: <input type="number" name="flatNumber" min="1" required/></label><br>


    <input type="submit" value="Зарегистрировать" name="Ok"> <input type="reset" name="сброс"><br>
</form>
<form method="get" action="<c:url value='/read'/>">
    <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
    <input type="submit" value="К списку пациентов"/>
</form>
</body>
</html>
