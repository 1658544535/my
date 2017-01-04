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
<div class="s_nav"><a href="userToyLogList.do">玩具记录</a> &gt;<a href="#">编辑发货信息</a></div>
  <div>
  <form action="updateToySendOut.do" method="post" id="from1">
  <input type="hidden" name="userToyLogPojo.id" value="${userToyLogPojo.id}">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
	    <td align="right" class="grey" width="15%">物流名称：</td>
		<td>
			<input type="text" name="userToyLogPojo.expressName" value="${userToyLogPojo.expressName}" class="floatLeft" >
		</td>
		<td><span id="userToyLogPojo.expressName"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">物流单号：</td>
		<td>
			<input type="text" name="userToyLogPojo.expressNo" value="${userToyLogPojo.expressNo}" class="floatLeft" >
		</td>
		<td><span id="userToyLogPojo.expressNo"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">收件人：</td>
		<td>
			<input type="text" name="userToyLogPojo.username" value="${userToyLogPojo.username}" class="floatLeft"  id="username">
		</td>
		<td><span id="userToyLogPojo.username"></span></td>
	</tr>
	<tr >
	    <td align="right" class="grey" width="15%">联系电话：</td>
		<td>
			<input type="text" name="userToyLogPojo.telphone" value="${userToyLogPojo.telphone}" class="floatLeft" id="telphone">
		</td>
		<td><span id="userToyLogPojo.telphone"></span></td>
	</tr>
	<tr >
	    <td align="right" class="grey" width="15%">送货地址：</td>
		<td>
			<input type="text" name="userToyLogPojo.address" value="${userToyLogPojo.address}" class="floatLeft" id="address">
		</td>
		<td><span id="userToyLogPojo.address"></span></td>
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
//验证
	var expressName = new tt.Field("物流名称 ","userToyLogPojo.expressName").setMsgId("userToyLogPojo.expressName");
	var expressNo = new tt.Field("物流单号 ","userToyLogPojo.expressNo").setMsgId("userToyLogPojo.expressNo");
	var username = new tt.Field("收件人 ","userToyLogPojo.username").setMsgId("userToyLogPojo.username");
	var telphone = new tt.Field("联系电话 ","userToyLogPojo.telphone").setMsgId("userToyLogPojo.telphone");
	var address = new tt.Field("收货地址 ","userToyLogPojo.address").setMsgId("userToyLogPojo.address");
	tt.vf.req.add(expressName,expressNo,username,telphone,address);
	
	
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});
		//检查当前用户是否为管理员
		loginType = '${userToyLogPojo.loginType}';
		if(loginType=='false'){
			//$("#removeButton").attr("disabled","disabled");
			$('#username').attr("disabled","disabled");
			$('#telphone').attr("disabled","disabled");
			$('#address').attr("disabled","disabled");
		}
	});	
</script>