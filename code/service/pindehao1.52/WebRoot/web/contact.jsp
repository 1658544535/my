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
<link href="/css/faq.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/_head.js"></script>
<script>
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>
<style>
*{ padding:0; margin:0;}
#outer {width: 920px;margin: 20px auto;}
#tab {overflow:hidden;zoom:1;background:#ececec;border: #d9d9d9 1px solid; border-bottom:none;}
#tab li {float:left;color:#4c4c4c;height:105px;	cursor:pointer;	line-height:50px;width:50%;}
#tab li.current {color:#4c4c4c;background:#fff; /*height:50px;*/ width:50%;}
#content { width: 918px;border: #d9d9d9 1px solid;border-top: none;padding: 30px 0; overflow:hidden;}
#content ul {line-height:25px;display:none;	margin:0 30px;padding:10px 0;}
#content li{ width:420px;  float:left;text-align: left;line-height: 30px; font-size:12px;color:#666;}
#content li a{ text-decoration:none; color:#666;}
</style>
<title>淘竹马联系客服</title>
<meta name="keywords" content="M2C 淘竹马 玩具电商  玩具分销 玩具厂家直销 玩具 玩具品牌联盟 群宇 玩具48小时发货 婴童玩具3C认证" />
<meta name="description" content="淘竹马平台为您提供一站式电商服务。提供在线咨询、客服热线服务。在线专业咨询，无需等待，人工服务时间：周一至周六9:00-17:30 ；客服热线，专业的人工电话服务热线，高峰期需要耐心等待。建议先使用在线咨询服务，服务热线：0754-88098777" />
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
        	<li><a href="goHelpWeb.do">首 页</a></li>
            <li><a href="goSelfServiceWeb.do">自主服务</a></li>
            <li><a href="goFaqWeb.do">常见问题</a></li>
            <li><a href="goContact.do"><span>联系客服</span> </a></li>
            <li><a href="goFeedBackWeb.do">意见反馈</a></li>
        </ul>
    </div>
</div>

<div class="faq-width">
	<div class="faq-L">
    	<div class="faq-L-title"><img src="images/faq_03.jpg" alt="" width="16" height="16" />&nbsp;&nbsp;&nbsp;&nbsp;帮助中心</div>
        
        <div class="faq-L-list">
            <div id="firstpane" class="menu_list">
                    <s:iterator value="helpTypePojoList">
                    <p class="menu_head"><s:property value="name"/></p>
                    <div id="head<s:property value="id"/>" style="display:none" class="menu_body" >
                      <s:iterator value="helpTypeChildPojoList">
                      <a href="goFaqWeb.do?typeid=<s:property value='id'/>&ptypeid=<s:property value='pid'/>"><s:property value="name"/></a>
                      </s:iterator>
                    </div>
                     </s:iterator>
            </div>
        </div>
    </div>
     <div class="faq-R">
		<!-- html代码begin -->
<div id="outer">
    <ul id="tab">
        <li class="current">
			<span style=" font-weight:bold; margin-left:20px;">在线咨询</span>&nbsp;<span><img src="images/qq-02.png" /></span>
			<p style="line-height:25px;margin-left:20px; margin-top:-10px;">为您提供各种在线专业咨询,无需等待!</p>
		</li>
        <li>
			<span style=" font-weight:bold; margin-left:20px;">客服热线</span>&nbsp;<span><img src="images/qq-03.png" /></span>
			<p style="line-height:25px;margin-left:20px; margin-top:-10px;">专业的人工电话服务热线,高峰期需要耐心等待.建议先使用在线
咨询服务.</p>
		</li>
       
    </ul>
    <div id="content">
        <ul style="display:block;">
          <li>QQ在线服务时间：周一至周日9：00-22：00</li>
                    <li style="height:30px;"></li>
                    <li>在线客服: <input style="vertical-align:middle" type="image" onClick="window.open('http://crm2.qq.com/page/portalpage/wpa.php?uin=4001501677&aty=0&a=0&curl=&ty=1 target=_blank')" src="images/qq-03.jpg" /></li>
        </ul>
        <ul>
             <li><a href="#">工作时间:周一至周六 9:00-17:30</a></li>
                    <li><a href="#">供应商入驻专线: 0754-88098777-606</a></li>
                    
                    <li><a href="#">服务热线: 0754-88098777</a></li>
                    <li><a href="#"><span style="margin-left:92px;">0754-88096777-626</span></a></li>
                    <li><a href="#">400热线: 400-150-3677</a></li>
                    <li style="height:30px;"><a href="#"></a></li>
                    <li><a href="#">分销商进驻专线:  0754-88096777-628</a></li>
                   
        </ul>
       
    </div>
	</div>
	
</div>

<!-- html代码end -->
<script src="http://www.lanrenzhijia.com/ajaxjs/jquery.min.js"></script>
<script>
	$(function(){
		window.onload = function()
		{
			var $li = $('#tab li');
			var $ul = $('#content ul');
						
			$li.mouseover(function(){
				var $this = $(this);
				var $t = $this.index();
				$li.removeClass();
				$this.addClass('current');
				$ul.css('display','none');
				$ul.eq($t).css('display','block');
			})
		}
	});
</script>
    </div>
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
<script type=text/javascript>
$(document).ready(function(){
	$("#firstpane .menu_body:eq(13)").show();
	$("#firstpane p.menu_head").click(function(){
		$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	$("#secondpane .menu_body:eq(0)").show();
	$("#secondpane p.menu_head").mouseover(function(){
		$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	
});
</script>
</body>
</html>
