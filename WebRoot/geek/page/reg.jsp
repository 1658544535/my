<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>淘竹马创客中心</title>
		<jsp:include page="common_head.jsp"></jsp:include>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/geek/css/homecsslib.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/geek/css/member_register.css" type="text/css" media="all" />
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<script src="http://static.geetest.com/static/tools/gt.js"></script>
		<style>
			.btn-bbnormal1 {
				height: 30px;
				line-height: 30px;
				color: #ccc;
				background: #fff;
				border: 1px solid #ccc;
				/*-webkit-transition: color .3s, background-color .3s;
				-moz-transition: color .3s, background-color .3s;
				-o-transition: color .3s, background-color .3s;
				transition: color .3s, background-color .3s;
				-webkit-border-radius: 3px;*/
				border-radius: 3px;
				padding: 0 8px;
				outline: 0;
				_overflow: hidden;
				display: inline-block;
				_display: inline;
				_zoom: 1
			}
		</style>
    </head>
	<body>
        <!-- 头部 -->
        <div class="m-wrapper-1080 m-mb-header">
            <div class="f-fl">
                <a class="mb-main-logo" href="#" title="淘竹马-妈妈的特卖会">
                </a>
            </div>
	        <div class="f-fr f-nav">
	            <a href="geekLogin.do" style="font-family:Microsoft YaHei;">登录</a>
	            <a class="active" href="goGeekRegWeb.do" style="font-family:Microsoft YaHei;">注册</a>
	        </div>
        </div>
        <!-- 内容区 -->
        <div class="m-wrapper-1080 m-mb-content">
            <!-- 左侧广告位 -->
            <div class="fl m-mb-amscont">
                
            </div>
            <!-- 右侧输入区域 -->
            <div class="fr m-ipt-cont">
                <form id="form1" action="doGeekRegWeb.do" class="m-ipt-area J_registerForm" method="" accept-charset="utf-8">
                    <input type="hidden" id="is_used" value="0" />
					<input type="hidden" id="is_phonecodetrue" value="0" />
                    <div class="area-title" style="font-family:Microsoft YaHei;">
                        新用户注册，欢迎加入！
                    </div>
                    <!-- 帐号 -->
                    <!-- <div class="ipt-group">
                        <label class="group-label J_groupLabel">
                            <span class="identi">
                                <i class="iconfont">&#xf012d;</i>
                            </span>
                            <input type="text" class="ipt-input J_iptUsername" placeholder="您的昵称"  name="sysLoginPojo.name" value="" id="name">
                            <span id="nameMsg"></span>-->
                            <!-- <div class="single-notice J_singleNotice" data-error=""><i class="iconfont">&#xf0142;</i>请输入正确的手机号</div>
                            <div class="u-phonecode-getting-msg J_phonecodeGettingMsg">校验码已发送到尾号为 2955 的手机，请查收</div> -->
                       <!--  </label>
                    </div></br> -->
                    <div class="ipt-group">
                        <label class="group-label J_groupLabel">
                            <span class="identi">
                                <i class="iconfont">&#xf012d;</i>
                            </span>
                            <input type="text" class="ipt-input J_iptUsername" placeholder="您的手机号"  name="sysLoginPojo.loginname" value="" id="loginname" onblur="javascript:checkVerifyCode()">
                            <span id="loginnameMsg"></span>
                            <div class="single-notice J_singleNotice" data-error=""><i class="iconfont">&#xf0142;</i>请输入正确的手机号</div>
                            <div class="u-phonecode-getting-msg J_phonecodeGettingMsg">校验码已发送，请查收</div>
                        </label>
                    </div></br>
                    <!-- 短信校验码 -->
                    <div class="ipt-group">
                        <label class="group-label J_groupLabel">
                            <span class="identi">
                                <i class="iconfont">&#xf0132;</i>
                            </span>
                            <input id="JS-vcodebtn" type="button" disabled="disabled" value="获取验证码"
									class="fr btn-bbnormal1 J_getPhonecode" onclick="test()"/>
                            <span id="JS-vcodelabel" style="display:none;" class="fr btn-bbnormal J_getPhonecode"><em class="red">60</em>秒后 重新发送</span>
                            <input type="text" class="ipt-input ipt-input-phonecode J_iptPhonecode" value="" name="phonecode" placeholder="短信校验码" id="phonecode">
                            <div class="single-notice J_singleNotice" data-error="">
                            </div>
                            <div class="u-nophonecode-msg J_noPhonecodeMsg">
                                <span>
                                    没收到校验码？ 请通过 400-668-0000 电话联系客服
                                </span>
                            </div>
                            <span id="phonecodeMsg"></span>
                        </label>
                    </div></br>
                    <div class="ipt-group">
                    	<label class="group-label J_groupLabel">
                    		<div id="captcha"></div>
                    	</label>
                    </div>
                    <!-- 密码 -->
                    <div class="ipt-group">
                        <label class="group-label J_groupLabel">
                            <span class="identi">
                                <i class="iconfont">&#xf00c9;</i>
                            </span>
                            <input type="password" class="ipt-input J_iptPasswd" placeholder="密码，6-16位字母、数字或符号" name="sysLoginPojo.password" value="" >
                            <span id="passwordMsg"></span>
                            <div class="single-notice J_singleNotice" data-error="123">
                            </div>
                        </label>
                    </div></br>
                    <!-- 确认密码 -->
                    <div class="ipt-group">
                        <label class="group-label J_groupLabel">
                            <span class="identi">
                                <i class="iconfont">&#xf00c9;</i>
                            </span>
                            <input type="password" class="ipt-input J_iptRepasswd" placeholder="确认密码" name="repasswd" value=""  >
                            <div class="single-notice J_singleNotice" data-error="">
                            </div>
                            <span id="repasswdMsg"></span>
                        </label>
                    </div></br>
                    <div class="ipt-btn-area">
                        <input id="sbutton" type="button" value="注册" class="ipt-btn J_iptBtn" style="border:none;width:100%;font-family:Microsoft YaHei;"/>
                    </div>
                    <div class="ipt-quick">
                        <span class="f-fr">
                            <span style="font-family:Microsoft YaHei;">
                                已有账号？
                            </span>
                            <a class="u-hp-target" href="geekLogin.do" style="font-family:Microsoft YaHei;">
                                直接登录
                            </a>
                        </span>
                    </div>
                    <div class="ipt-quick">
	                <span class="u-hp-target" id="msg" name="msg">
	                    ${msg }
	                </span>
	                </div>
                </form>
            </div>
        </div>
        <!-- 页脚 -->
        <div id="footer">
			<div class="footer-nav">
				<div class="tzm">
                    <div class="app_down">
                        <a class="ios" href="https://itunes.apple.com/cn/app/tao-zhu-ma-yi-ge-ji-yu-da/id1025618713?l=en&mt=8" title="iphone下载"></a>
                        <a class="android" href="http://android.myapp.com/myapp/detail.htm?apkName=com.ruiyu.taozhuma" title="android下载"></a>
                    </div>
                    <div class="app_wechat"></div>
                </div>
			</div>
			<!-- <div class="footer-content">
				<div class="wrapper">
					<p>
                    	广东群宇互动科技有限公司 Copyright © 2014 All Rights Reserved 版权所有 粤ICP备13081564号
					</p>
				</div>
			</div> -->
		</div>
    </body>
</html>
<script>
//logintt.Conf.reqStarCls = "";
var loginname =new tt.Field("手机号","sysLoginPojo.loginname").setMsgId("loginnameMsg");
//var check =new tt.Field("验证码","check").setMsgId("checkMsg");
var phonecode =new tt.Field("短信检验码","phonecode").setMsgId("phonecodeMsg");
var password =new tt.Field("密码","sysLoginPojo.password").setMsgId("passwordMsg");
var repasswd =new tt.Field("确认密码","repasswd").setMsgId("repasswdMsg");
tt.vf.req.add(/*vname,*/loginname,/* check, */phonecode,password,repasswd);
tt.vf.num.add(phonecode);
new tt.LV().set(6,16).add(password,repasswd);
new tt.RV().set(new RegExp("^1(3[0-9]|4[57]|5[01256789]|7[68]|8[0-9])([0-9]{8})$"), "请输入正确手机号").add(loginname);
//new tt.RV().set(new RegExp("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,30}$"), "为(6-30个)字母加数字的组合").add(vname);

$(document).ready(function() {
	$("#sbutton").click(function(){		
		if(checkLoginname() == false){
			return;
		}
		
		if(tt.validate()){
			var loginname=document.getElementById("loginname").value;
			var PATTERN_CHINAMOBILE = /^1(3[4-9]|5[012789]|8[23478]|4[7]|7[8])\d{8}$/;
			var PATTERN_CHINAUNICOM =/^1(3[0-2]|5[56]|8[56]|4[5]|7[6])\d{8}$/;
			var PATTERN_CHINATELECOM =/^1(3[3])|(8[019])\d{8}$/;
			var verifyCode;
			if(PATTERN_CHINAMOBILE.test(loginname)||PATTERN_CHINAUNICOM.test(loginname)||PATTERN_CHINATELECOM.test(loginname)){
				verifyCode=document.getElementById("phonecode").value;
				var reg = /^\d{6}$/;
				if(!reg.test(verifyCode)){
					$("#msg").html("校验码为6位数字！");
					return false;
				}
			}else{
				$("#msg").html("请输入正确手机号码！");
				return false;
			}
			
			if($("input[name='sysLoginPojo.password']").val() != $("input[name='repasswd']").val()){
				$("#msg").html("两次密码不一样！");
				return false;
			}
			
			$.ajax({
				type: "post",
				url: "checkVerify.do",
				data:{  
					 "sysloginPojo.loginname":loginname,
					 "verifyCode":verifyCode
				}, 
				dataType: 'json',
				async: false,
				success: function (msg) {
					if (msg == 0) {
						$("#msg").html('验证码错误，请重新输入！');
						return false;
					}else{
						$("#msg").html('');
						document.getElementById("form1").submit();
					}
				}
			});
		}
	});
});

var v_geetest_challenge;
var v_geetest_validate;
var v_geetest_seccode;
function sendverify(){
	var vcode = $('#loginname').val();
	vcode_startRycleVbtn();
	$.ajax({
		url:'sendVerify.do?userVerifyPojo.loginname='+vcode+'&geetest_challenge='+v_geetest_challenge+'&geetest_validate='+v_geetest_validate+'&geetest_seccode='+v_geetest_seccode,
		type:'post',
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("发送校验码失败");
    	},
    	success: function(result){
		   	if(result == 1){
    			alert("发送校验码成功");
  	  		}
    	}
	});
}
var vcode_leftTime = 60;
var vcode_timer = null;
function vcode_startRycleVbtn() {
	//alert(1)
	if (vcode_leftTime > 1) {
		$("#JS-vcodebtn").attr("disabled","disabled");
		vcode_leftTime--;
		$("#JS-vcodelabel").html('<em class="red">' + vcode_leftTime + '</em>秒后 重新发送');
		$("#JS-vcodelabel").show();
		$("#JS-vcodebtn").hide();
		vcode_timer = setTimeout(vcode_startRycleVbtn, 1000);
	} else {
		$("#JS-vcodebtn").removeAttr("disabled"); 
		if (vcode_timer) clearTimeout(vcode_timer);
			vcode_leftTime = 60;
			$("#JS-vcodelabel").hide();
			$("#JS-vcodebtn").show();
	}
	
}

function checkLoginname() {
	var loginname = $("#loginname").val();
		$.ajax({
			type: "post",
			url: "checkLoginname.do",
			data:{  
				 "sysloginPojo.loginname":loginname
			}, 
			dataType: 'json',
			async: false,
			success: function (msg) {
				if (msg == 1) {
					//$("#msg").html('该手机号码已经注册过，请重新输入！');
					$("#msg").html('该会员帐号已经注册过，请重新输入！');
					$("#is_used").val("1");
					return false;
				}else{
					$("#msg").html('');
					$("#is_used").val("0");
				}
			}
		});
}

function checkVerifyCode(){
	var phoneVal = $("#loginname").val();
	var reg = /^\d{11}$/;
	if(!reg.test(phoneVal)){
		$("#captcha").empty();
		$("#JS-vcodebtn").attr("class","fr btn-bbnormal1 J_getPhonecode");
		$("#JS-vcodebtn").attr("disabled","disabled");
	}else{
		$("#JS-vcodebtn").attr("class","fr btn-bbnormal J_getPhonecode");
		$('#JS-vcodebtn').removeAttr("disabled");
	}
}
</script>
<script>
  function test(){
	  $("#captcha").empty();
	  var handler = function (captchaObj) {
	        // 将验证码加到id为captcha的元素里
	        captchaObj.appendTo("#captcha").bindOn('#JS-vcodebtn');
	        captchaObj.onSuccess(function() { 
	       	v_geetest_challenge = $('.geetest_challenge').val();
	       		v_geetest_validate = $('.geetest_validate').val();
	       		v_geetest_seccode = $('.geetest_seccode').val();
	       		sendverify();
	         });
	    };
	   $.ajax({
	       // 获取id，challenge，success（是否启用failback）
	       url: "v2.1/getValidCode.do",
	       type: "get",
	       dataType: "json", // 使用jsonp格式
	       success: function (data) {
	           // 使用initGeetest接口
	           // 参数1：配置参数，与创建Geetest实例时接受的参数一致
	           // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
	           initGeetest({
	               gt: data.gt,
	               challenge: data.challenge,
	               product: "popup", // 产品形式
	               offline: !data.success
	           }, handler);
	       }
	   });
  }
</script>