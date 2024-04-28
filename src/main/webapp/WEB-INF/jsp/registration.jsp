<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Регистрация</title>
      <link href="styles/registration.css" rel="stylesheet">
</head>
<body>
<h1>Register new user</h1>
<div class="error-message" id="error-message">
    <c:if test="${not (param.authError eq null) }">
        <c:out value="${param.authError}"/>
    </c:if>
</div>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="DO_REGISTRATION"/>
    <div>
        <input type="text" id="username" name="username" placeholder="Username" required>
    </div>
    <div>
        <input type="text" id="email" name="email" placeholder="Email address" required>
    </div>
    <div>
        <input type="password" id="password" name="password" placeholder="Password" required>
    </div>
    <div>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm password" required>
    </div>
    <div>
        <button type="submit">Sign up</button>
    </div>
</form>
</body>
</html>