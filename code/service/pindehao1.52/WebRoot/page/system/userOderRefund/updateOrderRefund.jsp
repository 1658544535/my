<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<jsp:include page="../../common/top.jsp"></jsp:include>

<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">
function submitForm(){
	var form = document.getElementById("from1");
	form.submit();
} 
$(function() {
	$("#sbutton").click(submitForm);
});
</script>
<style type="text/css">
.table{border-collapse:collapse; margin: 0;text-align:left; font-size:14px;}
.table td { padding:7px 10px; vertical-align:middle; min-height:22px; line-height:22px;  word-wrap:word-break; word-break:break-all; border: 1px solid #cdcdcd;}
.table td input[type="text"]{height:22px; padding:3px; line-height:22px; min-width:180px; border:1px #cdcdcd solid;}
.table td select{width:180px; padding:5px; font-size:14px;}
.table td select option{ height:30px; line-height:30px;}
</style>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a>退货管理</a> &gt; <a href="userOrderRefundManage.do?refundStatus=2">退货进度</a> &gt; <a
				href="#">详情</a>
		</div>
		<div class="h15"></div>
		<div>
						<table width="100%" border="0" class="table">
				<tr><td class="grey" width="15%">商品信息</td></tr>
				<tr><td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/${userOrderRefundPojo.productImage}'  height='100px'>${userOrderRefundPojo.productName}</td></tr>
				</table>
				<table width="100%" border="0" class="table">
				<tr><td class="grey" width="15%">用户下单信息</td></tr>
				<tr><td>
				<div>用户账号:${userOrderRefundPojo.loginname1}</div>
				<div>用户昵称:${userOrderRefundPojo.loginName}</div>
				<div>收货人:${userOrderRefundPojo.consignee}</div>
				<div>买家电话:${userOrderRefundPojo.consigneePhone}</div>
				<div>收货地址:${userOrderRefundPojo.consigneeAddress}</div>
				<div>订单编号:${userOrderRefundPojo.orderNo}</div>
				<div>订单价格:${userOrderRefundPojo.allPrice}</div>
				<div>下单时间:${userOrderRefundPojo.orderDateString}</div>
				</td></tr>
				</table>
				<table width="100%" border="0" class="table">
				<tr><td class="grey" width="15%">售后信息</td></tr>
				<tr><td>
				<div>售后类型:${userOrderRefundPojo.typeName}</div>
				<div>退回数量:${userOrderRefundPojo.refundNum}</div>
				<div>退款金额:${userOrderRefundPojo.stockPrice}</div>
				<div>退款原因:${userOrderRefundPojo.refundReason}</div>
				<div>问题商品图片:</div>
				<div>
				<s:if test="userOrderRefundPojo.images!=''"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/orderRefund/${userOrderRefundPojo.images}'  height='100px'></s:if>
						<s:if test="userOrderRefundPojo.images2!=''"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/orderRefund/${userOrderRefundPojo.images2}'  height='100px'></s:if>
						<s:if test="userOrderRefundPojo.images3!=''"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/orderRefund/${userOrderRefundPojo.images3}'  height='100px'></s:if></div>
				</td></tr>
				</table>
				<table width="100%" border="0" class="table">
				<tr><td class="grey" width="15%">退回信息</td></tr>
				<tr><td>
				<div>物流公司:${userOrderRefundPojo.logisticsName}</div>
				<div>快递单号:${userOrderRefundPojo.logistics}</div>
				</td></tr>
				</table>
				<%--<form action="updateOrderRefund.do?refundStatus=2" method="post" id="from1">
				<input name="userOrderRefundPojo.id" id="orderId" value="${userOrderRefundPojo.id}" class="inputText" type="hidden">
				<table width="100%" border="0" class="table">
				 <tr><td class="grey" width="15%">客服留言</td></tr>
				<tr><td width="35%"><textarea class="floatLeft" rows="5" cols="50" name="userOrderRefundPojo.remarks" id="content">${userOrderRefundPojo.remarks}</textarea>
				<span id="remarks"></span></td> 
				</tr></table>
				</form>--%>
			<!--<form action="updateOrderRefund.do" method="post" id="from1">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<input name="userOrderRefundPojo.id" id="orderId" value="${userOrderRefundPojo.id}"
						class="inputText" type="hidden">
					<input name="userOrderRefundPojo.orderNo" id="orderNo" value="${userOrderRefundPojo.orderNo}"
						class="inputText" type="hidden">
					<tr>
						<td align="right" class="grey" width="15%">用户昵称:</td>
						<td width="35%">${userOrderRefundPojo.loginName}</td>
						<td align="right" class="grey" width="15%">订单号:</td>
						<td width="35%">${userOrderRefundPojo.orderNo}
						</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">商品名称:</td>
						<td width="35%">${userOrderRefundPojo.productName}</td>	
						<td align="right" class="grey" width="15%">物流信息:</td>
						<td width="35%">${userOrderRefundPojo.serialNumber}<span id="serialNumber"></span></td>	
					</tr>
					
					<tr>
						<td align="right" class="grey" width="15%">商品价格:</td>
						<td width="35%">
						<input type="text"  style="border:none;" name="userOrderRefundPojo.stockPrice" value="${userOrderRefundPojo.stockPrice}" readonly="true">
						<c:if test="${userOrderRefundPojo.couponPrice != 0 }">优惠金额:${userOrderRefundPojo.couponPrice }</c:if><span id="stockPrice"></span></td>

						<td align="right" class="grey" width="15%">退回数量:</td>
						<td width="35%">
							<input type="text"  style="border:none;" name="userOrderRefundPojo.refundNum" value="${userOrderRefundPojo.refundNum}" readonly="true">
						<span id="factPrice"></span></td>
					</tr>
					
					<tr>
						<td align="right" class="grey" width="15%">退货总金额:</td>
						<td width="35%"><fmt:formatNumber type="number" maxFractionDigits="2" value="${userOrderRefundPojo.stockPrice*userOrderRefundPojo.refundNum-userOrderRefundPojo.couponPrice}" /></td>	
						<td align="right" class="grey" width="15%">退回原因:</td>
						<td width="35%">${userOrderRefundPojo.refundReason}</td>	
					</tr>

					<tr>
						<td align="right" class="grey" width="15%">申请时间:</td>
						<td width="35%">${userOrderRefundPojo.creatDateString}</td>	
						<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%">
						<s:if test="userOrderRefundPojo.status==3">退货中</s:if>
						<s:else>买家填写退货信息中</s:else>
						</td>	
					</tr>
					
					<tr>
						<td align="right" class="grey" width="15%">商品图片:</td>
						<td width="35%"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/${userOrderRefundPojo.productImage}'  height='100px'></td>
						<td align="right" class="grey" width="15%">问题商品图片:</td>
						<td width="35%"><s:if test="userOrderRefundPojo.images!=''"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/orderRefund/${userOrderRefundPojo.images}'  height='100px'></s:if>
						<s:if test="userOrderRefundPojo.images2!=''"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/orderRefund/${userOrderRefundPojo.images2}'  height='100px'></s:if>
						<s:if test="userOrderRefundPojo.images3!=''"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/orderRefund/${userOrderRefundPojo.images3}'  height='100px'></s:if>
						</td>
					</tr>
					<tr> 
                        <td align="right" class="grey" width="15%">客服留言:</td>
						<td width="35%"><textarea class="floatLeft" rows="5" cols="50" name="userOrderRefundPojo.remarks" id="content">${userOrderRefundPojo.remarks}</textarea>
						<span id="remarks"></span></td>
					</tr>
				</table>-->
				<%-- <div class="Btn_div">
					<input type="button" class="back_btn" onclick="goback()" value="返回">
					<script type="text/javascript">
						function goback(){
							var formParam = "${formParam}";
							window.location.href = "userOrderRefundManage.do?pageNoVal="+"${pageNoVal}&"+formParam;
						}
					</script>
				 <s:if test="#session.role.roleId!=2"><input type="button" class="ok_btn" value="修改" id="sbutton" /></s:if>
				</div> --%>
			<!-- </form> -->
		</div>
	</div>

</body>
</html>