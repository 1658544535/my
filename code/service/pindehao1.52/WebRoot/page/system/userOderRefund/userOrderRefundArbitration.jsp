<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

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
			var url = "delUserOrderRefund.do?userOrderRefundPojo.id=" + val;
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
			<a href="#">退货管理</a> &gt; <a href="#">退货退款仲裁</a>
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
						<td align="right">用户昵称</td>
						<td><label><input type="text" name="userOrderRefundPojo.loginName"
								id="userOrderRefundPojo.loginName" value="<s:property
								value="userOrderRefundPojo.loginName" />"></label>
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
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<input type="hidden" name="refundStatus" value="<s:property value="refundStatus"/>">
				<div class="floatRight">
					<input type="submit" id="submitId" name="submitId" value="查询" class="submit_btn" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>
		<!-- 查询结束 -->
	
   <div>
   <a class="submit_btn" style="float: right;" href='userOrderRefundManage.do?refundStatus=0'/>已仲裁商品</a> </div>
   <form action="OrderDeleteId.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()"></th>
						<th>类型</th>
						<th>用户昵称</th>
						<th>订单号</th>
						<th>商品图片</th>
						<th>商品名称</th>
						<th>物流信息</th>
						<th>商品价格</th>
						<th>优惠金额</th>
						<th>退回数量</th>
						<th>退货总金额</th>
						<th>买家凭证</th>
						<th>买家理由</th>
						<th>申请时间</th>
						<th width="80px">状态</th>
						<th>商家凭证</th>
						<th>商家理由</th>
						<s:if test="#session.role.roleId!=7"><th width="100px">操作</th></s:if>
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
		if (tt.validate()) {
			var rand = Math.random() * (100000 + 1);
			queryData("getUserOrderRefundCount.do", "userOrderRefundAllList.do?randquery=" + rand);
		}
	}

	//分页展现页面函数 
	function installPage() {
	var ddd=this.status;
	var aaa="";
	var bbb="";
	var ccc="";
	if(ddd == 3){
	   var ss = "退货中";
	 //  aaa="<a class='edit_btn' href='checkOrderRefund.do?userOrderRefundPojo.id="
		//	+ this.id + "&userOrderRefundPojo.orderId="+this.orderId+"&userOrderRefundPojo.detailId="+this.detailId+"&is=4'>确认退货</a>";
	}else if(ddd == 2){
	   var ss = "买家填写退货信息中";
	}
	else if(ddd == 1){
	   var ss = "审核中";
	}
	var types=this.type;
	if(types==1){
		var typed="退款";
		bbb="<a class='edit_btn' href='updateStatusOfUserOrderRefundById.do?userOrderRefundPojo.id="+this.id+"&userOrderRefundPojo.type=1&userOrderRefundPojo.detailId="+this.detailId+"&is=2'>退款通过</a>";
		ccc="<a class='edit_btn' href='updateStatusOfUserOrderRefundById.do?userOrderRefundPojo.id="+this.id+"&userOrderRefundPojo.type=1&userOrderRefundPojo.detailId="+this.detailId+"&is=1'>退款不通过</a>";
	}
	if(types==2){
		var typed="退货";
		bbb="<a class='edit_btn' href='updateStatusOfUserOrderRefundById.do?userOrderRefundPojo.id="+this.id+"&userOrderRefundPojo.type=2&userOrderRefundPojo.detailId="+this.detailId+"&is=3'>退货通过</a>";
		ccc="<a class='edit_btn' href='updateStatusOfUserOrderRefundById.do?userOrderRefundPojo.id="+this.id+"&userOrderRefundPojo.type=2&userOrderRefundPojo.detailId="+this.detailId+"&is=4'>退货不通过</a>";
		
	}
	if(types==3){
		var typed="售后退货";
		bbb="<a class='edit_btn' href='updateStatusOfUserOrderRefundById.do?userOrderRefundPojo.id="+this.id+"&userOrderRefundPojo.type=3&userOrderRefundPojo.detailId="+this.detailId+"&is=3'>退货通过</a>";
		ccc="<a class='edit_btn' href='updateStatusOfUserOrderRefundById.do?userOrderRefundPojo.id="+this.id+"&userOrderRefundPojo.type=3&userOrderRefundPojo.detailId="+this.detailId+"&is=4'>退货不通过</a>";
	}
	var h=this.stockPrice*this.refundNum-this.couponPrice;
		var sum = h.toFixed(2);
		$("#body")
				.append(
						"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td><td>"
								+ typed
								+ "</td><td>"
								+ this.loginName
								+ "</td><td>"
								+ this.orderNo
								+ "</td><td>"
								+ "<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.productImage + "' height='80px' />"
								+ "</td><td>"
								+ this.productName
								+ "</td><td>"
								+ this.serialNumber
								+ "</td><td>"
								+ this.stockPrice
								+ "</td><td>"
								+ this.couponPrice
								+ "</td><td>"
								+ this.refundNum
								+ "</td><td>"
								+ sum
								+ "</td><td>"
								+ "<img src='/upfiles/orderRefund/"+ this.image + "' height='80px' />"
								+ "</td><td>"
								+ this.refundReason
								+ "</td><td>"
								+ this.creatDateString
								+ "</td><td>"
								+ ss
								+ "</td><td>"
								+ "<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/orderRefund/"+ this.rejectImages + "' height='80px' />"   
								+ "</td><td>"
								+ this.rejectReason
								+ "</td><s:if test="#session.role.roleId!=7"><td>"
								+ bbb
								+ ccc
								+"</td></s:if></tr>"
                         );
	}

	$(function() {
		// 首次要初始化分页
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userOrderRefundAllList.do?page.rowCount=<s:property value='page.rowCount'/>&randIni=" + rand);
		$("#query_btn").click(query);
	});

</script>