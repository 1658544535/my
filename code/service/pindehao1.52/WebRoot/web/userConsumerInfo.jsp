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
                <!--  <li><a href="#">退款维权</a></li>  -->
            </ul>
        </div>
    </div>
    
    <div class="my_supplier_Product-R">
    	<div class="user-Consumer-info-top-text"><span class="user-Consumer-info-left" style="float: left;">采购商个人信息</span><span class="user-Consumer-info-right" style="float: right;" ><a href="goUpdateConsumerWeb.do">修改采购商个人信息</a></span></div>
    	<div class="accountSecurityQuestion-fm">
    	<s:if test=" goUpdateConsumer == null ">
    		<div class="user-Consumer-info-top-text-none">
    			<div  class="user-Consumer-info-text-none"><span>您还没有设置个人信息!</span></div>
    	    </div>
	    </s:if>
	    <s:if test=" goUpdateConsumer != null ">
	    	<table border="0" cellpadding="0" cellspacing="0" class="apply-form-table">
			    <tr>
		        	<td class="register-form-tableTxt">公司名称</td>
		            <td><input name="goUpdateConsumer.company" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.company'/>" readonly="readonly" style="float: left;" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">主营类目 </td>
		            <td>
			   		            <s:if test=" 1 == goUpdateConsumer.mainCategory ">
			            	<input name="goUpdateConsumer.platform" type="text" class="register-form-tableInput" value="遥控/电动玩具" readonly="readonly" style="float: left;" />
			            </s:if> 
			             <s:if test=" 2 == goUpdateConsumer.mainCategory ">
			            	<input name="goUpdateConsumer.platform" type="text" class="register-form-tableInput" value="早教/音乐玩具" readonly="readonly" style="float: left;" />
			            </s:if> 
			             <s:if test=" 3 == goUpdateConsumer.mainCategory ">
			            	<input name="goUpdateConsumer.platform" type="text" class="register-form-tableInput" value="过家家玩具" readonly="readonly" style="float: left;" />
			            </s:if> 
			             <s:if test=" 4 == goUpdateConsumer.mainCategory ">
			            	<input name="goUpdateConsumer.platform" type="text" class="register-form-tableInput" value="童车玩具" readonly="readonly" style="float: left;" />
			            </s:if> 
			            <s:if test=" 5 == goUpdateConsumer.mainCategory ">
			            	<input name="goUpdateConsumer.platform" type="text" class="register-form-tableInput" value="益智玩具" readonly="readonly" style="float: left;" />
			            </s:if> 
			             <s:if test=" 6 == goUpdateConsumer.mainCategory ">
			            	<input name="goUpdateConsumer.platform" type="text" class="register-form-tableInput" value="其他玩具" readonly="readonly" style="float: left;" />
			            </s:if> 
			   
		            </td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">公司总人数 </td>
		            <td>
		            	<s:if test=" 1 == goUpdateConsumer.groups ">
		            		<input name="goUpdateConsumer.platform" type="text" class="register-form-tableInput" value="1~5人" readonly="readonly" style="float: left;" />
		            	</s:if>
		            	<s:if test=" 2 == goUpdateConsumer.groups ">
		            		<input name="goUpdateConsumer.platform" type="text" class="register-form-tableInput" value="6~20人" readonly="readonly" style="float: left;" />
		            	</s:if>
		            	<s:if test=" 3 == goUpdateConsumer.groups ">
		            		<input name="goUpdateConsumer.platform" type="text" class="register-form-tableInput" value="21~50人" readonly="readonly" style="float: left;" />
		            	</s:if>
		            	<s:if test=" 4 == goUpdateConsumer.groups ">
		            		<input name="goUpdateConsumer.platform" type="text" class="register-form-tableInput" value="51~100人" readonly="readonly" style="float: left;" />
		            	</s:if>
		            	<s:if test=" 5 == goUpdateConsumer.groups ">
		            		<input name="goUpdateConsumer.platform" type="text" class="register-form-tableInput" value="100人以上" readonly="readonly" style="float: left;" />
		            	</s:if>
		            </td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">销售平台</td>
		            <td><input name="goUpdateConsumer.platform" type="text" class="register-form-tableInput"  value="<s:property value='goUpdateConsumer.platform'/>" readonly="readonly" style="float: left;" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">销售地区</td>
		            <td><input name="goUpdateConsumer.salesArea" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.salesArea'/>" readonly="readonly" style="float: left;" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">联系人</td>
		            <td><input name="goUpdateConsumer.contact" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.contact'/>" readonly="readonly" style="float: left;" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">联系人职务</td>
		            <td><input name="goUpdateConsumer.duty" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.duty'/>" readonly="readonly" style="float: left;" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">联系人Email</td>
		            <td><input name="goUpdateConsumer.email" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.email'/>" readonly="readonly" style="float: left;" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">联系QQ</td>
		            <td><input name="goUpdateConsumer.QQ" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.QQ'/>" readonly="readonly" style="float: left;" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">联系电话 </td>
		            <td><input name="goUpdateConsumer.tel" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.tel'/>" readonly="readonly" style="float: left;" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">手机号码</td>
		            <td><input name="goUpdateConsumer.phone" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.phone'/>" readonly="readonly" style="float: left;" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">传真号码</td>
		            <td><input name="goUpdateConsumer.fax" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.fax'/>" readonly="readonly" style="float: left;" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">网店链接 </td>
		            <td><input name="goUpdateConsumer.webSite" type="text" class="register-form-tableInput" value="<s:property value='goUpdateConsumer.webSite'/>" readonly="readonly" style="float: left;" /></td>
		        </tr>
		        <tr>
		        	<td><div class="register-form-tableTxt">公司地址</div></td>
		        	<td>
		        	<div class="consumer-apply-textarea"><textarea name="goUpdateConsumer.address" cols="" rows="" class="consumer-apply-textarea" readonly="readonly" style="float: left;" ><s:property value='goUpdateConsumer.address'/></textarea></div>
		        	</td>
		        </tr>
	    	</table>
	    </s:if>	
    	</div>
    </div>
</div>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
