<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baidu.ueditor.ActionEnter"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
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
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a>资讯信息</a> &gt; <a href="store_list.html">编辑信息</a> &gt;
		</div>
		<div class="h15"></div>
		<div>
			<form action="updateInfo.do?infoPojo.type=${infoPojo.type}" method="post" id="from1"
				enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<input type="hidden" name="infoPojo.id" id="shopLatitude"
						value="${infoPojo.id}" class="inputText">
					<input type="hidden" name="infoPojo.type" id="shopLatitude"
						value="${infoPojo.type}" class="inputText">
					<tr>
						<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%">
						<select name="infoPojo.status" id="">
								<s:iterator value="statusSysDictList">
									<option value="<s:property value="value"/>"
									<s:if test="value == infoPojo.status">selected="selected"</s:if>><s:property
											value="name" />
									</option>
								</s:iterator>
						</select>
						<div id="ticketType_mgId"></div></td>
						<%-- <td align="right" class="grey" width="15%">信息类型:</td>
						<td width="35%">
						<select name="infoPojo.type" id="">
						 		<option value="">全部</option>
								<s:iterator value="infoTypeList">
									<option value="<s:property value="value"/>"
									<s:if test="value == infoPojo.type">selected="selected"</s:if>><s:property
											value="name" />
									</option>
								</s:iterator>
						</select>
							<div id="ticketType_mgId"></div>
						</td> --%>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">标题:</td>
						<td width="35%"><input type="text" name="infoPojo.title"
							class="floatLeft" id="merNamffe"
							value="<s:property value="infoPojo.title"/>"><span
							id="merTitle_msgId"></span></td>
						<td align="right" class="grey" width="15%">作者:</td>
						<td width="35%"><input type="text" name="infoPojo.author"
							class="floatLeft" id="merNamffe"
							value="<s:property value="infoPojo.author"/>"><span
							id="merAuthor_msgId"></span></td>
						<!-- 
						<td align="right" class="grey" width="15%">创建时间:</td>
						<td width="35%"><input name="pagePushPojo.create_date"
							id="shopAddDate" value="" class="inputText" readonly="readonly">
							<img id="calendarImgs"
							src="/js/My97DatePicker/skin/datePicker.gif" style="float: left;"
							width="16" height="22" align="absmiddle" /> <span
							id="noticeAddDate_msgId"></span></td>
					   -->
					 </tr>
					 <!--<tr>
						<td align="right" class="grey" width="15%">上传:</td>
						<td width="35%" colspan="3"><input type="file" name="upfile"
							class="floatLeft" id="ticketName">
							<img src="/upfiles/info/<s:property value="infoPojo.image"/>" height="100px">
							<span id="shopLongitude_msgIddd" ></span>
						</td>-->
					 </tr>
					  <tr>
					  <td align="right" class="grey" width="15%">内容:</td>
						<td width="35%" colspan="3">
						<textarea rows="5" cols="40" name="infoPojo.content" class="floatLeft" id="content"><s:property value="infoPojo.content"/></textarea>
						<script type="text/javascript">
							var ue = UE.getEditor("content");
						</script>
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
		var infoAuthor = new tt.Field(" 来源 ", "infoPojo.author")
				.setMsgId("merAuthor_msgId");

		tt.vf.req.add(infoTitle, infoAuthor);
		new tt.LV().set(0, 50).add(infoTitle);

	$(document).ready(function() {
		$("#sbutton").click(function() {
			if (tt.validate()) {
				document.getElementById("from1").submit();
			}
		});
	});
	
</script>