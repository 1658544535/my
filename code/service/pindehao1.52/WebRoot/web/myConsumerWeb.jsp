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
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马分销平台</title>
<script>
$(function() {
	var time = null;
	$('#zhaqsz').hover(function(){
		$('.drop').show();
	},function(){
		time = setTimeout(function(){
			$('.drop').hide();
		},50);
	});
	$(".drop").hover(function(){
		clearTimeout(time);
		$(this).show();
	},function(){
		time = setTimeout(function(){
			$('.drop').hide();
		},50);
	});
});
</script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="clear"></div>

<div class="help-nav-width">
	<div style="width:1190px; margin:0 auto;">
        <div class="favorite_01-logo">淘竹马分销</div>
        <div class="buy-nav">
            <ul>
                <li><a href="myConsumerWeb.do"><span>采购商主页</span></a></li>
                <li><a href="systemInfoConWeb.do"  id="myMessage" >消息</a></li>
                <li><a href="#" id="zhaqsz">帐户设置 <img src="images/buy_03.png" alt="" width="10" height="10" /></a></li>
                <!--	<li><a href="#">个人主页</a></li>	-->
            </ul>
        </div>
        
        <div class="drop">
        	<div class="drop-arrow"></div>
            <div class="drop-Bj">
            	<div class="drop-title">安全设置</div>
                <div class="drop-list">
                	<dl>
                    	<dt><a href="goRetrieve.do">修改登录密码</a></dt>
                        <!--<dt><a href="goSetAccountSecurityQuestion.do">密保问题设置</a></dt>-->
                        <!--  <dt><a href="#">其他</a></dt>  -->
                    </dl>
                </div>
            </div>
            
            <div class="drop-Bj02">
            	<div class="drop-title02">个人资料</div>
                <div class="drop-list">
                	<dl>
                    	<dt><a href="deliveryAddressListWeb.do">收货地址</a></dt>
                        <!--  <dt><a href="goUpdateConsumerWeb.do">修改个人信息</a></dt>-->
                        <dt><a href="goConsumerWeb.do">查看个人信息</a></dt>
                        <!--  <dt><a href="#">商铺认证</a></dt>	-->
                    </dl>
                </div>
            </div>
            
            <!-- 
            <div class="drop-Bj03">
            	<div class="drop-title03">账号绑定</div>
                <div class="drop-list">
                	<dl>
                    	<dt><a href="#">支付宝绑定</a></dt>
                        <dt><a href="#">微博绑定</a></dt>
                    </dl>
                </div>
            </div>
            -->
            
        </div>
    </div>
</div>

<div class="faq-width">
	<div class="buy-L">
    	<div class="buy-L-title"><img src="images/faq_03.jpg" alt="" width="16" height="16" />&nbsp;&nbsp;&nbsp;&nbsp;全部功能</div>
        <div class="buy-L-list">
        	<ul>
            	<li><a href="cartWeb.do">我的购物车</a></li>
                <li><a href="orderWeb.do">已买到的宝贝</a></li>
                <li><a href="deliveryAddressListWeb.do">我的收货地址</a></li>
                <li><a href="userCollectWeb.do">我的收藏</a></li>
                <!--  <li><a href="#">API申请</a></li>  -->
                <li><a href="userCommentAllListWeb.do">我的评价</a></li>
                <!--  <li><a href="#">退款维权</a></li>  -->
            </ul>
        </div>
    </div>
    
    <div class="buy-R">
    	<div class="my_buyer-Part01">
            
            <div class="my_buyer-Part01-R">
            	<ul>
            		<!--  <li><a href="#">我的钱包</a></li>	-->
            		<li><a href="orderWeb.do">已买到的宝贝</a></li>
                	<li><a href="deliveryAddressListWeb.do">我的收货地址</a></li>
                    <li><a href="goConsumerProductWeb.do">我的分销商品库</a></li>
                </ul>
            </div>
        </div>
        
        <div class="my_buyer-Part02">
        	<ul>
                <li><a href="orderWeb.do?order.orderStatus=1">待付款<font color="#df434e">${orderPojo.dfkNum}</font></a></li>
                <li><a href="orderWeb.do?order.orderStatus=2">待发货<font color="#df434e">${orderPojo.dfhNum}</font></a></li>
                <li><a href="orderWeb.do?order.orderStatus=3">待收货 <font color="#df434e">${orderPojo.dshNum}</font></a></li>
                <li><a href="orderWeb.do?order.orderStatus=4">待评价 <font color="#df434e">${orderPojo.dplNum}</font></a></li>
               
            </ul>
        </div>
        
        <div class="my_buyer-Part03">
        	<div class="my_buyer-Part03-title"><img src="images/my_buyer_14.jpg" alt="" width="26" height="26" /> 我的物流</div>
            <div class="my_buyer-Part03-list">
            	<ul>
	            	<s:iterator value="myWuLiuOrderListWeb">
	                	<li>
	                    	<div class="my_buyer-Part03-list-Pic"><a href=goProductDetail.do?productPojo.id=${productId}> <img src="/upfiles/product/<s:property value='productImages'/>" alt="" width="92" height="84" /></a></div>
	                    	
	                    	<s:if test="orderStatus==4">
	                        <div class="my_buyer-Part03-list-txt"><s:property value='orderStatusName'/><br /><s:property value='createDateString'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="goMyOrderDetailWeb.do?orderPojo.id=<s:property value='id'/>">查看物流明细</a></div>
	                        <div class="my_buyer-Part03-list-button"><a href="goUserCommentWeb.do?orderPojo.id=<s:property value='id'/> " class">评论</a></div>
	                        </s:if>
	                        <s:elseif test="orderStatus==1">
	                        <div class="my_buyer-Part03-list-txt"><s:property value='orderStatusName'/><br /><s:property value='createDateString'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="goMyOrderDetailWeb.do?orderPojo.id=<s:property value='id'/>"></a></div>
	                        <div class="my_buyer-Part03-list-button"><a href="goOrderDetailPay.do?orderPojo.id=<s:property value='id'/> ">立即付款</a></div>
	                        </s:elseif>
	                        <s:elseif test="orderStatus==5">
	                        <div class="my_buyer-Part03-list-txt"><s:property value='orderStatusName'/><br /><s:property value='createDateString'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="goMyOrderDetailWeb.do?orderPojo.id=<s:property value='id'/>">查看物流明细</a></div>
	                        <div class="my_buyer-Part03-list-button"><span>已评论</span></div>
	                        </s:elseif>
	                        <s:elseif test="orderStatus==3">
	                        <div class="my_buyer-Part03-list-txt"><s:property value='orderStatusName'/><br /><s:property value='createDateString'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="goMyOrderDetailWeb.do?orderPojo.id=<s:property value='id'/>">查看物流明细</a></div>
	                        <div class="my_buyer-Part03-list-button"><a href="updateOrderStatus.do?orderPojo.orderStatus=3&orderPojo.id=<s:property value='id'/>">确认收货</a></div>
	                        </s:elseif>
	                        <s:elseif test="orderStatus==2">
	                        <div class="my_buyer-Part03-list-txt"><s:property value='orderStatusName'/><br /><s:property value='createDateString'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="goMyOrderDetailWeb.do?orderPojo.id=<s:property value='id'/>"></a></div>
	                        <div class="my_buyer-Part03-list-button"><span>等待发货</span></div>
	                        </s:elseif>
	                        
	                    </li>
	                </s:iterator>
                </ul>
            </div>
        </div>
        
        <div class="my_buyer-Part04">
        	<div class="my_buyer-Part03-title"><img src="images/my_buyer_21.jpg" alt="" width="26" height="26" /> 推荐产品</div>
            <div class="my_buyer-Part04-list">
            	<ul>
            	
            		<s:iterator value="productAnList">
		    			<li><a href='goProductDetail.do?productPojo.id=<s:property value='id'/>'><img src="/upfiles/product/<s:property value='image'/>" alt="" width="218" height="218" />
	                    	<div class="my_buyer-Part04-list-txt01"><span class="my_buyer-Part04-list-txt01-L">¥<fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></span><span class="my_buyer-Part04-list-txt01-R">¥<s:property value='sellingPrice'/></span><div class="my_buyer-Part04-list-txt01-R02"><s:property value='shopName'/><s:if test='shopName==null'>官方热卖</s:if></div></div>
	                        <div class="my_buyer-Part04-list-txt02"><s:property value='productName'/></div>
	                        <div class="my_buyer-Part04-list-txt03">
	                        	<div class="my_buyer-Part04-list-txt03-L"><!--好评：97.18%--></div>
	                            <div class="my_buyer-Part04-list-txt03-R">月销：<s:property value='sellNumber'/></div>
	                        </div></a>
	                    </li>
					</s:iterator>
                   
                </ul>
                <div class="clear"></div>
            </div>
        </div>
        
        <div class="my_buyer-Part04">
        	<div class="my_buyer-Part03-title"><img src="images/my_buyer_28.jpg" alt="" width="26" height="26" /> 我的足迹</div>
            <div class="my_buyer-Part04-list">
            	<ul>
                	<s:iterator value="productHaList">
                	<s:if test="proStatus==1">
	    			<li><a href="goProductDetail.do?productPojo.id=<s:property value='businessId'/>"><img src="/upfiles/product/<s:property value='image'/>" alt="" width="218" height="218" />
                    	<div class="my_buyer-Part04-list-txt01"><span class="my_buyer-Part04-list-txt01-L">¥<fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></span><span class="my_buyer-Part04-list-txt01-R">¥<s:property value='sellingPrice'/></span><div width="400" class="my_buyer-Part04-list-txt01-R02"><s:property value='shopName'/><s:if test='shopName==null'>官方</s:if></div></div>
                        <div class="my_buyer-Part04-list-txt02"><s:property value='productName'/></div>
                        <div class="my_buyer-Part04-list-txt03">
                        	<div class="my_buyer-Part04-list-txt03-L"><!--好评：97.18%--></div>
                            <div class="my_buyer-Part04-list-txt03-R">月销：<s:property value='sellNumber'/></div>
                        </div></a>
                    </li>
                  </s:if>
    	          <s:else>
                  <li style="background:#e3e3e3;opacity:0.34;"><a href="goProductDetail.do?productPojo.id=<s:property value='businessId'/>"><img src="/upfiles/product/<s:property value='image'/>" alt="" width="218" height="218" />
                
              	<div class="my_buyer-Part04-list-txt01"><span class="my_buyer-Part04-list-txt01-L">¥<fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></span><span class="my_buyer-Part04-list-txt01-R">¥<s:property value='sellingPrice'/></span><div width="400" class="my_buyer-Part04-list-txt01-R02"><s:property value='shopName'/><s:if test='shopName==null'>官方</s:if></div></div>
                  <div class="my_buyer-Part04-list-txt02"><s:property value='productName'/></div>
                  <div class="my_buyer-Part04-list-txt03">
                  	<div class="my_buyer-Part04-list-txt03-L"><!--好评：97.18%--></div>
                      <div class="my_buyer-Part04-list-txt03-R">月销：<s:property value='sellNumber'/></div>
                      <div class="my_buyer-Part04-list-txt01-L" style="text-align:left;">该产品已下架 </div>
                  </div></a>
                </li>
	            </s:else>

					</s:iterator>
                </ul>
                <div class="clear"></div>
            </div>
        </div>
        
    </div>
</div>


<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
