<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>News Portal</title>
    <link href="styles/main.css" rel="stylesheet" />
</head>
<body>

<jsp:include page="header.jsp" />

<div class="main-container">
    <c:if test="${sessionScope.isInEditMode}">
                      <h2 class="action-title">Select an article to edit</h2>
                  </c:if>
    <main>

        <c:forEach var="news" items="${requestScope.mainNews}">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <c:set var="loginCommand" value="GO_TO_ARTICLE_PAGE"/>
                        <c:if test="${sessionScope.isInEditMode}">
                                          <c:set var="loginCommand" value="GO_TO_EDIT_ARTICLE_PAGE"/>
                                      </c:if>
                </c:when>
                <c:otherwise>
                    <c:set var="loginCommand" value="GO_TO_LOGIN_PAGE"/>
                </c:otherwise>
            </c:choose>
            <a class="news-item ${news.tileSize}" href="Controller?command=${loginCommand}&articleId=${news.articleId}">
                <div class="image-container">
                    <img src="${news.imgPath}" alt="Article image">
                </div>
                <div class="text-content">
                    <h2 class="news-title">${news.title}</h3>
                    <p class="news-text">${news.source}</p>
                </div>
            </a>
        </c:forEach>
    </main>
</div>
</body>
</html>
