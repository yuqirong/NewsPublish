<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="navbar navbar-inverse">
	<div class="navbar-inner">
		<div class="container">
			<a data-target=".navbar-responsive-collapse" data-toggle="collapse"
				class="btn btn-navbar"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a href="AdminAction_home" class="brand">新闻发布系统后台管理</a>
			<div class="nav-collapse collapse navbar-responsive-collapse">
				<ul class="nav">
					<li class="divider-vertical"></li>
					<!--  -->
					<li class="dropdown "><a data-toggle="dropdown"
						class="dropdown-toggle" href="javascript:void(0)">前台首页 <b
							class="caret"></b></a>
						<ul class="dropdown-menu">

							<li><s:a href="AdminAction_homePage">
									<i class="icon-user"></i>返回首页</s:a></li>
						</ul></li>
					<li class="dropdown "><a data-toggle="dropdown"
						class="dropdown-toggle" href="javascript:void(0)">系统管理 <b
							class="caret"></b></a>
						<ul class="dropdown-menu">

							<li><s:a action="AdminAction_sysConfig">
									<i class="icon-user"></i>系统配置</s:a></li>
						</ul></li>
				</ul>

				<ul class="nav pull-right">
					<li class="dropdown"><a data-toggle="dropdown"
						class="dropdown-toggle" href="javascript:void(0)">您好，<s:property
								value="#session.admin.username" /><b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><s:a action="PasswordAction_toChangePassword">
									<i class="icon-user"></i>修改密码</s:a></li>
							<li class="divider"></li>
							<li><s:a action="AdminAction_doneLogout">
									<i class="icon-user"></i>退出</s:a></li>
						</ul></li>
					<li><a href="/msg/msg-info-listReceived.do"> <i
							id="unreadMsg" class="badge"></i>
					</a></li>
				</ul>
			</div>
			<!-- /.nav-collapse -->
		</div>
	</div>
	<!-- /navbar-inner -->
</div>
<!-- end of header bar -->
