<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html lang="zh">
<head>
<meta charset="utf-8">
<!--IOS中Safari允许全屏浏览-->
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="email=no" name="format-detection" />
<meta content="telephone=no" name="format-detection">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=0" name="viewport">
<title>图文详情</title>
<style>
	html,body{width:100%;margin:0;padding:0;font-family:Helvetica,STHeiTi,Arial,sans-serif;}
	html{-webkit-text-size-adjust:none;}
	body,div,ul,li,h1,h2,h3,h4,p,dl,dt,dd{margin:0px;padding:0px;}
	ul,li{list-style:none;}
	img{display:block;max-width:100%;border:none;-webkit-touch-callout:none;}
	.info{position:relative;font-size:14px;color:#666;margin:10px;}
	.info .list li{padding-bottom:10px;}
	.info .ensure{position:absolute;right:0;top:0;}
	.text{padding:0 10px 10px;}
	.text img{width:100%;}
	
	.w-video{position:relative;}
	.w-video video{position:absolute;top:0;left:0;width:100%;}
</style>
</head>

<body>
	<div class="w-video">
    	<s:if test="productPojo.videoUrl != null && productPojo.videoUrl != ''">
    		<iframe height="350" width="100%" src="${productPojo.videoUrl}" allowtransparency="true" allowfullscreen="true" scrolling="no" border="0" frameborder="0"></iframe>
    	</s:if>
    </div>
    <div class="wrap">
		<section class="text">
			${productPojo.content}
		</section>
    </div>
</body>
<script>
document.domain='${doMain}'; 
window.onload = function(){
    resizeIframe();
}
var resizeIframe=function(){
    var bodyw=document.body.clientWidth;
    if(document.getElementsByTagName("iframe") != null && document.getElementsByTagName("iframe").length > 0){
	    for(var ilength=0;ilength<=document.getElementsByTagName("iframe").length;ilength++){
	        document.getElementsByTagName("iframe")[ilength].height = bodyw*9/16;//设定高度
	    }
    }
}
</script>
</html>
