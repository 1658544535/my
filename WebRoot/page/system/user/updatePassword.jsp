<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<html>
<head>

<title>Insert title here</title>
<!-- <link href="/css/content.css" media="screen"
	rel="stylesheet"> -->
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
			  $("#btn_sure").click(function(){
					if(tt.validate()){
						if($('#newPassword').val()!=$('#rePassword').val()){
							$('#rePassword_msg').text('   新密码与确认新密码不一致');
						}else{
							$.ajax({
								url:'updatePassword.do',
								type:'post',
								dataType:'json',
								data:{"loginId":$('#loginId').val(),"loginPd":$('#password').val(),"newPassword":$('#newPassword').val()},
								success:function(data){
									if(data.flag==0){
										alert('原始密码不正确');
									}else{
										alert('修改成功');
									}
								}
								
							});
						}
					}
				}); 
			 
			});
		 
		 function clearInfo(){
			 $('#rePassword_msg').text('');
		 }
		
		
	</script>
</head>
<body>

<div class="sub_wrap">
  <div class="s_nav"><a>系统管理</a> &gt; <a href="#" >用户管理</a> &gt; <a href="#">修改密码</a></div>
  <div class="h15"></div>
	
	<div>
    	
         <form method="post" action="updateuser.do" id="contentForm" enctype="multipart/form-data">
         		<input id="loginId" name="loginId" type="hidden" value="${sessionScope.loginname}"/>
         		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table" >
						
						<tr>
							<td align="right" class="grey" style="width: 400px;">原始密码：</td>
							<td>
								<input id="password" name="password" class="floatLeft" type="password" style="width:180px;height:28px" />
								<span id="password_msg"></span>
							</td>
						</tr>
						
						<tr>
							<td align="right" class="grey">新密码：</td>
							<td>
								<input id="newPassword" name="newPassword" class="floatLeft" type="password" style="width:180px;height:28px" />
								<span id="newPassword_msg"></span>
							</td>
						</tr>
						
						<tr>
							<td align="right" class="grey">确认新密码：</td>
							<td>
								<input id="rePassword" name="rePassword" class="floatLeft" type="password" style="width:180px;height:28px" onfocus="clearInfo()"/>
								<span id="rePassword_msg" style="font-size:12px;color:red;"></span>
							</td>
						</tr>
					</table>	
  				</form>
           	</div> 
			<div class="Btn_div">
  				<button type="button" class="ok_btn" id="btn_sure">确定</button>
				<button type="button" class="back_btn" onclick="window.history.back()">返回</button>
			
  			</div>
               
   </div>
</body>

<script>

	var password =new tt.Field(" 原始密码","password").setMsgId("password_msg");
	var newPassword =new tt.Field(" 新密码 ","newPassword").setMsgId("newPassword_msg");
	var rePassword =new tt.Field(" 确认新密码 ","rePassword").setMsgId("rePassword_msg");
	
	tt.vf.req.add(password,newPassword,rePassword);
	new tt.LV().set(0, 15).add(password);
	new tt.LV().set(0, 15).add(newPassword);
	new tt.LV().set(0, 15).add(rePassword);
</script>
</html>