<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=0" name="viewport">
<script type="text/javascript" src="/js/jquery/jquery-1.10.1.js"></script>
<title>${title}</title>
<style>
html,body{width:100%;margin:0;padding:0;font-family:'微软雅黑';}
html{font-size:12px;-webkit-text-size-adjust:none;}
body,div,ul,li,h1,h2,h3,h4,p,dl,dt,dd{margin:0px;padding:0px;}
h1,h2,h3,h4,h5{font-size:1rem;font-weight:normal;}
a{-webkit-tap-highlight-color:rgba(0,0,0,0);outline:none;text-decoration:none;}
input[type="text"],input[type="password"]{-webkit-appearance:none;appearance:none;outline:none;-webkit-tap-highlight-color:rgba(0,0,0,0);border-radius:0;}
ul,li{list-style:none;}
table{border-collapse:collapse;}
img{max-width:100%;border:none;-webkit-touch-callout:none;}

.header{height:47px;padding:0 10px;background-color:#fddf00;background-image:url(/webView/images/logo01.png);background-repeat:no-repeat;background-size:47px auto;background-position:10px center;}
.header h1{margin-left:67px;font-size:14px;color:#333;font-weight:bold;padding:6px 0 0 0;}
.header h3{margin-left:67px;font-size:13px;color:#333;}
.header a{position:absolute;top:0;right:10px;padding-left:29px;font-size:14px;color:#c70041;line-height:47px;background:url(/webView/images/share_login.png) no-repeat 6px center;background-size:17px auto;}
.mainTxt{margin:10px 10px 0 10px;font-size:14px;color:#333;text-align:center;line-height:20px;}
.link{margin:0 10px 10px;font-size:14px;color:#666;}
.link dt{padding:6px 0 3px;}
.link dd a{display:block;}
.link dd img{display:block;width:100%;height:auto;}
.footer{position:fixed;bottom:0;left:0;width:100%;}
.footer .download{position:relative;background:#fddf00;padding-bottom:15.2%;}
.footer .download .logo{position:absolute;left:0;bottom:0;width:60%;}
.footer .download .logo img{display:block;width:100%;height:auto;}
.download_btn{position:absolute;width:27.2%;left:62.66667%;top:22.81%;}
.download_btn img{display:block;width:100%;height:auto;}
.download_close{position:absolute;right:0;top:0;width:6%;padding-bottom:6%;background:url(/webView/images/share_download_close.png) no-repeat 50% 50%;background-size:9px 9px;cursor:pointer;}

.footer .option{background:#fff;padding:5px 10px;height:34px;}
.footer .option a{display:inline-block;vertical-align:middle;height:34px;line-height:34px;}
.footer .option a.next{float:right;color:#666;font-size:18px;}
.footer .option a.like{margin-left:20px;padding-left:28px;color:#666;font-size:18px;background:url(/webView/images/share_like.png) no-repeat left center;background-size:18px auto;}
pre {
white-space: pre-wrap;
word-wrap: break-word;
}
</style>
</head>

<body>
<div style="display:none;"><img src="${imgurl }" /></div>

    <div class="wraper">
        <div class="header">
            <h1>拼得好</h1>
            <h3>玩具低至7.7等你抢！</h3>
            <a href="http://dwz.cn/tzm1314">登录</a>
        </div>

        <div class="mainTxt" align="center">${title}</div>

        <!-- <dl class="link">
            <dt></dt>
            <dd><a href="#"></dd>
        </dl> -->
        
    	<div class="w-img">
    		<c:if test="${imgurl1 != null && imgurl1 != '' }"><div class="img"><img src="${imgurl2 }" /></div></c:if>
    		<c:if test="${imgurl3 != null && imgurl3 != '' }"><div class="img"><img src="${imgurl4 }" /></div></c:if>
    		<div class="info"><a href="http://dwz.cn/tzm1314"><pre>${content}</pre>
    		<c:if test="${type == 6 }"><%-- <jsp:include flush="true" page="/webView/growthLine/shareGrowthLine.jsp"/> --%>
    		<%@ include file="/webView/growthLine/shareGrowthLine.jsp"%></c:if>
    		</div>
    	</div>

    	<div class="w-video">
    		<c:if test="${url != null && url != '' }">
    			<iframe height="350" width="100%" src="${url }" allowtransparency="true" allowfullscreen="true" scrolling="no" border="0" frameborder="0"></iframe>
    		</c:if>
    	</div>
    	
<div class="wrapper">
	<ul class="list">
    	<c:forEach items="${list }" var="l"><li>
        	<a href="http://weixinm2c.taozhuma.com/product_detail?type=3&pid=${l.productId}&aid=${l.activityId}" id="#">
            	<div class="pro_pic"><img src="http://b2c.taozhuma.com/upfiles/product/${l.image}" /></div>
                <p class="pro_title">${l.productName }</p>
                <div class="pro_info">
                	<span class="pro_buy">立即购买</span>
                	<span class="pro_price">￥${l.activePrice }</span>
                </div>
            </a>
        </li></c:forEach>
    </ul>    

    
</div>
    </div>

        <!-- <div style="height:45px;"></div> -->
        <div class="footer">
            <div id="download" class="download">
                <div class="logo"><img src="/webView/images/share_footer.png" /></div>
                <a class="download_btn" href="http://dwz.cn/tzm1314"><img src="/webView/images/share_download.png" /></a>
                <span class="download_close" id="download_close"></span>
            </div>
            <!-- <div class="option">
                <a href="http://dwz.cn/tzm1314"><img src="/webView/images/share_suggestion.png" width="150" height="34" /></a>
                <a href="http://dwz.cn/tzm1314" class="like">999</a>
                <a href="http://dwz.cn/tzm1314" class="next">下一篇</a>
            </div> -->
        </div>

    </div>

    <script>
    	//$(function(){
		//	$("#content").html('${content}');
		//});
        document.getElementById("download_close").onclick = function(){
            document.getElementById("download").style.display = "none";
        };
    </script>

</body>
</html>
<style>
img{max-width:100%;border:none;-webkit-touch-callout:none;}
.w-img .img{position:relative;}
.w-img .img img{display:block;width:100%;height:auto;}
.w-img .info{padding:2.666666667% 4%;font-size:13px;color:#666;}
</style>
<style>
/* html,body,ul,li,h3,p{margin:0;padding:0;box-sizing:border-box;}
body{background:#f1f1f1;font-family:"Microsoft YaHei";} */
a:link,a:visited{color:#444343;text-decoration:none;box-sizing:border-box;-webkit-tap-highlight-color:rgba(0,0,0,0);}
a:hover{text-decoration:none;}
li{list-style:none;box-sizing:border-box;}
/* img{border:0;vertical-align:middle;box-sizing:border-box;} */
.wrapper{position:relative;width:100%;height:100%;margin:0 auto;box-sizing:border-box;}
.list{width:98%;margin:2% auto 4%;font-size:14px;overflow:hidden;}
.list li{float:left;width:48%;margin:1%;border:1px solid #ddd;background-color:#fff;}
.list li div.pro_pic{position:relative;padding-bottom:100%;border-bottom:1px solid #ddd;overflow:hidden;}
.list li div.pro_pic img{position:absolute;width:100%;left:0;top:0;}
.pro_title{height:50px;padding:4%;overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 2;-webkit-box-orient: vertical;line-height:20px;}
.pro_info{text-align:right;padding:4%;color:#999;height:20px;}
.pro_price{float:left;color:#ce0549;font-size:16px;}
.pro_buy{float: right;background: #e73c7b;color: #fff;border-radius: 10px;padding: 0 8px;text-decoration: none;line-height: 20px;}
</style>