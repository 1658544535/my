<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav"><a>订单管理</a> &gt; <a href="orderShip.do" >发货管理</a> &gt; <a href="#">编辑</a></div>
  <div class="h15"></div>
  <div>
  <form action="updateOrderShip.do" method="post" id="from1">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input name="orderShip.id" id="orderShipId" value="${orderShipPojo.id}" class="inputText" type="hidden" >
    <input name="orderShip.userId" id="orderShipId" value="${orderShipPojo.userId}" class="inputText" type="hidden" >
    <input name="orderShip.orderId" id="orderShipId" value="${orderShipPojo.orderId}" class="inputText" type="hidden" >
       <tr>
        <td align="right" class="grey" width="15%">物流名称:</td>	
        <td width="35%">
        <input type="text" name="orderShip.logisticsName"  value="${orderShipPojo.logisticsName}" class="floatLeft" id="ticketName"><span id="logistics_name"></span></td>
        <td align="right" class="grey" width="15%">物流单号:</td>	
        <td width="35%">
        <input type="text" name="orderShip.logisticsNo"  value="${orderShipPojo.logisticsNo}" class="floatLeft" id="ticketName"><span id="logistics_no"></span></td>
        
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">发货人:</td>	
        <td width="35%">
        <input type="text" name="orderShip.consignor"  value="${orderShipPojo.consignor}" class="floatLeft" id="ticketName"><span id="consignor"></span></td>
        <td align="right" class="grey" width="15%">收货人:</td>	
        <td width="35%">
        <input type="text" name="orderShip.consignee"  value="${orderShipPojo.consignee}" class="floatLeft" id="ticketName"><span id="consignee"></span></td>
      </tr>
      
      <tr>
        <td align="right" class="grey" width="15%">发货地址:</td>	
        <td width="35%">
        <input type="text" name="orderShip.consignorAddress"  value="${orderShipPojo.consignorAddress}" class="floatLeft" id="ticketName"><span id="consignor_address"></span></td>
        <td align="right" class="grey" width="15%">收货地址:</td>	
        <td width="35%">
        <input type="text" name="orderShip.consigneeAddress" value="${orderShipPojo.consigneeAddress}" class="floatLeft" id="ticketName"><span id="consignee_address"></span></td>
      </tr>
      
      <tr>
        <td align="right" class="grey" width="15%">发货电话:</td>	
        <td width="35%">
        <input type="text" name="orderShip.shipPhone"  value="${orderShipPojo.shipPhone}" class="floatLeft" id="ticketName"><span id="ship_phone"></span></td>
        <td align="right" class="grey" width="15%">收货人电话:</td>	
        <td width="35%">
        <input type="text" name="orderShip.consigneePhone" value="${orderShipPojo.consigneePhone}" class="floatLeft" id="ticketName"><span id="consignee_phone"></span></td>
      </tr>
     <tr>
        <td align="right" class="grey" width="15%">订单号:</td>	
        <td width="35%">
        <input type="text" name="orderShip.orderNo"  value="${orderShipPojo.orderNo}" class="floatLeft" id="ticketName"><span id="order_no"></span></td>
        <td align="right" class="grey"  width="15%">收货方式:</td>
        <td width="35%"><select name="orderShip.consigneeType" id="ticketType"  class="floatLeft">
							<option value="1" <c:if test="${orderShipPojo.consigneeType==1}">selected="selected" </c:if> >快递</option>
							<option value="2" <c:if test="${orderShipPojo.consigneeType==2}">selected="selected" </c:if>  >邮局</option>
				    </select><div id="status_mgId"></div></td>
      </tr>
      <tr>
      	<td align="right" class="grey"  width="15%">订单状态:</td>
        <td width="35%"><select name="orderShip.orderStatus" id="ticketType"  class="floatLeft">
							<option value="1" <c:if test="${orderShipPojo.orderStatus==1}">selected="selected" </c:if> >待发货</option>
							<option value="2" <c:if test="${orderShipPojo.orderStatus==2}">selected="selected" </c:if>  >已发货</option>
							<option value="3" <c:if test="${orderShipPojo.orderStatus==3}">selected="selected" </c:if>  >已签收</option>
				    </select><div id="status_mgId"></div></td>
        <td align="right" class="grey"  width="15%">状态:</td>
        <td width="35%"><select name="orderShip.status" id="ticketType"  class="floatLeft">
							<option value="1" <c:if test="${orderShipPojo.status==1}">selected="selected" </c:if> >已审核</option>
							<option value="0" <c:if test="${orderShipPojo.status==0}">selected="selected" </c:if>  >未审核</option>
				    </select><div id="status_mgId"></div></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">买家留言:</td>	
        <td>
        <textarea class="floatLeft" rows="10" cols="60" name="orderShip.buyerMessage" id="content" >${orderShipPojo.buyerMessage}</textarea><span id="buyer_message"></span></td>
      </tr>
           
    </table>
  
    </form>
  </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton"/>
  </div>
</div>

</body>
</html>


<script>
var logistics_name =new tt.Field(" 物流名称 ","orderShip.logisticsName").setMsgId("logistics_name");
var logistics_no =new tt.Field(" 物流单号 ","orderShip.logisticsNo").setMsgId("logistics_no");
var consignor =new tt.Field(" 发货人","orderShip.consignor").setMsgId("consignor");
var consignor_address =new tt.Field(" 发货地址 ","orderShip.consignorAddress").setMsgId("consignor_address");
var ship_phone =new tt.Field(" 发货电话","orderShip.shipPhone").setMsgId("ship_phone");
var consignee =new tt.Field(" 收货人","orderShip.consignee").setMsgId("consignee");
var consignee_address =new tt.Field(" 收货地址","orderShip.consigneeAddress").setMsgId("consignee_address");
var consignee_phone =new tt.Field(" 收货人电话","orderShip.consigneePhone").setMsgId("consignee_phone");
var order_no =new tt.Field(" 订单号","orderShip.orderNo").setMsgId("order_no");

tt.vf.req.add(logistics_name,logistics_no,consignor,consignor_address,ship_phone,consignee,consignee_address,consignee_phone,order_no);
new tt.LV().set(0, 25).add(logistics_name);
new tt.LV().set(0, 30).add(logistics_no);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>