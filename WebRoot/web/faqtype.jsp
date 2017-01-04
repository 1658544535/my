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
<script type="text/javascript" src="js/_head.js"></script>
<script>
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>
<title>淘竹马玩具分销平台</title>
</head>

<body class="body">
<jsp:include page="header.jsp"></jsp:include>

<div class="top02-width">
	<div class="logo02"></div>
	<div class="search"><input name="" type="text" value="请简单描述你的问题" onfocus="this.value=''" onblur="if(this.value==''){this.value='请简单描述你的问题'}" class="search-input"/><button type="submit" class="search-button">搜 索</button></div>
</div> 

<div class="clear"></div>

<div class="help-nav-width">
	<div class="help-nav">
    	<ul>
        	<li><a href="goHelpWeb.do">首 页</a></li>
            <li><a href="goSelfServiceWeb.do?pid=43">自主服务</a></li>
            <li><a href="goFaqWeb.do"><span>常见问题</span></a></li>
            <li><a href="#">联系客服</a></li>
            <li><a href="goFeedBackWeb.do">意见反馈</a></li>
        </ul>
    </div>
</div>

<div class="faq-width">
	<div class="faq-L">
    	<div class="faq-L-title"><img src="images/faq_03.jpg" alt="" width="16" height="16" />&nbsp;&nbsp;&nbsp;&nbsp;帮助中心</div>
        
        <div class="faq-L-list">
            <div id="firstpane" class="menu_list">
                    <s:iterator value="helpTypePojoList">
                <p class="menu_head"><s:property value="name"/></p>
                    <div id="head<s:property value="id"/>" style="display:none" class="menu_body" >
                      <s:iterator value="helpTypeChildPojoList">
                      <a href="goFaqWeb.do?typeid=<s:property value='id'/>&ptypeid=<s:property value='pid'/>"><s:property value="name"/></a>
                      </s:iterator>
                    </div>
                     </s:iterator>
            </div>
        </div>
    </div>
    
    <div class="faq-R">
    	<div class="faq-R01" id="tabs">
        </div>
        
        <div class="faq-R02">
        
        	<div class="faq-R02-L-new">
        	 <s:iterator value="helpTypePojoList">
            	<div class="faq-R02-title"><s:property value="name"/></div>
                <div class="faq-R02-txt"><s:iterator value="helpPojoList"><a href="#"><s:property value="title"/></a><span class="faq-R02-line">|</span></s:iterator></div>
            </s:iterator>
            </div>
        </div>
    </div>
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
