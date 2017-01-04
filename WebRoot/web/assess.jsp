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
<script type="text/javascript" src="/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马玩具分销平台</title>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="register-title">
	<div class="register-txt">淘竹马分销<span class="register-txt02">商品评价</span></div>
</div>

<div class="Store-information">
	<div class="Store-information01">店<br />铺<br />信<br />息</div>
	<div class="Store-information02"><img src="/upfiles/shop/<s:property value='shopDetail.shopImages'/>" alt="" width="68" height="68" /></div>
    <div class="Store-information03"><p class="Store-information03-txt01"><s:property value='shopDetail.shopName'/></p><p class="Store-information03-txt02">被评卖家: <s:property value='shopDetail.shopName'/>专营店</p></div>
</div>

<div class="Score-title">订单动态评分</div>
<div class="Score-title02">
	<div class="Score-title02Txt01">宝贝</div>
    <div class="Score-title02Txt02">宝贝与描述是否相符(匿名评价)</div>
</div>

<div class="Score-title03">

  	<s:iterator value="userComment" var="u">
  		<form action="addUserComment.do" method="post" name="myForm" id="myForm" >
			<input type="hidden" name="productCommentPojo.orderId" value="<s:property value='orderPojo.id'/>" id="productCommentPojo.orderId">
			<input type="hidden" name="productCommentPojo.orderNo" value="<s:property value='shopDetail.orderNo'/>" id="productCommentPojo.orderNo">
			<input type="hidden" name="productCommentPojo.productId" value="<s:property value='productId'/>" id="productCommentPojo.productId">
				<ul>
					<li>
						<div class="Score-title03-L">
					    	<div class="Score-title03-L-Pic"><a href='goProductDetail.do?productPojo.id=<s:property value='productId'/>'><img src="/upfiles/product/<s:property value='productImages'/>" alt="" width="157" height="159" /></a></div>
					        <div class="Score-title03-L-Txt"><a href='goProductDetail.do?productPojo.id=<s:property value='productId'/>'><s:property value='productName'/>
					        <!--<p class="Score-title03-L-Txt02">颜色分类：<s:property value='color'/><br /><s:property value='size'/></p>-->
					        </a></div>
					    </div>
					    <s:if test="reStatus==0">
					        <div class="Score-title03-R">
					     
							<textarea name="productCommentPojo.comment" cols="3" rows="1" class="Score-title03-R-form"></textarea>
							<div class="Score-title03-R-button"><button type="submit" class="addComment-button">提交评论</button></div>
		   					
		   				</div>
					     </s:if>
		   					<s:else>
					   			<div class="Score-title03-R">
									
		   						</div>
		   						<div class="Score-title03-R">
									
		   						</div>
		   						<div class="Score-title03-R">
									
		   						</div>
		   						<div class="Score-title03-R"> 注：该商品已申请退款/退货，不予评价！</div>
		   					</s:else>					 
		   				
				    </li>
				  
		   					<br/><br/>
				</ul>
		</form>	    
   </s:iterator>
  
</div>

<script type="text/javascript">
$(document).ready(function() {
	/**
	$("#submitId").click(function(){
			document.getElementById("myForm").submit();
	});
	*/
});
</script>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
