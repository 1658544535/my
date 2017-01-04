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

<div class="help-nav-width">
	<div style="width:1190px; margin:0 auto;">
        <div class="favorite_01-logo">淘竹马分销</div>
        <div class="buy-nav">
            <ul>
                <li><a href="myConsumerWeb.do"><span>采购商主页</span></a></li>
                <li><a href="systemInfoConWeb.do"  id="myMessage" >消息</object></a></li>	
                <li><a href="#" id="zhaqsz">帐户设置 <img src="images/buy_03.png" alt="" width="10" height="10" /></a></li>
                <!--	<li><a href="#">个人主页</a></li>	-->
            </ul>
        </div>
        
        <div class="drop">
        	<div class="drop-arrow"></div>
            <div class="drop-Bj">
            	<div class="drop-title">安全设置</div>
                <div class="drop-list">
                	<dl>
                    	<dt><a href="goRetrieve.do">修改登录密码</a></dt>
                        <dt><a href="goSetAccountSecurityQuestion.do">密保问题设置</a></dt>
                        <!--  <dt><a href="#">其他</a></dt>  -->
                    </dl>
                </div>
            </div>
            
            <div class="drop-Bj02">
            	<div class="drop-title02">个人资料</div>
                <div class="drop-list">
                	<dl>
                    	<dt><a href="deliveryAddressListWeb.do">收货地址</a></dt>
                         <!--  <dt><a href="goUpdateConsumerWeb.do">修改个人信息</a></dt>-->
                        <dt><a href="goConsumerWeb.do">查看个人信息</a></dt>
                        <!-- <dt><a href="#">商铺认证</a></dt>	-->
                    </dl>
                </div>
            </div>
            
            <!-- 
            <div class="drop-Bj03">
            	<div class="drop-title03">账号绑定</div>
                <div class="drop-list">
                	<dl>
                    	<dt><a href="#">支付宝绑定</a></dt>
                        <dt><a href="#">微博绑定</a></dt>
                    </dl>
                </div>
            </div>
            -->
            
        </div>
    </div>
</div>

<div class="faq-width">
<div class="buy-L">
    	<div class="buy-L-title"><img src="images/faq_03.jpg" alt="" width="16" height="16" />&nbsp;&nbsp;&nbsp;&nbsp;全部功能</div>
        <div class="buy-L-list">
        	<ul>
            	<li><a href="cartWeb.do">我的购物车</a></li>
                <li><a href="orderWeb.do">已买到的宝贝</a></li>
                <li><a href="deliveryAddressListWeb.do">我的收货地址</a></li>
                <li><a href="userCollectWeb.do">我的收藏</a></li>
                <!--  <li><a href="#">API申请</a></li>  -->
                <li><a href="userCommentAllListWeb.do">我的评价</a></li>
                 <!-- <li><a href="#">退款维权</a></li>   -->
            </ul>
        </div>
    </div>
    
    <div class="my_supplier_Product-R">
    	<div class="my_supplier_Product-R-title">订单详情</div>
    	<br/><br/>
    	<s:iterator value="orderDetailWeb">
	    	<div class="my_supplier_Product-R-list">
		            <table border="0" cellpadding="0" cellspacing="0" width="100%" class="my_supplier_Product-R-list-table">
		            	<tr>
		            		<td class="my_supplier_Product-R-list-tableTit" width="400">订单号</td>
		                    <td class="my_supplier_Product-R-list-tableTit" width="100">收货人</td>
		                    <td class="my_supplier_Product-R-list-tableTit" width="100">总金额</td>
		                    <td class="my_supplier_Product-R-list-tableTit" width="60">应付金额</td>
		                    <td class="my_supplier_Product-R-list-tableTit" width="60">订单状态</td>
		                    <td class="my_supplier_Product-R-list-tableTit">下单时间</td>
		                </tr>
		                <tr>
		                	<td class="my_supplier-Part03-table-See"><s:property value="orderWeb.orderNo"/></td>
		                    <td><s:property value="orderWeb.consignee"/></td>
		                    <td><s:property value="orderWeb.allPrice"/></td>
		                    <td><font color="#FF0000"><s:property value="orderWeb.factPrice"/></font></td>
		                    <td><s:property value="orderWeb.orderStatusName"/></td>
		                    <td><s:property value="orderWeb.createDateString"/></td>
		                </tr>
		            </table>
	        </div>
	        <div class="my_supplier_Product-R-list">
		            <table border="0" cellpadding="0" cellspacing="0" width="100%" class="my_supplier_Product-R-list-table">
		            	<tr> 
		            		<td class="my_supplier_Product-R-list-tableTit" width="400">产品名称</td>
		            		<td class="my_supplier_Product-R-list-tableTit" width="100">店铺名称</td>
		                    <td class="my_supplier_Product-R-list-tableTit" width="100">产品规格</td>
		                    <td class="my_supplier_Product-R-list-tableTit" width="60">产品价格</td>
		                    <td class="my_supplier_Product-R-list-tableTit" width="60">数量</td>
		                    <td class="my_supplier_Product-R-list-tableTit">来源渠道</td>
		                </tr>
		                <tr>
		                    <td id="productName" class="my_supplier_Product-R-list-tableTit_td"><s:property value="productName"/></td>
		                    <td id="shopName" class="my_supplier_Product-R-list-tableTit_td"><s:property value="shopName"/></td>
		                    <td id="productModel" class="my_supplier_Product-R-list-tableTit_td"><s:property value="productModel"/></td>
		                    <td id="stockPrice" class="my_supplier_Product-R-list-tableTit_td"><s:property value="stockPrice"/></td>
		                	<td id="num" class="my_supplier_Product-R-list-tableTit_td"><s:property value="num"/></td>
		                	<td id="channelName" class="my_supplier_Product-R-list-tableTit_td"><s:property value="channelName"/></td>
		                </tr>
		            </table>
	        </div>
	        <br/><br/>
        </s:iterator>
        <br/><br/>
    </div>
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
