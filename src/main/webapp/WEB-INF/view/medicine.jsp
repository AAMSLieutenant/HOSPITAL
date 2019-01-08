<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Введите данные о лекарствах</h2>
<br />

<form method="post" action="<c:url value='/medicine'/>">
    <br/>
    <div>
        <label>Выберите диагноз:
            <select name="diagId" size="1">
                <c:forEach var="diagnose" items="${requestScope.diagnosesDb}">
                    <option value="${diagnose.diagId}">${diagnose.diagId} ${diagnose.diagName}</option>
                </c:forEach>
            </select>
        </label>
    </div>

    <br/>

    <div>
        <label>Выберите препараты:
            <select name="medId" size="1">
                <c:forEach var="medicine" items="${requestScope.medicineDb}">
                    <option value="${medicine.medId}"> MEDICINE ${medicine.medId} ${medicine.medName} ${medicine.medValue}
                            ${medicine.surname} ${medicine.name} ${medicine.patronymic} ${medicine.posName}</option>
                </c:forEach>
            </select>
        </label>
    </div>

    <br/>

    <div>
        <label>Дата назначения препарата:
            <input type="date" name="medStart" />
        </label>
    </div>
    <br/>
    <div>
        <label>Дата окончания приема препарата:
            <input type="date" name="medEnd" />
        </label>
        <br>
        <input type="submit" value="Назначить"><br>
    </div>
</form>
</body>
</html>
