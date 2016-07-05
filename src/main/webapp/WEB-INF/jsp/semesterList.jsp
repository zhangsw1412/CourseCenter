<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学期列表</title>
</head>
<body>
	<c:if test="${empty requestScope.semesterList }">
		暂无学期信息
	</c:if>
	<c:if test="${!empty requestScope.semesterList }">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>学年</th>
				<th>季度</th>
				<th>学期开始时间</th>
				<th>学期结束时间</th>
				<th>周数</th>
				<th colspan="2">操作</th>
			</tr>
			<c:forEach items="${requestScope.semesterList }" var="semester">
				<tr>
					<td>${semester.schoolYear }-${semester.schoolYear+1 }</td>
					<td>${semester.season }</td>
					<td><fmt:formatDate value="${semester.startDate }" type="date"/></td>
					<td><fmt:formatDate value="${semester.endDate }" type="date"/></td>
					<td>${semester.weeks }</td>
					<td><a href="/updateSemester/${semester.id }">修改</a></td>
					<td><a href="/deleteSemester/${semester.id }">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<a href="/addSemester">添加学期</a>
</body>
</html>