<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

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

    <c:if test="${sessionScope.isInEditMode}">
        <h2 class="action-title"><fmt:message key="mode.edit.title" /></h2>
    </c:if>
    <c:if test="${sessionScope.isInDeleteMode}">
        <h2 class="action-title"><fmt:message key="mode.delete.title" /></h2>
    </c:if>

    <main>
        <c:forEach var="news" items="${requestScope.mainNews}">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <c:set var="loginCommand" value="go_to_article_page"/>
                    <c:if test="${sessionScope.isInEditMode}">
                        <c:set var="loginCommand" value="go_to_edit_article_page"/>
                    </c:if>
                    <c:if test="${sessionScope.isInDeleteMode}">
                        <c:set var="loginCommand" value="do_delete_article"/>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <c:set var="loginCommand" value="go_to_login_page"/>
                </c:otherwise>
            </c:choose>
            <a class="news-item ${news.tileSize}" href="Controller?command=${loginCommand}&articleId=${news.id}">
                <div class="image-container">
                    <img src="${news.imgPath}" alt="Article image">
                </div>
                <div class="text-content">
                    <h2 class="news-title">${news.title}</h3>
                    <p class="news-text">Author</p>
                </div>
            </a>
        </c:forEach>
    </main>
</div>
</body>
</html>
