<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>${requestScope.article.title}</title>
      <link href="styles/article.css" rel="stylesheet">
</head>
<body>
<header>
      <h1>News Portal</h1>
      <c:if test="${not (sessionScope.user.name eq null) }">
        <div class="auth-links">
          <a href="#"><c:out value="${sessionScope.user.name}" /></a>
          <a href="Controller?command=DO_LOG_OUT">Log out</a>
        </div>
      </c:if>
      <c:if test="${(sessionScope.user.name eq null) }">
        <div class="auth-links">
          <a href="Controller?command=GO_TO_LOGIN_PAGE"
            ><c:out value="Sign in"
          /></a>
          <a href="Controller?command=GO_TO_REGISTRATION_PAGE"
            ><c:out value="Sign up"
          /></a>
        </div>
      </c:if>
    </header>

<div class="container">
<h2>${requestScope.article.title}</h2>
<img src="${requestScope.article.imagePath}" alt="Article image">
<p>${requestScope.article.text}</p>
</div>
</body>
</html>