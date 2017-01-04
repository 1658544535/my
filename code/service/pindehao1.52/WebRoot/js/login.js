$(document).ready(function() {

	$('#dataform').submit(function() {
		return isEmpty();
	});
	
//	$("#sbutton").click(function() {
//		
//	});
});

function getPwd(){
	
	var login=document.getElementById("loginPd");
	login.value="";
	login.type="password";
}
function isEmpty(){
	
	var loginId = $('#loginId').val();
	var loginPd = $('#loginPd').val();
	$("#PwdNoticeContent").text("");
	$("#UserNoticeContent").text("");
	if(loginId == undefined||loginId=='UserName' || loginId.length < 1){
		$("#UserNoticeContent").text("请输入登录用户名！");
		$("#msg").text("");
		return false;
	}
	if(loginPd == undefined||loginPd=='Password' || loginPd.length < 1){
		$("#PwdNoticeContent").text("请输入登录密码！");
		$("#msg").text("");
		return false;
	}
	$("#PwdNoticeContent").text("");
	$("#UserNoticeContent").text("");
	return true;
}