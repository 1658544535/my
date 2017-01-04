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
<title>淘竹马玩具分销平台</title>
<meta name="keywords" content="期刊 纸媒 玩具总汇 国内玩具 婴童用品 模型 礼品 动漫 产品资讯 采购玩具 指南" />
<meta name="description" content="传统纸媒—买家找卖家，搭桥为大家 《玩具总汇》期刊于2008年6月创刊，行业中首创双封面、双扉页广告位形式。
刊物立足“广东、福建、浙江、山东、江苏”中国五大玩具生产基地，辐射全国11个玩具一线批发市场，全面展示国内玩具、婴童用品、模型、礼品、网游及动漫领域的最新产品资讯。《玩具总汇》已受到当今的不少国内外的买家的广泛认可，将《玩具总汇》作为采购玩具之必备指南。并且刊物还配套电子版本的CD盘、网上在线阅读等方式发行。每年受阅人数超20万。买家找卖家，搭桥为大家。" />
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
            <li><a href="gojkapp.do">见客</a></li>
            <li><a href="goshike.do">舌客</a></li>
            <li><a href="godsxy.do">梁山电商学院</a></li>
            <li><span><a href="gowjzh.do">玩具总汇期刊</a></span></li>
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
<h2>《玩具总汇》期刊</h2>
<h4 class="small_title">传统纸媒—买家找卖家，搭桥为大家</h4>
<p>《玩具总汇》期刊于2008年6月创刊，行业中首创双封面、双扉页广告位形式。</p>

<p>刊物立足“广东、福建、浙江、山东、江苏”中国五大玩具生产基地，辐射全国11个玩具一线批发市场，全面展示国内玩具、婴童用品、模型、礼品、网游及动漫领域的最新产品资讯。《玩具总汇》已受到当今的不少国内外的买家的广泛认可，将《玩具总汇》作为采购玩具之必备指南。并且刊物还配套电子版本的CD盘、网上在线阅读等方式发行。每年受阅人数超20万。买家找卖家，搭桥为大家。</p>
<img src="images/wanjuzonghui.jpg" />

 </div>
             <br/> <br/> <br/>   
             
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
<script type=text/javascript>
$(document).ready(function(){
	$("#firstpane .menu_body:eq(4)").show();
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
