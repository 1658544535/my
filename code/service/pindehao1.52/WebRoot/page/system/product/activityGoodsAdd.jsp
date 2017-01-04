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
		 <a href="#">活动商品管理</a> &gt;<c:if test="${op == 2}"><a href="thematicActivityManage.do?op=2">钱包专区设置</a></c:if>
		 <c:if test="${op != 2}"><a href="activityTimeManage.do?op=1">限时秒杀管理</a></c:if>&gt;<a>添加活动商品</a></div>
		<div class="h15"></div>
		<div>
			<form action="insertActivityGoods.do?op=${op }" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
				   <input type="hidden" name="activityGoodsPojo.timeId" value="${timeId}" id="activityGoodsPojo.timeId">
				   <input type="hidden" name="activityGoodsPojo.productId" value="${productId}" id="activityGoodsPojo.productId">
				   <input type="hidden" name="activityGoodsPojo.sellPrice" value="${sellPrice}" id="activityGoodsPojo.sellPrice">
				   <c:if test="${op != 2}"><a href="activityTimeManage.do?op=1">
				   <tr>
					   <td align="right" class="grey" width="15%">活动开始时间:</td>	
					   <td><label name="activityTimePojo.beginTime" class="floatLeft" > ${activityTimePojo.beginTime}<label>
					   <div id="beginTime_msgId"></div></td>
					   <td align="right" class="grey" width="15%">活动结束时间:</td>
					   <td><label name="activityTimePojo.endTime"  class="floatLeft" >${activityTimePojo.endTime}<label>
					   <div id="endTime_msgId"></div></td>
				   </tr></a></c:if>
                   <c:if test="${op == 2}"><a href="thematicActivityManage.do?op=2">
                   <tr>
					   <td align="right" class="grey" width="15%">活动开始时间:</td>	
					   <td><label name="activityTimePojo.beginTime" class="floatLeft" > ${activityTimePojo.beginTime}<label>
					   <div id="beginTime_msgId"></div></td>
					   <td align="right" class="grey" width="15%">活动结束时间:</td>
					   <td><label name="activityTimePojo.endTime"  class="floatLeft" >${activityTimePojo.endTime}<label>
					   <div id="endTime_msgId"></div></td>
				   </tr></a></c:if>
					<tr>
						<td align="right" class="grey" width="15%">商品名称:</td>
						<td width="35%"><label class="floatLeft" name="activityGoodsPojo.productName">${productName}<label></td>
						<td align="right" class="grey" width="15%">商品原价:</td>
						<td width="35%"><label class="floatLeft" name="activityGoodsPojo.sellPrice">${sellPrice}<label></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">审核状态:</td>
						<td width="35%">
						<select name="activityGoodsPojo.status" id="">
							<s:iterator value="statusSysDictList">
								<option value="<s:property value="value"/>"
								<s:if test="value == activityGoodsPojo.status">selected="selected"</s:if>><s:property value="name" />
								</option>
							</s:iterator>
						</select>
						<div id="status_msgId"></div>
						</td>
						<td align="right" class="grey" width="15%">排序:</td>
						<td width="35%"><input type="text" name="activityGoodsPojo.sorting" value="${activityGoodsPojo.sorting}" class="floatLeft" >
						<div id="sorting_msgId"></div></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">活动价格:</td>	
						<td width="35%"><input type="text" name="activityGoodsPojo.activePrice" value="${activityGoodsPojo.activePrice}" class="floatLeft" onchange="showtips()">
						<span id="activePrice_msgId"></span>
						</td>
						<td align="right" class="grey" width="15%">活动标示:</td>	
						<td width="35%"><span id="tips"></span></td>
					</tr>
					<tr>					
						<td align="right" class="grey" width="15%">活动商品总量:</td>	
						<td width="35%"><input type="text" name="activityGoodsPojo.activityNum" value="${activityGoodsPojo.activityNum}" class="floatLeft" >
						<span id="activityNum_msgId"></span>
						</td>				
						<td align="right" class="grey" width="15%">活动商品库存:</td>	
						<td width="35%"><input type="text" name="activityGoodsPojo.activityStock" value="${activityGoodsPojo.activityStock}" class="floatLeft" >
						<span id="activityStock_msgId"></span>
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
	var sorting = new tt.Field(" 排序 ", "activityGoodsPojo.sorting").setMsgId("sorting_msgId");
	var activePrice = new tt.Field(" 活动价格 ", "activityGoodsPojo.activePrice").setMsgId("activePrice_msgId");
	var activityNum = new tt.Field(" 活动商品总量 ", "activityGoodsPojo.activityNum").setMsgId("activityNum_msgId");
	var activityStock = new tt.Field(" 活动商品库存 ", "activityGoodsPojo.activityStock").setMsgId("activityStock_msgId");
	var title=new tt.Field("专场标题","activityTimePojo.title").setMsgId("title_mgId");
	tt.vf.req.add(sorting,activePrice,activityNum,activityStock,title);
	new tt.NRV().set(0, '++').add(activityNum,activityStock,sorting);
	tt.vf.int.add(activityNum,activityStock,sorting,activePrice);  
	new tt.CV().add(activityStock).set('n', '<=', activityNum);  
	$(document).ready(function() {
		$("#sbutton").click(function() {
			if (tt.validate()) {
				document.getElementById("from1").submit();
			}
		});
	});
	
	function showtips(){
		var s = $("input[name='activityGoodsPojo.sellPrice']").val().trim();
		var a = $("input[name='activityGoodsPojo.activePrice']").val().trim();
		if(s != "" && a != null){
			var t = a/s*10;
			$("#tips").text(t.toFixed(1)+"折");
		}
	}
</script>