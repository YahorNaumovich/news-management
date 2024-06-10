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

        .error-message {
            text-align: center;
            color: red;
            margin-bottom: 10px;
        }

    </style>
    <script>
        function submitFormOnChange(selectElement) {
            selectElement.form.submit();
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container">
<c:if test="${not empty errorMessage}">
    <div class="error-message" id="error-message">
        <c:if test="${not empty errorMessage}">
            <div style="color: red;">
                ${errorMessage}
            </div>
        </c:if>
        <c:if test="${not (param.errorMessage eq null) }">
            <c:out value="${param.errorMessage}"/>
        </c:if>
    </div>
</c:if>
    <table>
        <thead>
            <tr>
                <th><fmt:message key="manageUsersTableHeaderId" /></th>
                <th><fmt:message key="manageUsersTableHeaderEmail" /></th>
                <th><fmt:message key="manageUsersTableHeaderUsername" /></th>
                <th><fmt:message key="manageUsersTableHeaderRole" /></th>
                <th><fmt:message key="manageUsersTableHeaderActions" /></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="entry" items="${requestScope.users}">
                <tr>
                    <td>${entry.value.id}</td>
                    <td>${entry.key}</td>
                    <td>${entry.value.name}</td>
                    <td>
                        <form action="Controller" method="post">
                            <input type="hidden" name="command" value="do_change_user_role"/>
                            <input type="hidden" name="userId" value="${entry.value.id}"/>
                            <select id="roleSelect" name="role" onchange="submitFormOnChange(this)">
                                <c:forEach var="role" items="${roles}">
                                    <option value="${role}" <c:if test="${role == entry.value.role}">selected</c:if>>${role}</option>
                                </c:forEach>
                            </select>
                        </form>
                    </td>
                    <td><a class="delete-button" href="Controller?command=do_delete_user&userId=${entry.value.id}"><fmt:message key="manageUsersTableDeleteButtonLabel" /></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
