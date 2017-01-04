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
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马分销商</title>
<link rel="stylesheet" href="/css/files/global-min.css">
<link rel="stylesheet" href="/css/files/index-min.css">
<link rel="stylesheet" href="/css/files/index.css">
<style>.ww-light{overflow:hidden;}
.ww-block{display:block;margin-top:3px;}
.ww-inline{display:inline-block;vertical-align:text-bottom;}
.ww-light a{background-image: url("");background-image: -webkit-image-set(url("") 1x,url() 4x);background-image: -moz-image-set(url("") 1x,url("") 4x);background-image: -o-image-set(url("") 1x,url("") 4x);background-image: -ms-image-set(url("") 1x,url("") 4x);text-decoration:none!important;width:20px;height:20px;zoom:1;}
.ww-large a{width:67px;}a.ww-offline{background-position:0 -20px;}a.ww-mobile{background-position:0 -40px;}
.ww-small .ww-online{background-position:-80px 0;}.ww-small .ww-offline{background-position:-80px -20px;}
.ww-small .ww-mobile{background-position:-80px -40px;}.ww-static .ww-online{background-position:-110px 0;}
.ww-static .ww-offline{background-position:-110px -20px;}.ww-static .ww-mobile{background-position:-110px -40px;}
.ww-light a span{display:none;}</style>
<script type="text/javascript">
	$(function(){
		$.ajax({
		url:'getLogisticsInformation.do?orderPojo.id=${orderPojo.id}',
		type:'post',
		dataType: 'json',
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//alert("error.");
    	},
    	success: function(result){
	    		//alert(result);
	    		valueLogisticsInformation(result);
    		}
		});
	});
	
	//小计
	function add(){
	
		var price = document.getElementById("price");
		var num = document.getElementById("num");
		var sum = num*price;
		alert(sum);
		document.getElementById("sum").innerHTML = sum; 
	}
	
	 if(document.readyState=="complete"){  
	        add();  
	    }   
	
	function valueLogisticsInformation(result){
		var res = eval("("+result+")");
		//目前只能识别"中通"、"顺丰"、"韵达"
		if(res.com == "zhongtong"){
			var n = "中通";
		}
		if(res.com == "shunfeng"){
			var n = "顺丰";
		}
		if(res.com == "yunda"){
			var n = "韵达";
		}
		data = eval(res.data)  
		//for(var i=0;i<data.length;i++){
		//	alert(data[i].time);
		//}
		if(res==0){
			$("#order_list").html("");
			$("#order_list").append(
					"抱歉,目前还没有任何相关的物流信息! 您可以联系卖家确认是否发货了!!!");
		}else{
			$("#order_list").html("");
			$("#order_list").append(
					"<dl>"+
					"<dt>物流公司：</dt>"+
			        "<dd class='logistics-company' >"+n+"</dd>"+
			        "<dt>运单号码：</dt>"+
			        "<dd class='logistics-company' >"+res.nu+"</dd>"+
			        "<dt>物流跟踪：</dt>"+
			        "<dd class='logistics-company' >");
			for(var i=0;i<data.length;i++){
				$("#order_list").append(
					"&nbsp;"+
			        "<ol id='J_ExList' >"+
					 "<li class=''>"+data[i].time+"&nbsp;"+data[i].context+"&nbsp;"+"</li>"+
					 "</ol>"
		        );
			}
			$("#order_list").append(
				"</dd>"+"</dl>"
			);
		}
	
	}
	$(document).ready(function() {
		$("#sbutton").click(function(){
			document.getElementById("from1").submit();
		});
	});

</script>
<style type="text/css">
<!--
.STYLE2 {color: #FF0000; font-weight: bold; }
.STYLE3 {color: #FF0000; font-weight: bold; font-size: 18px; }
-->
</style>
<script type="text/javascript">
$(document).ready(function() {
		var h=(eval(document.getElementById('stockPrice')).value)*(eval(document.getElementById('refundNum')).value)
        document.getElementById("allprice").innerHTML=h.toFixed(1);
	});

</script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="top02-width">
	<div class="logo"></div>
	<div class="search"><input name="" type="text" value="搜索 商品/店铺" onfocus="this.value=''" onblur="if(this.value==''){this.value='搜索 商品/店铺'}" class="search-input"/><button type="submit" class="search-button">搜 索</button></div>
</div> 

	<div id="page">
		
		<div id="main">
												    <div class="step-list step-list-num4 _step-2">
    	    		    		    		<div class="step-u todo ">
    			<span class="step-i">
    				<em><i class="step-count">1</i></em>
    				买家申请退货退款
    				    				<span class="tail tail2"></span>
    				<span class="tail"></span>
    				    			</span>
    		</div>
    	    		    		    		<div class="step-u active">
    			<span class="step-i">
    				<em><i class="step-count">2</i></em>
    				卖家处理退货申请
    				    				<span class="tail tail2"></span>
    				<span class="tail"></span>
    				    			</span>
    		</div>
    	    		    		    		<div class="step-u todo">
    			<span class="step-i">
    				<em><i class="step-count">3</i></em>
    				买家退货给卖家
    				    				<span class="tail tail2"></span>
    				<span class="tail"></span>
    				    			</span>
    		</div>
    	    		    		    		<div class="step-u todo">
    			<span class="step-i">
    				<em><i class="step-count">4</i></em>
    				卖家确认收货，退款完成
    				    			</span>
    		</div>
    	    </div>

									</div>
		<div class="f-layout f-c2">
			
<div class="f-main">
    <div id="J_ApplyFormWrap">
    <!-- panel: -->
    
<div class="panel panel-form">
    
    
    <div class="panel-body clearfix">
    
        
        <div id="J_ModId_3" style="">
<form class="f-form" id="from1" action="orderWeb.do" novalidate="novalidate" method="post">
<input type="hidden" value="<s:property value='orderReturnPjWeb.stockPrice' />" name="orderDetail.stockPrice" id="stockPrice">
<input type="hidden" value="<s:property value='orderReturnPjWeb.refundNum' />" name="orderDetail.refundNum" id="refundNum"> 
  <div class="required-reminder">
      
  </div>
  <div class="bidi-viewer" data-view="form">
  
      
          <div class="row" id="bidi-form-548">
        <div class="auth-msg-warning-02" style="color:#df434e; font-size:14px;">您已成功申请退款,请耐心等待卖家处理申请退款</div>
    </div>
      
          
      
          <div class="row" id="bidi-form-549">
        <div class="f-label">
            退货退款原因：
        </div>
        <div class="f-controls">
          <span style="line-height:18px;">
          <s:if test='orderReturnPjWeb.refundType == 1'>
          	不喜欢
          </s:if>
          <s:if test='orderReturnPjWeb.refundType == 2'>
          	质量不好
          </s:if>
          <s:if test='orderReturnPjWeb.refundType == 3'>
          	尺码不对
          </s:if>
          <s:if test='orderReturnPjWeb.refundType == 4'>
          	颜色不对
          </s:if>
          <s:if test='orderReturnPjWeb.refundType == 5'>
          	其他
          </s:if>
          </span>          
            
            
      </div>
      
    </div>
      
          <div class="row" id="bidi-form-549">
        <div class="f-label">
            退货数量：
        </div>
        <div class="f-controls">
             <span style="line-height:18px;"><s:property value='orderReturnPjWeb.refundNum' /></span>    
            
      </div>
      
    </div>
    
     <!--     <div class="row" id="bidi-form-551">
        <div class="f-label">
            
                退款金额：
            
        </div>
        <div class="f-controls">
            
              
            <span class="units"><span id="allprice"></span></span>
            
		</div>
		
        <div class="f-controls hidden">
            <div class="auth-msg-warning"></div>
        </div>
    </div>-->
     
         
      
      
          <div class="row">
        <div class="f-label">
            退款说明：
          
        </div>
       <div class="f-controls">
            
              
            <span class="units"><s:property value='orderReturnPjWeb.refundReason' /></span>
            
		</div>
    </div>
           
        <div class="row row-submit">
  		<div class="f-controls">
    	<div class="btn btn-primary" id="sbutton">确定</div>
  	 </div>
  	 </div>     
  </div>
</form></div>
        <div class="loading" style="min-height: 200px; line-height: 200px; display: none;"></div>
    
    </div>
</div>
    
</div>
</div>

<div class="f-aside">
	<div id="J_CaseDetailBox">
    <!-- panel: -->
    <!--
<div class="panel panel-case-detail">
    
        <div class="panel-heading">
            
                <h2>订单信息</h2>
            
            
                
            
        </div>
   
    <div class="panel-body clearfix">
    
        
        <div id="J_ModId_1">
    <div class="case-detail">
        
          
              <div class="row" style="margin-bottom: 0;">
    <div class="f-label">
        <a href="" class="case-avatar"><img title="" class="goods-img" src=""></a>
    </div>
    <div class="f-desc">
        <span class="ellipsis"> <a class="text-link" href="http://trade.taobao.com/trade/detail/trade_snap.htm?trade_id=986047956092846&snapShot=true" target="_blank"><s:property value='orderDetailList.productName' /></a></span>
        <span class="ellipsis"></span>
        <span class="ellipsis">
            
        </span>
    </div>
</div>
 -->
<div class="divider-thin"></div>
       <!--
              <div class="row" style="margin-bottom: 0;">   
    <div class="f-label">卖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家：</div>
    <div class="f-desc"><s:property value='orderReturnPjWeb.shopName' /><s:property value='orderReturnPjWeb.productName' /></span></a></span></div>
</div>
          
              <div class="J_CaseInfo case-info">
        
            <div class="row ">
                <div class="f-label">订单编号：</div>
                
                    <div class="f-desc">
    
        
    
    <s:property value='orderReturnPjWeb.orderNo' /></a></div>
                
            </div>
        
            <div class="row ">
                <div class="f-label">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</div>
                
                    <div class="f-desc"><span class="color-price">128.00</span> 元 * 1(数量)</div>
                
            </div>
        
            <div class="row row-short">
                <div class="f-label">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：</div>
                
                    <div class="f-desc"><span class="color-price">0.00</span> 元</div>
                
            </div>
        -->
        <div class="divider-thin"></div>
    </div>
          
        
    </div>
</div>
        <div class="loading" style="display: none; min-height:200px;line-height:200px;"></div>
    
    </div>
</div>
   <div id="leftsead">
	<ul>
		
		<li><a href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4001501677&aty=0&a=0&curl=&ty=1"><img src="images/ll04.png" width="131" height="49" class="hides"/><img src="images/l04.png" width="47" height="49" class="shows" /></a></li>
		
	</ul>
</div>
<style type="text/css">
/* leftsead */
#leftsead{width:131px;height:143px;position:fixed;top:258px;right:0px;}
*html #leftsead{margin-top:258px;position:absolute;top:expression(eval(document.documentElement.scrollTop));}
#leftsead li{width:131px;height:60px;}
#leftsead li img{float:right;}
#leftsead li a{height:49px;float:right;display:block;min-width:47px;max-width:131px;}
#leftsead li a .shows{display:block;}
#leftsead li a .hides{margin-right:-143px;cursor:pointer;cursor:hand;}
#leftsead li a.youhui .hides{display:none;position:absolute;right:190px;top:2px;}
</style>
<script type="text/javascript">
$(document).ready(function(){

	$("#leftsead a").hover(function(){
		if($(this).prop("className")=="youhui"){
			$(this).children("img.hides").show();
		}else{
			$(this).children("img.hides").show();
			$(this).children("img.shows").hide();
			$(this).children("img.hides").animate({marginRight:'0px'},'slow'); 
		}
	},function(){ 
		if($(this).prop("className")=="youhui"){
			$(this).children("img.hides").hide('slow');
		}else{
			$(this).children("img.hides").animate({marginRight:'-143px'},'slow',function(){$(this).hide();$(this).next("img.shows").show();});
		}
	});

	$("#top_btn").click(function(){if(scroll=="off") return;$("html,body").animate({scrollTop: 0}, 600);});

});
</script> 
</div>
</div>



<div id="_umfp" style="display:inline;width:1px;height:1px;overflow:hidden"><object type="application/x-shockwave-flash" data="https://s.tbcdn.cn/g/security/umflash/fp.swf?v1=2" width="1" height="1" id="umFlash" class="umidWrapper"><param name="movie" value="https://s.tbcdn.cn/g/security/umflash/fp.swf?v1=2"><param name="allowScriptAccess" value="always"></object><embed height="1" width="1" id="umDcp" type="application/alidcp" class="umidWrapper">
<img src="./请填写申请服务表单_files/clear.png">
</div>

        </div>
	</div>
			
</body>
</html>
