<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
login
<form action="/login" method="POST">
    <input type="text" name="userId" />
    <input type="text" name="password" />
    <input type="submit" value="log in" />
</form>
${error}
</body>
</html>
