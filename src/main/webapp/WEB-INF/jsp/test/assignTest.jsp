<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>布置作业</title>
</head>
<body>
	<form action="/assign/${semesterCourseId}" method="POST">
	  作业名称：<input type="text" name="name" /><br/>
    基本要求：<input type="text" name="basicrequirement" /><br/>
    开始时间：<input type="text" name="starttime" /><br/>
    截止时间：<input type="text" name="deadline" /><br/>
    是否允许团队参与<input type="text" name="teamavaliable" /><br/>
    分数上限<input type="text" name="highestscore" /><br/>
    <input type="submit" value="确认" />
	</form>${error}
</body>
</html>