<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

<meta charset="utf-8" />

<title>添加新学期</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<meta content="" name="description" />

<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="/media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<link href="/media/css/bootstrap-responsive.min.css" rel="stylesheet"
	type="text/css" />

<link href="/media/css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />

<link href="/media/css/style-metro.css" rel="stylesheet" type="text/css" />

<link href="/media/css/style.css" rel="stylesheet" type="text/css" />

<link href="/media/css/style-responsive.css" rel="stylesheet"
	type="text/css" />

<link href="/media/css/default.css" rel="stylesheet" type="text/css"
	id="style_color" />

<link href="/media/css/uniform.default.css" rel="stylesheet"
	type="text/css" />

<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<link rel="stylesheet" type="text/css"
	href="/media/css/select2_metro.css" />

<link rel="stylesheet" type="text/css" href="/media/css/chosen.css" />

<link rel="stylesheet" type="text/css"
	href="/media/css/jquery-ui-1.10.1.custom.min.css" />

<!-- END PAGE LEVEL STYLES -->

<link rel="shortcut icon" href="/media/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

	<jsp:include page="../include/header.jsp"></jsp:include>

	<!-- BEGIN CONTAINER -->

	<div class="page-container row-fluid">

		<jsp:include page="../include/sidebar_admin.jsp"></jsp:include>

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

						<h3 class="page-title">

							添加学期 <small>编辑新学期信息</small>

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i> <a href="teacher_homepage.html">主页</a>

								<span class="icon-angle-right"></span>

							</li>

							<li>

								<a href="teacher_homework.html">学期管理</a> <span
									class="icon-angle-right"></span>

							</li>

							<li>
								<a href="teacher_homework_edit.html">添加学期</a>
							</li>

						</ul>

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->


				<div class="row-fluid">

					<div class="span12">

						<!-- BEGIN VALIDATION STATES-->

						<div class="portlet box blue">

							<div class="portlet-title">

								<div class="caption">
									<i class="icon-edit"></i>添加学期
								</div>

								<div class="tools">

									<a href="javascript:;" class="collapse"></a> <a
										href="#portlet-config" data-toggle="modal" class="config"></a>

									<a href="javascript:;" class="reload"></a> <a
										href="javascript:;" class="remove"></a>

								</div>

							</div>

							<div class="portlet-body form">

								<!-- BEGIN FORM-->

								<form action="/saveSemester" method="post" id="form_sample_1"
									class="form-horizontal">

									<div class="alert alert-error hide">

										<button class="close" data-dismiss="alert"></button>

										You have some form errors. Please check below.

									</div>

									<div class="alert alert-success hide">

										<button class="close" data-dismiss="alert"></button>

										Your form validation is successful!

									</div>

									<div class="control-group">

										<label class="control-label">学年：</label>

										<div class="controls">

											<input type="text" name="schoolYear"
												value="${semester.schoolYear }" class="span6 m-wrap" />

										</div>

									</div>

									<div class="control-group">

										<label class="control-label">季度</label>

										<div class="controls">

											<select name="season" class="large m-wrap" tabindex="17">

												<option value="0">春</option>
												<option value="1">秋</option>
												<option value="2">夏</option>


											</select>

										</div>

									</div>

									<div class="control-group">

										<label class="control-label">起止日期</label>

										<div class="controls">

											<input name="startDate" class="m-wrap small" size="16"
												type="text"
												value="<fmt:formatDate value="${semester.startDate }"
														type="date" />"
												id="ui_date_picker_range_from" />

											<span class="text-inline">&nbsp;至&nbsp;</span>

											<input name="endDate" class="m-wrap small" size="16"
												type="text"
												value="<fmt:formatDate value="${semester.startDate }"
														type="date" />"
												id="ui_date_picker_range_to" />

										</div>

									</div>

									<div class="control-group">

										<label class="control-label">周数</label>

										<div class="controls">

											<input type="text" placeholder="" name="weeks"
												class="m-wrap large" value="${semester.weeks }" />

											<span class="help-inline"></span>

										</div>

									</div>

									<div class="form-actions">

										<input type="submit" value="确认" class="btn blue" />

										<a href="teacher_homework.html"><button type="button"
												class="btn">取消</button> <a>
									</div>

								</form>

								<!-- END FORM-->

							</div>

						</div>

						<!-- END VALIDATION STATES-->

					</div>

				</div>

				<!-- END PAGE CONTENT-->

			</div>

			<!-- END PAGE CONTAINER-->

		</div>

		<!-- END PAGE -->

	</div>

	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->

	<div class="footer">

		<div class="footer-inner">2013 &copy; Metronic by keenthemes.</div>

		<div class="footer-tools">

			<span class="go-top"> <i class="icon-angle-up"></i>

			</span>

		</div>

	</div>

	<!-- END FOOTER -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="/media/js/jquery-migrate-1.2.1.min.js"
		type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="/media/js/jquery-ui-1.10.1.custom.min.js"
		type="text/javascript"></script>

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

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script type="text/javascript" src="/media/js/jquery.validate.min.js"></script>

	<script type="text/javascript"
		src="/media/js/additional-methods.min.js"></script>

	<script type="text/javascript" src="/media/js/select2.min.js"></script>

	<script type="text/javascript" src="/media/js/chosen.jquery.min.js"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<script src="/media/js/app.js"></script>

	<script src="/media/js/form-validation.js"></script>

	<script src="/media/js/ui-jqueryui.js"></script>

	<!-- END PAGE LEVEL STYLES -->

	<script>
		jQuery(document).ready(function() {

			// initiate layout and plugins

			App.init();

			FormValidation.init();

			UIJQueryUI.init();

		});
	</script>

	<!-- END JAVASCRIPTS -->

	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-37564768-1' ]);
		_gaq.push([ '_setDomainName', 'keenthemes.com' ]);
		_gaq.push([ '_setAllowLinker', true ]);
		_gaq.push([ '_trackPageview' ]);
		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://'
					: 'http://')
					+ 'stats.g.doubleclick.net/dc.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>
</body>

<!-- END BODY -->

</html>