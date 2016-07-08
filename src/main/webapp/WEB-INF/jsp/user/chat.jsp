<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

	<meta charset="utf-8" />

	<title>讨论</title>

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
	
	<!--<link rel="stylesheet" type="text/css" href="/media/css/select2_metro.css" />

	<link rel="stylesheet" href="/media/css/DT_bootstrap.css" /> -->
    
    <link href="/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>

	<link href="/media/css/daterangepicker.css" rel="stylesheet" type="text/css" />

	<link href="/media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>

	<link href="/media/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>

	<link href="/media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>

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

							讨论

						</h3>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home"></i>
								<a href="#.html">主页</a>
								<i class="icon-angle-right"></i>
							</li>
							<li>
								<a href="#">${course.name}</a>
								<i class="icon-angle-right"></i>

							</li>
							<li>
								<a href="teacher_homework.html">讨论</a>
							</li>

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="row-fluid">
				
					<div class="span12">
					
                    
							<div class="portlet box green">

								<div class="portlet-title line">

									<div class="caption"><i class="icon-comments"></i>Chats</div>

									<div class="tools">

										<a href="" class="collapse"></a>

										<a href="#portlet-config" data-toggle="modal" class="config"></a>

										<a href="" class="reload"></a>

										<a href="" class="remove"></a>

									</div>

								</div>

								<div class="portlet-body" id="chats">

									<div class="scroller" data-height="435px" data-always-visible="1" data-rail-visible1="1">

										<ul class="chats" id="messageList">
											<c:forEach items="${messages}" var="message">
												<c:if test="${message.userId == sessionScope.user.id}">
													<li class="out">
														<img class="avatar" alt="" src="/media/image/avatar2.jpg" />
												</c:if>
												<c:if test="${message.userId != sessionScope.user.id}">
													<li class="in">
													<img class="avatar" alt="" src="/media/image/avatar1.jpg" />
												</c:if>
														<div class="message">
															<span class="arrow"></span>
															<a href="#">${message.userName}</a>
															<span>${message.createTime}</span>
															<span class="body">
															${message.content}
															</span>
														</div>
													</li>
											</c:forEach>

										</ul>

									</div>

									<form id="chatForm" action="/semester/${semesterId}/course/${course.id}/chat" method="post">
										<input type="hidden" name="size" value="${fn:length(messages)}" />
										<div class="chat-form">
											<div class="input-cont">
												<input name="content" class="m-wrap" type="text" placeholder="输入消息..." />
											</div>
											<div class="btn-cont">
												<span class="arrow"></span>
												<input type="submit" class="btn blue icn-only" />
												<input id="refreshMessage" type="button" class="btn green" value="刷新"/>
											</div>
										</div>
									</form>

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
		});
	</script>
	<script>
		$("#sendMessage").unbind('click').click(function(){
			htmlobj = $.ajax({
				url : "/semester/${semesterId}/course/${course.id}/chat/ajax",
				data : $("#chatForm").serialize(),
				type : "POST",
				async : false
			});
			$("#messageList").html(htmlobj.responseText);
		});

		$("#refreshMessage").unbind('click').click(function(){
			htmlobj = $.ajax({
				url : "/semester/${semesterId}/course/${course.id}/chat/ajax",
				data : {size : ${fn:length(messages)}},
				type : "GET",
				async : false
			});
			$("#messageList").html(htmlobj.responseText);
		});

	</script>
<script type="text/javascript">
	$(function () {
		(function longPolling() {
			$.ajax({
				url : "/semester/${semesterId}/course/${course.id}/chat/ajax",
				type : "GET",
				timeout: 50000,
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					if (textStatus == "timeout") { // 请求超时
						longPolling(); // 递归调用
						// 其他错误，如网络错误等
					} else {
						longPolling();
					}
				},
				success: function (htmlobj, textStatus) {
					$("#messageList").appendChild(htmlobj.responseText);
					if (textStatus == "success") { // 请求成功
						longPolling();
					}
				}
			});
		})();

	});
</script>

	<!-- END JAVASCRIPTS -->

<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>

</body>

<!-- END BODY -->

</html>