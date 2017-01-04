<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/public.css"/>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/login.js"></script>

<script type="text/javascript">
function ale()
{
     alert("请联系管理人员！");
	}
</script>
<style type="text/css">
.login_bg{background:#df434e; height:330px;}
.login_div{background:url(<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/images/login_bg_b2c.jpg) no-repeat; width:952px; height:620px; margin:0 auto; position:absolute; z-index:99; top:0; left:15%}
.loginBox{margin-left:500px;}
.loginBox table td{line-height:26px; padding-top:10px;}
.loginBox a{ font-size:12px; color:#999999; text-decoration:none}
.loginBox a:hover{ color:#ea5b24; text-decoration:underline;}
.inputUser{ width:300px; height:32px; line-height:32px; color:#333; padding:0 3px; background:#f5f5f5 url(../images/loginbox_bg.png) right 0 no-repeat; border:#cdcdcd 2px solid; border-radius:5px; }
.inputUser:hover,.inputUser:focus{ background:#fff url(<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/images/loginbox_bg.png) right -35px no-repeat; border:#ea5b24 2px solid;}
.inputPass{ width:300px; height:32px; line-height:32px; color:#333; padding:0 3px; background:#f5f5f5 url(../images/loginbox_bg.png) right -70px no-repeat; border:#cdcdcd 2px solid;margin:0 0 15px 0; border-radius:5px;}
.inputPass:hover,.inputPass:focus{background:#fff url(<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/images/loginbox_bg.png) right -105px no-repeat; border:#ea5b24 2px solid;}
.login_btn{ padding:0 20px; height:34px; background-color:#ea5b24; color:#fff; font-size:16px; border:0;}
</style>
</head>
<body>
<div class="login_bg">
</div>

<div class="login_div">
	<div style="height:350px;"></div>
    <div class="loginBox">
    <form id="dataform" name="dataform" action="doLogin.do" method="post">
    	<table width="300" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td colspan="2">
             用户名： <input id="loginId" name="loginId" class="inputUser"  type="text"/><div id="UserNoticeContent"></div>
            </td>
          </tr>
          <tr>
            <td colspan="2">
             密码： <input id="loginPd" name="loginPd" class="inputPass" 	type="password"/><div id="PwdNoticeContent"></div>
            </td>
          </tr>
          <tr>
            <td><a onclick=ale()>忘记密码？</a></td>
            <td align="right"><button id="sbutton" class="login_btn">登陆</button></td>
          </tr>
        </table>
	</form>
	<div id="msg"><s:property value="msg" /></div>
  </div>
</div>
</body>
</html>
