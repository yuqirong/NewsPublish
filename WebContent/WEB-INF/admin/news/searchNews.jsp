<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%pageContext.setAttribute("currentMenu", "bpm-process");%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
li {list-style-type:none;}
</style>
<%@include file="/common/meta.jsp"%>
<title>搜索结果</title>
<%@include file="/common/s.jsp"%>
</head>
<body>
	<%@include file="/header/bpm-workspace.jsp"%>

	<div class="row-fluid">
		<%@include file="/menu/bpm-workspace.jsp"%>

		<!-- start of main -->
		<section id="m-main" class="span10" style="float: right">
		<div>
			<article class="m-widget"> <header class="header">
			<h4 class="title">查询</h4>
			<div class="ctrl">
				<a class="btn"><i id="cal-infoSearchIcon"
					class="icon-chevron-up"></i></a>
			</div>
			</header>
			<div id="cal-infoSearch" class="content content-inner">

				<form method="post" action="NewsAction_searchNews"
					class="form-inline">
					<label for="cal-info_name">搜索:</label>
					<s:select name="sw" list="#{'0':'标题','1':'正文'}" listKey="key"
						listValue="value" value="#request.sw" cssClass="select-size"></s:select>
					<input type="text" name="keywords" placeholder="请输入关键词查询"
						value='<s:property value="#session.keywords" />' />
					<button class="btn btn-small a-search" type="submit">查询</button>
					&nbsp;
				</form>
			</div>
			</article>
		</div>

		<article class="m-widget"> <header class="header">
		<h4 class="title">搜索结果</h4>
		</header>
		<div class="content">
			<table width="100%">
				<tbody>
					<s:if
						test="#request.page.list ==null || #request.page.list.size ==0">
						<tr>
							<td align="center"><h4>对不起,没有找到相关内容,请更换关键字后重试!</h4></td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="#request.page.list" var="n" status="status">
							<tr>
								<td>
									<ul>
										<li><s:a action="NewsAction_getNews?id=%{#n.id}">
												<s:property value="#n.title" escape="false" />
											</s:a></li>
										<li><s:date name="#n.createTime"
												format="yyyy-MM-dd HH:mm:ss" /></li>
										<li><s:property value="#n.content" escape="false" /></li>
									</ul>
									<br>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
		</div>
		</article> <article>
		<div class="m-page-info pull-left">
			共
			<s:property value="#request.page.totalCount" />
			条记录
		</div>

		<div class="m-pagination pull-right">
			<s:a href="NewsAction_searchNews?page=1&sw=%{#request.sw}">首页</s:a>
			<s:if test="#request.page.currentPage!=1">
				<s:a
					href="NewsAction_searchNews?page=%{#request.page.currentPage-1}&sw=%{#request.sw}">上一页</s:a>
			</s:if>
			<s:if test="#request.page.currentPage != #request.page.pageCount">
				<s:a
					href="NewsAction_searchNews?page=%{#request.page.currentPage+1}&sw=%{#request.sw}">下一页</s:a>
			</s:if>
			<s:a
				href="NewsAction_searchNews?page=%{#request.page.pageCount}&sw=%{#request.sw}">尾页</s:a>
			当前第
			<s:property value="#request.page.currentPage" />
			页,总共
			<s:property value="#request.page.pageCount" />
			页
		</div>
		<div class="m-clear"></div>
		</article> </section>
		<!-- end of main -->
	</div>

</body>

</html>
