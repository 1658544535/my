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
<meta name="keywords" content="群宇互动科技 梁山电商学院 电商学院 电商 SOHO 高校联盟 创业 培训 运营 掌柜 " />
<meta name="description" content="广东群宇互动科技有限公司旗下梁山电商学院成立于2014年，专业专注电子商务人才培训、创业指导服务机构。立足汕头服务潮汕，学院讲师拥有多年电商实战经验，成为创业者的孵化平台、SOHO精英的催化剂、电商从业者交流平台、校企合作实训平台、企业人才输送纽带。
梁山电商学院以“让天下没有难做的电商”为己任，致力成为潮汕实战电商领导者。专注电商运营规律与方法，研发开设了电商入门班、创业孵化班、电商精英班；建立了梁山电商高校联盟计划、梁山电商SOHO精英计划；不定期开设免费公开课；真正让电商“0”门槛、创业“0”门槛。
“梁山电商联盟” 群英招募计划来自宇博电商园与梁山电商学院共同推出的电商招募计划，0费用0门槛，让天下没有难做的电商，这里是创业者的孵化平台、SOHO精英的催化剂、厂商的渠道强心针。
从加入计划开始到培训再到运营，从运营策略、产品策略、产品摄影、平面设计再到货物寄仓、物流（一只发货）、每一个细节所到与服务，都有我们的全程专业指导与服务，只要“1人1脑”就可轻松实现掌柜梦。
欲了解更多关于“梁山电商联盟”群英招募计划 ，请与我们联系。" />
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
            <li><span><a href="godsxy.do">梁山电商学院</a></span></li>
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
<h2>梁山电商学院</h2>
</br>
<p>广东群宇互动科技有限公司旗下梁山电商学院成立于2014年，专业专注电子商务人才培训、创业指导服务机构。立足汕头服务潮汕，学院讲师拥有多年电商实战经验，成为创业者的孵化平台、SOHO精英的催化剂、电商从业者交流平台、校企合作实训平台、企业人才输送纽带。</p>

<p>梁山电商学院以“让天下没有难做的电商”为己任，致力成为潮汕实战电商领导者。专注电商运营规律与方法，研发开设了电商入门班、创业孵化班、电商精英班；建立了梁山电商高校联盟计划、梁山电商SOHO精英计划；不定期开设免费公开课；真正让电商“0”门槛、创业“0”门槛。</p>

<p>“梁山电商联盟” 群英招募计划来自宇博电商园与梁山电商学院共同推出的电商招募计划，0费用0门槛，让天下没有难做的电商，这里是创业者的孵化平台、SOHO精英的催化剂、厂商的渠道强心针。</p>

<p>从加入计划开始到培训再到运营，从运营策略、产品策略、产品摄影、平面设计再到货物寄仓、物流（一只发货）、每一个细节所到与服务，都有我们的全程专业指导与服务，只要“1人1脑”就可轻松实现掌柜梦。</p>
<p>欲了解更多关于“梁山电商联盟”群英招募计划 ，请与我们联系。</p><img src="images/dsxy-02.jpg"/>
 </div>
             <br/> <br/> <br/>   
             
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
<script type=text/javascript>
$(document).ready(function(){
	$("#firstpane .menu_body:eq(3)").show();
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
