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
<link rel="stylesheet" href="/css/files/global-min.css">
<link rel="stylesheet" href="/css/files/index-min.css">
<link rel="stylesheet" href="/css/files/index.css">
<script type="text/javascript" src="js/_head.js"></script>
<style>.ww-light{overflow:hidden;}
.ww-block{display:block;margin-top:3px;}
.ww-inline{display:inline-block;vertical-align:text-bottom;}
.ww-light a{background-image: url("");background-image: -webkit-image-set(url("") 1x,url() 4x);background-image: -moz-image-set(url("") 1x,url("") 4x);background-image: -o-image-set(url("") 1x,url("") 4x);background-image: -ms-image-set(url("") 1x,url("") 4x);text-decoration:none!important;width:20px;height:20px;zoom:1;}
.ww-large a{width:67px;}a.ww-offline{background-position:0 -20px;}a.ww-mobile{background-position:0 -40px;}
.ww-small .ww-online{background-position:-80px 0;}.ww-small .ww-offline{background-position:-80px -20px;}
.ww-small .ww-mobile{background-position:-80px -40px;}.ww-static .ww-online{background-position:-110px 0;}
.ww-static .ww-offline{background-position:-110px -20px;}.ww-static .ww-mobile{background-position:-110px -40px;}
.ww-light a span{display:none;}</style>
<title>淘竹马分销商</title>
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
	
</script>
<style type="text/css">
<!--
.STYLE2 {color: #FF0000; font-weight: bold; }
.STYLE3 {color: #FF0000; font-weight: bold; font-size: 18px; }
-->
</style>
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
    	    		    		    		<div class="step-u todo">
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
    	    		    		    		<div class="step-u  active">
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
<form class="f-form" action="" novalidate="novalidate">
  
  <div class="bidi-viewer" data-view="form">
  
      
          <div class="row" id="bidi-form-548">
        <div class="auth-msg-warning-02" style="line-height:30px; background:none; border:none; font-weight:normal; color:#000;" >退款成功
			<p>&bull;&nbsp;退款金额：<font color="#df434e">9.90</font>&nbsp;元</p>
		</div>
		<div class="f-controls" style="margin-left:30px; margin-top:15px;">
    		<div class="btn " id="bidi-form-562">查看钱款去向</div>    
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
    
<div class="panel panel-case-detail">
    
        <div class="panel-heading">
            
                <h2>订单信息</h2>
            
            
                
            
        </div>
    
    <div class="panel-body clearfix">
    
        
        <div id="J_ModId_1">
    <div class="case-detail">
        
          
              <div class="row" style="margin-bottom: 0;">
    <div class="f-label">
        <a href="http://tosp.taobao.com/leader/apply.htm?spm=0.0.0.0.uFKomu&orderId=986047956092846&bizClaimType=3#sku" class="case-avatar"><img title="正品韩版男士短款钱包横款 超薄学生青年商务小皮夹韩版个性潮" class="goods-img" src="./请填写申请服务表单_files/TB26vDRbVXXXXaLXXXXXXXXXXXX_!!324726511.jpg_sum"></a>
    </div>
    <div class="f-desc">
        <span class="ellipsis"> <a class="text-link" href="http://trade.taobao.com/trade/detail/trade_snap.htm?trade_id=986047956092846&snapShot=true" target="_blank">正品韩版男士短款钱包横款 超薄学生青年商务小皮夹韩版个性潮</a></span>
        <span class="ellipsis">颜色分类:1002蓝色</span>
        <span class="ellipsis">
            
        </span>
    </div>
</div>
<div class="divider-thin"></div>
          
              <div class="row" style="margin-bottom: 0;">
    <div class="f-label">卖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家：</div>
    <div class="f-desc"><!--<span class="f-l block">可爱的vs宝宝</span><span class="ww-light ww-large" data-nick="可爱的vs宝宝"><a href="http://amos.alicdn.com/getcid.aw?v=3&site=cntaobao&groupid=0&s=1&fromid=cntaobao%E8%8E%B4%E5%8D%9C%E6%98%AF%E9%9D%93%E4%BB%94&uid=%E5%8F%AF%E7%88%B1%E7%9A%84vs%E5%AE%9D%E5%AE%9D" target="_blank" class="ww-inline ww-online" title="点此可以直接和卖家交流选好的宝贝，或相互交流网购体验，还支持语音视频噢。">--><span>旺旺在线</span></a></span></div>
</div>
          
              <div class="J_CaseInfo case-info">
        
            <div class="row ">
                <div class="f-label">订单编号：</div>
                
                    <div class="f-desc">
    
        
    
    986047956092846</a></div>
                
            </div>
        
            <div class="row ">
                <div class="f-label">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</div>
                
                    <div class="f-desc"><span class="color-price">128.00</span> 元 * 1(数量)</div>
                
            </div>
        
            <div class="row row-short">
                <div class="f-label">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：</div>
                
                    <div class="f-desc"><span class="color-price">0.00</span> 元</div>
                
            </div>
        
        <div class="divider-thin"></div>
    </div>
          
        
    </div>
</div>
        <div class="loading" style="display: none; min-height:200px;line-height:200px;"></div>
    
    </div>
</div>
    
</div>
</div>



<div id="_umfp" style="display:inline;width:1px;height:1px;overflow:hidden"><object type="application/x-shockwave-flash" data="https://s.tbcdn.cn/g/security/umflash/fp.swf?v1=2" width="1" height="1" id="umFlash" class="umidWrapper"><param name="movie" value="https://s.tbcdn.cn/g/security/umflash/fp.swf?v1=2"><param name="allowScriptAccess" value="always"></object><embed height="1" width="1" id="umDcp" type="application/alidcp" class="umidWrapper">
<img src="./请填写申请服务表单_files/clear.png">
</div>

        </div>
	</div>

</body>
</html>
