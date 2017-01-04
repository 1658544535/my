<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/seller_common.css" type="text/css" media="all" />
        <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/homecsslib.css" type="text/css" media="all" />
        <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/preview.css" type="text/css" media="all" />
		<jsp:include page="../common_head.jsp"></jsp:include>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
    </head>
	<body>
		<div id="head">
				<div class="wrapper">
					<div class="main-logo ">
						<a class="main-logo-a" href="#">
							&nbsp;
						</a>
					</div>
				</div>
			</div>
			<div id="container">
				<div class="ui-nav seller-nav">
					<div class="ui-nav-main  seller-nav-bg">
						<ul class="wrapper seller-nav-main" id="J_sellerNav" style="height:40px">
						</ul>
					</div>
				</div>
			</div>
			<div id="content" class="wrapper">
                <style>.main-content ul{padding:0;}</style>
                <div id="item-detail">
                    <div id="detail-meta" c-rid="04001" clog="" c-emit="show" c-et="item" c-etl="1019229" clog-get="true">
                        <div class="detail-meta-bg clearfix">
                            <div class="side-wrapper">
                                <div class="carousel-wrapper carousel-control view-CarouselControl">
                                    <div class="main-img-cont clearfix"></div>
                                    <div class="thumb-cont">
                                        <div class="thumb-cont-inner">
                                            <ul class="view-ThumbList">
                                            <c:if test="${productFocusImagesList != null }">
                                            <c:forEach items="${productFocusImagesList }" var="p">
                                                <li>
                                                    <a href="javascript:;">
                                                        <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/productFocusImages/${p.images }" data-src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/productFocusImages/${p.images }">
                                                    </a>
                                                </li>
                                            </c:forEach>
                                            </c:if>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="main-wrapper">
                                <div class="title">
                                    <h3>
                                        <span class="pink" style="font-weight: normal;"></span>
                                        ${productPojo.productName }                   </h3>
                                    <p>${productPojo.productSketch }</p>
                                </div>
                                <div class="price-info view-SkuPriceInfo">
                                    <span class="pink" id="priceArr"><em class="f-16">¥</em> <em class="price" op-value="price">${productPojo.distributionPrice }</em></span>
                                    <span class="discount view-SkuPriceDiscountInfo" op-value="discount">${productPojo.discount }折</span>
                                    <span class="baoyou"><c:if test="${productPojo.postageType == 0 }">不包邮</c:if><c:if test="${productPojo.postageType == 1 }">包邮</c:if></span>
                                    <span class="market">市场价：¥ <em class="strike" op-value="originPrice">${productPojo.sellingPrice }</em></span>
                                </div>
                                <!-- sale-attention -->
                                <div class="sale-attrs view-SkuSelecter">
                                    <div class="attention-head">
                                        请选择你要的商品信息
                                    </div>
                                    <div class="attr">
                                        <label>运费</label>
                                        <span class="view-ShippingFeeInfo">包邮（偏远地区除外）</span>
                                    </div>
                                    <div class="view-SkuSelect">
                                        <div class="attr view-SkuSelectItem" sku-id="3" id="colorSku">
                                            <label>颜色</label>
                                            <ul op-each="value" class="attr-values">
                                            <c:if test="${skuColorList != null }">
                                            <c:forEach items="${skuColorList }" var="p">
                                                <li class="view-SkuSelectItemValue" style="display:inline;" id="${p.skuColorId }"  value="0" name="colorName">
                                                    <a href="javascript:;" title=" ${p.colorValue }">
                                                        <div class="thumb-box">${p.colorValue}</div>
                                                        <div class="current-box"></div><i class="iconfont">󰂲</i></a>
                                                </li>
                                            </c:forEach>
                                            </c:if>
                                            </ul>
                                        </div>
                                         <div class="attr view-SkuSelectItem" sku-id="4" id="formatSku">
                                            <label>规格</label>
                                            <ul op-each="value" class="attr-values">
                                            <c:if test="${skuFormatList != null }">
                                            <c:forEach items="${skuFormatList }" var="p">
                                                <li class="view-SkuSelectItemValue" style="display:inline;" id="${p.skuFormatId }"  value="0" name="formatName">
                                                    <a href="javascript:;" title=" ${p.formatValue }">
                                                        <div class="thumb-box"></div> <span>${p.formatValue }</span>
                                                        <div class="current-box"></div><i class="iconfont">󰂲</i></a>
                                                </li>
                                            </c:forEach>
                                            </c:if>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="attr buy-num pure-form">
                                        <label>数量</label>
                                        <div class="detail-number">
                                            <a href="javascript:;" onclick="numDown()"  class="number-op del-num view-ReductionItemNumBtn disable"><i class="iconfont">󰅶</i></a>
                                            <input class="view-BuyNum" id="view-BuyNum"  type="text" value="1">
                                            <a href="javascript:;" onclick="numUp()" class="number-op add-num view-AdditionItemNumBtn disable"><i class="iconfont">󰅵</i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- evaluate -->
                            <div class="eva-wrapper">
                                <div class="title">特卖品牌</div>
                                <div class="dec-con">
                                    <p>
                                        <label>品牌：</label><span>${productPojo.brandNames }</span></p>
                                    <p>
                                        <em class="icon icon-check" title="已通过企业实名认证"></em>
                                        <em class="icon icon-pay" title="已缴纳保证金"></em>
                                    </p>
                                    <!-- <p>
                                        <label>服务：</label>
                                    </p>
                                    <p class="p1">本商品由品牌入驻商从 xx 发货，并提供售后服务。</p> -->
                                </div>
                                <div class="eva-con">
                                    <p>
                                        <label>品牌综合评分：</label><span>${userBrandPojo.productCommt }</span></p>
                                    <p>
                                        <label>发货速度评分：</label><span>${userBrandPojo.deliverCommt }</span></p>
                                    <p>
                                        <label>物流速度评分：</label><span>${userBrandPojo.logisticsCommt }</span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="detail-main">
                        <div class="main-content fl" style="margin:0;">
                            <div class="detail-info pure-u-1 white-bg box-shadow nav-bar-parent">
                                <div class="nav-bar view-NavBar">
                                    <ul class="nav-menu clearfix">
                                        <li class="current"><i class="iconfont">󰆘</i> 商品详情</li>
                                    </ul>
                                    <div class="cart-duplication view-CartDuplacation">
                                        <a href="javascript:;"><i class="iconfont">󰅹</i> <span>立即抢购</span></a>
                                    </div>
                                </div>
                                <div class="cont view-Cont">
                                    <div id="productParam">
                                        <div class="cont-index">
                                            <h3>商品参数</h3>
                                            <ul class="props clearfix">
                                                <li><b>货号：</b><span title="${productPojo.productNum }">${productPojo.productNum }</span></li>
                                                <li><b>品牌：</b><span title="${productPojo.brandNames }">${productPojo.brandNames }</span></li>
                                                <li><b>产地：</b><span title="${productPojo.location }">${productPojo.location }</span></li>
                                                <li><b>材质：</b><span title="${productPojo.textureName }">${productPojo.textureName }</span></li>
                                                <%-- <li><b>适用性别：</b><span title=""></span></li>
                                                <li><b>适用年龄：</b><span title=""></span></li> --%>
                                            </ul>
                                        </div>
                                    </div>
                                    <h3>商品详情</h3>
                                    <div class="content">
                                    <c:if test="${productImagesList != null }">
                                    <c:forEach items="${productImagesList }" var="p">
                                        <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/product/${p.images }">
                                    </c:forEach>
                                    </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--推荐-->
                        <%-- <div class="pure-u detail-sidebar noview-SideBar">
                            <div class="title">相似推荐</div>
                            <div class="sidebar-inner bfd-sidebar-inner" c-rid="04002">
                                <div class="item">
                                    <a target="_blank" title="这里是淘竹马商品推荐区" href="#">
                                        <img width="150" height="150" src="">
                                    </a>
                                    <div class="item-info">
                                        <div class="item-title"></div>
                                        <span class="item-price mr-10"><span class="yen">¥</span></span>
                                        <span class="item-desc strike"><span class="yen">¥</span></span>
                                    </div>
                                </div>
                            </div>
                        </div> --%>
                    </div>
                </div>
			</div>
            </div>
		</div>
		<script src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/js/jquery.zoomtoo.min.js" type="text/javascript"></script>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
		<script>
            $(function(){
                //选择颜色
                /*  $("#detail-meta .attr-values li").on("click",function(){
                	 if($(this).hasClass("disabled") && ($(this).parents(".view-SkuSelectItem").attr("id")=='formatSku')){
                		 return false;
                	 }
                    $(this).parents(".attr-values").find("li").removeClass("current");
                    $(this).addClass("current");
                });  */

                //图片
                $(".view-ThumbList li").each(function(index,obj){
                    var imgSrc = $(obj).find("img").attr("src");
                    var bigImgSrc = $(obj).find("img").attr("data-src");
                    if(index==0){
                        $(".main-img-cont").append('<div class="view-MainImg-box" style="cursor: crosshair;"><img class="view-MainImg" src="'+ imgSrc +'" data-src="'+ bigImgSrc +'"></div>');
                    }else{
                        $(".main-img-cont").append('<div class="view-MainImg-box" style="display:none;cursor: crosshair;"><img class="view-MainImg" src="'+ imgSrc +'" data-src="'+ bigImgSrc +'"></div>');
                    }
                });

                $(".view-MainImg-box").eq(0).zoomToo({magnify: 1});
                $(".view-ThumbList li").eq(0).addClass("active")
                $(".view-ThumbList li").on("click",function(){
                    var index = $(".view-ThumbList li").index(this);
                    $(".view-ThumbList li").removeClass("active");    
                    $(this).addClass("active");

                    $(".view-MainImg-box").hide().eq(index).show();
                    $(".view-MainImg-box").eq(index).zoomToo({magnify: 1});
                })
                
            });

            //数量加减
            function numDown(){
                var nowVal = parseInt($("#view-BuyNum").val());
                if(nowVal>1){
                    $("#view-BuyNum").val(nowVal-1);
                }
            }
            function numUp(){
                var nowVal = parseInt($("#view-BuyNum").val());
                $("#view-BuyNum").val(nowVal+1);
            }
            </script> 
            <script>            
         	var jsondata = ${pslListJson};
         	var skuStockStr = ${skuStockStr};
         	var skuPriceStr = ${skuPriceStr};
         	var skuIdStr = ${skuIdStr};

         	var json=eval(jsondata);  
         	var skuStock=eval(skuStockStr)[0];  
         	var skuPrice=eval(skuPriceStr)[0];  
         	var skuId=eval(skuIdStr)[0];  

         	var colorId = null;
         	var formatId = null;
         	
            //商品规格选择
            $(function () {
                $("#colorSku").each(function () {
                    var i = $(this);
                    var p = i.find("ul>li");
                    p.click(function () {
                    	if(!$(this).hasClass("disabled")) {
                        if (!!$(this).hasClass("selected")) {
                            $(this).removeClass("selected");
                            //$("#stockNum").attr("value","");
                            $("li[name='formatName']").each(function () {
                            	if($(this).hasClass("disabled")) {
                              		$(this).removeClass("disabled");
                          		}
                               }); 
                        } else {
                             $(this).addClass("selected").siblings("li").removeClass("selected");
                           var id = $(this).attr("id");
                            $("li[name='formatName']").each(function () {
                            	if(!$(this).hasClass("selected")) {
                              		$(this).addClass("disabled");
                          		} else {
         		          			//$("#stockNum").attr("value","件 (库存" + skuStock[id+'_'+$(this).attr("id")] + "件)");
         		          			colorId = id;
         		          			formatId = $(this).attr("id");
         		          			changePrice(skuPrice[id+'_'+$(this).attr("id")]);
                          		}
                               }); 
        	               	for(var i=0,l=json.length;i<l;i++){ 
         		          	if(json[i]['skuColorId']==id) {
         		          		$("li[name='formatName']").each(function () {
         	                            if(json[i]['skuFormatId']==this.id) {
         	                           		$(this).removeClass("disabled");
        	                            }
        	                        }); 
        	          		    }
        	          	  	 }
                        }
                    	}
                    })
                })
        		
        		$("#formatSku").each(function () {
                    var i = $(this);
                    var p = i.find("ul>li");
                    p.click(function () {
                    	if(!$(this).hasClass("disabled")) {
                        if (!!$(this).hasClass("selected")) {
                            $(this).removeClass("selected");
                           // $("#stockNum").attr("value","");
                            $("li[name='colorName']").each(function () {
                            	if($(this).hasClass("disabled")) {
                              		$(this).removeClass("disabled");
                          		}
                               }); 
                        } else {
                             $(this).addClass("selected").siblings("li").removeClass("selected");
                           var id = $(this).attr("id");
                            $("li[name='colorName']").each(function () {
                            	if(!$(this).hasClass("selected")) {
                              		$(this).addClass("disabled");
                          		} else {
         		          			//$("#stockNum").attr("value","件 (库存" + skuStock[$(this).attr("id")+'_'+id] + "件)");
         		          			colorId = $(this).attr("id");
         		          			formatId = id;
         		          			changePrice(skuPrice[$(this).attr("id")+'_'+id]);
                          		}
                               }); 
        	               	for(var i=0,l=json.length;i<l;i++){ 
         		          	if(json[i]['skuFormatId']==id) {
         		          		$("li[name='colorName']").each(function () {
         	                            if(json[i]['skuColorId']==this.id) {
         	                           		$(this).removeClass("disabled");
        	                            }
        	                        }); 
        	          		    }
        	          	  	 }
                        }
                    	}
                    })
                })
            })
         	function changePrice(result){
        		var data = eval(result);
        		    $("#priceArr").html("");
        		    $("#priceArr").html("<em class='f-16'>¥</em> <em class='price' op-value='price'>"+parseFloat(data).toFixed(1)+"</em>");
        	}
        </script>
	</body>
</html>