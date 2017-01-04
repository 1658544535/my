<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>

<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<title>新增下属字典</title>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/common.js"></script>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/commonLoad.css" media="screen" rel="stylesheet">
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/sysDict/sysDictCommon.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">系统管理</a> &gt; <a href="gomanagemead.do">系统字典管理</a> &gt; <a
				href="#">新增系统下屬字典</a>
		</div>
	
	<div class="h15"></div>
	<div id="fullbg"></div>
	<div id="sending" style="width: 100%;height: 40px">
		<p class="close"></p>
		<p align="center">
			<img alt="加载中..." src="image/loading.gif" height="30px"
				width="30px">
		</p>
	</div>
		<form action="gomanageSysDictBranchAdd.do" method="post" id="from1">
	<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table">
				<tr>
					<td align="right" width="20%" class="grey">所属父类:</td>
					<td><input type="text" value="<s:property value="sysDictPojo.sysDictName"/>" disabled="disabled"/>
						<input type="hidden" name="sysDictPojo.sysDictLevel" value="<s:property value="sysDictPojo.sysDictLevel"/>">
						<input type="hidden" name="sysDictPojo.sysDictType" value="<s:property value="sysDictPojo.sysDictId"/>">
						
					</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">字典名称:</td>
					<td><input class="floatLeft" type="text"
						name="sysDictPojo.sysDictName" id="sysDictName"> <span
						id="sysDictName_mgId"></span></td>
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
	var sysDictName =new tt.Field(" 字典名称 ","sysDictPojo.sysDictName").setMsgId("sysDictName_mgId");
	tt.vf.req.add(sysDictName);
	new tt.LV().set(0, 50).add(sysDictName);

</script>
</html>