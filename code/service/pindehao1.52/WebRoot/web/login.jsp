<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<link href="/css/faq.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script>
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>
<title>淘竹马玩具分销平台</title>
<meta name="keywords" content="淘竹马 M2C分销平台 群宇互动 玩具产品 母婴用品一只发货 玩具厂家直销 玩具渠道 玩具电商 品牌玩具 " />
<meta name="description" content="欢迎登陆淘竹马。广东群宇互动科技有限公司旗下平台淘竹马，是全球专业M2C玩具分销平台，主要提供各类玩具产品的一站式分销服务，即玩具产品从生产商直接到分销商的平台模式。淘竹马以厂家直销、主营玩具、一只发货为特色，专注于为玩具厂商和分销商服务，致力整合玩具渠道，打造专属玩具供应商和采购商的一站式玩具分销平台。" />
<style type="text/css">
	.box-shadow {
box-shadow: 0 1px 2px #D1D1D1;
}
.mt-25 {
margin-top: 25px;
}
.wrapper {
margin: 0 auto;
position: relative;
width: 1190px;
}
.pure-g {
text-rendering: optimizespeed;
overflow:hidden;
}
.pure-u-7-10 {
width: 700px;
float:left;
}
#m-member-panel {
margin: 30px 0 60px 60px;
padding: 30px 0 0;
border-right: 1px solid #ccc;
min-height: 350px;
}
#m-member-panel h2 {
margin: 0 0 40px 15px;
border-left: 4px solid #CA3C3C;
padding-left: 10px;

}
h2 {
display: block;
font-size: 1.5em;
font-weight: bold;
letter-spacing:normal;
}
#m-member-panel form {
color: #666; margin:0; display:block;
}
.pure-form-aligned input{display: inline-block;
vertical-align: middle;}
.pure-form fieldset {
margin: 0;
padding: 0.35em 0 0.75em;
border: 0;
}
.pure-form-aligned .pure-control-group {
margin-bottom: 15px;
letter-spacing:normal;
}
.pure-form-aligned .pure-control-group label {
text-align: right;
display: inline-block;
vertical-align: middle;
width: 10em;
margin: 0 1em 0 0;
}
#m-member-panel form {
color: #666;
}
#m-member-panel .pure-input-1-3 {
margin-right: 12px;
}
.pure-form input[type=text], .pure-form input[type=password] {
height: 32px;
font-size: 12px;
padding-top: 8px;
}
.pure-form .pure-input-1-3 {
width: 33%;
padding: 0.5em 0.6em;
display: inline-block;
border: 1px solid #ccc;
box-shadow: inset 0 1px 3px #ddd;
border-radius: 2px;
-webkit-box-sizing: border-box;
-moz-box-sizing: border-box;
box-sizing: border-box;
}
.c-999{ color:#999; letter-spacing:normal; }
.pure-form-aligned .pure-controls {
margin: 1.5em 0 0 11em;
}
.c-999 a {
color: #3E3E3E;
text-decoration: none;
}
.c-999 a:hover{ color:#df434e;}
.pure-button-main {
background: #df434e;
/*border-color: #c6375e;*/
color: #fff;
border:1px solid #CC3333;
border-radius:3px;
text-shadow: 0 1px 1px rgba(0,0,0,.2);

}
#m-member-panel #submit-btn {
font-size: 14px;
}
.pure-button {
padding: .5em 1.5em;
}
.pure-u-3-10 {
width: 300px;
display: inline-block;
zoom: 1;
letter-spacing: normal;
word-spacing: normal;
vertical-align: top;
text-rendering: auto;
}
#m-login-tip {
text-align: center;
padding-top: 50px;
}
p {
margin: 0 0 8px;
display:block;
}
.mt-50 {
margin-top: 50px;
}
#m-open-login {
list-style-type: none;
margin: 15px 85px 0;
padding: 0;
text-align: left;
}
#m-open-login li {
background-color: #fff;
border: 1px solid #ddd;
border-radius: 4px;
cursor: pointer;
height: 24px;
line-height: 24px;
margin: 5px 0;
transition: border .2s linear 0s,-moz-box-shadow .2s linear 0s;
padding-left: 10px;
}
li {
display: list-item;
text-align: -webkit-match-parent;
}
.icons.qq-icon {
background-position: 0 -55px;
}
.icons {
background: url(http://s1.husor.cn/image/base/icons.png) no-repeat -1000px 0;
display: inline-block;
height: 18px;
line-height: 18px;
padding-left: 22px;
}
a {
color: #3E3E3E;
text-decoration: none;
}
.pure-button-green {
background: #df434e;
border-color: #CC3333;
}
.icons {
background: url(http://s1.husor.cn/image/base/icons.png) no-repeat -1000px 0;
display: inline-block;
height: 18px;
line-height: 18px;
padding-left: 22px;
}
#m-open-login li:hover {
border: 1px solid #ccc;
box-shadow: 0 0 7px #ccc;
}
.icons.taobao-icon {
background-position: 0 0;
}
.white-bg {
background: #ffffff;
margin-bottom: 10px;
overflow: hidden;
}

#main-nav {
height: 38px;
position: relative;
background: #df434e;
width:100%;
margin-top:10px;
}
#head .wrapper {
z-index: 200;
z-index: 301;
position: relative;
}
#main-nav .nav-item.current, #main-nav .nav-item.current:hover {
/*background: #cf2f3b;*/
color: #fff;
text-decoration: none;
line-height:38px;
}
#main-nav .nav-item {
/*background: #df434e;*/
float: left;
font-size: 16px;
height: 38px;
position: relative;
width: 120px;
color: #fff;
font: 700 15px/38px "";
text-align: center;
text-decoration: none;
line-height:38px;

}
.nav-item:hover{ background:#cf2f3b;}
.f-12 {
font-size: 12px;
}
.f-12:hover{ color:#df434e;}
</style>
<script type="text/javascript">
function searchShop(){
	$("#shopform").attr("action","searchShopWebByName.do#tabs-2");
	$("#shopform").submit();
}
function searchProductNo(){
	$("#opform").attr("action","searchProductNo.do#tabs-3");
	$("#opform").submit();
}
function setTab(name,cursel){
	cursel_0=cursel;
	for(var i=1; i<=links_len; i++){
		var menu = document.getElementById(name+i);
		var menudiv = document.getElementById("con_"+name+"_"+i);
		if(i==cursel){
			menu.className="off";
			menudiv.style.display="block";
		}
		else{
			menu.className="";
			menudiv.style.display="none";
		}
	}
}
function Next(){                                                        
	cursel_0++;
	if (cursel_0>links_len)cursel_0=1
	setTab(name_0,cursel_0);
} 
var name_0='one';
var cursel_0=1;
/*var ScrollTime=3000;*///循环周期（毫秒）
var links_len,iIntervalId;
onload=function(){
	var links = document.getElementById("tab1").getElementsByTagName('li')
	links_len=links.length;
	for(var i=0; i<links_len; i++){
		links[i].onmouseover=function(){
			clearInterval(iIntervalId);
			this.onmouseout=function(){
				iIntervalId = setInterval(Next,ScrollTime);;
			}
		}
	}
	document.getElementById("con_"+name_0+"_"+links_len).parentNode.onmouseover=function(){
		clearInterval(iIntervalId);
		this.onmouseout=function(){
			iIntervalId = setInterval(Next,ScrollTime);;
		}
	}
	setTab(name_0,cursel_0);
	iIntervalId = setInterval(Next,ScrollTime);
}
</script>
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}

.tab1{width:516px;margin:32px 0 0 auto;}
.menu{height:28px; font-size:12px; font-family:"微软雅黑";}
.menu li{float:left;width:29px;text-align:center;line-height:28px;height:28px;cursor:pointer;color:#666;font-size:14px;overflow:hidden; font-weight: bold;}
.menu li.off{/*background:#FFFFFF*/;color:#df434e;font-weight:bold;}

.menudiv{/*height:200px;*/border-left:#cccccc solid 1px;border-right:#cccccc solid 1px;border-top:0;background:#fefefe}
.menudiv div{/*padding:15px;*/line-height:28px;}
.sea-Txt-line{ line-height:28px; float:left;}
</style>
</head>
<body class="body" style="background:#f5f5f5;">
<jsp:include page="header.jsp"></jsp:include>

<div class="top02-width">
	<a href="goIndexWeb.do"><div class="logo"></div></a>
	<div class="tab1" id="tab1">
	<div class="menu">
		<ul>
			<li id="one1" onclick="setTab('one',1)">产品</li>
			<span class="sea-Txt-line">｜</span>
			<li id="one2" onclick="setTab('one',2)">店铺</li>
			<span class="sea-Txt-line">｜</span>
			<li id="one3" onclick="setTab('one',3)">货号</li>
		</ul>
	</div>
	<div class="menudiv">
		<div id="con_one_1">
			<form action="searchWeb.do" method="post" id="sysform">
				<div class="search2">
					<input name="productPojo.productName" type="text"    placeholder="搜索 商品"  class="search-input">
					<button type="submit" class="search-button" onclick="searchBtn()">搜 索</button>
				</div>
			</form>
		</div>
		<div id="con_one_2" style="display:none;">
			<form action="searchWeb.do" method="post" id="shopform">
				<div class="search2">
					<input name="shop.name" type="text" placeholder="搜索 店铺" class="search-input">
					<button type="submit" class="search-button" onclick="searchShop()">搜 索</button>
				</div>
			</form>
		</div>
	</div>
	
		<div id="con_one_3" style="display:none;">
			<form action="searchProductNo.do" method="post" id="opform">
				<div class="search3">
					<input name="productPojo.productNum" type="text" placeholder="搜索 货号" class="search-input">
					<button type="submit" class="search-button" onclick="searchProductNo()">搜 索</button>
				</div>
			</form>  
	</div>
	</div>

<div class="clear"></div>
<div id="main-nav">
        <div class="wrapper">
            
            <a href="goIndexWeb.do" class="nav-item current">首页</a>
            <a href="goNewWeb.do" class="nav-item ">新品快订</a>
            <a href="goshopWeb.do" class="nav-item ">
                店铺街
            </a>
            <a href="infoPageList.do" class="nav-item ">行业资讯</a>
          
    	</div>
    </div>
<div class="faq-width" style="font-size:12px;">
	<div id="content" class="pure-g wrapper mt-25 white-bg box-shadow">
                <div class="pure-u-7-10">
    <div id="m-member-panel" class="register">
        <h2>登录&nbsp;淘竹马玩具分销平台</h2>
        <form id="dataform" action="doLoginWebDone.do" class="pure-form pure-form-aligned" method="post" accept-charset="utf-8">
        <input name="url" id="url" value="${url}" class="inputText" type="hidden" >
        <fieldset>
            <div class="pure-control-group">
    			<label for="email">你的帐号：</label>
    			<input type="text" id="loginId" class="pure-input-1-3" data-hint="输入你的手机号码" placeholder="输入你的手机号码" autocomplete="off" name="loginId" value="">
    			
    			    		</div>
    		<div class="pure-control-group">
    			<label for="passwd">密码：</label>
    			<input type="password" id="loginPd" class="pure-input-1-3" data-hint="6-16位，可含字母、数字、符号" name="loginPd" value="">
    			<a href="goRetrieve.do" class="f-12">忘记密码?</a>
    			    		</div>
    		
			<div class="pure-controls" style="letter-spacing:normal;">
        		
                    <input type="checkbox" checked="checked" id="remember-me" name="remember-me">&nbsp;&nbsp;记住我（下次自动登录）
               
            </div>
			
    		<div class="pure-controls">
                <!--<button id="sbutton" class="pure-button  pure-button-main  pure-u-3-10" style="width:33%;">登陆</button>-->
                <input type="submit" id="btn_sure" value="登陆" style="width: 218px;height: 50px;font-size: 18px;font-weight: bold;text-align: center;color: #FFF;background: #df434e;margin: 20px 0 20px 0px;line-height: 50px;cursor: pointer;" />
                <span id="msg-area" class="f-12"><!--  --></span>
    		</div>
    		<div class="pure-controls">
                <div id="msg" style="color:red;"><s:property value="msg" /></div>
    		</div>
    	</fieldset>
        </form>    </div>
</div>

<div class="pure-u-3-10">
    <div id="m-login-tip">
        <p><font style="font-weight:bold;">没有账号？</font> <a href="goRegister.do" class="pure-button pure-input-1-3 pure-button-green pure-button-main">立即注册</a></p>
       <div class="mt-50 c-999">
            <p>你也可以通过以下途径登录</p>
            <ul id="m-open-login">
                <li><a class="icons qq-icon" href="toLoginByqq.do">用QQ账号登录</a></li>
                <li><a class="icons taobao-icon" href="toLoginBytaobao.do">用淘宝账号登录</a></li>
            </ul>
        </div>
    </div>
</div>
            
            </div>
    
   
</div>

<div class="clear"></div>

  


    
</div>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
