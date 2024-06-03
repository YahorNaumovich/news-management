<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Table</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            width: 1000px;
            margin: 30px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .delete-button {
              color: #fff; /* White text */
              text-decoration: none;
              padding: 10px 20px;
              border-radius: 5px;
              background-color: red;
              display: inline-block;
              margin: 10px 10px;
        }

        .delete-button:hover {
            background-color: orangered;
            cursor: pointer;
        }

    </style>
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container">
    <h2>User Information</h2>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Email</th>
                <th>Username</th>
                <th>User Role</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="entry" items="${requestScope.users}">
                <tr>
                    <td>${entry.value.id}</td>
                    <td>${entry.key}</td>
                    <td>${entry.value.name}</td>
                    <td>${entry.value.role}</td>
                    <td><a class="delete-button" href="Controller?command=do_delete_user&userId=${entry.value.id}">Delete user</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
