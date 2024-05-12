<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<link href="styles/header.css" rel="stylesheet">

<header>
    <h1>News Portal</h1>
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <div class="header-links">
                <c:set var="isAdmin" value="${sessionScope.user.role eq 'ADMINISTRATOR'}" />
                <c:set var="isContributor" value="${sessionScope.user.role eq 'CONTRIBUTOR'}" />
                <c:set var="isModerator" value="${sessionScope.user.role eq 'MODERATOR'}" />
                <c:if test="${isAdmin or isContributor or isModerator}">
                    <a class="article-action-link" href="Controller?command=go_to_add_article_page">Add article</a>
                    <a class="article-action-link" href="Controller?command=select_article_to_edit">Edit article</a>
                </c:if>
                <c:if test="${isAdmin or isModerator}">
                    <a class="article-action-link" href="">Delete article</a>
                </c:if>
                <c:if test="${isAdmin}">
                    <a class="article-action-link" href="#">Manage users</a>
                </c:if>
            </div>
            <div class="dropdown">
                <button class="dropbtn">${sessionScope.user.name}</button>
                <div class="dropdown-content">
                    <a href="#">Your Profile</a>
                    <a href="Controller?command=DO_LOG_OUT">Log out</a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="auth-links">
                <a href="Controller?command=GO_TO_LOGIN_PAGE">Sign in</a>
                <a href="Controller?command=GO_TO_REGISTRATION_PAGE">Sign up</a>
            </div>
        </c:otherwise>
    </c:choose>
</header>
