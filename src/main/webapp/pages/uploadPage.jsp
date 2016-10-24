
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
<form method="POST" action="/upload?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
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
