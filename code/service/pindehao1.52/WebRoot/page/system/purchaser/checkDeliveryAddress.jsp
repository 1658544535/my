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
			<a>采购商账户管理</a> &gt; <a href="">我的收货地址</a> &gt;<a href="">审核</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="verifyDeliveryAddress.do" method="post" id="from1"
				enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<input type="hidden" name="deliveryAddressPojo.id" id="shopLatitude"
						value="${deliveryAddressPojo.id}" class="inputText">
					<tr>
						<td align="right" class="grey" width="15%">订单编号:</td>
						<td width="35%">
						<s:property value="deliveryAddressPojo.id"/>
						<div id="ticketType_mgId"></div></td>
						<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%">
								<s:iterator value="statusSysDictList">
									<s:if test="value == deliveryAddressPojo.status">
										<s:property value="name" />
									</s:if>
								</s:iterator>
						<div id="ticketType_mgId"></div></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">省:</td>
						<td width="35%">
						<s:property value="deliveryAddressPojo.provinceName"/>
						<span id="shopLongitude_msgIddd" ></span>
						</td>
						<td align="right" class="grey" width="15%">城市:</td>
						<td width="35%">
						<s:property value="deliveryAddressPojo.cityName"/><span
							id="merTitle_msgId"></span>
						</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">邮编:</td>
						<td width="35%">
						<s:property value="deliveryAddressPojo.postcode"/><span
							id="merTitle_msgId"></span>
						</td>
						<td align="right" class="grey" width="15%">地址:</td>
						<td width="35%">
						<s:property value="deliveryAddressPojo.address"/><span
							id="merAuthor_msgId"></span>
						</td>
					 </tr>
					 <tr>
					 <!-- 
					  <td align="right" class="grey" width="15%">内容:</td>
						<td width="35%" colspan="3">
						<s:property value="infoPojo.content"/>
						<span id="merContent_msgId"></span>
					    </td>
					  --> 
					  	<td align="right" class="grey" width="15%">收货人:</td>
						<td width="35%">
						<s:property value="deliveryAddressPojo.consignee"/><span
							id="merTitle_msgId"></span>
						</td>
						<td align="right" class="grey" width="15%">收货人电话:</td>
						<td width="35%">
						<s:property value="deliveryAddressPojo.consigneePhone"/><span
							id="merAuthor_msgId"></span>
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
		var infoTitle = new tt.Field(" 信息标题 ", "infoPojo.title")
				.setMsgId("merTitle_msgId");
		var infoContent = new tt.Field(" 内容 ", "infoPojo.content")
				.setMsgId("merContent_msgId");
		var infoAuthor = new tt.Field(" 作者 ", "infoPojo.author")
				.setMsgId("merAuthor_msgId");

		tt.vf.req.add(infoTitle, infoContent, infoAuthor);
		new tt.LV().set(0, 30).add(infoTitle);
		new tt.LV().set(0, 100).add(infoContent);

	$(document).ready(function() {
		$("#sbutton").click(function() {
			if (tt.validate()) {
				document.getElementById("from1").submit();
			}
		});
	});
	
</script>