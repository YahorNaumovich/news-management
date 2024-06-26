<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<link href="styles/header.css" rel="stylesheet">

<div class="language-selector">
    <a href="Controller?command=set_Locale&lang=en">EN</a> |
    <a href="Controller?command=set_Locale&lang=ru">RU</a>
</div>

<header>
    <h1><a href="Controller?command=go_to_index_page"><fmt:message key="header.link.title" /></a></h1>
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <div class="header-links">
                <c:set var="isAdmin" value="${sessionScope.user.role eq 'ADMINISTRATOR'}" />
                <c:set var="isContributor" value="${sessionScope.user.role eq 'CONTRIBUTOR'}" />
                <c:set var="isModerator" value="${sessionScope.user.role eq 'MODERATOR'}" />
                <c:if test="${isAdmin or isContributor or isModerator}">
                    <a class="article-action-link" href="Controller?command=go_to_add_article_page"><fmt:message key="header.link.addArticleTitle" /></a>
                    <a class="article-action-link <c:if test="${sessionScope.isInEditMode}">active</c:if>" href="Controller?command=select_article_to_edit"><fmt:message key="header.link.editArticleTitle" /></a>
                </c:if>
                <c:if test="${isAdmin or isModerator}">
                    <a class="article-action-link <c:if test="${sessionScope.isInDeleteMode}">active</c:if>" href="Controller?command=select_article_to_delete"><fmt:message key="header.link.deleteArticleTitle" /></a>
                </c:if>
                <c:if test="${isAdmin}">
                    <a class="article-action-link" href="Controller?command=go_to_manage_users_page"><fmt:message key="header.link.manageUsersTitle" /></a>
                </c:if>
            </div>
            <div class="dropdown">
                <button class="dropbtn">${sessionScope.user.name}</button>
                <div class="dropdown-content">
                    <a href="Controller?command=go_to_user_profile_page"><fmt:message key="user.action.yourProfile" /></a>
                    <a href="Controller?command=do_log_out"><fmt:message key="user.action.logout" /></a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="auth-links">
                <a href="Controller?command=go_to_login_page"><fmt:message key="user.action.signIn" /></a>
                <a href="Controller?command=go_to_registration_page"><fmt:message key="user.action.signUp" /></a>
            </div>
        </c:otherwise>
    </c:choose>
</header>
