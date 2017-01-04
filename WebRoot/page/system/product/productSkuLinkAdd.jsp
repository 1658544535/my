<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>新增商品SKU</title>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="productManage.do">商品管理</a> &gt; <a href="productSkuLinkManage.do?productSkuLinkPojo.productId=${productSkuLinkPojo.productId}">商品SKU管理</a> &gt; <a
				href="#">新增商品SKU</a>
		</div>
	<!--
	<div class="h15"></div>
	<div id="fullbg"></div>
	<div id="sending" style="width: 100%; height: 40px">
		<p class="close"></p>
		<p align="center">
			<img alt="加载中..." src="image/loading.gif" height="30px" width="30px">
		</p>
	</div>
	-->
	<form action="productSkuLinkAdd.do" method="post" id="from1" enctype="multipart/form-data">
		<input type="hidden" name="productSkuLinkPojo.productId" id="productSkuLinkPojo.productId" value="${productSkuLinkPojo.productId}"> 
		<input type="hidden" name="productSkuLinkPojo.id" id="productSkuLinkPojo.id" value="${productSkuLinkPojo.id}"> 
		<input type="hidden" name="productSkuLinkPojo.type" id="productSkuLinkPojo.type" value="${productSkuLinkPojo.type}">
		<input type="hidden" name="productSkuLinkPojo.activityId" id="productSkuLinkPojo.activityId" value="${productSkuLinkPojo.activityId}">
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table">
			<!--
			<tr>
			<td align="right" class="grey">是否活动：</td>
			<td>
				<select name="productSkuLinkPojo.type" id="type" class="floatLeft" >
						<option value="0">否</option>
						<option value="1">是</option>
				</select>
			</td>
			<td><span id="type_mgId"></span></td>
			</tr>
			-->
		 	<tr>
				 <td align="right" class="grey">颜色：</td>
					<td>
						<select name="productSkuLinkPojo.skuColorId" id="skuColorId"
							class="floatLeft" onchange="selectColorChange();">
								<option value="">----请选择----</option>
								<c:forEach items="${colorAttribute}" var="colorAttribute">
										<option value="${colorAttribute.id}" image="${colorAttribute.image}">${colorAttribute.value}</option>
								</c:forEach>
						</select>
					</td>
					<td><span id="skuColorId_mgId"></span></td>
			</tr>
			<tr>
					<td align="right" class="grey">规格：</td>
					<td>
						<select name="productSkuLinkPojo.skuFormatId" id="skuFormatId"
							class="floatLeft" onchange="selectFormatChange();">
								<option value="">----请选择----</option>
								<c:forEach items="${formatAttribute}" var="formatAttribute">
										<option value="${formatAttribute.id}" image1="${formatAttribute.image}">${formatAttribute.value}</option>
								</c:forEach>
						</select>
					</td>		
					<td><span id="skuFormatId_mgId"></span></td>
				</tr> 
				<tr>
					<td align="right" width="20%" class="grey">库存总量：</td>
					<td><input class="floatLeft" type="text"
						name="productSkuLinkPojo.stockNum" id="stockNum"></td>
					<td><span id="stockNum_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">剩余库存：</td>
					<td><input class="floatLeft" type="text"
						name="productSkuLinkPojo.stock" id="stock"></td>
					<td><span id="stock_mgId"></span></td>
			
				<!--<td align="right" width="20%" class="grey">排序:</td>
					<td><input class="floatLeft" type="text"
						name="productSkuLinkPojo.sorting" id="sorting"> <span
						id="sorting_mgId"></span></td>-->
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">价格：</td>
					<td><input class="floatLeft" type="text"
						name="productSkuLinkPojo.price" id="price"></td>
					<td><span id="price_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商家编码：</td>
					<td><input class="floatLeft" type="text"
						name="productSkuLinkPojo.businessCode" id="price"></td>
					<td><span id="businessCode_mgId"></span></td>
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
var skuColorId =new tt.Field(" 颜色","productSkuLinkPojo.skuColorId").setMsgId("skuColorId_mgId");
var skuFormatId =new tt.Field(" 规格","productSkuLinkPojo.skuFormatId").setMsgId("skuFormatId_mgId");
var stockNum =new tt.Field(" 库存总量","productSkuLinkPojo.stockNum").setMsgId("stockNum_mgId");
var stock =new tt.Field(" 剩余库存","productSkuLinkPojo.stock").setMsgId("stock_mgId");
var price =new tt.Field(" 价格","productSkuLinkPojo.price").setMsgId("price_mgId");
var businessCode =new tt.Field(" 商家编码","productSkuLinkPojo.businessCode").setMsgId("businessCode_mgId");

tt.vf.req.add(skuColorId,skuFormatId,stockNum,stock,price);
new tt.NRV().set(0, '++').add(stockNum,stock);
tt.vf.int.add(stockNum,stock);
new tt.CV().add(stock).set('n', '<=', stockNum);
new tt.NRV().set(0, '++').add(price);

$(document).ready(function() {
	$("#submitId").click(function(){
		if(tt.validate()){		
			document.getElementById("from1").submit();
		}
	});
});
function selectColorChange() {
	var sel = document.getElementById("skuColorId");
	var index =sel.selectedIndex;
	var images =sel.options[index].getAttribute("image");  
	document.getElementById("skuColorId_mgId").innerHTML="<img class='floatLeft' src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/small/"+images+"' height='50px' />";
}
/*function selectFormatChange() {
	var sel = document.getElementById("skuFormatId");
	var index =sel.selectedIndex;
	var images =sel.options[index].getAttribute("image1");  
	document.getElementById("skuFormatId_mgId").innerHTML="<img class='floatLeft' src='../../../upfiles/product/small/"+images+"' height='50px' />";
}*/
</script>
</html>
