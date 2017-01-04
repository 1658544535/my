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
  <div class="s_nav"><a href="#">创客管理</a> &gt; <a href="userMaker.do">创客管理</a> &gt;
  <a href="goAddUserMaker.do">新增创客</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addUserMaker.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
	    <td align="right" class="grey" width="15%">账号：</td>
		<td><input type="text" name="sysLoginPojo.loginname" id="sysLoginPojo.loginname" style="float:left" value="<s:property value="sysLoginPojo.loginname"/>" /><span id="loginname_mgId"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">昵称：</td>
		<td><input type="text" name="sysLoginPojo.name" id="sysLoginPojo.name" style="float:left" value="<s:property value="sysLoginPojo.name"/>" /><span id="name_mgId"></span></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">密码：</td>
		<td><input type="text" name="sysLoginPojo.password" id="sysLoginPojo.password" style="float:left" value="<s:property value="sysLoginPojo.password"/>" /><span id="password_mgId"></span></td>
	</tr>	
	<tr>
		<td align="right" class="grey" width="15%">宝宝性别：</td>
		<td><select name="babySex" id="babySex"  class="floatLeft">
							<option value="1">王子</option>
							<option value="2">公主</option>
				    		</select><div id="babySex_mgId"></div></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">宝宝生日：</td>
		<td><input name="babyBirth" id="startdate" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear: true })"/>
		<span id="babyBirth_mgId"></span></td>
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
				var id = $("input[name='sysLoginPojo.password']").val();
				var r = /^[a-zA-Z\d]+$/;
				if(id != "" && !r.test(id)){
					alert("密码必须为数字和字母的组合！");
				}else{
					document.getElementById("from1").submit();	
				}				
			}
		});
	});	
	var v_loginname = new tt.Field("账号 ","sysLoginPojo.loginname").setMsgId("loginname_mgId");	
	var v_name      = new tt.Field("昵称 ","sysLoginPojo.name").setMsgId("name_mgId");
	var v_password  = new tt.Field("密码 ","sysLoginPojo.password").setMsgId("password_mgId");
	var v_babySex   = new tt.Field("宝宝性别 ","babySex").setMsgId("babySex_mgId");
	var v_babyBirth = new tt.Field("宝宝生日 ","babyBirth").setMsgId("babyBirth_mgId");
	tt.vf.req.add(v_name,v_loginname,v_password,v_babySex,v_babyBirth);
</script>