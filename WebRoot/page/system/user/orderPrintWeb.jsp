<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- saved from url=(0071)http://gz-ugarden.com/admin/index.php?module=order_print&order_id=22410 -->

<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/common.css" rel="stylesheet" type="text/css">
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/highslide.css" rel="stylesheet" type="text/css">
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/list_style.css" rel="stylesheet" type="text/css">
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/from.css" rel="stylesheet" type="text/css">

</head>
<body>

<title>订单-1426586959</title>
<style type="text/css">
body{font-size:18px;}
</style>
<style type="text/css" media="print">
.noprint{display : none }
</style>


<script>
function show()
{
window.print();
}

</script>

	<table width="650px" style="/* table-layout:fixed; */">
		<colgroup>
			<col style="width:15%;">
			<col>
			<col>
			<col style="width:40%;">
		</colgroup>
		<tbody><tr>
			<td colspan="4">
				<div class="noprint">
                   <a href="javascript:window.print();">[Print]</a>
                </div>
			</td>
		 </tr>
		 </tbody><tbody>
		 	<tr>
                <td colspan="4" style=" text-align:center;margin-left: 230px; font-size: 14px;">
		<h2 tyle="/* margin-left: 230px; */">淘竹马发货清单 <span style="font-size:14px;margin-left: 39px;"></span><span style="">
		</span></h2>
              </td>

            </tr>

		 	<tr style="height:15px;">
                <td colspan="4" style="white-space: nowrap;overflow: hidden;">-----------------------------------------------------------------------------------------------------------------------------------</td>
            </tr>
			<tr>
				<td align="right" style="width:80px;">
					收&nbsp;货&nbsp;人：
				</td>
				<td>
					${orderPojo.consignee}</td>
				<td align="right" style="width:150px;"></td>
				<td style="float:right;margin-right: 10px;">
					联系方式：${orderPojo.consigneePhone}</td>
			</tr>
			<tr>
				<td align="right">
			     	订&nbsp;单&nbsp;号：
				</td>
				<td>
					${orderPojo.orderNo}</td>
				<td align="right" style="width:150px;"></td>
				<td style="float:right;margin-right: 29px;white-space: nowrap;">订购日期：${orderPojo.creatDateString}</td>
			</tr>
			<tr>
			<td align="right" style="white-space:nowrap;">
			     	详细地址：
				</td>
				<td style="width:200px;">
					${orderPojo.consigneeAddress}</td>
				<td align="right" style="width:150px;"></td>
				<td style="float:right;margin-right: 29px;">快递公司：${orderPojo.logisticsName}</td>
			</tr>
			<tr>
			<td align="right">
			订单备注：
				</td>
				<td>
					${orderPojo.buyerMessage}</td>
			     	
				<td align="right" style="width:150px;"></td>
				<td style="float:right;margin-right: 29px;">快递单号：${orderPojo.logisticsNo}</td>
			</tr>
			<tr>
			<td align="right">
			
				</td>
				<td>
					</td>
			     	
				<td align="right" style="width:150px;"></td>
				<td style="float:right;margin-right: 29px;">送货方式：${orderPojo.consigneeTypeName}</td>
			</tr>
			<!--<tr>
			<td align="right">
			支付单号：
				</td>
				<td>
					${orderPojo.outTradeNo}</td>
			     	
			</tr>-->
			<tr style="height:15px;">
                <td colspan="4" style="white-space: nowrap;overflow: hidden;">-----------------------------------------------------------------------------------------------------------------------------------</td>
            </tr>
            <tr>
				<td colspan="4">
					<table width="100%">
            			<tbody><tr align="center">
            				<td align="left">名称</td>
            				<!--<td>规格</td>-->
            				<td align="center">品牌</td>
            				<td align="center">货号</td>
            				
            				<td align="center">数量</td>
            				<td style="text-align: right;">销售单价</td>
            				<!--<td style="text-align: right;">合计</td>-->
            			</tr>
            			<c:forEach items="${orderDetails}" var="trealProject">
						<tr>
							<td align="left" width="500px">${trealProject.productName}</td>
							<!--<td align="center">250g</td>-->
							<td align="center" width="100px">${trealProject.shopName}</td>
							<td align="center" width="100px">${trealProject.productNum}</td>
							
							<td align="center" width="60px">${trealProject.num}</td>
							<td align="right" style="text-align: right;" width="60px">￥${trealProject.stockPrice}</td>
							<!--<td align="right">￥18</td>-->
						</tr>
						</c:forEach>
						<tr>
						<td height="50px">  </td>
						</tr>
						
						<tr>

							<td colspan="4" align="right" valign="top"><b>小计:</b></td>
							<td align="right">￥${orderPojo.allPrice}</td>

						</tr>
						<!--
						<tr>

							<td colspan="4" align="right" valign="top"><b>运费:</b></td>
							<td align="right">￥${orderPojo.espressPrice}</td>

						</tr>
						-->
            		</tbody></table>
            	</td>
            </tr>
            <tr style="height:15px;">
                <td colspan="4" style="white-space: nowrap;overflow: hidden;">-----------------------------------------------------------------------------------------------------------------------------------</td>
            </tr>
            <tr>

            	<td colspan="2" align="left"></td>
            	<td align="right"></td>
            	<td valign="top">
            	</td>
            </tr>

		</tbody>

	</table>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <span style="margin-left: 150px;">实际付款:${orderPojo.factPrice}</span>
<br>
 
                ---------------------------------------------------------------------------------------------------------------------
  <table width="650px" style="table-layout:fixed;">
   <tr>
				<td colspan="4">
					<table width="100%">
            			<tbody><tr align="center">
            				<td align="left">仓库：</td>
            				
            				<td>收款确认：</td>
            				
            				
            				
            				<td style="text-align: right;">打单人：</td>
            			
            			</tr>          
           </tbody>
            
</table>

</body>