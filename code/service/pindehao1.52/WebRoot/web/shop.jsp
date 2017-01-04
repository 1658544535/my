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
<link href="/css/slide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马店铺街</title>
<meta name="keywords" content="玩具品牌 玩具3认证C 淘竹马 母婴玩具品牌 玩具企业 玩具分销 品牌玩具 群兴 骅威 玩具电商 音乐玩具 遥控玩具 早教玩具 益智玩具 电动玩具" />
<meta name="description" content="淘竹马玩具分销平台，拥有强大的发展潜力与市场前景，秉承“让天下，没有难做的电商”的核心价值，力争在2015年实现扶持百万级玩具企业100家、千万级玩具企业10家、亿级玩具企业2家的目标。已与奥飞动漫、群兴玩具、骅威玩具、星辉互动娱乐、信宇玩具等国内众多知名母婴品牌建立合作，更多品牌正在持续入驻中。所有入驻淘竹马的厂商都需提供产品3C认证证书、营业执照、授权书等证明材料。" />
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
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
    }
function ShopType(){
	var s=getQueryString("shop.mainCategory");
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
	</form> -->
	<div class="clear"></div> 

	<div class="nav">
        <ul>
            <li><a href="goIndexWeb.do"><span><img src="images/new_01.jpg" alt="" width="20" height="20" /> 首页</span></a></li>
            <li><a href="goNewWeb.do">新品快订</a></li>
            <li class="indexColor arrow"><a href="goshopWeb.do"><span>店铺街</span></a></li>
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

<div class="shop" id="info">
	<div class="shop-L">
    	<div class="shop-L-title" >店铺街</div>
        <div class="shop-L-Column">
        	<ul>
        		<li class="shop-L-Column00"><a href="goshopWeb.do#info">全部玩具店铺</a></li>
            	<li class="shop-L-Column01"><a href="goshopWeb.do?shop.mainCategory=1#info" onclick="ShopType()">遥控/电动玩具</a></li>
                <li class="shop-L-Column02"><a href="goshopWeb.do?shop.mainCategory=2#info" onclick="ShopType()">早教/音乐玩具</a></li>
                <li class="shop-L-Column03"><a href="goshopWeb.do?shop.mainCategory=3#info" onclick="ShopType()">过家家玩具</a></li>
                <li class="shop-L-Column04"><a href="goshopWeb.do?shop.mainCategory=4#info" onclick="ShopType()">童车玩具</a></li>
                <li class="shop-L-Column05"><a href="goshopWeb.do?shop.mainCategory=5#info" onclick="ShopType()">益智玩具</a></li>
                <li class="shop-L-Column06"><a href="goshopWeb.do?shop.mainCategory=6#info" onclick="ShopType()">其他玩具</a></li>
            </ul>
        </div>
    </div>
    
    <div class="shop-R">
    	<ul id="body">
    		<s:iterator value="shoplist">
        		<li><a href='shopDetailsWeb.do?shop.id=<s:property value='id' />'>
	            <div class='shop-R-Pic'><img src='/upfiles/shop/<s:property value='images' />' alt='' width='217' height='217' /></div></a>
		        <div class='shop-R-Pic-txt'>主营：<s:property value='mainCategoryName' /></div>
		        <div class='shop-R-Pic-txt02'>地址：<span class='shop-R-Pic-txt03'><s:property value='provinceName'/><s:property value='cityName'/><s:property value='areaName'/></span></div>
		        <div class='shop-R-button'>
		        <a href='shopDetailsWeb.do?shop.id=<s:property value='id' />'><div class='shop-R-button-L'><s:property value='name' /></div></a>
		        <a href='shopDetailsWeb.do?shop.id=<s:property value='id' />'><div class='shop-R-button-R'>进入店铺</div></a>
		        </div>
	            </li>
        	</s:iterator>
        </ul>
        <div class="clear"></div>
        <div class="shop-page">
        	<div class="digg" id="Pagination"></div>
		</div>
        </div>
	</div>
    </div>
</div>
<script type="text/javascript">
$(function() {
	$("#Pagination").pagination(<s:property value='page.rowCount'/>, {
		current_page : <s:property value='page.pageNo'/>-1,
		items_per_page:12,	//每页显示数量
		next_text : '>',
		prev_text : '<',
		first_text: "|<",
		last_text: ">|",
		num_edge_entries: 2,
		num_display_entries: 4,
		prev_show_always : true,
		next_show_always : true,
		callback: pageselectCallback,
		load_first_page : function() {
			return false;
		}
	});
});

function pageselectCallback(pageindex, jq) {
	var pageNo = pageindex+1;
	window.location.href="goshopWeb.do?page.pageNo="+pageNo+"&page.rowCount=<s:property value='page.rowCount'/>&shop.mainCategory=<s:property value='shop.mainCategory'/>";
	return false;
}

</script>
<div id="leftsead">
	<!-- <ul>
		
		<li><a href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4001501677&aty=0&a=0&curl=&ty=1" target="_blank"><img src="images/ll04.png" width="131" height="49" class="hides"/><img src="images/l04.png" width="47" height="49" class="shows" /></a></li>
		
	</ul> -->
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
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
