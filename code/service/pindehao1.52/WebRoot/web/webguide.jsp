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
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<link href="/css/down.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script>
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>
<script>
function search(){
		document.getElementById("idform").submit();
	}
</script>
<title>淘竹马玩具分销平台</title>
<script>
function search(){
		document.getElementById("idform").submit();
	}
</script>


<style type="text/css">
.hotCate {
width: 790px;
overflow: hidden;
margin:0 auto;

}
.hotServe {
width: 790px;
float: left;
margin-top: px;
}
.hotServe li {
float: left;
width: 187px;
height: 134px;
overflow: hidden;
display: inline-block;
margin-right: 14px;
text-align: center;
line-height: 25px;
}
.cateOne {
background-position: 0 0;
background:url(images/T1mRZBXoxbXXbOIiAn-528-300.png);
}
.hotServe h5 {
color: #666;margin: 10px 0;
}
.hotServe li {
text-align: center;
line-height: 25px;
}
.hotServe p {
width: 175px;
height: 20px;
float: left;
margin: 0 0px 5px 8px;
}
.hotServe a {
color: #646464;
margin: 0 6px;
display: inline-block;
width: 72px;
height: 20px;
overflow: hidden;
float: left;
}
.separateline {
display: inline-block;
height: 12px;
width: 1px;
overflow: hidden;
background: #646464;
margin: 0 3px;
float: left;
margin-top: 7px;
}
.cateTwo {
background:url(images/T1mRZBXoxbXXbOIiAn-528-300-2.png);


}
.cateThree {
background:url(images/T1mRZBXoxbXXbOIiAn-528-300-3.png);

}
.cateFour {

margin-right: 0!important;
background:url(images/T1mRZBXoxbXXbOIiAn-528-300-4.png) no-repeat;

}
.detail-list {
/*padding: 35px 0 30px 75px;*/
overflow:hidden;

}
.detail-line {
margin-bottom: 35px;
overflow:hidden;
}
.fd-clr {
zoom: 1;
}
.detail-list .detail {
float: left;
display: inline;
width: 238px;
overflow: hidden;
margin: 0 60px 0 0;
font-family: 微软雅黑, 'Microsoft YaHei';
font-size: 14px;
}
.detail h3 {
color: #df434e;
font-weight: bold;
margin-bottom: 4px;
font-size: 14px;
padding-left: 10px;
text-align:left;
}
.detail li {
float: left;
height: 18px;
overflow: visible;
padding: 0 10px;
margin: 5px 0;
border-right: 1px solid #dededf;
}
.detail-list .detail a:link, .detail-list .detail a:visited {
color: #888;
text-decoration: none;
white-space: nowrap;
}
.detail-list .detail a:hover {
color: #000;
text-decoration: none;
white-space: nowrap;
}
.hotServe a:hover{color: #ff7300;}
.itemUlClass li{ border-bottom:1px solid #dfdfdf; text-indent:10px;}
.separatePage {
width: 100%;
background-color: #ea3e00;
height: 2px;
}
</style>




</head>


<body class="body">
<jsp:include page="header.jsp"></jsp:include>




<div class="top02-width">
	<div class="logo02"></div>
	<form action="http://www.taozhuma.com/goSearch.do" id="idform" method="post">
	<div class="search"><input id="searchkey" name="searchkey" type="text" value="请简单描述你的问题" onfocus="this.value=&#39;&#39;" onblur="if(this.value==&#39;&#39;){this.value=&#39;请简单描述你的问题&#39;}" class="search-input"><button type="submit" class="search-button" onclick="search()">搜 索</button></div>
	</form> 
</div> 

<div class="clear"></div>

<div class="help-nav-width">
	<div class="help-nav-fu">
    	<ul>
        	<li><a href="goaboutus.do?helpPojo.id=37">公司简介</a></li>
            <li><a href="goaboutus.do?helpPojo.id=38">服务项目</a></li>
            <li><a href="goaboutus.do?helpPojo.id=39">联系我们</a></li>
            <li><a href="goaboutus.do?helpPojo.id=40">加入我们</a></li>
            <li><a href="goaboutus.do?helpPojo.id=230">供应商入驻</a></li>
            <li><a href="goaboutus.do?helpPojo.id=231">分销商招募</a></li>
        </ul>
    </div>
</div>

<div class="faq-width">
	<div class="faq-L">
    	<div class="faq-L-title"><img src="images/faq_03.jpg" alt="" width="16" height="16">&nbsp;&nbsp;&nbsp;&nbsp;淘竹马</div>
        
       <div class="faq-L-list">
            <div id="firstpane" class="menu_list">
               <p class="menu_head">淘竹马</p>
                    <div style="display:none" class="menu_body">
                      <a href="gotzmfxpt.do">淘竹马分销平台</a>
                      <a href="gotzmapp.do">淘竹马APP</a>
                      <a href="gowxgzh.do">淘竹马公众号</a>
                    </div>
                     <p class="menu_head">见客</p>
                    <div style="display:none" class="menu_body">
                      <a href="gojkapp.do">见客APP</a>
                      <a href="goqkd.do">企客端</a>
                      <a href="http://www.5315.cn/" target="blank">玩具总汇网</a>
                    </div>
                    <p class="menu_head"><a href="goshike.do">舌客</a></p>
                    <p class="menu_head"><a href="godsxy.do">梁山电商学院</a></p>
                    <p class="menu_head"><a href="gowjzh.do">玩具总汇期刊</a></p>
                    <p class="menu_head"><a href="gofdcWeb.do">翻动城</a></p>
                    
                    
            </div>
        </div>
    </div>
    
<div class="faq-R-fu">
    
    	<div style="padding:10px 30px;">
            <span style=" display:block; padding-top:20px; line-height:30px;font-size:18px; font-weight:bold; margin:0 auto; text-align:center;">网站导航</span><br>
           
            <div class="detail-list" data-spm="1367341213">
				<div class="detail-line fd-clr">
					<div class="detail">
						<h3>热点推荐</h3>
						<ul>
							<li>
								<a href="goNewWeb.do" title="" target="_self">新品快订</a>
							</li>
							<li>
								<a href="goshopWeb.do" title="" target="_self">店铺街</a>
							</li>
							<li>
								<a href="infoPageList.do" title="" target="_self">行业资讯</a>
							</li>
							
						</ul>
					</div>
					<div class="detail">
						<h3>行业市场</h3>
						<ul>
							<li>
								<a href="goProductListWeb.do?productPojo.productTypeIds=1#infor" title="" target="_self">遥控/电动玩具</a>
							</li>
							<li>
								<a href="goProductListWeb.do?productPojo.productTypeIds=2#infor" title="" target="_self">早教/音乐玩具</a>
							</li>
							<li>
								<a href="goProductListWeb.do?productPojo.productTypeIds=3#infor" title="" target="_self">过家家玩具</a>
							</li>
							<li>
								<a href="goProductListWeb.do?productPojo.productTypeIds=4#infor" title="" target="_self">童车玩具</a>
							</li>
							<li>
								<a href="goProductListWeb.do?productPojo.productTypeIds=5#infor" title="" target="_self">益智玩具</a>
							</li>
							<li class="detail-item-last">
								<a href="goProductListWeb.do?productPojo.productTypeIds=6#infor" title="" target="_self">其它玩具</a>
							</li>
						</ul>
					</div>
					<div class="detail">
						<h3>帮助中心</h3>
						<ul>
							<li>
								<a href="goHelpWeb.do" title="" target="_self">新手指南</a>
							</li>
							<li>
								<a href="goSelfServiceWeb.do" title="" target="_self">自主服务</a>
							</li>
							<li>
								<a href="goFaqWeb.do" title="" target="_self">常见问题</a>
							</li>
							
							
							<li class="detail-item-last">
								<a href="goContact.do" title="" target="_self">联系客服</a>
							</li>
						</ul>
					</div>
			   </div>
			   
			 																																																																		
			</div>
             
        </div>
</div>

<div class="clear"></div>


<jsp:include page="footer.jsp"></jsp:include>
<!-- 代码 结束 -->
<script type="text/javascript">
$(document).ready(function(){
	$("#firstpane .menu_body:eq(10)").show();
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

</div></body></html>