<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerMartshowItems-9b61bc2a35m.css" type="text/css" media="all" />
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">团免券管理</a> &gt; <a href="#">批量激活团免券</a>
		</div>
		<div class="h15"></div>
		<div>
		<form action="addGroupFreeCoupon.do" method="post" id="from1" enctype="multipart/form-data">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
				<tr>
					<td align="right" class="grey" width="15%">优惠券类型:</td>
					<td align="right">
						<select name="addTypeId" id="addTypeId"  class="floatLeft" onchange="addType()">
							<option value="0">按ID段</option>
							<option value="1">按指定ID</option>
				    	</select>
					</td>		
					<td></td>
				</tr>
				<tbody id="addType">
				</tbody>
				<tr style="display: none;">
					<td align="right" class="grey" width="15%">状态：</td>
					<td><input type="text" name="groupFreeCouponPojo.status" id="groupFreeCouponPojo.status" value="0" /></td>
					<td><span id="status_mgId"></span></td>
				</tr>
				<%-- <tr>
					<td align="right" class="grey" width="15%">激活时间：</td>
					<td><input type="text" name="groupFreeCouponPojo.activeTime" id="groupFreeCouponPojo.activeTime" value="" /></td>
					<td><span id="activeTime_mgId"></span></td>
				</tr> --%>
				<tr style="display: none;">
					<td align="right" class="grey" width="15%">发放数量：</td>
					<td><input type="text" name="num" id="" value="1" class="floatLeft" /></td>
					<td><span id="num_mgId"></span></td>
				</tr>
				<%-- <tr>
					<td align="right" class="grey" width="15%">失效时间：</td>
					<td><input type="text" name="groupFreeCouponPojo.invalidTime" id="groupFreeCouponPojo.invalidTime" value="" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); " readonly="readonly" class="Wdate" /></td>
					<td><span id="invalidTime_mgId"></span></td>
				</tr>
				<script>
					var num = new tt.Field("发放数量","num").setMsgId("num_mgId");
					var invalidTime = new tt.Field("失效时间","groupFreeCouponPojo.invalidTime").setMsgId("invalidTime_mgId");
					
					tt.vf.req.add(num,invalidTime);
					tt.vf.num.add(num);
				</script> --%>
				<%-- <tr>
					<td align="right" class="grey" width="15%">是否已使用，0否1是：</td>
					<td><input type="text" name="groupFreeCouponPojo.used" id="groupFreeCouponPojo.used" value="" /></td>
					<td><span id="used_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">上次使用时间：</td>
					<td><input type="text" name="groupFreeCouponPojo.lastUseTime" id="groupFreeCouponPojo.lastUseTime" value="" /></td>
					<td><span id="lastUseTime_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">来源：</td>
					<td><input type="text" name="groupFreeCouponPojo.source" id="groupFreeCouponPojo.source" value="" /></td>
					<td><span id="source_mgId"></span></td>
				</tr> --%>
			</table> 
		</form>
		</div>
		<div class="Btn_div">
			<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton"/>
		</div>
	</div>
</body>
</html>
<script>
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});
		
		$("#addType").html('<tr><td align="right" class="grey" width="15%">起始ID：</td><td><input type="text" name="startUserId" id="" value="" class="floatLeft" /></td><td><span id="startUserId_mgId"></span></td></tr><tr><td align="right" class="grey" width="15%">结束ID：</td><td><input type="text" name="endUserId" id="" value="" class="floatLeft" /></td><td><span id="endUserId_mgId"></span></td></tr>'+
		'<script>var startUserId = new tt.Field("起始ID","startUserId").setMsgId("startUserId_mgId");var endUserId = new tt.Field("结束ID","endUserId").setMsgId("endUserId_mgId");tt.vf.req.add(startUserId,endUserId);tt.vf.num.add(startUserId,endUserId);<\/script>');
	});	
	
	function addType(){
		var type = $("#addTypeId").val();
		if(type == 0){
			$("#addType").html('<tr><td align="right" class="grey" width="15%">起始ID：</td><td><input type="text" name="startUserId" id="" value="" class="floatLeft" /></td><td><span id="startUserId_mgId"></span></td></tr><tr><td align="right" class="grey" width="15%">结束ID：</td><td><input type="text" name="endUserId" id="" value="" class="floatLeft" /></td><td><span id="endUserId_mgId"></span></td></tr>'+
			'<script>var startUserId = new tt.Field("起始ID","startUserId").setMsgId("startUserId_mgId");var endUserId = new tt.Field("结束ID","endUserId").setMsgId("endUserId_mgId");tt.vf.req.add(startUserId,endUserId);tt.vf.num.add(startUserId,endUserId);<\/script>');
		}else{
			$("#addType").html('<tr><td align="right" class="grey" width="15%">请输入用户ID：</td><td><textarea rows="5" cols="25" name="userIds" class="floatLeft"></textarea><font color="#df434e">（请用,号隔开）</font></td><td><span id="userId_mgId"></span></td></tr>'+
			'<script>var userId = new tt.Field("用户ID","userIds").setMsgId("userId_mgId");tt.vf.req.add(userId);<\/script>');
		}
	}
</script>