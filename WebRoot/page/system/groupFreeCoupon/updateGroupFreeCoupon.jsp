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
			<a href="#">团免券管理</a> &gt; <a href="#">编辑</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="updateGroupFreeCoupon.do" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table"> 
					<input type="hidden" name="groupFreeCouponPojo.id" id="groupFreeCouponPojo.id" value="${groupFreeCouponPojo.id}" class="inputText" />
					<%-- <tr>
						<td align="right" class="grey" width="15%">用户id：</td>
						<td><input type="text" name="groupFreeCouponPojo.userId" id="groupFreeCouponPojo.userId" value="${groupFreeCouponPojo.userId}" /></td>
						<td><span id="userId_mgId"></span></td>
					</tr> --%>
					<tr>
						<td align="right" class="grey" width="15%">状态：</td>
						<td>
							<select name="groupFreeCouponPojo.status" id=""  class="floatLeft">
								<option value="0" <c:if test="${groupFreeCouponPojo.status == 0 }">selected="selected"</c:if>>未激活</option>
								<option value="1" <c:if test="${groupFreeCouponPojo.status == 1 }">selected="selected"</c:if>>已激活</option>
					    	</select>
						</td>
						<td><span id="status_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">激活时间：</td>
						<td><input type="text" name="groupFreeCouponPojo.activeTime" id="groupFreeCouponPojo.activeTime" value="${groupFreeCouponPojo.activeTimeStr}" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); " readonly="readonly" class="Wdate" /></td>
						<td><span id="activeTime_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">失效时间：</td>
						<td><input type="text" name="groupFreeCouponPojo.invalidTime" id="groupFreeCouponPojo.invalidTime" value="${groupFreeCouponPojo.invalidTimeStr}" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); " readonly="readonly" class="Wdate" /></td>
						<td><span id="invalidTime_mgId"></span></td>
					</tr>
					<script>
						var activeTime = new tt.Field("激活时间","groupFreeCouponPojo.activeTime").setMsgId("activeTime_mgId");
						var invalidTime = new tt.Field("失效时间","groupFreeCouponPojo.invalidTime").setMsgId("invalidTime_mgId");
						
						tt.vf.req.add(activeTime,invalidTime);
					</script>
					<tr>
						<td align="right" class="grey" width="15%">是否已使用，0否1是：</td>
						<td>
							<select name="groupFreeCouponPojo.used" id=""  class="floatLeft">
								<option value="0" <c:if test="${groupFreeCouponPojo.used == 0 }">selected="selected"</c:if>>未使用</option>
								<option value="1" <c:if test="${groupFreeCouponPojo.used == 1 }">selected="selected"</c:if>>已使用</option>
					    	</select>
						</td>
						<td><span id="used_mgId"></span></td>
					</tr>
					<%-- <tr>
						<td align="right" class="grey" width="15%">上次使用时间：</td>
						<td><input type="text" name="groupFreeCouponPojo.lastUseTime" id="groupFreeCouponPojo.lastUseTime" value="${groupFreeCouponPojo.lastUseTime}" /></td>
						<td><span id="lastUseTime_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">来源：</td>
						<td><input type="text" name="groupFreeCouponPojo.source" id="groupFreeCouponPojo.source" value="${groupFreeCouponPojo.source}" /></td>
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
	});	
</script>