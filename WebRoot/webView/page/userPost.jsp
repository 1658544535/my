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
<title>笔记详情</title>
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

.w-video{position:relative;}
.w-video video{position:absolute;top:0;left:0;width:100%;}
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
pre {
white-space: pre-wrap;
word-wrap: break-word;
}
</style>
</head>

<body>
    <div class="wraper">
    	<div class="w-title">
    		<h3 class="title">${userCirclePost.title}</h3>
    		<div class="info"></div>
    	</div>

    	<div class="w-img">
    		<s:if test="userCirclePost.banner != null  && userCirclePost.banner != ''">
    			<div class="img"><img src="<%=request.getContextPath() %>/upfiles/userCirclePost/${userCirclePost.banner}" /></div>
    		</s:if>
    		<s:if test="userCirclePost.image != null && userCirclePost.image != ''">
    			<div class="img"><img src="<%=request.getContextPath() %>/upfiles/userCirclePost/${userCirclePost.image}" /></div>
    		</s:if>
    		<div class="info"><pre>${userCirclePost.content}</pre></div>
    	</div>

    	<div class="w-video">
    		<s:if test="userCirclePost.videoUrl != null && userCirclePost.videoUrl != ''">
    			<iframe height="350" width="100%" src="${userCirclePost.videoUrl}" allowtransparency="true" allowfullscreen="true" scrolling="no" border="0" frameborder="0"></iframe>
    		</s:if>
    	</div>
    	<div id="w-btn" class="w-btn">
    		<a href='{"type":"age","value":"${fn:trim(userCirclePost.ageType)}"}'>${userCirclePost.ageTypeName}</a> 
    		<a href='{"type":"skill","value":"${fn:trim(userCirclePost.skillType)}"}'>${userCirclePost.skillTypeName}</a> 
    		<a href='{"type":"product","value":"${fn:trim(userCirclePost.productType)}"}'>${userCirclePost.productTypeName}</a> 
    		<a href='#'>${userCirclePost.optionTypeName}</a> 
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
