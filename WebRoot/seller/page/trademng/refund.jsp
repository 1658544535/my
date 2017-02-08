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
		<script src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<style>
			.disNone{
				display:none;
			}
		</style>
    </head>
	<body>
		<jsp:include page="../sellerHeader.jsp"></jsp:include>
		<div id="container">
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <style>
                           .u-icon-fenxiao{display:inline-block;*display:inline;*zoom:1;height:14px;line-height:14px;color:#fff;background:#ffae2e;border:1px solid #fa9a12;border-radius:2px;padding:0 3px;}.ol-b-item-info{width:344px;}.ol-b-item-price.pink{text-align:left;}
                        </style>
                        <h1 class="seller-title">
                            售后管理
                            <!-- <a style="font-size: 14px;margin-left: 15px;" href="#"
                            target="_blank">
                                退货/退款帮助说明
                            </a> -->
                        </h1>
                        <div class="p20">
                            <div class="ui-box seller-search-form">
                                  <!--<div class="ui-box-head">
                                    <h3 class="ui-box-head-title">
                                        售后查询
                                    </h3>
                                    <a href="#" class="ui-box-head-text underline">
                                        查看所有待处理申请
                                    </a>
                                </div>-->
                                <div class="ui-box-container">
                                    <form action="goRefundWeb.do" method="post" accept-charset="utf-8" id="sysform">
                                        <div class="ui-box-content">
                                            <!-- 
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    服务编号
                                                </label>
                                                <input class="ui-input" type="text" name="refund_id" value="">
                                            </div> 
                                            -->
<%--                                    <div class="ui-form-item">
                                      <label for="" class="ui-label" style="width: 75px;">
                                               申请客服介入
                                                </label>
                                                <select class="ui-select" name="userOrderRefundPojo.serviceInvolved" id="serviceInvolved">
                                                	<option value="" selected="">
                                                        全部
                                                    </option>
                                                    <option value=2>
                                                        是
                                                    </option>
                                                    <option value=1>
                                                        否
                                                    </option>
                                                </select>  
                                           </div> --%>    
                                           <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    订单号
                                                </label>
                                                <input class="ui-input" type="text" name="userOrderRefundPojo.orderNo" value="">
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    商品货号
                                                </label>
                                                <input class="ui-input" type="text" name="userOrderRefundPojo.productNum" value="">
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    收货手机
                                                </label>
                                                <input class="ui-input" type="text" name="userOrderRefundPojo.consigneePhone" value="">
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    退款编号
                                                </label>
                                                <input class="ui-input" type="text" name="userOrderRefundPojo.idStr" value="">
                                            </div>
                                       <div class="ui-form-item">         
                                                 <label for="" class="ui-label">
                                                    售后状态
                                                </label>
                                                <%-- <select class="ui-select" name="userOrderRefundPojo.status" id="status">
                                                    <option value="0" selected="">
                                                        全部
                                                    </option>
                                                    <option value="1">
                                                        审核中
                                                    </option>
                                                    <option value="2">
                                                        请退货
                                                    </option>
                                                    <option value="3">
                                                        退货中
                                                    </option>
                                                    <option value="4">
                                                        退货成功
                                                    </option>
                                                    <option value="5">
                                                        退货失败
                                                    </option>
                                                    <option value="6">
                                                        审核不成功
                                                    </option>
                                                    <option value="7">
                                                        退款成功
                                                    </option>
                                                </select> --%>
                                                <select class="ui-select" name="userOrderRefundPojo.reStatus" id="reStatus">
                                                    <option value="-1" selected="">
                                                        全部
                                                    </option>
                                                    <option value="0">
                                                        无售后/取消售后
                                                    </option>
                                                    <option value="1">
                                                        售后处理中
                                                    </option>
                                                    <option value="2">
                                                        退款中
                                                    </option>
                                                    <option value="3">
                                                        退款成功
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    售后类型
                                                </label>
                                                <select class="ui-select" name="userOrderRefundPojo.type" id="type">
                                                    <option value="0" selected="">
                                                        全部
                                                    </option>
                                                    <option value="1">
                                                        仅退款
                                                    </option>
                                                    <option value="2">
                                                        退货退款
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                    申请时间
                                                </label>
                                                <input class="ui-input" type="text" name="userOrderRefundPojo.beginDate" value="" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'e\')}'})" id="s">-
                                            	<input class="ui-input" type="text" name="userOrderRefundPojo.endDate" value="" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm;ss',minDate:'#F{$dp.$D(\'s\')}'})" id="e">
                                            </div>
                                            <div class="ui-form-item search-btn">
<!--                                            		<input type="hidden" name="page.pageNo" value=${page.pageNo}  id="pageNo" /> -->
												<input type="hidden" name="page.pageNo" value=0  id="pageNo" />
                                                <a href="#" class="ui-button ui-button-mred" id="query_btn">
                                                <i class="iconfont">&#xf012c;</i>
                                                    &nbsp;&nbsp;查询&nbsp;&nbsp;
                                                </a>
                                            </div>
                                            <div class="ui-form-item search-btn">
                                                <a href="#" class="ui-button ui-button-mred" id="excelAll">
                                                <i class="iconfont">&#xf012c;</i>
                                                    批量导出
                                                </a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <table class="ui-table order-list">
                                <thead>
                                    <tr>
                                    <style>
                                    	.ui-table th {
										    border-bottom: 1px solid #ececec;
										    padding: 10px 0px;
										    color: #999;
										}
									</style>
                                        <th class="ol-order-items">
                                            商品
                                        </th>
                                        <th class="ol-order-price">
                                            单价
                                        </th>
                                        <th class="ol-order-number">
                                            售后数量
                                        </th>
                                        <th class="ol-order-number">
                                            售后类型
                                        </th>
                                        <th class="ol-order-number">
                                            总金额
                                        </th>
                                        <th class="ol-order-price">
                                            退货金额
                                        </th>
                                        <th class="ol-order-status">
                                            售后状态
                                        </th>
                                        <th class="ol-order-operate">
                                            操作
                                        </th>
                                    </tr>
                                </thead>
                            </table>
                            <div id="body"></div>
                             <!-- 页码开始 -->
	                           <div class="pagination" id="Pagination">
	                            </div>
                                <!-- 页码结束 -->
                        </div>
                    </div>
                </div>
            </div>
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
	</body>
</html>
<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}";
var pageSize = 10;
var serviceInvolved;
var status;

//分页展现页面函数 
$(function() {
	//首次要初始化分页
	var rand=Math.random() * ( 100000 + 1);
	MAOWU.page.init(<s:property value="page.rowCount"/>,"getUserOrderRefundList.do?randIni="+rand,pageSize);
	$("#query_btn").click(query);
});

function query() {
	var rand=Math.random() * ( 100000 + 1);		
	queryData("getUserOrderRefundListCount.do", "getUserOrderRefundList.do?randquery="+rand,pageSize);
}



function orderRefundDetails(id){
//	serviceInvolved = $("#serviceInvolved").val();
	//status = $("#status").val();
	status = 0;
	$.ajax({
		type: "post",
//		url: "findUserOrderRefundListByorderId.do?orderId="+id+"&serviceInvolved="+serviceInvolved+"&uorStatus="+status,
		url: "findUserOrderRefundListByorderId.do?orderId="+id+"&uorStatus="+status,		
		dataType: 'json',
		async: false,
		success: function (msg) {
			var o_msg = eval(msg);
			for (var i = 0; i < o_msg.length; i++) {
				var sh="";
				var zc="";
				var wl="";
				//wl += "<p><a href='goRefundExpressSearch.do?type="+o_msg[i].logType+ "&postid="+o_msg[i].logistics+"' target='_blank'>"+"查看物流"+"</a></p>";
				var reStatusName="-";
				var reTypeName="-";
				if(o_msg[i].type==1){
					reTypeName="仅退款";
					//wl="<p style='display:none;'><a href='goRefundExpressSearch.do?type="+o_msg[i].logType+ "&postid="+o_msg[i].logistics+"' target='_blank'>"+"查看物流"+"</a></p>";
				}else if(o_msg[i].type==2){
					reTypeName="退货退款";
					//wl="<p style='display:none;'><a href='goRefundExpressSearch.do?type="+o_msg[i].logType+ "&postid="+o_msg[i].logistics+"' target='_blank'>"+"查看物流"+"</a></p>";
				}else if(o_msg[i].type==3){
					reTypeName="售后服务";
				}
				/* if(o_msg[i].status==1){
					reStatusName="审核中";
					//zc="<p style='margin-bottom: 10px'><a id='cfgoods' href='goRefundDetailWeb.do?orderDetailPojo.id="+o_msg[i].detailId+ "#reject'  class='ui-button ui-button-sblue' >申请仲裁</a></p>";
				}else if(o_msg[i].status==2){
					reStatusName="请退货";
					//zc="<p style='margin-bottom: 10px'><a id='cfgoods' href='goRefundDetailWeb.do?orderDetailPojo.id="+o_msg[i].detailId+ "#reject' class='ui-button ui-button-sblue' >申请仲裁</a></p>";
				}else if(o_msg[i].status==3){
					reStatusName="退货中";
					//wl="<p style='display:none;'><a href='goRefundExpressSearch.do?type="+o_msg[i].logType+ "&postid="+o_msg[i].logistics+"' target='_blank'>"+"查看物流"+"</a></p>";
					//sh="<p style='margin-bottom: 10px'><a id='cfgoods' onclick='confirmGoods("+o_msg[i].id+ ","+o_msg[i].id+ ","+o_msg[i].detailId+")' href='#' ba-ins='click~refundConfirm:2617680' class='ui-button ui-button-sblue' >确认收货</a></p>";
					//zc="<p style='margin-bottom: 10px'><a id='cfgoods' href='goRefundDetailWeb.do?orderDetailPojo.id="+o_msg[i].detailId+ "#reject'  class='ui-button ui-button-sblue' >申请仲裁</a></p>";
				}else if(o_msg[i].status==4){
					reStatusName="退货成功";
				}else if(o_msg[i].status==5){
					reStatusName="退货失败";
				}else if(o_msg[i].status==6){
					reStatusName="审核不成功";
				}else if(o_msg[i].status==7){
					reStatusName="退款成功";
				} */
				if(o_msg[i].reStatus == 0){
					reStatusName="无售后/取消售后";
				}else if(o_msg[i].reStatus == 1){
					if(o_msg[i].type == 1){
						reStatusName="仅退款，待商家处理";
					}else if(o_msg[i].type == 2){
						reStatusName="退货退款，待商家处理";
					}else{
						reStatusName="售后处理中";
					}
				}else if(o_msg[i].reStatus == 2){
					reStatusName="退款中";
				}else if(o_msg[i].reStatus == 3){
					reStatusName="退款成功";
				}
				var sta=2;
				if(o_msg[i].type == 1){
				   sta=7;
				    }
				 var kkk="<p class='ol-b-status c-999'>已申请客服介入</p>";
				var sss="<p><a target='_blank' href='checkOrderRefundWeb.do?userOrderRefundPojo.id="+o_msg[i].id+ "&userOrderRefundPojo.orderId="+o_msg[i].orderId+"&userOrderRefundPojo.detailId="+o_msg[i].detailId+"&is="+sta+"'>"+"审核通过"+"</a></p>";
				var uuu="<p><a target='_blank' href='checkOrderRefundWeb.do?userOrderRefundPojo.id="+o_msg[i].id+ "&userOrderRefundPojo.orderId="+o_msg[i].orderId+"&userOrderRefundPojo.detailId="+o_msg[i].detailId+"&is=6'>"+"审核不通过"+"</a></p>";                
                if(o_msg[i].serviceInvolved==1){
                      //kkk="<p><a target='_blank' href='updateServiceInvolvedById.do?userOrderRefundPojo.id="+o_msg[i].id+ "'>"+"申请客服介入"+"</a></p>";
                }
              
				tbody +="<tr class='order-item'>"+
                "<td class='ol-b-item-info clearfix'>"+
                "<a class='image' href='javascript:;'>"+
                    "<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/product/"+o_msg[i].productImage+"'>"+
                "</a>"+
                "<span class='title c-666'>商品货号："+
                    o_msg[i].productNum+
                "</span>"+
                "<span class='sku'>订单号："+
                    "<a target='_blank' class='c-999' href='javascript:;'>"+
                        o_msg[i].orderNo+
                    "</a>"+
                "</span>"+
            "</td>"+
            "<td class='ol-b-item-price'>"+
                "<p>￥"+o_msg[i].stockPrice+"</p>"+
            "</td>"+
            "<td class='ol-b-number c-999'>x"+o_msg[i].refundNum+"</td>"+
            "<td class='ol-b-number c-999'>"+reTypeName+"</td>"+
            "<td class='ol-b-number c-999'>￥"+o_msg[i].sumPrice+
            "<p class='ol-b-number c-999'>（优惠金额：<br/>￥-"+o_msg[i].couponPrice+ "）</p>"+
            "</td>"+
            "<td class='ol-b-item-price pink'>￥"+o_msg[i].refundSumPrice+"</td>"+
            "<td class='ol-b-status c-999'>"+
                  "<p>"+reStatusName+"</p>"+               
            "</td>"+
            "<td class='ol-b-operate'>"+
            "<p><a href='goRefundDetailWeb.do?orderDetailPojo.id="+o_msg[i].detailId+ "' target='_blank'>"+"处理/查看"+"</a></p>"+
            wl+
            sh+
            zc+
            "</td>"+
        "</tr>";
			}
			
		}
	});
} 

function installPage() {
	tbody = "";
	orderRefundDetails(this.orderId);//根据orderId查询订单商品
	var beg = this.creatDateString;
	var end = "2016-03-03 09:00:00"; 
	var begin = new Date(Date.parse(beg));
	var endDate = new Date(Date.parse(end));
	//console.log("格式化后:"+begin);
	//console.log("格式化后:"+endDate);
	if (begin.getTime() < endDate.getTime()) {  
		$("#buyname").remove();
    }
	
	$("#body").append(
			"<div class='trade-entry mt-10'>"+
				"<div class='order-entry' >"+
					"<div class='order-head'>"+
					//"<span>订单号:"+this.orderNo+"</span>"+
				    "<span>申请时间:"+this.creatDateString+"</span>"+
				    "<span id='buyname'>买家姓名:"+this.loginName+"</span>"+
				    "<span>手机号码:"+this.consigneePhone+"</span>"+
				    "<span>退款编号:"+this.id+"</span>"+
				    "<span>退货运单号:"+this.logistics+"</span>"+
				    "</div>"+
				    "<table class='pure-table'>"+
		            "<tbody id='tbody'>" +tbody+ "</tbody>"+
		        	"</table>"+
	            "</div>"+
            "</div>"
            );
}
//确认收货判断
function confirmGoods(id,orderId,detailId){
	var t = confirm('确认收货后,系统将自动打款给买家,如您没有收到货,可以打开详情申请仲裁.');
	if(t==true){
		$('#cfgoods').attr('href',"checkOrderRefundWeb.do?userOrderRefundPojo.id="+id+ "&userOrderRefundPojo.orderId="+orderId+"&userOrderRefundPojo.detailId="+detailId+"&is=4"); 
	}
	
}

$("#excelAll").click(function() {
	var formParam = $("#sysform").serialize();
	alert(formParam);
	$(location).attr('href', 'getOrderRefundExcelSeller.do?'+formParam);
});
</script>
