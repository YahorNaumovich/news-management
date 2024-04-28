<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>News Portal</title>
  <link href="styles/main.css" rel="stylesheet">
</head>
<body>

<header>
  <h1>News Portal</h1>
      <c:if test="${not (sessionScope.user.name eq null) }">
  <div class="auth-links">
          <c:out value="${sessionScope.user.name}"/>
  </div>
      </c:if>
        <c:if test="${(sessionScope.user.name eq null) }">
  <div class="auth-links">
  <a href="Controller?command=GO_TO_LOGIN_PAGE"><c:out value="Sign in"/></a>
  <a href="Controller?command=GO_TO_REGISTRATION_PAGE"><c:out value="Sign up"/></a>
  </div>
        </c:if>
</header>

<div class="main-container">
<main>
  <div class="news-item">
    <h2 class="news-title">Breaking News: Example Headline</h2>
  </div>

  <div class="news-item r1c2">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item r2c2">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item r2c1">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>
  <div class="news-item">
    <h2 class="news-title">Another News: Example Headline</h2>
  </div>

  <!-- Repeat the same structure for other news items -->

</main>
</div>

</body>
</html>
