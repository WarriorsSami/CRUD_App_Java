<%@ page contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="UTF-8">
    <title>User Management Web App</title>

    <link rel="icon" href="favi1.jpg" type="image/gif" sizes="16x16">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>

<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div>
            <a href="#" class="navbar-brand">User Management API</a>
        </div>

        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="btn btn-success" href="<%= request.getContextPath () %>/list">Users</a>
            </li>
        </ul>
    </nav>
</header>

<br>
<div class="container text-center">
    <div class="d-flex table-data">
        <table class="table table-stripped table-dark">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>Country</th>
                <th>Action</th>
            </tr>
            </thead>

            <tbody>
                <c:forEach var="user" items="${listUser}">
                    <tr>
                        <td><c:out value="${user.id}"/></td>
                        <td><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.country}"/></td>
                        <td>
                            <a href="edit?id=<c:out value='${user.id}' />">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="delete?id=<c:out value='${user.id}' />">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>