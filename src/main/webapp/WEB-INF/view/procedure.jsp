<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Введите данные о процедурах</h2>
<br />

<form method="post" action="<c:url value='/procedure'/>">
    <br/>
    <div>
        <label>Выберите диагноз:
            <select name="diagId" size="1" required>
                <c:forEach var="diagnose" items="${requestScope.diagnosesDb}">
                    <option value="${diagnose.diagId}">${diagnose.diagId} ${diagnose.diagName}</option>
                </c:forEach>
            </select>
        </label>
    </div>

    <br/>

    <div>
        <label>Выберите процедуру:
            <select name="procId" size="1" required>
                <c:forEach var="procedure" items="${requestScope.proceduresDb}">
                    <option value="${procedure.procId}"> PROCEDURE ${procedure.procId} ${procedure.procName} ${procedure.procValue}
                            ${procedure.surname} ${procedure.name} ${procedure.patronymic} ${procedure.posName}</option>
                </c:forEach>
            </select>
        </label>
    </div>

    <br/>

    <div>
        <c:set var="fin" value="${requestScope.fin}"/>
        <label>Дата процедуры:
            <input type="date" name="procDate" min="${fin}" required/>
        </label>
        <br>
        <input type="submit" value="Назначить"><input type="reset" name="сброс"><br>
    </div>
</form>
</body>
</html>
