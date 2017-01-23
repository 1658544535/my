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
		<jsp:include page="../common_head.jsp"></jsp:include>
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
    </head>
<body>
<jsp:include page="../sellerHeader.jsp"></jsp:include>
	<div id="content" class="wrapper">
		<div class="pure-g admin-wrapper">
			<div class="pure-u-1 main">
				<style type="text/css">
.ui-form fieldset {
	border: 0 none;
	margin: auto;
	padding: 60px 0 100px;
	width: 500px;
}

input.ui-button {
	padding: 0 1.5em;
}

.uploadify {
	position: relative;
	margin-bottom: 1em;
}

.uploadify-button {
	background-color: #ccc;
	background-image: linear-gradient(bottom, #ccc 0%, #eee 100%);
	background-image: -o-linear-gradient(bottom, #ccc 0%, #eee 100%);
	background-image: -moz-linear-gradient(bottom, #ccc 0%, #eee 100%);
	background-image: -webkit-linear-gradient(bottom, #ccc 0%, #eee 100%);
	background-image: -ms-linear-gradient(bottom, #ccc 0%, #eee 100%);
	background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #ccc),
		color-stop(1, #eee));
	background-position: center top;
	background-repeat: no-repeat;
	-webkit-border-radius: 30px;
	-moz-border-radius: 30px;
	border-radius: 2px;
	border: 1px solid #888;
	color: #666;
	font: bold 12px Arial, Helvetica, sans-serif;
	text-align: center;
	width: 100%;
}

.uploadify:hover .uploadify-button {
	background-color: #ccc;
	background-image: linear-gradient(top, #ccc 0%, #eee 100%);
	background-image: -o-linear-gradient(top, #ccc 0%, #eee 100%);
	background-image: -moz-linear-gradient(top, #ccc 0%, #eee 100%);
	background-image: -webkit-linear-gradient(top, #ccc 0%, #eee 100%);
	background-image: -ms-linear-gradient(top, #ccc 0%, #eee 100%);
	background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #ccc),
		color-stop(1, #eee));
	background-position: center bottom;
}

.upload-tax-registration-certificate {
	background-image:
		url(http://b5.hucdn.com/upload/seller/1510/19/45551263126827_1000x727.jpg);
	background-size: 120px 100px;
}

.upload-general-taxpayer-qualification {
	
}

.modify-stauts {
	display: block;
}

.u-note-tip {
	position: relative;
	top: 4px;
	left: 6px;
	cursor: pointer;
	*z-index: 20;
}

.u-note-tip .ui-poptip-blue {
	display: none;
	position: absolute;
	width: 325px;
	height: 196px;
	top: 22px;
	left: -155px;
	*z-index: 100;
}

.u-note-tip li {
	list-style: circle;
	margin-left: 16px;
}

.uploadify {
	position: relative;
	height: 180px;
	width: 180px;
	text-align: center;
	border: 1px solid #ddd;
}

.uploadPreview_note {
	width: 180px;
	height: 180px;
	line-height: 180px;
}

.uploadPreview_imgfile {
	position: absolute;
	width: 100%;
	height: 100%;
	left: 0;
	top: 0;
	opacity: 0;
	z-index: 3;
	filter: alpha(opacity = 0);
	cursor: pointer;
}

em {
	font-size: larger;
}
</style>

				<div class="ui-tipbox ui-tipbox-success" style="display:none;">
					<div class="ui-tipbox-icon">
						<i class="iconfont">&#xf0156;</i>
					</div>
					<!--<div class="ui-tipbox-content">
						<h3 class="ui-tipbox-title">退回原因</h3>
						<font color="red" style='word-wrap: break-word;' class="returnCont">${manufacturerPojo.returnContent}</font>
					</div>-->
				</div>
				<s:if test="sellerBankPojo.status == 1">
				<div class="ui-tipbox ui-tipbox-success">
					<div class="ui-tipbox-icon">
						<i class="iconfont">&#xf0156;</i>
					</div>
				</s:if>
				<s:else>
				<div class="ui-tipbox ui-tipbox-error">
					<div class="ui-tipbox-icon">
						<i class="iconfont">&#xf0155;</i>
					</div>
				</s:else>
					<div class="ui-tipbox-content">
						<h3 class="ui-tipbox-title">操作提示</h3>
						<c:if test="${sellerBankPojo.status == 1}">
							<p class="ui-tipbox-explain">审核已通过</p>
						</c:if>
						<c:if test="${sellerBankPojo.status == 0}">
							<p class="ui-tipbox-explain">等待管理员审核</p>
						</c:if>
						<!--<c:if test="${manufacturerPojo.status == 0 && (manufacturerPojo.returnContent != '' && manufacturerPojo.returnContent != null)}">
							<p class="ui-tipbox-explain">退回修改</p>
						</c:if>-->
					</div>
				</div> 
<form action="editSellerBank.do" method="post" id="form1" enctype="multipart/form-data" class="ui-form">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
					<input type="hidden" name="sellerBankPojo.id" id="sellerBankPojo.id" value="${sellerBankPojo.id}" class="inputText" />
					<h2> 账户设置</h2>
					<hr></hr>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 开户银行：</label>
							<input type="text" name="sellerBankPojo.bankName"  value="${sellerBankPojo.bankName}" class="ui-input" id="ticketName">
							<div id="bankName_mgId"></div>
						</div>
					<div class="ui-form-item">
								<label for="" class="ui-label"> 开户银行省市：</label>
							&nbsp;
							        <select id="province" name="sellerBankPojo.province" class="floatLeft" style="width:120px;float:left"></select>
							        <select id="city" name="sellerBankPojo.city" class="floatLeft" style="width:120px;float:left"></select>
							        <select id="area" name="sellerBankPojo.area" class="floatLeft" style="width:120px;float:left"></select>
							<div><span id="province_mgId"></span>
							<span id="city_mgId"></span>
							<span id="area_mgId"></span></div>
						</div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 开户支行：</label>
							<input type="text" name="sellerBankPojo.bankBranch"  value="${sellerBankPojo.bankBranch}" class="ui-input" id="ticketName">
							<div id="bankBranch_mgId"></div>
						</div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 银行卡号：</label>
							<input type="text" name="sellerBankPojo.bankCardNo" value="${sellerBankPojo.bankCardNo}" class="ui-input" id="ticketName">
							<div id="bankCardNo_mgId"></div>
						</div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 再次输入银行卡号：</label>
							<input type="text" name="reBankCardNo" value="${sellerBankPojo.bankCardNo}" class="ui-input" id="ticketName">
							<div id="reBankCardNo_mgId"></div>
						</div>
					<div class="ui-form-item">
							<label for="" class="ui-label"> 开户人姓名：</label>
							<input type="text" name="sellerBankPojo.userName" value="${sellerBankPojo.userName}" class="ui-input" id="ticketName">
							<div id="userName_mgId"></div>
						</div>		
		            <div class="ui-form-item">
                        <label for="" class="ui-label"> 手机号：</label>
                            <input type="text" class="ui-input"  name="sellerBankPojo.phone" value="${sellerBankPojo.phone}" id="loginname2" onblur="javascript:checkVerifyCode()">
                            <div id="loginname2Msg"></div>
                            <div class="u-phonecode-getting-msg J_phonecodeGettingMsg">校验 码已发送到尾号为 2955 的手机，请查收</div>
                    </div></br>
                    <!-- 短信校验码 -->
                    <div class="ui-form-item">
                         <label for="" class="ui-label"> 手机验证码：</label>
                            <!-- <a href="" class="fr btn-bbnormal J_getPhonecode" id="JS-vcodebtn">
                                免费获取校验码
                            </a> -->
                            <span id="JS-vcodelabel" style="display:none;" class="btn-bbnormal J_getPhonecode" style="float:left"><em class="red">60</em>秒后 重新发送</span>
                            <input type="text" class="ui-input" value="" name="phonecode" id="phonecode">
                            <input id="JS-vcodebtn" type="button" disabled="disabled" value="获取验证码"
									 onclick="test()"/>
                            <div class="single-notice J_singleNotice" data-error="">
                            </div>
                            <div class="u-nophonecode-msg J_noPhonecodeMsg">
                                <span>
                                    没收到校验码？ 请通过 400-668-0000 电话联系客服
                                </span>
                            </div>
                            <span id="phonecodeMsg"></span>
                    </div></br>
                    <div class="ui-form-item">
                    	<label class="group-label J_groupLabel">
                    		<div id="captcha"></div>
                    	</label>
                    </div>
              <div class="ui-form-item">
	                <span style="font-size:18px;color:red;" id="msg" name="msg">
	                    ${msg }
	                </span>
	                </div>
						<div class="ui-form-item">
							<input type="button" class="ui-button ui-button-lred" value="提交" id="sbutton">
						</div>
			</div>
		</div>
	</div>
</div>
	<jsp:include page="../sellerFooter.jsp"></jsp:include>
</body>
</html>
<script>
var bankName =new tt.Field("开户银行","sellerBankPojo.bankName").setMsgId("bankName_mgId");
var province =new tt.Field("开户银行省","sellerBankPojo.province").setMsgId("province_mgId");
var city =new tt.Field("开户银行市","sellerBankPojo.city").setMsgId("city_mgId");
var area =new tt.Field("开户银行区","sellerBankPojo.area").setMsgId("area_mgId");
var bankBranch =new tt.Field("开户支行","sellerBankPojo.bankBranch").setMsgId("bankBranch_mgId");
var bankCardNo =new tt.Field("银行卡号","sellerBankPojo.bankCardNo").setMsgId("bankCardNo_mgId");
var reBankCardNo =new tt.Field("再次输入银行卡号","reBankCardNo").setMsgId("reBankCardNo_mgId");
var userName =new tt.Field("开户人姓名","sellerBankPojo.userName").setMsgId("userName_mgId");
var phone =new tt.Field("手机号","sellerBankPojo.phone").setMsgId("loginname2Msg");
var phonecode =new tt.Field("手机验证码","phonecode").setMsgId("phonecodeMsg");
tt.vf.req.add(bankName,province,city,area,bankBranch,bankCardNo,reBankCardNo,userName,phone,phonecode);
tt.vf.num.add(phonecode);
//new tt.LV().set(11, 11).add(loginname2);
//new tt.LV().set(6,30).add(loginname);
//new tt.LV().set(6,16).add(password,repasswd);
new tt.RV().set(new RegExp("^1(3[0-9]|4[57]|5[01256789]|7[68]|8[0-9])([0-9]{8})$"), "格式有误！").add(phone);
//new tt.RV().set(new RegExp("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,30}$"), "为(6-30个)字母加数字的组合").add(loginname);

		$(document).ready(function() {
		$("#sbutton").click(function(){		
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
			
			if($("input[name='sellerBankPojo.bankCardNo']").val() != $("input[name='reBankCardNo']").val()){
				$("#msg").html("两次银行卡号不一样！");
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
		
		select1();
		$('#province').bind("change", select2);
        $('#city').bind("change", select3);
	});	
	
	function select1() {
		$("#province").append("<option value=''>- 请选择 -</option>");
		$.ajax(
		{
			type: "post",
			url: "getSysAreaByPid.do?sysAreaPojo.pid=0",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					var selectedStr = "";
                    if("${sellerBankPojo.province}" == o_msg[i].id){
                    	selectedStr = "selected='selected'";
                    }
					$("#province").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
				}
				if("${sellerBankPojo.province}"!=null && "${sellerBankPojo.province}"!=""){
					select2();
				}
			}
		})
	};
	function select2() {
            $("#city").html("");
            $("#city").append("<option value=''>- 请选择 -</option>");
            $.ajax(
            {
                type: "post",
                url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#province').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                    	var selectedStr = "";
                    	if("${sellerBankPojo.city}" == o_msg[i].id){
                    		selectedStr = "selected='selected'";
                    	}
                        $("#city").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
                    }
                    if("${sellerBankPojo.city}"!=null && "${sellerBankPojo.city}"!=""){
						select3();
					}
                }
            })
        };
	function select3() {
            $("#area").html("");
             $("#area").append("<option value=''>- 请选择 -</option>");
            $.ajax(
            {
                type: "post",
                url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#city').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                    	var selectedStr = "";
                    	if("${sellerBankPojo.area}" == o_msg[i].id){
                    		selectedStr = "selected='selected'";
                    	}
                        $("#area").append("<option value=" + o_msg[i].id + " "+selectedStr+"> " + o_msg[i].name + "</option>");
                    }
                }
            })
        };



function checkVerifyCode(){
	var phoneVal = $("#loginname2").val();
	var reg = /^\d{11}$/;
	if(!reg.test(phoneVal)){
		$("#captcha").empty();
		$("#JS-vcodebtn").attr("class","btn-bbnormal1 J_getPhonecode");
		$("#JS-vcodebtn").attr("disabled","disabled");
	}else{
		$("#JS-vcodebtn").attr("class","btn-bbnormal J_getPhonecode");
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
   //验证码       
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
  
</script>
</script>