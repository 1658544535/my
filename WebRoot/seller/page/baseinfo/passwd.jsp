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
		<jsp:include page="../sellerHeader.jsp"></jsp:include>
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <h1 class="seller-title">
                            修改密码
                        </h1>
                        <form action="passChangeWeb.do" accept-charset="utf-8" id="seller-account-passwd" class="ui-form">
                            <fieldset>
                                <div class="ui-form-item">
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
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        <span class="ui-form-required">
                                            *
                                        </span>
                                        请输入新密码
                                    </label>
                                    <input id="newPasswd1" class="ui-input " name="newPasswd" value="" type="password" >
                                    <label for="" class="ui-form-other" id="sameword2">
                                        
                                    </label>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label">
                                        <span class="ui-form-required">
                                            *
                                        </span>
                                        再次输入的新密码
                                    </label>
                                    <input id="newPasswd2" class="ui-input " name="newPasswdRepeat" value="" type="password">
                                    <label for="" class="ui-form-other" id="sameword3">
                                        
                                    </label>
                                </div>
                                <div class="ui-form-item">
                                    <input id="query_btn" type="button" class="ui-button ui-button-lred" value="保存">
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
		</div>
		<div class="m-feedback J_feedback">
		</div>
		<div class="m-fb-shadow J_fb-shadow">
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
	</body>
</html>
<script type="text/javascript">
function input(){
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
}	
$(function(){	
 $("#query_btn").click(query);
});
function query() {
	if(input()){
		//queryData("passChangeWeb.do");
		$("#seller-account-passwd").submit();
	}
}
</script>
