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
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马分销商</title>
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

<script>
$(document).ready(function(){
	$('.favorite_01-list li').hover(function(){
		$(this).find('.iconx').show();
	},function(){
		$(this).find('.iconx').hide();
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
                <li><a href="systemInfoConWeb.do"  id="myMessage" >消息</object></a></li>	
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
                <!--  <li><a href="#">退款维权</a></li>   -->
            </ul>
        </div>
    </div>
    
    <div class="buy-R">
  
		<!--  <div class="favorite_01-title"><div class="favorite_01-title-line">我的评价</div></div>   -->
	    <div class="buy-R-Part03">
	    	 <table border="0" cellpadding="0" cellspacing="0" width="100%" class="my_supplier_Product-R-list-table">
		            	<tr>
		            		<td class="my_supplier_Product-R-list-tableTit">用户昵称</td>
		                    <td class="my_supplier_Product-R-list-tableTit">订单号</td>
		                    <td class="my_supplier_Product-R-list-tableTit">产品名称</td>
		                    <td class="my_supplier_Product-R-list-tableTit">评价内容</td>
		                    <td class="my_supplier_Product-R-list-tableTit">评价时间</td>
		                </tr>
		                <s:iterator value="productCommentListWeb">
		    			<tr>
		    				<td>
				                <span class=""><s:property value='userName'/></span>
			    			</td>
		    				<td>
				                <span class=""><s:property value='orderNo'/></span>
			    			</td>
		    				<td>
			                   <span class=""><s:property value='productName'/></span>
		    				</td>
		    				<td>
			                   <span class=""><s:property value='comment'/></span>
		    				</td>
		    				<td>
				                <span class=""><s:property value='createDateStr'/></span>
		    				</td>
			            </tr>
						</s:iterator>
		      </table>
	    </div>
    <br/><br/><br/>
    <div class="clear"></div>
    
  </div>
</div>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

