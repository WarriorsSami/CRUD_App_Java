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
        <nav class="navbar navbar-expand-md navbar-dark">
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
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <c:if test="${user != null}">
                    <form action="update" method="post">
                </c:if>
                <c:if test="${user == null}">
                    <form action="insert" method="post">
                </c:if>

                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user != null}">
                                Insert New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />">
                    </c:if>

                    <fieldset class="form-group">
                        <label class="form-label">User Name</label>
                        <input type="text" name="name" value="<c:out value='${user.name}' />" class="form-control" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label class="form-label">Email</label>
                        <input type="text" name="email" value="<c:out value='${user.email}' />" class="form-control">
                    </fieldset>

                    <fieldset class="form-group">
                        <label class="form-label">Country</label>
                        <input type="text" name="country" value="<c:out value='${user.country}' />" class="form-control">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>