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
<link href="/css/gopay.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pay.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马分销</title>
<script type="text/javascript">
	
	
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
</div> 

<div class="clear"></div>
</br>
<div class="shopping_Cart">
	<div class="shopping_Cart-title">支付方式</div>
    
	<div class="my_addrss ">
		
		<div class="OrderSec">
    <!--*******在线支付*******-->
    <form action="goOrderPay.do" method="post" name="myForm" id="myForm" >
    <input name="outTradeNo" id="outTradeNo" value="<s:property value='orderDetailConfirm.outTradeNo'/>" class="inputText" type="hidden" >
    <input name="cbody" id="cbody" value="<s:property value='body'/>" class="inputText" type="hidden" >
    <input name="totalfee" id="totalfee" value="<s:property value='orderDetailConfirm.factPrice'/>" class="inputText" type="hidden" >
    <input name="extra_common_param" id="extra_common_param" value="<s:property value='orderPojo.id'/>" class="inputText"  type="hidden">
	        <div class="osBox">        
           
            <div class="os-pay">
                <div class="os-pay-con">
                    <p>付款方式：</p>
                    <p class="osp-ment"><b style="background-position: 0px -36px;"></b><a class="osp-altBut" href="javascript:;">其它支付方式</a></p>
                    
                </div>
                <!--修改支付方式-->
                <div class="os-ment-alter" style="display: block;">
                    <div class="oma-tit"><span class="otsOn">支付平台</span><span class="">储蓄卡</span><!--<span class="">信用卡</span>--></div>
                    <div class="oma-con">
                        <div class="oma-con-list">
                        <div class="platformPay payment" style="display: block;">
                            <ul style=" display:block;">
                                <li class="payment_1"><label title="支付宝"><input name="bankcode" type="radio" checked="true" value="alipay"><b paybank="支付宝" bgpos="0 -36px"></b></label></li>
                                <!--<li class="payment_2"><label title="财付通"><input name="paylogo" type="radio" value="102"><b paybank="财付通" bgpos="0 -72px"></b></label></li>-->
                                <!-- <li class="payment_3"><label title="手机支付"><input name="paylogo" type="radio"  value="103" /><b payBank="手机支付" bgPos="0 -112px"></b></label></li>-->
                               <!-- <li class="payment_4"><label title="在线支付"><input name="paylogo" type="radio" value="104"><b paybank="在线支付" bgpos="0 -151px"></b></label></li>-->
    							<div class="clear"></div>
                            </ul>
                         </div>
                         <div class="onlineBankPay payment" style="display: none;">
							<p class="title">网银储蓄卡（需要开通网上银行并安装该银行的网银证书）</p>
                            <ul>
                                <li class="payment_5"><label title="中国工商银行"><input name="bankcode" type="radio" value="ICBCB2C"><b paybank="中国工商银行" bgpos="0 -231px"></b></label></li>
                                <li class="payment_6"><label title="交通银行"><input name="bankcode" type="radio" value="COMM-DEBIT"><b paybank="交通银行" bgpos="0 -272px"></b></label></li>
                                <li class="payment_7"><label title="中国建设银行"><input name="bankcode" type="radio" value="CCB"><b paybank="中国建设银行" bgpos="0 -311px"></b></label></li>
                                <li class="payment_8"><label title="招商银行"><input name="bankcode" type="radio" value="CMB"><b paybank="招商银行" bgpos="0 -352px"></b></label></li>
                                <li class="payment_9"><label title="中国银行"><input name="bankcode" type="radio" value="BOCB2C"><b paybank="中国银行" bgpos="0 -391px"></b></label></li>
                                <li class="payment_10"><label title="上海农商银行"><input name="bankcode" type="radio" value="SHRCB"><b paybank="上海农商银行" bgpos="0 -432px"></b></label></li>
                                <li class="payment_11"><label title="中国民生银行"><input name="bankcode" type="radio" value="CMBC"><b paybank="中国民生银行" bgpos="0 -472px"></b></label></li>
                                <li class="payment_12"><label title="温州银行"><input name="bankcode" type="radio" value="WZCBB2C-DEBIT"><b paybank="温州银行" bgpos="0 -512px"></b></label></li>
                                <li class="payment_13"><label title="北京银行"><input name="bankcode" type="radio" value="BJBANK"><b paybank="北京银行" bgpos="0 -552px"></b></label></li>
                                <li class="payment_14"><label title="浦发银行"><input name="bankcode" type="radio" value="SPDB"><b paybank="浦发银行" bgpos="0 -585px"></b></label></li>
                                <li class="payment_15"><label title="北京农村商业银行 "><input name="bankcode" type="radio" value="BJRCB"><b paybank="北京农村商业银行 " bgpos="0 -624px"></b></label></li>
                                <li class="payment_16"><label title="广发银行"><input name="bankcode" type="radio" value="GDB"><b paybank="广发银行" bgpos="0 -663px"></b></label></li>
                                <li class="payment_17"><label title="中国邮政银行"><input name="bankcode" type="radio" value="POSTGC"><b paybank="中国邮政银行" bgpos="0 -704px"></b></label></li>
                                <li class="payment_18"><label title="中国光大银行"><input name="bankcode" type="radio" value="CEB-DEBIT"><b paybank="中国光大银行" bgpos="0 -744px"></b></label></li>
								<li class="payment_29"><label title="平安银行"><input name="bankcode" type="radio" value="SPABANK"><b bgpos="0 -1184px"></b></label></li>
                                <div class="clear"></div>
                            </ul>
                            </div>
                           <!-- <div class="creditCardPay payment" style="display: none;">
							<p class="title">快捷信用卡支付（由<span>支付宝</span>提供快捷支付保障，免开通，有卡就能付）</p>
                           	 <ul>
                                <li class="payment_19"><label title="中国工商银行"><input name="paylogo" type="radio" value="120"><b paybank="中国工商银行" bgpos="0 -780px"></b></label></li>
                                <li class="payment_20"><label title="中国农业银行"><input name="paylogo" type="radio" value="121"><b paybank="中国农业银行" bgpos="0 -811px"></b></label></li>
                                <li class="payment_21"><label title="中国建设银行"><input name="paylogo" type="radio" value="122"><b paybank="中国建设银行" bgpos="0 -844px"></b></label></li>
                                <li class="payment_22"><label title="招商银行"><input name="paylogo" type="radio" value="123"><b paybank="招商银行" bgpos="0 -879px"></b></label></li>
                                <li class="payment_23"><label title="中国银行"><input name="paylogo" type="radio" value="124"><b paybank="中国银行" bgpos="0 -913px"></b></label></li>
                                <li class="payment_24"><label title="中国民生银行"><input name="paylogo" type="radio" value="125"><b paybank="中国民生银行" bgpos="0 -947px"></b></label></li>
                                <li class="payment_25"><label title="深圳发展银行"><input name="paylogo" type="radio" value="126"><b paybank="深圳发展银行" bgpos="0 -980px"></b></label></li>
                                <li class="payment_26"><label title="兴业银行"><input name="paylogo" type="radio" value="127"><b paybank="兴业银行" bgpos="0 -1015px"></b></label></li>
                                <li class="payment_27"><label title="广发银行"><input name="paylogo" type="radio" value="128"><b paybank="广发银行" bgpos="0 -1049px"></b></label></li>
                                <li class="payment_28"><label title="中信银行"><input name="paylogo" type="radio" value="129"><b paybank="中信银行" bgpos="0 -1083px"></b></label></li>
    							<div class="clear"></div>
                            </ul>
                            </div>-->
                            <div class="clear"></div>
							<script>
								$(".os-pay-con").show();
                        		$(".os-ment-alter").hide();
                        		var pmentBg = $(".oma-con-list li label input:checked").siblings("b").attr("bgPos");
								//var payBank=$(".oma-con-list li label input:checked").siblings("b").attr("payBank");
                        		$(".osp-ment b").css('background-position',pmentBg);
								var index = $(".oma-con-list .payment").index($(".oma-con-list li label input:checked").parents(".payment"));
								$(".oma-tit span").removeClass("otsOn").eq(index).addClass("otsOn");
								$(".oma-con-list .payment").hide().eq(index).show();
								
							</script>
                        </div>
                      
                    </div>
                </div>
               <!-- <p class="osp-pay"><a class="payBut" id="submitId">立即支付</a></p>-->
                <!--修改支付方式end-->
            </div>
          
        </div>
		<!--*******在线支付end*******-->
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
						<td class="shopping_Cart-table-txt07" style="border-bottom:none;"><div align="left" style="margin-left:5px;">未付款<!--<s:property value='orderDetailConfirm.orderStatusName'/>--></div></td>
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
		
		  <p class="osp-pay">
		  <span style="font-size:18px;">共计：<font color="#FF0000">¥&nbsp;<s:property value="orderDetailConfirm.factPrice"/></font></span>&nbsp;&nbsp;&nbsp;&nbsp;
		  <a class="payBut" href="#" id="submitId">立即支付</a>
		  
		  </p>
		</form>
		</div>
	</div>

</div>

<div class="clear"></div>


 



<jsp:include page="footer.jsp"></jsp:include>
<!-- 代码 结束 -->
<script type="text/javascript">
<!-- 
(function (d) {
(window.bd_cpro_rtid = window.bd_cpro_rtid || []).push({id:"nH0vnjm4"});
var s = d.createElement("script");s.type = "text/javascript";s.async = true;s.src = location.protocol + "//cpro.baidu.com/cpro/ui/rt.js";
var s0 = d.getElementsByTagName("script")[0];s0.parentNode.insertBefore(s, s0);
})(document);
//-->

<!-- BFD begain -->
window["_BFD"] = window["_BFD"] || {};
	_BFD.BFD_INFO = {
		"order_id" : [ "17150309034332638288" ],  
		"order_items" : [["12045","29.00","1"],],	
		"total" : "39.00",
		"payment" : $(".oma-con-list li label input:checked").siblings("b").attr("payBank"),	
		"express" : "",  			
		"user_id" : getUserId(),		
		"page_type": "order" 
	};	
<!-- BFD end -->

//emarsys begain
try{
ScarabQueue.push(['purchase',{orderId: '17150309034332638288',items:[{item:"12045",price:29.00,quantity:1},]}]);
}catch(e){}
//emarsys end
</script>

<script type="text/javascript">
$(document).ready(function() {
	//结算
	$("#submitId").click(function(){
		
			document.getElementById("myForm").submit();
		
	});
});
</script>

</body></html>