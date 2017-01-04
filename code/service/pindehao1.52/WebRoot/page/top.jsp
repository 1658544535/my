<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib prefix="s" uri="/struts-tags"%>
<title>head</title>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/public.css"/>

</head>

<body>
<div class="header">
	<div class="logo_maowu">
	
	<a href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/page/right.jsp" target="workFrame"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/images/logo_bg.png" width="139" /></a>
	
	</div>
    <div class="head_user_info">
    	<table width="" border="0">
          <tr>
            <td align="right">
           	    <div class="head_user_btn">
           	    	${sessionScope.username}
           	    </div>
                <div class="head_user_btn">
                <a href="<%=request.getContextPath()%>/toUpdatePassword.do" target="workFrame"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/images/icon_key.png" align="absmiddle"/>修改密码</a>
                <!--<a href="<%=request.getContextPath()%>/goupdateuser.do?user.userId=${sessionScope.loginPojoId}&&showFlag=1" target="workFrame"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/images/icon_set.png" align="absmiddle"/>修改信息</a>-->
                <a href="<%=request.getContextPath()%>/loginout.do" target="_parent"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/images/icon_power.png" align="absmiddle"/>退 出</a>
                </div>
            </td>
            <td>
	            <div class="img">
	            
	            		<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/images/user_pic.jpg" onerror="this.src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/images/user_pic.jpg'" width="50" height="50" />
	            </div>
            </td>
          </tr>
        </table>
    </div>
    <div class="sys_name">
    	<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/images/name_b2c.png" />
    </div>
</div>
</body>
</html>
