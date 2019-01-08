<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

</head>
<body>

<div class="form">

    <h1>Вход в систему</h1><br>
    <form method="post" action="/">

        <input type="text" required placeholder="логин" name="login"><br>
        <input type="password" required placeholder="пароль" name="password"><br><br>
        <input class="button" type="submit" value="Войти">

    </form>
</div>
</body>
</html>
