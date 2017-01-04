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
<script>
function search(){
		document.getElementById("idform").submit();
	}
</script>
<title>淘竹马常见问题</title>
</head>

<body class="body">
<jsp:include page="header.jsp"></jsp:include>


<div class="top02-width">
	<div class="logo02"></div>
	<form action="goSearch.do" id="idform" method="post">
	<div class="search"><input id="searchkey" name="searchkey" type="text" value="请简单描述你的问题" onfocus="this.value=''" onblur="if(this.value==''){this.value='请简单描述你的问题'}" class="search-input"/><button type="submit" class="search-button" onclick="search()">搜 索</button></div>
	</form> 
</div> 

<div class="clear"></div>

<div class="help-nav-width">
	<div class="help-nav">
    	<ul>
        	<li><a href="goHelpWeb.do">首 页</a></li>
            <li><a href="goSelfServiceWeb.do">自主服务</a></li>
            <li><a href="goFaqWeb.do"><span>常见问题</a></span></li>
            <li><a href="goContact.do">联系客服 </a></li>
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
    
    <div class="faq-R-fu">
    <div class="faq-R01" id="tabs">
        	<div class="faq-R01-Bg">
                <ul>
                    <li><a href="#tabs-1">交易相关常见问题</a></li>
                    <li><a href="#tabs-2">热门问题</a></li>
                </ul>
            </div>
        	<div class="clear"></div>
            
            <div class="faq-R01-txt" id="tabs-1">
            	<ul>
                	<s:iterator value="helpUsualList">
                	<li><a href="helpDetail.do?helpPojo.id=<s:property value='id'/>"><s:property value="title"/></a></li>
                    </s:iterator>
                </ul>
                <div class="clear"></div>
            </div>

            <div class="faq-R01-txt" id="tabs-2">
            	<ul>
                	<s:iterator value="helpHotList">
                	<li><a href="helpDetail.do?helpPojo.id=<s:property value='id'/>"><s:property value="title"/></a></li>
                    </s:iterator>
                </ul>
                <div class="clear"></div>
            </div>
        </div>
    	<div  style="padding:10px 30px;">
            <span style=" display:block; padding-top:20px; line-height:30px;font-size:18px; font-weight:bold; margin:0 auto; text-align:center;"><s:property value="helpPojo.title"/></span><br/>
            <s:property value="helpPojo.content" escape="false"/>
             <br/> <br/> <br/>   
             
        </div>
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
<script type=text/javascript>
$(document).ready(function(){
	$("#firstpane .menu_body:eq(99)").show();
	$("#firstpane p.menu_head").click(function(){
		$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	$("#secondpane .menu_body:eq(0)").show();
	$("#secondpane p.menu_head").mouseover(function(){
		$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	
});
</script>
</body>
</html>
