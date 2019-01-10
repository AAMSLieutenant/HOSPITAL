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
<div>Возраст: <c:out value="${requestScope.patient.pAge}"/> </div>
<div>Пол: <c:out value="${requestScope.patient.pSex}"/> </div>
<div>Дата рождения: <c:out value="${requestScope.patient.pBirthDate}"/> </div>
<div>Дата поступления: <c:out value="${requestScope.patient.pArrivalDate}"/> </div>
<div>Дата выписки: <c:out value="${requestScope.patient.pDischargeDate}"/> </div>
<br />
<div>АДРЕС</div>
<br />
<div>Город: <c:out value="${requestScope.patient.pAddress.city}"/> </div>
<div>Улица: <c:out value="${requestScope.patient.pAddress.street}"/> </div>
<div>Дом: <c:out value="${requestScope.patient.pAddress.houseNumber}"/> </div>
<div>Квартира: <c:out value="${requestScope.patient.pAddress.flatNumber}"/> </div>
<br />

<div>
    <div><h3>Приемы</h3></div>
</div>
<%----------------------------ПРИЕМЫ--------------------------------------%>
<table>
    <tr>
        <td>
            <c:set var="appointments" value="${requestScope.isApp}"/>
            <c:choose>
                <c:when test="${appointments==true}">
                    <table align="left" border="1">
                        <tr align="center">
                            <td>номер приема</td>
                            <td>дата приема</td>
                            <td>стоимость приема</td>
                            <td width="300">жалоба</td>
                            <td>доктор</td>
                            <td>карта</td>
                        </tr>
                        <c:forEach var="appointment" items="${requestScope.appointmentsDb}">
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
        </td>
    </tr>
</table>


<%----------------------------ДИАГНОЗЫ--------------------------------------%>

<table>
    <tr>
        <td>
            <c:set var="appointments" value="${requestScope.isApp}"/>
            <c:choose>
            <c:when test="${appointments==true}">
            <div>
                <h3>Диагнозы</h3>
            </div>
            <c:set var="diagnoses" value="${requestScope.isDiag}"/>
            <c:choose>
                <c:when test="${diagnoses==true}">
                    <table align="left" border="1">
                        <tr align="center">
                            <td>номер диагноза</td>
                            <td width="300">диагноз</td>
                            <td>карта</td>
                        </tr>
                        <c:forEach var="diagnosis" items="${requestScope.diagnosesDb}">

                            <tr align="center">
                                <td ><c:out value="${diagnosis.diagId}"/></td>
                                <td ><c:out value="${diagnosis.diagName}"/></td>
                                <td ><c:out value="${diagnosis.cardId}"/></td>
                            </tr>

                        </c:forEach>
                    </table>
                </c:when>
            </c:choose>
        </td>
    </tr>
    </c:when>
    </c:choose>
</table>


<%----------------------------ОПЕРАЦИИ--------------------------------------%>
<table>
    <c:set var="operMedPro" value="${requestScope.operMedPro}"/>
    <c:choose>
        <c:when test="${operMedPro==true}">
            <tr>
                <c:set var="operSize" value="${requestScope.operSize}"/>
                <c:choose>
                    <c:when test="${operSize gt 0}">
                        ${operSize}
                        <td>

                            <div>
                                <h3>Операции</h3>
                            </div>
                            <table align="left" border="1">
                                <tr align="center">
                                    <td>do_id</td>
                                    <td>diag_id</td>
                                    <td>diag_name</td>
                                    <td>oper_id</td>
                                    <td>oper_name</td>
                                    <td>oper_date</td>
                                    <td>oper_done</td>
                                    <td>doctor_id</td>
                                    <td>emp_surname</td>
                                    <td>pos_name</td>
                                </tr>
                                <c:forEach var="operInfo" items="${requestScope.operInfosDb}">
                                    <tr align="center">
                                        <td ><c:out value="${operInfo.doId}"/></td>
                                        <td ><c:out value="${operInfo.diagId}"/></td>
                                        <td ><c:out value="${operInfo.diagName}"/></td>
                                        <td ><c:out value="${operInfo.operId}"/></td>
                                        <td ><c:out value="${operInfo.operName}"/></td>
                                        <td ><c:out value="${operInfo.operDate}"/></td>
                                        <td ><c:out value="${operInfo.operDone}"/></td>
                                        <td ><c:out value="${operInfo.doctorId}"/></td>
                                        <td ><c:out value="${operInfo.empSurname}"/></td>
                                        <td ><c:out value="${operInfo.posName}"/></td>
                                    </tr>
                                </c:forEach>
                            </table>

                        </td>
                    </c:when>
                </c:choose>
            </tr>
        </c:when>
    </c:choose>
</table>



<%----------------------------МЕДИКАМЕНТЫ--------------------------------------%>

<table>
    <c:set var="operMedPro" value="${requestScope.operMedPro}"/>
    <c:choose>
        <c:when test="${operMedPro==true}">
            <tr>
                <c:set var="medSize" value="${requestScope.medSize}"/>
                <c:choose>
                    <c:when test="${medSize gt 0}">
                        ${medSize}
                        <td>

                            <div>
                                <h3>Лекарства</h3>
                            </div>
                            <table align="left" border="1">
                                <tr align="center">
                                    <td>dm_id</td>
                                    <td>diag_id</td>
                                    <td>diag_name</td>
                                    <td>med_id</td>
                                    <td>med_name</td>
                                    <td>med_start</td>
                                    <td>med_end</td>
                                    <td>med_done</td>
                                    <td>emp_id</td>
                                    <td>emp_surname</td>
                                    <td>pos_name</td>
                                </tr>
                                <c:forEach var="medInfo" items="${requestScope.medInfosDb}">
                                    <tr align="center">
                                        <td ><c:out value="${medInfo.dmId}"/></td>
                                        <td ><c:out value="${medInfo.diagId}"/></td>
                                        <td ><c:out value="${medInfo.diagName}"/></td>
                                        <td ><c:out value="${medInfo.medId}"/></td>
                                        <td ><c:out value="${medInfo.medName}"/></td>
                                        <td ><c:out value="${medInfo.medStart}"/></td>
                                        <td ><c:out value="${medInfo.medEnd}"/></td>
                                        <td ><c:out value="${medInfo.medDone}"/></td>
                                        <td ><c:out value="${medInfo.empId}"/></td>
                                        <td ><c:out value="${medInfo.empSurname}"/></td>
                                        <td ><c:out value="${medInfo.posName}"/></td>
                                    </tr>
                                </c:forEach>
                            </table>

                        </td>
                    </c:when>
                </c:choose>
            </tr>
        </c:when>
    </c:choose>
</table>


<%----------------------------ПРОЦЕДУРЫ--------------------------------------%>


<table>
    <c:set var="operMedPro" value="${requestScope.operMedPro}"/>
    <c:choose>
        <c:when test="${operMedPro==true}">
            <tr>
                <c:set var="procSize" value="${requestScope.procSize}"/>
                <c:choose>
                    <c:when test="${procSize gt 0}">
                        ${procSize}
                        <td>

                            <div>
                                <h3>Процедуры</h3>
                            </div>

                            <c:set var="operMedPro" value="${requestScope.operMedPro}"/>
                            <table align="left" border="1">
                                <tr align="center">
                                    <td>dp_id</td>
                                    <td>diag_id</td>
                                    <td>diag_name</td>
                                    <td>proc_id</td>
                                    <td>proc_name</td>
                                    <td>proc_date</td>
                                    <td>proc_done</td>
                                    <td>emp_id</td>
                                    <td>emp_surname</td>
                                    <td>pos_name</td>
                                </tr>
                                <c:forEach var="procInfo" items="${requestScope.procInfosDb}">
                                    <tr align="center">
                                        <td ><c:out value="${procInfo.dpId}"/></td>
                                        <td ><c:out value="${procInfo.diagId}"/></td>
                                        <td ><c:out value="${procInfo.diagName}"/></td>
                                        <td ><c:out value="${procInfo.procId}"/></td>
                                        <td ><c:out value="${procInfo.procName}"/></td>
                                        <td ><c:out value="${procInfo.procDate}"/></td>
                                        <td ><c:out value="${procInfo.procDone}"/></td>
                                        <td ><c:out value="${procInfo.empId}"/></td>
                                        <td ><c:out value="${procInfo.empSurname}"/></td>
                                        <td ><c:out value="${procInfo.posName}"/></td>
                                    </tr>
                                </c:forEach>
                            </table>

                        </td>
                    </c:when>
                </c:choose>
            </tr>
        </c:when>
    </c:choose>
</table>
<br/>
<form method="get" action="<c:url value='/read'/>">
    <%--<input type="number" hidden name="pCardId" value="${requestScope.pCardId}" />--%>
    <input type="submit" value="К списку пациентов"/>
</form>
</div>

</body>
</html>