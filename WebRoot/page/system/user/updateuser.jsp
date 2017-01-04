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
			 
			/*  $("#btn_sure").click(function(){
				 var isTrue = true;
				 	if(!MAOWU.reg.isEmail($("#userEmail").val())){
						alert("邮箱输入有误，请重新输入");
						isTrue = false;
					}
					if(!MAOWU.reg.isPhone($("#userPhone").val())){
						alert("电话输入有误，请重新输入");
						isTrue = false;
					}
					if(isTrue){
						if(confirm("确认修改吗？")){
							document.getElementById("contentForm").submit();
						}
					}	
			 }); */
			 
 
			  $("#btn_sure").click(function(){
				
					if(tt.validate()){
						if (confirm("确认修改吗？")) {
							document.getElementById("contentForm").submit();
						}
					}
				}); 
			 
			});
		 
		
		
	</script>
</head>
<body>

<div class="sub_wrap">
  <div class="s_nav"><a>系统管理</a> &gt; <a href="#" >用户管理</a> &gt; <a href="#">修改用户信息</a></div>
  <div class="h15"></div>
	
	<div>
    	
         <form method="post" action="updateuser.do" id="contentForm" enctype="multipart/form-data">
         	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table" >

						<tr>
							<td align="right" class="grey" style="width: 15%">账号：</td>
							<td style="width: 40%">
								<input id="userId" name="user.userId" type="hidden"  value="<s:property value="user.userId" />"  readonly="readonly" class="floatLeft"/>
								<s:property value="user.userId" />
							</td>
							
							<td align="right" class="grey" style="width: 10%">姓名：</td>
							<td style="width: 35%">
								<input id="userName" name="user.userName" type="text" class="floatLeft" value="<s:property value="user.userName" />" />
								<span id="userName_mgId"></span>
							</td>
							
						</tr>
						
					
						<tr>
							<td align="right" class="grey">性别：</td>
							<td> 
								<input type="radio" value="01" name="user.userSex" id="userSex"  <c:if test="${user.userSex.equals('01')}"> checked="checked"</c:if>>男
				               	<input type="radio" value="02" name="user.userSex" id="userSex" <c:if test="${user.userSex.equals('02')}"> checked="checked"</c:if>>女
			               	</td>
			               	<td align="right" class="grey">邮箱地址：</td>
							<td>
								<input id="userEmail" name="user.userEmail" type="text" class="floatLeft" value="<s:property value="user.userEmail" />" />
								<span id="userEmail_mgId"></span>
							</td>
						</tr>
						
						<s:if test="showFlag!=1">
							<tr>
								<td align="right" class="grey">所在部门：</td>
								<td>
									<s:action name="departmentCombox" executeResult="true">
										<s:param name="user.userDept">
											<s:property value="#request.user.userDept" />
										</s:param>
									 </s:action>
									 <span id="userDept_mgId"></span>
								</td>
								
								<td align="right" class="grey">职位：</td>
								<td>
									<s:action name="positionCombox" executeResult="true" >
									 	<s:param name="position">
											<s:property value="#request.user.position" />
										</s:param> 
									</s:action>
									<span id="position_mgId"></span>
								</td>
							</tr>
						</s:if>
						
						<tr>
							<td align="right" class="grey">手机号码：</td>
							<td>
								<input id="userPhone" name="user.userPhone" type="text" class="floatLeft" value="${user.userPhone}"/>
								<span id="userPhone_mgId"></span>
							</td>
							<td align="right" class="grey">出生日期：</td>
							<td>
								<input id="userBirthday"
								name="user.userBirthday" type="text" class="floatLeft" value="${user.userBirthday}" readonly="readonly"> 
								<img id="calendarImg" src="/js/My97DatePicker/skin/datePicker.gif" width="16" class="floatLeft"
								height="22" align="absmiddle" />
							   <span id="userBirthday_mgId"></span>
							 </td>
						</tr>
						
						
						<tr>
					        <td align="right" class="grey" width="15%">修改照片:</td>	
					        <td>
					        	<input type="file" class="floatLeft" name="upfile" class="floatLeft" id="userPic" value="${user.userPic}">
					        </td>
							<td align="right" class="grey">旺旺号：</td>
							<td>
								<input type="text" name="user.userWWID" class="floatLeft" name="user.userWWID" value="<s:property value="user.userWWID" />" class="floatLeft" maxlength="50">
								<span id="userWWID_mgId"></span>
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
function setBirthday() {
	WdatePicker({
		el : 'userBirthday'
	});
}

$(function(){
	$("#userBirthday").click(setBirthday);
});

	var userName =new tt.Field(" 姓名 ","user.userName").setMsgId("userName_mgId");
	var userEmail =new tt.Field(" 用户邮箱 ","user.userEmail").setMsgId("userEmail_mgId");
	var userPhone =new tt.Field(" 手机号码 ","user.userPhone").setMsgId("userPhone_mgId");
	var userBirthday =new tt.Field(" 出生日期 ","user.userBirthday").setMsgId("userBirthday_mgId");
	var userDept =new tt.Field(" 所在部门 ","user.userDept").setMsgId("userDept_mgId");
	var position =new tt.Field(" 职位 ","user.position").setMsgId("position_mgId");
	
	tt.vf.req.add(userName,userBirthday,userDept,position);
	tt.vf.email.add(userEmail);
	tt.vf.mob.add(userPhone);
	new tt.LV().set(0, 15).add(userName);
	new tt.LV().set(0, 20).add(userEmail);
	new tt.LV().set(0, 11).add(userPhone);

</script>
</html>