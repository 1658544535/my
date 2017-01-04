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
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="top02-width">
	<div class="logo"></div>
	<div class="search"><input name="" type="text" value="搜索 商品/店铺" onfocus="this.value=''" onblur="if(this.value==''){this.value='搜索 商品/店铺'}" class="search-input"/><button type="submit" class="search-button">搜 索</button></div>
</div> 

<div class="clear"></div>

<div class="shopping_Cart">
	<div class="shopping_Cart-title">确认订单信息</div>
	<form action="confirmOrder.do" method="post" name="myForm" id="myForm" >
	<div class="top-width"><br/>
		<s:iterator value="addressList">
			<input type="radio" id="addressId<s:property value='id'/>" name="addressId" value="<s:property value='id'/>" <s:if test='isDefault == 1'>checked="checked"</s:if>/><label for="addressId<s:property value='id'/>">  地址：<s:property value='province'/> <s:property value='city'/> <s:property value='area'/> <s:property value='address'/> 收货人：<s:property value='consignee'/>(<s:property value='consigneePhone'/>)</label><br/>
		</s:iterator><br/>
	</div>
    <table border="0" cellpadding="0" cellspacing="0" class="shopping_Cart-table">
    	<tr>
          	<td colspan="4" class="shopping_Cart-table-title">商品信息</td>
          	<td class="shopping_Cart-table-title">单价（元）</td>
          	<td class="shopping_Cart-table-title">数量</td>
          	<td class="shopping_Cart-table-title">金额(元)</td>
        </tr>
        
        <s:iterator value="cartList">
	        <tr>
	        	<td colspan="2" class="shopping_Cart-table-txt01"><input type="hidden" name="cids" value="<s:property value='id'/>"><span class="shopping_Cart-table-txt01-Pic"><img src="/upfiles/product/<s:property value='productImage'/>" alt="" width="79" height="80" /></span></td>
	            <td class="shopping_Cart-table-txt02"><s:property value='productName'/></td>
	            <td class="shopping_Cart-table-txt03"><s:property value='color'/><p><s:property value='size'/></p></td>
	            <td class="shopping_Cart-table-txt04">¥<s:property value='stockPrice'/></td>
	            <td class="shopping_Cart-table-txt05"><s:property value='num'/></td>
	            <td class="shopping_Cart-table-txt06"><s:property value='allStockPrice'/></td>
	        </tr>
		</s:iterator>
		
        <tr>
        	<td colspan="4" class="shopping_Cart-table-txt08"></td>
            <td colspan="2" class="shopping_Cart-table-txt08">合计：<span class="shopping_Cart-table-txt08-red02">¥${allCartPrice}</span></td>
            <td class="shopping_Cart-table-txt09"><a href="#" id="submitId" class="shopping_Cart-table-txt09-Balance">提交订单</a></td>
        </tr>
    </table>
    
    </form>
</div>
<script type="text/javascript">
$(document).ready(function() {

	$("#submitId").click(function(){
			document.getElementById("myForm").submit();
	});
});
</script>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
