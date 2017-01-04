<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<link href="/css/lrtk.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/lrtk.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<!-- 代码 开始 -->
<s:if test="#session.wuser != null">
<div class="top">
	<div class="top-width">
    	<div class="welcome"><!--<img src="images/zm_pic.png" alt="" width="21" height="21"/>&nbsp;&nbsp;-->您好，欢迎&nbsp;${session.wuser.name}&nbsp;来到<a href="goIndexWeb.do" >竹马分销平台</a><a href="doLoginOutWeb.do" class="out">退出</a></div>
        <div class="login">
        	<span class="login-b">400热线：400-150-3677</span>
        	<span class="login-line">|</span>
        	<a href="goIndexWeb.do" class="login-a">首页</a><span class="login-line">|</span>
        <s:if test="#session.wuser.type ==2">
        	<a href="gomySupplier.do" class="login-a">我的</a><span class="login-line">|</span>
        </s:if>
        <s:elseif test="#session.wuser.type ==1">
        <a href="userCollectWeb.do" class="login-a">收藏夹</a><span class="login-line">|</span>
        </s:elseif>
        <s:else>
        	<a href="myConsumerWeb.do" class="login-a">我的</a><span class="login-line">|</span>
        	<a href="cartWeb.do" class="login-a">购物车</a><span class="login-line">|</span>
        	<a href="userCollectWeb.do" class="login-a">收藏夹</a><span class="login-line">|</span>
        </s:else>
        <a href="goHelpWeb.do" class="login-a">帮助中心</a>
        <span class="login-line">|</span>
        <a href="goContact.do" class="login-a">联系客服</a>
        <%-- <span class="login-line">|</span>
        <a href="#" class="login-a">网站导航</a> --%>
        </div>
    </div>
</div>
</s:if>
<s:else>
<div class="top">
	<div class="top-width">
    	<div class="welcome"><!--<img src="images/zm_pic.png" alt="" width="21" height="21"/>&nbsp;&nbsp;-->您好，欢迎来到<a href="goIndexWeb.do" >竹马分销平台</a></div>
        <div class="login"><span class="login-b">400热线：400-150-3677</span><span class="login-line">|</span><a href="goIndexWeb.do" class="login-a"> 首页</a><a href="doLoginWeb.do" class="login-a"> 立即登录</a><a href="goRegister.do" class="login-a">马上注册</a><a href="goContact.do" class="login-a">联系客服</a></div>
    </div>
</div>
</s:else>
