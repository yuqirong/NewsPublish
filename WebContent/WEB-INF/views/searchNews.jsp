<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果</title>
<link href='<s:url value="/css/style.css" />' rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src='<s:url value="/js/jquery-1.7.2.js" />' /></script>
<script type="text/javascript" src='<s:url value="/js/searchNews.js" />' /></script>
</head>
<body>
	<table border="1" width=80% bordercolor="#43A3E9" align="center">
		<tr>
			<td>
				<h2>搜索结果</h2>
			</td>
		</tr>
		<tr>
			<td>
				<fieldset>
					<legend>新闻信息查询</legend>
					<s:form action="NewsAction_getSearchedNews" method="post">
						<s:select name="sw" list="#{'0':'标题','1':'正文'}" listKey="key"
							listValue="value" value="#request.sw"></s:select>
						<input type="text" name="keywords" placeholder="请输入关键词查询"
							value='<s:property value="#session.keywords" />' />
						<input type="submit" value="查询" class="right-button01" />
					</s:form>
				</fieldset>
			</td>
		</tr>
	</table>
	<table border="1" width="80%" bordercolor="#43A3E9" align="center">
		<tbody align="center" valign="middle" id="tab">
			<s:if test="#request.page.list ==null || #request.page.list.size ==0">
				<tr>
					<td>对不起,没有找到相关内容,请更换关键字后重试!</td>
				</tr>
			</s:if>
			<s:else>
				<s:iterator value="#request.page.list" var="n" status="status">
					<tr>
						<td style="padding-left: 10px;" valign="top">
							<ul>
								<li><s:a action="NewsAction_getViewNews?id=%{#n.id}">
										<s:property value="#n.title" escape="false" />
									</s:a></li>
								<li><s:property value="#n.content" escape="false" /></li>
								<li><s:date name="#n.createTime"
										format="yyyy-MM-dd HH:mm:ss" /></li>
							</ul>
						</td>
					</tr>
				</s:iterator>
			</s:else>
		</tbody>
	</table>
	<br>
	<br>
	<center>
		<s:form name="f">
			<input type="hidden" id="q"
				value='<s:property value="%{#request.keywords}"/>'>
			<input type="hidden" id="p"
				value='<s:property value="%{#request.searchWhich}"/>'>
			<s:a href="NewsAction_getSearchedNews?page=1&sw=%{#request.sw}">首页</s:a>
			<s:if test="#request.page.currentPage!=1">
				<s:a
					href="NewsAction_getSearchedNews?page=%{#request.page.currentPage-1}&sw=%{#request.sw}">上一页</s:a>
			</s:if>
			<s:if test="#request.page.currentPage != #request.page.pageCount">
				<s:a
					href="NewsAction_getSearchedNews?page=%{#request.page.currentPage+1}&sw=%{#request.sw}"
					onclick="f.submit()">下一页</s:a>
			</s:if>
			<s:a
				href="NewsAction_getSearchedNews?page=%{#request.page.pageCount}&sw=%{#request.sw}">尾页</s:a>
		当前第
		<s:property value="#request.page.currentPage" />
		页,总共
		<s:property value="#request.page.pageCount" />
		页
		</s:form>
	</center>
	<s:include value="footer.html" />
</body>
</html>
