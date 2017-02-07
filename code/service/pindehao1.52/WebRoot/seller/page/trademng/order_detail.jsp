<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
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
		<jsp:include page="../sellerHeader.jsp"></jsp:include>
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <style>
                           .view-ModifyShipmentPanel{margin-bottom:5px;}h5.h5{width:960px;}h5.h5 span{float:right;width:100px;text-align:center;}.u-icon-fenxiao{display:inline-block;*display:inline;*zoom:1;height:14px;line-height:14px;color:#fff;background:#ffae2e;border:1px solid #fa9a12;border-radius:2px;padding:0 3px;}.view-ModifyShipmentSave{background:none;border:none;color:#08c;}.view-ModifyShipmentSave:hover{text-decoration:underline;}
                        </style>
                        <h1 class="seller-title">
                            订单详情
                        </h1>
                        
                        <div class="p20 seller-order">
                        <form action="updateOrder.do?os=${os}&guide=${guide}" method="post"  id="from5">
                        <input name="order.id" id="orderId" value="${orderPojo.id}" class="inputText" type="hidden">
                        <table style="width:100%">
                        <tr>
						<td>订单编号：${orderPojo.orderNo}</td>
						<td>订单状态：
							<c:forEach items="${orderStatus}" var="orderStatus">
								<c:if test="${orderPojo.orderStatus==orderStatus.value}">
									<label  name="order.orderStatus" class="floatLeft">${orderStatus.name}</label>
								</c:if>
							</c:forEach>
						</td>
						<td>支付状态：
						<c:forEach items="${payStatus}" var="payStatus">
								<c:if test="${orderPojo.payStatus==payStatus.value}">
								<label name="order.payStatus" class="floatLeft">${payStatus.name}</label>
								</c:if>
						</c:forEach>
						</td>
						</tr>
						<tr>
						<td>下单时间：${orderPojo.creatDateString}</td>
						<td>付款时间：${orderPojo.paymentDateStr}</td>
						<td>成团时间：${orderPojo.groupDateStr}</td>
						</tr>
						<tr>
						<td>确认收货时间：${orderPojo.confirmDateStr}</td>
						<td>发货时间：${orderPojo.sendDateStr}</td>
						<td>售后状态：${orderPojo.isCancelOrderName}</td>
						</tr>
						<tr>
						<td>快递公司：${orderPojo.logisticsName}
						</td>
						<td>快递单号: ${orderPojo.logisticsNo}</td>
						</tr>
                      <!--  <tr>
						<td align="right" class="grey" width="15%"  colspan="4"><input type="button" class="ok_btn" value="修改" id="sbutton6" /></td>
					    </tr>	-->					
					    </table>
						</form>
                        </div>
                        
                        <div class="p20 seller-order">
       <!--                     <h5 class="h5">
                                收货信息及交易详情
                                <span>
                                    成交价
                                </span>
                                <span>
                                    订单价
                                </span>
                                <span>
                                    维权/售后
                                </span>
                                <span>
                                    数量
                                </span>
                                <span style="width: 120px">
                                    单价
                                </span>
                            </h5>-->
                            <div class="trade-entry">
                                <div class="order-entry">
                                    <div class="order-head">
                                        <span style="display:inline-block;width:29%;">
                                           商品
                                        </span>
                                       <span style="display:inline-block;width:29%;">
                                           规格   
                                        </span>                                        
                                        <span style="display:inline-block;width:29%;"> 
                                           供货价（包邮）   
                                        </span>
                                       <span>
                                           数量
                                        </span>
                                    </div>
                                    <table class="pure-table">
                                        <tbody>
                                        <tr></tr>
                                        <c:forEach items="${orderDetailPojos }" var="o">
                                            <tr class="order-item">
                                                <td class="ol-b-item-info">
                                                    <a class="image" href="http://b2c.taozhuma.com/goProductDetail.do?productPojo.id=${o.productId }"
                                                    target="_blank">
                                                        <img src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/product/${o.productImages }">
                                                    </a>
                                                    <p>${o.businessCode }</p>
                                                    <p>${o.productName }</p>
                                         <!--             <a class="title c-666" target="_blank" href="http://b2c.taozhuma.com/goProductDetail.do?productPojo.id=${o.productId }">
                                                        ${o.productName }
                                                    </a>
                                                   <span class="sku">
                                                           
                                                                                                                                              （SKU：${o.productSku }&nbsp;&nbsp;&nbsp; ）
                                                    </span>-->
                                                </td>
                                           <td class="ol-b-item-info">
                    ${o.productSku }              
                                            </td>  
                                              <td class="ol-b-item-info">
                                                        ￥${o.stockPrice }              
                                            </td>    
                                            <td>
                       ${o.num }
                                            </td>
                       <!--                           <td class="ol-b-item-price">
                                                  <p>
                                                        ￥${o.stockPrice }              
                                                    </p>
                                                    
                                                    <p class="strike">
                                                        ￥${o.stockPriceOld }
                                                    </p>

                                                   
                                                </td>
                                                <td class="ol-b-number" style="text-align: center; ">
                                                    ${o.num }
                                                </td>
                                                <td class="ol-b-number" style="text-align: center">
                                                    <c:if test="${o.reStatus ==null or o.reStatus ==0}">-</c:if>
                                                    <c:if test="${o.reStatus ==1 or o.reStatus ==2 or o.reStatus ==3}">退款中，等待处理</c:if>
                                                    <c:if test="${o.reStatus ==4 or o.reStatus ==5 or o.reStatus ==6 or o.reStatus ==7}">售后服务完成</c:if>
                                                </td>
                                                <td class="ol-b-subtotal" style="text-align: center" rowspan="1">
                                                    <span class="price">
                                                        ￥${o.stockPrice*o.num }
                                                    </span>
                                                </td>
                                                <td class="ol-b-subtotal" rowspan="1">
                                                    <span class="price">
                                                        ￥${o.stockPrice*o.num }
                                                    </span>
                                                </td>
                                            </tr>-->
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div style="border-bottom: solid 1px #EFEFEF; height: 36px; color: #333333; padding-right: 5px; padding-top: 5px; padding-bottom: 5px;">
                                     <div style="width: 220px; margin-right: 0px">
               <!--                       <p class="price" style="font-size: 12px">   
                                                <span>
                                                    备注：
                                                </span>
                                                <span>
                                                    ${orderPojo.sellerMessage}
                                                </span>
                                        </p>    -->    
                                                </div>
                                        <div style="width: 220px; margin-left: 740px">
                                        <%--     <p class="price">
                                                <span>
                                                    平台促销抵扣：
                                                </span>
                                                <span>
                                                    (￥${orderPojo.usedPrice })
                                                </span>
                                            </p> --%>
                                            <p class="price" style="font-size: 14px">
                                                <span>
                                                    用户实付：
                                                </span>
                                                <span>
                                                    (￥${orderPojo.factPrice-orderPojo.usedPrice })
                                                </span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <h5 class="h5">
                                买家信息
                            </h5>
                            <div class="ui-box">
                                <div class="ui-box-container" id="modifyPannel">
                                    <div class="ui-box-content">
                                        收货地址：
                                        <span id="receiver_address">
                                            ${orderPojo.consigneeAddress }
                                        </span>
                                        ，收件人：
                                        <span id="receiver_name">
                                           ${orderPojo.consignee }
                                        </span>
                                        ，手机：
                                        <span id="receiver_phone">
                                            ${orderPojo.consigneePhone }
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <h5 class="h5">
                                物流信息
                            </h5>
                          <div class="shipment-detail" >
                                <a onclick="searchExpress()" class="">查询物流</a>&nbsp;&nbsp;&nbsp;
                                <!-- <a href="javascript:void(0);" class="view-ModifyShipmentLink">修改物流</a> -->
                                <form action="#" method="post">
                                <div class="hidden view-ModifyShipmentPanel">
                                    <select class="ui-input" name="company">
                                        <option value="shentong">
                                            申通
                                        </option>                                  
                                    </select>
                                    <input class="ui-input" name="out_sid" value="280424756057" style="width: 200px"/>
                                    <input type="submit" class="view-ModifyShipmentSave" value="保存" />
                                    <a href="javascript:void(0);" class="view-ModifyShipmentCancel">
                                        取消
                                    </a>
                                </div>
                                </form>
                                <div class="" id="expressBody">
                                </div>
                                <div class="order-entry" style="width: 900px;background: #FFFFFF;margin-bottom: 10px">
                                    <table class="pure-table" style="width: 900px;border: 1px solid #e3e3e3">
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                                <div style="color: #888">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

		<jsp:include page="../sellerFooter.jsp"></jsp:include>
        <script type="text/javascript">
    	function searchExpress(){
			var expressType ="${orderShipPojo.logisticsName}";
			var expressNo = "${orderShipPojo.logisticsNo}";
			if(expressType == "eyoubao"){
				expressType = "ems";
			}
			$.ajax({
				url:'expressSearch.do?type='+expressType+'&postid='+expressNo,
				type:'get',
				dataType: 'json',
				error: function(XMLHttpRequest, textStatus, errorThrown){
					alert(XMLHttpRequest.status);
		            alert(XMLHttpRequest.readyState);
		            alert(textStatus);
		     		alert('请求超时，请重新添加');
		    	},
		    	success: function(result){
		    		$("#expressBody").html("");
		    		valueExpress(result);
		    	}
			});
		}

		function valueExpress(result){
		var ddd="${orderShipPojo.logisticsName}";
		var nu="${orderShipPojo.logisticsNo}";
		var com="";
		if(ddd=="zhongtong"){
		com="中通";
		}else if(ddd=="shentong"){
		com="申通";
		}else if(ddd=="shunfeng"){
		com="顺丰";
		}else if(ddd=="yuantong"){
		com="圆通";
		}else if(ddd=="huitong"){
		com="汇通";
		}else if(ddd=="huitongkuaidi"){
		com="百世汇通";
		}else if(ddd=="tiantian"){
		com="天天";
		}else if(ddd=="yunda"){
		com="韵达";
		}else if(ddd=="dhl"){
		com="DHL";
		}else if(ddd=="zhaijisong"){
		com="宅急送";
		}else if(ddd=="debangwuliu"){
		com="德邦";
		}else if(ddd=="ems"){
		com="EMS国内";
		}else if(ddd=="eyoubao"){
		com="E邮宝";
		}else if(ddd=="guotongkuaidi"){
		com="国通";
		}else if(ddd=="longbangwuliu"){
		com="龙邦";
		}else if(ddd=="lianbangkuaidi"){
		com="联邦";
		}else if(ddd=="tnt"){
		com="TNT";
		}else if(ddd=="xinbangwuliu"){
		com="新邦";
		}else if(ddd=="zhongtiewuliu"){
		com="中铁";
		}else if(ddd=="zhongyouwuliu"){
		com="中邮";
		}else if(ddd=="youshuwuliu"){
		com="优速";
		}else if(ddd=="kuaijiesudi"){
		com="快捷";
		}else if(ddd=="youzhengguonei"){
		com="国内小包";
		}else if(ddd=="shenghuiwuliu"){
		com="盛辉";
		}
		var r = eval("(" + result + ")");
		var state = r.success;
		var message = "";
		if(state==true){
			state = r.status;
			if(state == "0"){
				message = "物流单号暂无结果";
			} else if(state == "3"){
				message = "在途";
			} else if(state == "4"){
				message = "揽件";
			} else if(state == "5"){
				message = "疑难";
			} else if(state == "6"){
				message = "已签收";
			} else if(state == "7"){
				message = "退签";
			} else if(state == "8"){
				message = "派件";
			} else if(state == "9"){
				message = "退回";
			}
		} else {
			message = r.reason;
		}
			$("#expressBody").append(
		             "<p style='color: #FF5482;padding-bottom: 8px'> "+
		             "快递公司："+com+
		             "&nbsp;&nbsp;&nbsp;"+
		             "运单号："+nu+
		             "</p>"+
					 "<p class=''>"+
			         "快递状态："+message+
			         "</p>"+
					 "<p class=''>"+
			         "-- 详细信息 --"+
			         "</p>" );
			var data = r.data;
			for(var i=0; i<data.length; i++){
				$("#expressBody").append(
				             "<p class=''>"+
				                 "<span class='ts'>"+data[i].time+
				                 "</span>"+data[i].context+"</p>");
			}
		}
		
			$(function(){
					
				$(".view-ModifyShipmentLink").on("click",function(){
					$(".view-ModifyShipmentPanel").show();
				});
				$(".view-ModifyShipmentCancel").on("click",function(){
					$(".view-ModifyShipmentPanel").hide();
				});
				$("#sbutton6").click(function(){		
			    if(tt.validate()){
				  document.getElementById("from5").submit();
			    }
		});
			})
		</script>
	</body>
</html>