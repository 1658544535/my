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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马分销商</title>
<script type="text/javascript">
	$(function(){
		$.ajax({
		url:'getLogisticsInformation.do?orderPojo.id=${orderPojo.id}',
		type:'post',
		dataType: 'json',
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//alert("error.");
    	},
    	success: function(result){
	    		//alert(result);
	    		valueLogisticsInformation(result);
    		}
		});
	});
	
	//小计
	function add(){
		var price = document.getElementById("price");
		var num = document.getElementById("num");
		var sum = num*price;
		alert(sum);
		document.getElementById("sum").innerHTML = sum; 
	}
	
	 if(document.readyState=="complete"){  
	        add();  
	    }   
	
	function valueLogisticsInformation(result){
		var res = eval("("+result+")");
		
		if(res.com == "zhongtong"){
			var n = "中通";
		}
		if(res.com == "shentong"){
			var n = "申通";
		}
		if(res.com == "shunfeng"){
			var n = "顺丰";
		}
		if(res.com == "yuantong"){
			var n = "圆通";
		}
		if(res.com == "huitong"){
			var n = "汇通";
		}
		if(res.com == "huitongkuaidi"){
			var n = "百世汇通";
		}
		if(res.com == "tiantian"){
			var n = "天天";
		}
		if(res.com == "yunda"){
			var n = "韵达";
		}
		if(res.com == "dhl"){
			var n = "DHL";
		}
		if(res.com == "zhaijisong"){
			var n = "宅急送";
		}
		if(res.com == "debang"){
			var n = "德邦";
		}
		if(res.com == "ems"){
			var n = "EMS国内";
		}
		if(res.com == "guotong"){
			var n = "国通";
		}
		if(res.com == "longbang"){
			var n = "龙邦";
		}
		if(res.com == "lianbang"){
			var n = "联邦";
		}
		if(res.com == "tnt"){
			var n = "TNT";
		}
		if(res.com == "xinbang"){
			var n = "新邦";
		}
		if(res.com == "zhongtie"){
			var n = "中铁";
		}
		if(res.com == "zhongyou"){
			var n = "中邮";
		}
		if(res.com == "eyoubao"){
			var n = "E邮宝";
		}
		if(res.com == "yousu"){
			var n = "优速";
		}
		if(res.com == "kuaijie"){
			var n = "快捷";
		}
		if(res.com == "gnxb"){
			var n = "国内小包";
		}
		if(res.com == "shenghui"){
			var n = "盛辉";
		}
		
		data = eval(res.data)  
		//for(var i=0;i<data.length;i++){
		//	alert(data[i].time);
		//}
		if(res==0){
			$("#order_list").html("");
			$("#order_list").append(
					"抱歉,目前还没有任何相关的物流信息! 您可以联系卖家确认是否发货了!!!");
		}else{
			$("#order_list").html("");
			$("#order_list").append(
					"<dl>"+
					"<dt>物流公司：</dt>"+
			        "<dd class='logistics-company' >"+n+"</dd>"+
			        "<dt>运单号码：</dt>"+
			        "<dd class='logistics-company' >"+res.nu+"</dd>"+
			        "<dt>物流跟踪：</dt>"+
			        "<dd class='logistics-company' >");
			for(var i=0;i<data.length;i++){
				$("#order_list").append(
					"&nbsp;"+
			        "<ol id='J_ExList' >"+
					 "<li class=''>"+data[i].time+"&nbsp;"+data[i].context+"&nbsp;"+"</li>"+
					 "</ol>"
		        );
			}
			$("#order_list").append(
				"</dd>"+"</dl>"
			);
		}
	
	}
	
</script>
<style type="text/css">
<!--
.STYLE2 {color: #FF0000; font-weight: bold; }
.STYLE3 {color: #FF0000; font-weight: bold; font-size: 18px; }
-->
</style>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="top02-width">
	<div class="logo"></div>
	<div class="search"><input name="" type="text" value="搜索 商品/店铺" onfocus="this.value=''" onblur="if(this.value==''){this.value='搜索 商品/店铺'}" class="search-input"/><button type="submit" class="search-button">搜 索</button></div>
</div> 

<div class="clear"></div>

<div class="shopping_Cart">
	<div class="shopping_Cart-title">订单详情</div>
    
	<div class="my_addrss clear">
		<div class="bd">
<div class="trade-status">
<h3>当前订单状态：
<s:property value='orderDetailConfirm.orderStatusName'/>
</h3>
     <ol>
		<li>如果没有收到货，或收到货后出现问题，您可以<span class="ww-light ww-large" data-nick="chenjianzhonggood" data-display="inline"><!--<a href="http://www.taobao.com/webww/?ver=1&amp;&amp;touid=cntaobaochenjianzhonggood&amp;siteid=cntaobao&amp;status=1&amp;portalId=&amp;gid=&amp;itemsId=" target="_blank" class="ww-inline ww-offline"><span>联系客服</span></a>--><a href="goContact.do" target="_blank" class="ww-inline ww-offline"><span>联系客服</span></a></span>协商解决。</li>
  		 <li>如果客服没有及时处理，您可以“投诉维权”</li>
     </ol>
</div>

<div class="logistics-list">
<h3>物流信息</h3>
<!--<hr>-->
<dl  id="order_list">
</dl>
 <div class="clearfix"></div>
</div>

</div>
	</div>
	
	<div class="order_information_box">
		<div class="order_information_con">
			<h3 class="wlxx">收货人信息</h3>
			<ul class="wlxx_detail">
				<li>收货人：<s:property value='orderDetailConfirm.consignee'/></li>
				<li>收货地址：<s:property value='orderDetailConfirm.consigneeAddress'/></li>
				<!--  <li>收货邮编：<s:property value='orderDetailConfirm.productName'/></li>  -->
				<li>联系电话：<s:property value='orderDetailConfirm.consigneePhone'/></li>
				<li>送货方式：<s:property value='orderDetailConfirm.consigneeTypeName'/></li>
				<li>客户留言：<s:property value='orderDetailConfirm.buyerMessage'/></li>
			</ul>
			<h3 class="wlxx">商品清单</h3>
			<table border="0" cellpadding="0" cellspacing="0" class="shopping_Cart-table" style="margin-top:15px; width:1155px; margin-bottom:15px;">
		    	
			    	<tr>
			        	<td colspan="2" class="shopping_Cart-table-title"></td>
			          	<td colspan="2" class="shopping_Cart-table-title">店铺宝贝</td>
			          	<td class="shopping_Cart-table-title"></td>
			          	<td class="shopping_Cart-table-title"><div align="left">单价（元）</div></td>
			          	<td class="shopping_Cart-table-title"><div align="left">数量</div></td>
							<td class="shopping_Cart-table-title"><div align="left">订单状态</div></td>
			          	<td class="shopping_Cart-table-title">小计</td>
			        </tr>
	      
	      
	      		<s:iterator value="orderDetailConfirmList">
			        <tr>
			        	<td colspan="2" class="shopping_Cart-table-txt01" style="border-bottom:none;"><span class="shopping_Cart-table-txt01-Pic"><a href='goProductDetail.do?productPojo.id=<s:property value='productId'/>'><img src="/upfiles/product/<s:property value='productImages'/>" alt="" width="79" height="80" /></span></td>
			            <td class="shopping_Cart-table-txt02" style="border-bottom:none;"><a href='goProductDetail.do?productPojo.id=<s:property value='productId'/>'><s:property value='productName'/></td>
			            <td class="shopping_Cart-table-txt03" style="border-bottom:none;"><p class='buy-R-Part03-table-txt01-color'><!--颜色分类：--><s:property value='color'/><br/><s:property value='size'/></p></td>
			            <td class="shopping_Cart-table-txt04"style="border-bottom:none;"></td>
			            <td class="shopping_Cart-table-txt05" style="border-bottom:none;" id="price"><div align="left" style="margin-left:25px;">¥<s:property value='stockPrice'/></div></td>
			            <td class="shopping_Cart-table-txt07" style="border-bottom:none;" id="num"><div align="left" style="margin-left:10px;" ><s:property value='num'/></div></td>
						<td class="shopping_Cart-table-txt07" style="border-bottom:none;"><div align="left" style="margin-left:5px;"><s:property value='orderDetailConfirm.orderStatusName'/></div></td>
			            <td class="shopping_Cart-table-txt06" style="border-bottom:none;"><div align="left" style="margin-left:10px;" id="sum"><s:property value='subtotal'/></div></td>
			        </tr>
      			</s:iterator>
                <tr>
			        	<td colspan="2" class="shopping_Cart-table-title" style="background:none; border-top:1px solid #dfdfdf;"></td>
			          	<td colspan="2" class="shopping_Cart-table-title" style="background:none;border-top:1px solid #dfdfdf;"></td>
			          	<td class="shopping_Cart-table-title" style="background:none;border-top:1px solid #dfdfdf;"></td>
			          	<td class="shopping_Cart-table-title" style="background:none;border-top:1px solid #dfdfdf;"></td>
						<td class="shopping_Cart-table-title" style="background:none;border-top:1px solid #dfdfdf;"><div align="left">订单金额：<span style="color:#FF0000;">¥<s:property value='orderDetailConfirm.allPrice'/></span></div></td>
			          	<td class="shopping_Cart-table-title" style="background:none;border-top:1px solid #dfdfdf; " align="center"><s:if test="orderDetailConfirm.consigneeType==2">自提</s:if><s:elseif test="orderDetailConfirm.espressPrice ==0.00">包邮</s:elseif><s:else>物流费：<span style="color:#FF0000;">¥<s:property value='orderDetailConfirm.espressPrice'/></span></s:else></td>
			          	<td class="shopping_Cart-table-title" style="background:none;border-top:1px solid #dfdfdf; ">总金额：<span style="color:#FF0000;">¥<s:property value='orderDetailConfirm.factPrice'/></span></td>
			        </tr>
    	</table>
		</div>
	</div>

</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
