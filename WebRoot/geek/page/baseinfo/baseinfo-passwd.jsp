<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>淘竹马创客中心</title>
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/css/default.css" media="all" />
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/css/seller_common.css" type="text/css" media="all" />
</head>

<body>
 <jsp:include page="../geekHeader.jsp"/>
    <div id="content" class="wrapper">
            <div class="pure-g admin-wrapper">
                <div class="pure-u-1 main">
                    <h1 class="seller-title">修改密码</h1>
                    <form action="passChangeGeekWeb.do" method="" accept-charset="utf-8" id="seller-account-passwd" class="ui-form">
                        <div class="ui-form-item">
                            <label for="" class="ui-label">
                                <span class="ui-form-required">*</span> 请输入旧密码
                            </label>
                            <input class="ui-input" name="oldPasswd" type="password">
                             <label for="" class="ui-form-other" id="sameword1">
                                        
                             </label>
                        </div>
                        <div class="ui-form-item">
                            <label for="" class="ui-label">
                                <span class="ui-form-required">*</span> 请输入新密码
                            </label>
                            <input class="ui-input " name="newPasswd" type="password">
                             <label for="" class="ui-form-other" id="sameword2">
                                        
                             </label>
                        </div>
                        <div class="ui-form-item">
                            <label for="" class="ui-label">
                                <span class="ui-form-required">*</span> 请再次输入新密码
                            </label>
                            <input class="ui-input " name="newPasswdRepeat" type="password">
                             <label for="" class="ui-form-other" id="sameword3">
                                        
                             </label>
                        </div>
                        <div class="ui-form-item">
                            <input id="query_btn" type="button" class="ui-button ui-button-lred" value="保存">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../geekFooter.jsp"/>
    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/js/base.js" type="text/javascript"></script>
    
</body>
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

</html>
