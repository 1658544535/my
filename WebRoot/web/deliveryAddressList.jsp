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
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/system/help/helpCommon.js"></script>
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
	
	function updateToDefault()
		{
			if(confirm("你确定要把此地址设置为默认地址么?"))
				{
					return true;
				}else{
					return false;
				}
		}
		
	function delAffirm()
		{
			if(confirm("你确定要删除该收货地址吗?"))
				{
					return true;
				}else{
					return false;
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
                <li><a href="systemInfoConWeb.do"   id="myMessage" >消息</a></li>	
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
                        <!-- <dt><a href="#">商铺认证</a></dt>	-->
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
                 <!-- <li><a href="#">退款维权</a></li>   -->
            </ul>
        </div>
    </div>
    
    <div class="buy-R">
  
	    <div class="user-Address-Part01">
            <div class="user-Address-Part01-R">
            	<ul>
                    <li><a href="goAddAddress.do"><font color="blue">添加收货地址</font></a></li>
                </ul>
            </div>
    	</div>
	    
	    <div class="buy-R-Part03">
	    	 <table border="0" cellpadding="0" cellspacing="0" width="100%" class="my_supplier_Product-R-list-table">
		            	<tr>
		                    <td class="my_supplier_Product-R-list-tableTit">收货人</td>
		                    <td class="my_supplier_Product-R-list-tableTit">收货人电话</td>
		                    <td class="my_supplier_Product-R-list-tableTit">省份</td>
		                    <td class="my_supplier_Product-R-list-tableTit">城市</td>
		                    <td class="my_supplier_Product-R-list-tableTit">区域</td>
		                    <td class="my_supplier_Product-R-list-tableTit">地址</td>
		                    <td class="my_supplier_Product-R-list-tableTit">邮编</td>
		                    <td class="my_supplier_Product-R-list-tableTit">是否为默认地址</td>
		                    <td class="my_supplier_Product-R-list-tableTit">操作</td>
		                </tr>
		                <s:iterator value="deliveryAddressListWeb">
		    			<tr>
		    			
		    				<td>
				                <span class=""><s:property value='consignee'/></span>
			    			</td>
			    			<td>
				                <span class=""><s:property value='consigneePhone'/></span>
			    			</td>
		    				<td>
			                   <span class=""><s:property value='provinceName'/></span>
		    				</td>
		    				<td>
			                   <span class=""><s:property value='cityName'/></span>
		    				</td>
		    				<td>
				                <span class=""><s:property value='areaName'/></span>
		    				</td>
			    			<td>
				                <span class=""><s:property value='address'/></span>
			    			</td>
			    			<td>
				                <span class=""><s:property value='postcode'/></span>
			    			</td>
			    			<td>
			    				<s:if test=" isDefault == 0 ">
				                	<span class="">否</span>
				                </s:if>
				                <s:if test=" isDefault == 1 ">
				                	<span class=""><font color="red">是</font></span>
				                </s:if>
				                <!--  <span class=""><s:property value='statusName'/></span>	-->
			    			</td>
			    			<td>
			    			 	<s:if test=" isDefault == 0 ">
				                	<span class="DeliveryAddress-table-operation-R-Txt"><a class='' onclick="return updateToDefault()" href="updateAddressToDefault.do?deliveryAddressPojo.id=<s:property value='id'/>" >设置为默认地址</a></span>
				                </s:if>
				                <s:if test=" isDefault == 1 ">
				                	<span class=""><font color="red">已经是默认地址</font></span>&nbsp;&nbsp;|&nbsp;&nbsp;
				                	<span class="DeliveryAddress-table-operation-L-Txt"><a  href="cancelAddressToDefault.do?deliveryAddressPojo.id=<s:property value='id'/>" onclick="javascript:return window.confirm('确定修改？');">取消</a></span>
				                </s:if>
				                &nbsp;&nbsp;|&nbsp;&nbsp;
				                <span class="DeliveryAddress-table-operation-L-Txt"><a class='' href="goUpdateDeliveryAddressWeb.do?deliveryAddressPojo.id=<s:property value='id'/>">修改</a></span>
				                &nbsp;&nbsp;|&nbsp;&nbsp;
				                <span class="DeliveryAddress-table-operation-L-Txt"><a class='' onclick="return delAffirm()" href="delAddress.do?deliveryAddressPojo.id=<s:property value='id'/>">删除</a></span>
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

