<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马玩具分销平台</title>
<script>
$(function() {
	var time = null;
	$('#zhaqsz').hover(function(){
		$('.drop').show();
	},function(){
		time = setTimeout(function(){
			$('.drop').hide();
		},50);
	});
	$(".drop").hover(function(){
		clearTimeout(time);
		$(this).show();
	},function(){
		time = setTimeout(function(){
			$('.drop').hide();
		},50);
	});
});
</script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="clear"></div>
<jsp:include page="my_supplier_head.jsp"></jsp:include>

<div class="faq-width">
<jsp:include page="my_supplier_left.jsp"></jsp:include>
    
    <div class="my_supplier_Product-R">
    	<div class="my_supplier_Product-R-title">更新产品信息</div>
    	<form action="productAddWeb.do" method="post" id="from1" enctype="multipart/form-data">
    		<div>
		        <table border="0" cellpadding="0" cellspacing="0" class="my_supplier_Upload-table">
		            <tr>
		                <td class="register-form-tableTxt">产品类型：</td>
		                <td>
			                <select name="productPojo.productTypeId" class="register-form-tableInput" id="productTypeId">
			               		${typeStr}
			                </select>
		                </td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">产品名称：</td>
		                <td><input name="productPojo.productName" type="text" class="register-form-tableInput"/></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">产品货号：</td>
		                <td><input name="productPojo.productNo" type="text" class="register-form-tableInput"/></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">分销价格：</td>
		                <td><input name="productPojo.distributionPrice" type="text" class="register-form-tableInput"/></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">建议零售价：</td>
		                <td><input name="productPojo.sellingPrice" type="text" class="register-form-tableInput"/></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">最低零售价：</td>
		                <td><input name="productPojo.lowestPrice" type="text" class="register-form-tableInput"/></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">排序：</td>
		                <td><input name="productPojo.sorting" type="text" class="register-form-tableInput"/></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">产品图片：</td>
		                <td><input name="upfile" type="file" class="register-form-tableInput" style="border:0;"/></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">SKU：</td>
		                <td><input name="productStockPojo.sku" type="text" class="register-form-tableInput"/></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">库存编号：</td>
		                <td><input name="productStockPojo.stockNo" type="text" class="register-form-tableInput"/></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">重量：</td>
		                <td><input name="productStockPojo.weight" type="text" class="register-form-tableInput"/>KG </td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">尺寸：</td>
		                <td><input name="productStockPojo.size" type="text" class="register-form-tableInput"/> <font color="#FF0000">CM 例如：12*10*8</font></td>
		            </tr>
		             <tr>
		                <td class="register-form-tableTxt">颜色：</td>
		                <td><input name="productStockPojo.color" type="text" class="register-form-tableInput"/></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt" valign="top">产品介绍：</td>
		                <td><textarea name="productPojo.content" cols="10" rows="80" class="apply-textarea"></textarea></td>
		            </tr>
		            <tr align="right">
		                <td align="right"><input name="" type="button" value="返回" class="my_supplier_Upload-table-button"
					style="cursor:pointer;" onclick="window.history.back()" /></td>
		                <td align="left"><input id="submitId"
					name="submitId" type="button" class="my_supplier_Upload-table-button" style="cursor:pointer;" value="更新" /></td>
		            </tr>
		    	</table>
	    	</div>
	    	
	    	<div class="Btn_div">
			</div>
    	</form>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function() {

	$("#submitId").click(function(){
			document.getElementById("from1").submit();
	});
});
</script>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
