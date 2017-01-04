<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>

<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/common.js"></script>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/commonLoad.css" media="screen"
	rel="stylesheet">
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/sysDict/sysDictCommon.js"></script>

<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	$("input[name='productPojo.isIntroduce'][value='${productPojo.isIntroduce}']").attr("checked","checked");
	$("input[name='productPojo.isNew'][value='${productPojo.isNew}']").attr("checked","checked");
	$("input[name='productPojo.isHotsale'][value='${productPojo.isHotsale}']").attr("checked","checked");
});
</script>
<style type="text/css">
	.wrapper02-content{width:1190px;}
	.wrapper02-content ul{list-style:none;}
	.wrapper02-content ul li{float:left; margin:0 2px; *margin:0 1px;}
	.wrapper02-content ul li a{text-decoration:none;}
	.wrapper02-content ul li a:hover{text-decoration:none;}
	.wrapper02-content1{width:1190px;}
	.wrapper02-content1 ul{list-style:none;}
	.wrapper02-content1 ul li{float:left; margin:0 2px; *margin:0 1px;}
	.wrapper02-content1 ul li a{text-decoration:none;}
	.wrapper02-content1 ul li a:hover{text-decoration:none;}
</style>
<title>修改商品</title>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="productManage.do">商品管理</a> &gt; <a href="productIntroduceManage.do">商品优先显示信息</a> &gt; <a
				href="#">商品优先显示信息设置</a>
		</div>
	
	<div class="h15"></div>
	<div id="fullbg"></div>
	<div id="sending" style="width: 100%; height: 40px">
		<p class="close"></p>
		<p align="center">
			<img alt="加载中..." src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/image/loading.gif" height="30px" width="30px">
		</p>
	</div>
	
	<form action="setIntroduce.do" method="post" id="from1" enctype="multipart/form-data">
		<input type="hidden" name="productPojo.id" id="productPojo.id"
						value="${productPojo.id}"> 
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table">
				<tr>
					<td align="right" width="20%" class="grey">掌柜推荐:</td>
					<td>
						<input type="radio"
							name="productPojo.isIntroduce" value="1" id="isIntroduce"/><label for="isIntroduce">是</label>
						<input type="radio"
							name="productPojo.isIntroduce" value="0" id="isNotIntroduce"/><label for="isNotIntroduce">否</label> <span
						id="minimum_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">是否新品:</td>
					<td><input type="radio"
						name="productPojo.isNew" value="1" id="isNewYes" checked="checked"><label for="isNewYes">是</label>
						<input type="radio"
						name="productPojo.isNew" value="0" id="isNewNo"><label for="isNewNo">否</label><span
						id="isNew_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">掌柜热卖:</td>
					<td><input type="radio"
						name="productPojo.isHotsale" value="1" id="isHotsaleYes" checked="checked"><label for="isNewYes">是</label>
						<input type="radio"
						name="productPojo.isHotsale" value="0" id="isHotsaleNo"><label for="isNewNo">否</label><span
						id="isNew_mgId"></span></td>
				</tr>
				
			</table>
		</div>

		<div class="Btn_div">
			<input name="" type="button" value="返回" class="back_btn"
				onclick="window.history.back()" /><input id="submitId"
				name="submitId" type="button" class="ok_btn" value="提 交" />
		</div>

	</form>
	</div>
</body>
<script>
	var sysDictName = new tt.Field(" 类型名称 ", "productTypePojo.name")
			.setMsgId("name_mgId");
	tt.vf.req.add(name);
	new tt.LV().set(0, 50).add(name);
</script>

</html>