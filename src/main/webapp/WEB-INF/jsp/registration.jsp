<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Регистрация</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        form {
            width: 300px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
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

        .error-message {
            text-align: center;
            color: red;
        }
    </style>
</head>
<body>
<h1>Register new user</h1>
<div class="error-message" id="error-message">
    <c:if test="${not (param.authError eq null) }">
        <c:out value="${param.authError}"/>
    </c:if>
</div>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="DO_REGISTRATION"/>
    <div>
        <input type="text" id="username" name="username" placeholder="Username" required>
    </div>
    <div>
        <input type="text" id="email" name="email" placeholder="Email address" required>
    </div>
    <div>
        <input type="password" id="password" name="password" placeholder="Password" required>
    </div>
    <div>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm password" required>
    </div>
    <div>
        <button type="submit">Sign up</button>
    </div>
</form>
</body>
</html>