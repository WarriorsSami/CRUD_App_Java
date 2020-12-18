<%@ language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isErrorPage="true" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>

    <link rel="icon" href="favi1.jpg" type="image/gif" sizes="16x16">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>

<body>
    <div class="container text-center">
        <br><br>
        <h1 class="py-4 bg-dark text-light rounded">Error:
            <span style="color: orange;"><%= exception.getMessage () %></span></h1>
    </div>
</body>
</html>