<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="s" uri="/struts-tags"%>
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
function searchExpress(){
	var expressType = "${logtype}";
	var expressNo = "${lognum}";
	$.ajax({
		url:'http://www.kuaidi100.com/query?type='+expressType+'&postid='+expressNo,
		type:'post',
		dataType: 'jsonp',
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
     		alert('请求超时，请重新添加');
    	},
    	success: function(result){
    		$("#expressBody").html("");
    		valueExpress(result);
    	}
	});
}

function valueExpress(result){
var ddd=result.com;
var com="";
if(ddd=="zhongtong"){
com="中通";
}else if(ddd=="shentong"){
com="申通";
}else if(ddd=="shunfeng"){
com="顺丰";
}else if(ddd=="yuantong"){
com="圆通";
}else if(ddd=="huitong"){
com="汇通";
}else if(ddd=="tiantian"){
com="天天";
}else if(ddd=="yunda"){
com="韵达";
}else if(ddd=="dhl"){
com="DHL";
}else if(ddd=="zhaijisong"){
com="宅急送";
}else if(ddd=="debang"){
com="德邦";
}else if(ddd=="ems"){
com="EMS国内";
}else if(ddd=="eyoubao"){
com="E邮宝";
}else if(ddd=="guotong"){
com="国通";
}else if(ddd=="longbang"){
com="龙邦";
}else if(ddd=="lianbang"){
com="联邦";
}else if(ddd=="tnt"){
com="TNT";
}else if(ddd=="xinbang"){
com="中铁";
}else if(ddd=="zhongtie"){
com="联邦";
}else if(ddd=="zhongyou"){
com="中邮";
}

	$("#expressBody").append(
				"<tr>"+
				"<td align='right' width='20%' class='grey'>物流公司：</td>"+
				"<td>"+com+"</td>"+
				"</tr>"+
				"<tr>"+
				"<td align='right' width='20%' class='grey'>物流单号：</td>"+
				"<td>"+result.nu+"</td>"+
				"</tr>"+
				"<tr>"+
				"<td align='right' width='20%' class='grey'>查询状态：</td>"+
				"<td>"+result.message+"</td>"+
				//"</tr>"+
				//"<tr>"+
			//	"<td align='right' width='20%' class='grey'>更新时间：</td>"+
				//"<td>"+result.updatetime+"</td>"+
				"</tr>");
	$("#expressBody").append(
				"<tr>"+
				"<td class='grey' colspan='2' align='center'>-- 详细信息 --</td>"+
				"</tr>");
	var data = result.data;
	for(var i=0; i<data.length; i++){
		$("#expressBody").append(
				"<tr>"+
				"<td align='right' width='20%' class='grey'>"+data[i].time+"</td>"+
				"<td>"+data[i].context+"</td>"+
				"</tr>");
	}
}
</script>
<title>修改商品图片</title>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">订单管理</a> &gt;  <a href="#">物流订单查询</a>
	</div>
	<div class="h15"></div>
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table" id="expressBody">
			</table>
		</div>
	</div>
	<div class="Btn_div">
		<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
	</div>
</body>
<script>
	$(function() {
		searchExpress();
	});

</script>
</html>