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
<meta name="keywords" content="跨界 玩具连锁 O2O 翻动城 手机端 批发 渠道 互联网思维  儿童玩具 电商" />
<meta name="description" content="用互联网思维做跨界的玩具万店连锁O2O  翻动城采用的模式为电商平台(手机端/平台端)+连锁体验终端店+儿童游乐中心+物流配送+售后维修，通过整合全国各地批发通路，帮助经销商渠道下沉直达终端店，为消费者提供更灵活、更便捷、更智能化的线下社区购物体验、儿童游乐中心体验。翻动城首创全国玩具线上线下购物、快递送货、零库存、玩具售后维修等多项业务。 翻动城用户可通过手机端购物，也可以到最近的连锁店，通过店内的展示样品，海报、二维码墙放置虚拟的商品，可以通过手机扫码、店内下单购买、网上支付、系统选择最近经销商发货等形式，店内不设库存，提供购买商品或指定品牌商家售后维修。 
无论是客户网络、服务理念，还是高科技技术的运用上，翻动城更胜一筹。有了实体店之后，翻动城与消费者的接触点会越来越多，其关系也会更加紧密。" />
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
            <li><a href="gowjzh.do">玩具总汇期刊</a></li>
            <li><span><a href="gofdcWeb.do">翻动城</a></span></li>
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
<h2>翻动城</h2>
<h4 class="small_title">用互联网思维做跨界的玩具万店连锁O2O</h4>

<img src="images/fdc-01.jpg" />
<p>翻动城采用的模式为电商平台(手机端/平台端)+连锁体验终端店+儿童游乐中心+物流配送+售后维修，通过整合全国各地批发通路，帮助经销商渠道下沉直达终端店，为消费者提供更灵活、更便捷、更智能化的线下社区购物体验、儿童游乐中心体验。翻动城首创全国玩具线上线下购物、快递送货、零库存、玩具售后维修等多项业务。 </p>
<img src="images/fdc-02.jpg" />
<p>翻动城用户可通过手机端购物，也可以到最近的连锁店，通过店内的展示样品，海报、二维码墙放置虚拟的商品，可以通过手机扫码、店内下单购买、网上支付、系统选择最近经销商发货等形式，店内不设库存，提供购买商品或指定品牌商家售后维修。 </p>

<p>无论是客户网络、服务理念，还是高科技技术的运用上，翻动城更胜一筹。有了实体店之后，翻动城与消费者的接触点会越来越多，其关系也会更加紧密。</p>
<img src="images/fdc-03.jpg" />
<img src="images/fdc-04.jpg" />
 </div>
             <br/> <br/> <br/>   
             
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
<script type=text/javascript>
$(document).ready(function(){
	$("#firstpane .menu_body:eq(5)").show();
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
