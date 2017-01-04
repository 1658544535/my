<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	var expressType = $("#expressType").val();
	var expressNo = $("#expressNo").val();
	if(expressType == "eyoubao"){
		expressType = "ems";
	}
	$.ajax({
		url:'expressSearch.do?type='+expressType+'&postid='+expressNo,
		type:'get',
		dataType: 'json',
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
var ddd=$("#expressType").val();
var nu=$("#expressNo").val();
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
}else if(ddd=="huitongkuaidi"){
com="百世汇通";
}else if(ddd=="tiantian"){
com="天天";
}else if(ddd=="yunda"){
com="韵达";
}else if(ddd=="dhl"){
com="DHL";
}else if(ddd=="zhaijisong"){
com="宅急送";
}else if(ddd=="debangwuliu"){
com="德邦";
}else if(ddd=="ems"){
com="EMS国内";
}else if(ddd=="eyoubao"){
com="E邮宝";
}else if(ddd=="guotongkuaidi"){
com="国通";
}else if(ddd=="longbangwuliu"){
com="龙邦";
}else if(ddd=="lianbangkuaidi"){
com="联邦";
}else if(ddd=="tnt"){
com="TNT";
}else if(ddd=="xinbangwuliu"){
com="新邦";
}else if(ddd=="zhongtiewuliu"){
com="中铁";
}else if(ddd=="zhongyouwuliu"){
com="中邮";
}else if(ddd=="youshuwuliu"){
com="优速";
}else if(ddd=="kuaijiesudi"){
com="快捷";
}else if(ddd=="youzhengguonei"){
com="国内小包";
}else if(ddd=="shenghuiwuliu"){
com="盛辉";
}
var r = eval("(" + result + ")");
var state = r.success;
var message = "";
if(state==true){
	state = r.status;
	if(state == "0"){
		message = "物流单号暂无结果";
	} else if(state == "3"){
		message = "在途";
	} else if(state == "4"){
		message = "揽件";
	} else if(state == "5"){
		message = "疑难";
	} else if(state == "6"){
		message = "已签收";
	} else if(state == "7"){
		message = "退签";
	} else if(state == "8"){
		message = "派件";
	} else if(state == "9"){
		message = "退回";
	}
} else {
	message = r.reason;
}


	$("#expressBody").append(
				"<tr>"+
				"<td align='right' width='20%' class='grey'>物流公司：</td>"+
				"<td>"+com+"</td>"+
				"</tr>"+
				"<tr>"+
				"<td align='right' width='20%' class='grey'>物流单号：</td>"+
				"<td>"+nu+"</td>"+
				"</tr>"+
				"<tr>"+
				"<td align='right' width='20%' class='grey'>快递状态：</td>"+
				"<td>"+message+"</td>"+
				"</tr>");
	$("#expressBody").append(
				"<tr>"+
				"<td class='grey' colspan='2' align='center'>-- 详细信息 --</td>"+
				"</tr>");
	var data = r.data;
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
			<a href="orderManage.do">订单管理</a> &gt;  <a href="#">物流订单查询</a>
		</div>
	<div class="Search_control">
			<p>按条件查找</p>
		</div>
		<!-- 查询开始 -->

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">物流类型：</td>
						<td><label>
							<!--<select id="expressType">
								<option value="">- 请选择 -</option>
								<option value="shunfeng">顺丰快递</option>
								<option value="shentong">申通快递</option>
								<option value="zhongtong">中通快递</option>
								<option value="yuantong">圆通快递</option>
								<option value="huitong">汇通快递</option>
								<option value="tiantian">天天快递</option>
								<option value="yunda">韵达快递</option>
								<option value="dhl">DHL快递</option>
								<option value="zhaijisong">宅急送</option>
								<option value="debang">德邦物流</option>
								<option value="ems">EMS国内</option>
								<option value="eyoubao">E邮宝</option>
								<option value="guotong">国通快递</option>
								<option value="longbang">龙邦速递</option>
								<option value="lianbang">联邦快递</option>
								<option value="tnt">TNT快递</option>
								<option value="xinbang">新邦物流</option>
								<option value="zhongtie">中铁快运</option>
								<option value="zhongyou">中邮物流</option>
							</select>-->
							<select id="expressType">
				        	 <c:forEach items="${sysDicPojoList}" var="sysDicPojoList">
								<option value="${sysDicPojoList.nameEn}">${sysDicPojoList.name}</option>
							 </c:forEach>   
					        </select>
						</label></td>
						<td align="right">物流单号：</td>
						<td><label><input type="text" id="expressNo" name="expressNo" value=""></label></td>
					</tr>

				</table>
				<div class="floatRight">
					<input type="button" class="submit_btn" onclick="searchExpress()" value="查询" />
				</div>

				<div class="Clear"></div>
			</div>
		<!-- 查询结束 -->
	<div class="h15"></div>
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table" id="expressBody">
			</table>
		</div>
	</div>
</body>

</html>