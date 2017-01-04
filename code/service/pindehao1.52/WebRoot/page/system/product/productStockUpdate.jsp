<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>修改商品图片</title>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="productManage.do">商品管理</a> &gt; <a href="productStockManage.do?productStockPojo.productId=<s:property value='productStockPojo.productId'/>">商品图片管理</a> &gt; <a
				href="#">修改商品图片</a>
		</div>
	
	<div class="h15"></div>
	<div id="fullbg"></div>
	<div id="sending" style="width: 100%; height: 40px">
		<p class="close"></p>
		<p align="center">
			<img alt="加载中..." src="image/loading.gif" height="30px" width="30px">
		</p>
	</div>
	<form action="productStockUpdate.do" method="post" id="from1" enctype="multipart/form-data">
		<input type="hidden" name="productStockPojo.id" id="productStockPojo.id"
						value="${productStockPojo.id}"> 
		<input type="hidden" name="productStockPojo.productId" id="productStockPojo.productId"
						value="${productStockPojo.productId}">
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table">
				<tr>
					<td align="right" width="20%" class="grey">SKU:</td>
					<td><input class="floatLeft" type="text"
						name="productStockPojo.sku" value="${productStockPojo.sku}" id="sku"> <span
						id="sku_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">库存编号:</td>
					<td><input class="floatLeft" type="text"
						name="productStockPojo.stockNo" value="${productStockPojo.stockNo}" id="stockNo"> <span
						id="stockNo_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">重量:</td>
					<td><input class="floatLeft" type="text"
						name="productStockPojo.weight" value="${productStockPojo.weight}" id="weight"> <span
						id="weight_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">尺寸:</td>
					<td><input class="floatLeft" type="text"
						name="productStockPojo.size" value="${productStockPojo.size}" id="size"> <span
						id="size_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">颜色:</td>
					<td><input class="floatLeft" type="text"
						name="productStockPojo.color" value="${productStockPojo.color}" id="color"> <span
						id="color_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">排序:</td>
					<td><input class="floatLeft" type="text"
						name="productStockPojo.sorting" value="${productStockPojo.sorting}" id="sorting"> <span
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

</html>