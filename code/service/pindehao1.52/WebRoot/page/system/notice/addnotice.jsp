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
			<a>推送信息管理</a> &gt; <a href="">添加推送信息</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="addPushNotice.do" method="post" id="from1"
				enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<tr>
						<td align="right" class="grey" width="15%">id参数:</td>
						<td width="35%"><input type="text" name="pagePushPojo.title"
							class="floatLeft" id="merNamffe"><span
							id="merTitle_msgId"></span></td>
						<td align="right" class="grey" width="15%"></td>
						<td></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%">
						<select name="pagePushPojo.status" id="">
								<s:iterator value="statusSysDictList">
									<option value="<s:property value="value"/>"
									<s:if test="value == pagePushPojo.status">selected="selected"</s:if>><s:property
											value="name" />
									</option>
								</s:iterator>
						</select>
						<div id="ticketType_mgId"></div>
						</td>
						<td align="right" class="grey" width="15%">信息类型:</td>
						<td >
						<select name="pagePushPojo.type" id="infoType" onchange="paramTypeOption(this.options[this.options.selectedIndex].value)">
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
						<td align="right" class="grey" width="15%">上传:</td>
						<td width="35%"><input type="file" name="upfile"
							class="floatLeft" id="ticketName"><span
							id="shopLongitude_msgIddd"></span></td>
						<td align="right" class="grey" width="15%">图片尺寸(例300*300):</td>
						<td width="35%"><input type="text" name="pagePushPojo.sizeTips"
							class="floatLeft" id="merNamffe"><span
							id="sizeTips_msgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">广告背景颜色(例#FFFF):</td>
						<td width="35%"><input type="text" name="pagePushPojo.backgroundColor"
							class="floatLeft" id="merNamffe"><span
							id="backgroundColor_msgId"></span></td>
						<td align="right" class="grey" width="15%">参数类型:</td>
						<td width="35%"><!--<input type="text" name="pagePushPojo.url"
							class="floatLeft" id="merNamffe">-->
						<select name="pagePushPojo.url" id="merNamffe">
							<option value="0">-请选择-</option>
							<option value="1">商品id</option>
							<option value="2">店铺id</option>
							<option value="3">列表</option>
							<option value="4">文字</option>
							<option value="5">活动页</option>
							
							<option value="6">创客首页</option>
							<option value="7">创客店铺详情</option>
							<option value="8">创客专题详情</option>
							<option value="9">平台专题详情(商品)</option>
							<option value="10">平台专题详情(内容)</option>
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
	var noticeTitle = new tt.Field(" 信息名称 ", "pagePushPojo.title")
			.setMsgId("merTitle_msgId");
	var noticesizeTips = new tt.Field("	图片尺寸", "pagePushPojo.sizeTips")
			.setMsgId("sizeTips_msgId");
	var backgroundColor = new tt.Field(" 颜色 ", "pagePushPojo.backgroundColor")
			.setMsgId("backgroundColor_msgId");					
	tt.vf.req.add(noticeTitle,noticesizeTips);
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
			"<option value='1'>商品id</option>"+
			"<option value='2'>店铺id</option>"+
			"<option value='3'>列表</option>"+
			"<option value='4'>文字</option>"+
			"<option value='5'>活动页</option>";
			$("[name='pagePushPojo.url']").empty();
			$("[name='pagePushPojo.url']").append(optionStr);
		} else if (val == 9){
			//内容banner
			optionStr = "<option value='0'>-请选择-</option>"+
			"<option value='1'>创客首页id</option>"+
			"<option value='2'>创客店铺id</option>"+
			"<option value='3'>创客专题id</option>"+
			"<option value='4'>平台商品专题id</option>"+
			"<option value='5'>平台内容专题id</option>";
			$("[name='pagePushPojo.url']").empty();
			$("[name='pagePushPojo.url']").append(optionStr);
		} 
	}
</script>