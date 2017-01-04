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
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">帮助/FAQ系统</a> &gt; <a href="productTypeManage.do">意见反馈</a> &gt; <a
href="#">意见反馈编辑</a>
		</div>
	<form action="updateFeedBackOk.do" method="post" id="from1">
		<input type="hidden" name="feedBackPojo.id" id="feedBackPojo.id"
						value="${feedBackPojo.id}"> 
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table">
				<tr>
				<td align="right" class="grey" width="15%">日期:</td>
				<td><label class="floatLeft" name="feedBackPojo.createDateStr">${feedBackPojo.createDateStr}<label></td>
					<td align="right" width="20%" class="grey">用户:</td>
						<td><label class="floatLeft" name="feedBackPojo.name">${feedBackPojo.name}<label></td>
				</tr>
				<tr>
				<td align="right" class="grey">意见类型：</td>
				<s:if test="feedBackPojo.type==1"><td>投诉</td></s:if>
				<s:if test="feedBackPojo.type==2"><td>建议</td></s:if>
					<td align="right" width="20%" class="grey">邮箱:</td>
                <td><label class="floatLeft" name="feedBackPojo.email">${feedBackPojo.email}<label></td>
				</tr>
				<tr>
				<td align="right" class="grey" width="15%">联系电话:</td>
                <td><label class="floatLeft" name="feedBackPojo.telephone">${feedBackPojo.telephone}<label></td>
					<td align="right" width="20%" class="grey">反馈内容:</td>
					<td><label class="floatLeft" name="feedBackPojo.content">${feedBackPojo.content}<label></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">处理结果:</td>
						<td><textarea class="floatLeft" rows="6" cols="50" name="feedBackPojo.remarks" id="content">${feedBackPojo.remarks}</textarea></td>
					<td align="right" class="grey">状态：</td>
			            <td><select name="feedBackPojo.status" id="feedBackPojo.status" class="floatLeft"> 
							<option value="0"<s:if test="feedBackPojo.status==0">selected="selected"</s:if>>未审核</option>
							<option value="1"<s:if test="feedBackPojo.status==1">selected="selected"</s:if>>已审核</option>
				    		</select>
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
</script>
</html>