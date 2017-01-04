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
<link href="/css/help.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery.cycle.all.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script>
function search(){
		document.getElementById("idform").submit();
	}
</script>
<title>淘竹马帮助中心</title>
<meta name="keywords" content="婴童 淘竹马 分销平台 早教玩具 电动玩具 母婴用品厂家直销 M2C平台模式 音乐玩具 遥控玩具 学步车 桌球玩具 悠悠球" />
<meta name="description" content="淘竹马作为全球专业M2C玩具分销平台，主要提供各类玩具产品的一站式分销服务，即玩具产品从生产商直接到分销商的平台模式。淘竹马以厂家直销、主营玩具、一只发货为特色，专注于为玩具厂商和分销商服务，致力整合玩具渠道，打造专属玩具供应商和采购商的一站式玩具分销平台。产品质量都是通过人工把关质检，每件产品都具有3C认证，安全，可靠。同时，淘竹马承诺买家完成付款，淘竹马会在48小时内完成发货。" />
</head>

<body class="body">
<jsp:include page="header.jsp"></jsp:include>

<div class="top02-width">
	<!--<div class="logo02"></div>-->
	<a href="goIndexWeb.do" class="logo"></a>
	<form action="goSearch.do" id="idform" method="post">
	<div class="search"><input id="searchkey" name="searchkey" type="text" value="请简单描述你的问题" onfocus="this.value=''" onblur="if(this.value==''){this.value='请简单描述你的问题'}" class="search-input"/><button type="submit" class="search-button" onclick="search()">搜 索</button></div>
	</form>
</div> 

<div class="clear"></div>

<div class="help-nav-width">
	<div class="help-nav">
    	<ul>
        	<li><a href="goHelpWeb.do"><span>首 页</a></span></li>
            <li><a href="goSelfServiceWeb.do">自主服务</a></li>
            <li><a href="goFaqWeb.do">常见问题</a></li>
            <li><a href="goContact.do">联系客服 </a></li>
            <li><a href="goFeedBackWeb.do">意见反馈</a></li>
        </ul>
    </div>
</div>

<div class="bigeyes">
	<div id="banner">
	<s:iterator value="pagePushPojos">
        <div class="topBanner" style="background:url(/upfiles/notice/<s:property value='images'/>) no-repeat top center;"></div>
    </s:iterator>    
    </div>
    <div id="btn"></div>
</div>

<div class="help-service-width">
	<div class="help-service01">
    	<img src="images/help_05.jpg" alt="" width="78" height="78" />
        <div class="help-service01-title">帐户密码服务</div>
        <div class="help-service01-txt">
        	<ul>
            	<li><a href="helpDetail.do?helpPojo.id=41">找回密码</a></li>
                <li><a href="helpDetail.do?helpPojo.id=42">找回登录名</a></li>
                <li><a href="helpDetail.do?helpPojo.id=43">修改密码</a></li>
                <li><a href="helpDetail.do?helpPojo.id=150">免费注册账户</a></li>
                <li><a href="helpDetail.do?helpPojo.id=45">注销普通会员账户</a></li>
            </ul>
        </div>
        <div class="help-service01-more"><a href="goFaqWeb.do">更多 >></a></div>
    </div>
    
    <div class="help-service01">
    	<img src="images/help_07.jpg" alt="" width="78" height="78" />
        <div class="help-service01-title">采购商服务</div>
        <div class="help-service01-txt">
        	<ul>
            	<li><a href="helpDetail.do?helpPojo.id=62">服务项目</a></li>
                <li><a href="helpDetail.do?helpPojo.id=63">数据包生成</a></li>
                <li><a href="helpDetail.do?helpPojo.id=64">如何在线交易</a></li>
                <li><a href="helpDetail.do?helpPojo.id=127">订单查询</a></li>
                <li><a href="helpDetail.do?helpPojo.id=66">如何安全交易</a></li>
            </ul>
        </div>
        <div class="help-service01-more"><a href="goFaqWeb.do">更多 >></a></div>
    </div>
    
    <div class="help-service01">
    	<img src="images/help_09.jpg" alt="" width="78" height="78" />
        <div class="help-service01-title">供应商服务</div>
        <div class="help-service01-txt">
        	<ul>
            	<li><a href="helpDetail.do?helpPojo.id=129">供应商入驻</a></li>
                <li><a href="helpDetail.do?helpPojo.id=130">供应商认证</a></li>
                <li><a href="helpDetail.do?helpPojo.id=240">经营模式</a></li>
                <li><a href="helpDetail.do?helpPojo.id=241">商品退换</a></li>
                <li><a href="helpDetail.do?helpPojo.id=132">订单结算</a></li>
            </ul>
        </div>
        <div class="help-service01-more"><a href="goFaqWeb.do">更多 >></a></div>
    </div>
    <div class="clear"></div>
</div>

<div class="help-Novice">
	<div class="help-Novice-title"><img src="images/help_15.jpg" alt="" width="34" height="34" /> 新手上路</div>
    <div class="help-Novice-txt">
        <div class="help-Novice-txt-L">分销商</div>
        <div class="help-Novice-txt-R"><a href="helpDetail.do?helpPojo.id=231">分销商招募</a><span style="color:#dcdcdc;">|</span><a href="helpDetail.do?helpPojo.id=29">支付方式</a><span style="color:#dcdcdc;">|</span><a href="helpDetail.do?helpPojo.id=223">搜索供应信息</a></div>
    </div>
    <div class="clear"></div>
    <div class="help-Novice-txt">
        <div class="help-Novice-txt-L02">供应商</div>
        <div class="help-Novice-txt-R"><a href="helpDetail.do?helpPojo.id=49">发布公司介绍</a><span style="color:#dcdcdc;">|</span><a href="helpDetail.do?helpPojo.id=50">商家入驻</a><span style="color:#dcdcdc;">|</span><a href="helpDetail.do?helpPojo.id=51">发布供应信息</a></div>
    </div>
    <div class="clear"></div>
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
