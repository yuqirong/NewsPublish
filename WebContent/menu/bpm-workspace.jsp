<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<!-- start of sidebar -->
	<aside id="m-sidebar" class="accordion span2" data-spy="affix"
		data-offset-top="100">

		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					data-parent="#m-sidebar" href="#collapse-bpm-process"> <i
					class="icon-user"></i> <span class="title">新闻管理</span>
				</a>
			</div>
			<div id="collapse-bpm-process" class="accordion-body collapse ${currentMenu == 'bpm-process' ? 'in' : ''}">
				<ul class="accordion-inner nav nav-list">
					<li><s:a action="NewsAction_getAllNews">
							<i class="icon-user"></i>新闻列表</s:a></li>
					<li><s:a action="NewsAction_addNews">
							<i class="icon-user"></i>添加新闻</s:a></li>
					<li><s:a action="NewsAction_getAllNotPassedNews">
							<i class="icon-user"></i>新闻审核</s:a></li>
				</ul>
			</div>
		</div>

		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					data-parent="#m-sidebar" href="#collapse-bpm-task"> <i
					class="icon-user"></i> <span class="title">栏目管理</span>
				</a>
			</div>
			<div id="collapse-bpm-task" class="accordion-body collapse ${currentMenu == 'bpm-task' ? 'in' : ''}">
				<ul class="accordion-inner nav nav-list">
					<li><s:a action="NewsTypeAction_getAllNewsTypes">
							<i class="icon-user"></i>栏目列表</s:a></li>
					<li><s:a action="NewsTypeAction_addNewsType">
							<i class="icon-user"></i>添加栏目</s:a></li>
				</ul>
			</div>
		</div>

		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					data-parent="#m-sidebar" href="#collapse-bpm-delegate"> <i
					class="icon-user"></i> <span class="title">权限设置</span>
				</a>
			</div>
			<div id="collapse-bpm-delegate" class="accordion-body collapse ${currentMenu == 'bpm-delegate' ? 'in' : ''}">
				<ul class="accordion-inner nav nav-list">
					<li><s:a action="RightAction_findAllRights">
							<i class="icon-user"></i>权限列表</s:a></li>
					<li><s:a action="RightAction_toAddRightPage">
							<i class="icon-user"></i>添加权限</s:a></li>
				</ul>
			</div>
		</div>

		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					data-parent="#m-sidebar" href="#collapse-cal"> <i
					class="icon-user"></i> <span class="title">角色管理</span>
				</a>
			</div>
			<div id="collapse-cal" class="accordion-body collapse ${currentMenu == 'cal' ? 'in' : ''}">
				<ul class="accordion-inner nav nav-list">
					<li><s:a action="RoleAction_findAllRoles">
							<i class="icon-user"></i>角色列表</s:a></li>
					<li><s:a action="RoleAction_toAddRolePage">
							<i class="icon-user"></i>添加角色</s:a></li>
				</ul>
			</div>
		</div>

		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					data-parent="#m-sidebar" href="#collapse-msg "> <i
					class="icon-user"></i> <span class="title">用户管理</span>
				</a>
			</div>
			<div id="collapse-msg" class="accordion-body collapse ${currentMenu == 'msg' ? 'in' : ''}">
				<ul class="accordion-inner nav nav-list">
					<li><s:a action="UserAction_getAllUsers">
							<i class="icon-user"></i>用户列表</s:a></li>
				</ul>
			</div>
		</div>

		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					data-parent="#m-sidebar" href="#collapse-doc"> <i
					class="icon-user"></i> <span class="title">管理员管理</span>
				</a>
			</div>
			<div id="collapse-doc" class="accordion-body collapse ${currentMenu == 'doc' ? 'in' : ''}">
				<ul class="accordion-inner nav nav-list">
					<li><s:a action="AdminAction_getAllAdmins">
							<i class="icon-user"></i>管理员列表</s:a></li>
					<li><s:a action="AdminAction_toAddAdminPage">
							<i class="icon-user"></i>添加管理员</s:a></li>
				</ul>
			</div>
		</div>

		<footer id="m-footer" class="text-center">
			<hr>
			<span style="font-family: Arial, Helvetica, sans-serif;">Copyright
				&copy; 2015 版权所有<br> All Rights Reserved
			</span>
		</footer>
	</aside>
	<!-- end of sidebar -->