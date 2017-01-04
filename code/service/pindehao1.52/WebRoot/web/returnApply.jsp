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
<title>淘竹马分销商</title>
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
		if (!confirm("确认？")) {
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
    	    		    		    		<div class="step-u active">
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
    
        <div class="panel-heading">
            
            
                <div id="J_ModId_2">
  <h2 class="inline-block font-normal">申请服务：</h2>


  
    <div id="ks-component266" class="dropdown-sort-select dropdown-sort-menu-button dropdown-sort-button" tabindex="0" role="button" title="" aria-describedby="" aria-expanded="false" aria-haspopup="true" style="width: 154px; -webkit-user-select: none;">
	
	
</div>
  
</div>
            
        </div>
    
    <div class="panel-body clearfix">
    
        
        <div id="J_ModId_3" style="">
<form class="f-form" action="addOrderRefundWeb.do" novalidate="novalidate" id="from1" method="post" enctype="multipart/form-data">
  <input type="hidden" value="<s:property value='orderReturnPjWeb.id' />" name="orderDetailPojo.id" id="orderDetailId">
  <input type="hidden" value="<s:property value="orderDetail.orderId"/>" name="orderDetail.orderId">
  <input type="hidden" value="<s:property value="orderDetail.id"/>" name="orderDetail.id">
  <input type="hidden" value="<s:property value="orderDetail.productId"/>" name="orderDetail.productId">
  <div class="required-reminder">
      <b class="color-price">*</b> 为必填项
  </div>
  <div class="bidi-viewer" data-view="form">
  
      
          <div class="row" id="bidi-form-548">
        <div class="auth-msg-warning">温馨提示:您只有一次售后维权的机会哦</div>
    </div>
        <div class="row" id="bidi-form-549">
             <div class="f-label">
            类型：<em>*</em>
        </div>
         <div class="f-controls">
       	<s:if test="os==1">
			<input type="hidden" value="<s:property value='os' />" name="orderRefund.type">
        	退款
        </s:if>
        <s:if test="os==2">
			<input type="hidden" value="<s:property value='os' />" name="orderRefund.type">
        	退货
        </s:if>
        <s:if test="os==3">
			<input type="hidden" value="<s:property value='os' />" name="orderRefund.type">
        	售后退货
        </s:if>
         </div>
      	</div>
          <div class="row" id="bidi-form-549">
        <div class="f-label">
            退货退款原因：<em>*</em>
        </div>
        <div class="f-controls">
            
              <select name="orderRefund.refundType" style="width:228px; height:28px; outline:none;">
			     <option value="1"> 不喜欢</option>
				 <option value="2"> 质量不好</option>
				 <option value="3"> 尺码不对</option>
				 <option value="4"> 颜色不对</option>
				 <option value="5"> 其他</option>
			  </select>        
      </div> 
    </div>
      
          <div class="row" id="bidi-form-549">
        <div class="f-label">
            退货数量：<em>*</em>
        </div>
        <div class="f-controls">
        <s:if test="os==1">
        <input type="hidden" value="<s:property value='orderReturnPjWeb.num' />" name="orderRefund.refundNum">
        <s:property value='orderReturnPjWeb.num' />
     	</s:if>
     	<s:else>
     	<select name="orderRefund.refundNum" style="width:228px; height:28px; outline:none;">
      		<s:bean name="org.apache.struts2.util.Counter" id="counter">
   			<s:param name="first" value="1" />
   			<s:param name="last" value="orderReturnPjWeb.num" />
   			<s:iterator>
     			counter:
     				<option value="<s:property/>"> <s:property/></option>

   			</s:iterator>
			</s:bean>
	 	</select>
	 	</s:else>   
      </div> 
    </div>
	
          <div class="row" id="bidi-form-551">
     <!--
        <div class="f-label">
            
                退款金额：<em>*</em>
            
        </div> -->
        <!--
        <div class="f-controls">
            
                <input class="input-l input-content-len-10 J_Plugin J_InputFee" type="text" name="refundFee" value="9.90" wrapper=".row" required="" required-msg="退款金额不能为空" min="0" min-msg="需要退款金额必须大于0" max="9.90" id="bidi-form-552" data-plugin="" verify-params="{&quot;maxFee&quot;:9.9,&quot;postFee&quot;:0}" max-msg="退款金额不可以超过9.90"> 
           		 <input class="input-l input-content-len-10 J_Plugin J_InputFee" type="text" name="refundFee" value="${orderReturnPjWeb.stockPrice*orderReturnPjWeb.num}"/>
            <span class="units">元</span>
            
               <span data-tpl="(最多&lt;strong&gt;{{maxFee}}&lt;/strong&gt;元，含发货邮费&lt;strong&gt;{{postFee}}&lt;/strong&gt;元)" id="bidi-form-553" data-init-text="(最多&lt;strong&gt;9.90&lt;/strong&gt;元，含发货邮费&lt;strong&gt;0.00&lt;/strong&gt;元)">(最多<strong>9.90</strong>元，含发货邮费<strong>0.00</strong>元)</span>
           <span class="desc"><a class="help-link" href="http://service.taobao.com/support/knowledge-1185685.htm" target="_blank"> 退款金额说明</a></span>
            
        <div class="msg-wrapper" style="display: none;"></div></div>
        <div class="f-controls hidden">
            <div class="auth-msg-warning"></div>
        </div>
           -->
    </div>
 
          <div class="row hidden" id="bidi-form-554">
        <div class="f-label">
            <span id="bidi-form-555">是否需要赔付</span>：<em>*</em>
        </div>
        <div class="f-controls" id="bidi-form-556" data-tpl="{f_unit_radio this}" msg-warning-wrapper="#msg-warning-wrapper-needCompensate">
            
    
        <span class="radio">
            <label data-tips="" class="J_Tips"><input type="radio" name="needCompensate" value="1" wrapper=".row" required="" msg-wrapper="#msg-wrapper-needCompensate" msg-warning-wrapper="#msg-warning-wrapper-needCompensate">是</label>
        </span>
    
        <span class="radio">
            <label data-tips="" class="J_Tips"><input type="radio" name="needCompensate" value="0" wrapper=".row" required="" msg-wrapper="#msg-wrapper-needCompensate" msg-warning-wrapper="#msg-warning-wrapper-needCompensate" checked="">否</label>
        </span>
  

        </div>
        
        
        <div class="f-controls" id="msg-warning-wrapper-needCompensate" style="display: none;"></div>
        <div class="f-controls" id="msg-wrapper-needCompensate" style="display: none;">
            <div class="auth-msg-error auth-msg">不可以为空！</div>
        </div> 
    </div>
      
          <div class="row hidden" id="bidi-form-557">
        <div class="f-label">
            
                赔付金额：<em>*</em>
            
        </div>
        <div class="f-controls">
            
                <input class="input-l input-content-len-10 J_Plugin J_InputFee" type="text" name="contractFee" value="" wrapper=".row" required="" required-msg="赔付金额不能为空" min="0" min-msg="赔付金额必须大于0" max="0.00" id="bidi-form-558" data-plugin="" verify-params="{&quot;maxCompensateFee&quot;:0}" max-msg="赔付金额不可以超过0.00">
            
            <span class="units">元</span>
            
                <span data-tpl="(最多&lt;strong&gt;{{maxCompensateFee}}&lt;/strong&gt;元) &lt;strong&gt;{{compensateDesc}}&lt;/strong&gt;" id="bidi-form-559" data-init-text="(最多&lt;strong&gt;0.00&lt;/strong&gt;元) &lt;strong&gt;&lt;/strong&gt;">(最多<strong>0.00</strong>元) <strong></strong></span>
            
            <span class="desc"><a class="J_Tips help-link" data-tips="&quot;1）凭证显示卖家承认商品材质不符或检测报告显示商品材质不符。2）买家申请面料材质不符、褪色/掉色，卖家同意退款协议。3）如果买家退款原因选择错误，将无法得到赔付。&quot;" href="http://service.taobao.com/support/seller/knowledge-5648163.htm" target="_blank">
    
        <i class="help"></i>
    
    如何获得赔付？</a></span>
            
        <div class="msg-wrapper" style="display: none;"></div></div>
        <div class="f-controls hidden">
            <div class="auth-msg-warning"></div>
        </div>
    </div>
      
          <div class="row">
        <div class="f-label">
            说明：
            <b class="counter num mutted J_Num" data-tpl="({count}/{max}字)">(0/200字)</b>
        </div>
        <div class="f-controls">
            <textarea class="textarea J_Description" name="orderRefund.refundReason" placeholder="" id="bidi-form-560"></textarea>
            
            
        <div class="msg-wrapper" style="display: none;"></div></div>
    </div>
      
          <div class="row"><!--
    <label class="f-label"><span>上传凭证：</span></label>
    <span class="f-controls uploader-btn-box">
        <a href="javascript:void(0)" class="g-u ks-uploader-button grayUploader-button"><span class="btn-text">选择凭证图片</span><div class="file-input-wrapper" style="overflow: hidden;"><input type="file" class="J_UploaderBtn file-input" value="选择凭证图片" text="选择凭证图片" name="orderRefund.images" max="5" maxsize="5120" allowexts="gif,jpeg,jpg,png,bmp" accept="image/*"></div></a>
        <input type="hidden" class="J_Urls" name="attachments" value="" id="bidi-form-561">
        
            
                <span class="desc"><a class="J_Tips help-link" data-tips="&quot;最多5张，每张不超过5M，支持GIF,JPEG,JPG,PNG,BMP格式&quot;">
    
        <i class="help"></i>
    
    上传帮助</a></span>-->
            
        <label class="f-label"><span>上传凭证：</span></label>
    <input type="file" class="J_UploaderBtn file-input" value="选择凭证图片" text="选择凭证图片" name="orderRefund.images" max="5" maxsize="5120" allowexts="gif,jpeg,jpg,png,bmp" accept="image/*">
        
    <div class="msg-wrapper" style="display: none;"></div><div class="msg-wrapper" style="display: none;"></div></span>
    <div class="f-controls J_WarningBox" style="display:none;">
        <div class="auth-msg-warning">最多5张，每张不超过5M，支持GIF,JPEG,JPG,PNG,BMP格式</div>
    </div>
    <div class="f-controls">
        <ul class="J_UploaderQueue ks-uploader-queue grayUploader-queue"></ul>
    </div>
</div>
      
          
      
          
      
          
      
          <div class="row row-submit">
  <div class="f-controls">
    <div class="btn btn-primary" id="sbutton">提交申请</div>
    
        


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
    
        <div class="panel-heading">
            
                <h2>订单信息</h2>
            
            
                
            
        </div>
    
    <div class="panel-body clearfix">
    
        
        <div id="J_ModId_1">
    <div class="case-detail">
        
          
              <div class="row" style="margin-bottom: 0;">
    <div class="f-label">
        <a href="" class="case-avatar"><img title="" class="goods-img" src="../upfiles/product/<s:property value='orderReturnPjWeb.productImages' />"></a>
    </div>
    <div class="f-desc">
        <span class="ellipsis"><s:property value='orderReturnPjWeb.productName' /></span>
        <span class="ellipsis"><!--颜色分类:1002蓝色--></span>
        <span class="ellipsis">
            
        </span>
    </div>
</div>
<div class="divider-thin"></div>
          
              <div class="row" style="margin-bottom: 0;">
    <div class="f-label">卖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家：</div>
    <div class="f-desc"><span class="f-l block"><s:property value='orderReturnPjWeb.shopName' /></span></div>
</div>
          
              <div class="J_CaseInfo case-info">
        
            <div class="row ">
                <div class="f-label">订单编号：</div>
                
                    <div class="f-desc">
    
        
    
    <s:property value='orderReturnPjWeb.orderNo' /></a></div>
                
            </div>
     
            <div class="row ">
                <div class="f-label">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</div>
                
                    <div class="f-desc"><span class="color-price"><s:property value='orderReturnPjWeb.stockPrice' /></span> 元 * 1(数量)</div>
                
            </div>
        
        
            <div class="row row-short">
                <div class="f-label"><!--邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：--></div>
                
                    <div class="f-desc"><span class="color-price"><!--0.00--></span><!-- 元--></div>
                
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
<div class="questions article">
  <h4>淘竹马发货须知</h4>
  <div id="J_question_400000001" class="J_question_box">

    <h5>1.关于平台</h5>
    <p>淘竹马是全球专业M2C玩具分销平台，主要提供各类玩具产品的一站式服务，即玩具产品从生产商直接到分销商的平台模式。所有产品都有品牌加盟商、品牌授权、3C认证证书、营业执照等资质证明材料。</p>

    <h5>2.发货须知</h5>
    <p>我们每天下午统一3点发货，超过时间次日发货，如有特殊要求，可及时联系客服协助解决。请各位分销商告知您的客户，在收货时当着快递人员的面先验货，确认无损再签收商品。如发现缺漏或损坏，可以拒签并尽快联系客服人员解决。</p>

  </div>
</div>


</body>
</html>
