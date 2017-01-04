<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>淘竹马玩具分销平台</title>
<meta name="keywords" content="淘竹马 M2C分销平台 群宇互动 玩具产品 母婴用品一只发货 玩具厂家直销 玩具渠道 玩具电商 品牌玩具 " />
<meta name="description" content="欢迎登陆淘竹马。广东群宇互动科技有限公司旗下平台淘竹马，是全球专业M2C玩具分销平台，主要提供各类玩具产品的一站式分销服务，即玩具产品从生产商直接到分销商的平台模式。淘竹马以厂家直销、主营玩具、一只发货为特色，专注于为玩具厂商和分销商服务，致力整合玩具渠道，打造专属玩具供应商和采购商的一站式玩具分销平台。" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/public.css"/>
<script type="text/javascript" src="/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/login.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<style type="text/css">
.login_bg{background:#df434e; height:330px;}
.login_div{background:url(/images/new_bg_login.jpg) no-repeat; width:952px; height:620px; margin:0 auto;z-index:99; margin-top:-330px;}
.loginBox{margin-left:500px;}
.loginBox table td{line-height:26px; padding-top:10px;}
.loginBox a{ font-size:12px; color:#999999; text-decoration:none}
.loginBox a:hover{ color:#ea5b24; text-decoration:underline;}
.inputUser{ width:300px; height:32px; line-height:32px; color:#333; padding:0 3px; background:#f5f5f5 url(../images/loginbox_bg.png) right 0 no-repeat; border:#cdcdcd 2px solid; border-radius:5px; }
.inputUser:hover,.inputUser:focus{ background:#fff url(../images/loginbox_bg.png) right -35px no-repeat; border:#ea5b24 2px solid;}
.inputPass{ width:300px; height:32px; line-height:32px; color:#333; padding:0 3px; background:#f5f5f5 url(../images/loginbox_bg.png) right -70px no-repeat; border:#cdcdcd 2px solid;margin:0 0 15px 0; border-radius:5px;}
.inputPass:hover,.inputPass:focus{background:#fff url(../images/loginbox_bg.png) right -105px no-repeat; border:#ea5b24 2px solid;}
.login_btn{ padding:0 20px; height:34px; background-color:#ea5b24; color:#fff; font-size:16px; border:0;}
</style>
</head>
<body>
<div class="login_bg">
</div>

<div class="login_div">
	<div style="height:350px;position:relative">
		<a href="goIndexWeb.do">	
		<div style="width:370px; height:70px;position:  absolute; top:250px;left:520px;">
		</div>
		</a>
	</div>
    <div class="loginBox">
    <form id="dataform" name="dataform" action="doLoginWebDone.do" method="post">
    	<table width="300" border="0" cellpadding="0" cellspacing="0">
    	<input name="url" id="url" value="${url}" class="inputText" type="hidden" >
          <tr>
            <td colspan="2">
             账号： <input id="loginId" name="loginId" class="inputUser"  type="text"/><div id="UserNoticeContent"></div>
            </td>
          </tr>
          <tr>
            <td colspan="2">
             密码： <input id="loginPd" name="loginPd" class="inputPass" 	type="password"/><div id="PwdNoticeContent"></div>
            </td>
          </tr>
          <tr>
            <td><a href="goRetrieve.do">忘记密码？</a>&nbsp;&nbsp;<a href="goRegister.do">马上注册</a></td>
            <td align="right"><button id="sbutton" class="login_btn">登陆</button></td>
          </tr>
        </table>
	</form>
	<div id="msg"><s:property value="msg" /></div>
  </div>
</div>
</body>
</html>
