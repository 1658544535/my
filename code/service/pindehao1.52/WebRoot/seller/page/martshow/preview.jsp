<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/default.css" media="all" />
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/seller_common.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/martshow_preview.css" type="text/css" media="all" />
    </head>
	<body>
	<s:if test="#session.wuser != null">
<div id="head">
	<div class="wrapper">
		<div class="main-logo ">
			<a class="main-logo-a" href="goSellerIndex.do" title="返回首页">
				&nbsp;
			</a>
			<span class="sec-title">
				<i class="b-inco">
				</i>
				<a href="goSellerIndex.do">
					商家中心
				</a>
			</span>
			<div id="st-nav">
				<span id="login-status" class="op" data-status="1">
					<a class="st-icons account" href="goSellerIndex.do">
						${session.wuser.name}
					</a>
					<a href="doSellerLogout.do" rel="nofollow">
						退出
					</a>
					|
					<a href="goMessagesCenterWeb.do">
						消息
					</a>
				</span>
				<a class="bold" href="goHelpWeb.do">
					帮助中心
				</a>
			</div>
		</div>
	</div>
			<div id="main-nav">
		        <div class="wrapper">
		            <a href="#portal" class="J_navItem nav-item current">专场入口预览</a>
		            <a href="#detail" class="J_navItem nav-item">专场列表预览</a>
		        </div>
		    </div>
</div>		    
</s:if>
<s:else>
<div id="head">
</div>
</s:else>
			<div id="container">
			<div id="content" class="wrapper">
				<style>
					#container{background:#fff;}
					#content{box-shadow:none;}
					a.J_eventListItem:hover,.view-ItemListItem a:hover{text-decoration:none;}
				</style>
			    <div id="portal" class="J_partHidden clearfix" style="display: block;">
			        <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/images/pc_s1.jpg" alt="" style="margin-top:15px;">
			        <a class="m-sale clearfix J_eventListItem">
			            <div class="m-sbanner-s">
			                <img class="sbanner" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/specialShow/${specialShowPojo.banner}" style="width: 500px; display: inline;" alt=" ${specialShowPojo.name}">
			            </div>
			            <div class="m-detail-cont">
			                <div class="countdown-cont">
			                    <span style="float:left;"><i class="iconfont">&#xf006a;</i>活动时间：</span>
			                    <%-- <span class="timer">剩余：<span><em>x</em>天</span><span><em>xx</em>时</span><span><em>xx</em>分</span><span><em>xx</em>秒</span></span> --%>
			                    <c:if test="${specialShowPojo.beginTimeStr!=null && specialShowPojo.beginTimeStr!=''}"><span class="timer" style="display:block;padding-top:10px;text-align:left;line-height:23px;">${specialShowPojo.beginTimeStr}至<br/>${specialShowPojo.endTimeStr}</span></c:if>
			                </div>
			                <div class="m-detail-s">
			                    <h3>
				                   <%--  <em class="mart-price"><span class="f-price-font">2.9</span>折起</em> --%>
				                    <span class="mart-name f-yh-font">${specialShowPojo.title}</span>
				                </h3>
			                    <div class="description">${specialShowPojo.introduction}</div>
			                </div>
			                <span class="u-people">
							    <span>${specialShowPojo.discountContext}</span>
			                </span>
			                <span class="u-enter-btn">进入专场</span>
			            </div>
			        </a>
			        <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/images/pc_s2.jpg" alt="">
			    </div>			    
			    <div id="detail" class="J_partHidden" style="display:none;">
			        <div class="g-mart-header">
			            <div class="g-side-left m-sbanner">
			                <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/specialShow/${specialShowPojo.banner}"  alt=" ${specialShowPojo.name}">
			            </div>
			            <div class="g-side-right">
			                <div class="m-info">
 			                    <div class="logo">
			                        <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/businessCenter/brandDic/${userBrandPojo.logo}" alt="${userBrandPojo.brandName}">
			                    </div>
			                    <div class="countdown-container">
			                        <i class="iconfont">&#xf006a;</i>
			                        <span>专场结束倒计时</span>
			                    </div>
			                </div>
			                <div class="m-detail">
			                    <h3>
							                    <span class="mart-name">${specialShowPojo.title}</span>
<%-- 							            <em class="mart-discount">
							                        <span>2.9</span>折起
							                    </em> --%>
							                </h3> 
			                    <div class="promotion">
			                        <span>${specialShowPojo.discountContext}</span>
			                    </div>
			                    <div class="description J_description">${specialShowPojo.introduction}</div>
			                </div>
			            </div>
			        </div>
			        <ul class="m-items z-ancient clearfix view-ItemList J_dragUl">
			        <s:iterator value="specialProductList" id="id">
			            <li class="view-ItemListItem">
			                <a target="_blank" href="#">
			                    <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>"  title="<s:property value='productName'/>">
			                    <div class="title" title="<s:property value='productName'/>">
			                        <s:property value='productName'/></div>
			                    <span class="price-info ">
					                <span class="discount"><s:property value='tips'/></span>
				                    <span class="symbol">¥</span>
				                    <span class="price price-int"><s:property value='specialPrice'/></span>
				                    <span class="origin-price strike">¥<s:property value='sellPrice'/></span>
				                </span>
			                </a>
			            </li>
			          </s:iterator>
			        </ul>
			    </div>
			</div>

		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
		<script src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
        <script>
			$(".nav-item").on("click",function(){
				$(this).addClass("current").siblings().removeClass("current");
				var id = $(this).attr("href");
				$(id).show().siblings().hide();
				return false;
			})

			$("#portal .J_eventListItem").on("click",function(){
				$(".nav-item:eq(1)").trigger("click");
			})
		</script>
	