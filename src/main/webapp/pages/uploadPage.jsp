
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<form method="POST" action="/upload" enctype="multipart/form-data">
    <table>
    <tr>
    <td><label path="file">Select a file to upload</label></td>
    <td><input type="file" name="file" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
    </tr>
    <tr>
    <td><input type="submit" value="Submit" /></td>
    </tr>
    </table>
    </form>
</body>
</html>
