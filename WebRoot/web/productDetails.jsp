<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<link href="/css/product_details.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>

<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/tipswindown.js"></script> 
<link rel="stylesheet" href="/css/tipswindown.css" type="text/css" media="all" />
<script type="text/javascript" src="js/_head.js"></script>
<script>
$(document).ready(function(){
	$('.product_details-Part02-list li').hover(function(){
		$(this).find('.iconx').show();
	},function(){
		$(this).find('.iconx').hide();
	});
	
	$( "#tabs" ).tabs();
	
	$('.product_details-Part01-Details-L-Pic02-span').hover(function(){
		$('#main_img').attr('src',$(this).find('img').attr('src'));
	});
});
</script>
<script type="text/javascript">  
$(document).ready(function(){// DOM的onload事件处理函数  
        $("#jian").click(function(){// 当按钮submit被点击时的处理函数 
        	z_number1();// submit别点击时执行postdata函数 	
        });  
         $("#jia").click(function(){// 当按钮submit被点击时的处理函数 
        	z_number();// submit别点击时执行postdata函数 	
        }); 
 function z_number1(){
	var z_num=$("#z_num").val();
    z_num=parseInt(z_num)
	if(z_num>1){
		z_num=z_num-1;
	}
	document.getElementById("z_num").value = z_num;
}
function z_number(){
	var z_num=$("#z_num").val();
	z_num=parseInt(z_num)
	z_num=z_num+1;
	document.getElementById("z_num").value = z_num;
}
}); 

$(function() {
	getTypes(${productPojo.id});
});
function getTypes(pid){
		$.ajax({
		url:'getProductPrices.do?productPojo.id='+pid,
		type:'post',
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown){
		alert("起批量加载失败");
    	},
    	success: function(result){
    		valuePriceType(pid,result);
    	}
		});
	}
	
	function valuePriceType(pid,result){
		var data = eval(result);
		for(var i=0;i<data.length;i++){
		    var number="";
		    if(data[i].max>0){
		    number=data[i].min+"-"+data[i].max;
		    }else{
		    number="≥"+data[i].min;
		    }
		    $("#numberArr"+i).html("<b>"+number+" ${productPojo.unitName}</b>");
		    $("#priceArr"+i).html("￥"+parseFloat(data[i].price).toFixed(1));
		}
	}
</script>
<script type="text/javascript">  
$(document).ready(function(){// DOM的onload事件处理函数  
//alert(window.location.href);
    $("#addcart").click(function(){// 当按钮submit被点击时的处理函数 
    	postdata(0);// submit别点击时执行postdata函数 	
    }); 
    $("#tobuy").click(function(){// 当按钮submit被点击时的处理函数 
    	postdata(1);// submit别点击时执行postdata函数 	
    }); 
    
    $("#addcart0").click(function(){// 当按钮submit被点击时的处理函数 
    	postdata(0);// submit别点击时执行postdata函数 	
    }); 
    $("#tobuy0").click(function(){// 当按钮submit被点击时的处理函数 
    	postdata(1);// submit别点击时执行postdata函数 	
    }); 
    
    $("#addcart_tishi").click(function(){// 当按钮submit被点击时的处理函数 
    	postdata_tishi(0);// submit别点击时执行postdata函数 	
    }); 
    $("#tobuy_tishi").click(function(){// 当按钮submit被点击时的处理函数 
    	postdata_tishi(1);// submit别点击时执行postdata函数 	
    }); 
    
    $("#addfenxiao").click(function(){
       fenxiao();
    });
    $("#addfenxiao0").click(function(){
       fenxiao();
    });
    $("#addfenxiao_tishi").click(function(){
       fenxiao();
    });
});  


function fenxiao(){// 提交数据函数  
        $.ajax({
		url:'addFenxiao.do?userConsumerCollectPojo.productId='+$("#pid").val(),
		type:'post',
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown){
		//alert("添加分销失败");
		tipsWindown("提示","text:该产品添加分销失败","250","50","true","","true","msg");
    	},
    	success: function(result){
    		if(result == 1){
    			//alert("添加分销成功");
    			tipsWindown("提示","text:该产品添加分销成功","250","50","true","","true","msg");
    		}else if(result == 3){
    			//window.location.replace("/doLoginWeb.do?url="+window.location.href);
		               window.location.href="/doLoginWeb.do?url="+window.location.href;
    		}else if(result == 4){
    			//alert("该产品已在分销产品库中");
    			tipsWindown("提示","text:该产品已在分销产品库中","250","50","true","","true","msg");
    		}else if(result == 5){
    			//alert("请先申请成为分销商");
    			tipsWindown("提示","text:请先申请成为分销商","250","50","true","","true","msg");
    		}else{
    			//alert("添加分销失败");
    			tipsWindown("提示","text:添加分销失败","250","50","true","","true","msg");
    		}
    	}
		});
}  
function postdata(tp){// 提交数据函数  
//var postage=$('#fareSelect option:selected').val();
//if(postage==""||postage==null){
//postage=0;
//}
//alert("cart.num="+$("#z_num").val()+"&productPojo.id="+$("#pid").val());
    $.ajax({// 调用jquery的ajax方法  
        type:"GET",// 设置ajax方法提交数据的形式  
        url:"/addCartWeb.do",// 把数据提交到ok.php    
        dataType:'json',       
        data: "cart.num="+$("#z_num").val()+"&productPojo.id="+$("#pid").val(),  
        success:function(data){// 提交成功后的回调，msg变量是ok.php输出的内容 
            var d = eval("("+data+")");
            if(tp==1){
	            if(d.text==1){
		          //    window.location.replace("/cartWeb.do");
		           window.location.href="/cartWeb.do?url="+window.location.href;
		        }else if(d.text==2){
		               //alert("加入购物车失败");
		               tipsWindown("提示","text:加入购物车失败!","250","50","true","","true","msg");
		        }else if(d.text==0){
		               //window.location.replace("/doLoginWeb.do?url="+window.location.href);
		               window.location.href="/doLoginWeb.do?url="+window.location.href;
		        }
            }else{
	            if(d.text==1){
		               tipsWindown("<s:property value="productPojo.productName.substring(0,16)+'...'" />成功加入购物车","url:get?/cartShow.do","420","150","true","","true","text");
		        }else if(d.text==2){
		               //alert("加入购物车失败");
		               tipsWindown("提示","text:加入购物车失败.","250","50","true","","true","msg");
		        }else if(d.text==0){
		               //window.location.replace("/doLoginWeb.do?url="+window.location.href);
		               window.location.href="/doLoginWeb.do?url="+window.location.href;
		        }
            }
	        
              
        },
        error: function(){
		//alert("加入购物车失败");
		tipsWindown("提示","text:加入购物车失败","250","50","true","","true","msg");
    	} 
    });  
}  

function postdata_tishi(tp){// 提交数据函数  
  tipsWindown("提示","text:您没有权限购买商品，只有分销商才可以在本网站购买商品","250","100","true","","true","msg");
} 
function collect(pid){
 $.ajax({// 调用jquery的ajax方法  
        type:"GET",// 设置ajax方法提交数据的形式  
        url:"/addProductCollect.do",// 把数据提交到ok.php       
        data: "productPojo.id="+pid,  
        success:function(data){// 提交成功后的回调，msg变量是ok.php输出的内容 
            if(data == 1){
    			//alert("该产品收藏成功");
    			tipsWindown("提示","text:该产品收藏成功","250","50","true","","true","msg");
    		}else if(data == 3){
    			//window.location.replace("/doLoginWeb.do?url="+window.location.href);
		               window.location.href="/doLoginWeb.do?url="+window.location.href;
    		}else if(data == 4){
    			//alert("该产品已在产品收藏库中");
    			tipsWindown("提示","text:该产品已在产品收藏库中","250","50","true","","true","msg");
    		}else{
    			//alert("该产品收藏失败");
    			tipsWindown("提示","text:该产品收藏失败","250","50","true","","true","msg");
    		}
        },
        error: function(){
		//alert("收藏商品失败");
		tipsWindown("提示","text:收藏商品失败","250","50","true","","true","msg");
    	} 
    });  
	
}

function chuanHuoSelect(selObj){


  var selectd = selObj.options[selObj.selectedIndex].value;
 // alert(selectd);
 $("#fare").html(selectd);
}

</script> 
<script type="text/javascript">  
function shopCo(val){
	$.ajax({// 调用jquery的ajax方法  
        type:"GET",// 设置ajax方法提交数据的形式  
        url:"/addUserShopCollect.do",// 把数据提交到ok.php       
        data: "userShopCollect.shopId="+val,  
        success:function(data){// 提交成功后的回调，msg变量是ok.php输出的内容 
            if(data == 1){
    			//alert("店铺收藏成功");
    			tipsWindown("提示","text:店铺收藏成功","250","50","true","","true","msg");
    		}else{
    			//alert("该店铺收藏过了");
    			tipsWindown("提示","text:该店铺收藏过了","250","50","true","","true","msg");
    		}
        },
        error: function(){
		//alert("请登录后收藏店铺");
        	//window.location.replace("/doLoginWeb.do?url="+window.location.href);
		               window.location.href="/doLoginWeb.do?url="+window.location.href;
    	} 
    });  
}
</script> 
<title>${productPojo.productName} | 淘竹马玩具分销平台</title>
</head>

<body class="body">
<jsp:include page="header.jsp"></jsp:include>

<div class="clear"></div>
<div class="delangyes">
	<div id="delang"><a href="/shopDetailsWeb.do?shop.id=${shopPojo.id}" >
        <div class="topDelang" style="background:url(<s:if test="shopPojo.topImage!=null">/upfiles/shop/${shopPojo.topImage}</s:if><s:else>images/topImages.jpg</s:else>) no-repeat top center;z-index:100;"></div>
    	</a>
    </div>
</div>
<%-- <div class="product_details-logoBg">
	<div class="product_details-logo"><a href="/shopDetailsWeb.do?shop.id=${shopPojo.id}" ><img src="/upfiles/shop/${shopPojo.images}" alt="" width="145" height="145" /> ${shopPojo.name}</a></div>
</div> --%>

<div class="product_details-Part01">
	<span class="product_details-Part01-title">${productPojo.productName}</span>
    <div class="product_details-Part01-Details">
    	<div class="product_details-Part01-Details-L">
        	<div class="product_details-Part01-Details-L-Pic"><img src="/upfiles/product/${productPojo.image}" alt="" width="382" height="382" border="0" id="main_img" /></div>
           <div class="product_details-Part01-Details-L-Pic02">
            	<s:iterator value="productFocusImageslist">
                <span class="product_details-Part01-Details-L-Pic02-span"><a href="#"><img src="/upfiles/productFocusImages/<s:property value='images'/>" alt="" width="42" height="42" border="0"/></a></span>
                </s:iterator>
                
            </div>
            <div class="product_details-Part01-Details-L-collect"><a onclick="collect(${productPojo.id});"><img src="images/product_details_11.jpg" alt="" width="22" height="22" /></a> <a onclick="collect(${productPojo.id});">收藏产品（${fcount}）</a></div>
        </div>
        <form action="/shoppingCartAction.do" method="get" name="searchform">
        <input type="hidden" name="pid"  id="pid" value="${productPojo.id}">     
        <div class="product_details-Part01-Details-R">
        	<div class="product_details-Part01-Details-R-Part01">
            	<div class="product_details-Part01-Details-R-Part01-txt01" style="width:768px;">
            	<span class="product_details-Part01-Details-R-Part01-txt01-span01">起批量：</span>
            	<span class="product_details-Part01-Details-R-Part01-txt01-span02" id="numberArr0"></span>
            	<span class="product_details-Part01-Details-R-Part01-txt01-span02" id="numberArr1"></span>
            	<span  id="numberArr2"></span>
            	</div>
                <div style="clear: both;"></div>
                <div class="product_details-Part01-Details-R-Part01-txt02"  style="width:768px;">
                <span class="product_details-Part01-Details-R-Part01-txt01-span01" style="height:40px;line-height: 40px;">价&nbsp;&nbsp;&nbsp;格：</span>
                <s:if test="#session.wuser.type==3">
                <span class="product_details-Part01-Details-R-Part01-txt01-span03" id="priceArr0"></span>
                <span class="product_details-Part01-Details-R-Part01-txt01-span03" id="priceArr1"></span>
                <span class="product_details-Part01-Details-R-Part01-txt01-span03" id="priceArr2"></span>
                </s:if>
                <s:elseif test="#session.wuser.type==2">
                <a style="text-decoration:none;color:red;font-size:16pt;" href="" onclick="javascript:return window.alert('您当前是供应商状态无法作此操作');" >分销商登录查看</a>
                </s:elseif>
                <s:elseif test="#session.wuser.type==1">
                <a style="text-decoration:none;color:red;font-size:16pt;" href="applyConsumer.do" onclick="javascript:return window.confirm('请先成为分销商');" >分销商登录查看</a>
                </s:elseif>
                <s:else>
                <a style="text-decoration:none;color:red;font-size:16pt;" href="applyConsumer.do">分销商登录查看</a>
                </s:else>
                <!--
                <span style="margin-right: 60px;font-size: 20px">分销商登录查看</span>
                <span style="margin-right: 60px;font-size: 20px">分销商登录查看</span>
                 -->
                </div>
            </div>
            
            <div class="product_details-Part01-Details-R-Part02">
            	<div class="product_details-Part01-Details-R-Part01-txt01">
            	<span class="product_details-Part01-Details-R-Part01-txt01-span01">物&nbsp;&nbsp;&nbsp;流：</span>
            	<%-- <span class="product_details-Part01-Details-R-Part01-txt01-span05">广东汕头<span class="product_details-Part01-Details-R-Part02-txt">至</span><select name="123" class="product_details-Part01-Details-R-Part02-txt02">
            	<option>请选择</option></select> ｜  快递 ¥0 </span> --%>
            	
            	<s:if test="#productPojo.postageType==\"1\"">
            	   <span class="product_details-Part01-Details-R-Part01-txt01-span05">包邮</span>
            	</s:if>
            	<s:elseif test="#productPojo.weight>3">
                 <span class="product_details-Part01-Details-R-Part01-txt01-span05"><label id="fare">${sysAreas[0].name}单件运费${sysAreas[0].fare2}元</label> &nbsp;&nbsp;&nbsp; </span>
                <select name="fareprice" class="product_details-Part01-Details-R-Part02-txt02" style="border: #dfdfdf 1px solid;" id="fareSelect" onChange="chuanHuoSelect(this)">
            	<option value="" selected>其他地区运费</option>
            	<s:iterator value="sysAreas">
            	<option value="<s:property value='name'/>单件运费<s:property value='fare2'/>元"><s:property value='name'/>单件运费<s:property value='fare2'/>元</option>
            	</s:iterator>    

            	</select>
                 </s:elseif> 
                <s:else>
                 <span class="product_details-Part01-Details-R-Part01-txt01-span05"><label id="fare">${sysAreas[0].name}单件运费${sysAreas[0].fare}元</label> &nbsp;&nbsp;&nbsp; </span>
                <select name="fareprice" class="product_details-Part01-Details-R-Part02-txt02" style="border: #dfdfdf 1px solid;" id="fareSelect" onChange="chuanHuoSelect(this)">
            	<option value="" selected>其他地区运费</option>
            	<s:iterator value="sysAreas">
            	<option value="<s:property value='name'/>单件运费<s:property value='fare'/>元"><s:property value='name'/>单件运费<s:property value='fare'/>元</option>
            	</s:iterator>

            	</select>
                 </s:else> 
            	</div>
                
                <div class="product_details-Part01-Details-R-Part01-txt01"><span class="product_details-Part01-Details-R-Part01-txt01-span01">评价/成交：</span><span class="product_details-Part01-Details-R-Part02-txt03">${pcnum }<font color="#333">条评价</font>&nbsp;&nbsp;&nbsp; ${odnum }<font color="#333">个成交</font>&nbsp;&nbsp;&nbsp; ${productPojo.hits}<font color="#333">次浏览</font></span></div>
                
                <div class="product_details-Part01-Details-R-Part01-txt01"><span class="product_details-Part01-Details-R-Part01-txt01-span01" style=" float:left;">采购量：</span><span><a href="javascript:void(0);" id="jian" class="product_details-Part01-Details-R-Part02-Symbol" style="float:left;">-</a><input type="text" name="number" id="z_num" onkeyup="if(this.value==0){this.value=this.value.replace(this.value.replace(/[^\d*]/,''),'1');}else{this.value=this.value.replace(/[^\d*]/,'');}"  style="width:50px;height:25px;text-align:center;padding-top:0px;border: #aaa 1px solid; float:left;" value="1"><a href="javascript:void(0);" id="jia" class="product_details-Part01-Details-R-Part02-Symbol" style="float:left;">+</a></span><!--<span class="product_details-Part01-Details-R-Part02-Sell">1155646艘可售</span>--></div>
                
                <div class="product_details-Part01-Details-R-Part02-button">
     
                 <s:if test="#request.loginPojotype==\"-1\"">
	                <a href="javascript:void(0);" class="product_details-Part01-Details-R-Part02-button-black-A" id="tobuy0">立即购买</a>
	                <a href="javascript:void(0);" class="product_details-Part01-Details-R-Part02-button-black-A" id="addcart0"><img src="images/agwc_new.png" alt="" width="23" height="23" /> 加入购物车</a>
	                <a href="javascript:void(0);" class="product_details-Part01-Details-R-Part02-button-black-B" id="addfenxiao0"><img src="images/afx-pic.png" alt="" width="23" height="23" /> 加入分销</a>
                 </s:if>
                 <s:else>
                     <s:if test="#request.loginPojotype==\"3\"">
			             <a href="javascript:void(0);" class="product_details-Part01-Details-R-Part02-button-A" id="tobuy">立即购买</a>
			             <a href="javascript:void(0);" class="product_details-Part01-Details-R-Part02-button-A" id="addcart"><img src="images/product_details_01.jpg" alt="" width="23" height="23" /> 加入购物车</a>
			             <a href="javascript:void(0);" class="product_details-Part01-Details-R-Part02-button-B" id="addfenxiao"><img src="images/product_details_02.jpg" alt="" width="23" height="23" /> 加入分销</a>
                     </s:if>
                     <s:else>
                         <a href="javascript:void(0);" class="product_details-Part01-Details-R-Part02-button-black-A" id="tobuy_tishi">立即购买</a>
	                     <a href="javascript:void(0);" class="product_details-Part01-Details-R-Part02-button-black-A" id="addcart_tishi"><img src="images/agwc_new.png" alt="" width="23" height="23" /> 加入购物车</a>
	                     <a href="javascript:void(0);" class="product_details-Part01-Details-R-Part02-button-black-B" id="addfenxiao_tishi"><img src="images/afx-pic.png" alt="" width="23" height="23" /> 加入分销</a>
                     </s:else>
                 </s:else>

                </div>
            </div>
            </form>
            <div class="product_details-Part01-Details-R-Part03">
            	<div class="product_details-Part01-Details-R-Part03-L"><span class="product_details-Part01-Details-R-Part03-L-txt">支付方式</span><br /><img src="images/product_details_14.jpg" alt="" width="16" height="16" /> 支付宝&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/product_details_16.jpg" alt="" width="16" height="16"/> 网上银行</div>
                <!--<div class="product_details-Part01-Details-R-Part03-R"><span class="product_details-Part01-Details-R-Part03-R-txt">买家保障</span><br /><img src="images/product_details_18.jpg" alt="" width="16" height="16" /> 卖家支持先行赔付，保障买家交易安全</div>-->
            </div>
            
        </div>
        <div class="clear"></div>
    </div>
</div>

<div class="product_details-Part02">
	<div class="product_details-Part02-titleBg">
    	<div class="product_details-Part02-title">掌柜推荐</div>
        <div class="product_details-Part02-list">
        	<ul>
        	
        	<s:iterator value="shopProductList">
            	<li><div class="product_details-Part02-list-Pic">
                	<div class="product_details-Part02-list-Pic-txt iconx"><s:if test="#session.wuser.type==3">¥<fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></s:if><s:else>分销商登录查看</s:else></div><a href="/goProductDetail.do?productPojo.id=<s:property value='id'/>"><img src="/upfiles/product/small/<s:property value='image'/>" alt="" width="177" height="176" /></a></div>
                </li>
            </s:iterator>    
                
            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>

<div class="product_details-Part03">
	<div class="product_details-Part03-L">
        <div class="product_details-Part03-Pro-L01">
            <div class="product_details-Part03-L01-title">${shopPojo.name}专营店</div>
            <div class="product_details-Part03-L01-Txt01"><font color="#747474">主营产品：</font>${shopPojo.mainProduct}</div>
            <div class="shop_details-Part01-L-Txt02">
                                           经营模式：<font color="#333333">经销批发</font> <font color="#aa6d00">[已认证]</font><br />
                                           所在地区：<font color="#333333">${shopPojo.address}</font>
            </div>
            <div class="shop_details-Part01-L-Txt03"><span class=""><img src="images/new_12.jpg" alt="" width="19" height="19" /><a href="helpDetail.do?helpPojo.id=67" target="_blank"> 实地认证</a></span><span class="shop_details-Part01-L-Txt03-R"><img src="images/new_14.jpg" alt="" width="19" height="19" /><a href="helpDetail.do?helpPojo.id=69" target="_blank"> 身份认证</a></span></br></br><span>&nbsp;<img src="images/index_3c.jpg" alt="" width="19" height="19" /><a href="helpDetail.do?helpPojo.id=222" target="_blank"> 3C认证</a></span><span class="shop_details-Part01-L-Txt03-R">&nbsp;<img src="images/index_35.jpg" alt="" width="19" height="19" /><a href="helpDetail.do?helpPojo.id=68" target="_blank"> 资质认证</a></span></div>
            <div class="product_details-Part03-L01-Txt04" style="margin-bottom:15px;"><a href="/shopDetailsWeb.do?shop.id=${shopPojo.id}" class="product_details-Part03-button01">进店逛逛</a> <a onclick="shopCo(${shopPojo.id})"  class="product_details-Part03-button02">店铺收藏</a><div style="clear:both;"></div></div>
            <div style="clear:both;"></div>
        </div>
       
        <div class="product_details-Part03-L02">
        	<div class="product_details-Part03-L02-title">宝贝热销榜</div>
            <div class="product_list-R-list">
                <ul>
                <s:iterator value="hotProductList">
                    <li><a href="/goProductDetail.do?productPojo.id=<s:property value='id'/>"><img src="/upfiles/product/small/<s:property value='image'/>" alt="" width="225" height="225" />
                        <div class="product_list-R-list-txt">
                            <div class="product_list-R-list-txt-L"><s:if test="#session.wuser.type==3">¥<fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></s:if><s:else><span style="font-size: 14px;float:left;width: 100px;">分销商登录查看</span></s:else></div>
                            <div class="product_list-R-list-txt-R">销量：<s:property value='sellNumber'/></div>
                            <div class="clear"></div>
                        </div></a>
                    </li>
                </s:iterator>    
                   
                </ul>
        	</div>
        </div>
    </div>
    
    <div class="product_details-Part03-R">
    	<div class="product_details-Part03-R-title" id="tabs">
        	<div class="product_details-Part03-R-titleBg">
                <ul>
                    <li><a href="#tabs-1">宝贝详情</a></li>
                    <li><a href="#tabs-2">累计评论 ${pcnum }</a></li>
                    <li><a href="#tabs-3">成交数量 ${odnum }</a></li>
                </ul>
            </div>
        
        	<div class="clear"></div>
            
            <div class="product_details-Part03-R-table-Bg" id="tabs-1">
                <table border="0" cellpadding="0" cellspacing="0" class="product_details-Part03-R-table">
                    <tr>
                        <td width="100" class="product_details-Part03-R-table-L">产品名称</td>
                        <td width="180" class="product_details-Part03-R-table-R">${productPojo.productName}</td>
                        <td width="100" class="product_details-Part03-R-table-L">是否电动</td>
                        <td width="180" class="product_details-Part03-R-table-R">${productPojo.isPowerName}</td>
                        <td width="100" class="product_details-Part03-R-table-L">包装方式</td>
                        <td width="180" class="product_details-Part03-R-table-R">${productPojo.packName}</td>
                    </tr>
                    <tr>
                        <td width="100" class="product_details-Part03-R-table-L">产品货号</td>
                        <td width="180" class="product_details-Part03-R-table-R">${productPojo.productNum}</td>
                        <td width="100" class="product_details-Part03-R-table-L">品牌</td>
                        <td width="180" class="product_details-Part03-R-table-R">${productPojo.brandName}</td>
                        <td width="100" class="product_details-Part03-R-table-L">产地</td>
                        <td width="180" class="product_details-Part03-R-table-R">${productPojo.location}</td>
                    </tr>
                    <tr>
                        <td width="100" class="product_details-Part03-R-table-L">是否包邮</td>
                        <td width="180" class="product_details-Part03-R-table-R">${productPojo.postageTypeName}</td>
                        <td width="100" class="product_details-Part03-R-table-L">材质</td>
                        <td width="180" class="product_details-Part03-R-table-R">${productPojo.textureName}</td>
                        <td width="100" class="product_details-Part03-R-table-L">适用年龄</td>
                        <td width="180" class="product_details-Part03-R-table-R">${productPojo.ageName}</td>
                    </tr>
                     <tr>
                        <td width="100" class="product_details-Part03-R-table-L">最低零售价</td>
                        <td width="180" class="product_details-Part03-R-table-R">￥${productPojo.lowestPrice}</td>
                        <td width="100" class="product_details-Part03-R-table-L">建议零售价</td>
                        <td width="180" class="product_details-Part03-R-table-R">￥ ${productPojo.sellingPrice}</td>
                    	<td width="100" class="product_details-Part03-R-table-L">重量</td>
                        <td width="180" class="product_details-Part03-R-table-R">${productPojo.weight}kg</td>
                    </tr>
                    
                </table>
                <div class="product_details-Part03-R-table-Pic">
                <s:iterator value="productImagesList">
                <img class="scrollLoading" xSrc="/upfiles/product/<s:property value='images'/>" alt="" src="image/loadImg.gif" width="750"/>
                 </s:iterator> 
                     <img src="/images/1-2-3.jpg" alt="" width="750" />
                </div>
            </div>
            
            <div class="product_details-Part03-R-table-Bg" id="tabs-2">
			
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rate-grid" style="font-size:12px;">
					<tr>
		        		<td class="col-author">
						  <div>
						   评论用户
						  </div>
						</td>
						<td  class="tm-col-master">
							评论内容
						</td>
						 </tr>
						
					  <s:iterator value="pclist">
		        		<tr>
		        		<td class="col-author">
						  <div>
						   <!--<s:if test="companyName.length()>2"><s:property value="companyName.substring(0,2)+'***'" /></s:if><s:else><s:property value="companyName" /></s:else>-->
						  <s:if test="userName.length()>2"><s:property value="userName.substring(0,2)+'***'" />（匿名）</s:if><s:else><s:property value="userName" /></s:else>
						  </div>
						</td>
						<td  class="tm-col-master">
							<div class="tm-rate-content"><div class="tm-rate-fulltxt"><s:property value='comment' /></div></div>
							<div class="tm-rate-date"><s:property value='createDateStr' /></div>
						</td>
						<!--<td class="col-meta"><div><span>颜色分类：</span><s:property value='color' /><span>规格：</span><s:property value='size' /></div></td>-->
					  </tr>
		        	</s:iterator>
		        	
				
				</table>
			
                 <div class="product_list-L-Part05-page"> <s:if test="#pcnum==0">暂无评论!</s:if>
    		<div class="digg" id="Pagination"></div>
    	</div> 
    </div>
<!--图片滚动加载-->
<script type="text/javascript">
    var scrollLoad = (function (options) {
        var defaults = (arguments.length == 0) ? { src: 'xSrc', time: 300} : { src: options.src || 'xSrc', time: options.time ||300};
        var camelize = function (s) {
            return s.replace(/-(\w)/g, function (strMatch, p1) {
                return p1.toUpperCase();
            });
        };
        this.getStyle = function (element, property) {
            if (arguments.length != 2) return false;
            var value = element.style[camelize(property)];
            if (!value) {
                if (document.defaultView && document.defaultView.getComputedStyle) {
                    var css = document.defaultView.getComputedStyle(element, null);
                    value = css ? css.getPropertyValue(property) : null;
                } else if (element.currentStyle) {
                    value = element.currentStyle[camelize(property)];
                }
            }
            return value == 'auto' ? '' : value;
        };
        var _init = function () {
            var offsetPage = window.pageYOffset ? window.pageYOffset : window.document.documentElement.scrollTop,
                offsetWindow = offsetPage + Number(window.innerHeight ? window.innerHeight : document.documentElement.clientHeight),
                docImg = document.images,
                _len = docImg.length;
            if (!_len) return false;
            for (var i = 0; i < _len; i++) {
                var attrSrc = docImg[i].getAttribute(defaults.src),
                    o = docImg[i], tag = o.nodeName.toLowerCase();
                if (o) {
                    postPage = o.getBoundingClientRect().top + window.document.documentElement.scrollTop + window.document.body.scrollTop; postWindow = postPage + Number(this.getStyle(o, 'height').replace('px', ''));
                    if ((postPage > offsetPage && postPage < offsetWindow) || (postWindow > offsetPage && postWindow < offsetWindow)) {
                        if (tag === "img" && attrSrc !== null) {
                            o.setAttribute("src", attrSrc);
                        }
                        o = null;
                    }
                }
            };
            window.onscroll = function () {
                setTimeout(function () {
                    _init();
                }, defaults.time);
            }
        };
        return _init();
    });
    scrollLoad();
</script> 
<script type="text/javascript">
$(function() {
	$("#Pagination").pagination(${pcnum}, {
		current_page : <s:property value='page.pageNo'/>-1,
		items_per_page:18,	//每页显示数量
		next_text : '>',
		prev_text : '<',
		first_text: "|<",
		last_text: ">|",
		num_edge_entries: 2,
		num_display_entries: 4,
		prev_show_always : true,
		next_show_always : true,
		callback: pageselectCallback,
		load_first_page : function() {
			return false;
		}
	});
});

function pageselectCallback(pageindex, jq) {
	var pageNo = pageindex+1;
	window.location.href="goProductDetail.do?page.pageNo="+pageNo+"&page.rowCount=${pcnum}&productPojo.id=<s:property value='productPojo.id'/>#tabs-2";
	return false;
}

</script>                
            
            
            <div class="product_details-Part03-R-table-Bg" id="tabs-3">
            	<div style=" margin:0 auto;">
					<table summary="成交记录" border="0" cellspacing="0" cellpadding="0" class="table-deal-record">  
					<tbody>
					   <tr> 
					   	   <th class="th-buyer">买家</th>
						   <th class="th-goods"><!--款式/型号--></th> <th class="th-quantity">数量</th>
						   <th class="th-price">价格</th> <th class="th-dealtime">成交时间</th> 
					   </tr>
					   <s:iterator value="odlist">
		        		<tr> 
						     <td class="cell-align-l buyer"> <div class="line">
						       <s:if test="channelName.length()>2"><s:property value="channelName.substring(0,2)+'***'" /></s:if><s:else><s:property value="channelName" /></s:else> </div> <div class="line"> 
						       
						  
						      </div> </td>
						     <td class="cell-align-l style" style="text-align:left;"> <!--颜色分类:</span><s:property value='color' /><br>套餐类型:<s:property value='size' />--> </td> 
							 <td class="quantity"> <s:property value='num' /> </td> <td class="price"><em>¥<s:if test="#session.wuser.type==3"><fmt:formatNumber value="${stockPrice}" pattern="#0.00" /></s:if><s:else>*<fmt:formatNumber value="${fn:substring(stockPrice,1,5)}" pattern="#0.00" /></s:else></em> <a title="活动促销 " class="buyer-cu-icon"></a> </td> 
							 <td class="dealtime"> <s:property value='updateDateStr' /> </td> 
						  </tr>
		        	</s:iterator>
		        	 <s:iterator value="elelist">
		        		<tr> 
						     <td class="cell-align-l buyer"> <div class="line">
						       <s:if test="channelName.length()>2"><s:property value="channelName.substring(0,2)+'***'" /></s:if><s:else>玩具反斗***</s:else> </div> <div class="line"> 
						       
						  
						      </div> </td>
						     <td class="cell-align-l style" style="text-align:left;"> <!--颜色分类:</span><s:property value='color' /><br>套餐类型:<s:property value='size' />--> </td> 
							 <td class="quantity"> <s:property value='num' /> </td> <td class="price"><em>¥*<fmt:formatNumber value="${fn:substring(pay,1,5)}" pattern="#0.00" /></em> <a title="活动促销 " class="buyer-cu-icon"></a> </td> 
							 <td class="dealtime"> <s:property value='createDateStr' /> </td> 
						  </tr>
		        	</s:iterator>
				   </tbody></table>
				    <div class="product_list-L-Part05-page">
    		<div class="digg" id="PaginationOd"></div>
    	</div> 
				</div>
            </div>
            
<script type="text/javascript">
$(function() {
	$("#PaginationOd").pagination( ${count}  , {
		current_page : <s:property value='page2.pageNo'/>-1,
		items_per_page:10,	//每页显示数量
		next_text : '>',
		prev_text : '<',
		first_text: "|<",
		last_text: ">|",
		num_edge_entries: 2,
		num_display_entries: 4,
		prev_show_always : true,
		next_show_always : true,
		callback: pageselectCallback2,
		load_first_page : function() {
			return false;
		}
	});
});

function pageselectCallback2(pageindex, jq) {
	var pageNo2 = pageindex+1;
	window.location.href="goProductDetail.do?page2.pageNo="+pageNo2+"&page2.rowCount=${count}&productPojo.id=<s:property value='productPojo.id'/>#tabs-3";
	return false;
}

</script>          
        </div><div class="clear"></div>
    </div>
    
</div>
<div id="leftsead">
	<!--<ul>
		
		<li><a href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4001501677&aty=0&a=0&curl=&ty=1" target="_blank"><img src="images/ll04.png" width="131" height="49" class="hides"/><img src="images/l04.png" width="47" height="49" class="shows" /></a></li>
		
	</ul> -->
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
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>