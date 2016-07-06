<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- BEGIN SIDEBAR -->

<div class="page-sidebar nav-collapse collapse">

    <!-- BEGIN SIDEBAR MENU -->

    <ul class="page-sidebar-menu">


        <li>
            <div style="margin:50px"></div>
        </li>

        <li class="active ">

            <a href="javascript:;">

                <i class="icon-bookmark-empty"></i>

                <span class="title">课程列表</span>


                <span class="selected"></span>

                <span class="arrow open"></span>

            </a>

            <ul class="sub-menu">
                <c:forEach items="${courses}" var="course">
                    <li >

                        <a href="/semester/${semester.id}/courseDetail/${course.id}">

                            ${course.name}</a>

                    </li>
                </c:forEach>
            </ul>

        </li>



    </ul>

    <!-- END SIDEBAR MENU -->

</div>


<!-- END SIDEBAR -->
