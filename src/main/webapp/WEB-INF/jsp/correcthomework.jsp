<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批改作业</title>
</head>
<body>
	<form action="/correcthomework/${homeworkId}" method="POST">
	分数：<input type="text" name="score" /><br/>
	评论：<input type="text" name="comment" /><br/>
    <input type="submit" value="确认" />
	</form>${error}
</body>
</html>