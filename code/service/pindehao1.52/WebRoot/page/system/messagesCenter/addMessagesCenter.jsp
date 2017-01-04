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
  <a>商家中心管理</a> &gt; 
  <a href="messagesCenter.do">商家消息管理</a> &gt; 
  <a href="goAddMessagesCenter.do">商家消息新增</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addMessagesCenter.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
		<td align="right" class="grey" width="15%">用户账号：</td>
		<td><input type="text" name="messagesCenterPojo.loginName" id="messagesCenterPojo.loginName" value="<s:property value="messagesCenterPojo.loginName"/>" />
		<font class="" style="color: red;">注意：输入 0 时为群发信息！</font></td>
		<td><span id="loginName_mgId"></span></td>
    </tr>   
    <tr>
		<td align="right" class="grey" width="15%">消息内容：</td>
        <td width="55%">
			<textarea rows="10" cols="70" name="messagesCenterPojo.message" class="floatLeft" id="introduction"></textarea>
			<script type="text/javascript">UE.getEditor("message");</script>
		</td>		
		<td><span id="message_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">审核状态：</td>
        <td><select name="messagesCenterPojo.status" id="messagesCenterPojo.status"  class="floatLeft">
							<option value="0">未审核</option>
							<option value="1">已审核</option>
				    		</select></td>
		<td><span id="status_mgId"></span></td>
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
				var loginname = $("input[name='messagesCenterPojo.loginName']").val();
				if (loginname == "0"){
					if (confirm("确定要群发信息？")){
						document.getElementById("from1").submit();					
					}
				} else {
					document.getElementById("from1").submit();					
				}
			}
		});
	});	
	    var v_loginName =new tt.Field("用户账号 ","messagesCenterPojo.loginName").setMsgId("loginName_mgId");
		var v_message   =new tt.Field("消息内容","messagesCenterPojo.message").setMsgId("message_mgId");
		var v_status    =new tt.Field("审核状态","messagesCenterPojo.status").setMsgId("status_mgId");
		tt.vf.req.add(v_loginName,v_message,v_status);
</script>