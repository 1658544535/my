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
  <div class="s_nav">
  <a>用户钱包</a> &gt; 
  <a href="userWalletLog.do">用户钱包使用记录</a> &gt; 
  <a href="addUserWalletLog.do">用户钱包使用记录新增</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addUserWalletLog.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
		<td align="right" class="grey" width="15%">用户ID：</td>
		<td><input type="text" name="userWalletLogPojo.userId" id="userWalletLogPojo.userId" value="<s:property value="userWalletPojo.userId"/>" /></td>
		<td><span id="userId_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">当前余额：</td>
		<td><input type="text" name="userWalletLogPojo.curBal" id="userWalletLogPojo.curBal" value="<s:property value="userWalletPojo.curBal"/>" /></td>
		<td><span id="curBal_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">交易方向：</td>
						<td><select name="userWalletLogPojo.type" id="userWalletLogPojo.type"  class="floatLeft">
							<option value="0">正</option>
							<option value="1">负</option>
				    		</select><div id="type_mgId"></div></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">交易金额：</td>
		<td><input type="text" name="userWalletLogPojo.tradeAmt" id="userWalletLogPojo.tradeAmt" value="<s:property value="userWalletPojo.tradeAmt"/>" /></td>
		<td><span id="tradeAmt_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">记录来源：</td>
		<td><input type="text" name="userWalletLogPojo.source" id="userWalletLogPojo.source" value="<s:property value="userWalletPojo.source"/>" /></td>
		<td><span id="source_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">订单id：</td>
		<td><input type="text" name="userWalletLogPojo.orderId" id="userWalletLogPojo.orderId" value="<s:property value="userWalletPojo.orderId"/>" /></td>
		<td><span id="orderId_mgId"></span></td>
    </tr>
    <tr>
        <font color="#FF0000">本记录的交易时间默认为本记录新增成功的时间！</font>
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
	var userId       =new tt.Field("用户ID ","userWalletLogPojo.userId").setMsgId("userId_mgId");
	var curBal       =new tt.Field("当前余额","userWalletLogPojo.curBal").setMsgId("curBal_mgId");
	var type         =new tt.Field("交易方向","userWalletLogPojo.type").setMsgId("type_mgId");
	var tradeAmt     =new tt.Field("交易金额","userWalletLogPojo.tradeAmt").setMsgId("tradeAmt_mgId");
	var source       =new tt.Field("记录来源","userWalletLogPojo.source").setMsgId("source_mgId");
	var orderId      =new tt.Field("订单id","userWalletLogPojo.orderId").setMsgId("orderId_mgId");	
	tt.vf.req.add(userId,curBal,type,tradeAmt,source,orderId);
</script>