<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交作业</title>
</head>
<body>
	<form action="/submithomework/${assignmentId}" method="POST">
	 文本：<input type="text" name="text" /><br/>
    <input type="submit" value="确认" />
	</form>${error}
</body>
</html>