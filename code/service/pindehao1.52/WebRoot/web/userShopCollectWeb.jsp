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
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<title>淘竹马玩具分销平台</title>
<script type="text/javascript" src="js/_head.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
		url:'userShopCollectWeb.do',
		type:'post',
		dataType: 'json',
		error: function(XMLHttpRequest, textStatus, errorThrown){
    	},
    	success: function(result){
    		valueShop(result);
    	}
		});
	});
	function valueShop(result){
		var data = eval(result);
		$("#shop_list").html("");
		for(var i=0;i<data.length;i++){
			if(data[i].status==0){
			$("#shop_list").append(
			"<div class='favorite_03-line'>"+
			"<div class='favorite_03-line-L0'>"+
	        "<div class='favorite_03-line-L-L'><a href='shopDetailsWeb.do?shop.id="+data[i].shopId+"'><img src='/upfiles/shop/"+data[i].shopImages+"' alt='' width='52' height='52' /></a></div>"+
	        "<div class='favorite_03-line-L-M'><a  href='shopDetailsWeb.do?shop.id="+data[i].shopId+"'><img src='images/favorite_01_13.jpg' alt='' width='18' height='18' />"+data[i].shopName+"</a><p class='favorite_03-line-L-M02'>"+data[i].suserName+"<br />地址："+data[i].shopAddress+"</p></div>"+
	        "<div class='favorite_03-line-L-R'><a onclick='del("+data[i].id+")'><img src='images/favorite_02_08.jpg' alt='' width='18' height='18' /></a><br /><p>店铺已下架</p></div>"+
	        
	        
	        "</div>"
	        );
			}
			else{
			$("#shop_list").append(
						"<div class='favorite_03-line'>"+
							"<div class='favorite_03-line-L'>"+
				        "<div class='favorite_03-line-L-L'><a href='shopDetailsWeb.do?shop.id="+data[i].shopId+"'><img src='/upfiles/shop/"+data[i].shopImages+"' alt='' width='52' height='52' /></a></div>"+
				        "<div class='favorite_03-line-L-M'><a  href='shopDetailsWeb.do?shop.id="+data[i].shopId+"'><img src='images/favorite_01_13.jpg' alt='' width='18' height='18' />"+data[i].shopName+"</a><p class='favorite_03-line-L-M02'>"+data[i].suserName+"<br />地址："+data[i].shopAddress+"</p></div>"+
				        "<div class='favorite_03-line-L-R'><a onclick='del("+data[i].id+")'><img src='images/favorite_02_08.jpg' alt='' width='18' height='18' /></a></div>"+
				        "</div>"
				        );
			}
			$("#shop_list").append("<div class='favorite_03-list'><ul id='shop"+data[i].suserId+"'>");
	        $.ajax({
				url:'getShopProduct.do?userShopCollect.suserId='+data[i].suserId,
				type:'post',
				dataType: 'json',
				error: function(XMLHttpRequest, textStatus, errorThrown){
    		},
    		success: function(result2){
    			valueProduct(result2);
    		}
			});
	        
	        $("#shop_list").append("</ul>"+
	        "</div>"+
	        "<div class='clear'></div>"+
	    	"</div>");
		}
	}
	function valueProduct(result){
		var data = eval(result);
		for(var i=0;i<data.length;i++){
			if(i==0){
			$("#shop"+data[i].userId).html("");
			}
			$("#shop"+data[i].userId).append(
	    		"<li>"+
	            "<div class='favorite_03-list-Pic'><a href='goProductDetail.do?productPojo.id="+data[i].id+"'><img src='/upfiles/product/"+data[i].image+"' alt='' width='147' height='147' /></a></div>"+
	            "<div class='favorite_01-list-Txt01'>"+data[i].productName+"</div>"+
	            "<div class='favorite_01-list-Txt02'>¥ "+parseFloat(data[i].distributionPrice).toFixed(2)+"</div>"+
	            "</li>"
	        );
		}
	}
	
	function del(val)
	{
		//alert(val);
		if(confirm("你真的想删除该记录么？"))
		{
			var url = "deluserShopCollectWeb.do?userShopCollect.id="+val;	
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
			window.location.href = "goUserShopCollectWeb.do";
		}else{
			alert("删除失败");
		}
	}
	</script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="clear"></div>

<div class="help-nav-width">
	<div style="width:1190px; margin:0 auto;">
        <div class="favorite_01-logo">我的淘竹马<span class="register-txt02">收藏夹</span></div>
        <div class="favorite_01-nav">
            <ul>
                <li><a href="userCollectWeb.do">宝贝收藏</a></li>
                <li><a href="goUserShopCollectWeb.do"><span>店铺收藏</span></a></li>
            </ul>
        </div>
    </div>
</div>

<div class="favorite_01-Bg">
	<div class="favorite_01-title"><div class="favorite_01-title-line">全部店铺</div></div>
    	<div id="shop_list"></div>
    
    
    <div class="favorite_01-title"><div class="favorite_02-title-line">热卖单品</div></div>
    <div class="favorite_02-list">
    	<ul>
        	<s:iterator value="productlist">
    			<li>
	            	<div class="favorite_02-list-Pic"><a href="goProductDetail.do?productPojo.id=<s:property value='id'/>"><img src="/upfiles/product/<s:property value='image'/>" alt="" width="218" height="218" /></a></div>
	            	<s:if test="#session.wuser.type==3"><div class="favorite_02-list-Txt01">¥<fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /><span class="favorite_02-list-Txt03">¥ <s:property value='sellingPrice'/></span></div></s:if>
	            	<s:else><span style="font-size: 14px;float: left;">分销商登录查看</span></s:else>
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
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
