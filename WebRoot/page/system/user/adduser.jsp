<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<html>
<head>

<title>Insert title here</title>
<!-- <link href="/css/content.css" media="screen" rel="stylesheet"> -->
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/common.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/sys_util.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script> 



<script type="text/javascript">
	$(document).ready(function() {
		
		$("#btn_cancle").click(function() {
			$("input").each(function(i) {
				$(this).val("");
			});
		});
						
		 $("#btn_sure").click(function(){
			if(tt.validate()){
				 if (confirm("确认添加吗？")) {
					document.getElementById("contentForm").submit();
				} 
			}
			
		}); 

	});
	
	function testName(){
	 	var userId = $("#userId").val();
		MAOWU.ajax.post("testUserId.do",{"user.userId":userId},callback);
	}
	function callback(value){
		if(value==1){
			alert("该账号已存在，请重新输入！");
			$("#userId").focus();
			
		}
	}
	
	
</script>
</head>
<body>

	<div class="sub_wrap">
  <div class="s_nav"><a>系统管理</a> &gt; <a href="#" >用户管理</a> &gt; <a href="#">添加用户</a></div>
  <div class="h15"></div>

	<div>

		<form method="post" action="adduser.do" id="contentForm" enctype="multipart/form-data">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
				<tr>
					<td align="right" class="grey" style="width:10%;">账号：</td>
					<td style="width:40%">
						<input id="userId" name="user.userId" type="text" class="floatLeft" onblur="javascript:testName()" />
						<span id="userId_mgId"></span>
					</td>
					<td align="right" class="grey" style="width:10%;">姓名：</td>
					<td style="width:40%">
						<input id="userName" name="user.userName" type="text" class="floatLeft"/>
						<span id="userName_mgId"></span>
					</td>
				</tr>
				
				
				<tr>
					<td align="right" class="grey">用户密码：</td>
					<td>
						<input id="loginPd" name="loginPd" type="password" style="width:180px;height:28px" class="floatLeft"/>
						<span id="loginPd_mgId"></span>
					</td>
					<td align="right" class="grey">性别：</td>
					<td>
						<input type="radio" value="01"name="user.userSex" id="userSex" checked="checked">男 
						<input type="radio" value="02" name="user.userSex" id="userSex">女
					</td>
				</tr>
				

				<tr>
					<td align="right" class="grey">邮箱地址：</td>
					<td>
						<input id="userEmail" name="user.userEmail" type="text" class="floatLeft"/>
						<span id="userEmail_mgId"></span>
					</td>
					<td align="right" class="grey">手机号码：</td>
					<td>
						<input id="userPhone" name="user.userPhone" type="text" class="floatLeft"/>
						<span id="userPhone_mgId"></span>
					</td>
				</tr>
				
				
				
				<tr>
					<td align="right" class="grey">职位：</td>
					<td>
						<s:action name="positionCombox" executeResult="true"></s:action>
					 	<span id="position_mgId"></span>
					 </td>
					 <td align="right" class="grey">所在部门：</td>
					 <td>
					 	<s:action name="departmentCombox" executeResult="true"></s:action>
					 	<span id="userDept_mgId"></span>
					 </td>
				</tr>  
			
				
				<tr>
					<td align="right" class="grey">出生日期：</td>
					<td>
						<input id="userBirthday"
							name="user.userBirthday" type="text" class="floatLeft" readonly="readonly"> 
							<img id="calendarImg" src="/js/My97DatePicker/skin/datePicker.gif" width="16"
							height="22" align="middle" class="floatLeft"/>
						<span id="userBirthday_mgId"></span>
					</td> 
					<td align="right" class="grey">上传照片:</td>	
			        <td><input type="file" name="upfile" class="floatLeft"></td>
				</tr>
				
				<tr>
					<td align="right" class="grey">旺旺号：</td>
					<td colspan="3">
						<input type="text" name="user.userWWID" name="user.userWWID" class="floatLeft" maxlength="50">
						<span id="userWWID_mgId"></span>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
		<div class="Btn_div">
	  		<button type="button" class="back_btn" id="btn_cancle">取消输入</button>
			<button type="button" class="ok_btn" id="btn_sure">确定</button>
			<button type="button" class="back_btn" onclick="window.history.back()">返回</button>
	
	  	</div>
  </div>
</body>

<script>
function setBirthday() {
	WdatePicker({
		el : 'userBirthday',
		maxDate:'%y-%M-%d'
	});
}

$(function(){
	$("#userBirthday").click(setBirthday);
});

	
	var id =new tt.Field(" 账号 ","user.userId").setMsgId("userId_mgId");
	var userName =new tt.Field(" 姓名 ","user.userName").setMsgId("userName_mgId");
	var loginPd =new tt.Field(" 用户密码 ","loginPd").setMsgId("loginPd_mgId");
	var userEmail =new tt.Field(" 用户邮箱 ","user.userEmail").setMsgId("userEmail_mgId");
	var userPhone =new tt.Field(" 手机号码 ","user.userPhone").setMsgId("userPhone_mgId");
	var userBirthday =new tt.Field(" 出生日期 ","user.userBirthday").setMsgId("userBirthday_mgId");
	var userDept =new tt.Field(" 所在部门 ","user.userDept").setMsgId("userDept_mgId");
	var position =new tt.Field(" 职位 ","user.position").setMsgId("position_mgId");
	tt.vf.req.add(id,userName,loginPd,userBirthday,userDept,position);
	tt.vf.email.add(userEmail);
	tt.vf.mob.add(userPhone);
	new tt.LV().set(0, 15).add(id);
	new tt.LV().set(0, 15).add(userName);
	new tt.LV().set(0, 15).add(loginPd);
	new tt.LV().set(0, 20).add(userEmail);
	new tt.LV().set(0, 11).add(userPhone);

</script>
</html>