<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8"/>

    <title>课程详情</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="" name="description"/>

    <meta content="" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->

    <link href="/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/style2.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

    <link href="/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

    <!-- END GLOBAL MANDATORY STYLES -->

    <link rel="shortcut icon" href="/media/image/favicon.ico"/>

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

        <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <div id="portlet-config" class="modal hide">

            <div class="modal-header">

                <button data-dismiss="modal" class="close" type="button"></button>

                <h3>portlet Settings</h3>

            </div>

            <div class="modal-body">

                <p>Here will be a configuration form</p>

            </div>

        </div>

        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <!-- BEGIN PAGE CONTAINER-->

        <div class="container-fluid">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN STYLE CUSTOMIZER -->

                    <div class="color-panel hidden-phone">

                        <div class="color-mode-icons icon-color"></div>

                        <div class="color-mode-icons icon-color-close"></div>

                        <div class="color-mode">

                            <p>THEME COLOR</p>

                            <ul class="inline">

                                <li class="color-black current color-default" data-style="default"></li>

                                <li class="color-blue" data-style="blue"></li>

                                <li class="color-brown" data-style="brown"></li>

                                <li class="color-purple" data-style="purple"></li>

                                <li class="color-grey" data-style="grey"></li>

                                <li class="color-white color-light" data-style="light"></li>

                            </ul>

                            <label>

                                <span>Layout</span>

                                <select class="layout-option m-wrap small">

                                    <option value="fluid" selected>Fluid</option>

                                    <option value="boxed">Boxed</option>

                                </select>

                            </label>

                            <label>

                                <span>Header</span>

                                <select class="header-option m-wrap small">

                                    <option value="fixed" selected>Fixed</option>

                                    <option value="default">Default</option>

                                </select>

                            </label>

                            <label>

                                <span>Sidebar</span>

                                <select class="sidebar-option m-wrap small">

                                    <option value="fixed">Fixed</option>

                                    <option value="default" selected>Default</option>

                                </select>

                            </label>

                            <label>

                                <span>Footer</span>

                                <select class="footer-option m-wrap small">

                                    <option value="fixed">Fixed</option>

                                    <option value="default" selected>Default</option>

                                </select>

                            </label>

                        </div>

                    </div>

                    <!-- END BEGIN STYLE CUSTOMIZER -->

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">



                    </h3>



                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <!-- BEGIN PAGE CONTENT-->

            <div class="tiles">
                    <div class="tile double bg-blue">
                        <div class="corner"></div>
                        <div class="check"></div>
                        <div class="tile-body">
                            <div style="margin:20px"></div>
                            <h3 style=" font-weight:bolder">${course.name}<small>(代码：${course.courseCode})</small></h3>
                            <div style="margin:20px"></div>
                            <h5>
                                <c:if test="${course.type==true}">必修课</c:if>
                                <c:if test="${course.type==false}">选修课</c:if>
                            </h5>
                            <div style="margin:20px"></div>
                            <h5>任课教师：
                                <c:forEach items="${teachers}" var="name">
                                    ${name}&nbsp;
                                </c:forEach>
                            </h5>
                            <div style="margin:20px"></div>
                            <h5>学时：${course.period}&nbsp;学分：${course.credit}&nbsp;上课人数：${countStudent}</h5>
                            <div style="margin:20px"></div>
                            <h5>
                                <c:if test="${course.teamAvaliable==true}">允许团队参与</c:if>
                            </h5>
                        </div>
                    </div>

                    <a href="/assignment/assignments/${semesterCourseId}">
                        <div class="tile double-down bg-green">
                            <div class="tile-body">
                                <i class="icon-calendar"></i>
                            </div>
                            <div class="tile-object">
                                <div class="name" style="font-size:large">
                                    作业
                                </div>
                            </div>
                        </div>
                    </a>

                    <a href="/semester/${semesterId}/course/${course.id}/resourceList">
                        <div class="tile double-down bg-purple">
                            <div class="tile-body">
                                <i class="icon-paste"></i>
                            </div>
                            <div class="tile-object">
                                <div class="name" style="font-size:large">
                                    资源
                                </div>
                            </div>
                        </div>
                    </a>
                    <a href="/semester/${semesterId}/course/${course.id}/chat">
                        <div class="tile double-down2 bg-yellow">
                            <div class="tile-body">
                                <i class="icon-comments-alt" style=" padding-top:75px"></i>
                            </div>
                            <div class="tile-object">
                                <div class="name" style="font-size:large">
                                    讨论
                                </div>
                            </div>
                        </div>
                    </a>
                <br>

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

            2013 &copy; Metronic by keenthemes.

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

    <script src="/media/js/jquery.uniform.min.js" type="text/javascript"></script>

    <!-- END CORE PLUGINS -->

    <script src="/media/js/app.js"></script>

    <script>

        jQuery(document).ready(function () {

            // initiate layout and plugins

            App.init();

        });

    </script>

    <!-- END JAVASCRIPTS -->


</body>

<!-- END BODY -->

</html>
