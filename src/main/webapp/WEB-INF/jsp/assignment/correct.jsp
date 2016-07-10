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

	<link href="/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" media="screen"/>

	<link href="/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

	<link href="/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="/media/css/style.css" rel="stylesheet" type="text/css"/>

	<link href="/media/css/style-responsive.css" rel="stylesheet" type="text/css" />

	<link href="/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<link href="/media/css/invoice.css" rel="stylesheet" type="text/css"/>

	<link href="/media/css/print.css" rel="stylesheet" type="text/css" media="print"/>

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

				<div class="row-fluid hidden-print">

					<div class="span12">

						<!-- BEGIN PAGE TITLE & BREADCRUMB-->

						<h3 class="page-title">

							作业批改 <small>请为这名学生的作业进行打分与评价</small>

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

							<li>

								<a href="/assignment/homeworks/${assignment.id}">提交列表</a>

								<i class="icon-angle-right"></i>

							</li>

							<li>
							
								<a href="#">作业批改</a>
								
							</li>

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="row-fluid invoice">

					<div class="row-fluid">

								<div class="span3">

									<h4>课程：</h4>

									<ul class="unstyled">

										<h1>${course.name}</h1>

									</ul>

								</div>

								<div class="span4">

									<h4>作业信息：</h4>

									<ul class="unstyled">

										<li><strong>作业名:</strong> ${assignment.name }</li>

										<li><strong>开始时间：</strong> ${assignment.startTime }</li>

										<li><strong>结束时间：</strong> ${assignment.deadline }</li>

										<li><strong>详细需求：</strong>	 ${assignment.basicRequirement }</li>

									</ul>

								</div>

								<div class="span4 invoice-payment">

									<h4>个人信息：</h4>

									<ul class="unstyled">

										<li><strong>姓名：</strong> ${student.name }</li>

										<li><strong>学号：</strong> ${student.id }</li>

									</ul>

								</div>
					
					</div>
					
					<div class="row-fluid">
					
						<div class="span12">

							<div class="portlet box yellow">

								<div class="portlet-title">

									<div class="caption"><i class="icon-pencil"></i>作业详情</div>

								</div>

								<div class="portlet-body">
								
									<div>
										
										<h3><strong>查阅</strong></h3>

											<textarea name="homeworktext" readonly="readonly" class="span10 m-wrap">${homework.text }</textarea>
											
											<div class="row-fluid">
												<c:if test="${homework.fileUrl != null}">
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
									</div>
									
									<br/>
									
									<hr/>
									
									<div class="post-comment">

										<h3><strong>批示</strong></h3>

										<form action="/assignment/correct/${homeworkId}" method="POST">

											<label>
											
												<strong>分数</strong><span class="color-red"></span>
												
												<input type="text" name="score" class="span1" <c:if test="${homework.comment != null}">readonly="readonly" value="${homework.score}"</c:if>>
											
												<strong><big><big>/${assignment.highestScore}</big></big></strong>
												<strong><span style="color:red">${illegalScore}</span></strong>
												<strong><span style="color:red">${scoreOutOfRange}</span></strong>
											</label>


											<label><strong>作业评价</strong></label>

											<textarea class="span10 m-wrap" name="comment" <c:if test="${homework.comment != null}">readonly="readonly"</c:if>>${homework.comment}</textarea>
																${noComment}<div class="row-fluid">

											<div class="span4">

											</div>
					
											<div class="span8 invoice-block">

											<br />
				
											<c:if test="${homework.comment == null}">
												<input type="submit" value="确认" class="btn green big hidden-print"/>
											</c:if>
												<c:if test="${homework.comment != null}">
													该项作业已批改，不能再次批改
												</c:if>
											<a class="btn big hidden-print" href="/assignment/homeworks/${assignment.id}">返回</a>
				
											</div>
				
											</div>
										</form>
										
									</div>
								</div>
								
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

	<script src="/media/js/app.js"></script>
	<script src="/ckeditor/ckeditor.js"></script>

	<script> CKEDITOR.replace('comment');</script>
	<script> CKEDITOR.replace('homeworktext');</script>
	<script>

		jQuery(document).ready(function() {    

		   App.init();

		});

	</script>

	<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>