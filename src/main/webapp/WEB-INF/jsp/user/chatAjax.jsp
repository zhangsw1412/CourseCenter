<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${messages}" var="message" varStatus="status">
    <c:if test="${message.userNum == sessionScope.user.num}">
        <li class="out" time="${message.createTime}" <c:if test="${status.last == true}">id="lastMessage"</c:if>>
        <img class="avatar" alt="" src="/assets/img/Avatar-${sessionScope.user.num%26}.jpg" />
    </c:if>
    <c:if test="${message.userNum != sessionScope.user.num}">
        <li class="in" time="${message.createTime}">
        <img class="avatar" alt="" src="/assets/img/Avatar-${message.userNum%26}.jpg" />
    </c:if>
    <div class="message">
        <span class="arrow"></span>
        <a href="javascript(0):;" class="name">${message.userName}</a>
        <span class="datetime">${message.createTime}</span>
        <span class="body">
            ${message.content}
        </span>
    </div>
    </li>
</c:forEach>