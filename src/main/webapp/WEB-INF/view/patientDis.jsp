<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PatientData</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="../../js/bootstrap.min.js"></script>
</head>
<div>
    <div class="container">

        <div class="form-group col-xs-10">
            <div class="form-group">


                <%--<div>pCardId: <c:out value="${requestScope.patient.pCardId}"/> </div>--%>
                <label for="pName">Имя:</label>
                <input type="text" id="pName" class="form-control" value="${requestScope.patient.pName}" readonly/><br>
                <label for="pSurname">Фамилия:</label>
                <input type="text" id="pSurname" class="form-control" value="${requestScope.patient.pSurname}" readonly/><br>
                <label for="pPatronymic">Отчество:</label>
                <input type="text" id="pPatronymic" class="form-control" value="${requestScope.patient.pPatronymic}" readonly/><br>
                <label for="pAge">Возраст(полных лет):</label>
                <input type="text" id="pAge" class="form-control" value="${requestScope.patient.pAge}" readonly/><br>
                <label for="pSex">Пол:</label>
                <input type="text" id="pSex" class="form-control" value="${requestScope.patient.pSex}" readonly/><br>
                <label for="pBirthDate">Дата рождения:</label>
                <input type="text" id="pBirthDate" class="form-control" value="${requestScope.patient.pBirthDate}" readonly/><br>
                <label for="pArrivalDate">Дата поступления:</label>
                <input type="datextte" id="pArrivalDate" class="form-control" value="${requestScope.patient.pArrivalDate}" readonly/><br>
                <div>АДРЕС</div>
                <br />
                <label for="city">Город:</label>
                <input type="text" id="city" class="form-control" value="${requestScope.patient.pAddress.city}" readonly/><br>
                <label for="street">Улица:</label>
                <input type="text" id="street" class="form-control" value="${requestScope.patient.pAddress.street}" readonly/><br>
                <label for="houseNumber">Номер дома:</label>
                <input type="text" id="houseNumber" class="form-control" value="${requestScope.patient.pAddress.houseNumber}" readonly/><br>
                <label for="flatNumber">Номер квартиры:</label>
                <input type="text" id="flatNumber" class="form-control" value="${requestScope.patient.pAddress.flatNumber}" readonly/><br>
            </div>

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
                                <table class="table table-striped" border="1">
                                    <thead>
                                    <tr>
                                        <th>номер приема</th>
                                        <th>дата приема</th>
                                        <th>стоимость приема</th>
                                        <th width="300">жалоба</th>
                                        <th>доктор</th>
                                        <th>карта</th>
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
                                <table class="table table-striped" border="1">
                                    <thead>
                                    <tr>
                                        <th>номер диагноза</th>
                                        <th width="300">диагноз</th>
                                        <th>карта</th>
                                    </tr>
                                    </thead>
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
            <br>
            <br>



            <%----------------------------ОПЕРАЦИИ--------------------------------------%>
            <table>
                <c:set var="operMedPro" value="${requestScope.operMedPro}"/>
                <c:choose>
                    <c:when test="${operMedPro==true}">
                        <tr>
                            <c:set var="operSize" value="${requestScope.operSize}"/>
                            <c:choose>
                                <c:when test="${operSize gt 0}">
                                    <%--${operSize}--%>
                                    <td>

                                        <div>
                                            <h3>Операции</h3>
                                        </div>
                                        <table class="table table-striped" border="1">
                                            <thead>
                                            <tr>
                                                <th>do_id</th>
                                                <th>diag_id</th>
                                                <th>diag_name</th>
                                                <th>oper_id</th>
                                                <th>oper_name</th>
                                                <th>oper_date</th>
                                                <th>oper_done</th>
                                                <th>doctor_id</th>
                                                <th>emp_surname</th>
                                                <th>pos_name</th>
                                            </tr>
                                            </thead>
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
                                        <table class="table table-striped" border="1">
                                            <thead>
                                            <tr align="center">
                                                <th>dm_id</th>
                                                <th>diag_id</th>
                                                <th>diag_name</th>
                                                <th>med_id</th>
                                                <th>med_name</th>
                                                <th>med_start</th>
                                                <th>med_end</th>
                                                <th>med_done</th>
                                                <th>emp_id</th>
                                                <th>emp_surname</th>
                                                <th>pos_name</th>
                                            </tr>
                                            </thead>
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
                                        <table class="table table-striped" border="1">
                                            <thead>
                                            <tr>
                                                <th>dp_id</th>
                                                <th>diag_id</th>
                                                <th>diag_name</th>
                                                <th>proc_id</th>
                                                <th>proc_name</th>
                                                <th>proc_date</th>
                                                <th>proc_done</th>
                                                <th>emp_id</th>
                                                <th>emp_surname</th>
                                                <th>pos_name</th>
                                            </tr>
                                            </thead>
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


            <br/>
            <form method="get" action="<c:url value='/read'/>">
                <%--<input type="number" hidden name="pCardId" value="${requestScope.pCardId}" />--%>
                <button type="submit" class="btn btn-primary">К списку пациентов</button>
            </form>



        </div>


    </div>
</div>
</body>
</html>