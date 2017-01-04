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
			<a href="#">团免券链接列表</a> &gt; <a href="#">编辑生成链接信息</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="updateGroupFreeCouponSetting.do" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table"> 
					<input type="hidden" name="groupFreeCouponSettingPojo.id" id="groupFreeCouponSettingPojo.id" value="${groupFreeCouponSettingPojo.id}" class="inputText" />
					<tr>
						<td align="right" class="grey" width="15%">发放数量：</td>
						<td><input type="text" name="groupFreeCouponSettingPojo.num" id="groupFreeCouponSettingPojo.num" value="${groupFreeCouponSettingPojo.num}" class="floatLeft" /></td>
						<td><span id="num_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">链接失效时间：</td>
						<td><input type="text" name="groupFreeCouponSettingPojo.invalidTime" id="groupFreeCouponSettingPojo.invalidTime" value="${groupFreeCouponSettingPojo.invalidTimeStr}" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); " readonly="readonly" class="Wdate" /></td>
						<td><span id="invalidTime_mgId"></span></td>
					</tr>
					<script>
						var num = new tt.Field("发放数量","groupFreeCouponSettingPojo.num").setMsgId("num_mgId");
						var invalidTime = new tt.Field("链接失效时间","groupFreeCouponSettingPojo.invalidTime").setMsgId("invalidTime_mgId");
						
						tt.vf.req.add(num,invalidTime);
						tt.vf.num.add(num);
					</script>
					<tr>
						<td align="right" class="grey" width="15%">剩余人数：</td>
						<td><input type="text" name="groupFreeCouponSettingPojo.surplusNum" id="groupFreeCouponSettingPojo.surplusNum" value="${groupFreeCouponSettingPojo.surplusNum}" class="floatLeft" /></td>
						<td><span id="surplusNum_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">剩余玩具：</td>
						<td><input type="text" name="groupFreeCouponSettingPojo.surplusToys" id="groupFreeCouponSettingPojo.surplusToys" value="${groupFreeCouponSettingPojo.surplusToys}" class="floatLeft" /></td>
						<td><span id="surplusToys_mgId"></span></td>
					</tr>
					<script>
						var surplusNum = new tt.Field("剩余数量","groupFreeCouponSettingPojo.surplusNum").setMsgId("surplusNum_mgId");
						var surplusToys = new tt.Field("剩余玩具","groupFreeCouponSettingPojo.surplusToys").setMsgId("surplusToys_mgId");
						
						tt.vf.req.add(surplusNum,surplusToys);
						tt.vf.num.add(surplusNum,surplusToys);
					</script>
					<%-- <tr>
						<td align="right" class="grey" width="15%">状态：</td>
						<td><input type="text" name="groupFreeCouponSettingPojo.status" id="groupFreeCouponSettingPojo.status" value="${groupFreeCouponSettingPojo.status}" /></td>
						<td><span id="status_mgId"></span></td>
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
	});	
</script>