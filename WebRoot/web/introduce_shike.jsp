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
<meta name="keywords" content="移动互联网 团购 O2O 餐饮 酒店 休闲娱乐 生活服务 玩具分销平台 玩具渠道扁平化 玩具新品推荐  玩具新品" />
<meta name="description" content="借助移动互联网、信息技术和线下服务能力，舌客将为消费者提供值得信赖的本地商家、消费评价和优惠信息及团购、预约预订、外送、电子会员卡等O2O闭环交易服务，覆盖了餐饮、电影、酒店、休闲娱乐、购物、美容保健、结婚亲子、家装等几乎所有本地生活服务行业，使消费者可以用最少花费享受时尚的高品质生活。我们团队深刻理解消费者与商户需求，致力于为其创造非凡体验和最大价值，并引领本地生活方式。" />
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
            <li><span><a href="goshike.do">舌客</a></span></li>
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
<h2>商务精英消费信息和交易平台 ——舌客</h2>
<h4 class="small_title">爱玩客，好吃客都上舌客</h4>
<p>借助移动互联网、信息技术和线下服务能力，舌客将为消费者提供值得信赖的本地商家、消费评价和优惠信息及团购、预约预订、外送、电子会员卡等O2O闭环交易服务，覆盖了餐饮、电影、酒店、休闲娱乐、购物、美容保健、结婚亲子、家装等几乎所有本地生活服务行业，使消费者可以用最少花费享受时尚的高品质生活。我们团队深刻理解消费者与商户需求，致力于为其创造非凡体验和最大价值，并引领本地生活方式。</p>
</br>
<img src="images/shike.jpg" />
 </div>
             <br/> <br/> <br/>   
             
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
<script type=text/javascript>
$(document).ready(function(){
	$("#firstpane .menu_body:eq(2)").show();
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
