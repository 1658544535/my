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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马玩具分销平台</title>
<script>
$(function() {
	 //var shopName=document.getElementById("shopName").value;
	 //alert(shopName);
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
		                    <!--<td class="my_supplier_Product-R-list-tableTit" width="100">产品规格</td>-->
		                    <td class="my_supplier_Product-R-list-tableTit" width="60">产品价格</td>
		                    <td class="my_supplier_Product-R-list-tableTit" width="60">数量</td>
		                    <td class="my_supplier_Product-R-list-tableTit">来源渠道</td>
		                </tr>
		                <tr>
		                    <td id="productName" class="my_supplier_Product-R-list-tableTit_td"><s:property value="productName"/></td>
		                    <td id="shopName" class="my_supplier_Product-R-list-tableTit_td"><s:property value="shopName"/></td>
		                   <!--<td id="productModel" class="my_supplier_Product-R-list-tableTit_td"><s:property value="productModel"/></td>-->
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
