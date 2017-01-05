<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
		<jsp:include page="../common_head.jsp"></jsp:include>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
    </head>
	<body>
		<%-- <jsp:include page="../sellerHeader.jsp"></jsp:include> --%>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/default.css" media="all" />
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/seller_common.css" type="text/css" media="all" />
		<script src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/js/base.js" type="text/javascript"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
		<script src="http://static.geetest.com/static/tools/gt.js"></script>
<div id="head">
	<div class="wrapper">
		<div class="main-logo ">
			<a class="main-logo-a" href="goSellerIndex.do" title="返回首页">
				&nbsp;
			</a>
			<span class="sec-title">
				<i class="b-inco">
				</i>
				<a href="goSellerIndex.do">
					商家中心
				</a>
			</span>
			<!-- <div id="st-nav">
				<a class="bold" href="goHelpWeb.do">
					帮助中心
				</a>
			</div> -->
		</div>
	</div>
</div>
<div id="container">
	<div class="ui-nav seller-nav">
		<div class="ui-nav-main  seller-nav-bg">
		</div>
	</div>
</div>
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <h1 class="seller-title">
                            找回密码
                        </h1>
                        <form action="doForgetPassword.do" accept-charset="utf-8" id="seller-account-passwd" class="ui-form">
                            <fieldset>
                                <%-- <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        <span class="ui-form-required">
                                            *
                                        </span>
                                        请输入旧密码
                                    </label>
                                    <input class="ui-input" name="oldPasswd" type="password" >
                                    <label for="" class="ui-form-other" id="sameword1">
                                        
                                    </label>
                                    <!-- <span class="ui-form-other">
                                        <a class="blue" href="#">
                                            忘记密码
                                        </a>
                                    </span> -->
                                </div> --%>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        手机号
                                    </label>
                                    <input type="text" class="ui-input" placeholder="您的手机号" name="sysLoginPojo.loginname2" value="" id="loginname2" onblur="javascript:checkVerifyCode()">
                                </div>
                                <div class="ui-form-item">
                            		<span id="loginname2Msg"></span>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        验证码
                                    </label>
                                    <style>
										.btn-bbnormal1 {
											height: 30px;
											line-height: 30px;
											color: #ccc;
											background: #fff;
											border: 1px solid #ccc;
											border-radius: 3px;
											padding: 0 8px;
											outline: 0;
											_overflow: hidden;
											display: inline-block;
											_display: inline;
											_zoom: 1
										}
									</style>
                                    <input id="JS-vcodebtn" type="button" disabled="disabled" value="获取验证码" class="fr btn-bbnormal1 J_getPhonecode" onclick="test()"/>
                            		<span id="JS-vcodelabel" style="display:none;" class="fr btn-bbnormal J_getPhonecode"><em class="red">60</em>秒后 重新发送</span>
                            		<input type="text" class="ui-input" value="" name="phonecode" placeholder="短信校验码" id="phonecode">
                                </div>
                                <div class="ui-form-item">
                                    <span id="phonecodeMsg"></span>
	                                <div id="captcha"></div>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        新登录密码
                                    </label>
                                    <input type="password" class="ui-input" placeholder="密码，6-16位字母、数字或符号" name="sysLoginPojo.password" value="" >
                                </div>
                                <div class="ui-form-item">
                            		<span id="passwordMsg"></span>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        确认新密码
                                    </label>
                                    <input type="password" class="ui-input" placeholder="确认密码" name="repasswd" value=""  >
                                </div>
                                <div class="ui-form-item">
                            		<span id="repasswdMsg"></span>
                                </div>
                                <div class="ui-form-item">
	                                <span class="red" id="msg" name="msg">
                                </div>
                                <div class="ui-form-item">
                                    <input id="query_btn" type="button" class="ui-button ui-button-lred" value="确认修改">
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
	</body>
</html>
<script type="text/javascript">
var loginname2 =new tt.Field("手机号","sysLoginPojo.loginname2").setMsgId("loginname2Msg");
var phonecode =new tt.Field("短信检验码","phonecode").setMsgId("phonecodeMsg");
var password =new tt.Field("密码","sysLoginPojo.password").setMsgId("passwordMsg");
var repasswd =new tt.Field("确认密码","repasswd").setMsgId("repasswdMsg");
tt.vf.req.add(loginname2,phonecode,password,repasswd);
tt.vf.num.add(phonecode);
new tt.LV().set(6,16).add(password,repasswd);
new tt.RV().set(new RegExp("^1(3[0-9]|4[57]|5[01256789]|7[68]|8[0-9])([0-9]{8})$"), "请输入正确手机号").add(loginname2);

/* function input(){
	var flag = true;
    var op1=$("input[name='oldPasswd']").val();
    var op2=$("input[name='newPasswd']").val();
    var op3=$("input[name='newPasswdRepeat']").val();
    var sw1=document.getElementById("sameword1");  
    var sw2=document.getElementById("sameword2"); 
    var sw3=document.getElementById("sameword3");
	if(op1==""){
        sw1.innerText="请输入旧密码！";
        flag = false;
    } else{
    	sw1.innerText="";
    }   
    if(op2==""){
        sw2.innerText="请输入新密码！";
        flag = false;
    } else if(op1 != "" && op2 != "" && op1 == op2){
    	sw2.innerText="新密码与旧密码相同，请换个！";
        flag = false;
    } else{
    	sw2.innerText="";
    }
    if(op3==""){
    	sw3.innerText="请再次输入新密码";
    	flag = false;
    } else if(op2!="" && op3!=""){
    	if(op2!=op3){
    		sw3.innerText="两次输入的密码必须一致！";
    		flag = false;
    	} 
    } else{
    	sw3.innerText="";
    }
    return flag;
}	 */
$(function(){	
 $("#query_btn").click(query);
});
function query() {
	/* if(input()){
		//queryData("passChangeWeb.do");
		$("#seller-account-passwd").submit();
	} */
	
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
					//document.getElementById("form1").submit();
					$("#seller-account-passwd").submit();
				}
			}
		});
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
  
  var v_geetest_challenge;
  var v_geetest_validate;
  var v_geetest_seccode;
  function sendverify(){
  	var vcode = $('#loginname2').val();
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
</script>