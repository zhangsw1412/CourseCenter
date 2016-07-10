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

	<title>提交作业</title>

	<meta content="width=device-width, initial-scale=1.0" name="viewport" />

	<meta content="" name="description" />

	<meta content="" name="author" />

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

	<link rel="shortcut icon" href="/media/image/favicon.ico" />
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

							作业管理 <small>学生提交课程作业</small>

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="/index">主页</a>

								<i class="icon-angle-right"></i>

							</li>

							<li>

								<a href="/semester/${semesterId}/courseDetail/${course.id}">${course.name}</a>								
								<!-- 数据库获取该课程名 -->

								<i class="icon-angle-right"></i>

							</li>
							
							<li>

								<a href="/assignment/assignments/${assignment.semesterCourseId}">作业管理</a>

								<i class="icon-angle-right"></i>

							</li>

							<li><a href="#">作业提交</a></li>

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

									<div class="tab-pane " id="tab_3">

									<div class="portlet box green">

										<div class="portlet-title">

											<div class="caption"><i class="icon-reorder"></i>作业详情</div>

										</div>
										<form action="/assignment/submit/${assignmentId}" method="POST" enctype="multipart/form-data">
										<div class="portlet-body form">

											<!-- BEGIN FORM-->

											<div class="form-horizontal form-view">

												<h3> ${course.name }: 作业</h3>

												<h3 class="form-section"></h3>

												<div class="row-fluid">

													<div class="span12 ">

														<div class="control-group">

															<label class="control-label" style=" font-weight:bolder">作业名称</label>

															<div class="controls">

																<span class="text">${assignment.name}</span>

															</div>

														</div>

													</div>

													

												</div>

								

												<div class="row-fluid">

													<div class="span12 ">

														<div class="control-group">

															<label class="control-label" style=" font-weight:bolder">截止时间</label>

															<div class="controls">

																<span class="text">${assignment.deadline }</span> 

															</div>

														</div>

													</div>

													<!--/span-->

												</div>

												<!--/row-->        

												<div class="row-fluid">

													<div class="span12 ">

														<div class="control-group">

															<label class="control-label" style=" font-weight:bolder">状态</label>

															<div class="controls">

																<span class="text">
																<c:if test="${currentTime<assignment.startTime}">未开始</c:if>
																<c:if test="${currentTime>=assignment.startTime and currentTime<assignment.deadline}">正在进行</c:if>
																<c:if test="${currentTime>=assignment.deadline}">已结束</c:if>
																</span>

															</div>

														</div>

													</div>

													<!--/span-->

												</div>
                                                
                                                <div class="row-fluid">

													<div class="span12 ">

														<div class="control-group">

															<label class="control-label" style=" font-weight:bolder">基本要求</label>

															<div class="controls">

																<span class="text">${assignment.basicRequirement}</span>

															</div>

														</div>

													</div>

													<!--/span-->

												</div>
                                                <div class="row-fluid">
												<c:if test="${assignment.fileUrl != null}">
													<div class="span12 ">

														<div class="control-group">

															<label class="control-label" style=" font-weight:bolder">附件</label>

															<div class="controls">

																<a href="${assignment.fileUrl}" class="btn green fileinput-button">
                                            				<i class="icon-download"></i>
                                            				<span>${fn:getFileName(assignment.fileUrl)}</span></a>

															</div>

														</div>

													</div>
													</c:if>
													<!--/span-->

												</div>
                                                <h3 class="form-section"></h3>
                                                
                                                 <div class="row-fluid">

													<div class="span12 ">
													<div class="control-group">

														<label class="control-label" style=" font-weight:bolder">作业</label>

														<div class="controls">

															<textarea class="span12 wysihtml5 m-wrap" name="text" 
															<c:if test="${currentTime<assignment.startTime or currentTime>=assignment.deadline or homework != null}">readonly="readonly"</c:if>>
															${homework.text}</textarea>${error}

														</div>

													</div>
														<c:if test="${homework == null and currentTime>=assignment.startTime and currentTime<assignment.deadline}">															
														<div class="control-group">
														
															<label class="control-label" style=" font-weight:bolder">上传</label>

															<div class="controls">

														<input type="file" name="files" class="fileupload"/>
														
															</div>
														</div>
														</c:if>
														<c:if test="${currentTime>assignment.startTime and homework.fileUrl != null}">
														<div class="control-group">
															<label class="control-label" style=" font-weight:bolder">附件</label>
															<div class="controls">
															<a href="${homework.fileUrl}" class="btn green fileinput-button">
                                            				<i class="icon-download"></i>
                                            				<span>${fn:getFileName(homework.fileUrl)}</span></a>
														</div>
														</div>
														</c:if>
													</div>

													<!--/span-->

												</div>
                                                
												
                                                <div style="margin:20px"></div>
												<!--/row-->                

													<!--/span-->

												</div>

												<!--/row-->

											<div class="form-actions">
												<c:if test="${homework == null}">
													<input type="submit" class="btn blue" value="确认"/>
												</c:if>
												<c:if test="${homework != null}">
													该项作业已提交，不能再次提交
												</c:if>
												<a href="/assignment/assignments/${semesterCourseId}" class="btn">取消</a>

												</div>

											</div>

											<!-- END FORM-->  
											</form>

									</div>
										</div>

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

	<script src="/media/js/app.js"></script>
	<script src="/ckeditor/ckeditor.js"></script>
	<script> CKEDITOR.replace('text');</script>

	<script>

		jQuery(document).ready(function() {       

		   // initiate layout and plugins

		   App.init();

		});

	</script>

	<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>

