<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/content.css" media="screen" rel="stylesheet"></link>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
    <base href="">
    
    <title>My JSP 'update.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    
    <h1><font color="red">Update User</font></h1>
    
    <s:form action="DD.do">
    	
    	<table>
    		<tr>
    			<td>
    				<s:hidden name="user.id" value="%{user.id}"></s:hidden>
    			</td>
    		</tr>
    		
    		<tr>
    			<td>
    				<s:textfield name="roleId" value="%{roleLists.roleId}" label="%{getText('roleId')}"></s:textfield>
    			</td>
    		</tr>
    		
    		<tr>
    			<td>
    				<s:textfield name="roleName" value="%{roleLists.roleName}" label="%{getText('roleName')}"></s:textfield>
    			</td>
    		</tr>
    		
    		<tr>
    			<td>
    				<s:textfield name="user.age" value="%{user.age}" label="%{getText('age')}"></s:textfield>
    			</td>
    		</tr>
    		
    		<tr>
    			<td>
    				<s:submit></s:submit>
    			</td>
    		</tr>
    	</table>
    	
    </s:form>
    <script >

	var merName =new tt.Field(" 商家名称 ","roleName").setMsgId("merName_msgId");



	//tt.vf.num.add(shopPeopleNum,shopLongitude,shopLatitude);  	//必须为数字
new tt.LV().set(0, 30).add(merName);
	tt.vf.req.add(merName);   	//为必输项

	

</script >
  </body>
  
</html>
