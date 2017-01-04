<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<a href="productSkuLinkManage.do?productSkuLinkPojo.productId=${productSkuLinkPojo.productId}">SKU管理</a> &gt; <a href="">修改颜色/规格</a>
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
	<form action="skuAttributeUpdate.do" method="post" id="form1" enctype="multipart/form-data">
		<input type="hidden" name="productSkuLinkPojo.productId" id="productSkuLinkPojo.productId" value="${productSkuLinkPojo.productId}"> 
		<input type="hidden" name="productSkuLinkPojo.type" id="productSkuLinkPojo.type" value="${productSkuLinkPojo.type}">
		<input type="hidden" name="productSkuLinkPojo.activityId" id="productSkuLinkPojo.activityId" value="${productSkuLinkPojo.activityId}">
		<input type="hidden" name="skuAttributePojo.status" id="skuAttributePojo.status" value="1"> 
		<input type="hidden" name="skuAttributePojo2.status" id="skuAttributePojo2.status" value="1"> 
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table">
			 <tr>
			 <td align="right" class="grey">要修改的颜色：</td>
				<td>
					<select name="skuAttributePojo.id" id="skuColorId"
						class="floatLeft" onchange="selectColorChange();">
							<option value="">----请选择----</option>
							<c:forEach items="${colorAttribute}" var="color">
									<option value="${color.id}" image="${color.image}">${color.value}</option>
							</c:forEach>
					</select>
					<span id="skuColorId_mgId">  </span>
				</td>
				<td align="right" class="grey">要修改的规格：</td>
				<td>
					<select name="skuAttributePojo2.id" id="skuFormatId"
						class="floatLeft" onchange="selectFormatChange();">
							<option value="">----请选择----</option>
							<c:forEach items="${formatAttribute}" var="format">
									<option value="${format.id}" image1="${format.image}">${format.value}</option>
							</c:forEach>
					</select>
					<span id="skuFormatId_mgId">  </span>
				</td>		
			</tr> 
			<tr>
				<td align="right" class="grey">新颜色名称：</td>
				<td> <input class="floatLeft" type="text" name="skuAttributePojo.value" id="value" onchange="valid()">
				<span id="value_mgId"></span></td>
				<td align="right" class="grey">新规格名称：</td>
				<td> <input class="floatLeft" type="text" name="skuAttributePojo2.value" id="value2" onchange="valid()">
				<span id="value2_mgId"></span></td>
			</tr>
			<!--
			<tr>
			<td align="right" width="20%" class="grey">新颜色状态：</td>
			<td><select name="skuAttributePojo.status" id="status">
			<option value="1" selected=selected>已审核</option>
			<option value="0">未审核</option>
			</select>
			 <span id="status_mgId"></span></td>
			<td align="right" width="20%" class="grey">新规格状态：</td>
			<td><select name="skuAttributePojo2.status" id="status2">
			<option value="1" selected=selected>已审核</option>
			<option value="0">未审核</option>
			</select>
			 <span id="status2_mgId"></span></td>
		    </tr>  
		    -->
			<tr>
				<td align="right" width="20%" class="grey">新颜色图片：</td>
				<td>
					<img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/small/${skuAttributePojo.image}" height="100px" />
					<input type="file" class="floatLeft" name="upfile" class="floatLeft" id="userPic">
					<font color="#FF0000">图片建议尺寸：150*150</font>
				</td>
				<!--	
				<td align="right" width="20%" class="grey">新规格图片:</td>
				<td>
					<img  class="floatLeft" src="upfiles/product/small/${skuAttributePojo2.image}" height="100px" />
					<input type="file" class="floatLeft" name="upfile2" class="floatLeft" id="userPic2">
					<font color="#FF0000">图片建议尺寸：150*150</font>
				</td>   
				-->
			</tr>
			<!--	
			<tr>
				<td align="right" width="20%" class="grey">新颜色排序:</td>
				<td><input class="floatLeft" type="text"
					name="skuAttributePojo.sorting" value="${skuAttributePojo.sorting}" id="sorting"> <span
					id="sorting_mgId"></span></td>
				<td align="right" width="20%" class="grey">新规格排序:</td>
				<td><input class="floatLeft" type="text"
					name="skuAttributePojo2.sorting" value="${skuAttributePojo2.sorting}" id="sorting2"> <span
					id="sorting2_mgId"></span></td>
			</tr>
			-->
				
			</table>
		</div>

		<div class="Btn_div">
			<input name="" type="button" value="返回" class="back_btn"
				onclick="window.history.back()" /><input id="submitButton"
				name="submitButton" type="button" class="ok_btn" value="提 交" />
		</div>

	</form>
	</div>
</body>
<script>
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
var skuColorId =new tt.Field(" 新颜色","skuAttributePojo.value").setMsgId("value_mgId");
var skuFormatId =new tt.Field(" 新规格","skuAttributePojo2.value").setMsgId("value2_mgId");

tt.vf.req.add(skuColorId,skuFormatId);

function valid(){
	if($("#value").val().trim() != "" || $("#value2").val().trim() != ""){
		tt.vf.req.rm(skuColorId,skuFormatId);
	}else{
		tt.vf.req.add(skuColorId,skuFormatId);
	}
}
$(document).ready(function() {
	$("#submitButton").click(function(){
		if(tt.validate()){		
			if($("#skuColorId").val() == "" && $("#skuFormatId").val() == ""){
				alert("请选择要修改的颜色/规格！");
				return;
			}
			$("#form1").submit();
		}
	});
});
</script>

</html>