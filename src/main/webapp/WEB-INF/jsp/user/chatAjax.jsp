<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${messages}" var="message">
    <c:if test="${message.userId == sessionScope.user.id}">
        <li class="out">
        <img class="avatar" alt="" src="/media/image/avatar2.jpg" />
    </c:if>
    <c:if test="${message.userId != sessionScope.user.id}">
        <li class="in">
        <img class="avatar" alt="" src="/media/image/avatar1.jpg" />
    </c:if>
    <div class="message">
        <span class="arrow"></span>
        <a href="#">${message.userName}</a>
        <span>${message.createTime}</span>
        <span class="body">
            ${message.content}
        </span>
    </div>
    </li>
</c:forEach>