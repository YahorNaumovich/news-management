<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1"/>
    <title><fmt:message key="page.login.title" /></title>
    <link href="styles/login.css" rel="stylesheet">
</head>
<body>
<h1><fmt:message key="page.login.title" /></h1>

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
    <input type="hidden" name="command" value="do_auth"/>
    <div>
        <input
            type="text"
            id="login"
            name="login"
            placeholder="<fmt:message key="user.form.emailPlaceholder" />"
            required
        />
    </div>
    <div>
        <input
            type="password"
            id="password"
            name="password"
            placeholder="<fmt:message key="user.form.passwordPlaceholder" />"
            required
        />
    </div>
    <div>
        <input type="checkbox" id="checkbox" name="checkbox"/>
        <label for="checkbox"><fmt:message key="user.form.rememberMe" /></label>
    </div>
    <div>
        <button type="submit"><fmt:message key="user.action.signIn" /></button>
    </div>
</form>
<a href="Controller?command=GO_TO_REGISTRATION_PAGE">
    <fmt:message key="user.form.registrationLink" />
</a>
</body>
</html>
