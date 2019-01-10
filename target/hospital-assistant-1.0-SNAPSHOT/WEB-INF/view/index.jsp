
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="F://Rostislav//EPAM//HOSPITAL//HOSPITAL//src//main//webapp//css//bootstrap.min.css">
    <script src="F://Rostislav//EPAM//HOSPITAL//HOSPITAL//src//main//webapp//js//bootstrap.min.js"></script>


</head>
<body>
    <h2>Список бациентов больницы</h2><br />
    <table>
        <tr>
            <td>
                <table align="left" border="1">
                    <tr align="center">
                        <td width="150">Имя</td>
                        <td width="150">Фамилия</td>
                        <td width="150">Отчество</td>
                        <td width="150">Дата рождения</td>
                        <td width="150">Дата поступления</td>
                        <td width="150">Дата выписки</td>
                        <td width="150">Обновить</td>
                        <td width="150">CardId</td>
                    </tr>
                    <c:forEach var="patient" items="${requestScope.patientsDb}">
                    <tr align="center">
                        <td ><c:out value="${patient.pName}"/></td>
                        <td ><c:out value="${patient.pSurname}"/></td>
                        <td ><c:out value="${patient.pPatronymic}"/></td>
                        <td ><c:out value="${patient.pBirthDate}"/></td>
                        <td ><c:out value="${patient.pArrivalDate}"/></td>
                        <td ><c:out value="${patient.pDischargeDate}"/></td>
                       <%--<td valign="center">--%>
                            <%--<div>--%>
                            <%--<form method="get" action="<c:url value='/update'/>">--%>
                            <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
                            <%--<input type="submit" value="Редактировать"/>--%>
                            <%--</form>--%>
                            <%--</div>--%>

                        <%--</td>--%>
                        <td valign="center">
                            <div>
                                <form method="get" action="<c:url value='/patient'/>">
                                    <input type="number" hidden name="pCardId" value="${patient.pCardId}" />
                                    <input type="submit" value="Личное дело"/>
                                </form>
                            </div>
                        </td>
                        <td valign="center"><c:out value="${patient.pCardId}"/></td>
                    </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <%--<a href="<c:url value="/logout"/>">Logout</a>--%>
                <form method="get" action="<c:url value='/logout'/>">
                    <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
                    <input type="submit" value="Выйти"/>
                </form>
            </td>
            <td>
                <%--<a href="<c:url value="/logout"/>">Logout</a>--%>
                <form method="get" action="<c:url value='/create'/>">
                    <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
                    <input type="submit" value="Зарегистрировать пациента"/>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>
