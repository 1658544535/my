<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>淘竹马分销平台</title>
<script type="text/javascript">
	if(${productPojo.productTypeId}!=null&&${productPojo.productTypeId}!="")
	{
		var ps=${productPojo.productTypeId};
		$.ajax({
		url:'typeName.do?productTypes='+ps,
		type:'post',
		dataType:'json',
		async:'false',
		error: function(XMLHttpRequest, textStatus, errorThrown){
		alert("加载失败!!!!!");
    	},
    	success: function(result){
    		document.title=result;
    	}});
}
</script>
<script type="text/javascript">
function searchBtn(){
	$("#sysform").attr("action","searchWebByName.do");
	$("#sysform").submit();
}
</script>
<style type="text/css">
.search-Part02-R-span {
margin-right: 15px;
}
</style>
<script type="text/javascript">  
$(function(){
	$("#BeginPrice").change(function(){
		$("#BeginPrice2").val($("#BeginPrice").val());
		$("#prform").submit();
	});
	$("#EndPrice").change(function(){
		$("#EndPrice2").val($("#EndPrice").val());
		$("#prform").submit();
	});
});
function collectCon(val){
	$.ajax({
		url:'addFenxiao.do?userConsumerCollectPojo.productId='+val,
		type:'post',
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown){
		alert("添加分销失败");
    	},
    	success: function(result){
    		if(result == 1){
    			alert("添加分销成功");
    		}else if(result == 3){
    			 window.location.replace("/doLoginWeb.do?url="+window.location.href);
    		}else if(result == 4){
    			alert("该产品已在分销产品库中");
    		}else if(result == 5){
    			alert("请先申请成为分销商");
    		}else{
    			alert("添加分销失败");
    		}
    	}
		});
}
    
function collect(pid){
 $.ajax({// 调用jquery的ajax方法  
        type:"GET",// 设置ajax方法提交数据的形式  
        url:"/addProductCollect.do",// 把数据提交到ok.php       
        data: "productPojo.id="+pid,  
        success:function(data){// 提交成功后的回调，msg变量是ok.php输出的内容 
            if(data == 1){
    			alert("该产品收藏成功");
    		}else if(data == 3){
    			 window.location.replace("/doLoginWeb.do?url="+window.location.href);
    		}else if(data == 4){
    			alert("该产品已在产品收藏库中");
    		}else{
    			alert("该产品收藏失败");
    		}
        },
        error: function(){
		alert("收藏商品失败");
    	} 
    });  
	
}
function pidBtn(val){
	$("#productTypeIds").val(val);
	$("#prform").submit();
}
function brandBtn(val){
	$("#brand").val(val);
	$("#prform").submit();
}
function textureBtn(val){
	$("#texture").val(val);
	$("#prform").submit();
}
function ageBtn(val){
	$("#age").val(val);
	$("#prform").submit();
}
function searchProductNo(){
	$("#opform").attr("action","searchProductNo.do#tabs-3");
	$("#opform").submit();
} 
function productNameEnBtn1(){
var price_sorting =document.getElementById("productNameEn").value;


if(price_sorting=="0"){

	$("#productNameEn").val(33);
	
		
}else{

	
	$("#productNameEn").val(0);
}

	$("#prform").submit();
}
function productNameEnBtn2(){
var price_sorting =document.getElementById("productNameEn").value;


if(price_sorting=="1"){

	$("#productNameEn").val(11);
	
		
}else{

	
	$("#productNameEn").val(1);
}

	$("#prform").submit();
}
function productNameEnBtn3(){
var price_sorting =document.getElementById("productNameEn").value;


if(price_sorting=="2"){

	$("#productNameEn").val(22);
	
		
}else{

	
	$("#productNameEn").val(2);
}

	$("#prform").submit();
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
	<form action="http://www.taozhuma.com/searchWeb.do" method="post" id="sysform">
	<div class="search2">
	
	<input name="productPojo.productName" type="text"  placeholder="搜索 商品"  class="search-input">
	<button type="submit" class="search-button" onclick="searchBtn()">搜 索</button></div>
	</form>  </div>
	<div id="con_one_2" style="display:none;">
	<form action="searchWeb.do" method="post" id="shopform">
	<div class="search2">
	
	<input name="shop.name" type="text" value="搜索 店铺" onfocus="this.value=&#39;&#39;" onblur="if(this.value==&#39;&#39;){this.value=&#39;搜索 店铺&#39;}" class="search-input">
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
            <li class="indexColor arrow"><a href="goIndexWeb.do"><span><img src="images/index_08.jpg" alt="" width="20" height="20" /> 首页</span></a></li>
            <li><a href="goNewWeb.do">新品快订</a></li>
            <li><a href="goshopWeb.do">店铺街</a></li>
            <li><a href="infoPageList.do">行业资讯</a></li>
        </ul>
        
    <div class="account">
   	  <span class="account-line"></span>
	  <s:if test='#session.wuser != null && #session.wuser.type=="3"'>
   	  	<a href="myConsumerWeb.do"><div class="account-Bg" style="background:url(../images/index_09.jpg) no-repeat center center;color:#fff">我的帐户</div></a>
   	  </s:if>
   	  <s:elseif test='#session.wuser != null && #session.wuser.type=="2"'>
   	  	<a href="gomySupplier.do"><div class="account-Bg" style="background:url(../images/index_09.jpg) no-repeat center center;color:#fff">我的帐户</div></a>
   	  </s:elseif>
   	  <s:else>
   	  	<!--<a href="#"><div class="account-Bg">我的帐户</div></a>-->
   	  </s:else>
    </div>
    </div>
</div> 
<div class="clear"></div>
<div class="bigeyes">
	<div id="banner" style="background-color:<s:property value='backgroundColor'/>">
    	<div class="topBanner" style="background:url(/upfiles/notice/<s:property value='adimages'/>) no-repeat top center;"></div>
    </div>
</div>
<jsp:include page="header_type.jsp"></jsp:include>

<form action="goProductListWeb.do?productPojo.status=1#infor" method="post" id="prform">
<input id="status" name="status" type="hidden" value="1" class="search-input02"/>
<input id="productTypeId" name="productPojo.productTypeId" type="hidden" value="<s:property value='productPojo.productTypeId'/>" class="search-input02"/>
<input id="productTypeIds" name="productPojo.productTypeIds" type="hidden" value="<s:property value='productPojo.productTypeIds'/>" class="search-input02"/>
<input id="brand" name="productPojo.brand" type="hidden" value="<s:property value='productPojo.brand'/>" class="search-input02"/>
<input id="texture" name="productPojo.texture" type="hidden" value="<s:property value='productPojo.texture'/>" class="search-input02"/>
<input id="age" name="productPojo.age" type="hidden" value="<s:property value='productPojo.age'/>" class="search-input02"/>
<input id="productNameEn" name="productPojo.productNameEn" type="hidden" value="<s:property value='productPojo.productNameEn'/>" class="search-input02"/>
<input id="BeginPrice2" name="productPojo.BeginPrice" type="hidden" value="<s:property value='productPojo.BeginPrice'/>" class="search-input02"/>
<input id="EndPrice2" name="productPojo.EndPrice" type="hidden" value="<s:property value='productPojo.EndPrice'/>" class="search-input02"/>
</form> 

<div class="clear"></div>
<div id="infor" class="infor">
	<div class="product_list-L">
		<div class="product_list-L-Part01">
            <div class="product_list-L-Part01-L">产品分类：</div>
            <div class="product_list-L-Part01-R">
            	<s:iterator value="productTypeIds">
                    	<s:if test="productPojo.productTypeIds==id"> 
                    		<span class="search-Part02-R-span"><a onclick="pidBtn(<s:property value='id' />)"><font color="#ff0000"><s:property value='name' /></font></a><a onclick="pidBtn()"><img src="images/productsearchcancle.png" alt=""  /></a></span>
                    	</s:if>
                    	<s:else>
                    		<span class="search-Part02-R-span"><a onclick="pidBtn(<s:property value='id' />)"><s:property value='name' /></a></span>
                    	</s:else>
		        	</s:iterator>
            </div>
            <div class="clear"></div>
    	</div>
    	<div class="product_list-L-Part02">
            <div class="product_list-L-Part01-L">品牌：</div>
             
            <div class="product_list-L-Part01-R">
            	<s:iterator value="brand">
                    	<s:if test="productPojo.brand==value"> 
                    		<span class="search-Part02-R-span"><a onclick="brandBtn(<s:property value='value' />)"><font color="#ff0000"><s:property value='name' /></font></a><a onclick="brandBtn()"><img src="images/productsearchcancle.png" alt=""  /></a></span>
                    	</s:if>
                    	<s:else>
                    		<span class="search-Part02-R-span"><a onclick="brandBtn(<s:property value='value' />)"><s:property value='name' /></a></span>
                    	</s:else>
		        		 
		        	</s:iterator>
            </div>
            <div class="clear"></div>
    	</div>
        
        <div class="product_list-L-Part02">
            <div class="product_list-L-Part01-L">材质：</div>
            <div class="product_list-L-Part01-R">
            	<s:iterator value="texture">
                     	<s:if test="productPojo.texture==value"><span class="search-Part02-R-span"><a onclick="textureBtn(<s:property value='value' />)"><font color="#ff0000"><s:property value='name' /></font></a><a onclick="textureBtn()"><img src="images/productsearchcancle.png" alt=""  /></a></span></s:if>
                     	<s:else><span class="search-Part02-R-span"><a onclick="textureBtn(<s:property value='value' />)"><s:property value='name' /></a></span></s:else>
		        </s:iterator>
            </div>
            <div class="clear"></div>
    	</div>
        
        <div class="product_list-L-Part02">
            <div class="product_list-L-Part01-L">适用年龄：</div>
            <div class="product_list-L-Part01-R">
            	<s:iterator value="age">
                     <s:if test="productPojo.age==value"><span class="search-Part02-R-span"><a onclick="ageBtn(<s:property value='value' />)"><font color="#ff0000"><s:property value='name' /></font></a><a onclick="ageBtn()"><img src="images/productsearchcancle.png" alt=""  /></a></span></s:if>
                     <s:else><span class="search-Part02-R-span"><a onclick="ageBtn(<s:property value='value' />)"><s:property value='name' /></a></span></s:else>
		        		 
		        </s:iterator>
            </div>
            <div class="clear"></div>
    	</div>
        
        <div class="product_list-L-Part03">
        	<div class="product_list-L-Part03-L">
                <ul>
                    <li><a href="#">综合排序</a></li>
                    <li><a onclick="productNameEnBtn1()">人气</a></li>
                    <li><a onclick="productNameEnBtn2()">销量</a></li>
                     <input id="hot_sorting" name="hot_sorting" type="hidden" value="33" class="search-input02"/>
                        <input id="sale_sorting" name="sale_sorting" type="hidden" value="11" class="search-input02"/>
                    <input id="price_sorting" name="price_sorting" type="hidden" value="2" class="search-input02"/>
                    <li><a onclick="productNameEnBtn3()">价格 <img src="images/search_02.png" alt="" width="18" height="18" /></a></li>
                </ul>
            </div>
            <div style="float:left;"><input id="BeginPrice" value="<s:property value='productPojo.BeginPrice'/>" type="text" class="product_list-L-Part03-input" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"/>&nbsp;&nbsp;&nbsp;~<input id="EndPrice" value="<s:property value='productPojo.EndPrice'/>" type="text" class="product_list-L-Part03-input" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"/></div>
            <!-- <div class="product_list-L-Part03-R">
            	<ul>
                    <li><a href="#"><img src="images/product_list_03.png" alt="" width="45" height="45" /></a></li>
                    <li><a href="#"><img src="images/product_list_06.png" alt="" width="45" height="45" /></a></li>
                </ul>
                <div class="product_list-L-Part03-R-Page"><a href="#"><</a> 1/100 <a href="#">></a></div>
            </div> -->
        </div>
        
        <div class="product_list-L-Part04">
        	<ul id="body">
        	<s:iterator value="productList">
        		<li><a href="goProductDetail.do?productPojo.id=<s:property value='id' />" target="_blank"><img src="/upfiles/product/small/<s:property value='image'/>" alt="" width="220" height="221" />
                    <div class='favorite_04-list-txt01'>
                    		<!-- 这里用了fn标签-->
                        <div class='favorite_04-list-txt01-L'>&nbsp<s:if test="#session.wuser.type==3">¥<fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></s:if>
                        <s:else>
                        	<span style="font-size: 14px;float: left;">分销商登录查看</span>
                        <!--*<fmt:formatNumber value="${fn:substring(distributionPrice,1,5)}" pattern="#0.00" />-->
                        </s:else>
                        </div>
                        <div class='favorite_04-list-txt01-R'><s:property value='sellNumber' />销量</div>
                    </div>
                    <div class='favorite_pro_04-list-txt02'><s:if test="productName.length()>16"><s:property value="productName.substring(0,16)+'...'" /></s:if><s:else><s:property value="productName" /></s:else></div>
                    <div class='favorite_pro_04-list-txt03'>
                        <div class='favorite_04-list-txt03-L'><img src='images/favorite_03_07.jpg' alt='' width='11' height='11' /><s:property value='shopName' /></div>
                       <div class='favorite_04-list-txt03-R'><s:if test="userAddress.length()>4"><s:property value="userAddress.substring(0,5)+''" /></s:if><s:else><s:property value="userAddress" /></s:else></div>
                        <div class='clear'></div>
                    </div></a>
                    <div class='search-Part06-txt01'>
                        <div class='search-Part06-txt01-L' onclick="collect(<s:property value='id' />);"><img src='images/search_07.jpg' alt='' width='25' height='25' /></div>
                        <div class='search-Part06-txt01-R' onclick="collectCon(<s:property value='id' />);"><img src='images/search_09.jpg' alt='' width='25' height='25' /> 添加分销</div>
                    </div>
                </li>
        	</s:iterator>
            </ul>
            <div class="clear"></div>
        </div>
        
        <div class="shop-page">
        	<div class="digg" id="Pagination"></div>
		</div>
    </div>
    
    <div class="product_list-R">
    	<div class="product_list-R-title">掌柜热卖</div>
        <div class="product_list-R-list">
        	<s:iterator value="hotAllProductList">
        		<ul>
            	<li><a  href="goProductDetail.do?productPojo.id=<s:property value='id'/>" target='_blank'><img src="/upfiles/product/small/<s:property value='image'/>" alt="" width="225" height="225" /></a>
                    <div class="product_list-R-list-txt">
                        <div color:#FFFFFF; >${productName}<div>
                        <div class="product_list-R-list-txt-L"><s:if test="#session.wuser.type==3">¥<fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></s:if><s:else><span style="font-size: 14px;float:left;width: 100px;">分销商登录查看</span></s:else></div>
                        <div class="product_list-R-list-txt-R">销量：<s:property value='sellNumber'/></div>
                        <div class="clear"></div>
                    </div>
                </li>
            	</ul>
             </s:iterator> 
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
	var pd=${productPojo.productTypeIds};
	document.title=typet(pd);
	function typet(pd){
		if(pd==1){
		return "遥控/电动玩具";
		}
		if(pd==2){
		return "早教/音乐玩具";
		}
		if(pd==3){
		return " 过家家玩具";
		}
		if(pd==4){
		return " 童车玩具";
		} 
		if(pd==5){
		return " 益智玩具";
		} 
		if(pd==6){
		return " 其它玩具";
		}
	}
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
<script type="text/javascript">
	$(function() {
		$("#Pagination").pagination(<s:property value='page.rowCount'/>, {
			current_page : <s:property value='page.pageNo'/>-1,
			items_per_page:20,	//每页显示数量
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
		window.location.href="goProductListWeb.do?page.pageNo="+pageNo+"&page.rowCount=<s:property value='page.rowCount'/>&productPojo.sortingType=<s:property value='productPojo.sortingType'/>&productPojo.productTypeId=<s:property value='productPojo.productTypeId'/>&productPojo.productTypeIds=<s:property value='productPojo.productTypeIds'/>&productPojo.brand=<s:property value='productPojo.brand'/>&productPojo.texture=<s:property value='productPojo.texture'/>&productPojo.age=<s:property value='productPojo.age'/>&productPojo.productNameEn=<s:property value='productPojo.productNameEn'/>&productPojo.BeginPrice=<s:property value='productPojo.BeginPrice'/>&productPojo.EndPrice=<s:property value='productPojo.EndPrice'/>";
		return false;
	}
</script>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
