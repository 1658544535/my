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
<meta name="keywords" content="淘竹马 广东群宇互动 M2C玩具分销  玩具分销 玩具产品 玩具分销平台 玩具电商0费用起步  知名玩具 玩具品牌 玩具一只发货" />
<meta name="description" content="广东群宇互动科技有限公司旗下平台淘竹马，是全球专业M2C玩具分销平台，主要提供各类玩具产品的一站式分销服务，即玩具产品从生产商直接到分销商的平台模式。淘竹马以厂家直销、主营玩具、一只发货为特色，专注于为玩具厂商和分销商服务，致力整合玩具渠道，打造专属玩具供应商和采购商的一站式玩具分销平台。一站式电商服务：为玩具供应商提供摄影、美工、仓储、分拣、物流一站式电商方案，解决目前电商渠道的囤货、发货的资金、人员压力 ；为采购商提供产品数据包、一只发货服务，解决目前分销商的采购囤货、采购资金、物流压力。
价格管控：通过淘竹马M2C分销平台的渠道整合，对产品价格体系进行管控，辅助玩具供应商重构价格体系，为采购商提供厂家质优价廉产品。
渠道扁平化：渠道优化整合，为供应商进行多种销售渠道下沉，流通环节减少至一对一，实现扁平化，省去了诸多的流通成本，保障售后服务质量。
品牌联盟：淘竹马已与国内众多知名玩具品牌建立合作，淘竹马正在火热招商中，更多品牌即将入驻。
资质认证：所有入驻淘竹马的供应商都需提供产品3C认证证书、营业执照、授权书等资质证明材料。
优质服务：一只发货，退货承诺，发货时间承诺等。" />
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
        	<li><span><a href="gotzmfxpt.do">淘竹马</a></span></li>
            <li><a href="gojkapp.do">见客</a></li>
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
<h2>淘竹马分销平台</h2>
</br>
<p>广东群宇互动科技有限公司旗下平台淘竹马，是全球专业M2C玩具分销平台，主要提供各类玩具产品的一站式分销服务，即玩具产品从生产商直接到分销商的平台模式。淘竹马以厂家直销、主营玩具、一只发货为特色，专注于为玩具厂商和分销商服务，致力整合玩具渠道，打造专属玩具供应商和采购商的一站式玩具分销平台。</p>

<p><b>特色优势:</b></p>

<img src="images/taozhumafenxiaopingtai.png"/>
<p><b>一站式电商服务：</b>为玩具供应商提供摄影、美工、仓储、分拣、物流一站式电商方案，解决目前电商渠道的囤货、发货的资金、人员压力 ；为采购商提供产品数据包、一只发货服务，解决目前分销商的采购囤货、采购资金、物流压力。</p>
<p><b>价格管控：</b>通过淘竹马M2C分销平台的渠道整合，对产品价格体系进行管控，辅助玩具供应商重构价格体系，为采购商提供厂家质优价廉产品。</p>
<p><b>渠道扁平化：</b>渠道优化整合，为供应商进行多种销售渠道下沉，流通环节减少至一对一，实现扁平化，省去了诸多的流通成本，保障售后服务质量。</p>
<p><b>品牌联盟：</b>淘竹马已与国内众多知名玩具品牌建立合作，淘竹马正在火热招商中，更多品牌即将入驻。</p>
<p><b>资质认证：</b>所有入驻淘竹马的供应商都需提供产品3C认证证书、营业执照、授权书等资质证明材料。</p>
<p><b>优质服务：</b>一只发货，退货承诺，发货时间承诺等。</p>
 </div>
             <br/> <br/> <br/>   
             
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
<script type=text/javascript>
$(document).ready(function(){
	$("#firstpane .menu_body:eq(0)").show();
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
