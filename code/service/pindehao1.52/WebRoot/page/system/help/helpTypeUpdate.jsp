<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>

<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/common.js"></script>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/commonLoad.css" media="screen"
	rel="stylesheet">
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/sysDict/sysDictCommon.js"></script>

<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<title>修改帮助类型</title>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">帮助/FAQ系统</a> &gt; <a href="productTypeManage.do">帮助分类管理</a> &gt; <a
				href="#">修改帮助类型</a>
		</div>
	
	<div class="h15"></div>
	<div id="fullbg"></div>
	<div id="sending" style="width: 100%; height: 40px">
		<p class="close"></p>
		<p align="center">
			<img alt="加载中..." src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/image/loading.gif" height="30px" width="30px">
		</p>
	</div>
	<form action="helpTypeUpdate.do" method="post" id="from1">
		<input type="hidden" name="helpTypePojo.id" id="helpTypePojo.id"
						value="${helpTypePojo.id}"> 
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table">
				<tr>
				<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%">
						<select name="helpTypePojo.status" id="">
								<s:iterator value="statusSysDictList">
									<option value="<s:property value="value"/>"
									<s:if test="value == helpTypePojo.status">selected="selected"</s:if>><s:property 
											value="name" />
									</option>
								</s:iterator>
						</select>
						<div id="ticketType_mgId"></div></td>
					<td align="right" width="20%" class="grey">类型名称:</td>
					<td><input class="floatLeft" type="text"
						name="helpTypePojo.name" value="${helpTypePojo.name}" id="name"> <span
						id="name_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">排序:</td>
					<td><input class="floatLeft" type="text"
						name="helpTypePojo.sorting" value="${helpTypePojo.sorting}" id="sorting"> <span
						id="sorting_mgId"></span></td>
				</tr>
			</table>
		</div>

		<div class="Btn_div">
			<input name="" type="button" value="返回" class="back_btn"
				onclick="window.history.back()" /><input id="submitId"
				name="submitId" type="button" class="ok_btn" value="提 交" />
		</div>
	</form>
	</div>
</body>
<script>
	var sysDictName = new tt.Field(" 类型名称 ", "productTypePojo.name")
			.setMsgId("name_mgId");
	tt.vf.req.add(name);
	new tt.LV().set(0, 50).add(name);
</script>
</html>