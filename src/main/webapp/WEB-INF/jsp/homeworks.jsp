<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>作业列表</title>
</head>
<body>
	<c:forEach items= "${homeworklist }" var="item">
	提交作业id:${item.id }
	<br/>
	课程id：${item.semesterCourseId }
	<br/>
	学生id：：${item.studentId }
	<br/>
	作业id：${item.assignmentId }
	<br/>
	文本：${item.text }
	<br/>
	附件地址：${item.fileUrl }
	<br/>
	分数：${item.score }
	<br/>
	评论：${item.comment }
	<br/>
	提交时间：${item.submitTime}
	<br/>
	</c:forEach>
</body>
</html>