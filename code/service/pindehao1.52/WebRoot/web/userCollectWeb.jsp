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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<title>淘竹马分销商</title>
<script>
$(document).ready(function(){
	$('.favorite_01-list li').hover(function(){
		$(this).find('.iconx').show();
	},function(){
		$(this).find('.iconx').hide();
	});
	
	$("#showMsgImg").hover(function(){
		$("#showMsg").show();
	},function(){
		$("#showMsg").hide();
	});
});
</script>
<script type="text/javascript">

	function del(val)
	{
		//alert(val);
		if(confirm("你真的想删除该记录么？"))
		{
			var url = "deluserCollectWeb.do?userCollect.id="+val;	
			doOpreator(url,null);
		}else{
			return ;
		}
		
	}
	function doOpreator(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage);
	}
	function goRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("删除成功");
			window.location.href = "userCollectWeb.do?userCollect.userId=${session.wuser.id}";
		}else{
			alert("删除失败");
		}
	}
	</script>
<style type="text/css">
	.grayBlack { 
		-webkit-filter: grayscale(100%); 
		cursor:pointer;
		-moz-filter: grayscale(100%); 
		-ms-filter: grayscale(100%); 
		-o-filter: grayscale(100%); 
		filter: grayscale(100%); 
		filter: gray; 
	} 
</style>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="clear"></div>

<div class="help-nav-width">
	<div style="width:1190px; margin:0 auto;">
        <div class="favorite_01-logo">我的淘竹马<span class="register-txt02">收藏夹</span></div>
        <div class="favorite_01-nav">
            <ul>
                <li><a href="userCollectWeb.do"><span>宝贝收藏</span></a></li>
                <li><a href="goUserShopCollectWeb.do">店铺收藏</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="favorite_01-Bg">
	<div class="favorite_01-title"><div class="favorite_01-title-line">全部宝贝</div></div>
    <div class="favorite_01-list">
    	<ul>
    		<s:iterator value="userCollectlist">
    			<s:if test="proStatus==1">
    			<li>
	            	<div class="favorite_01-list-Pic">
	                	<!--  <a href="#" class="iconx"><div class="favorite_01-list-Pic-Ico01"><img src="images/favorite_01_03.png" alt="" width="24" height="24" /></div></a>-->
	                    <a  class='iconx' onclick="del(<s:property value='id'/>)"><div class="favorite_01-list-Pic-Ico02"><img src="images/favorite_01_05.png" alt="" width="24" height="24" /></div></a>
	                    <a href="goProductDetail.do?productPojo.id=<s:property value='productId'/>"><img src="/upfiles/product/<s:property value='image'/>" alt="" width="147" height="147" /></a>
	                </div>
	                <div class="favorite_01-list-Txt01"><a href="goProductDetail.do?productPojo.id=<s:property value='productId'/>">
	                <s:if test='isIntroduce=="1"'><img src="images/favorite_01_13.jpg" alt="" width="18" height="18" /></s:if> <s:property value='productName'/></a></div>
	                
	                <s:if test='isIntroduce=="1"'>
	                <div class="favorite_01-list-Txt01">
	                &nbsp; &nbsp; <img src="images/favorite_01_17.jpg" alt="" width="16" height="16" /><span class="favorite_01-list-Txt02">¥ <fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></span>
	                <span class="favorite_01-list-Txt03"> ¥ <s:property value='sellingPrice'/></span></div></s:if>
	              
	                <s:if test='isIntroduce=="0"'>
	                <div class="favorite_01-list-Txt02">
	                <span class="favorite_01-list-Txt02">¥ <fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></span>
	                </div></s:if>
	            </li>
	            </s:if>
	            <s:else>
	            <li>
	            <div style="background:#e3e3e3;opacity:0.34;">
	            	<div class="favorite_01-list-Pic">
	                	<!--  <a href="#" class="iconx"><div class="favorite_01-list-Pic-Ico01"><img src="images/favorite_01_03.png" alt="" width="24" height="24" /></div></a>-->
	                	<a  class='iconx' onclick="del(<s:property value='id'/>)"><div class="favorite_01-list-Pic-Ico02"><img src="images/favorite_01_05.png" alt="" width="24" height="24" /></div></a>
	                    <!--<a onclick="del(<s:property value='id'/>)"><img src="/upfiles/product/<s:property value='image'/>" class="grayBlack" alt="" width="147" height="147" id="showMsgImg"/></a>--> 
	                	<a onclick="del(<s:property value='id'/>)"><img src="/upfiles/product/<s:property value='image'/>" alt="" width="147" height="147" id="showMsgImg"/></a>
	                    <a class='iconx' onclick="del(<s:property value='id'/>)" id="showMsg" style="position: absolute; top: 70px; left: 0;display:none;">&nbsp;&nbsp;&nbsp;<font color="f00000">该商品已经下架</font></a>
	                </div>
	                <div class="favorite_01-list-Txt01">
	                <s:if test='isIntroduce=="1"'><img src="images/favorite_01_13.jpg" alt="" width="18" height="18" /></s:if> <s:property value='productName'/></div>
	                
	                <s:if test='isIntroduce=="1"'>
	                <div class="favorite_01-list-Txt01">
	                &nbsp; &nbsp; <img src="images/favorite_01_17.jpg" alt="" width="16" height="16" /><span class="favorite_01-list-Txt02">¥ <fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></span>
	                <span class="favorite_01-list-Txt03"> ¥ <s:property value='sellingPrice'/></span></div></s:if>
	              
	                <s:if test='isIntroduce=="0"'>
	                <div class="favorite_01-list-Txt02">
	                <span class="favorite_01-list-Txt02">¥ <fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></span>
	                </div></s:if>
	                
	                </div>
	            </li>
	            </s:else>
			</s:iterator>
            
        </ul>
    </div>
    <div class="clear"></div>
    <div class="favorite_04-page">
    	<div class="digg" id="Pagination"></div>
    </div>
    <div class="favorite_01-title"><div class="favorite_02-title-line">热卖单品</div></div>
    <div class="favorite_02-list">
    	<ul>
    		<s:iterator value="productlist">
    			<li>
	            	<div class="favorite_02-list-Pic"><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>"><img src="/upfiles/product/<s:property value='image'/>" alt="" width="218" height="218" /></a></div>
	            	<div class='favorite_02-list-Txt01'>&nbsp
	            	<s:if test="#session.wuser.type==3">¥<fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /><span class="favorite_02-list-Txt03">¥ <s:property value='sellingPrice'/></span></s:if>
                    <s:else>
                    	<span style="font-size: 14px;float: left;">分销商登录查看</span></s:else>
	            	<!--<div class="favorite_02-list-Txt01">¥ <fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /> <span class="favorite_02-list-Txt03">¥ <s:property value='sellingPrice'/></span></div>-->
	                <div class="favorite_02-list-Txt02"><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>"><s:property value='productName'/></a></div>
	                <div class="favorite_02-list-Txt04">
	                	<div class="favorite_02-list-Txt04-L">销量：<s:property value='sellNumber'/></div>
	                    <div class="favorite_02-list-Txt04-R"><a href="shopDetailsWeb.do?shop.id=<s:property value='shopId'/>"><s:property value='shopName'/></a></div>
	                </div>
	            </li>
			</s:iterator>
        	
        </ul>
        <div class="clear"></div>
    </div>
</div>

<div class="clear"></div>
<!--分页-->
<script type="text/javascript">
$(function() {
	$("#Pagination").pagination(<s:property value='page.rowCount'/>, {
		current_page : <s:property value='page.pageNo'/>-1,
		items_per_page:14,	//每页显示数量
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
	window.location.href="userCollectWeb.do?page.pageNo="+pageNo+"&page.rowCount=<s:property value='page.rowCount'/>";
	return false;
}

</script>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

