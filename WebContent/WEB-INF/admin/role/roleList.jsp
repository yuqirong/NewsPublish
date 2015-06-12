<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%pageContext.setAttribute("currentMenu", "cal");%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/meta.jsp"%>
<title>角色管理</title>
<%@include file="/common/s.jsp"%>
</head>
<body>
	<%@include file="/header/bpm-workspace.jsp"%>
	<div class="row-fluid">
		<%@include file="/menu/bpm-workspace.jsp"%>

		<!-- start of main -->
		<section id="m-main" class="span10" style="float: right"> <article
			class="m-widget"> <header class="header">
		<h4 class="title">角色信息列表</h4>
		</header>
		<div class="content">
			<table id="demoGrid" class="m-table table-hover">
				<thead>
					<tr>
						<th class="sorting">序号</th>
						<th class="sorting">角色名称</th>
						<th class="sorting">角色属性</th>
						<th class="sorting">修改</th>
						<th class="sorting">删除</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="allRoles" var="r" status="st">
						<tr>
							<td><s:property value="#st.count" /></td>
							<td><s:property value="#r.roleName" /></td>
							<td><s:property value="#r.roleValue" /></td>
							<td><s:a action="RoleAction_editRole?id=%{#r.id}">修改</s:a></td>
							<td><s:a action="RoleAction_deleteRole?id=%{#r.id}">删除</s:a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		</article> </section>
		<!-- end of main -->
	</div>
</body>
</html>