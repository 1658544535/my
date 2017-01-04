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
  <div class="s_nav"><a>订单管理</a> &gt; <a href="orderRefund.do" >退货管理</a> &gt; <a href="#">编辑</a></div>
  <div class="h15"></div>
  <div>
  <form action="updateOrderRefund.do" method="post" id="from1">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input name="orderRefund.id" id="orderRefundId" value="${orderRefundPojo.id}" class="inputText" type="hidden" >
      <tr>
        <td align="right" class="grey" width="15%">商品名称:</td>	
        <td width="35%">
        <input type="text" name="orderRefund.productName"  value="${orderRefundPojo.productName}" class="floatLeft" id="ticketName"><span id="name_msgId"></span></td>
        
        <td align="right" class="grey"  width="15%">状态:</td>
        <td width="35%"><select name="orderRefund.status" id="ticketType"  class="floatLeft">
							<c:forEach items="${status}" var="status">
										<option value="${status.value}"<c:if test="${orderRefundPojo.status==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
				    </select><div id="status_mgId"></div></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">商品规格:</td>	
        <td width="35%">
        <input type="text" name="orderRefund.productModel"  value="${orderRefundPojo.productModel}" class="floatLeft" id="ticketName"><span id="productModel_msgId"></span></td>
           <td align="right" class="grey" width="15%">下单用户名:</td>	
        <td width="35%">
        <input type="text" name="orderRefund.loginname"  value="${orderRefundPojo.loginname}" class="floatLeft" id="ticketName"><span id="loginname"></span></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">商品原价:</td>	
        <td width="35%">
        <input type="text" name="orderRefund.stockPriceOld"  value="${orderRefundPojo.stockPriceOld}" class="floatLeft" id="ticketName"><span id="stock_price_old_msgId"></span></td>
        <td align="right" class="grey" width="15%">商品价格:</td>	
        <td width="35%">
        <input type="text" name="orderRefund.stockPrice" value="${orderRefundPojo.stockPrice}" class="floatLeft" id="ticketName"><span id="stockPrice"></span></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">退回数量:</td>	
        <td width="35%">
        <input type="text" name="orderRefund.refundNum"  value="${orderRefundPojo.refundNum}" class="floatLeft" id="ticketName"><span id="refundNum"></span></td>
       
        <td align="right" class="grey" width="15%">退货原因:</td>	
        <td width="35%">
        <textarea class="floatLeft" rows="10" cols="50" name="orderRefund.refundReason" id="content" >${orderRefundPojo.refundReason}</textarea>
        </td>
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
var adName =new tt.Field(" 商品名称 ","orderRefund.productName").setMsgId("name_msgId");
var adMoney =new tt.Field(" 商品规格 ","orderRefund.productModel").setMsgId("productModel_msgId");
var ticketPer =new tt.Field(" 商品原价 ","orderRefund.stockPriceOld").setMsgId("stock_price_old_msgId");
var refundNum =new tt.Field(" 退回数量 ","orderRefund.refundNum").setMsgId("refundNum");
var stockPrice =new tt.Field(" 商品价格 ","orderRefund.stockPrice").setMsgId("stockPrice");
var loginname =new tt.Field(" 下单用户名 ","orderRefund.loginname").setMsgId("loginname");

tt.vf.req.add(adName,adMoney,ticketPer,loginname,stockPrice,refundNum);
new tt.LV().set(0, 15).add(adMoney);
new tt.LV().set(0, 30).add(adName);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>