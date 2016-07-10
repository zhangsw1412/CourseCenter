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

									<div class="caption"><i class="icon-comments"></i>讨论</div>

								</div>

								<div class="portlet-body" id="chats">

									<div id="messageDiv" class="scroller" data-height="435px" data-always-visible="1" data-rail-visible1="1">

										<ul class="chats" id="messageList">
											<c:forEach items="${messages}" var="message" varStatus="status">
												<c:if test="${message.userNum == sessionScope.user.num}">
													<li class="out" time="${message.createTime}"  <c:if test="${status.last == true}"> id="lastMessage"</c:if>>
														<img class="avatar" alt="" src="/assets/img/Avatar-${sessionScope.user.num%15}.jpg" />
												</c:if>
												<c:if test="${message.userNum != sessionScope.user.num}">
													<li class="in" time="${message.createTime}" >
													<img class="avatar" alt="" src="/assets/img/Avatar-${message.userNum%15}.jpg" />
												</c:if>
														<div class="message">
															<span class="arrow"></span>
															<a href="javascript(0):;" class="name">${message.userName}</a>
															<span id="lastMessageTime" class="datetime">${message.createTime}</span>
															<span class="body">
															${message.content}
															</span>
														</div>

													</li>
											</c:forEach>

										</ul>

									</div>

									<form id="chatForm" method="post">
										<div class="chat-form" id="chat-form">
											<div class="input-cont">
												<input id="content" name="content" class="m-wrap" type="text" placeholder="输入消息..." />
											</div>
											<div class="btn-cont">
												<span class="arrow"></span>
												<button id="sendMessage" class="btn blue"><i class="icon-share-alt icon-white"></i></button>
											</div>
										</div>
									</form>

								</div>

							</div>
                    
                    
						</div>
                        
                        </div>
				</div>

				<div id="state"></div>
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
		function preventForm() {
			$("form").submit(function(e){
				e.preventDefault();
			});
		}

		function scrollToEnd() {
			$("#messageDiv").scrollTop($("#messageDiv")[0].scrollHeight);
		}

		function longPoll() {
			$.ajax({
				type:"POST",
				url:"/semester/${semesterId}/course/${course.id}/chat/ajax",
				timeout:80000,     //ajax请求超时时间80秒
				data:{lastMessageTime:$("#messageList li:last").attr("time")},
				success:function(data,textStatus){
					if (textStatus == "success") { // 请求成功
						if(data.length > 0){
							$("#messageList").append(data);
						}
						scrollToEnd();
						longPoll();
					}
				},
				//Ajax请求超时，继续查询
				error:function(XMLHttpRequest,textStatus,errorThrown){
					if(textStatus=="timeout"){
						longPoll();
					}
				}

			});
		}

		jQuery(document).ready(function() {
		   	App.init();
			preventForm();
			scrollToEnd();
			longPoll();
			$("#sendMessage").unbind('click').click(function(){
				$.ajax({
					url : "/semester/${semesterId}/course/${course.id}/chat/ajax",
					data : $("#chatForm").serialize(),
					type : "POST",
					success:function(data,textStatus){
						if (textStatus == "success") { // 请求成功
							$("#content").val("");
						}
					}
				});
			});
		});
	</script>

	<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>