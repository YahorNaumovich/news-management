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
    <title><fmt:message key="page.registration.title" /></title>
    <link href="styles/registration.css" rel="stylesheet">
</head>
<body>
<h1><fmt:message key="page.registration.title" /></h1>

<c:if test="${not empty sessionScope.errorMessage}">
    <div class="error-message">
        <fmt:message key="${sessionScope.errorMessage}"/>
    </div>
    <c:remove var="errorMessage" scope="session"/>
</c:if>

<c:if test="${not empty requestScope.errorMessage}">
    <div class="error-message">
        <fmt:message key="${requestScope.errorMessage}"/>
    </div>
</c:if>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="do_registration"/>
    <div>
        <input type="text" id="username" name="username" placeholder="<fmt:message key="user.form.usernamePlaceholder" />" required>
    </div>
    <div>
        <input type="text" id="email" name="email" placeholder="<fmt:message key="user.form.emailPlaceholder" />" required>
    </div>
    <div>
        <input type="password" id="password" name="password" placeholder="<fmt:message key="user.form.passwordPlaceholder" />" required>
    </div>
    <div>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="<fmt:message key="user.from.confirmPasswordPlaceholder" />" required>
    </div>
    <div>
        <button type="submit"><fmt:message key="user.action.signUp" /></button>
    </div>
</form>
</body>
</html>
