<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>修改商品类型</title>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">商品管理</a> &gt; <a href="productTypeManage.do">商品类型管理</a> &gt; <a
				href="#">修改商品类型</a>
		</div>
	
	<div class="h15"></div>
	<div id="fullbg"></div>
	<div id="sending" style="width: 100%; height: 40px">
		<p class="close"></p>
		<p align="center">
			<img alt="加载中..." src="image/loading.gif" height="30px" width="30px">
		</p>
	</div>
	<form action="productTypeUpdate1.do" method="post" id="from1" enctype="multipart/form-data" >
		<input type="hidden" name="typeName" id="" value="${typeName}">
		<input type="hidden" name="productTypePojo.id" id="productTypePojo.id"
						value="${productTypePojo.id}"> 
		<input type="hidden" name="productTypePojo.pid" id="productTypePojo.pid"
						value="${productTypePojo.pid}"> 
		<input type="hidden" name="productTypePojo.level" id="productTypePojo.level"
						value="${productTypePojo.level}">
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table">
				<tr>
					<td align="right" width="20%" class="grey">类型名称:</td>
					<td><input class="floatLeft" type="text"
						name="productTypePojo.name" value="${productTypePojo.name}" id="name"> <span
						id="name_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">排序:</td>
					<td><input class="floatLeft" type="text"
						name="productTypePojo.sorting" value="${productTypePojo.sorting}" id="sorting"> <span
						id="sorting_mgId"></span></td>
				</tr>
		      <tr>
		        <td align="right" class="grey"  width="15%">状态:</td>
		        <td width="35%"><select name="productTypePojo.status" id="ticketType"  class="floatLeft">
		        						<c:forEach items="${status}" var="status">
												<option value="${status.value}"<c:if test="${productTypePojo.status==status.value}">selected="selected" </c:if>>${status.name}</option>
										</c:forEach>
						    </select></td>
		      </tr>
		      <%-- <tr>
				<td align="right" class="grey" width="15%">上传商品类型图片:</td>
				<td width="35%"><input type="file" name="upfile2"
					class="floatLeft" id="ticketName2"></br>
					<font color="#df434e">上传图片规格（90 * 90）,勿超过200k</font>
					<img  height="100px" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/productType/${productTypePojo.image}">
					<span id="shopLongitude_msgIddd2"></span></td>
			</tr> --%>
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