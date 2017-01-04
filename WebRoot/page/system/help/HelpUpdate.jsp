<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baidu.ueditor.ActionEnter"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改帮助信息</title>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
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
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">帮助/FAQ系统</a> &gt; <a href="helpManage.do">帮助信息管理</a> &gt; <a
				href="#">修改帮助信息</a>
		</div>
		
	<!-- 实现公共的加载中方法 -->
	<div id="fullbg"></div>
	<div id="sending" style="width: 100%; height: 40px">
		<p class="close"></p>
		<p align="center">
			<img alt="加载中..." src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/image/loading.gif" height="30px" width="30px">
		</p>
	</div>

	<form action="helpManageUpdate.do" method="post" id="from1">
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table">
				<tr>
					<td align="right" width="20%" class="grey">信息名称</td>
					<td><input class="floatLeft" type="text"
						name="helpPojo.title" id="helpname"
						value="<s:property value="helpPojo.title"/>"> 
						<input type="hidden" name="helpPojo.id" id="helpPojo.helpid"
						value="<s:property value="helpPojo.id"/>"> 
						<span id="helpTitle_mgId"></span></td>
					<td align="right" width="20%" class="grey">帮助类型</td>
					<td>
						<!--${helpPojo.helpTypeName} -->
						<select class="floatLeft" disabled="disabled" name="helpPojo.typeId" id="typeId">
						${typeStr}
						</select>
						<span id="productTypeId_mgId"></span>
					</td>
					<!--  
					<td>
					<select name="helpPojo.type" id="">
						 		<option value="">全部</option>
								<s:iterator value="helpTypeList">
									<option value="<s:property value="value"/>"
									<s:if test="value == helpPojo.type">selected="selected"</s:if>><s:property
											value="name" />
									</option>
								</s:iterator>
					</select>
					<span id="helpType_mgId"></span>
					</td>
					-->
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%">
						<select name="helpPojo.status" id="">
								<s:iterator value="statusSysDictList">
									<option value="<s:property value="value"/>"
									<s:if test="value == helpPojo.status">selected="selected"</s:if>><s:property 
											value="name" />
									</option>
								</s:iterator>
						</select>
						<div id="ticketType_mgId"></div></td>
						<td align="right" width="20%" class="grey">排序:</td>
					<td><input class="floatLeft" type="text"
						name="helpPojo.sorting" id="sorting" value="<s:property
								value="helpPojo.sorting" />"> <span
						id="helpSorting_mgId"></span></td>
						</tr>
					<!--<tr>
						<td align="right" class="grey" width="15%">是否常见问题:</td>
						<td width="35%">
						<select name="helpPojo.isUsual" id="">
								<s:iterator value="yesornotSysDictList">
									<option value="<s:property value="value"/>"
									<s:if test="value == helpPojo.isUsual">selected="selected"</s:if>><s:property 
											value="name" />
									</option>
								</s:iterator>
						</select>
						<div id="ticketType_mgId"></div></td>
						<td align="right" class="grey" width="15%">是否热门问题:</td>
						<td width="35%">
						<select name="helpPojo.isHot" id="">
								<s:iterator value="yesornotSysDictList">
									<option value="<s:property value="value"/>"
									<s:if test="value == helpPojo.isHot">selected="selected"</s:if>><s:property 
											value="name" />
									</option>
								</s:iterator>
						</select>
						<div id="ticketType_mgId"></div></td>
						</tr>-->
				<tr>
						<td width="65%" colspan="4">
						<textarea rows="5" cols="80" name="helpPojo.content" class="floatLeft" id="content"><s:property
								value="helpPojo.content" /></textarea>
						<script type="text/javascript">
							var ue = UE.getEditor("content");
						</script>
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
	var helpTitle = new tt.Field(" 信息名称 ", "helpPojo.title")
			.setMsgId("helpTitle_mgId");
	var helpContent = new tt.Field(" 信息内容 ", "helpPojo.content")
			.setMsgId("helpContent_mgId");
	var helpType = new tt.Field(" 所属类型 ", "helpPojo.type")
	.setMsgId("helpType_mgId");
	tt.vf.req.add(helpTitle,helpContent,helpType);
	new tt.LV().set(0, 100).add(helpTitle);
</script>
</html>