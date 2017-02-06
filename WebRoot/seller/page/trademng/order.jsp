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
			<!-- <div class="seller-search-form" style="margin: 8px 0 -14px -8px;display:none;">
                <form action="#" method="post" accept-charset="utf-8" id="sysform">
                	<input type="hidden" name="page.pageNo" value=0 id="pageNo" />
                </form>
            </div> -->
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <style>
                            .seller-search-form .ui-input{width:96px}.seller-search-form .ui-form-item{padding-left:0px}.seller-title-head-more{top:16px}.ui-form-item select{height:25px;line-height:25px;border:1px solid #D8D8D8;width:100px}.ui-btn-theme{background:#f5f5f5;padding:0 10px;border:1px solid #e6e6e6;color:#666;font-size:12px}.ui-btn-theme i{font-size:14px;margin-right:-2px;vertical-align:top}a.ui-btn-theme:hover{color:#ff4965;border:1px solid #ff4965;background:#FFF}.ui-input-theme:hover,.ui-select-theme:hover{border:1px solid #888!important}.ui-input-theme{height:25px!important;border:1px solid #D8D8D8;line-height:25px!important;padding:0 5px!important;margin-top:2px}.order-entry .order-head .u-icon-fenxiao{display:inline-block;*display:inline;*zoom:1;height:14px;line-height:14px;color:#fff;background:#ffae2e;border:1px solid #fa9a12;border-radius:2px;padding:0 3px}.seller-remark{position:absolute;height:30px}
                        </style>
                        <div class="seller-title">
                       	     我的订单
                        </div>
                        <div class="p20">
                            <div class="ui-box seller-search-form">
                                <div class="ui-box-head">
                                    <h3 class="ui-box-head-title">
                                 	       订单查询
                                    </h3>
                                </div>
                                <div class="ui-box-container">
                                    <form action="getMyOrderWeb.do" method="post" accept-charset="utf-8" id="sysform">
                                        <div class="ui-box-content">
                                        	<div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                	 订单编号
                                                </label>
                                                <input class="ui-input ui-input-theme" type="text" name="orderPojo.orderNo" value="">
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                	 商品名称
                                                </label>
                                                <input class="ui-input ui-input-theme" type="text" name="orderPojo.productName" value="">
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                           	      	收货手机
                                                </label>
                                                <input class="ui-input ui-input-theme" type="text" name="orderPojo.consigneePhone"
                                                value="">
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                               		收货人
                                                </label>
                                                <input class="ui-input ui-input-theme" type="text" name="orderPojo.consignee" value="">
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="order_type" class="ui-label">
                                            	        订单状态
                                                </label>
                                                <select class="w-mart-select ui-select-theme" name="orderPojo.orderStatus">
	                                                <option value="">
	                                                    	全部
	                                                </option>
	                                                <option value="1">
	                                                    	待付款
	                                                </option>
	                                                <option value="2">
	                                                    	已付款
	                                                </option>
	                                                <option value="3">
	                                                    	已发货
	                                                </option>
	                                                <option value="4">
	                                                    	已确认
	                                                </option>
	                                                <option value="5">
	                                                    	已评论
	                                                </option>
                                            	</select>
                                            </div>
                                            <div class="ui-form-item">
                                                <label for="" class="ui-label">
                                                	 快递单号
                                                </label>
                                                <input class="ui-input ui-input-theme" type="text" name="orderPojo.logisticsNo" value="">
                                            </div>
                                            <%-- <div class="ui-form-item">
                                                <label for="order_type" class="ui-label">
                                            	        订单来源
                                                </label>
                                                <select name="orderPojo.channel" class="w-mart-select ui-select-theme">
                                                    <option value="">
                                          	                        全部
                                                    </option>
                                                    <option value="0">
                                                    PC端
                                                    </option>
                                                    <option value="1">
                                                        APP
                                                    </option>
                                                    <option value="2">
                                           	                         微信
                                                    </option>
                                                </select>
                                            </div> --%>
                                            <div class="ui-form-item c-999">
                                                <label for="" class="ui-label">
                                               	     成团时间
                                                </label>
                                                <input class="ui-input ui-input-theme" style="width:100px;" id="create-time-start-selector" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'create-time-end-selector\')}'})" type="text" name="orderPojo.groupBeginDateStr" value="">
                                                <label for="" class="ul-label">
                                                    -
                                                </label>
                                                <input class="ui-input ui-input-theme" style="width:100px;" id="create-time-end-selector" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'create-time-start-selector\')}'})" type="text" name="orderPojo.groupEndDateStr" value="">
                                            </div>
                                            <br/>
                                            <div class="ui-form-item search-btn">
                                            <input type="hidden" name="page.pageNo" value=0 id="pageNo" />
                                                <a href="#" class="ui-button ui-button-mwhite ui-btn-theme" id="query_btn">
                                                <i class="iconfont">&#xf012c;</i>
                                                    查询
                                                </a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <form action="importOrderSeller.do" method="post" id="formImport" enctype="multipart/form-data" >		
                               <div class="ui-form-item excel-btn" style="display: '' ;">
                                   <a  id="excel"  class="ui-button ui-button-mwhite ui-btn-theme" id="">导出已选订单</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                   <a  id="excelAll"  class="ui-button ui-button-mwhite ui-btn-theme" id="">导出所有查询订单</a>&nbsp;&nbsp;&nbsp;&nbsp;                                 			              
					               <!-- <input type="submit" value="订单导入" class="ui-button ui-button-mwhite ui-btn-theme"  /> 
					               <input type="file" name="importupfile" class="ui-button ui-button-mwhite ui-btn-theme"" />-->				                  
                             </div>
                             </form>
                            </div>
                            <form action="" id="idform" method="post">
                            <table class="ui-table order-list">
                                <thead>
                                    <tr>
                                        <th>
                                        <input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()"/>
                                        </th>
                                        <th class="ol-order-items">
                                            商品
                                        </th>
                                        <th class="ol-order-price">
                                            供货价
                                        </th>
                                        <th class="ol-order-number">
                                            数量
                                        </th>
                                        <th class="ol-order-price">
                                            维权售后
                                        </th>
                             <!--            <th class="ol-order-price">
                                            订单金额
                                        </th>
                                        <th class="ol-order-price">
                                            成交价
                                        </th> -->
                                        <th class="ol-order-status">
                                            备注
                                        </th>
                                         <th class="ol-order-status">
                                            操作
                                        </th>
                                    </tr>
                                </thead>
                            </table>
                            <span id="body"></span>
                            </form>
                            <div class="pagination" id="Pagination">
	                        </div>
                        </div>
                    </div>
                </div>
            </div>
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
		<script src="../../js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	</body>
</html>
<script type="text/javascript">
	var ctx  ="<s:property value="ctx" />";
	var pagecount = "${page.rowCount}";
	var pageSize = 10;
	
	function query() {
		/*var id = $("input[name='orderPojo.activityId']").val();
		var r = /^[0-9]*[1-9][0-9]*$/;
		if(id != "" && !r.test(id)){
			alert("专场ID必须为正整数！");
			return;
		}*/
		if(tt.validate()){
			var rand=Math.random() * ( 100000 + 1);
			queryData("getMyOrderCountWeb.do", "getMyOrderListWeb.do?randquery="+rand,pageSize);
		}
	}
	
	var tbody = "";
	var createDate;
	function orderDetails(id,os,sellerMessage){
		$.ajax({
			type: "post",
			url: "orderDetailAllList.do?contentGuide=1&page.pageNo=1&page.pageSize=100&orderDetail.orderId="+id,
			dataType: 'json',
			async: false,
			success: function (msg) {
				var o_msg = eval(msg);
				var lastDate = createDate.getTime() + 86400000 * 2;
				var nowDate = new Date().getTime();
				colorStr = "";
				for (var i = 0; i < o_msg.length; i++) {
					if(nowDate > lastDate && o_msg[i].reStatus == 0){
						colorStr = "red";
						//$("#numStr").attr("class","red");
					}
					var reStatusName="无售后";
					/* if(o_msg[i].reStatus==1 || o_msg[i].reStatus==2 || o_msg[i].reStatus==3){
						reStatusName="<a href='goRefundDetailWeb.do?orderDetailPojo.id="+o_msg[i].id+"'>退款中，等待处理</a>";
					}else if(o_msg[i].reStatus==4 || o_msg[i].reStatus==5 || o_msg[i].reStatus==6 || o_msg[i].reStatus==7){
						reStatusName="<a href='goRefundDetailWeb.do?orderDetailPojo.id="+o_msg[i].id+"'>售后服务完成</a>";
					} */
					if(o_msg[i].reStatus==1 || o_msg[i].reStatus==2 || o_msg[i].reStatus==3){
						reStatusName="退款中，等待处理";
					}else if(o_msg[i].reStatus==4 || o_msg[i].reStatus==5 || o_msg[i].reStatus==6 || o_msg[i].reStatus==7){
						reStatusName="售后服务完成";
					}
	            	tbody += "<tr class='order-item'>"+
	                "<td class='ol-b-item-info clearfix' style='width: 360px'>"+
	                /*"<a class='image' href='http://b2c.taozhuma.com/goProductDetail.do?productPojo.id="+o_msg[i].productId+"' target='_blank'>"+
	                    "<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/product/"+ o_msg[i].productImage + "'>"+
	                "</a>"+
	                 "<a class='title c-666' target='_blank' href='http://b2c.taozhuma.com/goProductDetail.do?productPojo.id="+o_msg[i].productId+"'>"+o_msg[i].productName+
	                "</a>"+ */
	                "<a class='image' href='#'>"+
	                    "<img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/product/"+ o_msg[i].productImage + "'>"+
	                "</a>"+
	                "<span class='sku'>"+o_msg[i].productName+"&nbsp;&nbsp;&nbsp;</span>"+
		            "</td>"+
		            "<td class='ol-b-item-price'>"+
		                "<p>"+
		                    "￥"+(o_msg[i].stockPrice).toFixed(2)+"</p>"+
		                "<p class='strike'>"+
		                    "￥"+(o_msg[i].stockPriceOld).toFixed(2)+"</p>"+
		            "</td>"+
		            "<td class='ol-b-number' style='text-align: center;'><font id='numStr' class='"+colorStr+"'>"+(o_msg[i].num).toFixed(0)+"</font></td>"+
		            "<td class='ol-b-ops c-999'>"+
		               reStatusName+
		            "</td>"+
		            "<td class='ol-b-subtotal' rowspan='1' style='font-size:12px'>"+
				        "<span>"+
		                    "<a class='edit_btn' onclick=update('"+id+"','"+sellerMessage+"')>备注</a>"+
		                "</span><br>"+
		                "<span class='sellerMessage'>"+sellerMessage+"</span>"+
		            "</td>"+
		            "<td class='ol-b-status c-999' rowspan='1'>"+
		                "<span class='green'>"+os+"</span>"+
		                "<br>"+
		                "<a href='getMyOrderDetailWeb.do?orderPojo.id="+o_msg[i].orderId+"' target='_blank'>"+
		                    "查看详情"+
		                "</a>"+
		            "</td>"+
		            "</tr>";
				}
			}
		});
	}
	
	function installPage() {
		createDate = new Date(this.createDateStr);
		var vString="";
		tbody = "";
		orderDetails(this.id,this.orderStatusName,this.sellerMessage);
		if(this.orderStatus==2){
			vString = "<span style='float:right;'><a class='edit_btn' href='goOrderShipAddSeller.do?orderPojo.id="+this.id+"'>发货</a></span>";
		}
		$("#body").append(
			"<div class='trade-entry mt-10'>"+
                "<div class='order-entry'>"+
                    "<div class='order-head'>"+
                        "<input  name='tids' type='checkbox' value="+this.id +">"+
                        "<span>"+
                           " 订单号："+
                            "<a href='getMyOrderDetailWeb.do?orderPojo.id="+this.id+"' target='_blank'>"+this.orderNo+"</a>"+
                        "</span>"+
                        "<span>"+
                           " 下单时间："+this.createDateStr+
                        "</span>"+
                        "<span>"+
                           " 收件人："+this.consignee+" / "+this.consigneePhone+
                        "</span>"+
                        "<span>"+
                           vString+
                        "</span>"+
                    "</div>"+
                    "<table class='pure-table'>"+
                        "<tbody id='tbody'>"+
                            tbody+
                        "</tbody>"+
                    "</table>"+
                "</div>"+
            "</div>"
		);
	}
	
	//分页展现页面函数 
	
	$(function() {
		//首次要初始化分页
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,"getMyOrderListWeb.do?randIni="+rand,pageSize);
		$("#query_btn").click(query);
		
		//导出已选订单
		$("#excel").click(function() {
			if(tt.validate()){
				var v = $("select[name='orderStatus']").val();
				var formParam = $("#sysform").serialize();
				var formParam1 = $("#idform").serialize();
				var allformParam = formParam+"&"+formParam1;
				$(location).attr('href', 'getOrderExcelSeller.do?orderPojo.os='+v+'&'+allformParam);
			}
		});
		
		//导出所有查询订单
		$("#excelAll").click(function() {
			if(tt.validate()){
				var v = $("select[name='orderStatus']").val();
				var formParam = $("#sysform").serialize();
				var formParam1 = $("#idform").serialize();
				var allformParam = formParam+"&"+formParam1;
				$(location).attr('href', 'getOrderExcelSeller.do?isAll=1&orderPojo.os='+v+'&'+allformParam);
			}
		});	
	});
	
	//查询订单状态
	function searchByorderStatus(){
		var v = $("select[name='orderStatus']").val();
		$("input[name='orderPojo.orderStatus']").val(v);
		//$("#sysform").submit();
		query();
	}
	
	//全选框
	function allcb(){
		var checkbox=document.getElementById("selectcb"); 
		if(checkbox.checked==true){
			var code_Values = document.getElementsByName("tids") ; 
			for(var i = 0;i < code_Values.length;i++){ 
				if(code_Values[i].type == "checkbox") 
					{ 
						code_Values[i].checked = true; 
					} 
			} 
		 
		}else{	
			var code_Values = document.getElementsByName("tids") ; 
			for(i = 0;i < code_Values.length;i++){ 
				if(code_Values[i].type == "checkbox") 
					{ 
						code_Values[i].checked = false
					} 
			} 				
		}
	}
	//备注
		function update(id,sellerMessage)
	{
		var remarks = prompt("请输入此订单备注", sellerMessage); 
		if(remarks){
			 $.ajax({
	             type: "get",
	             url: "codeWeb.do?id="+id+"&sellerMessage="+remarks,
	             success: function(data){
	             	alert("成功");
	            	var page = $("#pageNo").val();
	            	MAOWU.ajax.get("getMyOrderListWeb.do?page.pageNo="+page, "", listEach);
	             },
				error: function(){
					alert("失败");
				}
	             });	
		}

            
	}
	
		function listEach(result){
    		var c = eval("(" + result + ")");
    		$("#body").html("");
    		$.each(c, installPage);
    	}
</script>