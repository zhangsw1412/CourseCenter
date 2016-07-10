<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" isELIgnored="false"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><!DOCTYPE html><!--[if IE 8]> <html lang="en" class="ie8"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9"> <![endif]--><!--[if !IE]><!--><html lang="en"><!--<![endif]--><!-- BEGIN HEAD --><head><meta charset="utf-8" /><title>个人信息管理</title><meta content="width=device-width, initial-scale=1.0" name="viewport" /><meta content="" name="description" /><meta content="" name="author" /><!-- BEGIN GLOBAL MANDATORY STYLES --><link href="media/css/bootstrap.min.css" rel="stylesheet"	type="text/css" /><link href="media/css/bootstrap-responsive.min.css" rel="stylesheet"	type="text/css" /><link href="media/css/font-awesome.min.css" rel="stylesheet"	type="text/css" /><link href="media/css/style-metro.css" rel="stylesheet" type="text/css" /><link href="media/css/style.css" rel="stylesheet" type="text/css" /><link href="media/css/style-responsive.css" rel="stylesheet"	type="text/css" /><link href="media/css/default.css" rel="stylesheet" type="text/css"	id="style_color" /><link href="media/css/uniform.default.css" rel="stylesheet"	type="text/css" /><!-- END GLOBAL MANDATORY STYLES --><!-- BEGIN PAGE LEVEL STYLES --><link href="media/css/bootstrap-fileupload.css" rel="stylesheet"	type="text/css" /><link href="media/css/chosen.css" rel="stylesheet" type="text/css" /><link href="media/css/profile.css" rel="stylesheet" type="text/css" /><!-- END PAGE LEVEL STYLES --><link rel="shortcut icon" href="media/image/favicon.ico" /></head><!-- END HEAD --><!-- BEGIN BODY --><body class="page-header-fixed">	<jsp:include page="../include/header.jsp"></jsp:include>	<!-- BEGIN CONTAINER -->	<div class="page-container row-fluid">		<jsp:include page="../include/sidebar.jsp"></jsp:include>		<!-- BEGIN PAGE -->		<div class="page-content">			<!-- BEGIN PAGE CONTAINER-->			<div class="container-fluid">				<!-- BEGIN PAGE HEADER-->				<div class="row-fluid">					<div class="span12">						<!-- BEGIN STYLE CUSTOMIZER -->						<div class="color-panel hidden-phone">							<div class="color-mode-icons icon-color"></div>							<div class="color-mode-icons icon-color-close"></div>							<div class="color-mode">								<p>THEME COLOR</p>								<ul class="inline">									<li class="color-black current color-default"										data-style="default"></li>									<li class="color-blue" data-style="blue"></li>									<li class="color-brown" data-style="brown"></li>									<li class="color-purple" data-style="purple"></li>									<li class="color-grey" data-style="grey"></li>									<li class="color-white color-light" data-style="light"></li>								</ul>								<label> <span>Layout</span> <select									class="layout-option m-wrap small">										<option value="fluid" selected>Fluid</option>										<option value="boxed">Boxed</option>								</select>								</label> <label> <span>Header</span> <select									class="header-option m-wrap small">										<option value="fixed" selected>Fixed</option>										<option value="default">Default</option>								</select>								</label> <label> <span>Sidebar</span> <select									class="sidebar-option m-wrap small">										<option value="fixed">Fixed</option>										<option value="default" selected>Default</option>								</select>								</label> <label> <span>Footer</span> <select									class="footer-option m-wrap small">										<option value="fixed">Fixed</option>										<option value="default" selected>Default</option>								</select>								</label>							</div>						</div>						<!-- END BEGIN STYLE CUSTOMIZER -->						<!-- BEGIN PAGE TITLE & BREADCRUMB-->						<h3 class="page-title">							个人信息管理 <small>修改个人信息</small>						</h3>						<ul class="breadcrumb">							<li>								<i class="icon-home"></i> <a href="index.html">个人信息</a> <i									class="icon-angle-right"></i>							</li>						</ul>						<!-- END PAGE TITLE & BREADCRUMB-->					</div>				</div>				<!-- END PAGE HEADER-->				<!-- BEGIN PAGE CONTENT-->				<div class="row-fluid profile">					<div class="span12">						<div class="portlet box blue tabbable">							<div class="portlet-title">								<div class="caption">									<i class="icon-reorder"></i> <span class="hidden-480">个人信息</span>								</div>							</div>							<div class="portlet-body form">								<div class="tabbable portlet-tabs">									<br />									<br />									<br />									<br />									<div class="tab-content">										<div class="tab-pane active" id="portlet_tab1">											<!-- BEGIN FORM-->											<form action="/updatePassword" class="form-horizontal"												method="post">												<div class="control-group">													<label class="control-label" style="font-weight: bolder">学号：</label>													<div class="controls">														<span class="text">${user.id }</span>													</div>												</div>												<div class="control-group">													<label class="control-label" style="font-weight: bolder">姓名：</label>													<div class="controls">														<span class="text">${user.name }</span>													</div>												</div>												<div class="control-group">													<label class="control-label" style="font-weight: bolder">性别：</label>													<div class="controls">														<span class="text"><c:if																test="${user.gender==false }">男</c:if> <c:if																test="${user.gender==true }">女</c:if></span>													</div>												</div>												<div class="control-group">													<label class="control-label" style="font-weight: bolder">身份：</label>													<div class="controls">														<span class="text"><c:if test="${user.type==0 }">学生</c:if>															<c:if test="${user.type==1 }">老师</c:if> <c:if																test="${user.type==2 }">教务</c:if></span>													</div>												</div>												<div class="control-group">													<label class="control-label" style="font-weight: bolder">原密码：</label>													<div class="controls">														<input class="s-wrap placeholder-no-fix" type="password"															placeholder="输入原密码" name="old_password" />													</div>												</div>												<div class="control-group">													<label class="control-label" style="font-weight: bolder">新密码：</label>													<div class="controls">														<input class="s-wrap placeholder-no-fix" type="password"															placeholder="输入新密码" name="new_password" />													</div>												</div>												<div class="control-group">													<label class="control-label" style="font-weight: bolder">确认密码：</label>													<div class="controls">														<input class="s-wrap placeholder-no-fix" type="password"															placeholder="输入确认密码" name="confirm_password" />													</div>												</div>												<div class="control-group">													<label class="control-label" style="font-weight: bolder">上次登录IP：</label>													<div class="controls">														<span class="text">${user.lastLoginIp }</span>													</div>												</div>												<div class="control-group">													<label class="control-label" style="font-weight: bolder">上次登录时间：</label>													<div class="controls">														<span class="text"><fmt:formatDate																value="${user.lastLoginTime }" type="both" /></span>													</div>												</div>												<div class="control-group">													<label class="control-label" style="font-weight: bolder">用户状态：</label>													<div class="controls">														<span class="text"><c:if																test="${user.valid==false }">无效</c:if> <c:if																test="${user.valid==true }">有效</c:if></span>													</div>												</div>												<div class="form-actions">													<h4 style="color: red">${message }</h4>													<input type="submit" class="btn blue" value="提交" />												</div>											</form>											<!--end tab-pane-->										</div>									</div>								</div>							</div>							<!--END TABS-->						</div>					</div>					<!-- END PAGE CONTENT-->				</div>				<!-- END PAGE CONTAINER-->			</div>			<!-- END PAGE -->		</div>	</div>	</div>	</div>	<!-- END CONTAINER -->	<jsp:include page="../include/footer.jsp"></jsp:include>	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->	<!-- BEGIN CORE PLUGINS -->	<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>	<script src="media/js/jquery-migrate-1.2.1.min.js"		type="text/javascript"></script>	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->	<script src="media/js/jquery-ui-1.10.1.custom.min.js"		type="text/javascript"></script>	<script src="media/js/bootstrap.min.js" type="text/javascript"></script>	<!--[if lt IE 9]>	<script src="media/js/excanvas.min.js"></script>	<script src="media/js/respond.min.js"></script>  	<![endif]-->	<script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>	<script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>	<script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>	<script src="media/js/jquery.uniform.min.js" type="text/javascript"></script>	<!-- END CORE PLUGINS -->	<!-- BEGIN PAGE LEVEL PLUGINS -->	<script type="text/javascript" src="media/js/bootstrap-fileupload.js"></script>	<script type="text/javascript" src="media/js/chosen.jquery.min.js"></script>	<!-- END PAGE LEVEL PLUGINS -->	<!-- BEGIN PAGE LEVEL SCRIPTS -->	<script src="media/js/app.js"></script>	<!-- END PAGE LEVEL SCRIPTS -->	<script>		jQuery(document).ready(function() {			// initiate layout and plugins			App.init();		});	</script>	<!-- END JAVASCRIPTS -->	<script type="text/javascript">		var _gaq = _gaq || [];		_gaq.push([ '_setAccount', 'UA-37564768-1' ]);		_gaq.push([ '_setDomainName', 'keenthemes.com' ]);		_gaq.push([ '_setAllowLinker', true ]);		_gaq.push([ '_trackPageview' ]);		(function() {			var ga = document.createElement('script');			ga.type = 'text/javascript';			ga.async = true;			ga.src = ('https:' == document.location.protocol ? 'https://'					: 'http://')					+ 'stats.g.doubleclick.net/dc.js';			var s = document.getElementsByTagName('script')[0];			s.parentNode.insertBefore(ga, s);		})();	</script></body><!-- END BODY --></html>