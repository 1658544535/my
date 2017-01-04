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
<title>淘竹马分销平台</title>
<style type="text/css">
<!--
.STYLE2 {color: #FF0000; font-weight: bold; }
.STYLE3 {color: #FF0000; font-weight: bold; font-size: 18px; }
-->
</style>
<script type="text/javascript">
$(window).load(function(){
        $(function(){
            $('.radiosss').click(function(){
				var label_radio_value = $(':radio[name="addressId"]:checked').val();
			    <s:iterator value="addressList">
			    $("#address_li<s:property value='id'/>").removeClass('selected');
			    $("#edit_<s:property value='id'/>").css({"display":"none"});
			    </s:iterator>
	        //     alert(label_radio_value);
			//	$(".label_radio").css({"border":"#e6e6e6 2px solid","color":"#666"});
			//	$(".label_radio1").css({"border":"#e6e6e6 2px solid","color":"#666"});
				$("#address_li"+label_radio_value).addClass('selected');
                 $("#edit_"+label_radio_value).css({"display":"block"});
				// setupLabel();
            });
			//setupLabel();
        });
});
</script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<br/>
 
<div class="top02-width">
	<div class="logo"></div>
	<div class="search"><input name="" type="text" value="搜索 商品/店铺" onfocus="this.value=''" onblur="if(this.value==''){this.value='搜索 商品/店铺'}" class="search-input"/><button type="submit" class="search-button">搜 索</button></div>
</div> 

<div class="clear"></div>

<div class="shopping_Cart">
<form class="addform" action="confirmOrder.do" method="post" name="myForm" id="myForm">
			<input type="hidden" name="totalfee" id="totalfee" value="${allCartPrice}" class="inputText">
	<div class="shopping_Cart-title">确认收货地址</div>
    
	<div class="my_addrss clear">
		
			<!--<ul class="address_list" >
				<s:iterator value="addressList">
				<s:if test='isDefault == 1'>
				<li class="selected" id="">
				<label  for="address_<s:property value='id'/>">
					<span class="marker"></span>
					<span style="color:#df434e; font-weight:bold;">&nbsp;寄送至</span>
					<input name="addressId" type="radio" value="<s:property value='id'/>" id="address_<s:property value='id'/>"/>
					<span style="font-size:14px; font-weight:bold; padding-left:10px;" for="address_<s:property value='id'/>"><s:property value='provinceName'/> <s:property value='cityName'/> <s:property value='areaName'/> <s:property value='address'/> (<s:property value='consignee'/> 收) <s:property value='consigneePhone'/></span>
					
					<span style="float:right; margin-right:6px;"><a href="#" class="address_a">修改本地址</a></span>
					<span style="float:right; margin-right:6px; margin-right:40px; color:#666666;">默认地址</span>
				</label>	
				</li>
				</s:if>
				
				<s:if test='isDefault == 0'>
				<li class="address_list_two">
				<label  for="address_<s:property value='id'/>">
					<input name="addressId" type="radio" value="<s:property value='id'/>" id="address_<s:property value='id'/>" />
					<span class="address_con"><s:property value='provinceName'/> <s:property value='cityName'/> <s:property value='areaName'/> <s:property value='address'/> (<s:property value='consignee'/> 收) <s:property value='consigneePhone'/></span>
				</label>
				</li>
				</s:if>
				</s:iterator>
				
			</ul>-->
			<div style="color:#df434e; font-weight:bold;float:left;width:60px;">&nbsp;寄送至</div>
			<div style="float:left;width:1130px;">
			<ul class="address_list" id=""address_list" style="float:left;width:1130px;">
				<s:iterator value="addressList">
				<s:if test='isDefault == 1'>
				<li class="selected" style="line-height: 32px;" id="address_li<s:property value='id'/>">
				<label  for="address_<s:property value='id'/>">
					<span class="marker"></span>
					
					<input name="addressId" type="radio" value="<s:property value='id'/>" onclick="change('<s:property value='id'/>');" id="address_<s:property value='id'/>" class="radiosss" checked/>
					<span style="font-size:14px; font-weight:bold; padding-left:10px;" for="address_<s:property value='id'/>"><s:property value='provinceName'/> <s:property value='cityName'/> <s:property value='areaName'/> <s:property value='address'/> (<s:property value='consignee'/> 收) <s:property value='consigneePhone'/></span>
					
					<span style="float:right; margin-right:6px;" id="edit_<s:property value='id'/>"><a href="goUpdateDeliveryAddressWeb.do?cidsStr=<s:property value="cidsStr" />&deliveryAddressPojo.id=<s:property value="id" />" class="address_a">修改本地址</a></span>
					<span style="float:right; margin-right:6px; margin-right:40px; color:#666666;display:none;">默认地址</span>
				</label>	
				</li>
				</s:if>
				
				<s:if test='isDefault == 0'>
				<!--<li class="address_list_two">
				<label  for="address_<s:property value='id'/>">
					<input name="addressId" type="radio" value="<s:property value='id'/>"  onclick="change('<s:property value='id'/>');" id="address_<s:property value='id'/>"  class="radiosss" />
					<span class="address_con"><s:property value='provinceName'/> <s:property value='cityName'/> <s:property value='areaName'/> <s:property value='address'/> (<s:property value='consignee'/> 收) <s:property value='consigneePhone'/></span>
				</label>
				</li>-->
				<li style="line-height: 32px;" id="address_li<s:property value='id'/>">
				<label  for="address_<s:property value='id'/>">
					<span class="marker"></span>
					
					<input name="addressId" type="radio" value="<s:property value='id'/>" id="address_<s:property value='id'/>"  class="radiosss" />
					<span style="font-size:14px; font-weight:bold; padding-left:10px;" for="address_<s:property value='id'/>"><s:property value='provinceName'/> <s:property value='cityName'/> <s:property value='areaName'/> <s:property value='address'/> (<s:property value='consignee'/> 收) <s:property value='consigneePhone'/></span>
					
					<span style="float:right; margin-right:6px;display:none;" id="edit_<s:property value='id'/>"><a href="goUpdateDeliveryAddressWeb.do?cidsStr=<s:property value="cidsStr" />&deliveryAddressPojo.id=<s:property value="id" />" class="address_a">修改本地址</a></span>
					<span style="float:right; margin-right:6px; margin-right:40px; color:#666666;display:none;">默认地址</span>
				</label>	
				</li>
				</s:if>
				</s:iterator>
				
			</ul>
			</div>
			<div class="clear"></div>
			<div class="add_new_address">
				<a href="goAddAddress.do?cidsStr=<s:property value="cidsStr" />" class="add_newaddress_btn"><font style="font-weight:bold; font-size:16px;">+</font>使用新地址</a>
			</div>
		
	</div>
	
	<div class="shopping_Cart-title">送货方式</div>
    
	<div class="my_addrss clear">

			<div style="color:#df434e; font-weight:bold;float:left;width:60px;">&nbsp;送货方式</div>
			<div style="float:left;width:1130px;">
			<ul class="address_list" id=""address_list" style="float:left;width:1130px;">
				
				<li style="line-height: 32px;">
				<label  for="delivery_1">
					<span class="marker"></span>
					
					<input name="consigneeType" type="radio" value="1" id="delivery_1" class="radiosss" checked/>
					<span style="font-size:14px; font-weight:bold; padding-left:10px;" for="delivery_1">快递</span>
				
				</label>	
				</li>
				<li style="line-height: 32px;" >
				<label  for="delivery_2">
					<span class="marker"></span>
					
					<input name="consigneeType" type="radio" value="2" id="delivery_2" class="radiosss"/>
					<span style="font-size:14px; font-weight:bold; padding-left:10px;" for="delivery_2">自提</span>
				
				</label>	
				</li>
				
				
			</ul>
			</div>
			<div class="clear"></div>
		
	</div>
	<div class="shopping_Cart-title">购物车商品</div>

	<table border="0" cellpadding="0" cellspacing="0" class="shopping_Cart-table" style="margin-top:15px;">
	    	<tr>
	        	<td colspan="2" class="shopping_Cart-table-title">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp店铺宝贝</td>
	          	<td colspan="2" class="shopping_Cart-table-title">产品名称</td>
	          	<td class="shopping_Cart-table-title"></td>
	          	<td class="shopping_Cart-table-title"><div align="left">单价（元）</div></td>
	          	<td class="shopping_Cart-table-title"><div align="left">数量</div></td>
	          	<td class="shopping_Cart-table-title">小计</td>
	        </tr>
        	<s:iterator value="cartList">
	        <tr>
	        	<td colspan="2" class="shopping_Cart-table-txt01"><input type="hidden" name="cids" value="<s:property value='id'/>"><span class="shopping_Cart-table-txt01-Pic"><a href="goProductDetail.do?productPojo.id=<s:property value="productId"/>"><img src="/upfiles/product/<s:property value='productImage'/>" alt="" width="79" height="80" /></span></td>
	            <td class="shopping_Cart-table-txt02"><a href="goProductDetail.do?productPojo.id=<s:property value="productId"/>"><s:property value='productName'/></td>
	            <td class="shopping_Cart-table-txt03"><s:property value='color'/><p><s:property value='size'/></p></td>
	            <td class="shopping_Cart-table-txt04"></td>
	            <td class="shopping_Cart-table-txt05"><div align="left" style="margin-left:25px;">¥<s:property value='stockPrice'/></div></td>
	            <td class="shopping_Cart-table-txt07"><div align="left" style="margin-left:10px;"><s:property value='num'/></div></td>
	            <td class="shopping_Cart-table-txt06"><s:property value='allStockPrice'/></td>
		    </tr>
		    </s:iterator>
		    <tr>
		    	<td class="shopping_Cart-table-txt04" style="font-size:14px; font-weight:bold; padding-left:10px;">留言：</td>
		    	<td><textarea style="border:solid 1px #999999;" name="buyer_message"  id="buyer_message" rows="3" cols="40"></textarea>
				</td>
		    </tr>  
	        <tr>
	        	<td colspan="2" class="shopping_Cart-table-txt08"></td>
	            <td class="shopping_Cart-table-txt08"></td>
	            <td class="shopping_Cart-table-txt08"></td>
	            <td class="shopping_Cart-table-txt08">已选商品 <span class="shopping_Cart-table-txt08-red01">${goodCount}</span> 件</td>
	            <td colspan="2" class="shopping_Cart-table-txt08">合计：<span class="shopping_Cart-table-txt08-red02">¥${allCartPrice}</span></td>
	            <td class="shopping_Cart-table-txt09"><a href="#" id="submitId" ="shopping_Cart-table-txt09-Balance">提交订单</a></td>
	        </tr>
    </table>
   </form>
</div>

<script type="text/javascript">
$(document).ready(function() {
	$("#submitId").click(function(){
	var radArr = document.getElementsByName("addressId"); 
	var consigneeType = document.getElementsByName("consigneeType"); 
	var radValue = ""; 
	//alert(radArr.length); 
	for(var i=0; i<radArr.length; i++){ 
	//alert(radArr[i].checked+" "+radArr[i].name + " "+ radArr[i].value); 
	if(radArr[i].checked){ 
	radValue = radArr[i].value; 
		} 
	} 
	if(consigneeType[1].checked) {
	document.getElementById("myForm").submit();
	}else{
	
	if(radValue != null && radValue != ""){ 
	document.getElementById("myForm").submit();
	}else{ 
	alert("请选择您的收货地址！"); 
	} 
		}	
	});
});
</script>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
