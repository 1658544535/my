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
	$(document).ready(function() {
		$("#sbutton").click(function(){
		if (!confirm("确认提交？")) {
            window.event.returnValue = false;
        }else{	
			document.getElementById("from1").submit();
			}
		});
	});
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
<!--
<div class="top02-width">
	<div class="logo"></div>
	<div class="search"><input name="" type="text" value="搜索 商品/店铺" onfocus="this.value=''" onblur="if(this.value==''){this.value='搜索 商品/店铺'}" class="search-input"/><button type="submit" class="search-button">搜 索</button></div>
</div> 
-->
	<div id="page">
		
		<div id="main">
												    <div class="step-list step-list-num4 _step-2">
    	    		    		    		<div class="step-u todo">
    			<span class="step-i">
    				<em><i class="step-count">1</i></em>
    				买家申请退货退款
    				    				<span class="tail tail2"></span>
    				<span class="tail"></span>
    				    			</span>
    		</div>
    	    		    		    		<div class="step-u  todo">
    			<span class="step-i">
    				<em><i class="step-count">2</i></em>
    				卖家处理退货申请
    				    				<span class="tail tail2"></span>
    				<span class="tail"></span>
    				    			</span>
    		</div>
    	    		    		    		<div class="step-u active">
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
    
     
    
    <div class="panel-body">
    
        
        <div id="J_ModId_3" style="">
<form class="f-form" id="from1"action="goReturnsSellerSuccessWeb.do" novalidate="novalidate" method="post">
  <input type="hidden" value="<s:property value="orderDetail.id"/>" name="orderDetail.id">
  <input type="hidden" value="<s:property value="orderDetail.orderId"/>" name="orderDetail.orderId">
  <input type="hidden" value="<s:property value="orderDetail.productId"/>" name="orderDetail.productId">
  <div class="bidi-viewer" data-view="form">
  
      
    <div class="row" id="bidi-form-549">
        <div class="f-label">退货地址：<em>*</em></div>
        <div class="f-controls"> <span class="units">广东省汕头市澄海区莱美路宇博电子商务园4楼群宇互动科技有限公司</span></div>
    </div>
    <div class="row" id="bidi-form-549">
      <div class="f-label">联系人：<em>*</em></div>
        <div class="f-controls"> <span class="units">淘竹马售后组</span></div>
    </div>
    <div class="row" id="bidi-form-549">
      <div class="f-label">联系电话：<em>*</em></div>
        <div class="f-controls"> <span class="units">0754-88098777</span></div>
    </div>
          <div class="row" id="bidi-form-549">
        <div class="f-label">
            物流公司：<em>*</em>
        </div>
        <div class="f-controls">
            
              <select name="orderRefund.logType" style="width:228px; height:28px; outline:none;">
			     <option value="shunfeng">
		            	顺丰快递
		            </option>
		            <option value="shentong">
		            	申通快递
		            </option>
		            <option value="zhongtong">
		            	中通快递
		            </option>
		            <option value="yuantong">
		            	圆通快递
		            </option>
		            <option value="huitong">
		            	汇通快递
		            </option>
		            <option value="tiantian">
		            	天天快递
		            </option>
		             <option value="yunda">
		            	韵达快递
		            </option>
		             <option value="dhl">
		            	DHL快递
		            </option>
		             <option value="zhaijisong">
		            	宅急送
		            </option>
		             <option value="debang">
		            	德邦物流
		            </option>
		            <option value="ems">
		            	EMS国内
		            </option>
		            <option value="eyoubao">
		            	E邮宝
		            </option>
		            <option value="guotong">
		            	国通快递
		            </option>
		            <option value="longbang">
		            	龙邦速递
		            </option>
		            <option value="lianbang">
		            	联邦快递
		            </option>
		            <option value="tnt">
		            	TNT快递
		            </option>
		            <option value="xinbang">
		            	新邦物流
		            </option>
		            <option value="zhongtie">
		            	中铁快运
		            </option>
		            <option value="zhongyou">
		            	中邮物流
		            </option>
			  
			  
			  </select>
            
            
            
            
      </div>
       <div class="row" id="bidi-form-549">
        <div class="f-label">
            运单号：<em>*</em>
        </div>
        <div class="f-controls">
            
            <input type="text" name="orderRefund.logistics" id="logistics"></div>      
            
            
            
      </div>
    </div>
          <div class="row" id="bidi-form-551">
      
        <div class="f-controls">
            
        <div class="msg-wrapper" style="display: none;"></div></div>
        <div class="f-controls hidden">
            <div class="auth-msg-warning"></div>
        </div>
    </div>
      
          <div class="row hidden" id="bidi-form-554">
        <div class="f-label">
            <span id="bidi-form-555">是否需要赔付</span>：<em>*</em>
        </div>
       
        
        
        <div class="f-controls" id="msg-warning-wrapper-needCompensate" style="display: none;"></div>
        <div class="f-controls" id="msg-wrapper-needCompensate" style="display: none;">
            <div class="auth-msg-error auth-msg">不可以为空！</div>
        </div> 
    </div>
      
         
      <!--
          <div class="row">
        <div class="f-label">
           发货说明：
          
        </div>
        <div class="f-controls">
            <textarea class="textarea J_Description" name="description" placeholder="" id="bidi-form-560"></textarea>
            
            
        <div class="msg-wrapper" style="display: none;"></div></div>
    </div>
      
          <div class="row">
    <label class="f-label"><span>上传凭证：</span></label>
    <span class="f-controls uploader-btn-box">
        <a href="javascript:void(0)" class="g-u ks-uploader-button grayUploader-button"><span class="btn-text">选择凭证图片</span><div class="file-input-wrapper" style="overflow: hidden;"><input type="file" class="J_UploaderBtn file-input" value="选择凭证图片" text="选择凭证图片" name="imgFile" max="5" maxsize="5120" allowexts="gif,jpeg,jpg,png,bmp" accept="image/*"></div></a>
        <input type="hidden" class="J_Urls" name="attachments" value="" id="bidi-form-561">      
  </span>
</div>
      -->
          
      
          
      
          
      
          <div class="row row-submit">
  <div class="f-controls">
    <div class="btn btn-primary" id="sbutton" style="padding-left:30px; padding-right:35px;">确定</div>
    <a class="btn btn-link J_IBtn" href="orderWeb.do">取消并返回</a>
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
    <!--
        <div class="panel-heading">
            
                <h2>订单信息</h2>
            
            
                
            
        </div>
    -->
    <div class="panel-body clearfix">
    
      <!--  
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
    <div class="f-desc"><span>旺旺在线</span></a></span></div>
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
    -->
</div>
</div>



<div id="_umfp" style="display:inline;width:1px;height:1px;overflow:hidden"><object type="application/x-shockwave-flash" data="https://s.tbcdn.cn/g/security/umflash/fp.swf?v1=2" width="1" height="1" id="umFlash" class="umidWrapper"><param name="movie" value="https://s.tbcdn.cn/g/security/umflash/fp.swf?v1=2"><param name="allowScriptAccess" value="always"></object><embed height="1" width="1" id="umDcp" type="application/alidcp" class="umidWrapper">
<img src="./请填写申请服务表单_files/clear.png">
</div>

        </div>
	</div>

</body>
</html>
