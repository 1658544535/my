<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>demo页面</title>
<link href="/css/content.css" media="screen"
	rel="stylesheet">
<script type="text/javascript"
	src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript"
	src="/js/common.js"></script>
</head>
<body>
<div class="top-div">
		<div class="top-title-div">
			<span class="top-title">demo页面</span>
		</div>
		<hr class="top-hr">
		<div class="top-main-div">
			<div class="top-main-title-div">
				<span class="top-main-title"></span>
			</div>
			<div class="top-main-content-div">
			<table class="top-mian-content-table">
					<tr>
						<td>菜单ID:</td>
						<td><input></td>
						<td>菜单名称:</td>
						<td><input></td>
					</tr>
					<tr>
						<td>test:</td>
						<td><input></td>
						<td>用户出生日期</td>
						<td><input id="userBirthday" name="userBirthday" type="text">
						<img id="calendarImg" src="/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle" /></td>
					</tr>
				</table>
			</div>
			<div class="top-main-bottom-div">
				<span class="top-main-bottom-button"><button>查询</button></span>
			</div>
		</div>
	</div>
	<div class="center-div">
		<div class="center-main-div">
			<display:table name="menuList" class="simple" pagesize="10"
				requestURI="gomanagemenu.do">
				<display:column property="menuId" title="菜单ID" />
				<display:column property="menuName" title="菜单名称" />
				<display:column property="menuPath" title="菜单路径" />
			</display:table>
		</div>
		<div class="center-main-botomm-div">
			<span class="center-main-bottom-button1"><a href="goaddmenu.do?level=1">新增主菜单</a></span>
			<span class="center-main-bottom-button2"><a href="goaddmenu.do?level=2">新增子菜单</a></span>
		</div>
	</div>
	<div class="bottom-div"></div>
</body>
</html>