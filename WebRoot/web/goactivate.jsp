<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="/js/testJSP/js/validate/css/validate.css" />
<script type="text/javascript" src="/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script src="/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>
<script type="text/javascript" src="/js/sys_util.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘宝用户激活</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn_sure").click(function() {
			if (tt.validate()) {
				if (document.getElementById("checkRadio").checked) {
					var loginname=document.getElementById("loginname").value;
					if(loginname==""){
						$("#loginname_mgId").text("*手机号码为必填项!").css("color","red");
						return false;
					}
					var PATTERN_CHINAMOBILE = /^1(3[4-9]|5[012789]|8[23478]|4[7]|7[8])\d{8}$/;
					var PATTERN_CHINAUNICOM =/^1(3[0-2]|5[56]|8[56]|4[5]|7[6])\d{8}$/;
					var PATTERN_CHINATELECOM =/^1(3[3])|(8[019])\d{8}$/;
					if(PATTERN_CHINAMOBILE.test(loginname)||PATTERN_CHINAUNICOM.test(loginname)||PATTERN_CHINATELECOM.test(loginname)){
						document.getElementById("registerForm").submit();
					}else{
						$("#loginname_mgId").text("请输入正确手机号码！").css("color","red");
						return false;
					}
					
					
				} else {
					alert("请选择同意条款");
				}

			}

		});

	});
	function onTextfocusTel(){
		$("#loginname").val("");
		$("#loginname_mgId").text("*手机号码为必填项!").css("color","red");
	}
	function checkLoginname() {
		var loginname = $("#loginname").val();
		var PATTERN_CHINAMOBILE = /^1(3[4-9]|5[012789]|8[23478]|4[7]|7[8])\d{8}$/;
		var PATTERN_CHINAUNICOM =/^1(3[0-2]|5[56]|8[56]|4[5]|7[6])\d{8}$/;
		var PATTERN_CHINATELECOM =/^1(3[3])|(8[019])\d{8}$/;
		if(PATTERN_CHINAMOBILE.test(loginname)||PATTERN_CHINAUNICOM.test(loginname)||PATTERN_CHINATELECOM.test(loginname)){
			$("#loginname_mgId").text("");
		}else{
			$("#loginname_mgId").text("请输入正确手机号码！").css("color","red");
			return false;
		}
	}
	
		function sendverify(){
			var vcode = $('#loginname').val();
			if(vcode == ""||vcode==" 手机号码即登录账号"){
				alert("手机号码不能为空！");
			}else{
				if($("#is_used").val()==1){
				
				}else{
					vcode_startRycleVbtn();
					$.ajax({
						url:'sendVerify.do?userVerifyPojo.loginname='+vcode,
						type:'post',
						dataType:'json',
						error: function(XMLHttpRequest, textStatus, errorThrown){
						alert("发送验证码失败");
				    	},
				    	success: function(result){
			 		   		if(result == 1){
				    			alert("发送验证码成功");
				  	  		}
				    	}
					});
				}
			}
		}
		var vcode_leftTime = 60;
		var vcode_timer = null;
		function vcode_startRycleVbtn() {
			if (vcode_leftTime > 1) {
				vcode_leftTime--;
				$("#JS-vcodelabel").html('<em class="red">' + vcode_leftTime + '</em>秒后 重新发送');
				$("#JS-vcodelabel").show();
				$("#JS-vcodebtn").hide();
				vcode_timer = setTimeout(vcode_startRycleVbtn, 1000);
			} else {
				if (vcode_timer) clearTimeout(vcode_timer);
					vcode_leftTime = 60;
					$("#JS-vcodelabel").hide();
					$("#JS-vcodebtn").show();
			}
		}
	//判断验证
	function onTextfocusYanzheng(){
		$("#verifyCode_mgId").text("*验证码为必填项！").css("color","red");
	}
	function onTextblurYanzheng(){
		var verifyCode=document.getElementById("yanzheng").value;
		var phone=document.getElementById("loginname").value;
		var reg = /^\d{6}$/;
		if(!reg.test(verifyCode)){
			$("#verifyCode_mgId").text("*验证码为6位数字！").css("color","red");
			return false;
		}
		MAOWU.ajax.post("checkVerify.do", {
				"sysloginPojo.loginname" : phone,
				"verifyCode":verifyCode
				}, checkYanzheng1);
	}
	function checkYanzheng1(value){
		if (value == 0) {
			$('#verifyCode_mgId').text('验证码错误，请重新输入！').css("color","red");
			return false;
		}else{
			$('#verifyCode_mgId').text('').css("color","green");
		}
	}
</script>
<style type="text/css">
	.box-shadow {
box-shadow: 0 1px 2px #D1D1D1;
}
.mt-25 {
margin-top: 25px;
}
.wrapper {
margin: 0 auto;
position: relative;
width: 1190px;
}
.pure-g {
text-rendering: optimizespeed;
overflow:hidden;
}
.pure-u-7-10 {
width: 700px;
float:left;
}
#m-member-panel {
margin: 30px 0 60px 60px;
padding: 30px 0 0;
border-right: 1px solid #ccc;
min-height: 350px;
}
#m-member-panel h2 {
margin: 0 0 40px 15px;
border-left: 4px solid #CA3C3C;
padding-left: 10px;

}
h2 {
display: block;
font-size: 1.5em;
font-weight: bold;
letter-spacing:normal;
}
#m-member-panel form {
color: #666; margin:0; display:block;
}
.pure-form-aligned input{display: inline-block;
vertical-align: middle;}
.pure-form fieldset {
margin: 0;
padding: 0.35em 0 0.75em;
border: 0;
}
.pure-form-aligned .pure-control-group {
margin-bottom: 15px;
letter-spacing:normal;
}
.pure-form-aligned .pure-control-group label {
text-align: right;
display: inline-block;
vertical-align: middle;
width: 10em;
margin: 0 1em 0 0;
}
#m-member-panel form {
color: #666;
}
#m-member-panel .pure-input-1-3 {
margin-right: 12px;
}
.pure-form input[type=text], .pure-form input[type=password] {
height: 32px;
font-size: 12px;
padding-top: 8px;
}
.pure-form .pure-input-1-3 {
width: 33%;
padding: 0.5em 0.6em;
display: inline-block;
border: 1px solid #ccc;
box-shadow: inset 0 1px 3px #ddd;
border-radius: 2px;
-webkit-box-sizing: border-box;
-moz-box-sizing: border-box;
box-sizing: border-box;
}
.c-999{ color:#999; letter-spacing:normal; }
.pure-form-aligned .pure-controls {
margin: 1.5em 0 0 11em;
}
.c-999 a {
color: #3E3E3E;
text-decoration: none;
}
.c-999 a:hover{ color:#df434e;}
.pure-button-main {
background: #df434e;
/*border-color: #c6375e;*/
color: #fff;
border:1px solid #CC3333;
border-radius:3px;
text-shadow: 0 1px 1px rgba(0,0,0,.2);

}
#m-member-panel #submit-btn {
font-size: 14px;
}
.pure-button {
padding: .5em 1.5em;
}
.pure-u-3-10 {
width: 300px;
display: inline-block;
zoom: 1;
letter-spacing: normal;
word-spacing: normal;
vertical-align: top;
text-rendering: auto;
}
#m-login-tip {
text-align: center;
padding-top: 50px;
}
p {
margin: 0 0 8px;
display:block;
}
.mt-50 {
margin-top: 50px;
}
#m-open-login {
list-style-type: none;
margin: 15px 85px 0;
padding: 0;
text-align: left;
}
#m-open-login li {
background-color: #fff;
border: 1px solid #ddd;
border-radius: 4px;
cursor: pointer;
height: 24px;
line-height: 24px;
margin: 5px 0;
transition: border .2s linear 0s,-moz-box-shadow .2s linear 0s;
padding-left: 10px;
}
li {
display: list-item;
text-align: -webkit-match-parent;
}
.icons.qq-icon {
background-position: 0 -55px;
}
.icons {
background: url(http://s1.husor.cn/image/base/icons.png) no-repeat -1000px 0;
display: inline-block;
height: 18px;
line-height: 18px;
padding-left: 22px;
}
a {
color: #3E3E3E;
text-decoration: none;
}
.pure-button-green {
background: #df434e;
border-color: #CC3333;
}
.icons {
background: url(http://s1.husor.cn/image/base/icons.png) no-repeat -1000px 0;
display: inline-block;
height: 18px;
line-height: 18px;
padding-left: 22px;
}
#m-open-login li:hover {
border: 1px solid #ccc;
box-shadow: 0 0 7px #ccc;
}
.icons.taobao-icon {
background-position: 0 0;
}
.white-bg {
background: #ffffff;
margin-bottom: 10px;
overflow: hidden;
}

#main-nav {
height: 38px;
position: relative;
background: #df434e;
width:100%;
margin-top:10px;
}
#head .wrapper {
z-index: 200;
z-index: 301;
position: relative;
}
#main-nav .nav-item.current, #main-nav .nav-item.current:hover {
/*background: #cf2f3b;*/
color: #fff;
text-decoration: none;
line-height:38px;
}
#main-nav .nav-item {
/*background: #df434e;*/
float: left;
font-size: 16px;
height: 38px;
position: relative;
width: 120px;
color: #fff;
font: 700 15px/38px "";
text-align: center;
text-decoration: none;
line-height:38px;

}
.nav-item:hover{ background:#cf2f3b;}
.f-12 {
font-size: 12px;
}
.f-12:hover{ color:#df434e;}
</style>

<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}

.tab1{width:516px;margin:32px 0 0 auto;}
.menu{height:28px; font-size:12px; font-family:"微软雅黑";}
.menu li{float:left;width:29px;text-align:center;line-height:28px;height:28px;cursor:pointer;color:#666;font-size:14px;overflow:hidden; font-weight: bold;}
.menu li.off{/*background:#FFFFFF*/;color:#df434e;font-weight:bold;}

.menudiv{/*height:200px;*/border-left:#cccccc solid 1px;border-right:#cccccc solid 1px;border-top:0;background:#fefefe}
.menudiv div{/*padding:15px;*/line-height:28px;}
.sea-Txt-line{ line-height:28px; float:left;}
</style>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="top02-width">
	<a href="goIndexWeb.do"><div class="logo"></div></a>
		<div class="tab1" id="tab1" >
	<div class="menu">
		<ul>
			<li id="one1" onclick="setTab('one',1)">产品</li>
			<span class="sea-Txt-line">｜</span>
			<li id="one2" onclick="setTab('one',2)">店铺</li>
			
		</ul>
	</div>
	<div class="menudiv">
	<div id="con_one_1">
	<form action="searchWeb.do" method="post" id="sysform">
	<div class="search" style=" margin-top:0px;">
	
	<input name="productPojo.productName" type="text" value="搜索 商品" onfocus="this.value=&#39;&#39;" onblur="if(this.value==&#39;&#39;){this.value=&#39;搜索 商品&#39;}" class="search-input">
	<button type="submit" class="search-button" onclick="searchBtn()">搜 索</button></div>
	</form>  </div>
	<div id="con_one_2" style="display:none;">
	<form action="searchWeb.do" method="post" id="shopform">
	<div class="search" style=" margin-top:0px;">
	
	<input name="shop.name" type="text" value="搜索 店铺" onfocus="this.value=&#39;&#39;" onblur="if(this.value==&#39;&#39;){this.value=&#39;搜索 店铺&#39;}" class="search-input">
	<button type="submit" class="search-button" onclick="searchShop()">搜 索</button></div>
	</form>  </div>
		
	</div>
</div>
</div> 

<div class="clear"></div>
<div id="main-nav">
        <div class="wrapper">
            
            <a href="goIndexWeb.do" class="nav-item current">首页</a>
            <a href="goNewWeb.do" class="nav-item ">新品快订</a>
            <a href="goshopWeb.do" class="nav-item ">
                店铺街
            </a>
            <a href="infoPageList.do" class="nav-item ">行业资讯</a>
          
    	</div>
    </div>

	<div class="register-schedule">
		<div class="register-schedule-L">请绑定你的手机</div>
		<div class="register-schedule-R">绑定成功</div>
		<div class="clear"></div>
		<div class="register-schedule-Pic">
			<img src="images/register_06.jpg" alt="" width="660" height="44" />
		</div>
	</div>
	<div class="register-form">
		<form id="registerForm" action="doBound.do" method="post">
			<input type="hidden" id="is_used" value="0" />
			<input type="hidden" id="is_usedname" value="0" />
			<table border="0" cellpadding="0" cellspacing="0"
				class="register-form-table">
				<div id="msg" class="red" style="text-align: center;margin-top:20px;"><s:property value="msg" /></div>
				<tr>
					<td class="register-form-tableTxt">手机号码</td>
					<td><input
						id="loginname" name="sysloginPojo.loginname" value=" 手机号码即登录账号" onfocus="javascript:onTextfocusTel()"
						onblur="javascript:checkLoginname()" type="text" 
						class="register-form-tableInput" /><span id="loginname_mgId" style="font-size:12px;color:red;"></span><input
						id="JS-vcodebtn" type="button" value="获取验证码"
						class="register-form-tableButton" onclick="sendverify();"/>&nbsp;
						<span id="JS-vcodelabel" style="display:none;" class="send-info"><em class="red">59</em>秒后 重新发送</span>
					</td>
				</tr>
				<tr>
					<td class="register-form-tableTxt">手机验证码</td>
					<td><input name="verifyCode" type="text" onfocus="javascript:onTextfocusYanzheng()" onBlur="javascript:onTextblurYanzheng()"
						class="register-form-tableInput" id="yanzheng" /><span id="verifyCode_mgId"></span></td>
				</tr>
			</table>
		<div class="register-agree">
			<input id="checkRadio" type="checkbox"  checked ='checked' value="" /> <font
				color="#4c4c4c">同意</font><a href="serviceWeb.do" target="blank">&nbsp;《淘竹马服务协议》</a>
		</div>
		<div>
			<input type="button" id="btn_sure" value="立即绑定"
				class="register-register_button" />
		</div>
		</form>
	</div>	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
