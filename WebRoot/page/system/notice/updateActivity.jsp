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
			<a>app活动管理</a> &gt; <a href="store_list.html">修改app活动信息</a> &gt;
		</div>
		<div class="h15"></div>
		<div>
			<form action="updateActivity.do" method="post" id="from1"
				enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<input type="hidden" name="activityPojo.id" id="shopLatitude"
						value="${activityPojo.id}" class="inputText">
					<input type="hidden" name="activityPojo.images" id="activityPojo.images"
						value="${activityPojo.images}">	
					<tr>
						<td align="right" class="grey" width="15%">标题:</td>
						<td width="35%"><input type="text" name="activityPojo.title"
							value="${activityPojo.title}" class="floatLeft" id="merNamffe"><span
							id="merTitle_msgId"></span></td>
						<td align="right" class="grey" width="15%">商品类型:</td>
						<td width="35%">
						<select name="activityPojo.mainCategory" id="">
						 		<option value="">全部</option>
								<s:iterator value="mainProductList">
									<option value="<s:property value="value"/>"
									<s:if test="value == activityPojo.mainCategory">selected="selected"</s:if>><s:property
											value="name" />
									</option>
								</s:iterator>
						</select>
							<div id="ticketType_mgId"></div>
						</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">ID类型:</td>
						<td width="35%">
						<select name="activityPojo.type" id="">
						 		<option value="">全部</option>
								<s:iterator value="typeSysDictList">
									<option value="<s:property value="value"/>"
									<s:if test="value == activityPojo.type">selected="selected"</s:if>><s:property
											value="name" />
									</option>
								</s:iterator>
						</select>
							<div id="type_mgId"></div>
						</td>
						<td align="right" class="grey" width="15%">ID(商品or店铺or关键字):</td>
						<td width="35%"><input type="text" name="activityPojo.typeId" value="${activityPojo.typeId}"
							class="floatLeft" ></td>
					</tr>
			
					<tr>
						<td align="right" class="grey" width="15%">图片:</td>
						<td width="35%" colspan="2"><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/activity/${activityPojo.image}" height="100px"  />
						<input type="file" name="upfile2"
							class="floatLeft" id="ticketName"><p><font color="#df434e">上传图片规格（793*376）</font></p>
						<!--<font color="#FF0000">图片尺寸要求:<s:property value="activityPojo.sizeTips"/></font>-->
						<span id="shopLongitude_msgIddd"></span></td>
						
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
	var noticeTitle = new tt.Field(" 标题 ", "activityPojo.title")
			.setMsgId("merTitle_msgId");
			
	tt.vf.req.add(noticeTitle);
	new tt.LV().set(0, 50).add(noticeTitle);
	
	$(document).ready(function() {
		$("#sbutton").click(function() {
			if (tt.validate()) {
				document.getElementById("from1").submit();
			}
		});
	});

	
</script>