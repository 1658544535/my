<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<jsp:include page="../../common/top.jsp"></jsp:include>

</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">联动标签管理</a> &gt; <a href="goAddSocialCircle.do">新增标签</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="addAgeSkillLink.do" method="post" id="from1"
				enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">

					<tr>
						<td align="right" class="grey" width="15%">适用年龄：</td>
						<td><select id="ageType"
							name="ageSkillLinkPojo.ageId" class="floatLeft">
								<option value="">----请选择----</option>
								<c:forEach items="${ageTypeList}" var="ageTypeList">
									<option value="${ageTypeList.id}">${ageTypeList.name}</option>
								</c:forEach>
						</select></td>
						<td><span id="ageId_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">能力：</td>
						<td><select id="skillType"
							name="ageSkillLinkPojo.skillId" class="floatLeft">
								<option value="">----请选择----</option>
								<c:forEach items="${skillList}" var="skillList">
									<option value="${skillList.id}">${skillList.name}</option>
								</c:forEach>
						</select></td>
						<td><span id="skillId_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">次能力：</td>
						<td><select id="secondSkillType"
							name="ageSkillLinkPojo.secondSkillId" class="floatLeft">
								<option value="">----请选择----</option>
								<c:forEach items="${secondSkillList}" var="secondSkillList">
									<option value="${secondSkillList.id}">${secondSkillList.name}</option>
								</c:forEach>
						</select></td>
						<td><span id="secondSkillId_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">商品分类：</td>
						<td><select id="productType"
							name="ageSkillLinkPojo.productId" class="floatLeft">
								<option value="">----请选择----</option>
								<c:forEach items="${productTypeList}" var="productTypeList">
									<option value="${productTypeList.id}">${productTypeList.name}</option>
								</c:forEach>
						</select></td>
						<td><span id="productId_mgId"></span></td>
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
	var v_ageId = new tt.Field("适用年龄 ", "ageSkillLinkPojo.ageId")
			.setMsgId("ageId_mgId");
	var v_skillId = new tt.Field("能力 ", "ageSkillLinkPojo.skillId")
			.setMsgId("skillId_mgId");
	var v_secondSkillId = new tt.Field("次能力 ", "ageSkillLinkPojo.secondSkillId")
			.setMsgId("secondSkillId_mgId");
	var v_productId = new tt.Field("商品分类", "ageSkillLinkPojo.productId")
			.setMsgId("productId_mgId");
	tt.vf.req.add(v_ageId, v_skillId, v_secondSkillId, v_productId);

	
</script>