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
  <a href="userWallet.do">用户钱包管理</a> &gt; 
  <a href="updateUserWallet.do">用户钱包编辑</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="updateUserWallet.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input type="hidden" name="userWalletPojo.id" id="shopLatitude" value="${userWalletPojo.id}" class="inputText" />
    <tr>
		<td align="right" class="grey" width="15%">用户ID：</td>
		<td><input type="text" name="userWalletPojo.userId" id="userWalletPojo.userId" value="<s:property value="userWalletPojo.userId"/>" /></td>
		<td><span id="userId_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">用户余额：</td>
		<td><input type="text" name="userWalletPojo.balance" id="userWalletPojo.balance" value="<s:property value="userWalletPojo.balance"/>" /></td>
		<td><span id="balance_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">钱包总额：</td>
		<td><input type="text" name="userWalletPojo.totalBalance" id="userWalletPojo.totalBalance" value="<s:property value="userWalletPojo.totalBalance"/>" /></td>
		<td><span id="totalBalance_mgId"></span></td>
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
	var userId       =new tt.Field("用户ID ","userWalletPojo.userId").setMsgId("userId_mgId");
	var balance      =new tt.Field("用户余额","userWalletPojo.balance").setMsgId("balance_mgId");
	var totalBalance =new tt.Field("钱包总额","userWalletPojo.totalBalance").setMsgId("totalBalance_mgId");
	tt.vf.req.add(userId,balance,totalBalance);
</script>