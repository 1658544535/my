<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">用户兑换码表管理</a> &gt; <a href="#">新增</a>
		</div>
		<div class="h15"></div>
		<div>
		<form action="addUserRedeemCode.do" method="post" id="from1" enctype="multipart/form-data">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
				<tr>
					<td align="right" class="grey" width="15%">金额：</td>
					<td><input type="text" name="userRedeemCodePojo.price" id="userRedeemCodePojo.price" value="" /></td>
					<td><span id="price_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">有效开始时间：</td>
					<td>
						<input type="text" name="userRedeemCodePojo.validStime" id="userRedeemCodePojo.validStime" value="" 
						readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/>
					</td>
					<td><span id="validStime_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">有效结束时间：</td>
					<td>
						<input type="text" name="userRedeemCodePojo.validEtime" id="userRedeemCodePojo.validEtime" value="" 
						readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/>
					</td>
					<td><span id="validEtime_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">审核状态：</td>
					<td>
						<select name="userRedeemCodePojo.status" id="userRedeemCodePojo.status">
								<option value="1">已审核</option>
								<option value="0">未审核</option>
				    		</select>	
					</td>
					<td><span id="status_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">生成数量：</td>
					<td><input type="text" name="userRedeemCodePojo.num" id="userRedeemCodePojo.num" value="" /></td>
					<td><span id="num_mgId"></span></td>
				</tr>
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
	var price =new tt.Field("金额","userRedeemCodePojo.price").setMsgId("price_mgId");
	var validStime =new tt.Field("有效开始时间","userRedeemCodePojo.validStime").setMsgId("validStime_mgId");
	var validEtime =new tt.Field("有效结束时间","userRedeemCodePojo.validEtime").setMsgId("validEtime_mgId");
	var status1 =new tt.Field("审核状态","userRedeemCodePojo.status").setMsgId("status_mgId");
	var num =new tt.Field("生成数量","userRedeemCodePojo.num").setMsgId("num_mgId");
	tt.vf.req.add(price,validStime,validEtime,status1,num);
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});
	});	
</script>