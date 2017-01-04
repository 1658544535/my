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
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">销售记录</a> &gt; <a href="#">审核</a> &gt;
		</div>
		<div class="h15"></div>
		<div>
			<form action="checkUserOrderDetail.do" method="post" id="from1"
				enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<input type="hidden" name="userOrderDetailPojo.id" id="shopLatitude"
						value="${userOrderDetailPojo.id}" class="inputText">
					<tr>
						<td align="right" class="grey" width="15%">用户名:</td>
						<td width="35%">
						<s:property value="userOrderDetailPojo.loginName"/><span
							id="merTitle_msgId"></span>
						<div id="ticketType_mgId"></div>
						</td>
						<td align="right" class="grey" width="15%">店铺名称:</td>
						<td width="35%">
						<s:property value="userOrderDetailPojo.shopName"/><span
							id="merTitle_msgId"></span>
						<div id="ticketType_mgId"></div>
						</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">商品名称:</td>
						<td width="35%">
						<s:property value="userOrderDetailPojo.productName"/><span
							id="merTitle_msgId"></span>
						<div id="ticketType_mgId"></div>
						</td>
						<td align="right" class="grey" width="15%">数量:</td>
						<td width="35%">
						<s:property value="userOrderDetailPojo.num"/><span
							id="merTitle_msgId"></span>
						<div id="ticketType_mgId"></div>
						</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">商品价格:</td>
						<td width="35%">
						<s:property value="userOrderDetailPojo.stockPrice"/><span
							id="merTitle_msgId"></span>
						<div id="ticketType_mgId"></div>
						</td>
						<td align="right" class="grey" width="15%">商品规格:</td>
						<td width="35%">
						<s:property value="userOrderDetailPojo.productModel"/><span
							id="merTitle_msgId"></span>
						<div id="ticketType_mgId"></div>
						</td>
					 </tr>
					  <tr>
						<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%">
						<select name="userOrderDetailPojo.status" id="">
						 		<option value="">全部</option>
								<s:iterator value="statusSysDictList">
									<option value="<s:property value="value"/>"
									<s:if test="value == userOrderDetailPojo.status">selected="selected"</s:if>>
									<s:property value="name" />
									</option>
								</s:iterator>
						</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="Btn_div">
			<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
			<input type="button" class="ok_btn" value="提交" id="sbutton" />
		</div>
	</div>
</body>
</html>

<script>
	$(document).ready(function() {
		$("#sbutton").click(function() {
			if (tt.validate()) {
				document.getElementById("from1").submit();
			}
		});
	});
</script>