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
    <style>
        /* Style The Dropdown Button */
        .dropbtn {
            background-color: transparent; /* Transparent background */
            color: #fff; /* White text */
            padding: 10px;
            font-size: 16px;
            border: none;
            cursor: pointer;
            font-weight: bold; /* Bold text */
        }

        /* The container <div> - needed to position the dropdown content */
        .dropdown {
            position: relative;
            display: inline-block;
        }

        /* Dropdown Content (Hidden by Default) */
        .dropdown-content {
            display: none;
            position: absolute;
            min-width: 160px;
            background-color: #fff; /* White background */
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
            right: 0;
            border-radius: 5px; /* Rounded corners */
            padding: 8px 0; /* Padding */
            border: 1px solid #ddd; /* Border */
        }

        /* Links inside the dropdown */
        .dropdown-content a {
            color: #333; /* Dark text color */
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            transition: background-color 0.3s; /* Smooth transition */
        }

        /* Change color of dropdown links on hover */
        .dropdown-content a:hover {
            background-color: #f1f1f1; /* Light gray background on hover */
        }

        /* Show the dropdown menu on hover */
        .dropdown:hover .dropdown-content {
            display: block;
        }

        /* Change the background color of the dropdown button when the dropdown content is shown */
        .dropdown:hover .dropbtn {
            background-color: transparent; /* Transparent background */
            color: #f1f1f1; /* Blue text color */
        }
    </style>
</head>
<body>
<header>
    <h1>News Portal</h1>
    <c:if test="${not (sessionScope.user.name eq null) }">
        <div class="dropdown">
            <button class="dropbtn">${sessionScope.user.name}</button>
            <div class="dropdown-content">

                <c:set var="isAdmin" value="${sessionScope.user.role eq 'ADMINISTRATOR'}" />
                <c:set var="isContributor" value="${sessionScope.user.role eq 'CONTRIBUTOR'}" />
                <c:set var="isModerator" value="${sessionScope.user.role eq 'MODERATOR'}" />

                <c:choose>
                    <c:when test="${isAdmin or isContributor or isModerator}">
                        <a href="#">Add article</a>
                        <a href="#">Edit article</a>
                    </c:when>
                    <c:when test="${isAdmin or isModerator}">
                        <a href="#">Delete article</a>
                    </c:when>
                    <c:when test="${isAdmin}">
                        <a href="#">Manage users</a>
                    </c:when>
                </c:choose>

                <a href="#">Your Profile</a>
                <a href="Controller?command=DO_LOG_OUT">Log out</a>
            </div>
        </div>
    </c:if>
    <c:if test="${(sessionScope.user.name eq null) }">
        <div class="auth-links">
            <a href="Controller?command=GO_TO_LOGIN_PAGE"><c:out value="Sign in" /></a>
            <a href="Controller?command=GO_TO_REGISTRATION_PAGE"><c:out value="Sign up" /></a>
        </div>
    </c:if>
</header>

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
