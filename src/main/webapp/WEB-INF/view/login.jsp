<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="../../js/bootstrap.min.js"></script>

</head>
<body>

<div class="container">

    <div class="form-group col-xs-3">
    </div>
    <div class="form-group col-xs-6">
    <h2>Вход в систему</h2>

        <div class="form-group">
        <form method="post" action="/">
            <label for="login"><h4>Логин</h4></label>
            <input type="text" class="form-control" id="login" required placeholder="логин" name="login">
            <small id="loginHelp" class="form-text text-muted">Введите ваш логин для входа</small><br>
            <label for="password"><h4>Пароль</h4></label>
            <input type="password" class="form-control" id="password" required placeholder="пароль" name="password">
            <small id="passwordHelp" class="form-text text-muted">Введите ваш пароль для входа</small><br>
            <%--<input class="button" type="submit" value="Войти">--%>
            <button type="submit" class="btn btn-primary">Войти</button>
            <button type="reset" class="btn btn-danger">Cбросить</button>
        </form>
        </div>


    </div>
</div>
</body>
</html>
