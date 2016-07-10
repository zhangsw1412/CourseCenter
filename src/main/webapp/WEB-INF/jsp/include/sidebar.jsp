<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- BEGIN SIDEBAR -->

<div class="page-sidebar nav-collapse collapse">

    <!-- BEGIN SIDEBAR MENU -->

	<c:if test="${sessionScope.user.type==0 or sessionScope.user.type==1}">
        <ul class="page-sidebar-menu">
            <div align="center" class="icon_head">
                <a href="/userInfo">
                    <img src="/assets/img/Avatar-${sessionScope.user.num%15}.jpg" alt="头像" style="border-radius:10px;width:50%;margin:5%"/>
                </a>
                <h3  style="color:white; font-family:'汉仪粗圆简'">${sessionScope.user.name}</h3>
                 <p style="color:white; font-family:'幼圆'">${sessionScope.user.id}</p>
            </div>
            <li class="active ">
                <a href="javascript:;">
                    <i class="icon-bookmark-empty"></i>
                    <span class="title">课程列表</span>
                    <span class="selected"></span>
                    <span class="arrow open"></span>
                </a>
                <ul class="sub-menu">
                    <c:forEach items="${courses}" var="c">
                        <li <c:if test="${course.id == c.id}">class="active"</c:if>>
                        <a href="/semester/${sessionScope.semesterId}/courseDetail/${c.id}">
                                ${c.name}
                        </a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
            <li class="">
                <a href="/userInfo"> <i class="icon-file"></i> <span
                        class="title">个人信息</span>
                </a>
            </li>
        </ul>
    </c:if>
    <c:if test="${sessionScope.user.type==2}">
        <ul class="page-sidebar-menu">
            <div align="center" class="icon_head">
                <a href="/userInfo">
                    <img src="/assets/img/Avatar-${sessionScope.user.num%15}.jpg" alt="头像" style="border-radius:10px;width:50%;margin:5%"/>
                </a>
                <h3  style="color:white; font-family:'汉仪粗圆简'">${sessionScope.user.name}</h3>
                <p style="color:white; font-family:'幼圆'">${sessionScope.user.id}</p>
            </div>
            <li>
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                <div class="sidebar-toggler hidden-phone"></div>
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            </li>
            <li>
                <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
                <form class="sidebar-search">
                    <div class="input-box">
                        <a href="javascript:;" class="remove"></a>
                        <input type="text" placeholder="Search..." />
                        <input type="button" class="submit" value=" " />
                    </div>
                </form>
                <!-- END RESPONSIVE QUICK SEARCH FORM -->
            </li>
            <li class=" ">
                <a href="admin_homepage.html"> <i class="icon-home"></i> <span
                        class="title">主页</span>
                </a>
            </li>
            <li class="start active ">
                <a href="/semesterList"> <i class="icon-table"></i> <span
                        class="title">学期列表</span> <span class="selected"></span>
                </a>
            </li>
            <li class="">
                <a href="/adminCourse"> <i class="icon-cogs"></i> <span
                        class="title">课程管理</span>
                </a>
            </li>
            <li class="">
                <a href="/userList"> <i class="icon-file"></i> <span
                        class="title">基本信息管理</span>
                </a>
            </li>
            <li class="">
                <a href="userInfo"> <i class="icon-file"></i> <span
                        class="title">个人信息</span>
                </a>
            </li>
        </ul>
    </c:if>
    <!-- END SIDEBAR MENU -->

</div>


<!-- END SIDEBAR -->
