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
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马分销平台</title>
<link href="css/down.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
<div class="top02-width">
	<div class="logo"></div>
	<form action="searchWeb.do" method="post" id="sysform">
	<div class="search">
	<input name="productPojo.productName" type="text" value="搜索 商品/店铺" onfocus="this.value=''" onblur="if(this.value==''){this.value='搜索 商品/店铺'}" class="search-input"/>
	<button type="submit" class="search-button" onclick="searchBtn()">搜 索</button></div>
	</form> 
	<div class="clear"></div> 
	<div class="help-nav-width">
	<div class="help-nav-fu">
    	<ul>
        	<li><a href="goIndexWeb.do">首 页</a></li>
            <li><a href="goNewWeb.do">新品快递</a></li>
            <li><a href="goshopWeb.do">店铺街</a></li>
            <li><a href="infoPageList.do">行业资讯</a></li>
        </ul>
    </div>
</div>
	
<div class="down_box">
<h2>翻动城</h2>
<h4 class="small_title">用互联网思维做跨界的玩具万店连锁O2O</h4>

<img src="images/fdc-01.jpg" />
<p>翻动城采用的模式为电商平台(手机端/平台端)+连锁体验终端店+儿童游乐中心+物流配送+售后维修，通过整合全国各地批发通路，帮助经销商渠道下沉直达终端店，为消费者提供更灵活、更便捷、更智能化的线下社区购物体验、儿童游乐中心体验。翻动城首创全国玩具线上线下购物、快递送货、零库存、玩具售后维修等多项业务。 </p>
<img src="images/fdc-02.jpg" />
<p>翻动城用户可通过手机端购物，也可以到最近的连锁店，通过店内的展示样品，海报、二维码墙放置虚拟的商品，可以通过手机扫码、店内下单购买、网上支付、系统选择最近经销商发货等形式，店内不设库存，提供购买商品或指定品牌商家售后维修。 </p>

<p>无论是客户网络、服务理念，还是高科技技术的运用上，翻动城更胜一筹。有了实体店之后，翻动城与消费者的接触点会越来越多，其关系也会更加紧密。</p>
<img src="images/fdc-03.jpg" />
<img src="images/fdc-04.jpg" />
 </div>
		

</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
