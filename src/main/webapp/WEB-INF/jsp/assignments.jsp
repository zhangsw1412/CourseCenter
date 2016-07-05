<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>作业列表</title>
</head>
<body>
	<c:forEach items= "${assignmentlist }" var="item">
	作业id:${item.id }
	<br/>
	课程id：${item.semesterCourseId }
	<br/>
	作业名称：${item.name }
	<br/>
	基本要求：${item.basicRequirement }
	<br/>
	附件地址：${item.fileUrl }
	<br/>
	开始时间：${item.startTime }
	<br/>
	截止时间：${item.deadline }
	<br/>
	是否允许团队参与：${item.teamAvaliable }
	<br/>
	分数上限:${item.highestScore }
	<br/>
	</c:forEach>
</body>
</html>