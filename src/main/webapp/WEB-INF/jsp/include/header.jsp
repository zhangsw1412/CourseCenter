<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN HEADER -->

<div class="header navbar navbar-inverse navbar-fixed-top">

	<!-- BEGIN TOP NAVIGATION BAR -->

	<div class="navbar-inner">

		<div class="container-fluid">

			<!-- BEGIN LOGO -->

			<ul class="nav pull-right" style="vertical-align: middle">
				<li class="dropdown user">
					<a href="javascript:;">
						<jsp:useBean id="now" class="java.util.Date" />
						<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy年M月d日" />
					</a>
				</li>
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<li class="dropdown user">
					<a href="javascript:;">
						第${semester.schoolYear}学年度
					</a>
				</li>
				<li class="dropdown user">
					<a href="javascript:;">
						第${semester.season}学期
					</a>
				</li>
				<li class="dropdown user">

					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img style="width:30px" src="/assets/img/Avatar-${sessionScope.user.num	%26}.jpg" />
						<span class="username">${sessionScope.user.name}</span>
						<i class="icon-angle-down"></i>
					</a>

					<ul class="dropdown-menu">
						<li>
							<a href="/userInfo"><i class="icon-user"> </i>个人信息</a>
						</li>
						<li>
							<a href="/logout"><i class="icon-signout"> </i>退出</a>
						</li>

					</ul>

				</li>

				<!-- END USER LOGIN DROPDOWN -->

			</ul>

			<!-- END LOGO -->

			<!-- BEGIN RESPONSIVE MENU TOGGLER -->

			<a class="brand" href="/index"> <img src="/media/image/logo.png"
				alt="logo" />

			</a>

			<!-- END RESPONSIVE MENU TOGGLER -->

			<!-- BEGIN TOP NAVIGATION MENU -->

			<a href="javascript:;" class="btn-navbar collapsed"
				data-toggle="collapse" data-target=".nav-collapse"> <img
				src="/media/image/menu-toggler.png" alt="" />

			</a>

			<!-- END TOP NAVIGATION MENU -->

		</div>

	</div>

	<!--END OF NAVIGATION BAR -->

</div>

<!-- END HEADER -->
