<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=0" name="viewport">
<!--IOS中Safari允许全屏浏览-->
<meta content="yes" name="apple-mobile-web-app-capable">
<!--IOS中Safari顶端状态条样式-->
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<!--忽略将页面中的数字识别为电话号码-->
<meta content="telephone=no" name="format-detection">
<!--去除Android平台中对邮箱地址的识别-->
<meta content="email=no" name="format-detection" />

<title>${scenePojo.name}</title>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" href="/ueditor1_4_3-utf8-jsp/third-party/video-js/video-js.min.css" type="text/css"  />
<script src="/ueditor1_4_3-utf8-jsp/third-party/video-js/video.js"></script>
<style>
html,body,ul,li,h3,p{margin:0;padding:0;box-sizing:border-box;}
body{background:#f1f1f1;font-family:"Microsoft YaHei";}
a:link,a:active{color:#444343;text-decoration:none;box-sizing:border-box;-webkit-tap-highlight-color:rgba(0,0,0,0);}
a:hover{text-decoration:none;}
li{list-style:none;box-sizing:border-box;}
img{border:0;vertical-align:middle;box-sizing:border-box;}
.wrapper{position:relative;width:100%;height:100%;margin:0 auto;box-sizing:border-box;}
.top{background:#fff;overflow:hidden;}
.top img{width:100%;}
.top_info{margin:0 10px;font-size:16px;line-height:28px;color:#727171!important;text-align: justify;}
.top_info *{font-size:16px!important;color:#727171!important;}
.top_info p{text-indent:0em!important;}
.top_info p img{margin-left:0em!important;}
.top_price{margin:2% 4% 0;font-size:14px;color:#666;}
.top_price{position:relative;padding:0 10px;border-left:4px solid #d2d4d3;}
.top_nowPrice{font-size:20px;color:#ce0549;}
.top_oldPrice{font-size:14px;color:#999;}
.top_oldPrice font{text-decoration:line-through;}
.top_price strong{position:absolute;right:0;top:5px;font-size:15px;color:#ce0549;border:2px solid #ce0549;border-radius:20px;padding:5px 20px;font-weight:normal;}
.top_info img{max-width:100%;height:auto;}
.list{width:98%;margin:2% auto 4%;font-size:14px;overflow:hidden;}
.list li{float:left;width:48%;margin:1%;border:1px solid #ddd;background-color:#fff;}
.list li div.pro_pic{position:relative;padding-bottom:100%;overflow:hidden;}
.list li div.pro_pic img{position:absolute;width:100%;left:0;top:0;}
.list_big{background:#fff;}
.list_big li{padding:0 0 2%;}
.list_big li .list_big_title{padding:2% 4% 2%;font-size:18px;color:#222;font-weight:normal;line-height:26px;}
.list_big li .list_big_title i{float:left;margin:2px 5px 0 0;border-radius:50%;width:22px;height:22px;background:#ce0549;color:#fff;font-style:normal;text-align:center;line-height:22px;}
.list_big li img{display:block;width:90%;margin:0 auto;}
.pro_title{height:40px;margin:4%;overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 2;-webkit-box-orient: vertical;line-height:20px;}
.pro_info{text-align:right;padding:4%;color:#999;height:20px;}
.pro_price{float:left;color:#ce0549;font-size:16px;}
</style>
<%--
<script>
    $(function(){   
        $("#youkuplayer").width($(window).width());
        $("#youkuplayer").height($(window).width()*3/4);
        $("#youkuplayer").wrap('<div style="width:auto;height:'+$(window).width()*3/4+'px"></div>')
        $("#youkuplayer").css({"position":"absolute","top":"0","left":"0"});

/*      var messagingIframe;
        messagingIframe = document.createElement('iframe');
        messagingIframe.style.display = 'none';
        document.documentElement.appendChild(messagingIframe);
        $(".list li a,.top .top_price a").click(TestIOSJS);
        function TestIOSJS(){
            var id = $(this).attr("id");
            messagingIframe.src = id;
            return false; 
        }; */
    });      
</script>
<script type="text/javascript" src="http://player.youku.com/jsapi">
var video = '${scenePojo.video}';
if(video!=''){
    player = new YKU.Player('youkuplayer',{
        styleid: '0',
        client_id: '2030bbd6350713e8',
        vid: video
        });
}
</script>
--%>
</head>

<body>
<div class="wrapper">
    <div class="top">
    <!-- <img src="../upfiles/scene/${scenePojo.image}">-->
   <div class="top_info">${scenePojo.introduction}</div> 
    </div>
    <ul class="list_big">
    <s:iterator value="sceneProductPojos1" status='item'>
        <li><a href='{"type":"1","sceneId":"${scenePojo.activityId}","productId":"${productId}","detail":"1"}'>
            <h3 class="list_big_title"><i><s:property value='#item.count'/> </i><p style="margin-left:25px;">${title}</p></h3>
            <p class="top_info">${introduction}</p> 
            <img src="upfiles/product/${image}" />
            <div class="top_price">
                <span class="top_nowPrice">￥${scenePrice}</span>
                <br>
                <span class="top_oldPrice">&nbsp;价格<font>￥${sellPrice}</font></span>
                <strong>去购买</strong>
            </div>
        </a></li>
     </s:iterator>
    </ul>
    <ul class="list">
    <s:iterator value="sceneProductPojos0">
        <li>
        <a href='{"type":"1","sceneId":"${scenePojo.activityId}","productId":"${productId}","detail":"1"}'>
                <div class="pro_pic"><img src="upfiles/product/${image}" /></div>
                <p class="pro_title">${title}</p>
                <div class="pro_info">
                    <span class="pro_price">￥${scenePrice}</span>
                </div>
            </a>
        </li>
     </s:iterator>
    </ul>    
    
</div>
<script>
    $(function(){
        $(".video-js").parent().css({
            "width": $(window).width(),
            "marginLeft": "-10px",
            "margin-bottom": "10px"
        });
    });
</script>
</body>
</html>
