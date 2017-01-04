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
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a>信息管理系统</a> &gt; <a href="message.do">消息列表</a> &gt; <a href="#">回复</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="addMessageReport.do" method="post" id="from1">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<tr>
						<td align="right" class="grey" width="15%">用户昵称:</td>
						<td width="35%">${sysLoginPojo.name}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">联系电话:</td>
						<td width="35%">${messagePojo.phone}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">状态:</td>
						<td><select name="messagePojo.status" class="floatLeft">
        						<c:forEach items="${status}" var="status">
										<option value="${status.value}"<c:if test="${messagePojo.status==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
				    </select></td>
					</tr>

					<tr>
						<td align="right" class="grey" width="15%">类型:</td>
						<td><select name="messagePojo.type" class="floatLeft">
        						<c:forEach items="${type}" var="type">
										<option value="${type.value}"<c:if test="${messagePojo.type==type.value}">selected="selected" </c:if>>${type.name}</option>
								</c:forEach>
				    </select></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">标题:</td>
						<td width="85%">${messagePojo.title}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%"
							style="text-align: top;">消息内容:</td>
						<td width="85%">${messagePojo.content}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%"
							style="text-align: top;">回复内容:</td>
						<td width="85%">
							<textarea class="floatLeft" rows="10" cols="60" name="messageReport.content" id="content"   ></textarea>
							<input type="hidden" name="messageReport.userId"  value="${messagePojo.userId}">
							<input type="hidden" name="messageReport.type"  value="${messagePojo.type}">
							<input type="hidden" name="messageReport.messageId" value="${messagePojo.id}">
							<input type="hidden" name="messageReport.status" value="${messagePojo.status}">
							<span id="equiName_msgId"></span></td>
					</tr>
				</table>

			</form>
		</div>
		<div class="Btn_div">
			<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
			<input type="button" class="ok_btn" value="回复" id="sbutton" />
		</div>
	</div>

</body>
</html>


<script>
var equiName =new tt.Field(" 回复内容","messageReport.content").setMsgId("equiName_msgId");
tt.vf.req.add(equiName);
new tt.LV().set(0, 250).add(equiName);

	$(document).ready(function() {
		$("#sbutton").click(function(){		
				if(tt.validate()){
					document.getElementById("from1").submit();
				}
		});
	});
	

</script>