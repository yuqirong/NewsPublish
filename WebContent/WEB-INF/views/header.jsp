<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" width="100%" bordercolor="#43A3E9" align="center">
		<tr align="center">
			<td><span style="font-size: 26px">新闻频道</span></td>
		</tr>
		<tr>
			<td align="left"><s:include value="weather.jsp" /></td>
		</tr>
		<tr>
			<td align="right"><s:if test="#session.user.id != null">
					<font size="2"> Hello: <s:property
							value="#session.user.username" />&nbsp;&nbsp;<a
						href="UserAction_loginout">退出</a>&nbsp;&nbsp;
					</font>
				</s:if> <s:else>
					<s:a href="/NewsPublish/LoginAction_login">点击登录</s:a>&nbsp;&nbsp;&nbsp;&nbsp;<s:a
						href="/NewsPublish/RegAction_reg">注册</s:a>&nbsp;&nbsp;
				</s:else></td>
		</tr>
	</table>
	<fieldset>
		<legend>新闻信息查询</legend>
		<s:form action="NewsAction_getSearchedNews" method="post">
			<s:select name="sw" list="#{'0':'标题','1':'正文'}" listKey="key"
				listValue="value"></s:select>
			<input type="text" name="keywords" placeholder="请输入关键词查询" />
			<input type="submit" value="查询" class="right-button01" />
		</s:form>
	</fieldset>
	<div style="width: 100%">
		<div>
			<a href="">科研新知</a> &nbsp; <a href="">权威发布</a> &nbsp; <a href="">聚焦医改</a>
			&nbsp; <a href="">健康奇闻</a> &nbsp; <a href="">健康ICU</a>
		</div>
	</div>
	<div style="width: 100%">
		<div>
			<a href="">疾病信息</a> &nbsp; <a href="">医院动态</a> &nbsp; <a href="">健康新闻</a>
			&nbsp; <a href="">行业峰会</a> &nbsp; <a href="">社会万象</a>
		</div>
	</div>
</body>
</html>