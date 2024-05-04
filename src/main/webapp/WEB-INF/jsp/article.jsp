<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${requestScope.article.title}</title>
    <link href="styles/article.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container">
    <h2>${requestScope.article.title}</h2>
    <img src="${requestScope.article.imagePath}" alt="Article image">
    <p>${requestScope.article.text}</p>
</div>
</body>
</html>
