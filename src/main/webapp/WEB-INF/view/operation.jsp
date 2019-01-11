<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateOperation</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="../../js/bootstrap.min.js"></script>
</head>
    <body>
    <div class="container">
        <div class="form-group col-xs-2">
        </div>
        <div class="form-group col-xs-6">
            <div class="form-group">

                <h2>Введите данные об операции</h2>
                <br />

                <form method="post" action="<c:url value='/operation'/>">
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
                        <label for="operId">Выберите операцию:</label>
                        <select name="operId" id="operId" class="form-control" size="1" required>
                            <c:forEach var="operation" items="${requestScope.operationsDb}">
                                <option value="${operation.operId}">${operation.operId} ${operation.operName} ${operation.operValue} ${operation.operLength}
                                        ${operation.surname} ${operation.name} ${operation.patronymic} ${operation.posName}</option>
                                </c:forEach>
                        </select>
                    </div>

                    <br/>

                    <div>
                        <c:set var="fin" value="${requestScope.fin}"/>
                        <label for="operDate">Дата проведения операции:</label>
                            <input type="date" id="operDate" class="form-control" name="operDate" min="${fin}" required/>
                        <br>
                        <button type="submit" class="btn btn-primary">Назначить операцию</button>
                        <button type="reset" class="btn btn-danger">Сброс</button>
                        <%--<input type="submit" value="Назначить операцию"> <input type="reset" name="сброс"><br>--%>
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
