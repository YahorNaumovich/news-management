<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<link href="styles/header.css" rel="stylesheet">

<div class="language-selector">
    <a href="Controller?command=set_Locale&lang=en">English</a> |
    <a href="Controller?command=set_Locale&lang=ru">Русский</a>
</div>

<header>
    <h1><a href="Controller?command=go_to_index_page"><fmt:message key="title" /></a></h1>
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <div class="header-links">
                <c:set var="isAdmin" value="${sessionScope.user.role eq 'ADMINISTRATOR'}" />
                <c:set var="isContributor" value="${sessionScope.user.role eq 'CONTRIBUTOR'}" />
                <c:set var="isModerator" value="${sessionScope.user.role eq 'MODERATOR'}" />
                <c:if test="${isAdmin or isContributor or isModerator}">
                    <a class="article-action-link" href="Controller?command=go_to_add_article_page"><fmt:message key="addArticle" /></a>
                    <a class="article-action-link <c:if test="${sessionScope.isInEditMode}">active</c:if>" href="Controller?command=select_article_to_edit"><fmt:message key="editArticle" /></a>
                </c:if>
                <c:if test="${isAdmin or isModerator}">
                    <a class="article-action-link <c:if test="${sessionScope.isInDeleteMode}">active</c:if>" href="Controller?command=select_article_to_delete"><fmt:message key="deleteArticle" /></a>
                </c:if>
                <c:if test="${isAdmin}">
                    <a class="article-action-link" href="Controller?command=go_to_manage_users_page"><fmt:message key="manageUsers" /></a>
                </c:if>
            </div>
            <div class="dropdown">
                <button class="dropbtn">${sessionScope.user.name}</button>
                <div class="dropdown-content">
                    <a href="#"><fmt:message key="yourProfile" /></a>
                    <a href="Controller?command=DO_LOG_OUT"><fmt:message key="logout" /></a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="auth-links">
                <a href="Controller?command=GO_TO_LOGIN_PAGE"><fmt:message key="signIn" /></a>
                <a href="Controller?command=GO_TO_REGISTRATION_PAGE"><fmt:message key="signUp" /></a>
            </div>
        </c:otherwise>
    </c:choose>
</header>
