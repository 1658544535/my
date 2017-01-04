<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理页面</title>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/content.css" media="screen" rel="stylesheet">
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/common.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/sys_util.js"></script>

<script type="text/javascript">
	$().ready(function() {
				$(".query_user").click(
						function() {
							var request_path = "queryUser.do?";
							//为空的就不要传过去
							var username = $("#username").val().trim();
							var userdept = $("#depart").val().trim();
                           var flag = false;
                           if(!MAOWU.lang.isEmpty(username)){
								request_path +="userName="
									+ username ;
								flag = true;
							}  
                           if(!MAOWU.lang.isEmpty(userdept)){
                        	   if(flag){
                        		   request_path +="&userDept="
   									+ userdept;   
                        	   }else{
                        		   request_path +="userDept="
   									+ userdept ;
                        	   }
							}  
							$(window.parent.document).find("#mainiframe").attr(
									"src", request_path); 
						});
				
				
		});
	 
				function doDel(val){
					var request_path = "deleteuser.do?userId=" + val;
					if(confirm("确定要删除吗？")){
						$(window.parent.document).find("#mainiframe").attr(
								"src", request_path); 
					}
					
				}
	  	

 			
</script>
</head>
<body>

	<div class="top-div">
		<div class="top-title-div">
			<span class="top-title">用户管理页面</span>
		</div>
		<hr class="top-hr">
		<div class="top-main-div">
			<div class="top-main-title-div">
				<span class="top-main-title"></span>
			</div>
			<div class="top-main-content-div">
				<table class="top-mian-content-table">
					<tr>
						<td>姓名:</td>
						<td><input type="text" id="username" name="username"></td>
					</tr>
					<tr>
						<td>所在部门:</td>
						<td>
							<%-- <select style="width:156px;" name="u" id="userDept" name="userDept" >
								<s:iterator value="#request.BusinessDictList" id="us">
									<option value="<s:property value="#us.businessDictId"/>">
										<s:property value="#us.businessDictName" /></option>
								</s:iterator>
							</select> --%> <s:action name="departmentCombox"
								executeResult="true">
								<s:param name="userDept">
									<s:property value="userDept" />
								</s:param>
							</s:action>

						</td>

					</tr>
				</table>
			</div>
			<div class="top-main-bottom-div">
				<span class="top-main-bottom-button"><button
						class="query_user">查询</button></span>
			</div>
		</div>
	</div>
	<div class="center-div">
		<div class="center-main-div">
			<display:table name="${userList}" class="simple" pagesize="10"
				id="row" requestURI="gomanageuser.do">
				<display:column property="userId" title="账号" />
				<display:column property="userName" title="姓名" />
				<display:column property="userSex" title="用户性别" />
				<display:column property="userEmail" title="用户邮箱" />
				<display:column property="userPhone" title="电话号码" />
				<display:column property="businessDictName" title="所在部门" />
				<display:column property="userBirthday" title="用户生日" />
				<display:column title="操作">
					<a href="#" onclick="doDel('${row.userId}')">删除</a>
					<a href="goupdateuser.do?userId=${row.userId}">修改</a>


				</display:column>
			</display:table>
		</div>
		<div class="center-main-botomm-div">
			<span class="center-main-bottom-button1"><a
				href="goadduser.do">添加用户</a></span>
		</div>
	</div>
	<div class="bottom-div"></div>

</body>
</html>


