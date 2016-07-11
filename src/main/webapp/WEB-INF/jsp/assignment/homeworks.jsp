<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

	<meta charset="utf-8" />

	<title>提交列表</title>

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
	
	<link rel="stylesheet" type="text/css" href="/media/css/select2_metro.css" />

	<link rel="stylesheet" href="/media/css/DT_bootstrap.css" />

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

							提交列表
								<small>选择要批改、评分的作业</small>
						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="/index">主页</a>

								<i class="icon-angle-right"></i>

							</li>
							<li>

								<a href="/semester/${sessionScope.semesterId}/courseDetail/${course.id}">${course.name}</a>
								
								<!-- 数据库获取该课程名 -->

								<i class="icon-angle-right"></i>

							</li>

							<li>

								<a href="/assignment/assignments/${assignment.semesterCourseId}">作业管理</a>

								<i class="icon-angle-right"></i>

							</li>

							<li><a href="#">提交列表</a></li>

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->          

				<div class="row-fluid">

					<div class="span12">

						<!-- BEGIN SAMPLE TABLE PORTLET-->

						<div class="portlet box blue">

							<div class="portlet-title">

								<div class="caption"><i class="icon-pencil"></i>${assignment.name}</div>

							</div>

							<div class="portlet-body">

								<table class="table table-hover table-bordered table-striped" id="sample_2">

									<thead>

										<tr>
											
											<th>学号</th>

											<th>姓名</th>

											<th class="hidden-480">提交日期</th>

											<th>已阅</th>

											<th>操作</th>

										</tr>

									</thead>

									<tbody>
										<c:forEach items="${homeworklist }" var="item">
										
										<tr>
											<td>${studentlist[item.studentId+0].id }</td>

											<td>${studentlist[item.studentId+0].name }</td>

											<td>${item.submitTime }</td>

											<td><c:if test="${item.score>=0}" ><i class="icon-check"></i></c:if>

											<td><a href="/assignment/correct/${item.id}" class="btn mini yellow"><i class="icon-pencil"></i> 批改</a></td>
										</tr>
										</c:forEach>
									</tbody>

								</table>

							</div>

						</div>

						<!-- END SAMPLE TABLE PORTLET-->

					</div>


						<!-- END BORDERED TABLE PORTLET-->

					</div>

			<!-- END PAGE CONTAINER-->

		</div>

		<!-- END PAGE -->

	</div>

	<!-- END CONTAINER -->

	<jsp:include page="../include/footer.jsp"></jsp:include>
	
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
	
	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script type="text/javascript" src="/media/js/select2.min.js"></script>

	<script type="text/javascript" src="/media/js/jquery.dataTables.min.js"></script>

	<script type="text/javascript" src="/media/js/DT_bootstrap.js"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<script src="/media/js/app.js"></script>  
    
	<script src="/media/js/table-advanced.js"></script>       

	<script>

		jQuery(document).ready(function() {       

		   // initiate layout and plugins

		   App.init();
		   
		   TableAdvanced.init();

		});

	</script>

</body>

<!-- END BODY -->

</html>