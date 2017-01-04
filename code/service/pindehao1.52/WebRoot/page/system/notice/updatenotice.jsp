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
			<a>推送信息管理</a> &gt; <a href="store_list.html">修改推送信息</a> &gt;
		</div>
		<div class="h15"></div>
		<div>
			<form action="updatePushnotice.do" method="post" id="from1"
				enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<input type="hidden" name="pagePushPojo.id" id="shopLatitude"
						value="${pagePushPojo.id}" class="inputText">
					<input type="hidden" name="pagePushPojo.images" id="pagePushPojo.images"
						value="${pagePushPojo.images}">	
					<tr>
						<td align="right" class="grey" width="15%">id参数:</td>
						<td width="35%"><input type="text" name="pagePushPojo.title"
							value="${pagePushPojo.title}" class="floatLeft" id="merNamffe"><span
							id="merTitle_msgId"></span></td>
						<td align="right" class="grey" width="15%">信息类型:</td>
						<td width="35%">
						<select name="pagePushPojo.type" id="" onchange="paramTypeOption(this.options[this.options.selectedIndex].value)">
						 		<option value="">全部</option>
								<s:iterator value="noticeSysDictList">
									<option value="<s:property value="value"/>"
									<s:if test="value == pagePushPojo.type">selected="selected"</s:if>><s:property
											value="name" />
									</option>
								</s:iterator>
						</select>
							<div id="ticketType_mgId"></div>
						</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%">
						<select name="pagePushPojo.status" id="">
						 		<option value="">全部</option>
								<s:iterator value="statusSysDictList">
									<option value="<s:property value="value"/>"
									<s:if test="value == pagePushPojo.status">selected="selected"</s:if>><s:property
											value="name" />
									</option>
								</s:iterator>
						</select>
							<div id="ticketType_mgId"></div>
						</td>
						<td align="right" class="grey" width="15%">参数类型:</td>
						<td width="35%">
						<!--<input type="text" name="pagePushPojo.url"
							class="floatLeft" id="ticketName" value="${pagePushPojo.url}">-->
						<select name="pagePushPojo.url" id="ticketName">
							<option value="0">-请选择-</option>
							<option value="1" <s:if test="1 == pagePushPojo.url">selected="selected"</s:if>>商品id</option>
							<option value="2" <s:if test="2 == pagePushPojo.url">selected="selected"</s:if>>专场id</option>
							<option value="3" <s:if test="3 == pagePushPojo.url">selected="selected"</s:if>>列表</option>
							<option value="4" <s:if test="4 == pagePushPojo.url">selected="selected"</s:if>>文字</option>
							<option value="5" <s:if test="5 == pagePushPojo.url">selected="selected"</s:if>>活动页</option>
						</select>
						<span id="noticeUrlNum_msgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">背景颜色值:</td>
						<td width="35%"><input type="text" name="pagePushPojo.backgroundColor"
							class="floatLeft" id="ticketName" value="${pagePushPojo.backgroundColor}"><span
							id="backgroundColor_msgId"></span></td>
						<td align="right" class="grey" width="15%">排序:</td>
						<td width="35%"><input type="text" name="pagePushPojo.sorting"
							class="floatLeft" id="ticketName" value="${pagePushPojo.sorting}"><span
							id="sorting_msgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">图片:</td>
						<td width="35%" colspan="2"><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/${pagePushPojo.images}" height="100px"  />
						<input type="file" name="upfile"
							class="floatLeft" id="ticketName"><span
							id="shopLongitude_msgIddd"></span></td>
						<td width="35%"> <p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（793*376）</font></p>
						<%-- <font color="#FF0000">图片尺寸要求:<s:property value="pagePushPojo.sizeTips"/></font> --%></td>	
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
	var noticeTitle = new tt.Field(" 信息名称 ", "pagePushPojo.title")
			.setMsgId("merTitle_msgId");
	var sorting = new tt.Field("排序 ", "pagePushPojo.sorting")
			.setMsgId("sorting_msgId");
	var backgroundColor = new tt.Field(" 颜色 ", "pagePushPojo.backgroundColor")
			.setMsgId("backgroundColor_msgId");				
	tt.vf.req.add(noticeTitle);
	new tt.LV().set(0, 50).add(noticeTitle);
	new tt.LV().set(0, 10).add(backgroundColor);
	$(document).ready(function() {
		$("#sbutton").click(function() {
			if (tt.validate()) {
				document.getElementById("from1").submit();
			}
		});
	});

	//初始化参数类型下拉
	$(function(){
		var val = $("[name='pagePushPojo.type']").val();
		paramTypeOption(val);
	})
	
	//查找参数类型下拉
	function paramTypeOption(val){
		var optionStr = "";
		//alert(val);
		if(val == 8 || val == 10){
			//商城banner
			optionStr = "<option value='0'>-请选择-</option>"+
			"<option value='1' <s:if test='1 == pagePushPojo.url'>selected='selected'</s:if>>商品id</option>"+
			"<option value='2' <s:if test='2 == pagePushPojo.url'>selected='selected'</s:if>>专场id</option>"+
			"<option value='3' <s:if test='3 == pagePushPojo.url'>selected='selected'</s:if>>列表</option>"+
			"<option value='4' <s:if test='4 == pagePushPojo.url'>selected='selected'</s:if>>文字</option>"+
			"<option value='5' <s:if test='5 == pagePushPojo.url'>selected='selected'</s:if>>活动页</option>";
			$("[name='pagePushPojo.url']").empty();
			$("[name='pagePushPojo.url']").append(optionStr);
		} else if (val == 9){
			//内容banner
			optionStr = "<option value='0'>-请选择-</option>"+
			"<option value='1' <s:if test='1 == pagePushPojo.url'>selected='selected'</s:if>>创客首页id</option>"+
			"<option value='2' <s:if test='2 == pagePushPojo.url'>selected='selected'</s:if>>创客店铺id</option>"+
			"<option value='3' <s:if test='3 == pagePushPojo.url'>selected='selected'</s:if>>创客专题id</option>"+
			"<option value='4' <s:if test='4 == pagePushPojo.url'>selected='selected'</s:if>>平台商品专题id</option>"+
			"<option value='5' <s:if test='5 == pagePushPojo.url'>selected='selected'</s:if>>平台内容专题id</option>";
			$("[name='pagePushPojo.url']").empty();
			$("[name='pagePushPojo.url']").append(optionStr);
		} 
	}
</script>