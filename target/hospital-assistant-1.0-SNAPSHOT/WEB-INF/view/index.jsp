
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="../../js/bootstrap.min.js"></script>


</head>
<body>
<div class="form-group col-xs-1">
</div>
<div class="form-group col-xs-9">

        <h2>Список бациентов больницы</h2><br />
        <table>
            <tr>
                <td>
                    <table class="table table-striped" border="1">
                        <thead class="thead-dark">
                        <tr >
                            <th width="150">Имя</th>
                            <th width="150">Фамилия</th>
                            <th width="150">Отчество</th>
                            <th width="150">Возраст</th>
                            <th width="150">Пол</th>
                            <th width="150">Дата рождения</th>
                            <th width="150">Дата поступления</th>
                            <th width="150">Дата выписки</th>
                            <th width="150">Подробности</th>
                            <%--<th width="150">CardId</th>--%>
                        </tr>
                        </thead>
                        <c:forEach var="patient" items="${requestScope.patientsDb}">
                        <tr>
                            <td ><c:out value="${patient.pName}"/></td>
                            <td ><c:out value="${patient.pSurname}"/></td>
                            <td ><c:out value="${patient.pPatronymic}"/></td>
                            <td ><c:out value="${patient.pAge}"/></td>
                            <td ><c:out value="${patient.pSex}"/></td>
                            <td ><c:out value="${patient.pBirthDateString}"/></td>
                            <td ><c:out value="${patient.pArrivalDateString}"/></td>
                            <td ><c:out value="${patient.pDischargeDateString}"/></td>
                            <td valign="center">
                                <div>
                                    <form method="get" action="<c:url value='/patient'/>">
                                        <input type="number" hidden name="pCardId" value="${patient.pCardId}" />
                                        <button type="submit" class="btn btn-primary">Личное дело</button>
                                        <%--<input type="submit" value="Личное дело"/>--%>
                                    </form>
                                </div>
                            </td>
                            <%--<td valign="center"><c:out value="${patient.pCardId}"/></td>--%>
                        </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <%--<a href="<c:url value="/logout"/>">Logout</a>--%>
                    <form method="get" action="<c:url value='/create'/>">
                        <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
                        <button type="submit" class="btn btn-primary">Зарегистрировать пациента</button>
                    </form>

                </td>
            </tr>
            <tr>
                <td>

                    <form method="get" action="<c:url value='/logout'/>">
                        <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
                            <button type="submit" class="btn btn-danger">Выйти</button>
                    </form>

                </td>
            </tr>
        </table>
</div>
</body>
</html>
