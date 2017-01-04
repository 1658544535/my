<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">商品优惠管理</a> &gt;<a href="#">新增用户优惠券</a></div>
  <div>
  <form action="insertUserCoupon.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
	    <td align="right" class="grey" width="15%">优惠券类型：</td>
		<td>
			<select name="userCouponPojo.couponId" id="">
				<s:iterator value="couponList">
					<option value="<s:property value="couponId"/>"><s:property value="name" /></option>
				</s:iterator>
			</select>
		</td>
		<td><span id="couponId_mgId"></span></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">启用状态:</td>
        <td>
        <select name="userCouponPojo.status" id="" >
        	<option value="0">禁用</option>
        	<option value="1" selected="selected">启用</option>
	    </select>
      	</td>
		<td><span id="status_mgId"></span></td>
    </tr>
    <tr>
	  	<td align="right" class="grey" width="15%">有效期开始时间:</td>
        <td><input id="s" name="userCouponPojo.validstimeStr" value="${couponPojo.validstimeStr }" class="Wdate" type="text" onFocus="var e=$dp.$('e');WdatePicker({onpicked:function(){e.focus();},isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'e\')}'})"/>
      	</td>
		<td><span id="validStime_mgId"></span></td>
    </tr>
      <tr>
		<td align="right" class="grey"  width="15%">有效期结束时间:</td>
        <td><input id="e" name="userCouponPojo.validetimeStr" value="${couponPojo.validetimeStr }" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'s\')}'})"/>
      	</td>
      	<td><span id="validEtime_mgId"></span></td>
      </tr>
      <tr>
	    <td align="right" class="grey" width="15%">用户帐号：</td>
		<td>
			<input type="text" name="sysLoginPojo.loginname" value="${sysLoginPojo.loginname}" class="floatLeft" id="ticketName">
		</td>
		<td><span id="loginname_mgId"></span></td>
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
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	
	var couponId =new tt.Field(" 优惠券类型 ","userCouponPojo.couponId").setMsgId("couponId_mgId");
	var stat =new tt.Field(" 启用状态","userCouponPojo.status").setMsgId("status_mgId");
	var validStime =new tt.Field(" 有效期开始时间","userCouponPojo.validstimeStr").setMsgId("validStime_mgId");
	var validEtime =new tt.Field(" 有效期结束时间 ","userCouponPojo.validetimeStr").setMsgId("validEtime_mgId");
	
	tt.vf.req.add(couponId,stat,validStime,validEtime);
</script>