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
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/g.base.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script>
	$(function() {
		$( "#tabs" ).tabs();
		var time = null;
		$('.nav_box-L-category li').hover(
			function () {
				$('div[r-cate]').hide();
				$(this).addClass('nav_box-L-category_a_hover');
				$('div[r-cate="'+$(this).attr('a-cate')+'"]').show();
				$('.topBanner').css('background','url('+$(this).attr('banner')+') no-repeat top center;');
			},
			function () {
				$(this).removeClass('nav_box-L-category_a_hover');
				var cate = $(this).attr('a-cate');
				time = setTimeout(function(){
					$('div[r-cate="'+cate+'"]').hide();
				},50);
			}
		);
		$(".nav_box-R-cate01").hover(function(){
				clearTimeout(time);
				$('li[a-cate="'+$(this).attr('r-cate')+'"]').addClass('nav_box-L-category_a_hover');
				$(this).show();
			},function(){
				var cate = $(this).attr('r-cate');
				time = setTimeout(function(){
					$('li[a-cate="'+cate+'"]').removeClass('nav_box-L-category_a_hover');
					$('div[r-cate]').hide();
			},50);
    	});
	});
	
	function search(){
		document.getElementById("idform").submit();
	}
	function search2(){
		document.getElementById("idform2").submit();
	}
</script>
<title>淘竹马行业资讯</title>
<meta name="keywords" content="玩具前沿 玩具行业 玩具资讯 淘竹马 玩具资讯 最新玩具资讯 玩具市场动态 玩具展会 玩具行业新闻 玩具市场" />
<meta name="description" content="“淘竹马”的行业资讯栏目中,汇聚了大量的最新、最全、最及时的玩具行业资讯。及时的发布一些最新的行业资讯情报。从报价资讯到行业动态分析,全方位报到最新、最热的资讯内容。" />
<script type="text/javascript">
function searchBtn(){
	$("#sysform").attr("action","searchWebByName.do#tabs-1");
	$("#sysform").submit();
}
function searchProductNo(){
	$("#opform").attr("action","searchProductNo.do#tabs-3");
	$("#opform").submit();
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
function openTick(){
	alert("请先成为分销商或者供应商！");
}
function searchShop(){
	$("#shopform").attr("action","searchShopWebByName.do#tabs-2");
	$("#shopform").submit();
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
	<!--<div class="logo"></div>-->
	<a href="goIndexWeb.do" class="logo"></a>
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
	<form action="http://www.taozhuma.com/searchWeb.do" method="post" id="sysform">
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
            <li><a href="goIndexWeb.do"><img src="images/new_01.jpg" alt="" width="20" height="20" /> 首页</a></li>
            <li><a href="goNewWeb.do">新品快订</a></li>
            <li><a href="goshopWeb.do">店铺街</a></li>
            <li class="indexColor arrow"><a href="#"><span>行业资讯</span></a></li>
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

<div class="infor">
	<div class="infor-L">
	<form action="infoPageList.do" id="idform" method="post">
    	<div class="infor-L-search">市场动态<input id="marketTitleKeyWord" name="marketTitleKeyWord"  value="<s:property value='marketTitleKeyWord'/>" type="text" class="infor-L-searchTxt01"/><input name="searchButton" type="button" value="搜 索" class="infor-L-searchTxt02" onclick="search()"/></div>
    </form>    
        <div class="infor-L-List">
        	<div class="infor-L-List-Title">
            	<div class="infor-L-List-Title-L">市场动态</div>
                <div class="infor-L-List-Title-R"><a href="infoList.do?type=2">更多</a></div>
            </div>
            <div class="infor-L-List-Txt">
            	<ul>
            		<s:iterator value="infoList">
                			<li><div class="infor-L-List-Txt01"><a href="infoDetail.do?infoPojo.id=<s:property value='id'/>"><s:property value="title"/></a></div><div class="infor-L-List-Txt02"><s:property value="createDateStr" /></div></li>
                	</s:iterator>
                </ul>
            </div>
        </div>
    </div>
    
    <div class="infor-R">
    <form action="infoPageList.do" id="idform2" method="post">
    	<div class="infor-L-search">行业资讯<input id="tradeTitleKeyWord" name="tradeTitleKeyWord" value="<s:property value='tradeTitleKeyWord'/>" type="text" class="infor-L-searchTxt01"/><input name="searchButton" type="button" value="搜 索" class="infor-L-searchTxt02" onclick="search2()"/></div>
    </form>    
        <div class="infor-L-List">
        	<div class="infor-L-List-Title">
            	<div class="infor-L-List-Title-L">行业资讯</div>
                <div class="infor-L-List-Title-R"><a href="infoList.do?type=1">更多</a></div>
            </div>
            <div class="infor-L-List-Txt">
            	<ul>
	            	<s:iterator value="pageInfoList">
                			<li><div class="infor-L-List-Txt01"><a href="infoDetail.do?infoPojo.id=<s:property value='id'/>"><s:property value="title"/></a></div><div class="infor-L-List-Txt02"><s:property value="createDateStr" /></div></li>
                	</s:iterator>
                </ul>
            </div>
        </div>
    </div>
    
</div>
<div id="leftsead">
	<!--<ul>
		
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
