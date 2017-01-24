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
<script>
	$(function() {
		$( "#tabs" ).tabs();
	});
	
	//申请提现
function withdraw(){
	if(tt.validate()){
				var verifyCode=document.getElementById("phonecode").value;
				var reg = /^\d{6}$/;
				if(!reg.test(verifyCode)){
					$("#msg").html("校验码为6位数字！");
					return false;
				}		
			$.ajax({
				type: "post",
				url: "checkVerify.do",
				data:{  
					 "sysloginPojo.loginname":${sellerBankPojo.phone},
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
						if(confirm("确认申请提现吗？")){
							var val=Number($("input[name='manufacturerWithdrawPojo.withdrawAmount']").val());
							var url = "doWithdrawWebCheck.do?manufacturerWithdrawPojo.withdrawAmount="+val+"&sellerBankPojo.phone=${sellerBankPojo.phone}&phonecode="+verifyCode;	
							doOpreator(url,null);
					}
				}
				}
			  });

		}else{
			return ;
		}
	
}
function doOpreator(url,params){
	MAOWU.ajax.get(url, params, goRefreshPage);
}
 
function goRefreshPage(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("操作成功！");
		queryData("goWithdrawWebCount.do", "goWithdrawWebList.do?randquery="+rand);
	}else if(result=="2"){
		alert("申请后1小时后才能再次申请！");
	}else if(result=="3"){
	    alert("您还未绑定银行卡！");
	}else if(result=="4"){
	    alert("你的账户还未通过审核哦！");
	}else if(result=="5"){
	    alert("验证码错误！");
	}else{
		alert("操作失败！");
	}
}
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
            <td><input id="withdrawAmount" name="manufacturerWithdrawPojo.withdrawAmount" type="text" class="retrieve-form-tableInput" style="float:left" value=""/><span id="withdrawAmount_Msg"></span></td>
        </tr>
        <tr><td></td><td><span class="orange" style="font-size:12px;margin-left:10px;width:150px">
                                      注：  提现金额最少为100元；申请1小时后才能再次申请
                                        </span></td></tr>
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
            <td><input name="phonecode" id="phonecode" type="text" class="retrieve-form-tableInput02" style="float:left" />&nbsp&nbsp<input
						id="JS-vcodebtn" type="button" value="获取验证码"
						class="register-form-tableButton" onclick="test();"/><span id="JS-vcodelabel" style="display:none;" class="send-info"><em class="red">59</em>秒后 重新发送</span><span id="authcode"></span></td>
            <td class="retrieve-form-change">请输入收到的验证码</td>
        </tr>
        <tr><td></td><td>
                    	<label class="group-label J_groupLabel">
                    		<div id="captcha"></div>
                    	</label>
                    </td></tr>
         <tr><td></td><td>
                     <span style="font-size:18px;color:red;" id="msg" name="msg">
	                    ${msg }
	                </span>
	                </td></tr>
    </table>
     </form>
	               
    <input type="button"  value="提现" class="retrieve-register_button" id="sbutton" onclick="withdraw()">
</div>


<div class="clear"></div>
<jsp:include page="../sellerFooter.jsp"></jsp:include>
</body>
</html>
<script>
	var withdrawAmount = new tt.Field("提现金额","manufacturerWithdrawPojo.withdrawAmount").setMsgId("withdrawAmount_Msg");
	var authcode =new tt.Field(" 验证码   ","phonecode").setMsgId("authcode");
	//tt.Conf.reqStarCls = "";
	tt.vf.req.add(withdrawAmount,authcode);
	tt.vf.num.add(withdrawAmount);
	new tt.NRV().set(100, '++').add(withdrawAmount);
	//new tt.NRV().set(100, ${sellerBankPojo.balance}).add(withdrawAmount);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				var verifyCode=document.getElementById("phonecode").value;
				var reg = /^\d{6}$/;
				if(!reg.test(verifyCode)){
					$("#msg").html("校验码为6位数字！");
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
	var vcode =${sellerBankPojo.phone};
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