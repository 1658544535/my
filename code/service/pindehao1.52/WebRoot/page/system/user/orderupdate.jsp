<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">
function manySend(href){
	var form = document.getElementById("form8");
	form.action = href;//传想要跳转的路径
	form.submit();
} 

function cancel(val)
{
	if(confirm("你真的想取消该条订单么？"))
		{
			 manySend("isCancel.do?order.id="+val);        
			 return true;
		}else{
			return ;
		}
}

function returnGoods(val)
{
	if(confirm("你确定要退货么？"))
		{
			 manySend("goReturnGoods.do?order.id="+val);        
			 return true;
		}else{
			return ;
		}
}

</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a>订单管理</a> &gt; <a href="order.do?os=${os}">用户订单</a> &gt; <a
				href="#">详情</a>
		</div>
		<div class="h15"></div>
		<!-- 订单信息 -->
		<div>
			<form action="updateOrder.do?os=${os}&guide=${guide}" method="post"  id="from5">
				<input name="pageNoVal" id="" value="${pageNoVal}" type="hidden">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
					<input name="formParam" id="" value="${formParam}" class="inputText" type="hidden">
					<input name="order.id" id="orderId" value="${orderPojo.id}" class="inputText" type="hidden">
					<input name="order.orderNo" id="orderNo" value="${orderPojo.orderNo}" class="inputText" type="hidden">
					<input name="order.sourceId" id="sourceId" value="${orderPojo.sourceId}" class="inputText" type="hidden">
					<input name="order.activityId" id="activityId" value="${orderPojo.activityId}" class="inputText" type="hidden">
					<tr>
						<td align="right" class="grey" width="15%">订单号:</td>
						<td width="35%">${orderPojo.orderNo}
							<span id="orderNo">
								<c:if test="${orderPojo.isCancelOrder==1}">
									<font size="1" color="red">此订单已取消</font>
								</c:if>
							</span>
						</td>
						<td align="right" class="grey" width="15%">下单时间:</td>
						<td width="35%">${orderPojo.creatDateString}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">订单状态:</td>
   						<td>
							<c:forEach items="${orderStatus}" var="orderStatus">
								<c:if test="${orderPojo.orderStatus==orderStatus.value}">
									<label  name="order.orderStatus" class="floatLeft">${orderStatus.name}</label>
								</c:if>
							</c:forEach>
							<span id="factPrice"></span>
					    </td>
						<td align="right" class="grey" width="15%">付款时间:</td>
						<td width="35%">${orderPojo.paymentDateStr}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">支付状态:</td>
						<td>
							<c:forEach items="${payStatus}" var="payStatus">
								<c:if test="${orderPojo.payStatus==payStatus.value}">
								<label name="order.payStatus" class="floatLeft">${payStatus.name}</label>
								</c:if>
							</c:forEach>
							<span id="factPrice"></span>
						</td>
						<td align="right" class="grey" width="15%">参团时间:</td>
						<td width="35%">${orderPojo.groupDateStr}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">快递公司:</td>
						<td>
						<!-- <input value="${orderPojo.logisticsName}" name="order.logisticsName"/> -->
						<select name="order.logisticsName">
							<option value="zhongtong"<c:if test="${orderPojo.logisticsName=='中通快递'}">selected="true"</c:if>>中通快递</option>
							<option value="shunfeng"<c:if test="${orderPojo.logisticsName=='顺丰快递'}">selected="true"</c:if>>顺丰快递</option>
							<option value="shentong"<c:if test="${orderPojo.logisticsName=='申通快递'}">selected="true"</c:if>>申通快递</option>
							<option value="yuantong"<c:if test="${orderPojo.logisticsName=='圆通快递'}">selected="true"</c:if>>圆通快递</option>
							<option value="huitongkuaidi"<c:if test="${orderPojo.logisticsName=='百世汇通'}">selected="true"</c:if>>百世汇通</option>
							<option value="tiantian"<c:if test="${orderPojo.logisticsName=='天天快递'}">selected="true"</c:if>>天天快递</option>
							<option value="yunda"<c:if test="${orderPojo.logisticsName=='韵达快递'}">selected="true"</c:if>>韵达快递</option>
							<option value="dhl"<c:if test="${orderPojo.logisticsName=='DHL'}">selected="true"</c:if>>DHL</option>
							<option value="zhaijisong"<c:if test="${orderPojo.logisticsName=='宅急送'}">selected="true"</c:if>>宅急送</option>
							<option value="yunda"<c:if test="${orderPojo.logisticsName=='韵达快递'}">selected="true"</c:if>>韵达快递</option>
							<option value="debangwuliu"<c:if test="${orderPojo.logisticsName=='德邦快递'}">selected="true"</c:if>>德邦快递</option>
							<option value="ems"<c:if test="${orderPojo.logisticsName=='EMS国内'}">selected="true"</c:if>>EMS国内</option>
							<option value="eyoubao"<c:if test="${orderPojo.logisticsName=='E邮宝'}">selected="true"</c:if>>E邮宝</option>
							<option value="guotongkuaidi"<c:if test="${orderPojo.logisticsName=='国通快递'}">selected="true"</c:if>>国通快递</option>
							<option value="longbangwuliu"<c:if test="${orderPojo.logisticsName=='龙邦快递'}">selected="true"</c:if>>龙邦快递</option>
							<option value="lianbangkuaidi"<c:if test="${orderPojo.logisticsName=='联邦快递'}">selected="true"</c:if>>联邦快递</option>
							<option value="tnt"<c:if test="${orderPojo.logisticsName=='TNT'}">selected="true"</c:if>>TNT</option>
							<option value="xinbangwuliu"<c:if test="${orderPojo.logisticsName=='新邦快递'}">selected="true"</c:if>>新邦快递</option>
							<option value="zhongtiewuliu"<c:if test="${orderPojo.logisticsName=='中铁快递'}">selected="true"</c:if>>中铁快递</option>
							<option value="zhongyouwuliu"<c:if test="${orderPojo.logisticsName=='中邮快递'}">selected="true"</c:if>>中邮快递</option>
							<option value="youshuwuliu"<c:if test="${orderPojo.logisticsName=='优速快递'}">selected="true"</c:if>>优速快递</option>
							<option value="kuaijiesudi"<c:if test="${orderPojo.logisticsName=='快捷快递'}">selected="true"</c:if>>快捷快递</option>
							<option value="youzhengguonei"<c:if test="${orderPojo.logisticsName=='国内小包'}">selected="true"</c:if>>国内小包</option>
							<option value="quanfengkuaidi"<c:if test="${orderPojo.logisticsName=='全峰快递'}">selected="true"</c:if>>全峰快递</option>
							<option value="jd"<c:if test="${orderPojo.logisticsName=='京东快递'}">selected="true"</c:if>>京东快递</option>
							<option value="annengwuliu"<c:if test="${orderPojo.logisticsName=='安能物流'}">selected="true"</c:if>>安能物流</option>
							<option value="nanjingshengbang"<c:if test="${orderPojo.logisticsName=='晟邦物流'}">selected="true"</c:if>>晟邦物流</option>
							<option value="wanxiangwuliu"<c:if test="${orderPojo.logisticsName=='万象-自营'}">selected="true"</c:if>>万象-自营</option>
						</select>
						</td>
						<td align="right" class="grey" width="15%">发货时间:</td>
						<td width="35%">${orderPojo.sendDateStr}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">快递单号:</td>
						 <td><input value="${orderPojo.logisticsNo}" name="order.logisticsNo"/></td>
						<td align="right" class="grey" width="15%">收货时间:</td>
						<td width="35%">${orderPojo.confirmDateStr}</td>	
					</tr>
                    <tr>
						<td align="right" class="grey" width="15%">售后状态:</td>
						 <td><label class="floatLeft">${orderPojo.isCancelOrderName}</label></td>
						<td align="right" class="grey" width="15%">订单来源:</td>
						<td width="35%">${orderPojo.sourceName}</td>	
					</tr>
                    <tr>
						<td align="right" class="grey" width="15%">优惠券码:</td>
						 <td><label class="floatLeft">${orderPojo.couponNo}</label></td>
						<td align="right" class="grey" width="15%">优惠金额:</td>
						<td width="35%">${orderPojo.usedPrice}</td>	
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">订单利润:</td>
						 <td><label class="floatLeft">${orderPojo.orderProfit}</label></td>
						<td align="right" class="grey" width="15%">商家扣款:</td>
						<td width="35%"><input value='${orderPojo.sellerDeduct}' name="order.sellerDeduct"/></td>	
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">自动确认收货时间:</td>
						<td width="35%"><input type="text" name="order.autoRecTime" id="order.autoRecTime" value="${orderPojo.autoRecTimeStr}" 
						readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></td>
						<td align="right" class="grey" width="15%">商家货款:</td>
						<td width="35%">${orderPojo.sellerGoodsPrice}</td>	
					</tr>
					<tr>
						<td align="center" class="grey" width="15%"  colspan="4"><input type="button" class="ok_btn" value="修改" id="sbutton5" /></td>
					</tr>
				</table>
				<table width="100%" border="1" class="Info_list_table">
					<tr>
						<td align="right" class="grey" width="15%"  colspan="4">平台留言:</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="6%">平台留言:</td>
						<td width="35%" class="grey">
							<textarea class="floatLeft" rows="5" cols="50" name="order.platformMsg" id="content">${orderPojo.platformMsg}</textarea>
							<span id="remarks"></span>
						</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%"  colspan="4"><input type="button" class="ok_btn" value="修改" id="sbutton6" /></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 订单商品信息-->
		<c:if test="${orderDetails.size()!=0}">
			<div>
				<table width="100%" border="1" class="Info_list_table">
					<tr>
						<th>商品名称</th>
						<th>商品图片</th>
						<th>货号</th>
						<th>规格</th>
					<!--<th>商品原价</th>-->
						<th>订单总价</th>
						<th>数量</th>
						<th>实付金额</th>
					<!--<th>操作</th>-->
					</tr>
					<c:forEach items="${orderDetails}" var="trealProject">
						<tr></tr>
				        <tr> 
				        <td align="center"><a target="_blank" href="<%=request.getContextPath() %>/goProductDetail.do?productPojo.id=${trealProject.productId}">${trealProject.productName}</a></td>
						<td align="center">	<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/${trealProject.productImages}' height='50px' /> </td>
						<!--<td align="center">${trealProject.productName}</td>-->
						<td align="center">${trealProject.productNum}</td>
						<td align="center">${trealProject.businessCode}</td>
						<!--<td align="center">${trealProject.stockPriceOld}</td>-->
						<td align="center">${orderPojo.allPrice}</td>
						<td align="center">${trealProject.num}</td>
						<td align="center">${orderPojo.factPrice}</td>
						<!--	<td align="center"><a class="edit_btn" href="goReturnGoods.do?order.id=${orderPojo.id}">退货</a></td>-->
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
		<!-- 订单状态-->
		<div>
			<form action="updateOrder.do?os=${os}&guide=${guide}" method="post" id="from1">
			<input name="pageNoVal" id="" value="${pageNoVal}" type="hidden">
			<input name="formParam" id="" value="${formParam}" class="inputText" type="hidden">
			<input name="order.id" id="orderId" value="${orderPojo.id}" class="inputText" type="hidden">
			<input name="order.orderNo" id="orderNo" value="${orderPojo.orderNo}" class="inputText" type="hidden">
			<input name="order.sourceId" id="orderNo" value="${orderPojo.sourceId}" class="inputText" type="hidden">
			<input name="order.activityId" id="orderNo" value="${orderPojo.activityId}" class="inputText" type="hidden">
			<input name="order.source" id="orderNo" value="${orderPojo.source}" class="inputText" type="hidden">
			<input name="order.userId" id="orderNo" value="${orderPojo.userId}" class="inputText" type="hidden">
			<table width="100%" border="1" class="Info_list_table">
				<tr><td align="right" class="grey" width="15%"  colspan="4">修改状态:</td></tr>
				<tr>
					<td align="right" class="grey" width="15%">订单状态:</td>
				   	 	<s:if test="#session.role.roleId==1||#session.role.roleId==5"> 
	  						<td>
	  							<select name="order.orderStatus" class="floatLeft">
									<c:forEach items="${orderStatus}" var="orderStatus">
										<option value="${orderStatus.value}"
											<c:if test="${orderPojo.orderStatus==orderStatus.value}">selected="selected" </c:if>>${orderStatus.name}</option>
									</c:forEach>
							   </select>
							</td>		
					   </s:if>
					   <s:else>  
						<td width="35%">
							<c:forEach items="${orderStatus}" var="orderStatus">
							<c:if test="${orderPojo.orderStatus==orderStatus.value}"><label  name="order.orderStatus" class="floatLeft">${orderStatus.name}</label></c:if>
							</c:forEach>
							<span id="factPrice"></span>
					    </td>
					   </s:else>
					<td class="grey" width="15%">支付状态:</td>
						<s:if test="#session.role.roleId==1||#session.role.roleId==5">
							<td>
								<select name="order.payStatus" class="floatLeft">
									<c:forEach items="${payStatus}" var="payStatus">
										<option value="${payStatus.value}"
											<c:if test="${orderPojo.payStatus==payStatus.value}">selected="selected" </c:if>>${payStatus.name}</option>
									</c:forEach>
								</select>
							</td>		
						</s:if>
						<s:else> 
						<td width="35%">
							<c:forEach items="${payStatus}" var="payStatus">
								<c:if test="${orderPojo.payStatus==payStatus.value}">
								<label name="order.payStatus" class="floatLeft">${payStatus.name}</label>
								</c:if>
							</c:forEach>
							<span id="factPrice"></span>
						</td>
						</s:else>
				</tr>
				<tr><td align="right" class="grey" width="15%"  colspan="4"><input type="button" class="ok_btn" value="修改" id="sbutton" /></td></tr>
			</table>
			</form>
		</div>
		<!-- 订单支付信息-->
		<div>
			<table width="100%" border="1" class="Info_list_table">
				<tr>
					<td align="right" class="grey" width="15%"  colspan="4">支付信息:</td>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${orderPojo.payMethod == 1}">
							<td align="right" class="grey" width="15%">支付宝单号:</td>
							<td width="35%">${orderPojo.outTradeNo}</td>
						</c:when>
						<c:when test="${orderPojo.payMethod == 2 || orderPojo.payMethod == 8}">
							<td align="right" class="grey" width="15%">微信支付单号:</td>
							<td width="35%">${orderPojo.outTradeNo}</td>
						</c:when>
						<c:when test="${orderPojo.payMethod == 4}">
							<td align="right" class="grey" width="15%">支付方式:</td>
							<td width="35%">钱包全额支付</td>
						</c:when>
						<c:when test="${orderPojo.payMethod == 5}">
							<td align="right" class="grey" width="15%">银联支付单号:</td>
							<td width="35%">${orderPojo.outTradeNo}</td>
						</c:when>
						<c:when test="${orderPojo.payMethod == 6}">
							<td align="right" class="grey" width="15%">苹果支付单号:</td>
							<td width="35%">${orderPojo.outTradeNo}</td>
						</c:when>
						<c:otherwise>
							<td align="right" class="grey" width="15%">支付方式</td> 
							<td width="35%">货到付款</td>
						</c:otherwise>
					</c:choose>
					<td align="right" class="grey" width="15%">流水号:</td>
					<td width="35%">${orderPojo.tradeNo}</td>
				</tr>
			</table>
		</div>
		<!-- 买家地址信息-->
		<div>
			<form action="updateOrder.do?os=${os}&guide=${guide}" method="post" id="from2">
		    	<input name="pageNoVal" id="" value="${pageNoVal}" type="hidden">
				<input name="formParam" id="" value="${formParam}" class="inputText" type="hidden">
				<input name="order.id" id="orderId" value="${orderPojo.id}" class="inputText" type="hidden">
				<input name="order.orderNo" id="orderNo" value="${orderPojo.orderNo}" class="inputText" type="hidden">
				<table width="100%" border="1" class="Info_list_table">
					<tr><td align="right" class="grey" width="15%"  colspan="4">买家地址信息:</td></tr>
						<tr>
							<td align="right" class="grey" width="15%">收货人:</td>
							<td width="35%"><input type="text" name="order.consignee"
								value="${orderPojo.consignee}" class="floatLeft" id="ticketName"><span
								id="consignee"></span></td>
							<td align="right" class="grey" width="15%">收货地址:</td>
							<td width="35%"><input type="text"  size="90"
								name="order.consigneeAddress"
								value="${orderPojo.consigneeAddress}" class="floatLeft"
								id="ticketName"><span id="consigneeAddress"></span></td>
						</tr>
						<tr>
							<td align="right" class="grey" width="15%">收货人电话:</td>
							<td width="35%" class="grey"><input type="text" name="order.consigneePhone" value="${orderPojo.consigneePhone}" class="floatLeft" id="ticketName"><span id="consigneePhone"></span></td>
							<td align="right" class="grey" width="15%"></td>
							<td width="35%" class="grey"></td>
						</tr>
						<tr><td align="right" class="grey" width="15%"  colspan="4"><input type="button" class="ok_btn" value="修改" id="sbutton2" /></td></tr>
				</table>
			</form>
			<form action="updateOrder.do?os=${os}&guide=${guide}" method="post" id="from3">
				<input name="pageNoVal" id="" value="${pageNoVal}" type="hidden">
				<input name="formParam" id="" value="${formParam}" class="inputText" type="hidden">
				<input name="order.id" id="orderId" value="${orderPojo.id}" class="inputText" type="hidden">
				<input name="order.orderNo" id="orderNo" value="${orderPojo.orderNo}" class="inputText" type="hidden">
				<table width="100%" border="1" class="Info_list_table">
					<tr><td align="right" class="grey" width="15%"  colspan="4">客服留言:</td></tr>
					<tr><td align="right" class="grey" width="6%">客服留言:</td>
						<td width="35%" class="grey"><textarea class="floatLeft" rows="5"
								cols="50" name="order.remarks" id="content">${orderPojo.remarks}</textarea>
							<span id="remarks"></span></td>
										<td align="right" class="grey" width="6%">客户订单留言:</td>
						<td width="35%" class="grey"><textarea class="floatLeft" rows="5"
								cols="50" name="order.csRemarks" id="content">${orderPojo.csRemarks}</textarea>
							<span id="remarks"></span></td>
					</tr>
						
					<tr>
						<td align="right" class="grey" width="15%"  colspan="4"><input type="button" class="ok_btn" value="修改" id="sbutton3" /></td>
					</tr>
				</table>
			</form>
			<form action="updateOrder.do?os=${os}&guide=${guide}" method="post" id="from4">
				<input name="pageNoVal" id="" value="${pageNoVal}" type="hidden">
				<input name="formParam" id="" value="${formParam}" class="inputText" type="hidden">
				<input name="order.id" id="orderId" value="${orderPojo.id}" class="inputText" type="hidden">
				<input name="order.orderNo" id="orderNo" value="${orderPojo.orderNo}" class="inputText" type="hidden">
				<table width="100%" border="1" class="Info_list_table">
					<tr><td align="right" class="grey" width="15%"  colspan="4">备注:</td></tr>
					<tr>
						<td align="right" class="grey" width="15%">用户:</td>
						<td width="35%"  class="grey">${orderPojo.userName}</td>
						<td align="right" class="grey" width="15%">创建时间:</td>
						<td width="35%"  class="grey">${orderPojo.updateDateStr}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="6%">备注:</td>
						<td width="35%" class="grey">
							<textarea class="floatLeft" rows="5" cols="50" name="order.buyerMessage" id="content">${orderPojo.buyerMessage}</textarea>
							<span id="remarks"></span>
						</td>
						<td align="right" class="grey" width="6%"></td>
						<td width="35%" class="grey"></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%"  colspan="4"><input type="button" class="ok_btn" value="修改" id="sbutton4" /></td>
					</tr>
				</table>
			</form>
			<table width="100%" border="1" class="Info_list_table">
				<td align="right" class="grey" width="15%"  colspan="4">退货/退款信息:</td>
                <tr>
					<td align="right" class="grey" width="15%">退款id:</td>
					<td width="35%">${userOrderRefundPojo.id}</td>
					<td align="right" class="grey" width="15%">退款金额:</td>
					<td width="35%">${orderPojo.factPrice}</td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">申请时间:</td>
					<td width="35%">${userOrderRefundPojo.creatDateString}</td>
					<td align="right" class="grey" width="15%">申请人:</td>
					<td width="35%">${userOrderRefundPojo.userName}</td>
				</tr>
				<tr>
				<td align="right" class="grey" width="15%">备注:</td>
                      <td width="35%">${userOrderRefundPojo.refundReason}</td>		
                      <td align="right" class="grey" width="15%">退款状态:</td>
				<td width="35%">${userOrderRefundPojo.statusName}</td>	
				</tr>
			</table>
		</div>
		<div class="Btn_div">
				<button type="input" class="back_btn" onclick="goOrderManange()">返回</button>
		</div>
		<script type="text/javascript">
			function goOrderManange(){
				var formParam = "${formParam}";
				window.location.href = "order.do?os=&pageNoVal="+"${pageNoVal}&"+formParam;
			}
		</script>
</body>
</html>
<script>
//alert(${orderPojo.isCancelOrder});
var consignee =new tt.Field(" 收货人 ","cart.consignee").setMsgId("consignee");
var consigneeAddress =new tt.Field(" 收货地址 ","cart.consigneeAddress").setMsgId("consigneeAddress");
var consigneePhone =new tt.Field(" 收货人电话 ","cart.consigneePhone").setMsgId("consigneePhone");
var orderNo =new tt.Field(" 订单号 ","cart.orderNo").setMsgId("orderNo");
var serialNumber =new tt.Field(" 流水号 ","cart.serialNumber").setMsgId("serialNumber");
var buyerMessage =new tt.Field(" 买家留言 ","cart.buyerMessage").setMsgId("buyerMessage");
var consignor =new tt.Field(" 发货人 ","cart.consignor").setMsgId("consignor");
var consignorAddress =new tt.Field(" 发货地址 ","cart.consignorAddress").setMsgId("consignorAddress");
var shipPhone =new tt.Field(" 发货人电话 ","cart.shipPhone").setMsgId("shipPhone");

tt.vf.req.add(consignee,consigneeAddress,consigneePhone,orderNo,serialNumber,buyerMessage,consignor,consignorAddress,shipPhone);

	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
		$("#sbutton2").click(function(){		
			if(tt.validate()){
				document.getElementById("from2").submit();
			}
		});
		$("#sbutton3").click(function(){		
			if(tt.validate()){
				document.getElementById("from3").submit();
			}
		});
		$("#sbutton4").click(function(){		
			if(tt.validate()){
				document.getElementById("from4").submit();
			}
		});
		$("#sbutton5").click(function(){		
			if(tt.validate()){
				document.getElementById("from5").submit();
			}
		});
		$("#sbutton6").click(function(){		
			if(tt.validate()){
				document.getElementById("from5").submit();
			}
		});
	});
	
</script>