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
<script type="text/javascript" src="js/_head.js"></script>
<script>
function search(){
		document.getElementById("idform").submit();
	}
</script>
<title>淘竹马玩具分销平台</title>
</head>

<body class="body">
<jsp:include page="header.jsp"></jsp:include>

<div class="top02-width">
	<div class="logo02"></div>
</div> 

<div class="clear"></div>

<div class="help-nav-width">
	<div class="help-nav">
    	<ul>
        	<li><a href="goHelpWeb.do">首 页</a></li>
            <li><a href="goSelfServiceWeb.do"><span>自主服务</span></a></li>
            <li><a href="goFaqWeb.do">常见问题</a></li>
            <li><a href="goContact.do">联系客服 </a></li>
            <li><a href="goFeedBackWeb.do">意见反馈</a></li>
        </ul>
    </div>
</div>

<div class="self_service01">
	<div class="self_service01-L">
    	<div class="self_service01-L"><img src="images/self_service_03.jpg" alt="" width="78" height="78" /></div>
        <div class="self_service01-L-Txt">
        	<div class="self_service01-L-Txt01">找回密码</div>
            <div class="self_service01-L-Txt02">忘记密码了？点此自助找回您的密码</div>
            <a href="goRetrieve.do" class="self_service01-L-Txt03">点击进入</a>
        </div>
    </div>
    <div style="float:right;">
    	<div class="self_service01-L"><img src="images/self_service_05.jpg" alt="" width="78" height="78" /></div>
        <div class="self_service01-L-Txt">
        	<div class="self_service01-L-Txt01">找回登录名</div>
            <div class="self_service01-L-Txt02">点此了解找回登录名的方法</div>
            <a href="helpDetail.do?helpPojo.id=53&helpPojo.helpType=self" class="self_service01-L-Txt03">点击进入</a>
        </div>
    </div>
    <div class="clear"></div>
</div>


<div class="faq-width">
	<div class="faq-L">
    	<div class="faq-L-title"><img src="images/faq_03.jpg" alt="" width="16" height="16" />&nbsp;&nbsp;&nbsp;&nbsp;帐户密码服务</div>
        <div class="self_service02-list">
        	<ul>
            	<li><a href="goSelfServiceWeb.do?pid=43">会员账户& 密码</a></li>
                <li><a href="goSelfServiceWeb.do?pid=41">供应商服务</a></li>
                <li><a href="goSelfServiceWeb.do?pid=44">采购帮助</a></li>
                <li><a href="goSelfServiceWeb.do?pid=45">投诉维权</a></li>
            </ul>
        </div>
    </div>
    
    <div class="self_service02-R-Kj-fu">
    <div  style="padding:10px 30px;">
            <span style=" display:block; padding-top:20px; line-height:30px;font-size:18px; font-weight:bold; margin:0 auto; text-align:center;"><s:property value="helpPojo.title"/></span><br/>
            <s:property value="helpPojo.content" escape="false"/>
             <br/> <br/> <br/>   
             
        </div>  
    </div>
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
