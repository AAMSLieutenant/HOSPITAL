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
<%--<select name="menu" size="1">--%>
    <%--<option value="first"><c:out value="${requestScope.arr[0]}"/></option>--%>
    <%--<option selected="selected" value="second"><c:out value="${requestScope.arr[1]}"/></option>--%>
    <%--<option value="third"><c:out value="${requestScope.arr[2]}"/></option>--%>
<%--</select>--%>

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
        <table align="left" border="1">
            <tr align="center">
                <td>номер приема</td>
                <td>дата приема</td>
                <td>стоимость приема</td>
                <td>жалоба</td>
                <td>доктор</td>
                <td>карта</td>
            </tr>
        <c:forEach var="appointment" items="${requestScope.appointmentsDb}">

                <%--this.appId=a.appId;--%>
                <%--this.appDate=a.appDate;--%>
                <%--this.appValue=a.appValue;--%>
                <%--this.appCompliant=a.appCompliant;--%>
                <%--this.docId=a.docId;--%>
                <%--this.cardId=a.cardId;--%>
                <%--this.diagId=a.diagId;--%>

            <tr align="center">
                <td ><c:out value="${appointment.appId}"/></td>
                <td ><c:out value="${appointment.appDate}"/></td>
                <td ><c:out value="${appointment.appValue}"/></td>
                <td ><c:out value="${appointment.appComplaint}"/></td>
                <td ><c:out value="${appointment.docId}"/></td>
                <td ><c:out value="${appointment.cardId}"/></td>
            </tr>

        </c:forEach>
        </table>
    </c:when>
</c:choose>
<div>

    <form method="get" action="<c:url value='/appointment'/>">
        <input type="number" hidden name="pCardId" value="${requestScope.pCardId}" />
        <input type="submit" value="Зарегистрировать прием"/>
    </form>

</div>



<%--<form method="post" action="<c:url value='/update'/>">--%>

    <%--<label>Новое имя: <input type="text" name="pName" /></label><br>--%>
    <%--<label>Новая фамилия: <input type="text" name="pSurname" /></label><br>--%>
    <%--<label>Новое отчество: <input type="text" name="pPatronymic" /></label><br>--%>

    <%--<input type="number" hidden name="pCardId" value="${requestScope.patient.pCardId}"/>--%>

    <%--<input type="submit" value="Ok" name="Ok"><br>--%>
<%--</form>--%>
</body>
</html>
