<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">信息统计</a> &gt; <a href="#">淘竹马订单详情</a>
		</div>
		<div>
			<form action="" method="post" id="from1">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
					<tr>
						<td align="right" class="grey" width="15%">订单号：</td>
						<td width="35%">${orderPojo.orderNo}</td>
						<td align="right" class="grey" width="15%">商品总数量：</td>
						<td width="35%">${orderPojo.count}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">订单原价：</td>
						<td width="35%">${orderPojo.allPrice}</td>
						<td align="right" class="grey" width="15%">实收金额：</td>
						<td width="35%">${orderPojo.factPrice}</td>	
					</tr>
					
					<tr>
						<td align="right" class="grey" width="15%">运费:</td>
						<td width="35%">${orderPojo.espressPrice}</td>

						<td align="right" class="grey" width="15%">收货人:</td>
						<td width="35%">${orderPojo.consignee}</td>
					</tr>
					
					<tr>
						<td align="right" class="grey" width="15%">收货地址:</td>
						<td width="35%">${orderPojo.consigneeAddress}</td>
						<td align="right" class="grey" width="15%">收货人电话:</td>
						<td width="35%">${orderPojo.consigneePhone}</td>
					</tr>
				</table>

			</form>
		</div>
		<c:if test="${orderDetails.size()!=0}">
			<div>
				<table width="100%" border="1" class="Info_list_table">
					<tr>
						<th>商品名称</th>
						<th>商品价格</th>
						<th>数量</th>
	
					</tr>

					<c:forEach items="${orderDetails}" var="product">
						<tr> 
						<td align="center">${product.productName}</td>
						<td align="center">${product.stockPrice}</td>
						<td align="center">${product.num}</td>
						</tr>
					</c:forEach>
		
				</table>
			</div>
		</c:if>
		<div class="Btn_div">
				<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
		</div>	
	</div>
</body>
</html>