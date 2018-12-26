<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Введите данные приема</h2>
<br />

<form method="post" action="<c:url value='/appointment'/>">

    <label>Дата приема: <input type="text" name="appDate" /></label><br>
    <label>Стоимость приема: <input type="text" name="appValue" /></label><br>
    <label>Жалоба пациента: <textarea style="resize:none" name="appComplaint" cols="30" rows="10"></textarea></label><br>
    <label>Врач:
    <select name="menu" size="1">
        <c:forEach var="doctor" items="${requestScope.doctorsDb}">
        <option value="${doctor.empId}">${doctor.empSurname} ${doctor.empName}, ${doctor.posName}</option>
    <%--<option selected="selected" value="second"><c:out value="${requestScope.arr[1]}"/></option>--%>
    <%--<option value="third"><c:out value="${requestScope.arr[2]}"/></option>--%>
        </c:forEach>
    </select>
    <input type="submit" value="Зарегистрировать"><br>
</form>


<%--<form method="post" action="<c:url value='/update'/>">--%>

<%--<label>Новое имя: <input type="text" name="pName" /></label><br>--%>
<%--<label>Новая фамилия: <input type="text" name="pSurname" /></label><br>--%>
<%--<label>Новое отчество: <input type="text" name="pPatronymic" /></label><br>--%>

<%--<input type="number" hidden name="pCardId" value="${requestScope.patient.pCardId}"/>--%>

<%--<input type="submit" value="Ok" name="Ok"><br>--%>
<%--</form>--%>
</body>
</html>
