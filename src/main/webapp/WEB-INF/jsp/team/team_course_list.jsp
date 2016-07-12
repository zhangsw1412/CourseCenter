<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8" />

    <title>团队课程列表</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="" name="description" />

    <meta content="" name="author" />

    <!-- BEGIN GLOBAL MANDATORY STYLES -->

    <link href="/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/style.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

    <link href="/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL STYLES -->

    <link rel="stylesheet" href="/media/css/DT_bootstrap.css" />

    <link href="/media/css/jquery.fileupload-ui.css" rel="stylesheet" />

    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="/media/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

<jsp:include page="../include/header.jsp"></jsp:include>

<!-- BEGIN CONTAINER -->

<div class="page-container row-fluid">

    <jsp:include page="../include/sidebar.jsp"></jsp:include>

    <!-- BEGIN PAGE -->

    <div class="page-content">


        <!-- BEGIN PAGE CONTAINER-->

        <div class="container-fluid">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        团队课程列表 <small>申请本团队加入团队课程</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="/index">主页</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <a href="/team/my_teams">我的团队</a>

                            <i class="icon-angle-right"></i>

                        </li>
                        
                        <li>

                            <a href="/team/team_details/${teamId}">${teamId}</a>

                            <i class="icon-angle-right"></i>

                        </li>
                        
                        <li>

                            <a href="#">加入课程</a>

                        </li>


                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN EXAMPLE TABLE PORTLET-->

                    <div class="portlet box blue">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-table"></i>课程列表 </div>

                        </div>
                        <div class="portlet-body">
                            <table class="table table-striped table-hover table-bordered">

                                <tr>

                                    <th class="span2" style="text-align:center">课程名称</th>
                                    <th class="span2" style="text-align:center">任课教师</th>
                                    <th class="span2" style="text-align:center">学时</th>
                                    <th class="span2" style="text-align:center">学分</th>
                                    <th class="span4" style="text-align:center">操作</th>

                                </tr>

                                <c:forEach items="${team_courses}" var="item">
                                    <tr>

                                        <td style="text-align:center">${item.name}</td>
                                        <td style="text-align:center">
                                            <c:forEach items="${teachersMap[item.id+0]}" var="teacherName" varStatus="status">
                                                    ${teacherName}&nbsp;
                                            </c:forEach>
                                        </td>
                                        <td style="text-align:center">${item.period}</td>
                                        <td style="text-align:center">${item.credit}</td>
                                        <td style="text-align:center">
                                            <c:if test="${courseStatusMap[item.id+0]==0}">已申请</c:if>
                                            <c:if test="${courseStatusMap[item.id+0]==1}">已加入</c:if>
                                            <c:if test="${courseStatusMap[item.id+0]==2}">已拒绝</c:if>
                                            <c:if test="${courseStatusMap[item.id+0]==3}">
                                                <form action="/team/apply_course" method="post">
                                                    <input type="hidden" name="semesterId" value="${sessionScope.semesterId}"/>
                                                    <input type="hidden" name="courseId" value="${item.id}"/>
                                                    <input type="hidden" name="teamId" value="${teamId}"/>
                                                    <input type="submit" class="btn green " value="可申请"/>
                                                </form>
                                            </c:if>
                                        </td>

                                    </tr>
                                </c:forEach>

                            </table>

                        </div>

                    </div>

                    <!-- END EXAMPLE TABLE PORTLET-->

                </div>

            </div>


            <!-- END SAMPLE TABLE PORTLET-->

        </div></div>

    <!-- END PAGE CONTENT-->

</div>

<!-- END PAGE CONTAINER-->

</div>

<!-- END PAGE -->

</div>

<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<div class="footer">

    <div class="footer-inner">

        2016 BuaaSoftware Best Group Null

    </div>

    <div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

    </div>

</div>

<!-- END FOOTER -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

<!-- BEGIN CORE PLUGINS -->

<script src="/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

<script src="/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

<script src="/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="/media/js/bootstrap.min.js" type="text/javascript"></script>

<!--[if lt IE 9]>

<script src="/media/js/excanvas.min.js"></script>

<script src="/media/js/respond.min.js"></script>

<![endif]-->

<script src="/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="/media/js/jquery.blockui.min.js" type="text/javascript"></script>

<script src="/media/js/jquery.cookie.min.js" type="text/javascript"></script>

<script src="/media/js/jquery.uniform.min.js" type="text/javascript" ></script>

<!-- END CORE PLUGINS -->

<script src="/media/js/app.js"></script>

<script>

    jQuery(document).ready(function() {

        // initiate layout and plugins

        App.init();

    });

</script>

</body>

<!-- END BODY -->

</html>