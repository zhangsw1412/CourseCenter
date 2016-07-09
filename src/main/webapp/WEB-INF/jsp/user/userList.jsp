<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" isELIgnored="false"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><!DOCTYPE html><!--[if IE 8]> <html lang="en" class="ie8"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9"> <![endif]--><!--[if !IE]><!--><html lang="en"><!--<![endif]--><!-- BEGIN HEAD --><head><meta charset="utf-8" /><title>基本信息管理</title><meta content="width=device-width, initial-scale=1.0" name="viewport" /><meta content="" name="description" /><meta content="" name="author" /><!-- BEGIN GLOBAL MANDATORY STYLES --><link href="/media/css/bootstrap.min.css" rel="stylesheet"	type="text/css" /><link href="/media/css/bootstrap-responsive.min.css" rel="stylesheet"	type="text/css" /><link href="/media/css/font-awesome.min.css" rel="stylesheet"	type="text/css" /><link href="/media/css/style-metro.css" rel="stylesheet" type="text/css" /><link href="/media/css/style.css" rel="stylesheet" type="text/css" /><link href="/media/css/style-responsive.css" rel="stylesheet"	type="text/css" /><link href="/media/css/default.css" rel="stylesheet" type="text/css"	id="style_color" /><link href="/media/css/uniform.default.css" rel="stylesheet"	type="text/css" /><!-- END GLOBAL MANDATORY STYLES --><!-- BEGIN PAGE LEVEL STYLES --><link rel="stylesheet" type="text/css"	href="/media/css/select2_metro.css" /><link rel="stylesheet" href="/media/css/DT_bootstrap.css" /><!-- END PAGE LEVEL STYLES --><link rel="shortcut icon" href="/media/image/favicon.ico" /></head><!-- END HEAD --><!-- BEGIN BODY --><body class="page-header-fixed">	<jsp:include page="../include/header.jsp"></jsp:include>	<!-- BEGIN CONTAINER -->	<div class="page-container row-fluid">		<jsp:include page="../include/sidebar.jsp"></jsp:include>		<!-- BEGIN PAGE -->		<div class="page-content">			<!-- BEGIN PAGE CONTAINER-->			<div class="container-fluid">				<!-- BEGIN PAGE HEADER-->				<div class="row-fluid">					<div class="span12">						<!-- BEGIN PAGE TITLE & BREADCRUMB-->						<h3 class="page-title">							个人信息管理 <small>管理教师、学生个人信息</small>						</h3>						<ul class="breadcrumb">							<li>								<i class="icon-home"></i> <a href="admin_homepage.html">主页</a> <i									class="icon-angle-right"></i>							</li>							<li>								<a href="admin_infoManager.html">个人信息管理</a>							</li>						</ul>						<!-- END PAGE TITLE & BREADCRUMB-->					</div>				</div>				<!-- END PAGE HEADER-->				<!-- BEGIN PAGE CONTENT-->				<div class="row-fluid">					<div class="span12">						<!-- BEGIN EXAMPLE TABLE PORTLET-->						<div class="portlet box blue">							<div class="portlet-title">								<div class="caption">									<i class="icon-edit"></i>师生基本信息								</div>								<div class="tools">									<a href="javascript:;" class="collapse"></a> <a										href="#portlet-config" data-toggle="modal" class="config"></a>									<a href="javascript:;" class="reload"></a>								</div>							</div>							<div class="portlet-body">								<div class="clearfix">									<!-- <div class="btn-group">										<button id="sample_editable_1_new" class="btn green">										添加新纪录 <i class="icon-plus"></i>										</button>									</div> -->								</div>								<table class="table table-striped table-hover table-bordered"									id="sample_editable_1">									<thead>										<tr>											<th>学号</th>											<th>姓名</th>											<th>性别</th>											<th>用户类型</th>											<th>帐户状态</th>											<th>上次登录时间</th>											<th>上次登录IP</th>											<th>操作</th>										</tr>									</thead>									<tbody>										<c:forEach items="${requestScope.users }" var="user">											<tr>												<td>${user.id }</td>												<td>${user.name }</td>												<td><c:if test="${user.gender==false }">男</c:if> <c:if														test="${user.gender==true }">女</c:if></td>												<td><c:if test="${user.type==0 }">学生</c:if> <c:if														test="${user.type==1 }">老师</c:if> <c:if														test="${user.type==2 }">教务</c:if></td>												<td><c:if test="${user.valid==false }">无效</c:if> <c:if														test="${user.valid==true }">有效</c:if></td>												<td><fmt:formatDate value="${user.lastLoginTime }"														type="both" /></td>												<td>${user.lastLoginIp }</td>												<td><a href="/cancelUser/${user.num }">注销</a></td>											</tr>										</c:forEach>									</tbody>								</table>							</div>						</div>						<!-- END EXAMPLE TABLE PORTLET-->					</div>					<a class="btn large green">查询学生信息</a> <a class="btn large red">查询教师信息</a>				</div>				<!-- END PAGE CONTENT -->			</div>			<!-- END PAGE CONTAINER-->		</div>		<!-- END PAGE -->	</div>	<!-- END CONTAINER -->	<!-- BEGIN FOOTER -->	<div class="footer">		<div class="footer-inner">2013 &copy; Metronic by keenthemes.</div>		<div class="footer-tools">			<span class="go-top"> <i class="icon-angle-up"></i>			</span>		</div>	</div>	<!-- END FOOTER -->	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->	<!-- BEGIN CORE PLUGINS -->	<script src="/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>	<script src="/media/js/jquery-migrate-1.2.1.min.js"		type="text/javascript"></script>	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->	<script src="/media/js/jquery-ui-1.10.1.custom.min.js"		type="text/javascript"></script>	<script src="/media/js/bootstrap.min.js" type="text/javascript"></script>	<!--[if lt IE 9]>	<script src="/media/js/excanvas.min.js"></script>	<script src="/media/js/respond.min.js"></script>  	<![endif]-->	<script src="/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>	<script src="/media/js/jquery.blockui.min.js" type="text/javascript"></script>	<script src="/media/js/jquery.cookie.min.js" type="text/javascript"></script>	<script src="/media/js/jquery.uniform.min.js" type="text/javascript"></script>	<!-- END CORE PLUGINS -->	<!-- BEGIN PAGE LEVEL PLUGINS -->	<script type="text/javascript" src="/media/js/select2.min.js"></script>	<script type="text/javascript" src="/media/js/jquery.dataTables.js"></script>	<script type="text/javascript" src="/media/js/DT_bootstrap.js"></script>	<!-- END PAGE LEVEL PLUGINS -->	<!-- BEGIN PAGE LEVEL SCRIPTS -->	<script src="/media/js/app.js"></script>	<script src="/media/js/table-editable.js"></script>	<script>		jQuery(document).ready(function() {			App.init();			TableEditable.init();		});	</script></body><!-- END BODY --></html>