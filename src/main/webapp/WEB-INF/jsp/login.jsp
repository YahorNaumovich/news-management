<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1"/>
    <title>Войти</title>
      <link href="styles/login.css" rel="stylesheet">
</head>
<body>
<h1>Log in, please</h1>
<div class="error-message" id="error-message">
    <c:if test="${not (param.authError eq null) }">
        <c:out value="${param.authError}"/>
    </c:if>
</div>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="DO_AUTH"/>
    <div>
        <input
                type="text"
                id="login"
                name="login"
                placeholder="Email address"
                required
        />
    </div>
    <div> 
        <input
                type="password"
                id="password"
                name="password"
                placeholder="Password"
                required
        />
    </div>
    <div>
        <input type="checkbox" id="checkbox" name="checkbox"/>
        <label for="checkbox">Remember me</label>
    </div>
    <div>
        <button type="submit">Sign in</button>
    </div>
</form>
<a href="Controller?command=GO_TO_REGISTRATION_PAGE"
>Register new account</a
>
</body>
</html>
