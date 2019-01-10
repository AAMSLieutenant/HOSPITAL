<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>createPatient</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>


    <div class="container">

        <div class="form-group col-xs-2">
        </div>
        <div class="form-group col-xs-6">
            <h2>Введите данные о пациенте</h2>

            <div class="form-group">

                <form method="post" action="<c:url value='/create'/>">

                    <input type="text" class="form-control" placeholder="имя пациента" name="pName" required/><br>
                    <input type="text" class="form-control" placeholder="фамилия пациента" name="pSurname" required/><br>
                    <input type="text" class="form-control" placeholder="отчество пациента" name="pPatronymic" required/><br>
                    <input type="number" class="form-control" placeholder="возраст (полных лет)" name="pAge" min="1" max="110" required/><br>
                    <input type="text" id="city" class="form-control" placeholder="город проживания" name="city" required/><br>
                    <input type="text" class="form-control" placeholder="улица" name="street" required/><br>
                    <input type="number" class="form-control" placeholder="номер дома" name="houseNumber" min="1"  required/></label><br>
                   <input type="number" class="form-control" placeholder="номер квартиры" name="flatNumber" min="1" required/></label><br>
                    <label for="sex">Пол:<br>
                    <label class="radio-inline"><input type="radio" id="sex" name="pSex" value="male" required>мужской</label>
                    <label class="radio-inline"><input type="radio" name="pSex" value="female" required>женский</label><br><br>

                    <%--<input type="radio" id="sex" name="pSex" value="male" required>мужской--%>
                    <%--<input type="radio" name="pSex" value="female" required>женский</label><br>--%>
                    <c:set var="fin" value="${requestScope.fin}"/>
                        <label for="birthDate">дата рождения:</label>
                    <input type="date" id="birthDate" class="form-control"  name="pBirthDate" min="1900-01-01" max="${fin}" required/><br>
                        <label for="arrivalDate">дата прибытия:</label>
                    <input type="date" id="arrivalDate" class="form-control" name="pArrivalDate" value="${fin}" readonly/><br>


                    <%--<label>дата рождения: "${fin}" <input type="date" name="pBirthDate" min="1900-01-01" max="${fin}" required/></label><br>--%>
                    <%--<label>дата прибытия: <input type="date" name="pArrivalDate" value="${fin}" readonly/></label><br>--%>
                    <%--<br/>--%>
                    <%--<label>АДРЕС--%>
                    <%--<br/>--%>
                    <%--<br/>--%>

                        <button type="submit" class="btn btn-primary">Зарегистрировать</button>
                        <button type="reset" class="btn btn-danger" >Сброс</button>
                </form>
                <br>
                <br>
                <form method="get" action="<c:url value='/read'/>">
                    <%--<input type="number" hidden name="pCardId" value="${patient.pCardId}" />--%>
                    <button type="submit" class="btn btn-primary">К списку пациентов</button>
                    <%--<input type="submit" value="К списку пациентов"/>--%>
                </form>
            </div>

        </div>
    </div>
</body>
</html>
