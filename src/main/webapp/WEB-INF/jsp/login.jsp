<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1"/>
    <title>Войти</title>
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

        input[type="checkbox"] {
            margin-right: 5px;
            margin-bottom: 15px;
            cursor: pointer;
        }

        label {
            font-size: 14px;
            color: #666;
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

        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
        }

        .error-message {
            text-align: center;
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<h1>Log in, please</h1>
<div class="error-message" id="error-message">
    <c:if test="${not (param.authError eq null) }">
        <c:out value="${param.authError}"/>
    </c:if>
</div>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="DO_AUTH"/>
    <div>
        <input
                type="text"
                id="login"
                name="login"
                placeholder="Email address"
                required
        />
    </div>
    <div>
        <input
                type="password"
                id="password"
                name="password"
                placeholder="Password"
                required
        />
    </div>
    <div>
        <input type="checkbox" id="checkbox" name="checkbox"/>
        <label for="checkbox">Remember me</label>
    </div>
    <div>
        <button type="submit">Sign in</button>
    </div>
</form>
<a href="Controller?command=GO_TO_REGISTRATION_PAGE"
>Register new account</a
>
</body>
</html>
