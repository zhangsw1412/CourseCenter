<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--[if IE 8]> <html lang="en" class="ie8"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9"> <![endif]--><!--[if !IE]><!--> <html lang="en"> <!--<![endif]--><!-- BEGIN HEAD --><head>	<meta charset="utf-8" />	<title>课程中心登录页面</title>	<meta content="width=device-width, initial-scale=1.0" name="viewport" />	<meta content="" name="description" />	<meta content="" name="author" />	<!-- BEGIN GLOBAL MANDATORY STYLES -->	<link href="/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>	<link href="/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>	<link href="/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>	<link href="/media/css/style-metro.css" rel="stylesheet" type="text/css"/>	<link href="/media/css/style.css" rel="stylesheet" type="text/css"/>	<link href="/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>	<link href="/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>	<link href="/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>	<!-- END GLOBAL MANDATORY STYLES -->	<!-- BEGIN PAGE LEVEL STYLES -->	<link href="/media/css/login.css" rel="stylesheet" type="text/css"/>	<!-- END PAGE LEVEL STYLES -->	<link rel="shortcut icon" href="/media/image/favicon.ico" /></head><!-- END HEAD --><!-- BEGIN BODY --><body class="login" background="assets/img/sky.jpg" style="background-size:100%"><div style="margin-top: 10%"></div>	<!-- BEGIN LOGIN -->	<div class="content" style="background-color:rgba(255, 255, 255, 0.1)">		<!-- BEGIN LOGIN FORM -->		<form class="form-vertical login-form"  action="/login" method="post">			<img src="/media/image/logo-big.png" alt="" />			<div style="height:25px">			</div>			<div class="alert alert-error hide">				<button class="close" data-dismiss="alert"></button>				<span>用户名或密码不能为空！</span>			</div>			<div class="control-group">				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->				<label class="control-label visible-ie8 visible-ie9">Username</label>				<div class="controls">					<div class="input-icon left">						<i class="icon-user"></i>						<input class="m-wrap placeholder-no-fix" type="text" style="height:2em;width:100%;color:#ffffff" placeholder="Username" name="userId"/>					</div>				</div>			</div>			<div class="control-group">				<label class="control-label visible-ie8 visible-ie9">Password</label>				<div class="controls">					<div class="input-icon left">						<i class="icon-lock"></i>						<input class="m-wrap placeholder-no-fix" style="height:2em;width:100%;color:#ffffff" type="password" placeholder="Password" name="password"/>					</div>				</div>			</div>			<div class="form-actions" style="background-color:rgba(255, 255, 255, 0); text-align:center; margin:30px">				<span style="color:red">${error}</span>				<button type="submit" class="btn green" style="width:100%;">					<strong><big>登录</big></strong>				</button>			</div>		</form>		<!-- END LOGIN FORM -->	</div>	<!-- END LOGIN -->	<!-- BEGIN COPYRIGHT -->	<div class="copyright">	</div>	<!-- END COPYRIGHT -->	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->	<!-- BEGIN CORE PLUGINS -->	<script src="/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>	<script src="/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->	<script src="/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      	<script src="/media/js/bootstrap.min.js" type="text/javascript"></script>	<!--[if lt IE 9]>	<script src="/media/js/excanvas.min.js"></script>	<script src="/media/js/respond.min.js"></script>  	<![endif]-->   	<script src="/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>	<script src="/media/js/jquery.blockui.min.js" type="text/javascript"></script>  	<script src="/media/js/jquery.cookie.min.js" type="text/javascript"></script>	<script src="/media/js/jquery.uniform.min.js" type="text/javascript" ></script>	<!-- END CORE PLUGINS -->	<!-- BEGIN PAGE LEVEL PLUGINS -->	<script src="/media/js/jquery.validate.min.js" type="text/javascript"></script>	<!-- END PAGE LEVEL PLUGINS -->	<!-- BEGIN PAGE LEVEL SCRIPTS -->	<script src="/media/js/app.js" type="text/javascript"></script>	<script src="/media/js/login.js" type="text/javascript"></script>      	<!-- END PAGE LEVEL SCRIPTS --> 	<script>		jQuery(document).ready(function() {     		  App.init();		  Login.init();		});	</script>	<!-- END JAVASCRIPTS --></body><!-- END BODY --></html>