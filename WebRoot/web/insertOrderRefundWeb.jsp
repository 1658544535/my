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
<link href="/css/slide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<title>淘竹马玩具分销平台</title>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="/js/testJSP/js/validate/css/validate.css" /> 
<script src="/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="/js/testJSP/js/validate/css/validate.css" /> 
<script type="text/javascript" src="js/_head.js"></script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="register-title">
	<div class="register-txt">淘竹马分销 |<span class="register-txt02">退货申请</span></div>
</div>

<div class="register-form">
	<div class="apply-form-title"></div>
	<form action="addOrderRefundWeb.do" method="post" id="from1">
	<table border="0" cellpadding="0" cellspacing="0" class="apply-form-table">
	<!--  <input name="goUpdateConsumer.userId" id="sysDictId" value="${session.wuser.id}" class="inputText" type="hidden" >  -->
	<input name="orderReturnPjWeb.loginname" id="orderReturnPjWeb.loginname" value="${orderReturnPjWeb.loginname}" class="inputText" type="hidden" >
	<input name="orderReturnPjWeb.orderId" id="orderReturnPjWeb.orderId" value="${orderReturnPjWeb.orderId}" class="inputText" type="hidden" >
	<input name="orderReturnPjWeb.productName" id="orderReturnPjWeb.productName" value="${orderReturnPjWeb.productName}" class="inputText" type="hidden" >
	<input name="orderReturnPjWeb.productModel" id="orderReturnPjWeb.productModel" value="${orderReturnPjWeb.productModel}" class="inputText" type="hidden" >
	<input name="orderReturnPjWeb.stockPriceOld" id="orderReturnPjWeb.stockPriceOld" value="${orderReturnPjWeb.stockPriceOld}" class="inputText" type="hidden" >
	<input name="orderReturnPjWeb.stockPrice" id="orderReturnPjWeb.stockPrice" value="${orderReturnPjWeb.stockPrice}" class="inputText" type="hidden" >
    	<tr>
        	<td class="register-form-tableTxt">用户名:</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="orderReturnPjWeb.loginname"/></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">订单号:</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="orderReturnPjWeb.orderId"/></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">产品名称:</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="orderReturnPjWeb.productName"/></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">产品规格:</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="orderReturnPjWeb.productModel"/></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">产品原价:</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="orderReturnPjWeb.stockPriceOld"/></td>
        </tr>
         <tr>
        	<td class="register-form-tableTxt">产品价格:</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="orderReturnPjWeb.stockPrice"/></td>
        </tr>
         <tr>
        	<td class="register-form-tableTxt">退回数量:</td>
            <td><input name="orderReturnPjWeb.refundNum" type="text" class="register-form-tableInput"  style="float: left;" /></td>
        </tr>
        <tr>
        	<td><div class="register-form-tableTxt">退回原因:</div></td>
        	<td>
        	<div class="consumer-apply-textarea"><textarea name="orderReturnPjWeb.refundReason" cols="" rows="" class="consumer-apply-textarea" style="float: left;" ></textarea><span id="address" ></span></div>
        	</td>
        </tr>
    </table>
    
    </form>
    <div class="apply-button" id="sbutton" >提交</div>
</div>


<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

<script>
//var company =new tt.Field(" 公司名称","goUpdateConsumer.company").setMsgId("company");
//tt.vf.req.add(company);
//new tt.LV().set(0, 800).add(address);

	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	
</script>