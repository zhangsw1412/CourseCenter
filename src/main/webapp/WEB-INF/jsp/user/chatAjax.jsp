<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${messages}" var="message" varStatus="status">
    <c:if test="${message.userId == sessionScope.user.id}">
        <li class="out">
        <img class="avatar" alt="" src="/assets/img/Avatar-${sessionScope.user.id%15}.jpg" />
    </c:if>
    <c:if test="${message.userId != sessionScope.user.id}">
        <li class="in">
        <img class="avatar" alt="" src="/assets/img/Avatar-${message.userId%15}.jpg" />
    </c:if>
    <div class="message" <c:if test="${status.last == true}">id="messageDiv"</c:if>>
        <span class="arrow"></span>
        <a href="javascript(0):;" class="name">${message.userName}</a>
        <span class="datetime">${message.createTime}</span>
        <span class="body">
            ${message.content}
        </span>
    </div>
    </li>

</c:forEach>