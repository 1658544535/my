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
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/_head.js"></script>
<script>
function search(){
		document.getElementById("idform").submit();
	}
</script>
<title>淘竹马自主服务</title>
<meta name="keywords" content="婴童  母婴 淘竹马 玩具 玩具分销渠道 写字板 玩具琴 母婴用品 婴儿健身架 别墅玩具屋  手推车 " />
<meta name="description" content="平台提供在线自主服务，为了节约您的时间，当客服热线繁忙时，您可以通过自主服务找回账户密码，找回登录名等服务。" />

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
            <li><a href="goSelfServiceWeb.do"><span>自主服务</span></a></li>
            <li><a href="goFaqWeb.do">常见问题</a></li>
            <li><a href="goContact.do">联系客服 </a></li>
            <li><a href="goFeedBackWeb.do">意见反馈</a></li>
        </ul>
    </div>
</div>

<div class="self_service01">
	<div class="self_service01-L">
    	<div class="self_service01-L"><img src="images/self_service_03.jpg" alt="" width="78" height="78" /></div>
        <div class="self_service01-L-Txt">
        	<div class="self_service01-L-Txt01">找回密码</div>
            <div class="self_service01-L-Txt02">忘记密码了？点此自助找回您的密码</div>
            <a href="goRetrieve.do" class="self_service01-L-Txt03">点击进入</a>
        </div>
    </div>
    <div style="float:right;">
    	<div class="self_service01-L"><img src="images/self_service_05.jpg" alt="" width="78" height="78" /></div>
        <div class="self_service01-L-Txt">
        	<div class="self_service01-L-Txt01">找回登录名</div>
            <div class="self_service01-L-Txt02">点此了解找回登录名的方法</div>
            <a href="helpDetail.do?helpPojo.id=42" class="self_service01-L-Txt03">点击进入</a>
        </div>
    </div>
</div>
<div class="self_service02">
 <div class="self_service01-L">
    	<div class="self_service01-L"><img src="images/self_service_03.jpg" alt="" width="78" height="78" /></div>
        <div class="self_service01-L-Txt">
        	<div class="self_service01-L-Txt01">淘宝解除绑定</div>
        	<div class="self_service01-L-Txt02">解除淘宝登录账号绑定</div>
            <a href="reBound.do" class="self_service01-L-Txt03">点击进入</a>
        </div>
    </div>
    <div style="float:right;">
    	<div class="self_service01-L"><img src="images/self_service_05.jpg" alt="" width="78" height="78" /></div>
        <div class="self_service01-L-Txt">
        	<div class="self_service01-L-Txt01">QQ解除绑定</div>
        	<div class="self_service01-L-Txt02">解除QQ登录账号绑定</div>
            <a href="reBound.do" class="self_service01-L-Txt03">点击进入</a>
        </div>
    </div>
<div class="clear"></div>
</div>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
