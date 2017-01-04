<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a>订单管理</a> &gt; <a href="store_list.html">申请退货</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="addOrderRefund.do" method="post" id="from1">
				<input type="hidden" name="orderReturnPj.loginname" id="shopLatitude"
						value="${orderReturnPj.loginname}" class="inputText">
				<input type="hidden" name="orderReturnPj.orderId" id="shopLatitude"
						value="${orderReturnPj.orderId}" class="inputText">
				<input type="hidden" name="orderReturnPj.productName" id="shopLatitude"
						value="${orderReturnPj.productName}" class="inputText">
				<input type="hidden" name="orderReturnPj.productModel" id="shopLatitude"
						value="${orderReturnPj.productModel}" class="inputText">
				<input type="hidden" name="orderReturnPj.stockPriceOld" id="shopLatitude"
						value="${orderReturnPj.stockPriceOld}" class="inputText">
				<input type="hidden" name="orderReturnPj.stockPrice" id="shopLatitude"
						value="${orderReturnPj.stockPrice}" class="inputText">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<tr>
						<td align="right" class="grey" width="15%">用户名:</td>
						<td width="35%">
							<s:property value="orderReturnPj.loginname"/>
							<span id="merTitle_msgId"></span>
						</td>
						<td align="right" class="grey" width="15%">订单号:</td>
						<td width="35%">
							<s:property value="orderReturnPj.orderId"/>
							<span id="merTitle_msgId"></span>
						</td>
					 </tr>
					 <tr>
						<td align="right" class="grey" width="15%">商品名称:</td>
						<td width="35%">
							<s:property value="orderReturnPj.productName"/>
							<span id="merTitle_msgId"></span>
						</td>
						
						<td align="right" class="grey" width="15%">商品规格:</td>
						<td width="35%">
							<s:property value="orderReturnPj.productModel"/>
							<span id="merTitle_msgId"></span>
						</td>
					 </tr>
					 
					 <tr>
						<td align="right" class="grey" width="15%">商品原价:</td>
						<td width="35%">
							<s:property value="orderReturnPj.stockPriceOld"/>
							<span id="merTitle_msgId"></span>
						</td>
						
						<td align="right" class="grey" width="15%">商品价格:</td>
						<td width="35%">
							<s:property value="orderReturnPj.stockPrice"/>
							<span id="merTitle_msgId"></span>
						</td>
					 </tr>
					 <tr>
						<td align="right" class="grey" width="15%">退回数量:</td>
						<td width="35%">
							<input type="text" name="orderReturnPj.refundNum" class="floatLeft" id="merNamffe">
							<span id="merTitle_msgId"></span>
						</td>
						<td align="right" class="grey" width="15%">退回原因:</td>
						<td width="35%">
							<input type="text" name="orderReturnPj.refundReason" class="floatLeft" id="merNamffe">
							<span id="merAuthor_msgId"></span>
						</td>
					 </tr>
				</table>
			</form>
		</div>
		<div class="Btn_div">
			<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
			<input id="submitId" name="submitId" type="button" class="ok_btn" value="提 交" />
		</div>
	</div>
</body>
</html>

<script>
	var infoTitle = new tt.Field(" 信息标题 ", "infoPojo.title")
			.setMsgId("merTitle_msgId");

	tt.vf.req.add(infoTitle, infoContent, infoAuthor);
	new tt.LV().set(0, 30).add(infoTitle);

	$(document).ready(function() {
		$("#sbutton").click(function() {
			if (tt.validate()) {
				document.getElementById("from1").submit();
			}
		});
	});
	
</script>