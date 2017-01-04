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
<link href="/css/shop_details.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<title>淘竹马店铺街</title>
<script type="text/javascript" src="js/_head.js"></script>
<script>
$(document).ready(function(){
	$('.shop_details-Part01-R-list li').hover(function(){
		$(this).find('.iconx').show();
	},function(){
		$(this).find('.iconx').hide();
	});
});
</script>
<script type="text/javascript">  
function shopCo(val){
	$.ajax({// 调用jquery的ajax方法  
        type:"GET",// 设置ajax方法提交数据的形式  
        url:"/addUserShopCollect.do",// 把数据提交到ok.php       
        data: "userShopCollect.shopId="+val,  
        success:function(data){// 提交成功后的回调，msg变量是ok.php输出的内容 
            if(data == 1){
    			alert("店铺收藏成功");
    		}else{
    			alert("该店铺收藏过了");
    		}
        },
        error: function(){
		//alert("请登录后收藏店铺");
        	window.location.replace("/doLoginWeb.do?url="+window.location.href);
    	} 
    });  
}
</script> 
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="clear"></div>



<div class="delangyes">
	<div id="delang">
		<div class="topDelang" style="background:url(<s:if test="#shopPojo.topImage!=null">/upfiles/shop/${shopPojo.topImage}</s:if><s:else>images/topImages.jpg</s:else>) no-repeat top center;z-index:100;"></div>
    </div>
</div>
<!--<div class="delangyes">
	<div id="delang">
		<div class="topDelang" style="background:url(<s:if test="#shopPojo.topImage!=null">/upfiles/shop/${shopPojo.topImage}</s:if><s:else>images/topImages.jpg</s:else>) no-repeat top center;z-index:100;"></div>
    </div>
</div>-->

<%-- <div style="background:url(<s:if test="shopPojo.topImage!=null">/upfiles/shop/${shopPojo.topImage}</s:if><s:else>images/shop_details_02.jpg</s:else>) no-repeat;height:200px;">
	<div style="margin-left:50px;" >
	<img src="/upfiles/shop/${shopPojo.images}" alt="" width="145" height="145" /> <font color="#ffffff" size="6">${shopPojo.name}</font> 
	</div>
</div> --%>


<div class="shop_details-Part01">
	<div class="shop_details-Part01-L">
    	<div class="shop_details-Part01-L-title">店铺信息</div>
        <div class="shop_details-Part01-L-Pic"><img src="images/shop_details_08.jpg" alt="" width="189" height="33" /></div>
        <div class="shop_details-Part01-L-Txt01">${shopPojo.name}</div>
        <div class="shop_details-Part01-L-Txt02">
        	经营模式：<font color="#333333">经销批发</font> <font color="#aa6d00">[已认证]</font><br />
        <!--所在地区：<font color="#333333">${shopPojo.provinceName}${shopPojo.cityName}${shopPojo.areaName}${shopPojo.address}</font>-->
        	所在地区：<font color="#333333">${shopPojo.address}</font>
        </div>
        <div class="shop_details-Part01-L-Txt03">
        <span><s:if test="#shopPojo.locationCertification==1"><img src="images/new_12.jpg" alt="" width="19" height="19" /> 实地认证</s:if></span>
        <span class="shop_details-Part01-L-Txt03-R"><s:if test="#shopPojo.identityCertification==1"><img src="images/new_14.jpg" alt="" width="19" height="19" /> 身份认证</s:if></span></div>
        <div class="shop_details-Part01-L-Txt04"><a onclick="shopCo(${shopPojo.id})" class="shop_details-Part01-L-Txt04-button">店铺收藏</a></div>
    </div>
    <div class="shop_details-Part01-R">
    	<div class="shop_details-Part01-R-title">主推产品</div>
        <div class="shop_details-Part01-R-list">
        	<ul>
        		<s:iterator value="productListYes">
        			<li>
	                	<div class="shop_details-Part01-R-list-Pic">
	                		<div class="shop_details-Part01-R-list-Pic-txt iconx"><s:if test="#session.wuser.type==3">¥<fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></s:if><s:else>分销商登录查看</s:else><br /><s:property value='productName'/></div><a href="/goProductDetail.do?productPojo.id=<s:property value='id'/>"><img src="/upfiles/product/<s:property value='image'/>" alt="" width="237" height="245" /></a>
	                	</div>
               	 	</li>
				</s:iterator>
            </ul>
        </div>
    </div>
</div>

<div class="shop_details-Part02">
	<ul id="body">
		<s:iterator value="productlist">
        		<li>
	            <div class='shop_details-Part02-Pic'><a href='/goProductDetail.do?productPojo.id=<s:property value='id' />'><img src='/upfiles/product/<s:property value='image' />' alt='' width='225' height='225' /></a></div>
		        <div class='shop_details-Part02-txt01'><s:if test="productName.length()>18"><s:property value="productName.substring(0,18)+'...'" /></s:if><s:else><s:property value="productName" /></s:else></div>
		        <div class='shop_details-Part02-txt02'>
		        <div class='shop_details-Part02-txt02-L' style="
    width: 100px;
"><s:if test="#session.wuser.type==3">¥<fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></s:if><s:else>分销商登录查看</s:else></div>
		        <div class='shop_details-Part02-txt02-R' style="
    float: right;
">总销量：<font color='#FF0000'><s:property value='sellNumber' /></font></div>
		        </div>
	            </li>
        	</s:iterator>
        </ul>
    	
    <div class="clear"></div>
    
    <div class="favorite_04-page">
    	<div class="digg" id="Pagination"></div>
    </div>
</div>
<script type="text/javascript">
$(function() {
	$("#Pagination").pagination(<s:property value='page.rowCount'/>, {
		current_page : <s:property value='page.pageNo'/>-1,
		items_per_page:15,	//每页显示数量
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
	window.location.href="shopDetailsWeb.do?page.pageNo="+pageNo+"&page.rowCount=<s:property value='page.rowCount'/>&shop.id=${shopPojo.id}";
	return false;
}

</script>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
