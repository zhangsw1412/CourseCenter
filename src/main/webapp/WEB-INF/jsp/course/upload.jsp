<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

	<meta charset="utf-8" />

	<title>添加资源</title>

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
    	<link rel="stylesheet" type="text/css" href="/media/css/bootstrap-fileupload.css" />

	<link rel="stylesheet" type="text/css" href="/media/css/jquery.gritter.css" />

	<link rel="stylesheet" type="text/css" href="/media/css/chosen.css" />

	<link rel="stylesheet" type="text/css" href="/media/css/select2_metro.css" />

	<link rel="stylesheet" type="text/css" href="/media/css/jquery.tagsinput.css" />

	<link rel="stylesheet" type="text/css" href="/media/css/clockface.css" />

	<link rel="stylesheet" type="text/css" href="/media/css/bootstrap-wysihtml5.css" />

	<link rel="stylesheet" type="text/css" href="/media/css/datepicker.css" />

	<link rel="stylesheet" type="text/css" href="/media/css/timepicker.css" />

	<link rel="stylesheet" type="text/css" href="/media/css/colorpicker.css" />

	<link rel="stylesheet" type="text/css" href="/media/css/bootstrap-toggle-buttons.css" />

	<link rel="stylesheet" type="text/css" href="/media/css/daterangepicker.css" />

	<link rel="stylesheet" type="text/css" href="/media/css/datetimepicker.css" />

	<link rel="stylesheet" type="text/css" href="/media/css/multi-select-metro.css" />

	<link href="/media/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/>

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

						<!-- BEGIN PAGE TITLE & BREADCRUMB-->

						<h3 class="page-title">

							资源管理 

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="teacher_homepage.html">主页</a> 

								<i class="icon-angle-right"></i>

							</li>

							<li>

								<a href="teacher_resources.html">资源管理</a>

								<i class="icon-angle-right"></i>

							</li>

							<li>

								<a href="teacher_sourceuploading.html">添加资源</a>

							</li>
						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT--> 
 
 									<div class="row-fluid">

					<div class="span12">

						<!-- BEGIN EXTRAS PORTLET-->

						<div class="portlet box blue">

							<div class="portlet-title">

								<div class="caption"><i class="icon-reorder"></i>资源上传</div>
							</div>

							<div class="portlet-body form">

								<!-- BEGIN FORM-->

								<form action="/uploadResource/semester/${semester.id}/course/${course.id}" class="form-horizontal" method="post" enctype="multipart/form-data">


									<div class="control-group">
										<label class="control-label">资源名称</label>
										<div class="controls">
											<input type="file" name="files"  class="fileupload" />
											<br/>
											<span>${message}</span>
										</div>
									</div>


									<div class="control-group">

										<label class="control-label">备注</label>

										<div class="controls">

											<textarea class="span12 wysihtml5 m-wrap" rows="6"></textarea>

										</div>

									</div>

									<div class="form-actions">

										<button type="submit" class="btn blue">上传</button>

										<a href="teacher_resources.html"><button type="button" class="btn">取消</button><a>

									</div>

								</form>

								<!-- END FORM-->

							</div>

						</div>

						<!-- END EXTRAS PORTLET-->

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

	<script src="/media/js/jquery.uniform.min.js" type="text/javascript" ></script>

	<!-- END CORE PLUGINS -->

	<script src="/media/js/app.js"></script>
	<script>

		jQuery(document).ready(function() {
			App.init();
		});

	</script>

<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>

<!-- END BODY -->

</html>
