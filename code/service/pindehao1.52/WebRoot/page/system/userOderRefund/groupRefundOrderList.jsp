<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
<script>

function uncheck1(url){
	if(confirm("确定要退款吗？")){
		var formParam = $("#idform").serialize();
		MAOWU.ajax.get(url, formParam, uncheckRefreshPage1);
	}else{
		return ;
	}
}


function uncheckRefreshPage1(result){
	var data = eval("("+result+")");
	if(data.result=="0"){
		alert("申请退款成功");
		//给sys_util.js的pageNumber赋值记录页数,刷新数据就会在修改的那页
		pageNumber = data.pageNo;
		queryData("userOrderRefundRowCnt.do", "userOrderRefundList.do");
	} else if(data.result=="3"){
	    alert("请选择要退款的订单!");
	} else if(data.result=="4"){
		alert("请先登录!");
	} else {
		alert(data.result);
	}
}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="#">用户订单管理</a> &gt; <a href="#">用户订单管理</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">支付方式:</td>
						<td>
						<select name="payType" id="payType">
							<option value="">全部</option>
							<option value="1">支付宝</option>
							<option value="2">微信</option>
							<option value="4">钱包</option>
						</select>
						</td>
						<td align="right">退款状态:</td>
						<td>
						<select name="isRefund" id="isRefund">
							<option value="">全部</option>
							<option value="0">未退款</option>
							<option value="1">处理中</option>
							<option value="2">退款成功</option>
							<option value="3">退款失败</option>
						</select>
						</td>
						<td align="right">订单号:</td>
						<td><input type="text" name="orderNoStr" /></td>	
						<td align="right">用户账号：</td>
						<td><label><input type="text"
								name="order.loginname" id="ticketRulePojo.ticketName"
								value=""></label>
						</td>	
						<td align="right">订单来源：</td>
						<td>
							<select name="order.source" id="" class="floatLeft">
									<option value="">----请选择----</option>
									<option value="1" <c:if test="${order.source == 1 }">selected=selected</c:if>>普通拼团</option>
									<option value="2" <c:if test="${order.source == 2 }">selected=selected</c:if>>团免</option>
									<option value="3" <c:if test="${order.source == 3 }">selected=selected</c:if>>猜价</option>
									<option value="4" <c:if test="${order.source == 4 }">selected=selected</c:if>>单独购</option>
									<option value="5" <c:if test="${order.source == 5 }">selected=selected</c:if>>0.1夺宝</option>
									<option value="6" <c:if test="${order.source == 6 }">selected=selected</c:if>>掌上秒杀</option>
									<option value="7" <c:if test="${order.source == 7 }">selected=selected</c:if>>免费抽奖</option>
									<option value="8" <c:if test="${order.source == 8 }">selected=selected</c:if>>拼得客</option>
							</select>
					    </td>   
					</tr>
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			
			<form action="#" id="idform" method="post" >
			<a class="delAll_btn" onclick="uncheck1('goaliWxRefund.do')" >批量退款</a>
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>用户信息</th>
						<th>团编号</th>
						<th>商品信息</th>
						<th>订单原价</th>
						<th>实收金额</th>
						<th>支付订单号</th>
						<th>订单号</th>
						<th>来源渠道</th>
						<th>支付方式</th>
						<th>活动ID</th>
						<th>活动来源</th>
						<th>创建时间</th>
						<th>退款状态</th>
						<th>商户退款单号</th>
						<th>退款时间</th>
						<th>客服留言</th>
						<s:if test="#session.role.roleId==12 || #session.role.roleId==13 || #session.role.roleId==1"><th>操作</th></s:if>
					</tr>
					<tbody id="body"></tbody>    
				</table>
			</form>
			<div class="page">
				<div class="floatleft">
					总共 <i id="rowcount">${page.rowCount}</i> 条/<i id="pagecount"></i>页
				</div>
				<div style="float: right" id="Pagination" class="pagination"></div>
				<div class="Clear"></div>
			</div>
		</div>
	</div>
	</script>
</body>
</html>
<script type="text/javascript">
	var ctx  ="<s:property value="ctx" />";
	var pagecount = "${page.rowCount}"; 
	/**
	 ** 条件查询函数 
	 **/
	function query() {
		pageNumber = 1;
		if(tt.validate()){
			var rand=Math.random() * (100001);
			queryData("userOrderRefundRowCnt.do", "userOrderRefundList.do?randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	 var channelStr = "";
	 var payMethodStr = "";
	 var sourceStr = "";
	 var isRefundStr = "";
	 var tuikuanStr="";
	function installPage() {
		if(this.channel == 0){
			channelStr = "PC端";
		} else if(this.channel == 1){
			channelStr = "APP";
		} else if(this.channel == 2){
			channelStr = "微信";
		}
		
		if(this.payMethod == 1){
			payMethodStr = "支付宝";
		} else if(this.payMethod == 2 || this.payMethod == 8){
			payMethodStr = "微信";
		} else if(this.payMethod == 3){
			payMethodStr = "货到付款";
		} else if(this.payMethod == 4){
			payMethodStr = "钱包支付";
		} else if(this.payMethod == 5){
			payMethodStr = "银联支付";
		} else if(this.payMethod == 6){
			payMethodStr = "苹果支付";
		}
		
		if(this.source == 1){
			sourceStr="普通拼团";
		} else if(this.source == 2){
			sourceStr="团免";
		} else if(this.source == 3){
			sourceStr="猜价";
		} else if(this.source == 4){
			sourceStr="单独购";
		} else if(this.source == 5){
			sourceStr="0.1夺宝";
		} else if(this.source == 6){
			sourceStr="掌上秒杀";
		}
		
		if(this.isRefund == 0){
			isRefundStr="未退款";
			tuikuanStr = "<a class='edit_btn' onclick='groupRefund("+this.id +")'>退款</a>";
		} else if(this.isRefund == 1){
			isRefundStr="处理中";
			tuikuanStr = "";
		} else if(this.isRefund == 2){
			isRefundStr="退款成功";
			tuikuanStr = "";
		} else if(this.isRefund == 3){
			isRefundStr="退款失败";
			tuikuanStr = "<a class='edit_btn' onclick='groupRefund("+this.id +")'>退款</a>";
		}
		
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.userId + "<br>"+this.userName+"</td>"+
				"<td>"+ this.attendId + "</td>"+
				"<td>"+this.productNum+"<br/><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+this.productImage+"' width='50px' height='50px'></td>"+
				"<td>"+ this.allPrice + "</td>"+
				"<td>"+ this.factPrice + "</td>"+
				"<td>"+ this.outTradeNo + "</td>"+
				"<td>"+ this.orderNo + "</td>"+
				"<td>"+ channelStr + "</td>"+
				"<td>"+ payMethodStr + "</td>"+
				"<td>"+ this.activityId + "</td>"+
				"<td>"+ sourceStr + "</td>"+
				"<td>"+ this.createDateStr + "</td>"+
				"<td>"+ isRefundStr + "</td>"+
				"<td>"+ this.outRefundNo + "</td>"+
				"<td>"+ this.refundDateStr + "</td>"+
				"<td>"+ this.remarks + "</td>"+
				<s:if test="#session.role.roleId == 12 || #session.role.roleId==13 || #session.role.roleId == 1">
				"<td>"+tuikuanStr+"</td>"+
				</s:if>
				""
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "userOrderRefundList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
	//退款
	/*function groupRefund(orderId,payMethod){
		if(confirm("确认要退款吗？")){
	         $.ajax({
	             type: "POST",
	             url: "groupRefundHandle.do",
	             data: {orderId:orderId,payType:payMethod},
	             dataType: "json",
	             success: function(data){
	                   if(data == "1"){
	                  	 alert("退款成功!");
	                   } else if(data == "2"){
	                  	 alert("查询不到已支付订单!");
	                   } else if(data == "3"){
	                  	 alert("查询不到支付宝记录!");
	                   } else if(data == "4"){
	                  	 alert("支付宝交易号为空或实付金额为空!");
	                   } else if(data == "5"){
	                  	 alert("查询不到已支付订单!");
	                   } else if(data == "6"){
	                  	 alert("订单id不能为空!");
	                   } else if(data == "7"){
	                  	 alert("支付类型有误!");
	                   } else {
	                	 alert("退款失败:"+data);
	                   }
                 }
	         });
		}else{
			return false;
		}
	}*/
	function groupRefund(orderId){
		if(confirm("确认要退款吗？")){
			var pageNoVal = $("#pageNo").val();
			var isRefundVal = $("#isRefund").val();
			var payTypeVal = $("#payType").val();
			MAOWU.ajax.get("goaliWxRefund.do","tids="+orderId+"&page.pageNo="+pageNoVal, uncheckRefreshPage1);
		}else{
			return false;
		}
	}
</script>