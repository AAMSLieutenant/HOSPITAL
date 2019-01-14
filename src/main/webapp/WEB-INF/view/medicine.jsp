<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>createProcedure</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="form-group col-xs-2">
    </div>
    <div class="form-group col-xs-6">
        <div class="form-group">

            <h2>Введите данные о лекарствах</h2>
            <br />

            <form method="post" action="<c:url value='/medicine'/>">
                <br/>
                <div>
                    <label for="diagId">Выберите диагноз:</label>
                        <select name="diagId" id="diagId" class="form-control" size="1" required>
                            <c:forEach var="diagnose" items="${requestScope.diagnosesDb}">
                                <option value="${diagnose.diagId}">${diagnose.diagName}</option>
                            </c:forEach>
                        </select>
                </div>

                <br/>

                <div>
                    <label for="medId">Выберите препараты:</label>
                        <select name="medId" id="medId" class="form-control" size="1" required>
                            <c:forEach var="medicine" items="${requestScope.medicineDb}">
                                <option value="${medicine.medId}"> ${medicine.medName}, стоимость: ${medicine.medValue},
                                        ответственный: ${medicine.surname} ${medicine.name}, ${medicine.posName}</option>
                            </c:forEach>
                        </select>

                </div>

                <br/>

                <div>
                    <c:set var="fin" value="${requestScope.fin}"/>
                    <label for="medStart">Дата назначения препарата:</label>
                        <input type="date" id="medStart" class="form-control" name="medStart" value="${fin}"  disabled/>
                </div>
                <br/>
                <div>
                    <label for="medEnd">Дата окончания приема препарата: </label>
                        <input type="date" id="medEnd" class="form-control" name="medEnd" min="${fin}" required/>
                    <br>
                    <button type="submit" class="btn btn-primary">Назначить лекарство</button>
                    <button type="reset" class="btn btn-danger">Сброс</button>
                    <%--<input type="submit" id="" value="Назначить"><input type="reset" name="сброс"><br>--%>
                </div>

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
