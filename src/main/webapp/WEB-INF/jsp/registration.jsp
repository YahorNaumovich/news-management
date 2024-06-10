<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Регистрация</title>
      <link href="styles/registration.css" rel="stylesheet">
</head>
<body>
<h1><fmt:message key="registrationPageTitle" /></h1>
<div class="error-message" id="error-message">
    <c:if test="${not (param.authError eq null) }">
        <c:out value="${param.authError}"/>
    </c:if>
</div>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="do_registration"/>
    <div>
        <input type="text" id="username" name="username" placeholder="<fmt:message key="usernamePlaceholder" />" required>
    </div>
    <div>
        <input type="text" id="email" name="email" placeholder="<fmt:message key="emailPlaceholder" />" required>
    </div>
    <div>
        <input type="password" id="password" name="password" placeholder="<fmt:message key="passwordPlaceholder" />" required>
    </div>
    <div>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="<fmt:message key="confirmPasswordPlaceholder" />" required>
    </div>
    <div>
        <button type="submit"><fmt:message key="signUp" /></button>
    </div>
</form>
</body>
</html>