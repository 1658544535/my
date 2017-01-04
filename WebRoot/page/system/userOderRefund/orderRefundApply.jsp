<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">
	function allcb() {
		var checkbox = document.getElementById("selectcb");
		if (checkbox.checked == true) {
			var code_Values = document.getElementsByName("tids");
			for (i = 0; i < code_Values.length; i++) {
				if (code_Values[i].type == "checkbox") {
					code_Values[i].checked = true;
				}
			}
		} else {
			var code_Values = document.getElementsByName("tids");
			for (i = 0; i < code_Values.length; i++) {
				if (code_Values[i].type == "checkbox") {
					code_Values[i].checked = false
				}
			}
		}
	}

	function manySend(href){
		var form = document.getElementById("idform");
		form.action = href;//传想要跳转的路径
		form.submit();
	} 
	
	//批量审核
	function updateAll(){
	if(confirm("确认审核所选记录吗？"))
		{
			manySend("checkAllUserOrderRefundById.do?userOrderRefundPojo.status=1&refundStatus=1");//状态(取业务字典：0未审核1已审核2用户发货3退货不成功4退货成功)       
            return true;
		}else{
			return ;
		}
	}
	
	//批量修改:发货
	function deliver(){
		if(confirm("要将所选的所有订单修改为'发货'么？"))
			{
				manySend("checkAllUserOrderRefundById.do?userOrderRefundPojo.status=2&refundStatus=1");//状态(取业务字典：0未审核1已审核2用户发货3退货不成功4退货成功)       
	            return true;
			}else{
				return ;
			}
	}
	
	//批量修改:退货不成功
	function returnFailure(){
		if(confirm("要将所选的所有订单修改为'退货不成功'么？"))
			{
				manySend("checkAllUserOrderRefundById.do?userOrderRefundPojo.status=3&refundStatus=1");//状态(取业务字典：0未审核1已审核2用户发货3退货不成功4退货成功)       
	            return true;
			}else{
				return ;
			}
	}
	
	//批量修改:退货成功
	function returnSuccess(){
		if(confirm("要将所选的所有订单修改为'退货成功'么？"))
			{
				manySend("checkAllUserOrderRefundById.do?userOrderRefundPojo.status=4&refundStatus=1");//状态(取业务字典：0未审核1已审核2用户发货3退货不成功4退货成功)       
	            return true;
			}else{
				return ;
			}
	}

	function del(val) {
		if (confirm("你真的想删除该记录么？")) {
			var formParam = escape($("#sysform").serialize() + "&" + $("#idform").serialize());
			pageNoVal = $("#pageNo").val();
			
			var url = "delUserOrderRefund.do?userOrderRefundPojo.id=" + val+"&pageNoVal="+pageNoVal+"&formParam="+formParam;
			doOpreator(url, null);
		} else {
			return;
		}
	}
	
	function doOpreator(url, params) {
		MAOWU.ajax.get(url, params, goRefreshPage);
	}

	function goRefreshPage(result) {
		var rand = Math.random() * (100000 + 1);
		if (result == "1") {
			alert("删除成功");
			pageNumber = $("#pageNo").val();
			queryData("getUserOrderRefundRowCount.do", "userOrderRefundAllList.do?randdelete=" + rand);
		} else {
			alert("删除失败");
		}
    }
/*	
	function upd(val) {
		if (confirm("确认审核吗？")) {
			var url = "updateUserOrderRefund.do?userOrderRefundPojo.id=" + val;
			doOpreator(url, null);
		} else {
			return;
		}
	}
	
	function doOpreator(url, params) {
		MAOWU.ajax.get(url, params, goRefreshPage);
	}

	function goRefreshPage(result) {
		var rand = Math.random() * (100000 + 1);
		if (result == "1") {
			alert("审核成功");
			queryData("getUserOrderRefundRowCount.do", "userOrderRefundAllList.do?randdelete=" + rand);
		} else {
			alert("审核失败");
		}
    }
*/
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">退货管理</a> &gt; <a href="#">退货申请</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="userOrderRefundManage.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">订单号</td>
						<td><label><input type="text" name="userOrderRefundPojo.orderNo"
								id="userOrderRefundPojo.orderNo" value="<s:property
								value="userOrderRefundPojo.orderNo" />"></label>
						</td>
						<td align="right">用户账号</td>
						<td><label><input type="text" name="userOrderRefundPojo.loginname1"
								id="userOrderRefundPojo.loginname1" value="<s:property
								value="userOrderRefundPojo.loginname1" />"></label>
						</td>
					</tr>
					<tr>
						<td align="right">商品名称</td>
						<td><label><input type="text" name="userOrderRefundPojo.productName"
								id="userOrderRefundPojo.productName" value="<s:property
								value="userOrderRefundPojo.productName" />"></label></td>
						<td align="right">商品货号</td>
						<td><label><input type="text" name="userOrderRefundPojo.productNum"
								id="userOrderRefundPojo.productNum" value="<s:property
								value="userOrderRefundPojo.productNum" />"></label></td>
					</tr>
					<tr>
						<td align="right" >申请时间区间：</td>
						<td width="80px"  style="padding: 0px 0px;">从<input value="${userOrderRefundPojo.createDateStartStr }" style="min-width:60px;" name="userOrderRefundPojo.createDateStartStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></td>
					    <td>到<input value="${userOrderRefundPojo.createDateEndStr }" style="min-width:60px;" name="userOrderRefundPojo.createDateEndStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></td>
					    <td align="right"></td>
					</tr>
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
				<input type="hidden" name="refundStatus" value="<s:property value="refundStatus"/>">
				<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<!--<a class="Add_btn" onclick="updateAll()">批量审核</a>-->
			<!--<a class="Add_btn" onclick="deliver()">用户发货</a>-->
			<!--<a class="Add_btn" onclick="returnSuccess()">退货成功</a>-->
			<!--<a class="Add_btn" onclick="returnFailure()">退货不成功</a>-->
			<input title="导出所有结果" type="button" value="导出 EXCEL "  id="excel" class="submit_btn" style="float: right;"  />
			<input title="导出查询结果（可以筛选）" type="button" value="导出查询结果"  id="excelAll" class="submit_btn" style="float: right;margin-left: 20px;margin-right: 20px;"  />
			<form action="OrderDeleteId.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()"></th>
						<th>类型</th>
						<th>用户账号</th>
						<th>订单号</th>
						<th>商品图片</th>
						<th>商品名称</th>
					<!--<th>退货状态</th>-->
						<th>商品价格</th>
						<th>优惠金额</th>
						<th>退回数量</th>
						<th>退货总金额</th>
						<th>退回原因</th>
						<th width="80px">申请时间</th>
						<!--<th>申请客服介入</th>-->
						<th>问题商品图片</th>
						<th>退款状态</th>
						<th>客服留言</th>
						<th width='100px'>操作</th>
					</tr>
					<tbody id="body"></tbody>
				</table>
			</form>
			<div class="page">
				<div class="floatleft">
					总共 <i id="rowcount">${page.rowCount }</i> 条/<i id="pagecount"></i>页
				</div>
				<div style="float: right" id="Pagination" class="pagination"></div>
				<div class="Clear"></div>
			</div>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
	var ctx = "<s:property value="ctx" />";
	var pagecount = "${page.rowCount}";
	function query() {
			pageNumber = 1;
			var rand = Math.random() * (100000 + 1);
			queryData("getUserOrderRefundRowCount.do", "userOrderRefundAllList.do?randquery=" + rand);
	}

	//分页展现页面函数 
	function installPage() {
		var formParam = escape($("#sysform").serialize() + "&" + $("#idform").serialize());
		pageNoVal = $("#pageNo").val();
		
		var isRefundName = "未退款";
		if(this.isRefund == 1){
			isRefundName = "处理中";
		}else if(this.isRefund == 2){
			isRefundName = "退款成功";
		}else if(this.isRefund == 3){
			isRefundName = "退款失败";
		}
	var ddd=this.status;
	if(ddd == 1){
	var ss = "待审核";
	}
	var types=this.type;
	var h=this.stockPrice*this.refundNum-this.couponPrice;
	var sum = h.toFixed(2);
	var imgHtml="<td>无图 </td>";
	if(this.images!=""){
		imgHtml="<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/orderRefund/"
			+ this.images
			+ "' height='80px' onclick='javascript:window.open(this.src);'/></td>"; 
	}
	var typed="";
	var str="<td>";
	<s:if test="#session.role.roleId == 12 || #session.role.roleId==13 || #session.role.roleId == 1">
	if(types==1){
		typed="退款";
		if(this.isRefund != 1 || this.isRefund != 2){
								str+= "<a class='edit_btn' href='checkOrderRefund.do?userOrderRefundPojo.id="
								+ this.id + "&userOrderRefundPojo.orderId="+this.orderId+"&userOrderRefundPojo.detailId="+this.detailId+"&is=7&orderId="+this.orderId+"&payMethod="+this.payMethod+"&pageNoVal="+pageNoVal+"&formParam="+formParam+"'>审核通过</a>"
								str+= "<a class='edit_btn' href='checkOrderRefund.do?userOrderRefundPojo.id="
								+ this.id + "&userOrderRefundPojo.orderId="+this.orderId+"&userOrderRefundPojo.detailId="+this.detailId+"&is=6&pageNoVal="+pageNoVal+"&formParam="+formParam+"'>审核不通过</a>"
		}
								str+= "<a class='del_btn' onclick=del('"
								+ this.id
								+ "')>删除</a>"
								+ "<a class='edit_btn' href='goFindRefundOrder.do?userOrderRefundPojo.id="
								+ this.id + "&userOrderRefundPojo.orderId="+this.orderId+"&userOrderRefundPojo.detailId="+this.detailId+"&refundStatus=1&pageNoVal="+pageNoVal+"&formParam="+formParam+"'>详情</a>"
								+"</td>";
	}
	if(types==2){
		typed="退货";
		str+="<a class='edit_btn' href='checkOrderRefund.do?userOrderRefundPojo.id="
								+ this.id + "&userOrderRefundPojo.orderId="+this.orderId+"&userOrderRefundPojo.detailId="+this.detailId+"&is=2&pageNoVal="+pageNoVal+"&formParam="+formParam+"'>审核通过</a>"
								+ "<a class='edit_btn' href='checkOrderRefund.do?userOrderRefundPojo.id="
								+ this.id + "&userOrderRefundPojo.orderId="+this.orderId+"&userOrderRefundPojo.detailId="+this.detailId+"&is=6&pageNoVal="+pageNoVal+"&formParam="+formParam+"'>审核不通过</a>"
								+ "<a class='del_btn' onclick=del('"
								+ this.id
								+ "')>删除</a>"
								+ "<a class='edit_btn' href='goFindRefundOrder.do?userOrderRefundPojo.id="
								+ this.id + "&userOrderRefundPojo.orderId="+this.orderId+"&userOrderRefundPojo.detailId="+this.detailId+"&refundStatus=1&pageNoVal="+pageNoVal+"&formParam="+formParam+"'>详情</a>"
								+"</td>";
	}
	if(types==3){
		typed="售后退货";
		str+="<a class='edit_btn' href='checkOrderRefund.do?userOrderRefundPojo.id="
								+ this.id + "&userOrderRefundPojo.orderId="+this.orderId+"&userOrderRefundPojo.detailId="+this.detailId+"&is=2&pageNoVal="+pageNoVal+"&formParam="+formParam+"'>审核通过</a>"
								+ "<a class='edit_btn' href='checkOrderRefund.do?userOrderRefundPojo.id="
								+ this.id + "&userOrderRefundPojo.orderId="+this.orderId+"&userOrderRefundPojo.detailId="+this.detailId+"&is=6&pageNoVal="+pageNoVal+"&formParam="+formParam+"'>审核不通过</a>"
								+ "<a class='del_btn' onclick=del('"
								+ this.id
								+ "')>删除</a>"
								+ "<a class='edit_btn' href='goFindRefundOrder.do?userOrderRefundPojo.id="
								+ this.id + "&userOrderRefundPojo.orderId="+this.orderId+"&userOrderRefundPojo.detailId="+this.detailId+"&refundStatus=1&pageNoVal="+pageNoVal+"&formParam="+formParam+"'>详情</a>"
								+"</td>";
	}
	</s:if>
	<s:if test="#session.role.roleId != 12 && #session.role.roleId!=13 && #session.role.roleId != 1">
	if(types==1){
		typed="退款";
	}
	if(types==2){
		typed="退货";
	}
	if(types==3){
		typed="售后退货";
	}
	str+="<a class='edit_btn' href='goFindRefundOrder.do?userOrderRefundPojo.id="
	+ this.id + "&userOrderRefundPojo.orderId="+this.orderId+"&userOrderRefundPojo.detailId="+this.detailId+"&refundStatus=1&pageNoVal="+pageNoVal+"&formParam="+formParam+"'>详情</a>"
	+"</td>";
	</s:if>
	
		var trStr = "<tr>";
			if(this.serviceInvolved == 2){
					trStr = "<tr style=\"color:#FF0000;\">"
				}	
		$("#body").append(""+trStr+"<td><input  name='tids' type='checkbox' value="+this.id +"   /></td><td>"
								+ typed
								+ "</td><td>"
								+ this.loginname1
								+ "</td><td>"
								+ this.orderNo
								+ "</td>"
								+"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.productImage + "' height='80px' /></td>"
								+"<td>"
								+ this.productName
							<!--	+ "</td><td width='20px'>"+ ss -->
								+ "</td><td width='20px'>"
								+ this.stockPrice
								+ "</td><td width='20px'>"
								+ this.couponPrice
								+ "</td><td width='20px'>"
								+ this.refundNum
								+ "</td><td>"
								+ sum
								+ "</td><td width='300px'>"
								+ this.refundReason
								+ "</td><td width='100px'>"
								+ this.creatDateString
								+ "</td>"
								//+"<td>"+ this.serviceInvolvedName+ "</td>"
								+imgHtml
								+"<td>"
								+isRefundName
								+"</td>"
								+"<td>"
								+this.remarks
								+"</td>"
								+str
								+"</tr>"
                         );
	}

	$(function() {
		pageNumber = '${pageNoVal == null ? 1 : pageNoVal}';
		 console.log(pageNumber);
		
		// 首次要初始化分页
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userOrderRefundAllList.do?randIni=" + rand);
		$("#query_btn").click(query);
		
				
	$("#excel").click(function() {			
			if(tt.validate()){
				var formParam = $("#sysform").serialize();
				var formParam1 = $("#idform").serialize();
				var allformParam = formParam+"&"+formParam1;
				$(location).attr('href', 'getOrderRefundExcel.do?'+allformParam);
			}
		});
	$("#excelAll").click(function() {
		if(tt.validate()){
			var formParam = $("#sysform").serialize();
			var formParam1 = $("#idform").serialize();
			var allformParam = formParam+"&"+formParam1;
			$(location).attr('href', 'getOrderRefundExcel.do?isAll=1&'+allformParam);
		}
   });
});

</script>