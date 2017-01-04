<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
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
			<a>活动商品管理</a> &gt; <a href="">活动商品设置</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="addActivityProduct.do" method="post" id="from1" enctype="multipart/form-data">
			<input type="hidden" name="activityProductPojo.type" value="${activityProductPojo.type}" id="activityProductPojo.type">
			<input type="hidden" name="activityProductPojo.productId" value="${activityProductPojo.productId}" id="activityProductPojo.productId">
			<input type="hidden" name="activityProductPojo.id" value="${activityProductPojo.id}" id="activityProductPojo.id">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<tr>
						<td align="right" class="grey" width="15%">审核状态:</td>
						<td width="35%">
						<select name="activityProductPojo.status" id="">
					 		<option value="">全部</option>
							<s:iterator value="statusSysDictList">
								<option value="<s:property value="value"/>"
								<s:if test="value == activityProductPojo.status">selected="selected"</s:if>><s:property value="name" />
								</option>
							</s:iterator>
						</select>
						<div id="status_msgId"></div>
						</td>
						<td align="right" class="grey" width="15%">排序:</td>
						<td width="35%"><input type="text" name="activityProductPojo.sorting" value="${activityProductPojo.sorting}" class="floatLeft" >
						<div id="sorting_msgId"></div></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">备注:</td>	
						<td width="35%">
				        <textarea class="floatLeft" rows="6" cols="50" name="activityProductPojo.remarks" id="activityProductPojo.remarks" >${activityProductPojo.remarks}</textarea>
						<span id="remarks_msgId"></span>
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
	var sorting = new tt.Field(" 排序 ", "activityProductPojo.sorting").setMsgId("sorting_msgId");
	
	tt.vf.req.add(sorting);

	$(document).ready(function() {
		$("#sbutton").click(function() {
			if (tt.validate()) {
				document.getElementById("from1").submit();
			}
		});
	});
</script>