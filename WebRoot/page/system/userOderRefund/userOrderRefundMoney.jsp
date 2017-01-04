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
/**
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

	function deleteAll() {
		document.getElementById("idform").submit();
	}
*/
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
		//alert(result);
		var rand = Math.random() * (100000 + 1);
		if (result == "1") {
			alert("删除成功");
			queryData("getUserOrderRefundRowCount.do", "userOrderRefundAllList.do?randdelete=" + rand);
		} else {
			alert("删除失败");
		}
    }


</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">退货管理</a> &gt; <a href="#">退款进钱包</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<div style="color:red">
    		<s:fielderror />
		</div>
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
				<div class="floatRight">
				<input type="hidden" name="refundStatus" value="<s:property value="refundStatus"/>"> 
				<input type="submit" id="submitId" name="submitId" value="查询" class="submit_btn" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<!-- 
			<a class="delAll_btn" onclick="deleteAll()">删除全部</a>
			 -->
			<form action="OrderDeleteId.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<!-- 
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()"></th>
						 -->
						 <th>类型</th>
						<th>用户昵称</th>
						<th>订单号</th>
						<th>商品图片</th>
						<th  width="200px">商品名称</th>
						<th>商品价格</th>
						<th>优惠金额</th>
						<th>退回数量</th>
						<!-- <th>建议退回金额</th> -->
						<th width="80px">退回金额</th>
						<th width="75px">申请时间</th>
						<th width="75px">退货时间</th>
						<th width="75px">退款时间</th>
						<th width="80px">状态</th>
<!-- 						<th>申请客服介入</th> -->
						<th>操作</th>
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
	var types=this.type;
	if(types==1){
		var typed="退款";
	}
	if(types==2){
		var typed="退货";
	}
	if(types==3){
		var typed="售后退货";
	}
		var h=this.stockPrice*this.refundNum-this.couponPrice;
		//alert(h.toFixed(1));
		var sum = h.toFixed(2);
        //document.getElementById("allprice").innerHTML=h.toFixed(1);
		var ddd=this.refundMoney.toFixed(1);
		if(ddd==0){
		 var ss = "未退款";
		 var money="<input  id='code"+this.id+"' type='text' value="+sum +"  style='color:#FF0000;text-align:center' size='12' />" ;
		 var operation="<a class='edit_btn' onclick=refundMoney('"+this.id+"')>退款</a>";
		}else{
		var ss = "已退款";
		var money="<input  id='code"+this.id+"' type='text' value="+ddd +"  style='text-align:center' size='12'readonly />" ;
		var operation="";
		}
        var trStr = "<tr>";
			if(this.serviceInvolved == 2){
					trStr = "<tr style=\"color:#FF0000;\">"
				}
		$("#body")
				.append(
								//"<tr><td>"<input  name='tids' type='checkbox' value="+this.id +"   /></td><td>
								trStr+"<td>"
								+ typed
								+ "</td><td>"
								+ this.loginName
								+ "</td><td>"
								+ this.orderNo
								+ "</td><td>"
								+ "<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.productImage + "' height='80px' />"+ "</td><td>"
								+ this.productName
								+ "</td><td>"
								+ this.stockPrice
								+ "</td><td>"
								+ this.couponPrice
								+ "</td><td>"
								+ this.refundNum
                             /* + "</td><td>"
								+ sum */
								+ "</td><td>"
								+ money
								+ "</td><td>"
								+ this.creatDateString
								+ "</td><td>"
								+ this.updateDateString
								+ "</td><td>"
								+ this.refundDateString
								+ "</td><td>"
								+ ss
								+ "</td>"
								//+"<td>"
								//+ this.serviceInvolvedName
								//+ "</td>"
								+"<td>"
                             /* + "<a class='edit_btn' href='goCheckUserOrderRefund.do?userOrderRefundPojo.id="
								+ this.id + "'>编辑</a>"
								+ "<a class='del_btn' onclick=del('"
								+ this.id
								+ "')>删除</a>" */								
								+ operation
								+"</td></tr>"
                         );
	}

	$(function() {
		// 首次要初始化分页
		//alert(this.createDateString);
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userOrderRefundAllList.do?randIni=" + rand);
		$("#query_btn").click(query);
	});
	
	function refundMoney(id) {
		if (confirm("你真的想退款吗？")) {
			var d=document.getElementById('code'+id).value;
			d = window.encodeURI(window.encodeURI(d));  
			var url = "refundMoney.do?userOrderRefundPojo.id="+id+"&userOrderRefundPojo.refundMoney="+d;
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
			alert(result);
			queryData("getUserOrderRefundRowCount.do","userOrderRefundAllList.do?randdelete=" + rand);
    }
</script>