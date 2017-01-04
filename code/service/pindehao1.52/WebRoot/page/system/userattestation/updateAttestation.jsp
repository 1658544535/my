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
			<a href="#">采购商账户管理 </a> &gt; <a href="#">店铺认证 </a> &gt; <a href="">编辑</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="updateUserAttestation.do?type=${type}" method="post" id="from1"
				enctype="multipart/form-data">
				<input type="hidden" name="userAttestationPojo.userId" value=<s:property value="userAttestationPojo.userId"/> id="userAttestationPojo.userId">
				<input type="hidden" name="userAttestationPojo.status" value=<s:property value="userAttestationPojo.status"/> id="userAttestationPojo.status">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<tr>
						<td align="right">用户</td>
						<td align="left"><s:property value="userAttestationPojo.userName" />
						</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">上传营业执照:</td>
						<td width="35%"><input type="file" name="upfile"
							class="floatLeft" id="ticketName"></br>
							<font color="#df434e">上传图片规格（800 * 600）,勿超过200k</font>
							<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/attestation/<s:property value="userAttestationPojo.attestationImage"/>" height="100px">
							<span id="shopLongitude_msgIddd"></span></td>
					</tr>
					<tr>
					<td align="right" class="grey" width="15%">上传3C证书:</td>
					<td width="35%"><input type="file" name="upfile2"
						class="floatLeft" id="ticketName2"></br>
						<font color="#df434e">上传图片规格（800 * 600）,勿超过200k</font>
						<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/attestation/<s:property value="userAttestationPojo.user3cImage"/>" height="100px">
						<span id="shopLongitude_msgIddd2"></span></td>
				</tr>
				</table>
			</form>
		</div>
		<div class="Btn_div">
			<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
			<input type="button" class="ok_btn" value="编辑" id="sbutton" />
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