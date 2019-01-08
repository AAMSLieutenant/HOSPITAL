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
    <c:set var="fin" value="${requestScope.fin}"/>
    <label>Дата приема: <input type="date" name="appDate" value="${fin}" readonly /></label><br>
    <label>Стоимость приема: <input type="text" min="1" name="appValue" value="250" readonly /></label><br>
    <label>Жалоба пациента: <textarea style="resize:none" name="appComplaint" cols="30" rows="10" minlength="10" required></textarea></label><br>
    <label>Врач:
    <select name="docId" size="1">
        <c:forEach var="doctor" items="${requestScope.doctorsDb}">
        <option value="${doctor.empId}">${doctor.empSurname} ${doctor.empName}, ${doctor.posName}</option>
    <%--<option selected="selected" value="second"><c:out value="${requestScope.arr[1]}"/></option>--%>
    <%--<option value="third"><c:out value="${requestScope.arr[2]}"/></option>--%>
        </c:forEach>
    </select></label>
    <input type="number" hidden name="pCardId" value="${requestScope.pCardId}" />
    <input type="submit" value="Зарегистрировать"> <input type="reset" name="сброс"><br>
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
