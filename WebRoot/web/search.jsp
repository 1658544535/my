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
<link href="/css/search.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<title></title>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script>
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>
<script type="text/javascript">
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

function searchBtn(){
	$("#sysform").attr("action","searchWebByName.do");
	$("#sysform").submit();
}
function brandBtn(val){
	$("#brand").val(val);
	$("#sysform").attr("action","searchWebByName.do");
	$("#sysform").submit();
}
function textureBtn(val){
	$("#texture").val(val);
	$("#sysform").attr("action","searchWebByName.do");
	$("#sysform").submit();
}
function ageBtn(val){
	$("#age").val(val);
	$("#sysform").attr("action","searchWebByName.do");
	$("#sysform").submit();
}
function remarksBtn(val){
	$("#remarks").val(val);
	$("#sysform").attr("action","searchWebByName.do#tabs-1");
	$("#sysform").submit();
}

function searchShop(){
	$("#shopform").attr("action","searchShopWebByName.do#tabs-2");
	$("#shopform").submit();
}
function searchProductNo(){
	$("#opform").attr("action","searchProductNo.do#tabs-3");
	$("#opform").submit();
}
</script>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

<div class="top02-width">
	<div class="search02" id="tabs">
    	<!--<div class="logo03"></div>-->
    	<a href="goIndexWeb.do" class="logo03"></a>
    	<div class="search02-Txt">
        	<ul>
            	<li><a href="#tabs-1">产品</a><span class="search02-Txt-line">｜</span></li>
				<li><a href="#tabs-2">店铺</a><span class="search02-Txt-line">｜</span></li>
                <li><a href="#tabs-3">货号</a></li>
            </ul>
        </div>
        <form action="searchWebByName.do" method="post" id="sysform">
        <div id="tabs-1">
        	<div class="search02_tabs-1">
        		<input name="productPojo.productName" type="text" value="<s:property value='productPojo.productName'/>" class="search-input02"/>
        		<input id="brand" name="productPojo.brand" type="hidden" value="<s:property value='productPojo.brand'/>" class="search-input02"/>
        		<input id="texture" name="productPojo.texture" type="hidden" value="<s:property value='productPojo.texture'/>" class="search-input02"/>
        		<input id="age" name="productPojo.age" type="hidden" value="<s:property value='productPojo.age'/>" class="search-input02"/>
        		<input id="remarks" name="productPojo.productNameEn" type="hidden" value="<s:property value='productPojo.productNameEn'/>" class="search-input02"/>
        	<button  type="submit" class="search-button" onclick="searchBtn()">搜 索</button>
        	</div>
            <div class="clear"></div>
         </form>    
            <div class="search-width">
                <div class="search-Part01">
                    <div class="search-Part01-L">搜索结果 </div>
                    <div class="search-Part01-R">共 <s:property value='page.rowCount'/>件宝贝</div>
                </div>
                
                <div class="search-Part02">
                    <div class="search-Part02-L">品牌：</div>
                    <div class="search-Part02-R">
                    <s:iterator value="brand">
                    	<s:if test="productPojo.brand==value"> 
                    		<span class="search-Part02-R-span"><a onclick="brandBtn(<s:property value='value' />)"><font color="#ff0000"><s:property value='name' /></font></a></span>
                    	</s:if>
                    	<s:else>
                    		<span class="search-Part02-R-span"><a onclick="brandBtn(<s:property value='value' />)"><s:property value='name' /></a></span>
                    	</s:else>
		        		 
		        	</s:iterator>
                    </div>
                    <div class="clear"></div>
                </div>
                
                <div class="search-Part03">
                    <div class="search-Part03-L">材质：</div>
                    <div class="search-Part02-R">
                     <s:iterator value="texture">
                     	<s:if test="productPojo.texture==value"><span class="search-Part02-R-span"><a onclick="textureBtn(<s:property value='value' />)"><font color="#ff0000"><s:property value='name' /></font></a></span></s:if>
                     	<s:else><span class="search-Part02-R-span"><a onclick="textureBtn(<s:property value='value' />)"><s:property value='name' /></a></span></s:else>
		        	</s:iterator>
                     </div>
                    <div class="clear"></div>
                </div>
                
                <div class="search-Part04">
                    <div class="search-Part02-L">适用年龄：</div>
                    <div class="search-Part02-R">
                     <s:iterator value="age">
                     <s:if test="productPojo.age==value"><span class="search-Part02-R-span"><a onclick="ageBtn(<s:property value='value' />)"><font color="#ff0000"><s:property value='name' /></font></a></span></s:if>
                     <s:else><span class="search-Part02-R-span"><a onclick="ageBtn(<s:property value='value' />)"><s:property value='name' /></a></span></s:else>
		        		 
		        	</s:iterator>
                    </div>
                    <div class="clear"></div>
                </div>
                
                <div class="search-Part05">
                    <ul>
                        <li><a href="#">综合排序</a></li>
                        <li><a onclick="remarksBtn(1)">销量</a></li>
                        <li><a onclick="remarksBtn(2)">价格 <img src="images/search_03.png" alt="" width="18" height="18" /></a></li>
                    </ul>
                </div>
                <div class="search-Part06">
                    <ul>
                    	<s:iterator value="productList">
			        		<li><a href="goProductDetail.do?productPojo.id=<s:property value='id' />" target="_blank"><img src='/upfiles/product/<s:property value='image' />' width="220" height="221" />
	                            <div class="favorite_04-list-txt01">
	                                <div class="favorite_04-list-txt01-L"><s:if test="#session.wuser.type==3">¥<s:property value='distributionPrice' /></s:if><s:else> <span style="font-size: 14px;float: left;"> 分销商登录查看</span></s:else></div>
	                                <div class="favorite_04-list-txt01-R"><s:property value='sellNumber' />销量</div>
	                            </div>
	                            <div class="favorite_04-list-txt02"><s:if test="productName.length()>16"><s:property value="productName.substring(0,15)+'...'" /></s:if><s:else><s:property value="productName" /></s:else></div>
	                            <div class="favorite_04-list-txt03">
	                                <div class="favorite_04-list-txt03-L"><img src="images/favorite_03_07.jpg" alt="" width="11" height="11" /><s:if test="shopName.length()>8"><s:property value="shopName.substring(0,7)+'...'" /></s:if><s:else><s:property value="shopName" /></s:else></div>
	                                <div class="favorite_04-list-txt03-R"><s:property value='location' /></div>
	                                <div class="clear"></div>
	                            </div></a>
	                            <div class="search-Part06-txt01">
	                                <div class="search-Part06-txt01-L" onclick="collect(<s:property value='id' />);"><img src="images/search_07.jpg" alt="" width="25" height="25" /></div>
	                                <div class="search-Part06-txt01-R" onclick="collectCon(<s:property value='id' />);"><img src="images/search_09.jpg" alt="" width="25" height="25" /> 添加分销</div>
	                            </div>
	                        </li>
			        	</s:iterator>
                       
                    </ul>
                    <div class="clear"></div>
                </div>
                
                <div class="favorite_04-page">
                    <div class="digg" id="Pagination1"></div>
                </div>
            </div>
        </div><!--tabs-1-->
        
<script type="text/javascript">
$(function() {
	$("#Pagination").pagination(<s:property value='page.rowCount'/>, {
		current_page : <s:property value='page.pageNo'/>-1,
		items_per_page:10,	//每页显示数量
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
$(function() {
	$("#Pagination1").pagination(<s:property value='page.rowCount'/>, {
		current_page : <s:property value='page.pageNo'/>-1,
		items_per_page:10,	//每页显示数量
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
	window.location.href="searchWebByName.do?page.pageNo="+pageNo+"&page.rowCount=<s:property value='page.rowCount'/>&productPojo.productName=<s:property value='productPojo.productName'/>&productPojo.brand=<s:property value='productPojo.brand'/>&productPojo.texture=<s:property value='productPojo.texture'/>&productPojo.age=<s:property value='productPojo.age'/>&productPojo.productNameEn=<s:property value='productPojo.productNameEn'/>";
	return false;
}

</script>       

        <div id="tabs-2">
        <form action="searchWeb.do" method="post" id="shopform">
        	<div class="search02_tabs-1">
        	<input name="shop.name" type="text" value="<s:property value='shop.name'/>" class="search-input02"/>
        	<button  type="submit" class="search-button" onclick="searchShop()">搜 索</button>
        	</div>
            
            <div class="clear"></div>
         </form>   
            <div class="search-width">
                <div class="search-Part01">
                    <div class="search-Part01-L">搜索结果</div>
                    <div class="search-Part01-R">共 ${num}间店铺</div>
                </div>
                
                <div class="search-Part07">
                    <ul>
                    	<s:iterator value="shoplist">
			        		<li><a href='shopDetailsWeb.do?shop.id=<s:property value='id' />'>
				            <div class='shop-R-Pic'>
				            <img src='/upfiles/shop/<s:property value='images' />' alt='' width='217' height='217' /></div></a>
					        <div class='shop-R-Pic-txt'>主打：<s:property value='mainCategoryName' /></div>
					        <div class='shop-R-Pic-txt02'>地址：<span class='shop-R-Pic-txt03'><s:property value='provinceName' /><s:property value='cityName'/><s:property value='areaName'/></span></div>
					        <div class='shop-R-button'>
					        <a href='shopDetailsWeb.do?shop.id=<s:property value='id' />'><div class='shop-R-button-L'><s:property value='name' /></div></a>
					        <a href='shopDetailsWeb.do?shop.id=<s:property value='id' />'><div class='shop-R-button-R'>进入店铺</div></a>
					        </div>
				            </li>
			        	</s:iterator>
                        
                    </ul>
                    <div class="clear"></div>
                </div>
                
                <div class="favorite_04-page">
                    <div class="digg" id="Paginationshop"></div>
                </div>
            </div>
        </div><!--tabs-2-->
        
 <script type="text/javascript">
$(function() {
	$("#Paginationshop").pagination(${num}, {
		current_page : <s:property value='page.pageNo'/>-1,
		items_per_page:10,	//每页显示数量
		next_text : '>',
		prev_text : '<',
		first_text: "|<",
		last_text: ">|",
		num_edge_entries: 2,
		num_display_entries: 4,
		prev_show_always : true,
		next_show_always : true,
		callback: pageselectCallbacks,
		load_first_page : function() {
			return false;
		}
	});
});

function pageselectCallbacks(pageindex, jq) {
	var pageNo = pageindex+1;
	window.location.href="searchShopWebByName.do?page.pageNo="+pageNo+"&page.rowCount=<s:property value='page.rowCount'/>&shop.name=<s:property value='shop.name'/>#tabs-2";
	return false;
}

</script> 
     
	<form action="searchProductNo.do#tabs-3" method="post" id="opform">
        <div id="tabs-3">
        	<div class="search02_tabs-1">
        		<input name="productPojo.productNum" type="text" value="<s:property value='productPojo.productNum'/>" class="search-input02"/>
        		<input id="brand" name="productPojo.brand" type="hidden" value="<s:property value='productPojo.brand'/>" class="search-input02"/>
        		<input id="texture" name="productPojo.texture" type="hidden" value="<s:property value='productPojo.texture'/>" class="search-input02"/>
        		<input id="age" name="productPojo.age" type="hidden" value="<s:property value='productPojo.age'/>" class="search-input02"/>
        		<input id="remarks" name="productPojo.productNameEn" type="hidden" value="<s:property value='productPojo.productNameEn'/>" class="search-input02"/>
        	<button  type="submit" class="search-button" onclick="searchBtn()">搜 索</button>
        	</div>
            <div class="clear"></div>
         </form>    
            <div class="search-width">
                <div class="search-Part01">
                    <div class="search-Part01-L">搜索结果 </div>
                    <div class="search-Part01-R">共 <s:property value='page.rowCount'/>件宝贝</div>
                </div>
                
                <div class="search-Part02">
                    <div class="search-Part02-L">品牌：</div>
                    <div class="search-Part02-R">
                    <s:iterator value="brand">
                    	<s:if test="productPojo.brand==value"> 
                    		<span class="search-Part02-R-span"><a onclick="brandBtn(<s:property value='value' />)"><font color="#ff0000"><s:property value='name' /></font></a></span>
                    	</s:if>
                    	<s:else>
                    		<span class="search-Part02-R-span"><a onclick="brandBtn(<s:property value='value' />)"><s:property value='name' /></a></span>
                    	</s:else>
		        		 
		        	</s:iterator>
                    </div>
                    <div class="clear"></div>
                </div>
                
                <div class="search-Part03">
                    <div class="search-Part03-L">材质：</div>
                    <div class="search-Part02-R">
                     <s:iterator value="texture">
                     	<s:if test="productPojo.texture==value"><span class="search-Part02-R-span"><a onclick="textureBtn(<s:property value='value' />)"><font color="#ff0000"><s:property value='name' /></font></a></span></s:if>
                     	<s:else><span class="search-Part02-R-span"><a onclick="textureBtn(<s:property value='value' />)"><s:property value='name' /></a></span></s:else>
		        	</s:iterator>
                     </div>
                    <div class="clear"></div>
                </div>
                
                <div class="search-Part04">
                    <div class="search-Part02-L">适用年龄：</div>
                    <div class="search-Part02-R">
                     <s:iterator value="age">
                     <s:if test="productPojo.age==value"><span class="search-Part02-R-span"><a onclick="ageBtn(<s:property value='value' />)"><font color="#ff0000"><s:property value='name' /></font></a></span></s:if>
                     <s:else><span class="search-Part02-R-span"><a onclick="ageBtn(<s:property value='value' />)"><s:property value='name' /></a></span></s:else>
		        		 
		        	</s:iterator>
                    </div>
                    <div class="clear"></div>
                </div>
                
                <div class="search-Part05">
                    <ul>
                        <li><a href="#">综合排序</a></li>
                        <li><a onclick="remarksBtn(1)">销量</a></li>
                        <li><a onclick="remarksBtn(2)">价格 <img src="images/search_03.png" alt="" width="18" height="18" /></a></li>
                    </ul>
                </div>
                <div class="search-Part06">
                    <ul>
                    	<s:iterator value="productList">
			        		<li><a href="goProductDetail.do?productPojo.id=<s:property value='id' />" target="_blank"><img src='/upfiles/product/<s:property value='image' />' width="220" height="221" />
	                            <div class="favorite_04-list-txt01">
	                                <div class="favorite_04-list-txt01-L"><s:if test="#session.wuser.type==3">¥<s:property value='distributionPrice' /></s:if><s:else><span style="font-size: 14px;float:left;width: 100px;">分销商登录查看</span></s:else></div>
	                                <div class="favorite_04-list-txt01-R"><s:property value='sellNumber' />销量</div>
	                            </div>
	                            <div class="favorite_04-list-txt02"><s:if test="productName.length()>16"><s:property value="productName.substring(0,15)+'...'" /></s:if><s:else><s:property value="productName" /></s:else></div>
	                            <div class="favorite_04-list-txt03">
	                                <div class="favorite_04-list-txt03-L"><img src="images/favorite_03_07.jpg" alt="" width="11" height="11" /><s:if test="shopName.length()>8"><s:property value="shopName.substring(0,7)+'...'" /></s:if><s:else><s:property value="shopName" /></s:else></div>
	                                <div class="favorite_04-list-txt03-R"><s:property value='location' /></div>
	                                <div class="clear"></div>
	                            </div></a>
	                            <div class="search-Part06-txt01">
	                                <div class="search-Part06-txt01-L" onclick="collect(<s:property value='id' />);"><img src="images/search_07.jpg" alt="" width="25" height="25" /></div>
	                                <div class="search-Part06-txt01-R" onclick="collectCon(<s:property value='id' />);"><img src="images/search_09.jpg" alt="" width="25" height="25" /> 添加分销</div>
	                            </div>
	                        </li>
			        	</s:iterator>
                       
                    </ul>
                    <div class="clear"></div>
                </div>
                
                <div class="favorite_04-page">
                    <div class="digg" id="Pagination"></div>
                </div>
            </div>
        </div><!--tabs-3-->
        
<script type="text/javascript">
$(function() {
	$("#Pagination").pagination(<s:property value='page.rowCount'/>, {
		current_page : <s:property value='page.pageNo'/>-1,
		items_per_page:10,	//每页显示数量
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
	window.location.href="searchProductNo.do?page.pageNo="+pageNo+"&page.rowCount=<s:property value='page.rowCount'/>&productPojo.productName=<s:property value='productPojo.productName'/>&productPojo.brand=<s:property value='productPojo.brand'/>&productPojo.texture=<s:property value='productPojo.texture'/>&productPojo.age=<s:property value='productPojo.age'/>&productPojo.productNameEn=<s:property value='productPojo.productNameEn'/>";
	return false;
}

</script>       



   	</div>
</div> 
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
