<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>createAppointment</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="form-group col-xs-2">
        </div>
        <div class="form-group col-xs-6">
            <div class="form-group">

        <h2>Введите данные приема</h2>
        <br />

        <form method="post" action="<c:url value='/appointment'/>">
            <c:set var="fin" value="${requestScope.fin}"/>

            <label for="appDate">Дата приема:</label>
            <input type="date" id="appDate" name="appDate" class="form-control" value="${fin}" readonly /><br>
            <label for="appValue">Стоимость приема:</label>
            <input type="text" id="appValue" class="form-control" min="1" name="appValue" value="250" readonly /><br>
            <label for="appCompliant">Жалоба пациента:</label>
            <textarea id="appCompliant" class="form-control" style="resize:none" placeholder="Не меньше 10 знаков" name="appComplaint" rows="8" minlength="10" required></textarea><br>
            <label for="docId">Врач:</label>
            <select name="docId" id="docId" class="form-control" size="1">
                <c:forEach var="doctor" items="${requestScope.doctorsDb}">
                <option value="${doctor.empId}">${doctor.empSurname} ${doctor.empName}, ${doctor.posName}</option>
                </c:forEach>
            </select>

            <input type="number" hidden name="pCardId" value="${requestScope.pCardId}" />
            <br>
            <button type="submit" class="btn btn-primary">Зарегистрировать</button>
            <button type="reset" class="btn btn-danger">Сброс</button>
            <br>
        </form>


        <form method="get" action="<c:url value='/patient'/>">

        <input type="number" hidden name="pCardId" value="${requestScope.pCardId}" />
        <button type="submit" class="btn btn-primary">К личному делу пациента</button>

        </form>
            </div>
        </div>

    </div>


</body>
</html>
