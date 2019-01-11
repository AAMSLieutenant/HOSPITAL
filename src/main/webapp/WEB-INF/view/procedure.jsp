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

            <h2>Введите данные о процедурах</h2>
            <br />

            <form method="post" action="<c:url value='/procedure'/>">
                <br/>
                <div>
                    <label for="diagId">Выберите диагноз:</label>
                        <select name="diagId" id="diagId" class="form-control" size="1" required>
                            <c:forEach var="diagnose" items="${requestScope.diagnosesDb}">
                                <option value="${diagnose.diagId}">${diagnose.diagId} ${diagnose.diagName}</option>
                            </c:forEach>
                        </select>
                </div>

                <br/>

                <div>
                    <label for="procId">Выберите процедуру:</label>
                        <select name="procId" id="procId" class="form-control" size="1" required>
                            <c:forEach var="procedure" items="${requestScope.proceduresDb}">
                                <option value="${procedure.procId}"> PROCEDURE ${procedure.procId} ${procedure.procName} ${procedure.procValue}
                                        ${procedure.surname} ${procedure.name} ${procedure.patronymic} ${procedure.posName}</option>
                            </c:forEach>
                        </select>

                </div>

                <br/>

                <div>
                    <c:set var="fin" value="${requestScope.fin}"/>
                    <label for="procDate">Дата процедуры:</label>
                        <input type="date" id="procDate" class="form-control" name="procDate" min="${fin}" required/>

                    <br>
                    <button type="submit" class="btn btn-primary">Назначить процедуру</button>
                    <button type="reset" class="btn btn-danger">Сброс</button>
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
