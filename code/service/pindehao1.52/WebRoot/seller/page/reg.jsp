<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
		<jsp:include page="common_head.jsp"></jsp:include>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/homecsslib.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/member_register.css" type="text/css" media="all" />
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
            <div class="f-fr">
                <a class="mb-guarantee-logo" href="#" title="淘竹马正品保证-100%正品,全场包邮,7天无理由退货-淘竹马">
                </a>
            </div>
        </div>
        <!-- 内容区 -->
        <div class="m-wrapper-1080 m-mb-content">
            <!-- 左侧广告位 -->
            <div class="fl m-mb-amscont">
                
            </div>
            <!-- 右侧输入区域 -->
            <div class="fr m-ipt-cont">
                <form id="form1" action="doRegWeb.do" class="m-ipt-area J_registerForm" method="" accept-charset="utf-8">
                    <input type="hidden" id="is_used" value="0" />
					<input type="hidden" id="is_phonecodetrue" value="0" />
                    <div class="area-title">
                        新用户注册，欢迎加入！
                    </div>
                    <!-- 帐号 -->
                    <div class="ipt-group">
                        <label class="group-label J_groupLabel">
                            <span class="identi">
                                <i class="iconfont">&#xf012d;</i>
                            </span>
                            <input type="text" class="ipt-input J_iptUsername" placeholder="您的帐号"  name="sysLoginPojo.loginname" value="" id="loginname">
                            <span id="loginnameMsg"></span>
                            <!-- <div class="single-notice J_singleNotice" data-error=""><i class="iconfont">&#xf0142;</i>请输入正确的手机号</div>
                            <div class="u-phonecode-getting-msg J_phonecodeGettingMsg">校验码已发送到尾号为 2955 的手机，请查收</div> -->
                        </label>
                    </div></br>
                    <div class="ipt-group">
                        <label class="group-label J_groupLabel">
                            <span class="identi">
                                <i class="iconfont">&#xf012d;</i>
                            </span>
                            <input type="text" class="ipt-input J_iptUsername" placeholder="您的手机号"  name="sysLoginPojo.loginname2" value="" id="loginname2" onblur="javascript:checkVerifyCode()">
                            <span id="loginname2Msg"></span>
                            <div class="single-notice J_singleNotice" data-error=""><i class="iconfont">&#xf0142;</i>请输入正确的手机号</div>
                            <div class="u-phonecode-getting-msg J_phonecodeGettingMsg">校验 码已发送到尾号为 2955 的手机，请查收</div>
                        </label>
                    </div></br>
                    <!-- 图片验证码 -->
                    <%-- <div class="ipt-group  J_checkcodeCont">
                        <label class="group-label J_groupLabel">
                            <span class="identi">
                                <i class="iconfont">&#xf00cb;</i>
                            </span>
                            <img class="f-fr checkcode-img J_checkcodeImg" title="看不清？换一张" src="http://d.beibei.com/checkcode/show.html">
                            <input type="text" class="ipt-input ipt-input-checkcode J_iptCheckcode" placeholder="验证码" name="check" maxlength="4" value="" >
                            <span id="checkMsg"></span>
                            <div class="single-notice J_singleNotice" data-error="">
                            </div>
                        </label>
                    </div></br> --%>
                    <!-- 短信校验码 -->
                    <div class="ipt-group">
                        <label class="group-label J_groupLabel">
                            <span class="identi">
                                <i class="iconfont">&#xf0132;</i>
                            </span>
                            <!-- <a href="" class="fr btn-bbnormal J_getPhonecode" id="JS-vcodebtn">
                                免费获取校验码
                            </a> -->
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
                    <%-- <div class="ipt-tip">
                        <span>
                            注册即接受
                            <a href="#" target="_blank">
                                《淘竹马使用协议》
                            </a>
                            和
                            <a href="#" target="_blank">
                                《社区使用协议》
                            </a>
                        </span>
                    </div> --%>
                    <div class="ipt-btn-area">
                        <input id="sbutton" type="button" value="注册" class="ipt-btn J_iptBtn" style="border:none;width:100%;" />
                    </div>
                    <div class="ipt-quick">
                        <span class="f-fr">
                            <span>
                                已有账号？
                            </span>
                            <a class="u-hp-target" href="sellerLogin.do">
                                直接登录
                            </a>
                        </span>
                        <%-- <span class="f-fl">
                            快捷登录方式：
                        </span>
                        <a class="f-fl ipt-quick-login ipt-quick-qq" href="#"
                        title="QQ账号登录">
                        </a>
                        <a class="f-fl ipt-quick-login ipt-quick-tb" href="#"
                        title="淘宝账号登录">
                        </a>
                        <a class="f-fl ipt-quick-login ipt-quick-wx" href="#"
                        title="微信账号登录">
                        </a> --%>
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
			&nbsp;
				<!-- <a rel="nofollow" href="index.html">
					首页
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					关于淘竹马
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					商家入驻
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					正品保证
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					人才招聘
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					联系我们
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					帮助中心
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					友情链接
				</a>
				|
				<a rel="nofollow" target="_blank" href="#">
					手机客户端
				</a>
				|
				<a rel="nofollow" target="_blank" href="help.html">
					客服在线
				</a> -->
			</div>
			<div class="footer-content">
				<div class="wrapper">
					<p>
                    	广东群宇互动科技有限公司 Copyright © 2014 All Rights Reserved 版权所有 粤ICP备13081564号
					</p>
				</div>
			</div>
		</div>
    </body>
</html>
<script>
//logintt.Conf.reqStarCls = "";
var loginname =new tt.Field("帐号","sysLoginPojo.loginname").setMsgId("loginnameMsg");
var loginname2 =new tt.Field("手机号","sysLoginPojo.loginname2").setMsgId("loginname2Msg");
//var check =new tt.Field("验证码","check").setMsgId("checkMsg");
var phonecode =new tt.Field("短信检验码","phonecode").setMsgId("phonecodeMsg");
var password =new tt.Field("密码","sysLoginPojo.password").setMsgId("passwordMsg");
var repasswd =new tt.Field("确认密码","repasswd").setMsgId("repasswdMsg");
tt.vf.req.add(loginname,loginname2,/* check, */phonecode,password,repasswd);
//tt.vf.num.add(loginname2);
tt.vf.num.add(phonecode);
//new tt.LV().set(11, 11).add(loginname2);
//new tt.LV().set(6,30).add(loginname);
new tt.LV().set(6,16).add(password,repasswd);
new tt.RV().set(new RegExp("^1(3[0-9]|4[57]|5[01256789]|7[68]|8[0-9])([0-9]{8})$"), "请输入正确手机号").add(loginname2);
new tt.RV().set(new RegExp("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,30}$"), "为(6-30个)字母加数字的组合").add(loginname);

$(document).ready(function() {
	$("#sbutton").click(function(){		
		if(checkLoginname() == false){
			return;
		}
		
		if(tt.validate()){
			var loginname2=document.getElementById("loginname2").value;
			var PATTERN_CHINAMOBILE = /^1(3[4-9]|5[012789]|8[23478]|4[7]|7[8])\d{8}$/;
			var PATTERN_CHINAUNICOM =/^1(3[0-2]|5[56]|8[56]|4[5]|7[6])\d{8}$/;
			var PATTERN_CHINATELECOM =/^1(3[3])|(8[019])\d{8}$/;
			var verifyCode;
			if(PATTERN_CHINAMOBILE.test(loginname2)||PATTERN_CHINAUNICOM.test(loginname2)||PATTERN_CHINATELECOM.test(loginname2)){
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
			
			//MAOWU.ajax.post("checkVerify.do", {
				//"sysloginPojo.loginname" : loginname2,
				//"verifyCode":verifyCode
			//}, checkYanzheng1);
			//setTimeout(function(){
				//if($("#is_phonecodetrue").val() != 1){
					//document.getElementById("form1").submit();
				//}
			//}, 1000);
			$.ajax({
				type: "post",
				url: "checkVerify.do",
				data:{  
					 "sysloginPojo.loginname":loginname2,
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
	//alert(2);
	var vcode = $('#loginname2').val();
	//if(checkLoginname() != false){
	//setTimeout(function(){
	//if($("#is_used").val()==1){
		//alert("该手机号码已经注册过，请重新输入！");
	//}else{
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
	//}
	//}, 1000);
	//}
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
	var phoneVal = $("#loginname2").val();
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