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
    <title>User Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            width: 400px;
            margin: 30px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            display: grid;
            grid-template-columns: 1fr 1fr;
            grid-gap: 20px;
            align-items: center;
        }

        .info-label {
            font-weight: bold;
        }

        .info-value {
            margin: 0;
        }

        .error-message {
            grid-column: span 2;
            text-align: center;
            color: red;
            margin-bottom: 10px;
        }

        .button {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
        }

        .button:hover {
            background-color: #0056b3;
        }

        .user-form{
            margin-top: 20px;
        }

        .form-field {
            grid-column: span 2;
        }

        .form-field input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container">
    <c:if test="${not empty param.errorMessage}">
        <div class="error-message" id="error-message">
            <c:out value="${param.errorMessage}"/>
        </div>
    </c:if>

    <div class="info-label">Your username:</div>
    <div class="info-value">${sessionScope.user.name}</div>

    <div class="info-label">Your role:</div>
    <div class="info-value">${sessionScope.user.role}</div>

    <form class="user-form" action="Controller" method="post">
        <input type="hidden" name="command" value="DO_CHANGE_USER_PASSWORD" />
        <input type="hidden" name="userId" value="${sessionScope.user.id}" />
        <div class="form-field">
            <label for="new-password" class="info-label">New Password:</label>
            <input type="password" id="new-password" name="newPassword" required />
        </div>
        <div class="form-field">
            <label for="confirm-password" class="info-label">Confirm Password:</label>
            <input type="password" id="confirm-password" name="confirmPassword" required />
        </div>
        <div class="button-container">
            <button type="submit" class="button">Change Password</button>
        </div>
    </form>
</div>
</body>
</html>
