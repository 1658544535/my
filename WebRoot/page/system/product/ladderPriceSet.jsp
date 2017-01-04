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
<script type="text/javascript">
$(function(){
	$("#priceBody").html("");
	$.each(${productPojo.ladderPrice}, valuePrice);
});

function valuePrice(){
	$("#priceBody").append(
				"<tr><td><input type='checkbox' class='cb_price' /></td>"+
				"<td align='right' width='10%' class='grey'>最小数量：</td>"+
				"<td><input type='text' name='min' value='"+this.min+"'></td>"+
				"<td align='right' width='10%' class='grey'>最大数量：</td>"+
				"<td><input type='text' name='max' value='"+this.max+"'></td>"+
				"<td align='right' width='10%' class='grey'>价格：</td>"+
				"<td><input type='text' name='prices' value='"+this.price+"'><font size='1' color='red'>&nbsp;&nbsp;&nbsp;&nbsp;注：价格只保留小数点后一位</font></td></tr>");
}
function addPrice(){
	$("#priceBody").append(
				"<tr><td><input type='checkbox' class='cb_price' /></td>"+
				"<td align='right' width='15%' class='grey'>最小数量：</td>"+
				"<td><input type='text' name='min' value='0'></td>"+
				"<td align='right' width='15%' class='grey'>最大数量：</td>"+
				"<td><input type='text' name='max' value='0'></td>"+
				"<td align='right' width='20%' class='grey'>价格：</td>"+
				"<td><input type='text' name='prices' value='0'><font size='1' color='red'>&nbsp;&nbsp;&nbsp;&nbsp;注：价格只保留小数点后一位</font></td></tr>");
}
function delPrice(){
	$(".cb_price:checked").each(function(){
			$(this).parent().parent().remove();
	});
}
</script>
<title>修改商品图片</title>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="productManage.do">商品管理</a> &gt; <a href="ladderPriceManage.do">商品价格阶梯设置</a> &gt; <a
				href="#">价格阶梯设置</a>
		</div>
	
	<div class="h15"></div>
	<div id="fullbg"></div>
	<div id="sending" style="width: 100%; height: 40px">
		<p class="close"></p>
		<p align="center">
			<img alt="加载中..." src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/image/loading.gif" height="30px" width="30px">
		</p>
	</div>
	<a class="Add_btn"  onclick="addPrice()" >添加价格</a>
	<a class="delAll_btn"  onclick="delPrice()" >删除所选价格</a>
	<form action="setLadderPrice.do" method="post" id="from1" enctype="multipart/form-data">
		<input type="hidden" name="productPojo.id" id="productPojo.id" value="${productPojo.id}">
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table" id="priceBody">
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