<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>pCardId: <c:out value="${requestScope.patient.pCardId}"/> </div>
<div>Имя: <c:out value="${requestScope.patient.pName}"/> </div>
<div>Фамилия: <c:out value="${requestScope.patient.pSurname}"/> </div>
<div>Отчество: <c:out value="${requestScope.patient.pPatronymic}"/> </div>
<div>Пол: <c:out value="${requestScope.patient.pSex}"/> </div>
<div>Дата рождения: <c:out value="${requestScope.patient.pBirthDate}"/> </div>
<div>Дата поступления: <c:out value="${requestScope.patient.pArrivalDate}"/> </div>
<br />

<%--<div>--%>
    <%--<form method="post" action="<c:url value='/appointment'/>">--%>
        <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
        <%--<input type="submit" value="Приемы"/>--%>
    <%--</form>--%>
<%--</div>--%>
<br />
<div>
    <div><h3>Приемы</h3></div>
</div>


<c:set var="appointments" value="${requestScope.isApp}"/>
<c:choose>
    <c:when test="${appointments==false}">
        NO APPOINTMENTS FOR THIS PATIENT
    </c:when>
    <c:when test="${appointments==true}">
        THERE IS AN APPOINTMENT
    </c:when>
</c:choose>



<%--<form method="post" action="<c:url value='/update'/>">--%>

    <%--<label>Новое имя: <input type="text" name="pName" /></label><br>--%>
    <%--<label>Новая фамилия: <input type="text" name="pSurname" /></label><br>--%>
    <%--<label>Новое отчество: <input type="text" name="pPatronymic" /></label><br>--%>

    <%--<input type="number" hidden name="pCardId" value="${requestScope.patient.pCardId}"/>--%>

    <%--<input type="submit" value="Ok" name="Ok"><br>--%>
<%--</form>--%>
</body>
</html>
