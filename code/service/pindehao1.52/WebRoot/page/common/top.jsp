<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">  
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/spage.css"/>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>

<script type="text/javascript"	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagination/pagination.css" media="screen" rel="stylesheet">
<script type="text/javascript"	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/sys_util.js"></script>
	
<script language="javascript" type="text/javascript">
function showandhide(){
    if($("#search_show").css("display")=="none"){//隐藏
        $("#search_show").show();
		$("#searchBar").addClass("pack");
    }
    else{
        $("#search_show").hide();
		$("#searchBar").removeClass("pack");
    }
}
</script>
<script type="text/javascript" language="javascript">     
//调整js窗口
function reinitIframe(){  
	var iframe = document.getElementById("iframepage");  
	try{  
    	var bHeight = iframe.contentWindow.document.body.scrollHeight;  
    	var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
    	var height = Math.max(bHeight, dHeight);  
    	iframe.height = height;  
	}catch (ex){}  
}  
  
var timer1 = window.setInterval("reinitIframe()", 500); //定时开始  
  
function reinitIframeEND(){  
	var iframe = document.getElementById("iframepage");  
	try{  
    	var bHeight = iframe.contentWindow.document.body.scrollHeight;  
   		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
    	var height = Math.max(bHeight, dHeight);  
    	iframe.height = height;  
	}catch (ex){}  
	// 停止定时  
	window.clearInterval(timer1);  
}  
</script>
