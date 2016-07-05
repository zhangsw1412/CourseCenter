<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath }/saveSemester" method="post"
		modelAttribute="semester">
		Year:<form:input path="schoolYear" />
		<br />
		Season:<form:input path="season" />
		<br />
		Start<form:input path="startDate" />
		<br />
		End<form:input path="endDate" />
		<br />
		Weeks<form:input path="weeks" />
		<br />
		<input type="submit" value="提交">
	</form:form>
</body>
</html>