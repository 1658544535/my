<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<link href="/css/faq.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="/js/base/operation/shop/shop.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<link type="text/css" rel="stylesheet" href="/js/testJSP/js/validate/css/validate.css" /> 
<script src="/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<link type="text/css" rel="stylesheet" href="/js/testJSP/js/validate/css/validate.css" /> 
<script type="text/javascript" src="/js/sys_util.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script>
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>
<title>淘竹马修改密码</title>
</head>

<body>
<jsp:include page="../sellerHeader.jsp"></jsp:include>
<div class="retrieve-form">
	<div class="retrieve-form-title"><img src="images/retrieve_03.jpg" alt="" width="17" height="17" />温馨提示：财务将尽快审核您的提现申请，请耐心等候。到账时间以银行受理时间为准</div>
	<form action="doRetrieve.do" method="post" id="from1">
	<table border="0" cellpadding="0" cellspacing="0" class="retrieve-form-table">
    	<tr>
        	<td class="register-form-tableTxt">可提现余额</td>
            <td><label>${sellerBankPojo.balance}</label></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">提现金额</td>
            <td><input id="loginname" name="SellerBankPojo.loginname" type="text" class="retrieve-form-tableInput" style="float:left" value=""/><span id="loginname_Msg"></span></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt">银行卡号</td>
        	 <td><label>${sellerBankPojo.bankCardNo}</label></td>
        </tr>
         <tr>
        	<td class="register-form-tableTxt">提现绑定手机号</td>
        	<td><label>${sellerBankPojo.phone}</label></td>
        </tr>
        <tr>
        	<td class="register-form-tableTxt" >验证码</td>
            <td><input name="retrievePojo.authcode" type="text" class="retrieve-form-tableInput02" style="float:left" />&nbsp&nbsp<input
						id="JS-vcodebtn" type="button" value="获取验证码"
						class="register-form-tableButton" onclick="sendverify();"/><span id="JS-vcodelabel" style="display:none;" class="send-info"><em class="red">59</em>秒后 重新发送</span><span id="authcode"></span></td>
            <td class="retrieve-form-change">请输入收到的验证码</td>
        </tr>

    </table>
     </form>
    <div class="retrieve-register_button" id="sbutton">确 定</div>
</div>


<div class="clear"></div>
<jsp:include page="../sellerFooter.jsp"></jsp:include>
</body>
</html>
<script>
var loginname =new tt.Field(" 登录名  ","retrievePojo.loginname").setMsgId("loginname_Msg");
var authcode =new tt.Field(" 验证码   ","retrievePojo.authcode").setMsgId("authcode");
var newpass =new tt.Field(" 新密码   ","retrievePojo.newpass").setMsgId("newpass");
var newpassaffirm =new tt.Field("密码确认  ","retrievePojo.newpassaffirm").setMsgId("newpassaffirm");

tt.vf.req.add(loginname,authcode,newpass,newpassaffirm);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
			var loginname2=document.getElementById("loginname2").value;
			var PATTERN_CHINAMOBILE = /^1(3[4-9]|5[012789]|8[23478]|4[7]|7[8])\d{8}$/;
			var PATTERN_CHINAUNICOM =/^1(3[0-2]|5[56]|8[56]|4[5]|7[6])\d{8}$/;
			var PATTERN_CHINATELECOM =/^1(3[3])|(8[019])\d{8}$/;
			var verifyCode;
			var checkbox=document.getElementById("selectcb"); 
			if(checkbox.checked==false){
			   $("#msg").html("你未同意注册协议！");
			   return false;
			}
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
						document.getElementById("form1").submit();
					}
				}
			  });
		     }						
			
		});
	});
	

</script>
<script type="text/javascript">
		function sendverify(){
			var vcode = $('#loginname').val();
			if(vcode == ""){
				alert("登录名不能为空！");
			}else{
			checkLoginname();
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
		
		function checkLoginname() {
		var loginname = $("#loginname").val();
		
		if (loginname!=null&&loginname!="") {
		
			MAOWU.ajax.post("checkLoginname.do", {
				"sysloginPojo.loginname" : loginname
			}, callback);
		}
		
	}
	function callback(value) {
		if (value == 1) {
			vcode_startRycleVbtn();
			var vcode = $('#loginname').val();
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
		}else{
			alert("该号码尚未注册！");
		}
	}
	
	</script>