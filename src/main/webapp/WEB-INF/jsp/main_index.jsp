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
    <main>
        <c:forEach var="news" items="${requestScope.mainNews}">
            <a class="news-item ${news.tileSize}" href="Controller?command=GO_TO_ARTICLE_PAGE&articleId=${news.articleId}">
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
