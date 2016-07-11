<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

	<meta charset="utf-8" />

	<title>source</title>

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

	<link rel="stylesheet" href="/media/css/DT_bootstrap.css" />
    
    <link href="/media/css/jquery.fancybox.css" rel="stylesheet" />

	<link href="/media/css/jquery.fileupload-ui.css" rel="stylesheet" />
    
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

							我的团队

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="/index">主页</a> 

								<i class="icon-angle-right"></i>

							</li>
							<li>

								<a href="#">我的团队</a> 

							</li>
						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					
					<div class="span12">


						<div class="portlet box green">
							<div class="portlet-title">

								<div class="caption"><i class="icon-edit"></i>我的团队列表 </div>

							</div>
							<div class="portlet-body">
								<a  class="btn blue" href="/team/create"  data-toggle="modal"><i class="icon-group"></i>&nbsp;创建团队</a> 
								<div style="padding:5px"></div>
								<table class="table table-striped table-hover table-bordered" id="sample_editable_1">

									<thead>

										<tr>

											<th>团队编号</th>

											<th>团队名</th>

											<th>负责人</th>

											<th>人数</th>
											
											<th>团队详情</th>
										</tr>

									</thead>

									<tbody>
										<c:forEach items="${teams}" var="item">
										<tr class="">

											<td>${item.id}</td>

											<td>${item.name}</td>

											<td>${item.leaderName}</td>

											<td>${item.num}</td>
											<td><a href="team/details" class="btn mini green" style="margin-right:10px">查看</a></td>
										</tr>
										</c:forEach>
									</tbody>

								</table>

							</div>

						</div>


						<!-- END EXAMPLE TABLE PORTLET-->

					</div>

				</div>
   				<div class="row-fluid">
					
					<div class="span12">


						<div class="portlet box purple">
							<div class="portlet-title">

								<div class="caption"><i class="icon-edit"></i>申请加入团队列表 </div>

							</div>

							<div class="portlet-body">
								<table class="table table-striped table-hover table-bordered" id="sample_editable_1">

									<thead>
										<tr>

											<th>团队编号</th>

											<th>团队名</th>

											<th>负责人</th>

											<th>人数</th>
											
											<th>团队详情</th>
											
											<th>申请时间</th>
											
											<th>状态</th>

										</tr>
									</thead>

									<tbody>

										<c:forEach items="${teamApplications}" var="item">
										
										<tr class="">

											<td>${item.teamId}</td>

											<td>${teamsApplied[item.teamId+0].name}</td>

											<td>${teamsApplied[item.teamId+0].leaderName}</td>

											<td>${teamsApplied[item.teamId+0].num}</td>
											
											<td>${item.applyTime}</td>
											
											<td>
											<c:if test="${item.status==0}">未处理</c:if>
											<c:if test="${teamMap[item.id+0]==1}">已加入</c:if>
											<c:if test="${teamMap[item.id+0]==2}">已拒绝</c:if>
											</td>
										</tr>
										</c:forEach>
									</tbody>

								</table>

							</div>

						</div>


						<!-- END EXAMPLE TABLE PORTLET-->

					</div>

				</div>
				

				
						<!-- END SAMPLE TABLE PORTLET-->

					
                    <div id="form_modal2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">

									<div class="modal-header">

										<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

										<h3 id="myModalLabel2">创建团队</h3>

									</div>

									<div class="modal-body">

										<form action="#" class="form-horizontal">
                                        
                                        	<div class="control-group">

										<label class="control-label">团队名称</label>

										<div class="controls">

											<input type="text" class="span2 m-wrap" />
                                      

										</div>

									</div>
                                    
                                    <div class="control-group">

										<label class="control-label">团队人数</label>

										<div class="controls">

											<input type="text" class="span2 m-wrap" />
                                            
                                          
										</div>

									</div>


											

										
										</form>

									</div>

									<div class="modal-footer">

										<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>

										<button class="btn green btn-primary" data-dismiss="modal">创建</button>

									</div>

								</div>
                    </div>

				<!-- END PAGE CONTENT-->

			</div></div>

			<!-- END PAGE CONTAINER-->

		</div>

		<!-- END PAGE -->

	</div>

	<!-- END CONTAINER -->

	<jsp:include page="../include/footer.jsp"></jsp:include>


	<script src="/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

	<script src="/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      

	<script src="/media/js/bootstrap.min.js" type="text/javascript"></script>  

	<script src="/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="/media/js/jquery.blockui.min.js" type="text/javascript"></script>  

	<script src="/media/js/jquery.cookie.min.js" type="text/javascript"></script>

	<script src="/media/js/app.js"></script>  
    
    <script src="/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

	<script src="/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      

	<script src="/media/js/bootstrap.min.js" type="text/javascript"></script>

	<script src="/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="/media/js/jquery.blockui.min.js" type="text/javascript"></script>  

	<script src="/media/js/jquery.cookie.min.js" type="text/javascript"></script>

	<script src="/media/js/jquery.uniform.min.js" type="text/javascript" ></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script type="text/javascript" src="/media/js/ckeditor.js"></script>  

	<script type="text/javascript" src="/media/js/bootstrap-fileupload.js"></script>

	<script type="text/javascript" src="/media/js/chosen.jquery.min.js"></script>

	<script type="text/javascript" src="/media/js/select2.min.js"></script>

	<script type="text/javascript" src="/media/js/wysihtml5-0.3.0.js"></script> 

	<script type="text/javascript" src="/media/js/bootstrap-wysihtml5.js"></script>

	<script type="text/javascript" src="/media/js/jquery.tagsinput.min.js"></script>

	<script type="text/javascript" src="/media/js/jquery.toggle.buttons.js"></script>

	<script type="text/javascript" src="/media/js/bootstrap-datepicker.js"></script>

	<script type="text/javascript" src="/media/js/bootstrap-datetimepicker.js"></script>

	<script type="text/javascript" src="/media/js/clockface.js"></script>

	<script type="text/javascript" src="/media/js/date.js"></script>

	<script type="text/javascript" src="/media/js/daterangepicker.js"></script> 

	<script type="text/javascript" src="/media/js/bootstrap-colorpicker.js"></script>  

	<script type="text/javascript" src="/media/js/bootstrap-timepicker.js"></script>

	<script type="text/javascript" src="/media/js/jquery.inputmask.bundle.min.js"></script>   

	<script type="text/javascript" src="/media/js/jquery.input-ip-address-control-1.0.min.js"></script>

	<script type="text/javascript" src="/media/js/jquery.multi-select.js"></script>   

	<script src="/media/js/bootstrap-modal.js" type="text/javascript" ></script>

	<script src="/media/js/bootstrap-modalmanager.js" type="text/javascript" ></script> 

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="/media/js/app.js"></script>

	<script src="/media/js/form-components.js"></script>     

	<!-- END PAGE LEVEL SCRIPTS -->

	<script>

		jQuery(document).ready(function() {       

		   // initiate layout and plugins

		   App.init();

		   FormComponents.init();

		});

	</script>

	<!-- END JAVASCRIPTS -->   

<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>    

	<script>

		jQuery(document).ready(function() {       

		   // initiate layout and plugins

		   App.init();

		});

	</script>

</body>

<!-- END BODY -->

</html>