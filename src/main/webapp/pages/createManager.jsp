<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create manager</title>
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div style="width: 300px; margin: auto; padding-top: 200px">
    <c:if test="${param.error != null}">
        <div class="alert alert-danger" role="alert">
            Username or password is incorrect!
        </div>
    </c:if>
    <c:if test="${param.ok != null}">
        <div class="alert alert-success" role="alert">
            New manager is created!
        </div>
    </c:if>
    <form method="post" action="/admin/createManager">
        <div class="input-group">
            <span class="input-group-addon"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></span>
            <input id="usernameInput" name="username" type="username" class="form-control" placeholder="Username" aria-describedby="basic-addon1" required>
        </div>
        <p></p>
        <div class="input-group">
            <span class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span>
            <input id="passwordInput" name="password" type="password" class="form-control" placeholder="Password" aria-describedby="basic-addon1" required>
        </div>
        <p></p>
        <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
        <div class="form-actions">
            <input type="submit" class="btn btn-block btn-primary" value="Create manager">
        </div>
    </form>
</div>
</body>
</html>
