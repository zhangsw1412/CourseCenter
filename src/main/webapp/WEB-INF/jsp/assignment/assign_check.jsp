<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fileUtil.tld"%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

	<meta charset="utf-8" />

	<title>作业管理</title>

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

	<link rel="stylesheet" type="text/css" href="/media/css/chosen.css" />
	
	<link rel="stylesheet" type="text/css" href="/media/css/jquery-ui-1.10.1.custom.min.css"/>

	<link rel="stylesheet" type="text/css" href="/media/css/bootstrap-toggle-buttons.css" />
	<link rel="stylesheet" type="text/css" href="/media/css/bootstrap-datetimepicker.min.css" />

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="/media/image/favicon.ico" />
	<style>
		.readonlyinput:hover{ 
			cursor:pointer;  
		}
	</style>

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

	<jsp:include page="../include/header.jsp"></jsp:include>

	<!-- BEGIN CONTAINER -->

	<div class="page-container row-fluid">

		<!-- BEGIN PAGE -->  
		
        <div class="page-sidebar nav-collapse collapse">

			<jsp:include page="../include/sidebar.jsp"></jsp:include>

		</div>

        
		<div class="page-content">

			<!-- BEGIN PAGE CONTAINER-->

			<div class="container-fluid">

				<!-- BEGIN PAGE HEADER-->   

				<div class="row-fluid">

					<div class="span12">

						<h3 class="page-title">

							作业详情

							 <small>确认作业详细信息</small>

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="/index">主页</a> 

								<span class="icon-angle-right"></span>

							</li>
							<li>

								<a href="/semester/${sessionScope.semesterId}/courseDetail/${course.id}">${course.name}</a>

								<!-- 数据库获取该课程名 -->

								<i class="icon-angle-right"></i>

							</li>
							<li>

								<a href="/assignment/assignments/${semesterCourseId }">作业管理</a>

								<span class="icon-angle-right"></span>

							</li>

							<li><a href="#">作业详情</a></li>

						</ul>

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="row-fluid">
					
					<div class="span12">
					<div style="color:red">${error}</div>						<div class="portlet box green" id="form_wizard_1">

							<div class="portlet-body form">

								<form action="/assignment/assign/${semesterCourseId}" class="form-horizontal" id="submit_form" method="POST" enctype="multipart/form-data">

									<div class="form-wizard">

										<div class="tab-content">

												<h2 class="form-section">详细信息</h2>

												<div class="control-group">

													<label class="control-label">课程名：</label>

													<div class="controls">

														<span class="text display-value">${course.name}</span>
														<!-- 获取课程名 course -->

													</div>

												</div>

												<div class="control-group">

													<label class="control-label">作业名称：</label>

													<div class="controls">

														<span class="text display-value">${assignment.name}</span>

													</div>

												</div>
												<c:if test="${assignment.highestScore>0}">
												<div class="control-group">

													<label class="control-label">分数上限：</label>

													<div class="controls">

														<span class="text display-value">${assignment.highestScore}</span>
														

													</div>

												</div>
												</c:if>
												<div class="control-group">

													<label class="control-label">团队参与：</label>

													<div class="controls">

														<span class="text display-value">
														<c:if test="${assignment.teamAvaliable==true}">允许</c:if>
														<c:if test="${assignment.teamAvaliable==false}">不允许</c:if>
														</span>

													</div>

												</div>

												<div class="control-group">

													<label class="control-label">开始时间：</label>

													<div class="controls">
													
														<span class="text display-value">${assignment.startTime}</span>

													</div>

												</div>
												<div class="control-group">

													<label class="control-label">截止时间：</label>

													<div class="controls">
													
														<span class="text display-value">${assignment.deadline}</span>

													</div>

												</div>
												<div class="control-group">

													<label class="control-label">基本要求：</label>

													<div class="controls">

														<span class="text display-value">${assignment.basicRequirement}</span>

													</div>

												</div>
												<c:if test="${assignment.fileUrl!=null}">
												<div class="control-group">

													<label class="control-label">附件：</label>

													<div class="controls">

														<a href="${assignment.fileUrl}" class="btn green fileinput-button">
                                            				<i class="icon-download"></i>
                                            				<span>${fn:getFileName(assignment.fileUrl)}</span></a>

													</div>
												
												</div>
												
												</c:if>
										</div>

									</div>

								</form>
								
							</div>
								
						</div>
						
						<div align="center"><a href="/assignment/assignments/${semesterCourseId}" class="btn">返回</a></div>
						
					</div>

				</div>

				<!-- END PAGE CONTENT-->         

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

	<script type="text/javascript" src="/media/js/jquery.validate.min.js"></script>

	<script type="text/javascript" src="/media/js/additional-methods.min.js"></script>

	<script type="text/javascript" src="/media/js/jquery.bootstrap.wizard.min.js"></script>

	<script type="text/javascript" src="/media/js/chosen.jquery.min.js"></script>

	<script type="text/javascript" src="/media/js/select2.min.js"></script>

	<script type="text/javascript" src="/media/js/jquery.tagsinput.min.js"></script>

	<script type="text/javascript" src="/media/js/jquery.toggle.buttons.js"></script>
	
	<script type="text/javascript" src="/media/js/bootstrap-datetimepicker.min.js"></script>
	
	<script type="text/javascript" src="/media/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="/media/js/app.js"></script>

	<script src="/media/js/form-wizard.js"></script>    
	
	<!-- END PAGE LEVEL SCRIPTS -->

	<script>

		jQuery(document).ready(function() {       

		   // initiate layout and plugins

		   App.init();

		   FormWizard.init();
			$('.basic-toggle-button').toggleButtons({
				style: {
					enabled: "success",
					disabled: ""
				}
			});
		   $('#ui_date_picker_range_from').datetimepicker({
		        language:  'zh-CN',
				format: 'yyyy-mm-dd hh:ii',
		        weekStart: 1,
		        todayBtn:  1,
						autoclose: 1,
						todayHighlight: 1,
						startView: 2,
						forceParse: 0,
		        showMeridian: 1
		    	});

			$('#ui_date_picker_range_to').datetimepicker({
		        language:  'zh-CN',
				format: 'yyyy-mm-dd hh:ii',
		        weekStart: 1,
		        todayBtn:  1,
						autoclose: 1,
						todayHighlight: 1,
						startView: 2,
						forceParse: 0,
		        showMeridian: 1
		    	});
		});

	</script>
	<!-- END JAVASCRIPTS -->   

<!-- END BODY -->

</html>