<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8" />

    <title>申请列表</title>

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

    <link href="/media/css/jquery.fancybox.css" rel="stylesheet" />

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

                        团队申请审核

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="/index">主页</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <i class="icon-home"></i>

                            <a href="#">处理团队申请</a>

                            <i class="icon-angle-right"></i>

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

                    <div class="portlet box purple">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-edit"></i>申请列表 </div>

                        </div>

                        <div class="portlet-body">
                            <div style="padding:15px"></div>
                            <table class="table table-striped table-hover table-bordered" id="">

                                <thead>

                                <tr>

                                    <th>团队名</th>

                                    <th>组长</th>

                                    <th>团队人数</th>

                                    <th>审核</th>

                                </tr>

                                </thead>

                                <tbody>
                                <c:forEach items="${unhandledApp}" var="app">
                                    <tr class="">

                                        <td><a href="/team/team_details/${teams[app.teamId+0].id}"  style="margin-right:10px"> ${teams[app.teamId+0].name}</a></td>

                                        <td>${teams[app.teamId+0].leaderName}</td>

                                        <td>${teams[app.teamId+0].num}</td>

                                        <td>
                                            <form action="/team/permitCourseApplication" method="post">
                                                <input type="hidden" name="applicationId" value="${app.id}"/>
                                                <input type="submit" class="btn green mini" value="同意"/>
                                            </form>
                                            <form action="/team/denyCourseApplication" method="post">
                                                <input type="hidden" name="applicationId" value="${app.id}"/>
                                                <input type="submit" class="btn green mini" value="拒绝"/>
                                            </form>
                                        </td>

                                    </tr>
                                </c:forEach>
                                </tbody>

                            </table>

                        </div>

                    </div>

                    <!-- END EXAMPLE TABLE PORTLET-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN EXAMPLE TABLE PORTLET-->

                    <div class="portlet box purple">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-edit"></i>已处理申请 </div>

                        </div>

                        <div class="portlet-body">
                            <div style="padding:15px"></div>
                            <table class="table table-striped table-hover table-bordered">

                                <thead>

                                <tr>

                                    <th>团队名</th>

                                    <th>组长</th>

                                    <th>团队人数</th>

                                    <th>审核结果</th>

                                </tr>

                                </thead>

                                <tbody>

                                <c:forEach items="${handledApp}" var="app">
                                    <tr class="">

                                        <td><a href="#"  style="margin-right:10px"> ${teams[app.teamId+0].name}</a></td>

                                        <td>${teams[app.teamId+0].leaderName}</td>

                                        <td>${teams[app.teamId+0].num}</td>

                                        <td>${app.status}</td>

                                    </tr>
                                </c:forEach>

                                </tbody>

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