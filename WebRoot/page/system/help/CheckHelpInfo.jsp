<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核帮助信息</title>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/common.js"></script>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/commonLoad.css" media="screen" rel="stylesheet">
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">帮助/FAQ系统</a> &gt; <a href="#">帮助信息管理</a> &gt;
			<a href="#">审核帮助信息</a>
		</div>

		<div class="h15"></div>
		<!-- 实现公共的加载中方法 -->
		<div id="fullbg"></div>
		<div id="sending" style="width: 100%; height: 40px">
			<p class="close"></p>
			<p align="center">
				<img alt="加载中..." src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/image/loading.gif" height="30px" width="30px">
			</p>
		</div>

		<form action="checkHelpInfo.do" method="post" id="from1">
			<div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<input type="hidden" name="helpPojo.id" id="shopLatitude"
						value="${helpPojo.id}" class="inputText">
					<tr>
						<td align="right" width="20%" class="grey">信息名称</td>
						<td><input class="floatLeft" type="text"
						name="helpPojo.title" id="helpname"
						value="<s:property value="helpPojo.title"/>">
							<span id="helptTitle_mgId"></span>
						</td>
						<td align="right" width="20%" class="grey">所属类型</td>
						<td><input class="floatLeft" type="text"
						name="helpPojo.helpTypeName" id="helpTypeName"
						value="<s:property value="helpPojo.helpTypeName"/>">
							<span id="helptTitle_mgId"></span>
						</td>
						<!-- 
							<td>
								<s:iterator value="helpTypeList">
									<s:if test="value == helpPojo.type">
										<input class="floatLeft" type="text" name="" id="" value="<s:property value="name" />">
									</s:if>
								</s:iterator>
								<span id="helpType_mgId"></span>
							</td>
						 -->
					</tr>
					<tr>
						<td align="right" width="20%" class="grey">信息内容</td>
						<td colspan="3"><textarea class="floatLeft" rows="8" cols="90"
							name="helpPojo.content" id="helpcontent"><s:property
								value="helpPojo.content" /></textarea> 
							<span id="helpContent_mgId"></span>
					    </td>
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
    //实例化文本框和输出框
	var helpTitle = new tt.Field(" 信息名称 ", "helpPojo.title")
			.setMsgId("helptTitle_mgId");
	var helpContent = new tt.Field(" 信息内容 ", "helpPojo.content")
			.setMsgId("helpContent_mgId");
	var helpType = new tt.Field(" 所属类型 ", "helpPojo.type")
			.setMsgId("helpType_mgId");
	//tt.vf.req.add(xxx); xxx为必输项
	tt.vf.req.add(helpTitle, helpContent, helpType);
	//字符长度验证
	new tt.LV().set(0, 100).add(helpTitle);////说明：最少输入0个字符最多输入100个字符
	new tt.LV().set(0, 2000).add(helpContent);
</script>
</html>