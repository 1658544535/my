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
<title>专题详情</title>
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
	text-align: center;
	padding: 10px 0 5px;
	background: #fff;
}
.w-btn a{
	display: inline-block;
	margin: 5px 5px;
	padding: 4px 12px;
	font-size: 12px;
	color: #666;
	border: 1px solid #666;
	border-radius:4px;
}
</style>
</head>

<body>
    <div class="wraper">
    	<div class="w-title">
    		<h3 class="title">${platformSpecialPojo.title}</h3>
    		<div class="info"></div>
    	</div>

    	<div class="w-img">
    		<div class="img"><img src="<%=request.getContextPath() %>/upfiles/platformSpecial/${platformSpecialPojo.banner}" /></div>
    		<div class="info">${platformSpecialPojo.content}</div>
    	</div>

    	<!-- <div class="w-video">
    		<iframe height="300" width="375" src="${userCirclePost.videoUrl}" allowtransparency="true" allowfullscreen="true" allowfullscreeninteractive="true" scrolling="no" border="0" frameborder="0"></iframe>
    	</div> -->
    	
    	<div id="w-btn" class="w-btn">
    		<a href='{"type":"age","value":"${fn:trim(platformSpecialPojo.ageType)}"}'>${platformSpecialPojo.ageTypeStr}</a> 
    		<a href='{"type":"skill","value":"${fn:trim(platformSpecialPojo.skillType)}"}'>${platformSpecialPojo.skillTypeStr}</a> 
    		<a href='{"type":"product","value":"${fn:trim(platformSpecialPojo.productType)}"}'>${platformSpecialPojo.productTypeStr}</a> 
    		<a href='#'>${platformSpecialPojo.optionTypeStr}</a> 
    	</div>
    </div>

    <script>
        window.onload = function(){
            var oBtn = document.getElementById("w-btn").getElementsByTagName("a");
            for(var i=0; i<oBtn.length; i++){
                var aBtnHtml = oBtn[i].innerHTML;
                if(aBtnHtml == ''){
                    oBtn[i].style.display="none";
                }
            }
        }
    </script>
</body>
</html>
