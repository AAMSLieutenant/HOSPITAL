<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
    <body>

        <h2>Введите данные об операции</h2>
        <br />

        <form method="post" action="<c:url value='/operation'/>">
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
                <label>Выберите операцию:
                    <select name="operId" size="1">
                        <c:forEach var="operation" items="${requestScope.operationsDb}">
                            <option value="${operation.operId}">${operation.operId} ${operation.operName} ${operation.operValue} ${operation.operLength}
                                    ${operation.surname} ${operation.name} ${operation.patronymic} ${operation.posName}</option>
                            </c:forEach>
                    </select>
                </label>
            </div>

            <br/>

            <div>
                <label>Дата проведения операции:
                    <input type="date" name="operDate" />
                </label>
                <br>
                <input type="submit" value="Назначить операцию"><br>
            </div>
        </form>
    </body>
</html>
