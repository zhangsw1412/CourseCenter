<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

	<meta charset="utf-8" />

	<title>团队详情</title>

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

							团队详情 <small>查看团队信息，组员信息等</small>

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="/index">主页</a> 

								<i class="icon-angle-right"></i>

							</li>

							<li>

								<a href="#">团队详情</a>

							</li>

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="row-fluid">
				
					<div class="span12">
					
						<!-- BEGIN SAMPLE TABLE PORTLET-->
						
						<div class="portlet box green">
						
							<div class="portlet-title">
							
								<div class="caption"><i class="icon-table"></i>团队信息</div>
								
							</div>
							
							<div class="portlet-body">
									<form action="#" class="form-horizontal">
									<div class="actions row-fluid">
									<div class="span4">

										<ul class="unstyled">
	
											<li><h5><strong>团队编号：</strong> ${team.id}</h5></li>
	
											<li><h5><strong>团队名：</strong> ${team.name}</h5></li>
	
											<li><h5><strong>负责人：</strong> ${team.leaderName}</h5></li>
	
										</ul>

									</div>

									<div class="span4">
	
										<ul class="unstyled">
	
											<li><h5><strong>人数上限：</strong> ${team.maxNum}</h5></li>
	
											<li><h5><strong>当前人数：</strong> ${team.num}</h5></li>
	
											<li><h5><strong>开放申请：</strong>
											<c:if test="${team.applicable==true}">是</c:if>
											<c:if test="${team.applicable==false}">否</c:if>
											</h5></li>
	
										</ul>
	
									</div>
									</div>
									</form>
							</div>
							
						</div>

						<div class="portlet box yellow">

							<div class="portlet-title">

								<div class="caption"><i class="icon-cogs"></i>组员信息</div>

								<div class="actions">
									
								
								</div>
								
							</div>

							<div class="portlet-body flip-scroll">

								<table class="table-bordered table-striped table-condensed flip-content">

									<thead class="flip-content">

										<tr>

											<th>学号</th>

											<th>姓名</th>

											<th>性别</th>

											<th>委任管理员</th>
											
										</tr>

									</thead>

									<tbody>
										<c:forEach items="${members}" var="item">
										<tr>

											<td>${item.id}</td>

											<td>${item.name}</td>

											<td><c:if test="${item.gender==true}">女</c:if><c:if test="${item.gender==false}">男</c:if></td>

										</tr>
										</c:forEach>
									</tbody>

								</table>

							</div>

							</div>

						</div>
						<div class="portlet box purple">

							<div class="portlet-title">

								<div class="caption"><i class="icon-cogs"></i>申请列表</div>

								<div class="actions">
									
								
								</div>
								

							</div>

							<div class="portlet-body flip-scroll">

								<table class="table-bordered table-striped table-condensed flip-content">

									<thead class="flip-content">

										<tr>

											<th>学号</th>

											<th>姓名</th>

											<th class="numeric">性别</th>

											<th class="numeric">申请时间</th>

											<th class="numeric">审核</th>

										</tr>

									</thead>

									<tbody>
										<c:forEach items="${applications}" var="item">
										<tr>

											<td>13210000</td>

											<td>张三</td>

											<td>男/女</td>

											<td>${item.applyTime}</td>

											<td><div align="center"> <a  class="btn mini blue"><i class="icon-pencil"></i> 同意</a>     <a  class="btn mini black"><i class="icon-trash"></i> 拒绝</a></div></td>
										
										</tr>
										</c:forEach>
									</tbody>

								</table>

							</div>

							</div>

						</div>

						<!-- END SAMPLE TABLE PORTLET-->
						<div align="center">
																<a class="btn medium purple" ><i class="icon-edit"></i> <big>关闭申请</big></a>
																<a class="btn medium black" ><i class="icon-edit"></i> <big>解散</big></a>
																<a class="btn medium blue" ><i class="icon-edit"></i> <big>加入课程</big></a>
						</div>
			
						<!-- END SAMPLE TABLE PORTLET-->
					</div>
				</div>

				<!-- END PAGE CONTENT-->

			</div>

			<!-- END PAGE CONTAINER-->

		</div>


		<!-- END PAGE -->


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