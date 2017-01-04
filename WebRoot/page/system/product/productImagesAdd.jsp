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
<title>新增商品</title>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="productManage.do?productPojo.userId=${productImagesPojo.userId}">商品管理</a> &gt; <a href="productImagesManage.do?productImagesPojo.productId=${productImagesPojo.productId}&productImagesPojo.userId=${productImagesPojo.userId}">商品图片管理</a> &gt; <a
				href="#">新增商品图片</a>
		</div>
	
	<div class="h15"></div>
	<div id="fullbg"></div>
	<div id="sending" style="width: 100%; height: 40px">
		<p class="close"></p>
		<p align="center">
			<img alt="加载中..." src="image/loading.gif" height="30px" width="30px">
		</p>
	</div>
	<form action="productImagesAdd.do" method="post" id="from1" enctype="multipart/form-data">
		<input type="hidden" name="productImagesPojo.productId" id="productImagesPojo.productId"
						value="${productImagesPojo.productId}">
		<input type="hidden" name="productImagesPojo.userId" id="productImagesPojo.userId"
						value="${productImagesPojo.userId}"> 
						
		<input type="hidden" name="productImagesPojo2.productId" id="productImagesPojo2.productId"
						value="${productImagesPojo.productId}">
		<input type="hidden" name="productImagesPojo2.userId" id="productImagesPojo2.userId"
						value="${productImagesPojo.userId}"> 
						
		<input type="hidden" name="productImagesPojo3.productId" id="productImagesPojo3.productId"
						value="${productImagesPojo.productId}">
		<input type="hidden" name="productImagesPojo3.userId" id="productImagesPojo3.userId"
						value="${productImagesPojo.userId}"> 
						
		<input type="hidden" name="productImagesPojo4.productId" id="productImagesPojo4.productId"
						value="${productImagesPojo.productId}">
		<input type="hidden" name="productImagesPojo4.userId" id="productImagesPojo4.userId"
						value="${productImagesPojo.userId}"> 
						
		<input type="hidden" name="productImagesPojo5.productId" id="productImagesPojo5.productId"
						value="${productImagesPojo.productId}">
		<input type="hidden" name="productImagesPojo5.userId" id="productImagesPojo5.userId"
						value="${productImagesPojo.userId}"> 
						
		<input type="hidden" name="productImagesPojo6.productId" id="productImagesPojo6.productId"
						value="${productImagesPojo.productId}">
		<input type="hidden" name="productImagesPojo6.userId" id="productImagesPojo6.userId"
						value="${productImagesPojo.userId}"> 																				
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table">
				<tr>
					<td align="right" width="20%" class="grey">商品图片:</td>
					<td><input type="file" name="upfile" class="floatLeft"><font color="red">图片建议尺寸：300*300</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">排序:</td>
					<td><input class="floatLeft" type="text"
						name="productImagesPojo.sorting" id="sorting"> <span
						id="sorting_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品图片:</td>
					<td><input type="file" name="upfile2" class="floatLeft"><font color="red">图片建议尺寸：300*300</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">排序:</td>
					<td><input class="floatLeft" type="text"
						name="productImagesPojo2.sorting" id="sorting"> <span
						id="sorting_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品图片:</td>
					<td><input type="file" name="upfile3" class="floatLeft"><font color="red">图片建议尺寸：300*300</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">排序:</td>
					<td><input class="floatLeft" type="text"
						name="productImagesPojo3.sorting" id="sorting"> <span
						id="sorting_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品图片:</td>
					<td><input type="file" name="upfile4" class="floatLeft"><font color="red">图片建议尺寸：300*300</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">排序:</td>
					<td><input class="floatLeft" type="text"
						name="productImagesPojo4.sorting" id="sorting"> <span
						id="sorting_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品图片:</td>
					<td><input type="file" name="upfile5" class="floatLeft"><font color="red">图片建议尺寸：300*300</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">排序:</td>
					<td><input class="floatLeft" type="text"
						name="productImagesPojo5.sorting" id="sorting"> <span
						id="sorting_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品图片:</td>
					<td><input type="file" name="upfile6" class="floatLeft"><font color="red">图片建议尺寸：300*300</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">排序:</td>
					<td><input class="floatLeft" type="text"
						name="productImagesPojo6.sorting" id="sorting"> <span
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