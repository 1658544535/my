<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=0" name="viewport">
<!--IOS中Safari允许全屏浏览-->
<meta content="yes" name="apple-mobile-web-app-capable">
<!--IOS中Safari顶端状态条样式-->
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<title>web</title>
<style>
html,body{width:100%;height:100%;margin:0;padding:0;font-family:Helvetica;}
body{font-size:12px;background-color:#ffb931;}
body,div,ul,li,h1,h2,h3,h4,p,dl,dt,dd{margin:0px;padding:0px;}
h1,h2,h3,h4,h5{font-size:1rem;font-weight:normal;}
a{-webkit-tap-highlight-color:rgba(0,0,0,0);outline:none;text-decoration:none;}
input[type="text"],input[type="password"]{-webkit-appearance:none;appearance:none;outline:none;-webkit-tap-highlight-color:rgba(0,0,0,0);border-radius:0;}
ul,li{list-style:none;}
table{border-collapse:collapse;}
img{border:none;-webkit-touch-callout:none;}
<!--忽略将页面中的数字识别为电话号码-->
<meta content="telephone=no" name="format-detection">
<!--去除Android平台中对邮箱地址的识别-->
<meta content="email=no" name="format-detection" />
.wrapper{position:relative;width:100%;height:100%;}

.title,.tv,.cloud,.shine,.adorn,.bubble{position:absolute;width:100%;height:100%;z-index:1;background-size:auto 100%;}
.title{background-image:url(<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/webView/images/channel_title.png);background-position:top center;background-repeat:no-repeat;z-index:5;}
.adorn{background-image:url(<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/webView/images/channel_adorn.jpg);background-size:100% auto;background-position:top center;background-repeat:repeat-x;z-index:1;}
.tv{background-image:url(<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/webView/images/channel_tv.png);background-position:right bottom;background-repeat:no-repeat;z-index:4;}
.cloud{background-image:url(<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/webView/images/channel_cloud.png);background-position:right bottom;background-repeat:repeat-x;z-index:3;}
.shine{background-image:url(<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/webView/images/channel_shine.png);background-position:right bottom;background-repeat:no-repeat;z-index:2;}

.channel{position:absolute;width:100%;height:100%;z-index:9;}
.channel>div{position:absolute;}
.channel>div a{position:relative;display:block;width:100%;padding-bottom:100%;overflow:hidden;background:url(webView/images/channel_bg.png) 0 0 no-repeat;background-size:100% 100%;}
.channel>div a img{position:absolute;width:76%;height:76%;left:12%;top:12%;overflow:hidden;border-radius:50%;}
.channel_1{width:21%;bottom:67%;right:22%;}
.channel_2{width:23%;bottom:55%;right:74%;}
.channel_3{width:25%;bottom:54%;right:43%;}
.channel_4{width:30%;bottom:46%;right:0%;}
.channel_5{width:36%;bottom:30%;right:64%;}
.channel_6{width:23%;bottom:10%;right:65%;}
/*横屏 css*/
@media screen and (orientation: landscape) {
    .title{background-position:22% 10%;background-size:60% auto;}
    .channel_1{width:16%;top:30%;left:10%;}
    .channel_2{width:16%;top:30%;left:30%;}
    .channel_3{width:16%;top:30%;left:50%;}
    .channel_4{width:16%;top:60%;left:10%;}
    .channel_5{width:16%;top:60%;left:30%;}
    .channel_6{width:16%;top:60%;left:50%;}
}
</style>
</head>

<body>
    <div class="wrapper">

        <!--背景饰图 start-->
        <div class="title"></div>
        <div class="adorn"></div>
        <div class="tv"></div>
        <div class="cloud"></div>
        <div class="shine"></div>
        <!--背景饰图 end-->

        <!--频道列表 start-->
        <div class="channel">
        <s:if test="#childrenChannelList!=null ">
         <s:iterator value="childrenChannelList" id="id" status="L">  
            <div class="channel_<s:property value='#L.index+1'/>"><a href='{"channelId":"<s:property value='id'/>","name":"<s:property value='name'/>"}'><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/childrenChannel/<s:property value='picture'/>" /></a></div>
        </s:iterator> 
        </s:if>
        </div>
        <!--频道列表 start-->

    </div>
</body>
</html>
