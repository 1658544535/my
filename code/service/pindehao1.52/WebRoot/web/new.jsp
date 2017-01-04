<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<link href="/css/slide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/g.base.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马新品快订</title>
<meta name="keywords" content="新品玩具  玩具分销  母婴玩具订货  2015玩具新款 玩具分销平台  遥控玩具  淘竹马  星辉 澄星  玩具一件订货  早教玩具 童车玩具 益智玩具 " />
<meta name="description" content="淘竹马以厂家直销、主营玩具、一只发货为特色，专注于为玩具厂商和分销商服务，致力整合玩具渠道，打造专属玩具供应商和采购商的一站式玩具分销平台。2015春季新品订货会，一件起订货，新品每天更新。聚集了大量品牌玩具新品，如奥飞动漫、信宇玩具、群兴玩具、星辉互动娱乐、澄星航模等等。" />
<script type="text/javascript">
function openTick(){
	alert("请先成为分销商或者供应商！");
}
function searchBtn(){
	$("#sysform").attr("action","searchWebByName.do");
	$("#sysform").submit();
}
</script>

<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}

.tab1{width:516px;margin:32px 0 0 auto;}
.menu{height:28px; font-size:12px; font-family:"微软雅黑";}
.menu li{float:left;width:29px;text-align:center;line-height:28px;height:28px;cursor:pointer;color:#666;font-size:14px;overflow:hidden; font-weight: bold;}
.menu li.off{/*background:#FFFFFF*/;color:#df434e;font-weight:bold;}

.menudiv{/*height:200px;*/border-left:#cccccc solid 1px;border-right:#cccccc solid 1px;border-top:0;background:#fefefe}
.menudiv div{/*padding:15px;*/line-height:28px;}
.sea-Txt-line{ line-height:28px; float:left;}
</style>
<script type="text/javascript">
function searchShop(){
	$("#shopform").attr("action","searchShopWebByName.do#tabs-2");
	$("#shopform").submit();
}
function searchProductNo(){
	$("#opform").attr("action","searchProductNo.do#tabs-3");
	$("#opform").submit();
}
function setTab(name,cursel){
	cursel_0=cursel;
	for(var i=1; i<=links_len; i++){
		var menu = document.getElementById(name+i);
		var menudiv = document.getElementById("con_"+name+"_"+i);
		if(i==cursel){
			menu.className="off";
			menudiv.style.display="block";
		}
		else{
			menu.className="";
			menudiv.style.display="none";
		}
	}
}
function Next(){                                                        
	cursel_0++;
	if (cursel_0>links_len)cursel_0=1
	setTab(name_0,cursel_0);
} 
var name_0='one';
var cursel_0=1;
/*var ScrollTime=3000;*///循环周期（毫秒）
var links_len,iIntervalId;
onload=function(){
	var links = document.getElementById("tab1").getElementsByTagName('li')
	links_len=links.length;
	for(var i=0; i<links_len; i++){
		links[i].onmouseover=function(){
			clearInterval(iIntervalId);
			this.onmouseout=function(){
				iIntervalId = setInterval(Next,ScrollTime);;
			}
		}
	}
	document.getElementById("con_"+name_0+"_"+links_len).parentNode.onmouseover=function(){
		clearInterval(iIntervalId);
		this.onmouseout=function(){
			iIntervalId = setInterval(Next,ScrollTime);;
		}
	}
	setTab(name_0,cursel_0);
	iIntervalId = setInterval(Next,ScrollTime);
}
</script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="top02-width">
	<a href="goIndexWeb.do"><div class="logo"></div></a>
	<div class="tab1" id="tab1">
	<div class="menu">
		<ul>
			<li id="one1" onclick="setTab('one',1)">产品</li>
			<span class="sea-Txt-line">｜</span>
			<li id="one2" onclick="setTab('one',2)">店铺</li>
			<span class="sea-Txt-line">｜</span>
			<li id="one3" onclick="setTab('one',3)">货号</li>
		</ul>
	</div>
	<div class="menudiv">
	<div id="con_one_1">
	<form action="searchWeb.do" method="post" id="sysform">
	<div class="search2">
	
	<input name="productPojo.productName" type="text"  placeholder="搜索 商品"  class="search-input">
	<button type="submit" class="search-button" onclick="searchBtn()">搜 索</button></div>
	</form>  </div>
	<div id="con_one_2" style="display:none;">
	<form action="searchWeb.do" method="post" id="shopform">
	<div class="search2">
	
	<input name="shop.name" type="text" placeholder="搜索 店铺" class="search-input">
	<button type="submit" class="search-button" onclick="searchShop()">搜 索</button></div>
	</form>  </div>
		
	</div>
	<div id="con_one_3" style="display:none;">
			<form action="searchProductNo.do" method="post" id="opform">
				<div class="search3">
					<input name="productPojo.productNum" type="text" placeholder="搜索 货号" class="search-input">
					<button type="submit" class="search-button" onclick="searchProductNo()">搜 索</button>
				</div>
			</form>  
	</div>
</div>
	<!--<form action="searchWeb.do" method="post" id="sysform">
	<div class="search">
	<input name="productPojo.productName" type="text" value="搜索 商品/店铺" onfocus="this.value=''" onblur="if(this.value==''){this.value='搜索 商品/店铺'}" class="search-input"/>
	<button type="submit" class="search-button" onclick="searchBtn()">搜 索</button></div>
	</form>-->  
	<div class="clear"></div> 

	<div class="nav">
        <ul>
            <li><a href="goIndexWeb.do"><img src="images/new_01.jpg" alt="" width="20" height="20" /> 首页</a></li>
            <li class="indexColor arrow"><a href="goNewWeb.do"><span>新品快订</span></a></li>
            <li><a href="goshopWeb.do">店铺街</a></li>
            <li><a href="infoPageList.do">行业资讯</a></li>
        </ul>
        
    <div class="account">
	  <s:if test='#session.wuser != null && #session.wuser.type=="3"'>
   	  	<a href="myConsumerWeb.do"><div class="account-Bg" style="background:url(../images/index_09.jpg) no-repeat center center;color:#fff">我的帐户</div></a>
   	  </s:if>
   	  <s:elseif test='#session.wuser != null && #session.wuser.type=="2"'>
   	  	<a href="gomySupplier.do"><div class="account-Bg" style="background:url(../images/index_09.jpg) no-repeat center center;color:#fff">我的帐户</div></a>
   	  </s:elseif>
   	  <s:elseif test='#session.wuser != null && #session.wuser.type=="1"'>
   	  	<a href="goIndexWeb.do#type" onclick='openTick()'><div class="account-Bg" style="background:url(../images/index_09.jpg) no-repeat center center;color:#fff">我的帐户</div></a>
   	  </s:elseif>
   	  <!--<s:else>
   	  	<a href="#"><div class="account-Bg">我的帐户</div></a>
   	  </s:else>-->
    </div>
    </div>
</div> 

<div class="clear"></div>
<div class="bigeyes">
	<div id="banner" style="background-color:<s:property value='backgroundColor0'/>">
    	<div class="topBanner" style="background:url(/upfiles/notice/<s:property value='adimages0'/>) no-repeat top center;"></div>
    </div>
</div>
<jsp:include page="header_type.jsp"></jsp:include>

<div class="clear"></div>

<div class="new-title">新品快订/<span class="wrapper02-title02">抢首发</span></div>

	<s:iterator value="shopPojos" id="pro">
    			<div class="new">
    <div class="new-content">
	    	<div class="new-content-title"><s:property value='name'/><br />2015夏季新品订货会</div>
	        <div class="new-content-L">
	        	<div class="new-content-L-txt01"><s:property value='userName'/></div>
	            <div class="new-content-L-txt02"><s:if test="locationCertification==1"><a href="helpDetail.do?helpPojo.id=67" target="_blank"><img src="images/new_12.jpg" alt="" width="19" height="19" /> 实地认证</a></s:if><a href="helpDetail.do?helpPojo.id=69" target="_blank"><img src="images/new_14.jpg" alt="" width="19" height="19" /> 身份认证</a></br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="helpDetail.do?helpPojo.id=222" target="_blank"><img src="images/index_3c.jpg" alt="" width="19" height="19" /> 3C认证</a>&nbsp;&nbsp;<a href="helpDetail.do?helpPojo.id=68" target="_blank"><img src="images/index_35.jpg" alt="" width="19" height="19" /> 资质认证</a></div>
	            <div class="new-content-L-txt01">产地：<s:property value='provinceName'/><s:property value='cityName'/><s:property value='areaName'/></div>
	            <div class="new-content-L-txt03Pic">
	            	<div class="new-content-L-txt03"><a href="shopDetailsWeb.do?shop.id=<s:property value='id' />">4款<br />新品</a></div>
	            </div>
	        </div>
	        
	        <div class="new-content-R">
	        	<ul>
	        		<s:iterator value="#pro.productPojos">
		    			<li><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>"><img src="/upfiles/product/<s:property value='image'/>" width="225" height="219" /></a>
	                    <div class="new-content-R-PicTxt"><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>"><s:if test="productName.length()>14"><s:property value="productName.substring(0,14)+'...'" /></s:if><s:else><s:property value="productName" /></s:else></a></div>
	                    <div class="new-content-R-Discount">
	                    	<%-- <div class="new-content-R-Discount-L01"><s:property value='discount'/>折</div> --%>
	                        <div class="new-content-R-Discount-R01"><s:property value='minimum'/>件起订</div>
	                    </div>
	                    <div class="clear"></div>
	                    <div class="new-content-R-Discount">
	                    	<div class="new-content-R-Discount-L02" line-height:30px><span class="font01"></span><span class="font02"><s:if test="#session.wuser.type==3">¥<fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></s:if><s:else><span style="font-size:16px" style=" width:100px; height:20px; line-height:20px; text-align:center; float:left;" class="font02">分销商查看价格<span></s:else></span></div>
	                        <div class="new-content-R-Discount-R02"><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>">立即订购</a></div>
	                    </div>
	               	 	</li>
					</s:iterator>
	                
	            </ul>
	        </div>
	    </div>
	</div>
	</s:iterator>

<div id="leftsead">
	<!--<ul>
		
		<li><a href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4001501677&aty=0&a=0&curl=&ty=1" target="_blank"><img src="/images/ll04.png" width="131" height="49" class="hides"/><img src="images/l04.png" width="47" height="49" class="shows" /></a></li>
		
	</ul>-->
</div>
<style type="text/css">
/* leftsead */
#leftsead{width:131px;height:143px;position:fixed;top:258px;right:0px;}
*html #leftsead{margin-top:258px;position:absolute;top:expression(eval(document.documentElement.scrollTop));}
#leftsead li{width:131px;height:60px;}
#leftsead li img{float:right;}
#leftsead li a{height:49px;float:right;display:block;min-width:47px;max-width:131px;}
#leftsead li a .shows{display:block;}
#leftsead li a .hides{margin-right:-143px;cursor:pointer;cursor:hand;}
#leftsead li a.youhui .hides{display:none;position:absolute;right:190px;top:2px;}
</style>
<script type="text/javascript">
$(document).ready(function(){

	$("#leftsead a").hover(function(){
		if($(this).prop("className")=="youhui"){
			$(this).children("img.hides").show();
		}else{
			$(this).children("img.hides").show();
			$(this).children("img.shows").hide();
			$(this).children("img.hides").animate({marginRight:'0px'},'slow'); 
		}
	},function(){ 
		if($(this).prop("className")=="youhui"){
			$(this).children("img.hides").hide('slow');
		}else{
			$(this).children("img.hides").animate({marginRight:'-143px'},'slow',function(){$(this).hide();$(this).next("img.shows").show();});
		}
	});

	$("#top_btn").click(function(){if(scroll=="off") return;$("html,body").animate({scrollTop: 0}, 600);});

});
</script>
<div class="clear"></div>
<!-- WPA Button Begin -->
<script charset="utf-8" type="text/javascript" src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODA0NzI3N18yNjEzNDVfNDAwMTUwMTY3N18"></script>
<!-- WPA Button END -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
