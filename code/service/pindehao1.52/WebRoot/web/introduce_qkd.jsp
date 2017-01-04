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
<link href="css/down.css" rel="stylesheet" type="text/css" />
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
<title>淘竹马分销平台</title>
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
	<div class="help-nav-fu">
    	<ul>
        	<li><a href="gotzmfxpt.do">淘竹马</a></li>
            <li><span><a href="gojkapp.do">见客</a></span></li>
            <li><a href="goshike.do">舌客</a></li>
            <li><a href="godsxy.do">梁山电商学院</a></li>
            <li><a href="gowjzh.do">玩具总汇期刊</a></li>
            <li><a href="gofdcWeb.do">翻动城</a></li>
        </ul>
    </div>
</div>

<div class="faq-width">
	<div class="faq-L">
    	<div class="faq-L-title"><img src="images/faq_03.jpg" alt="" width="16" height="16" />&nbsp;&nbsp;&nbsp;&nbsp;淘竹马</div>
        
       <div class="faq-L-list">
            <div id="firstpane" class="menu_list">
                <p class="menu_head">淘竹马</p>
                    <div style="display:none" class="menu_body" >
                      <a href="gotzmfxpt.do">淘竹马分销平台</a>
                      <a href="gotzmapp.do">淘竹马APP</a>
                      <a href="gowxgzh.do">淘竹马公众号</a>
                    </div>
                     <p class="menu_head">见客</p>
                    <div style="display:none" class="menu_body" >
                      <a href="gojkapp.do">见客APP</a>
                      <a href="goqkd.do">企客端</a>
                      <a href="http://www.5315.cn/" target="blank">玩具总汇网</a>
                    </div>
                    <p class="menu_head"><a href="goshike.do">舌客</a></p>
                    <p class="menu_head"><a href="godsxy.do">梁山电商学院</a></p>
                    <p class="menu_head"><a href="gowjzh.do">玩具总汇期刊</a></p>
                    <p class="menu_head"><a href="gofdcWeb.do">翻动城</a></p>
                    
                    
            </div>
        </div>
    </div>
    
    <div class="faq-R-fu2">
          <div class="down_box">
<h2>B2B平台——企客手机APP端</h2>
<h4 class="small_title">改变商人的传播习惯</h4>
<p>企客手机APP端，拥有Android端、Android HD、iOS端、iOS iPad、与PC端玩具总汇网云端数据同步，让更多的玩具企业低门槛进入移动互联网世界，为玩具企业开辟了便捷、多样、高效的营销渠道。</p>

<p>成为玩具总汇网（www.5315.cn/）的会员即可使用玩具总汇网的所有功能，同时系统自动为每个企业会员生成一个专享的二维码和独立企业APP服务端，为企业量身定制一张信息化移动名片，包含：产品展示、动态视频、企业信息、一键导航、二维码安装、信息推送等众多强大功能，通过扫描二维码自动安装的形式安装到客户的手机上，相当于把企业的名片、宣传册和产品等一次性派发给客户，客户使用次数也不受限制，从而把企业的宣传费用降到最低，让企业最大限度地享受到“企客手机APP端”为企业开展移动营销带来的便捷。</p>
<img src="images/qkd-01.jpg" />
<p>“企客手机APP端”,扛起“打造玩具产业链生态圈”的重任，为国内玩具行业提供一站式的新营销解决方案！突破玩具业的常规推广及销售瓶颈！</p>

<p>“企客手机APP端”，让您和企业的综合竞争力不断升级！</p>
 </div>
             <br/> <br/> <br/>   
             
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
<script type=text/javascript>
$(document).ready(function(){
	$("#firstpane .menu_body:eq(1)").show();
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
