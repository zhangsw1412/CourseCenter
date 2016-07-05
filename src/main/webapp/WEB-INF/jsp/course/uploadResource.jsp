<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>upload resource</title>
</head>
<body>
${semester}
<br/>
${course}
<br/>
${message}
    <form action="/uploadResource/${semester.id}/${course.id}" method="post" enctype="multipart/form-data">
        <input type="file" name="files"/>
        <input type="submit" />
    </form>
</body>
</html>
