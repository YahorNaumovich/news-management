<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${requestScope.article.title}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            width: 800px;
            margin: 30px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        input[type="text"],
        input[type="password"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="file"] {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            background-color: #fff; /* Add background color to match form */
        }

        button {
            display: block;
            width: 100%;
            padding: 10px;
            border: none;
            background-color: #007bff;
            color: #fff;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .form-group {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container">
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="do_edit_article"/>
        <div class="form-group">
            <label for="articleId"><fmt:message key="articleFormId" /></label>
            <textarea id="articleId" name="articleId" rows="1" cols="50" readonly>${article.id}</textarea>
        </div>
        <div class="form-group">
            <label for="title"><fmt:message key="articleFormTitle" /></label>
            <textarea id="title" name="title" rows="2" cols="50">${article.title}</textarea>
        </div>
        <div class="form-group">
            <label for="articleText"><fmt:message key="articleFormText" /></label>
            <textarea id="articleText" name="articleText" rows="10" cols="50">${article.text}</textarea>
        </div>

        <div class="form-group">
            <label for="image"><fmt:message key="articleFormImage" /></label>
            <input type="file" id="image" name="image" accept="image/*">
        </div>
        <div class="form-group">
            <label for="tileSize"><fmt:message key="articleFormTileSize" /></label>
            <select id="tileSize" name="tileSize">
                <option value="1x1">1x1</option>
                <option value="r1c2">1x2</option>
                <option value="r2c1">2x1</option>
                <option value="r2c2">2x2</option>
            </select>
        </div>
        <div class="form-group">
            <button type="submit"><fmt:message key="editArticleFormAction" /></button>
        </div>
    </form>
</div>
</body>
</html>
