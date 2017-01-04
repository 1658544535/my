<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title }</title>
    <link rel="stylesheet" type="text/css" href="/tzmeditor/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/tzmeditor/css/web-b-reset.css" />
    <link rel="stylesheet" type="text/css" href="/tzmeditor/js/Swiper/swiper.css" />
    <script src="/tzmeditor/js/jquery-1.11.2.min.js"></script>
    <script src="/tzmeditor/js/bootstrap.min.js"></script>
    <script src="/tzmeditor/js/baiduTemplate.js"></script>
    <script src="/tzmeditor/js/Swiper/swiper.min.js"></script>
    <%-- <script src="/tzmeditor/js/web-app2.js"></script> --%>
    <script src="/tzmeditor/js/web-app3.js"></script>
    <style>
		html,body{width:100%;margin:0;padding:0;font-family:Helvetica;}
		html{font-size:12px;-webkit-text-size-adjust:none;}
		body,div,ul,li,h1,h2,h3,h4,p,dl,dt,dd{margin:0px;padding:0px;}
		h1,h2,h3,h4,h5{font-size:1rem;font-weight:normal;}
		a{-webkit-tap-highlight-color:rgba(0,0,0,0);outline:none;text-decoration:none;}
		input[type="text"],input[type="password"]{-webkit-appearance:none;appearance:none;outline:none;-webkit-tap-highlight-color:rgba(0,0,0,0);border-radius:0;}
		ul,li{list-style:none;}
		table{border-collapse:collapse;}
		img{max-width:100%;border:none;-webkit-touch-callout:none;}
		
		.w-title{padding:2.666666667% 4%;}
		.w-title .title{padding-bottom:1%;font-size:14px;color:#333;}
		.w-title .info{font-size:13px;color:#666;}
		
		.w-img .img{position:relative;}
		.w-img .img img{display:block;width:100%;height:auto;}
		.w-img .info{padding:2.666666667% 4%;font-size:13px;color:#666;}
		
		.w-video{position:relative;padding-bottom:56%;}
		.w-video video{position:absolute;top:0;left:0;width:100%;height:100%;}
		.w-btn{
			text-align: left;
			padding: 10px 15px 15px;
			background: #fff;
		}
		.w-btn a,.w-btn a:visited{
			display: inline-block;
			margin: 5px 5px;
			padding: 4px 12px;
			font-size: 12px;
			color: #666;
			border-radius:4px;
			background:#ededed;
			text-decoretion:none;
		}
		.goodDetail{
			cursor: pointer;
		}
		.coupon{
			cursor: pointer;
		}
		
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
	</style>
</head>

<body>
    <div class="wrap">
		<div id="phoneView"></div>
    </div>
    <!-- <div id="w-btn" class="w-btn"> -->
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
    <script>
    	//$(function(){
		//	$("#content").html('${content}');
		//});
        document.getElementById("download_close").onclick = function(){
            document.getElementById("download").style.display = "none";
        };
    </script>
   	</div>
	<!-- 前端模板开始 -->
    <script id='tpl_text' type="text/template">
		<section class="view-text" data-type="<\%=type%>" data-id=<\%=id%>>
			<\%=data.text%>
		</section> 
	</script>
	<script id='tpl_goods' type="text/template">
		<section class="view-goods" data-type="<\%=type%>" data-id=<\%=id%>>
			<\%if(data.view=="big"){%>
				<ul class="big" id="goodsList">
			<\%}else if(data.view=="small"){%>
				<ul class="small" id="goodsList">
			<\%}else{%>
				<ul class="detail" id="goodsList">
			<\%}%>
					<\%for(var i=0;i<data.list.length;i++){%>
						<li data-goodId="<\%=data.list[i]["goodId"]%>" data-activityId="<\%=data.list[i]['activityId']%>" class="goodDetail">
							<div class="box">
								<div class="img"><img src="<\%=data.list[i]["img"]%>" /></div>
								<div class="info">
									<\%if(data.viewAttr.title){%>
										<h3 class="title <\%if(!data.viewAttr.title){%> r-hidden<\%}%>"><\%=data.list[i]["title"]%></h3>
									<\%}%>
									<div class="clearfix">
										<div class="oldPrice">￥<\%=data.list[i]["oldPrice"]%></div>
										<div class="price<\%if(!data.viewAttr.price){%> r-hidden<\%}%>">￥<\%=data.list[i]["price"]%></div>
										<div class="seeDetail<\%if(!data.viewAttr.seeDetail){%> r-hidden<\%}%>"><button class="btn btn-sm btn-default pull-right" type="button" style="background:#c0504d">立即购买</button></div>
									</div>
								</div>
							</div>
						</li>
					<\%}%>
				</ul>
		</section>
	</script>
    <script id='tpl_video' type="text/template">
		<section class="view-video" data-type="<\%=type%>" data-id=<\%=id%>>
			<div><iframe src="<\%=data.src%>" frameborder=0 'allowfullscreen'></iframe></div>
		</section> 
	</script>
    <script id='tpl_image' type="text/template">
		<section class="view-image" data-type="<\%=type%>" data-id=<\%=id%>>
			<\%if(data.carousel){%>
				<div class="swiper-container">
		            <div class="swiper-wrapper">
						<\%for(var i=0;i<data.list.length;i++){%>
						<div class="swiper-slide">
							<\%if(!!data["list"][i]["goodId"]){%>
								<a href={"type":"goods","goodsId":"<\%=data["list"][i]["goodId"]%>","activityId":"<\%=data["list"][i]["activityId"]%>"}><img src="<\%=data["list"][i]["img"]%>" /></a>
							<\%}else{%>
								<img src="<\%=data["list"][i]["img"]%>" />
							<\%}%>
						</div>
						<\%}%>
		            </div>
		            <!-- Add Pagination -->
		            <div class="swiper-pagination"></div>
	            </div>
			<\%}else{%>
				<\%for(var i=0;i<data.list.length;i++){%>
				<div>
					<\%if(!!data["list"][i]["goodId"]){%>
						<a href={"type":"goods","goodsId":"<\%=data["list"][i]["goodId"]%>","activityId":"<\%=data["list"][i]["activityId"]%>"}><img src="<\%=data["list"][i]["img"]%>" /></a></div>
					<\%}else{%>
						<img src="<\%=data["list"][i]["img"]%>" />
					<\%}%>
				</div>
				<\%}%>
			<\%}%>
		</section> 
	</script>
    <script id='tpl_ueditor' type="text/template">
		<section class="view-ueditor" data-type="<\%=type%>" data-id=<\%=id%>>
			<div><\%=data.html%></div>
		</section> 
	</script>
    <script id='tpl_coupon' type="text/template">
		<section class="view-coupon" data-type="<\%=type%>" data-id=<\%=id%>>
			<div>
				<ul id="couponList">
					<\%for(var i=0;i<data.list.length;i++){%>
					<li class="coupon <\%if(data["list"][i]["disabled"]){%>disabled<\%}%>" data-couponId="<\%=data["list"][i]["couponId"]%>">
						<p class="price"><strong><\%=data["list"][i]["title"]%></strong></p>
						<p class="time"><\%=data["list"][i]["starTime"]%> - <\%=data["list"][i]["endTime"]%></p>
					</li>
					<\%}%>
				</ul>
			</div>
		</section> 
	</script>
	<!-- 前端模板结束 -->
	<script type="text/javascript">
		//商品
		var pageId = GetQueryString("id");
		var pageType = GetQueryString("type");
    	$(document).delegate("#goodsList .goodDetail","click", function(){
    		goodsId = $.trim($(this).attr('data-goodId'));
    		activityId = $.trim($(this).attr('data-activityId'));
    		//location.href="{\"type\":\"goods\",\"pageId\":\""+pageId+"\",\"pageType\":\""+pageType+"\",\"goodsId\":\""+goodsId+"\",\"activityId\":\""+activityId+"\"}";
    		location.href="http://weixinm2c.taozhuma.com/product_detail?product_id="+goodsId+"&from=singlemessage&isappinstalled=1";
    	});
		//优惠卷
    	/* $(document).delegate("#couponList .coupon","click", function(){
    		couponId = $.trim($(this).attr('data-couponId'));
    		location.href="{\"type\":\"coupon\",\"couponId\":\""+couponId+"\"}";
    	}); */
    	
    	window.onload = function(){
            resizeIframe();
        }
        var resizeIframe=function(){
		    var bodyw=document.body.clientWidth;
		    for(var ilength=0;ilength<=document.getElementsByTagName("iframe").length;ilength++){
		        document.getElementsByTagName("iframe")[ilength].height = bodyw*9/16;//设定高度
		    }
		}
	</script>
</body>

</html>
