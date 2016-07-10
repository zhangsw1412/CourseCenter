<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

							添加作业

							 <small>输入作业详细信息</small>

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="/index">主页</a> 

								<span class="icon-angle-right"></span>

							</li>
							<li>

								<a href="/semester/${semesterId}/courseDetail/${course.id}">${course.name}</a>

								<!-- 数据库获取该课程名 -->

								<i class="icon-angle-right"></i>

							</li>
							<li>

								<a href="/assignment/assignments/${semesterCourseId }">作业管理</a>

								<span class="icon-angle-right"></span>

							</li>

							<li><a href="#">添加作业</a></li>

						</ul>

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="row-fluid">
					
					<div class="span12">
						<div style="color:red">${error}</div>
						<div class="portlet box green" id="form_wizard_1">

							<div class="portlet-title">

								<div class="caption">

									<i class="icon-plus"></i> 添加作业 - <span class="step-title">Step 1 of 3</span>

								</div>

							</div>

							<div class="portlet-body form">

								<form action="/assignment/assign/${semesterCourseId}" class="form-horizontal" id="submit_form" method="POST" enctype="multipart/form-data">

									<div class="form-wizard">

										<div class="navbar steps">

											<div class="navbar-inner">

												<ul class="row-fluid">

													<li class="span4">

														<a href="#tab1" data-toggle="tab" class="step">

														<span class="number">1</span>

														<span class="desc"><i class="icon-ok"></i> 起止时间</span>   

														</a>

													</li>

													<li class="span4">

														<a href="#tab2" data-toggle="tab" class="step">

														<span class="number">2</span>

														<span class="desc"><i class="icon-ok"></i> 详细要求</span>   

														</a>

													</li>

													<li class="span4">

														<a href="#tab3" data-toggle="tab" class="step">

														<span class="number">3</span>

														<span class="desc"><i class="icon-ok"></i> 确认信息</span>   

														</a> 

													</li>

												</ul>

											</div>

										</div>

										<div id="bar" class="progress progress-success progress-striped">

											<div class="bar"></div>

										</div>

										<div class="tab-content">

											<div class="alert alert-error hide">

												<button class="close" data-dismiss="alert"></button>

												You have some form errors. Please check below.

											</div>

											<div class="alert alert-success hide">

												<button class="close" data-dismiss="alert"></button>

												Your form validation is successful!

											</div>
											
											<!-- step1 -->
											<div class="tab-pane" id="tab1">

												<h3 class="block">请设置作业时间区域：</h3>

												<div class="control-group">

													<label class="control-label">起止时间</label>

													<div class="controls">

														<input name="starttime" readonly="readonly" class="m-wrap small" size="16" type="text" value="" id="ui_date_picker_range_from"/>

														<span class="text-inline">&nbsp;至&nbsp;</span>

														<input name="deadline" readonly="readonly" class="m-wrap small" size="16" type="text" value="" id="ui_date_picker_range_to"/>

													</div>
													
												</div>

											</div>

											<!-- step2 -->
											<div class="tab-pane" id="tab2">

												<h3 class="block">请填写作业要求与详情：</h3>

												<div class="control-group">

													<label class="control-label">作业名称</label>

													<div class="controls">

														<input type="text" class="span6 m-wrap" name="name" />

														<span class="help-inline"></span>

													</div>

												</div>

												<div class="control-group">

													<label class="control-label">分数上限</label>

													<div class="controls">

														<input type="text" name="highestscore" />

														<span class="help-inline"></span>
														请输入两位以内的正整数
													</div>
													
												</div>
												
												<div class="control-group">

													<label class="control-label">团队参与</label>

													<div class="controls">

														<input type="checkbox" name="teamavaliable" />

														<span class="help-inline"></span>

													</div>

												</div>
												
												<div class="control-group">

													<label class="control-label">基本要求</label>

													<div class="controls">

														<textarea class="span6 m-wrap" rows="3"	name="basicrequirement"></textarea>

														<span class="help-inline"></span>

													</div>

												</div>
												
												<div class="control-group">

													<label class="control-label">上传附件(如有)</label>

														<div class="control-group">
														
															<div class="controls">

														<input type="file" name="files" class="fileupload"/>

															</div>
														</div>
												</div>
											</div>

											<!-- step3 -->
											<div class="tab-pane" id="tab3">

												<h3 class="block">请确认：</h3>

												<h4 class="form-section">详细信息</h4>

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

														<span class="text display-value" data-display="name"></span>

													</div>

												</div>

												<div class="control-group">

													<label class="control-label">分数上限：</label>

													<div class="controls">

														<span class="text display-value" data-display="highestscore"></span>
														

													</div>

												</div>

												<div class="control-group">

													<label class="control-label">团队参与：</label>

													<div class="controls">

														<span class="text display-value" data-display="teamavaliable">
														</span>

													</div>

												</div>

												<div class="control-group">

													<label class="control-label">起止日期：</label>

													<div class="controls">
													
														<span class="text display-value" data-display="starttime"></span>
														&nbsp;&nbsp;至
														<span class="text display-value" data-display="deadline"></span>

													</div>

												</div>

												<div class="control-group">

													<label class="control-label">基本要求:</label>

													<div class="controls">

														<span class="text display-value" data-display="basicrequirement"></span>

													</div>

												</div>
												
												<div class="control-group">

													<label class="control-label">附件名:</label>

													<div class="controls">

														<span class="text display-value" data-display="files"></span>

													</div>

												</div>

											</div>

										</div>

										<div class="form-actions clearfix">

											<a href="javascript:;" class="btn button-previous">

											<i class="m-icon-swapleft"></i> 上一步 

											</a>

											<a href="javascript:;" class="btn green button-next">

											下一步 <i class="m-icon-swapright m-icon-white"></i>

											</a>

											<a href="javascript:;" class="btn green button-submit" onclick="document:submit_form.submit();">

											提交 <i class="m-icon-swapright m-icon-white"></i>

											</a>

										</div>

									</div>

								</form>

							</div>

						</div>

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