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
    <title>Войти</title>
      <link href="styles/login.css" rel="stylesheet">
</head>
<body>
<h1><fmt:message key="loginPageTitle" /></h1>
<div class="error-message" id="error-message">
    <c:if test="${not (param.authError eq null) }">
        <c:out value="${param.authError}"/>
    </c:if>
</div>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="do_auth"/>
    <div>
        <input
                type="text"
                id="login"
                name="login"
                placeholder="<fmt:message key="emailPlaceholder" />"
                required
        />
    </div>
    <div> 
        <input
                type="password"
                id="password"
                name="password"
                placeholder="<fmt:message key="passwordPlaceholder" />"
                required
        />
    </div>
    <div>
        <input type="checkbox" id="checkbox" name="checkbox"/>
        <label for="checkbox"><fmt:message key="loginRememberMe" /></label>
    </div>
    <div>
        <button type="submit"><fmt:message key="signIn" /></button>
    </div>
</form>
<a href="Controller?command=GO_TO_REGISTRATION_PAGE"
><fmt:message key="loginRegistration" /></a
>
</body>
</html>
