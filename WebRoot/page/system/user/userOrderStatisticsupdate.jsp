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
  <div class="s_nav"><a>财务管理</a> &gt; <a href="userOrderStatistics.do" >订单统计结算</a> &gt; <a href="#">编辑</a></div>
  <div class="h15"></div>
  <div>
  <form action="updateUserOrderStatistics.do" method="post" id="from1">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input name="userOrderStatistics.id" id="userOrderStatisticsId" value="${userOrderStatisticsPojo.id}" class="inputText" type="hidden" >
    <input name="userOrderStatistics.userId" id="userOrderStatisticsId" value="${userOrderStatisticsPojo.userId}" class="inputText" type="hidden" >
      <tr>
        <td align="right" class="grey" width="15%">商品名称:</td>	
        <td width="35%">
        <input type="text" name="userOrderStatistics.productName"  value="${userOrderStatisticsPojo.productName}" class="floatLeft" id="ticketName"><span id="name_msgId"></span></td>
        
        <td align="right" class="grey"  width="15%">状态:</td>
        <td width="35%"><select name="userOrderStatistics.status" id="ticketType"  class="floatLeft">
        						<c:forEach items="${status}" var="status">
										<option value="${status.value}"<c:if test="${userOrderStatisticsPojo.status==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
				    </select></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">用户昵称:</td>	
        <td width="35%">
        <input type="text" value="${sysLoginPojo.name}" class="floatLeft" id="ticketName" readOnly></td>
        
        <td align="right" class="grey"  width="15%">来源渠道:</td>
        <td width="35%"><select name="userOrderStatistics.channel" id="ticketType"  class="floatLeft">
        					<c:forEach items="${channel}" var="channel">
										<option value="${channel.value}"<c:if test="${userOrderStatisticsPojo.channel==channel.value}">selected="selected" </c:if>>${channel.name}</option>
							</c:forEach>
				    </select><div id="channel_mgId"></div></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">商品规格:</td>	
        <td width="35%">
        <input type="text" name="userOrderStatistics.productModel"  value="${userOrderStatisticsPojo.productModel}" class="floatLeft" id="ticketName"><span id="productModel_msgId"></span></td>
           <td align="right" class="grey" width="15%">库存ID:</td>	
        <td width="35%">
        <input type="text" name="userOrderStatistics.stockId"  value="${userOrderStatisticsPojo.stockId}" class="floatLeft" id="ticketName"><span id="stock_id_msgId"></span></td>
      </tr>
      
      <tr>
        <td align="right" class="grey" width="15%">商品原价:</td>	
        <td width="35%">
        <input type="text" name="userOrderStatistics.stockPriceOld"  value="${userOrderStatisticsPojo.stockPriceOld}" class="floatLeft" id="ticketName"><span id="stock_price_old_msgId"></span></td>
        <td align="right" class="grey" width="15%">商品价格:</td>	
        <td width="35%">
        <input type="text" name="userOrderStatistics.stockPrice" value="${userOrderStatisticsPojo.stockPrice}" class="floatLeft" id="ticketName"><span id="stock_price_msdgId"></span></td>
      </tr>
      
      <tr>
        <td align="right" class="grey" width="15%">数量:</td>	
        <td width="35%">
        <input type="text" name="userOrderStatistics.num"  value="${userOrderStatisticsPojo.num}" class="floatLeft" id="ticketName"><span id="num_msgId"></span></td>
        <td align="right" class="grey" width="15%">店铺ID:</td>	
        <td width="35%">
        <input type="text" name="userOrderStatistics.shopId" value="${userOrderStatisticsPojo.shopId}" class="floatLeft" id="ticketName"><span id="shop_id_msdgId"></span></td>
      </tr>
     <tr>
        <td align="right" class="grey" width="15%">商品ID:</td>	
        <td width="35%">
        <input type="text" name="userOrderStatistics.productId"  value="${userOrderStatisticsPojo.productId}" class="floatLeft" id="ticketName"><span id="product_id_msgId"></span></td>
        <td align="right" class="grey" width="15%">类型:</td>	
        <td width="35%">
        <input type="text" name="userOrderStatistics.type" value="${userOrderStatisticsPojo.type}" class="floatLeft" id="ticketName"><span id="type_msdgId"></span></td>
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
var adName =new tt.Field(" 商品名称 ","userOrderStatistics.productName").setMsgId("name_msgId");
var adMoney =new tt.Field(" 商品规格 ","userOrderStatistics.productModel").setMsgId("productModel_msgId");
var ticketType =new tt.Field(" 库存ID ","userOrderStatistics.stockId").setMsgId("stock_id_msgId");
var ticketPer =new tt.Field(" 商品原价 ","userOrderStatistics.stockPriceOld").setMsgId("stock_price_old_msgId");

tt.vf.req.add(adName,ticketType,adMoney,ticketPer);
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