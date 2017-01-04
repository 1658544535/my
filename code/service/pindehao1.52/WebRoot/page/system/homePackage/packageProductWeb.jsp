<%@page import="com.tzmb2c.web.pojo.SceneProductPojo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=0" name="viewport">
<!--IOS中Safari允许全屏浏览-->
<meta content="yes" name="apple-mobile-web-app-capable">
<!--IOS中Safari顶端状态条样式-->
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<!--忽略将页面中的数字识别为电话号码-->
<meta content="telephone=no" name="format-detection">
<!--去除Android平台中对邮箱地址的识别-->
<meta content="email=no" name="format-detection" />
<title>套餐</title>
<script src="jquery-2.1.4.min.js"></script>
<style>
html,body,ul,p{margin:0;padding:0;box-sizing:border-box;}
body{background:#f1f1f1;font-family:"Microsoft YaHei";}
a:link,a:active{color:#444343;text-decoration:none;box-sizing:border-box;-webkit-tap-highlight-color:rgba(0,0,0,0);}
a:hover{text-decoration:none;}
li{list-style:none;box-sizing:border-box;}
img{border:0;vertical-align:middle;box-sizing:border-box;}
.wrapper{position:relative;width:100%;height:100%;margin:0 auto;box-sizing:border-box;}
.top{padding-bottom:2%;background:#fff;}
.top img{width:100%;}
.top_price,.top_info{margin:2% 4% 0;font-size:16px;line-height:28px;color:#727171;text-align:justify;}
.top_info *{color:#727171!important;}
.top_info p{text-indent:2em!important;}
.top_info p img{margin-left:-2em!important;}
.top_price{position:relative;padding:0 10px;border-left:4px solid #d2d4d3;}
.top_nowPrice{font-size:20px;color:#ce0549;}
.top_oldPrice{font-size:14px;color:#999;}
.top_oldPrice font{text-decoration:line-through;}
.top_price a{position:absolute;right:0;top:5px;font-size:15px;color:#ce0549;border:2px solid #ce0549;border-radius:20px;padding:5px 20px;}
.top_info img{max-width:100%;height:auto;}
.list{width:98%;margin:2% auto 4%;font-size:14px;overflow:hidden;}
.list li{float:left;width:48%;margin:1%;border:1px solid #ddd;background-color:#fff;}
.list li div.pro_pic{position:relative;padding-bottom:100%;overflow:hidden;}
.list li div.pro_pic img{position:absolute;width:100%;left:0;top:0;}
.pro_title{height:40px;margin:4%;overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 2;-webkit-box-orient: vertical;line-height:20px;}
.pro_info{text-align:right;padding:4%;color:#999;height:20px;}
.pro_price{float:left;color:#ce0549;font-size:16px;}
</style>
<script>
	$(function(){	
		var messagingIframe;
		messagingIframe = document.createElement('iframe');
		messagingIframe.style.display = 'none';
		document.documentElement.appendChild(messagingIframe);
		$(".list li a,.top .top_price a").click(TestIOSJS);
		function TestIOSJS(){
			var id = $(this).attr("id");
			messagingIframe.src = id;
			return false;
		};
	});	
</script>
</head>

<body>
<div class="wrapper">
	<c:if test="${scenePojo != null && sceneProductPojos.size() != 0 }">
    <div class="top">
    	<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/homePackage/${scenePojo.image }" />
        <div class="top_price">
        	<span class="top_nowPrice">￥${scenePojo.packagePrice }</span>
            <br>
            <span class="top_oldPrice">&nbsp;价格<font>￥${scenePojo.psellPrice }</font></span>
            <a href='{"type":"2","sceneId":"${scenePojo.id}","detail":"0"}' id="buy">去购买</a>
        </div>
        <div class="top_info">${scenePojo.introduction }</div>
    </div>
	<ul class="list">
	<%-- <%List<SceneProductPojo> list = (List<SceneProductPojo>)session.getAttribute("sceneProductPojoList");
	if(list != null){
	for (SceneProductPojo s : list) { %>
		<li>
        	<a href="javascript:;" id="630">
            	<div class="pro_pic"><img src="/upfiles/homePackage/packageProduct/<%=s.getImage() %>" /></div>
                <p class="pro_title"><%=s.getIntroduction() %></p>
                <div class="pro_info">
                	<span class="pro_price">￥<%=s.getScenePrice() %></span>
                </div>
            </a>
        </li>
	<%}} %> --%>
    <c:forEach items="${sceneProductPojos }" var="s">
    	<li>
        	<a href='{"type":"2","sceneId":"0","productId":"${s.productId}","detail":"1"}' id="630">
            	<div class="pro_pic"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/${s.image }" /></div>
                <p class="pro_title">${s.title }</p>
                <div class="pro_info">
                	<span class="pro_price">￥${s.distributionPrice }</span>
                </div>
            </a>
        </li>
    </c:forEach>
    </ul>
    </c:if>

    
</div>
</body>
</html>