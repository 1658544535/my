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
<!--忽略将页面中的数字识别为电话号码-->
<meta content="telephone=no" name="format-detection">
<title>${activityTitlePojo.title}</title>
<style>
html,body{margin:0 auto;padding:0;font-family:Helvetica;font-size:12px;background:#eeefef;}
a,input{-webkit-tap-highlight-color:rgba(0,0,0,0);text-decoration:none;}
input{-webkit-appearance:none;border-radius:0;}

.banner,.banner img,.title,.title img{display:block;width:100%;}
.list_num3{margin-bottom:9px;padding:10px 5px 0;background:#fff;overflow:hidden;}
.list_num2{margin:0 5px;overflow:hidden;}
.list_num3 ul{margin:0;padding:0;overflow:hidden;}
.list_num2 ul{padding:0;margin:0;overflow:hidden;margin:0 0 -6px;}
.list_num3 li{float:left;width:33.3333%;list-style:none;box-sizing:border-box;padding:0 5px;}
.pro_info{display:block;padding-bottom:6px;background:#fff;color:#333;font-size:12px;text-decoration:none;}
.pro_info p{height:32px;line-height:16px;margin:14px 14px 0 12px;padding:0;overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 2;-webkit-box-orient: vertical;}
.list_num3 .pro_info p{margin:8px 0 0px 0;}
.pro_info p.tm{background:url(<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/webView/images/active_tm.png) no-repeat left 1px;background-size:40px auto;text-indent:44px;}
/*.pro_info p.tm{background:url(../images/active_tm.png) no-repeat left 1px;background-size:40px auto;text-indent:44px;}*/
.pro_price{padding:2px 0 10px;background:#fff;overflow:hidden;}
.list_num3 .pro_price{padding-left:0;}
.new_price{display:inline-block;vertical-align:bottom;padding-right:2px;color:#e73c7b;font-size:21px;line-height:20px;}
.list_num3 li .new_price{font-weight:normal;font-size:17px;}
.old_price{display:inline-block;vertical-align:bottom;color:#969696;font-size:13px;text-decoration:line-through;}
.pro_price font{float:right;background:#e73c7b;color:#fff;border-radius:10px;padding:0 8px;text-decoration:none;line-height:20px;}
.price_tips{display:inline-block;vertical-align:bottom;border:1px solid #e73c7b;color:#e73c7b;font-size:10px;border-radius:3px;padding:0 10px;text-decoration:none;line-height:20px;}

.list_num2 li{float:left;width:50%;list-style:none;box-sizing:border-box;padding:0 3px 6px;overflow:hidden;}
.list_num2 li .pro_info{padding-bottom:4px;}
.list_num2 li .pro_info p{font-size:15px;line-height:20px;height:40px;}
.list_num2 li .pro_price{padding:4px 10px 10px;}
.list_num2 li .pro_price font{float:none;display:block;margin:9px 0 0;padding:4px 0;border-radius:4px;font-size:13px;background:#e73c7b;color:#fff;text-align:center;}

.pro_info_img{position:relative;padding-bottom:100%;overflow:hidden;}
.pro_info_img img{position:absolute;width:100%;}

@media screen and (min-width:414px){
    html{font-size:13px;}
    .pro_info p{font-size:13px;line-height:20px;height:40px;}
    .list_num2 li .pro_info p{font-size:16px;line-height:21px;height:42px;}
    .pro_info p.tm{background-size:40px auto;text-indent:46px;background-position:3px auto;}
    /*.new_price{font-size:24px;}*/
    .list_num3 li .new_price{font-weight:normal;font-size:18px;}
    .old_price{font-size:14px;}
    .pro_price font{font-size:13px;}
    .list_num2 li .pro_price font{font-size:14px;font-weight:bold;}
}
@media screen and (max-width:350px){
    /*.new_price{font-size:18px;}*/
    .list_num3 li .new_price{margin-left:-3px;font-size:14px;}
    .list_num3 li .pro_price font{font-size:12px;padding:0 5px;}
    .list_num2 li .pro_info p{margin:10px 10px 0 10px;}
}
</style>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>

</head>

<body>
    <div class="banner">
        <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/${activityTitlePojo.banner}" alt="" />
    </div>

    <div class="title">
        <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/${activityTitlePojo.titlePic}" alt="" />
    </div>
<s:if test=" #activityProductList!=null ">
    <div class="list_num3">
        <ul>
        	<s:iterator value="activityProductList" id="id1">
            <li>
				<a onclick="skip(this,1,<s:property value='activityId'/>,<s:property value='productId'/>,'<s:property value="activityTitle"/>');" >
	                <span class="pro_info">
	                    <div class="pro_info_img"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" /></div>
	                    <p><s:property value='productName'/></p>
	                </span>
	                <div class="pro_price">
	                    <span class="new_price">￥<s:property value='activePrice'/></span>
	                    <!-- <span class="old_price">￥<s:property value='sellPrice'/></span> -->
	                    <font>抢购</font>
	                </div>
				</a>
            </li>
          </s:iterator>
        </ul>
    </div>
</s:if>
    
<s:if test=" #activityProductList1!=null ">
    <div class="list_num2">
        <ul>    
     <s:iterator value="activityProductList1" id="id2">  
            <li>           
				<a onclick="skip(this,2,<s:property value='activityId'/>,0,'<s:property value="activityTitle"/>')" >
	                <span class="pro_info">
	                    <div class="pro_info_img"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" /></div>
	                    <p class="tm"><s:property value='productName'/></p>
	                </span>
	                <div class="pro_price">
	                    <span class="new_price">￥<s:property value='activePrice'/></span>
	                    <span class="old_price">￥<s:property value='sellPrice'/></span>
	                   <font>立即抢购</font>
	                </div>
				</a>
            </li>   
        </s:iterator>      
        </ul>
    </div>
</s:if>

<s:if test=" #activityProductList2!=null ">
    <div class="title">
        <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/${activityTitlePojo.titlePicture}" alt="" />
    </div>
    <div class="list_num2">
        <ul>      
     <s:iterator value="activityProductList2" id="id2">
            <li>           
				<a onclick="skip(this,2,<s:property value='activityId'/>,0,'<s:property value="activityTitle"/>')" >
	                <span class="pro_info">
	                    <div class="pro_info_img"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" /></div>
	                    <p class="tm"><s:property value='productName'/></p>
	                </span>
	                <div class="pro_price">
	                    <span class="new_price">￥<s:property value='activePrice'/></span>
	                    <span class="old_price">￥<s:property value='sellPrice'/></span>
	                   <font>立即抢购</font>
	                </div>
				</a>
            </li>       
        </s:iterator>  
        </ul>
    </div>
</s:if>
 
 <s:if test=" #activityProductList3!=null ">
     <div class="title">
        <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/${activityTitlePojo.titlePhoto}" alt="" />
    </div>
    <div class="list_num2">
        <ul>     
     <s:iterator value="activityProductList3" id="id2"> 
            <li>           
				<a onclick="skip(this,2,<s:property value='activityId'/>,0,'<s:property value="activityTitle"/>')" >
	                <span class="pro_info">
	                    <div class="pro_info_img"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" /></div>
	                    <p class="tm"><s:property value='productName'/></p>
	                </span>
	                <div class="pro_price">
	                    <span class="new_price">￥<s:property value='activePrice'/></span>
	                    <span class="old_price">￥<s:property value='sellPrice'/></span>
	                   <font>立即抢购</font>
	                </div>
				</a>
            </li>      
        </s:iterator>   
        </ul>
    </div>
</s:if>   
</body>
<script>
		
function skip(obj,type,aid,pid,title){
	var tag = obj;
 	$.ajax({
		type: "post",
		url: "getActivityTimeById.do?activityTimePojo.id="+aid,
		dataType: 'json',
		async: false,
		success: function (data, status) { 			
		    var string='{"type":"'+type+'","activityId":"'+aid+'","productId":"'+pid+'","activityName":"'+title+'"';
			    if(data==1){
			    	string += ',"status":"1"}';
			    	tag.setAttribute("href",string);
                }else{
                	string += ',"status":"0"}';
			    	tag.setAttribute("href",string);
			    }
		},
		error: function(){
			var string='{"type":"'+type+'","activityId":"'+aid+'","productId":"'+pid+'","activityName":"'+title+'","status":"0"}';
			tag.setAttribute("href",string);
		}
	});	 
}

</script>  
</html>
