<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<link href="styles/header.css" rel="stylesheet">

<header>
    <h1>News Portal</h1>
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
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
        </c:when>
        <c:otherwise>
            <div class="auth-links">
                <a href="Controller?command=GO_TO_LOGIN_PAGE">Sign in</a>
                <a href="Controller?command=GO_TO_REGISTRATION_PAGE">Sign up</a>
            </div>
        </c:otherwise>
    </c:choose>
</header>