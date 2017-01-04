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
<link href="/css/index.css" rel="stylesheet" type="text/css" />
<link href="/css/slide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<LINK rel=stylesheet type=text/css href="css/lrtk.css">
<title>淘竹马玩具分销平台</title>
<meta name="keywords" content="淘竹马 M2C玩具分销平台 玩具厂家分销 玩具直销 玩具一只发货 玩具3C认证 玩具48小时发货 儿童玩具  玩具分销平台  玩具电商 玩具渠道  电商创业 母婴玩具 分销玩具" />
<meta name="description" content="淘竹马作为全球专业M2C玩具分销平台，主要提供各类玩具产品的一站式分销服务，即玩具产品从生产商直接到分销商的平台模式。淘竹马以厂家直销、主营玩具、一只发货为特色，专注于为玩具厂商和分销商服务，致力整合玩具渠道，打造专属玩具供应商和采购商的一站式玩具分销平台。产品质量都是通过人工把关质检，每件产品都具有3C认证，安全，可靠。同时，淘竹马承诺买家完成付款，淘竹马会在48小时内完成发货。" />
<!-- slider 开始-->
<style type="text/css">
#bigeyes .JQ-content-box{overflow:hidden;width:100%;height:402px;position:relative}
#bigeyes .JQ-slide-content{position:absolute}
#bigeyes .JQ-slide-content li{float:left;zoom:1;overflow:hidden;height:402px;width:100%;vertical-align:text-top}
#bigeyes .JQ-slide-content li a{width: 1190px;height: 402px;margin: 0 auto;display: block;}
#bigeyes img{display:block;} 
#bigeyes .JQ-slide-nav{position:absolute;right:10px;bottom:10px;height:18px;padding-top:2px;}
#bigeyes .JQ-slide-nav li{ list-style-type: none;background:url(../images/h_jd.png) no-repeat;border:0 solid;cursor:pointer;float:left;font-size:12px;height:16px;line-height:16px;margin-left:3px;text-align:center;width:16px;}
#bigeyes .JQ-slide-nav li.on{background:url(../images/x_jd.png) no-repeat;color:#FFFFFF;font-weight:bold;height:16px;line-height:16px;width:16px;}
#bigeyes .JQ-slide-nav li img{display:block}
.search-input {

line-height: 20px;
}

</style>
<script type="text/javascript" src="/js/jq.Slide.js"></script>
<script type="text/javascript">
$(function(){
	$("#bigeyes").Slide({
    effect : "scroolY",
    speed : "normal",
    timer : 3000
	});
});
</script>
<!-- slider 结束-->
<script type="text/javascript">
function openTick(){
	alert("请先成为分销商或者供应商！");
}

function searchBtn(){
	$("#sysform").attr("action","searchWebByName.do");
	$("#sysform").submit();
}
function dologin()
{
	if(window.confirm("你还没登录，确定现在登录吗？")){ 
		//alert("确定"); 
		window.location.href="doLoginWeb.do";
		return true; 
		}else{ 
		//alert("取消"); 
		return false; 
		} 
	
}
</script>
<style type="text/css">
.slide img{
	width:120px;
	height:120px;
}
</style>
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
<!--
<div class=head_ad style="width:1190px;left: 50%;top: 0px;padding: 0px;cursor: pointer;margin-left: -595px;position: fixed;z-index: 99;">
	<div id=adv_forum_home_full></div>
</div>
-->
<SCRIPT type=text/javascript src="/js/loadalladv.js"></SCRIPT>
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
					<input name="productPojo.productName" type="text"    placeholder="搜索 商品"  class="search-input">
					<button type="submit" class="search-button" onclick="searchBtn()">搜 索</button>
				</div>
			</form>
		</div>
		<div id="con_one_2" style="display:none;">
			<form action="searchWeb.do" method="post" id="shopform">
				<div class="search2">
					<input name="shop.name" type="text" placeholder="搜索 店铺" class="search-input">
					<button type="submit" class="search-button" onclick="searchShop()">搜 索</button>
				</div>
			</form>
		</div>
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
            <li class="indexColor arrow"><a href="goIndexWeb.do"><span><img src="images/index_08.jpg" alt="" width="20" height="20" /> 首页</span></a></li>
            <li><a href="goNewWeb.do">新品快订</a></li>
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
   	  	<a onclick='dologin()'><div class="account-Bg">我的帐户</div></a>
   	  </s:else>-->
    </div>
    </div>
</div> 
<div class="clear"></div>
<div id="bigeyes" class="bigeyes">
	<div class="JQ-content-box">
				<ul class="JQ-slide-content">
					<li style="background-color:<s:property value='backgroundColor0'/>"><a href="<s:property value='imageUrl0'/>" ><img alt="" title="" src="/upfiles/notice/<s:property value='adimages0'/>"  height="402px"  /></a> </li>
					<li style="background-color:<s:property value='backgroundColor1'/>"><a href="<s:property value='imageUrl1'/>" ><img alt="" title="" src="/upfiles/notice/<s:property value='adimages1'/>"  height="402px"/></a> </li>
					<li style="background-color:<s:property value='backgroundColor2'/>"><a href="<s:property value='imageUrl2'/>" ><img alt="" title="" src="/upfiles/notice/<s:property value='adimages2'/>"  height="402px" /></a> </li>
				</ul>
				<ul class="JQ-slide-nav">
					<li class="on"></li>
					<li></li>
					<li></li>
				</ul>
			</div>
</div>
<jsp:include page="header_type.jsp"></jsp:include>

<div class="clear"></div>

<div class="wrapper01">
	<div class="wrapper01-L"  id="type">
    	<div class="wrapper01-L-Pic"><img src="images/index_21.jpg" alt="" width="200" height="194" /></div>
        <div class="wrapper01-L-Txt">
        	<div class="wrapper01-L-Txt-title" id="tabs">
            	<ul>
                	<li><a href="#tabs-1">我要分销</a></li>
                    <li><a href="#tabs-2">我要供货</a></li>
                </ul>
                <div class="tabs-line"></div>
                <div class="clear"></div>
                
                <div id="tabs-1" class="tabs-1-Txt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当您在做玩具产品销售时，您是否会为寻找玩具新产品而烦恼？是否会为备货资金压力大而忧心？是否会为仓储场地有限而郁闷？淘竹马M2C玩具分销平台，集聚众多玩具品牌厂家，为您提供从产品挑选、质检、仓储、订单处理到物流配送一站式电商方案，还提供产品数据包、一只发货等特色服务，助您轻松实现低投入、零库存、低风险的分销运营。</div>
                <div id="tabs-2" class="tabs-1-Txt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您是否还为局限于传统营销渠道而愁闷？您是否正苦苦寻求玩具营销的互联新渠道？淘竹马，作为全球专业的M2C玩具分销平台，Web端、App端、微信服务号多端同步，帮助玩具企业开拓网络分销渠道，实现玩具企业渠道扁平化，跨界式全网营销，并提供一站式摄影、美工、仓储、分拣、物流等服务,助您轻松实现电子商务可持续化经营。</div>
            </div>
        </div>
        
        <div class="clear"></div>
        
        <div class="wrapper01-L-bottom">
        	<div class="wrapper01-L-bottom-txt">
            	<ul>
                	<li><a href="helpDetail.do?helpPojo.id=67"><img src="images/index_33.jpg" alt="" width="31" height="31" /> 实地认证</a></li>
                    <li><a href="helpDetail.do?helpPojo.id=68"><img src="images/index_35.jpg" alt="" width="31" height="31" /> 资质认证</a></li>
                    <li><a href="helpDetail.do?helpPojo.id=69"><img src="images/index_37.jpg" alt="" width="31" height="31" /> 身份认证</a></li>
                    <li><a href="helpDetail.do?helpPojo.id=222"><img src="images/index_3c.jpg" alt="" width="31" height="31" /> 3C认证</a></li>
                </ul>
            </div>
        </div>
    </div>
    
    <div class="wrapper01-R">
    	<a href="applyConsumer.do" class="wrapper01-R-pic01">我要分销</a>
        <a href="applyManufacturer.do" class="wrapper01-R-pic02">我要供货</a>
    </div>
</div>

<div class="clear"></div>

<div class="wrapper02">
	<div class="wrapper02-title01">新品推荐/<span class="wrapper02-title02">每日为您精心挑选</span></div>
    <div class="wrapper02-content">
    	<ul>
    	<s:iterator value="productNewList">
        	<li><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target="_blank"><img src="/upfiles/product/<s:property value='image'/>" alt="" width="234" height="242" /><div class="wrapper02-content-txt01"><s:if test="productName.length()>12"><s:property value="productName.substring(0,12)+'...'" /></s:if><s:else><s:property value="productName" /></s:else><span class="wrapper02-content-txt02">爆款</span><p class="wrapper02-content-txt03"><s:property value="productSketch" /></p></div></a></li>
        </s:iterator>
        </ul>
    </div>
</div>

<div class="clear"></div>

<div class="wrapper03">
	<div class="wrapper03-L">
    	<div class="wrapper01-L-T">
        	<div class="wrapper03-L-T-Floor">1F</div>
        	<div class="wrapper03-L-T-title"><img src="images/index_52.jpg" alt="" width="34" height="34" /><div ><style>a{text-decoration:none}</style><a class="wrapper03-L-T-txt" href=goProductListWeb.do?productPojo.productTypeIds=1#infor>遥控/电动玩具</a></div></div>
        </div>
        <div class="wrapper03-L-M">
        	<div id="slide01" class="slide"></div>
            <script type="text/javascript">$("#slide01").jdSlide({width:184,height:184,pics:<s:property value='shopImages1' />})</script>
        </div>
        <div class="wrapper03-L-B">
        	<ul>
        		<s:iterator value="productTypeIds1">
		        	<li><a href="goProductListWeb.do?productPojo.status=1&productPojo.productTypeId=<s:property value='id'/>#infor"><s:property value='name'/></a></li>
		        </s:iterator>
             </ul>
         </div>
     </div>
     <div class="wrapper03-M"><a href="<s:property value='imageUrl3'/>"><img src="/upfiles/notice/<s:property value='adimages3'/>" alt="" width="265" height="410" /></a></div>
     <div class="wrapper03-R">
     	<ul>
     	<s:iterator value="productTypeList1">
        	<li><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target="_blank" title="<s:property value="productName" />"><div class="wrapper03-R-txt01"><s:if test="productName.length()>14"><s:property value="productName.substring(0,14)+'...'" /></s:if><s:else><s:property value="productName" /></s:else><div class="wrapper03-R-txt02"><s:property value="productSketch" /></div></div></a><div class="wrapper03-R-txt01-Pic"><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target="_blank"><img src="/upfiles/product/small/<s:property value='image'/>" alt="" width="155" height="155"/></a></div>
        </s:iterator>
        </ul>
    </div> 
</div>

<div class="wrapper03">
	<div class="wrapper03-L">
    	<div class="wrapper02-L-T">
        	<div class="wrapper03-L-T-Floor">2F</div>
        	<div class="wrapper03-L-T-title"><img src="images/index_71.jpg" alt="" width="34" height="34" /><div ><style>a{text-decoration:none}</style><a class="wrapper03-L-T-txt" href=goProductListWeb.do?productPojo.productTypeIds=2#infor>早教/音乐玩具</a></div></div>
        </div>
        <div class="wrapper03-L-M">
        	<div id="slide02" class="slide"></div>
            <script type="text/javascript">$("#slide02").jdSlide({width:184,height:184,pics:<s:property value='shopImages2' />})</script>
        </div>
        <div class="wrapper03-L-B">
        	<ul>
            	<s:iterator value="productTypeIds2">
		        	<li><a href="goProductListWeb.do?productPojo.status=1&productPojo.productTypeId=<s:property value='id'/>#infor"><s:property value='name'/></a></li>
		        </s:iterator>
             </ul>
         </div>
     </div>
     <div class="wrapper03-M"><a href="<s:property value='imageUrl4'/>"><img src="/upfiles/notice/<s:property value='adimages4'/>" alt="" width="265" height="410" /></a></div>
     <div class="wrapper03-R">
     	<ul>
     	<s:iterator value="productTypeList2">
        	<li><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target="_blank" title="<s:property value="productName" />"><div class="wrapper03-R-txt01"><s:if test="productName.length()>14"><s:property value="productName.substring(0,14)+'...'" /></s:if><s:else><s:property value="productName" /></s:else><div class="wrapper03-R-txt02"><s:property value="productSketch" /></div></div></a><div class="wrapper03-R-txt01-Pic"><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target="_blank"><img src="/upfiles/product/small/<s:property value='image'/>" alt="" width="155" height="155"/></a></div>
        </s:iterator>
        </ul>
    </div> 
</div>

<div class="wrapper03">
	<div class="wrapper03-L">
    	<div class="wrapper03-L-T">
        	<div class="wrapper03-L-T-Floor">3F</div>
        	<div class="wrapper03-L-T-title"><img src="images/index_94.jpg" alt="" width="34" height="34" /><div ><style>a{text-decoration:none}</style><a class="wrapper03-L-T-txt" href=goProductListWeb.do?productPojo.productTypeIds=3#infor>过家家玩具</a></div></div>
        </div>
        <div class="wrapper03-L-M">
        	<div id="slide03" class="slide"></div>
            <script type="text/javascript">$("#slide03").jdSlide({width:184,height:184,pics:<s:property value='shopImages3' />})</script>
        </div>
        <div class="wrapper03-L-B">
        	<ul>
            	<s:iterator value="productTypeIds3">
		        	<li><a href="goProductListWeb.do?productPojo.status=1&productPojo.productTypeId=<s:property value='id'/>#infor"><s:property value='name'/></a></li>
		        </s:iterator>
             </ul>
         </div>
     </div>
     <div class="wrapper03-M"><a href="<s:property value='imageUrl5'/>"><img src="/upfiles/notice/<s:property value='adimages5'/>" alt="" width="265" height="410" /></a></div>
     <div class="wrapper03-R">
     	<ul>
     	<s:iterator value="productTypeList3">
        	<li><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target="_blank" title="<s:property value="productName" />"><div class="wrapper03-R-txt01"><s:if test="productName.length()>14"><s:property value="productName.substring(0,14)+'...'" /></s:if><s:else><s:property value="productName" /></s:else><div class="wrapper03-R-txt02"><s:property value="productSketch" /></div></div></a><div class="wrapper03-R-txt01-Pic"><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target="_blank"><img src="/upfiles/product/small/<s:property value='image'/>" alt="" width="155" height="155"/></a></div>
        </s:iterator>
        </ul>
    </div> 
</div>

<div class="wrapper03">
	<div class="wrapper03-L">
    	<div class="wrapper04-L-T">
        	<div class="wrapper03-L-T-Floor">4F</div>
        	<div class="wrapper03-L-T-title"><img src="images/index_97.jpg" alt="" width="34" height="34" /><div ><style>a{text-decoration:none}</style><a class="wrapper03-L-T-txt" href=goProductListWeb.do?productPojo.productTypeIds=4#infor>童车玩具</a></div></div>
        </div>
        <div class="wrapper03-L-M">
        	<div id="slide04" class="slide"></div>
            <script type="text/javascript">$("#slide04").jdSlide({width:184,height:184,pics:<s:property value='shopImages4' />})</script>
        </div>
        <div class="wrapper03-L-B">
        	<ul>
            	<s:iterator value="productTypeIds4">
		        	<li><a href="goProductListWeb.do?productPojo.status=1&productPojo.productTypeId=<s:property value='id'/>#infor"><s:property value='name'/></a></li>
		        </s:iterator>
             </ul>
         </div>
     </div>
     <div class="wrapper03-M"><a href="<s:property value='imageUrl6'/>"><img src="/upfiles/notice/<s:property value='adimages6'/>" alt="" width="265" height="410" /></a></div>
     <div class="wrapper03-R">
     	<ul>
     	<s:iterator value="productTypeList4">
        	<li><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target="_blank" title="<s:property value="productName" />"><div class="wrapper03-R-txt01"><s:if test="productName.length()>14"><s:property value="productName.substring(0,14)+'...'" /></s:if><s:else><s:property value="productName" /></s:else><div class="wrapper03-R-txt02"><s:property value="productSketch" /></div></div></a><div class="wrapper03-R-txt01-Pic"><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target="_blank"><img src="/upfiles/product/small/<s:property value='image'/>" alt="" width="155" height="155"/></a></div>
        </s:iterator>
        </ul>
    </div> 
</div>

<div class="wrapper03">
	<div class="wrapper03-L">
    	<div class="wrapper05-L-T">
        	<div class="wrapper03-L-T-Floor">5F</div>
        	<div class="wrapper03-L-T-title"><img src="images/index_75.jpg" alt="" width="34" height="34" /><div ><style>a{text-decoration:none}</style> <a class="wrapper03-L-T-txt" href=goProductListWeb.do?productPojo.productTypeIds=5#infor>益智玩具</a></div></div>
        </div>
        <div class="wrapper03-L-M">
        	<div id="slide05" class="slide"></div>
            <script type="text/javascript">$("#slide05").jdSlide({width:184,height:184,pics:<s:property value='shopImages5' />})</script>
        </div>
        <div class="wrapper03-L-B">
        	<ul>
            	<s:iterator value="productTypeIds5">
		        	<li><a href="goProductListWeb.do?productPojo.status=1&productPojo.productTypeId=<s:property value='id'/>#infor"><s:property value='name'/></a></li>
		        </s:iterator>
             </ul>
         </div>
     </div>
     <div class="wrapper03-M"><a href="<s:property value='imageUrl7'/>"><img src="/upfiles/notice/<s:property value='adimages7'/>" alt="" width="265" height="410" /></a></div>
     <div class="wrapper03-R">
     	<ul>
     	<s:iterator value="productTypeList5">
        	<li><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target="_blank" title="<s:property value="productName" />"><div class="wrapper03-R-txt01"><s:if test="productName.length()>14"><s:property value="productName.substring(0,14)+'...'" /></s:if><s:else><s:property value="productName" /></s:else><div class="wrapper03-R-txt02"><s:property value="productSketch" /></div></div></a><div class="wrapper03-R-txt01-Pic"><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target="_blank"><img src="/upfiles/product/small/<s:property value='image'/>" alt="" width="155" height="155"/></a></div>
        </s:iterator>
        </ul>
    </div> 
</div>

<div class="wrapper03">
	<div class="wrapper03-L">
    	<div class="wrapper06-L-T">
        	<div class="wrapper03-L-T-Floor">6F</div>
        	<div class="wrapper03-L-T-title"><img src="images/index_100.jpg" alt="" width="34" height="34" /><div><style>a{text-decoration:none}</style><a  class="wrapper03-L-T-txt" href=goProductListWeb.do?productPojo.productTypeIds=6#infor>其他玩具</a></div></div>
        </div>
        <div class="wrapper03-L-M">
        	<div id="slide06" class="slide"></div>
            <script type="text/javascript">$("#slide06").jdSlide({width:184,height:184,pics:<s:property value='shopImages6' />})</script>
        </div>
 <div class="wrapper03-L-B">
        	<ul>
            	<s:iterator value="productTypeIds6">
		        	<li><a href="goProductListWeb.do?productPojo.status=1&productPojo.productTypeId=<s:property value='id'/>#infor"><s:property value='name'/></a></li>
		        </s:iterator>
             </ul>
         </div>
     </div>
     <div class="wrapper03-M"><a href="<s:property value='imageUrl8'/>"><img src="/upfiles/notice/<s:property value='adimages8'/>" alt="" width="265" height="410" /></a></div>
     <div class="wrapper03-R">
     	<ul>
     	<s:iterator value="productTypeList6">
        	<li><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target="_blank" title="<s:property value="productName" />"><div class="wrapper03-R-txt01"><s:if test="productName.length()>14"><s:property value="productName.substring(0,14)+'...'" /></s:if><s:else><s:property value="productName" /></s:else><div class="wrapper03-R-txt02"><s:property value="productSketch" /></div></div></a><div class="wrapper03-R-txt01-Pic"><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target="_blank"><img src="/upfiles/product/small/<s:property value='image'/>" alt="" width="155" height="155"/></a></div>
        </s:iterator>
        </ul>
    </div> 
</div>

<div id="leftsead">
	<!--<ul>
		
		<li><a href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4001501677&aty=0&a=0&curl=&ty=1" target="_blank"><img src="images/ll04.png" width="131" height="49" class="hides"/><img src="images/l04.png" width="47" height="49" class="shows" /></a></li>
		
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
