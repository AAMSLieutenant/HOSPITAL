<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>createDiagnosis</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="form-group col-xs-2">
    </div>
        <div class="form-group col-xs-6">
            <div class="form-group">
                <h2>Введите Диагноз</h2>
                <br />

                <form method="post" action="<c:url value='/diagnosis'/>">

                    <label for="diagName">Диагноз: </label>
                    <textarea style="resize:none" id="diagName" class="form-control" placeholder="Не менее 5 знаков" name="diagName" cols="30" rows="10" minlength="5" maxlength="200" required></textarea><br>
                    <button type="submit" class="btn btn-primary">Поставить</button>
                    <button type="reset" class="btn btn-danger">Сброс</button>
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
