<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>News Portal</title>
    <link href="styles/main.css" rel="stylesheet" />
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

    <div class="main-container">
      <main>

      		<c:forEach var="news" items="${requestScope.mainNews}">
      		        <a class="news-item ${news.tileSize}" href="#">
      		            <div class="image-container">
      				        <img src="${news.imgPath}" alt="Article image">
      				    </div>
      				    <div class="text-content">
      					    <h2 class="news-title">${news.title}</h3>
      					    <p class="news-text">${news.source}</p>
      				    </div>
      				</a>
      		</c:forEach>

        <!-- Repeat the same structure for other news items -->
      </main>
    </div>
  </body>
</html>
