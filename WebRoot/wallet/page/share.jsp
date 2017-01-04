<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=0" name="viewport">
<!--IOS中Safari允许全屏浏览-->
<meta content="yes" name="apple-mobile-web-app-capable">
<!--IOS中Safari顶端状态条样式-->
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<title>分享好友</title>
<style>
html,body{width:100%;height:100%;margin:0;padding:0;background:#fff798;font-family:arial,Microsoft YaHei;}
html{font-size:40px;}
h3,p{margin:0;padding:0;font-weight:normal;}
.red{color:#e73c7b!important;}
.brown{color:#842302!important;text-align:center;font-size:1.4rem;line-height:2rem;}
.top,.top img{display:block;width:100%;height:auto;}
.cash{position:relative;width:100%;padding-bottom:45%;overflow:hidden;background:url(<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/wallet/images/share_cash.png) no-repeat;background-size:100% auto;}
.cash p{position:absolute;top:24%;left:0;width:100%;text-align:center;color:#fff;font-size:7rem;}
.cash p span{font-size:5rem;}

.receive{position:relative;width:100%;padding-bottom:33%;overflow:hidden;background:url(<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/wallet/images/receive.png) 0 -1px no-repeat;background-size:100% auto;}
.receive_a{position:absolute;left:5%;top:14%;width:88%;height:40%;-webkit-opacity:0;opacity:0;}
a{-webkit-tap-highlight-color:rgba(0,0,0,0);}
@media screen and (max-width: 1200px) {html{font-size:30px;}}
@media screen and (max-width: 1024px) {html{font-size:28px;}}
@media screen and (max-width: 800px) {html{font-size:24px;}}
@media screen and (max-width: 768px) {html{font-size:20px;}}
@media screen and (max-width: 600px) {html{font-size:16px;}}
@media screen and (max-width: 500px) {html{font-size:14px;}}
@media screen and (max-width: 414px) {html{font-size:10.5px;}}
@media screen and (max-width: 320px) {html{font-size:9px;}}
</style>
</head>
<body>
<div class="wrapper">
    <!--顶部图片-->
    <div class="top"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/wallet/images/top_share.jpg" alt="淘竹马，大批现金来袭送不停" /></div>

    <!--现金-->
    <div class="cash">
        <p><span>￥</span>100元</p>
    </div>

    <div class="brown" style="padding:3% 0 0">
        接受${sysLoginPojo.name}的邀请领取100元半价券，<br/><span style="font-size:1.5rem;">到淘竹马任性买买买！</span>
    </div>

    <!--马上领取-->
    <div class="receive">
        <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.ruiyu.taozhuma" class="receive_a">马上领取</a>
    </div>

    <!--邀请码-->
    <div class="brown">
        邀请码：<b class="red" style="font-size:2.6rem;">${sysLoginPojo.invitationCode}</b>
    </div>

    <div class="brown" style="width:86%;margin:0 auto;padding:5% 0 10%;">
        做雷锋帮好友赚钱，拷贝邀请码，下载淘竹马APP，到“我的-输入邀请码”中输入领取
    </div>

</div>
</body>
</html>
