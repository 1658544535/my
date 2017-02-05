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
<script type="text/javascript">
$(function(){
		$("#excel").click(function() {			
			if(tt.validate()){
				var formParam = $("#sysform").serialize();
				var formParam1 = $("#idform").serialize();
				var allformParam = formParam+"&"+formParam1;
				$(location).attr('href', 'getOrderExcelExport.do?'+allformParam);
			}
		});
	$("#excelAll").click(function() {
		if(tt.validate()){
			var formParam = $("#sysform").serialize();
			var formParam1 = $("#idform").serialize();
			var allformParam = formParam+"&"+formParam1;
			$(location).attr('href', 'getOrderExcelExport.do?isAll=1&'+allformParam);
		}
});
});
function allcb(){
	var checkbox=document.getElementById("selectcb"); 
	if(checkbox.checked==true){
		var code_Values = document.getElementsByName("tids") ; 
		for(i = 0;i < code_Values.length;i++){ 
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

	//多路径提交
	function manySend(href){
		var form = document.getElementById("idform");
		form.action = href;//传想要跳转的路径
		form.submit();
		} 

	function deleteAll(){
		if (confirm("你真的想删除所选记录么？")) {
			//manySend("OrderDeleteId.do?os=${os}");
			document.getElementById("idform").submit();
			} else {
				return;
			}
	}

	function del(val)
	{
		if(confirm("你真的想删除该记录么？"))
			{
				var url = "isDelete.do?order.id="+val;	
				doOpreator(url,null);
			}else{
				return ;
			}
	}
	function update(id)
	{
		var idt = 'code'+id;
		var d=document.getElementById(idt);
		if(d != null){
		d = window.encodeURI(window.encodeURI(d.value));  
		 $.ajax({
             type: "get",
             url: "code.do?id="+id+"&remarks="+d,
             success: function(data){
             	alert("成功");
             }
             });	
		}
            
	}
	function doOpreator(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage);
	}
	 
	function goRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("删除成功");
			pageNumber = $("#pageNo").val();
			queryData("getOrderCount.do?os=${os}&a=${a}&orderType=${orderType}", "orderAllList.do?os=${os}&a=${a}&orderType=${orderType}&randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	//push
	function pushOrder(val)
	{
		if(confirm("推送此订单信息么？"))
			{
				var url = "orderPush.do?order.id="+val;	
				doOpreator2(url,null);
			}else{
				return ;
			}
	}
	function doOpreator2(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage2);
	}
	 
	function goRefreshPage2(result){
		if(result=="1"){
			alert("推送成功");
		}else{
			alert("推送失败");
		}
	}
	
	function checkOrderType(val){
		if(confirm("确定给予发货么？")){
			var url = "checkOrderType.do?order.id="+val;	
			doOpreator3(url,null);
		}
	}
	/* 
	function doOpreator3(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage3);
	}
	 
	function goRefreshPage3(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核成功");
			queryData("getOrderCount.do?os=${os}&a=${a}&orderType=${orderType}", "orderAllList.do?os=${os}&a=${a}&orderType=${orderType}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function checkOrderTypeAll(){
		if (confirm("确定给予发货么？")) {
			$("#idform").attr("action","checkOrderTypeAll.do?os=${os}&testcount=${testcount}&orderType=${orderType}").submit();
		}
	}
	 */
	 function updatesendOrder(){
		if (confirm("确定收货么？")) {
				var code_Values = document.getElementsByName("tids");
				var times_Values = document.getElementsByName("times2") ; 	
				var num=0;
				for(i = 0;i < code_Values.length;i++){
				if(code_Values[i].type == "checkbox") 
				{ 
					if(code_Values[i].checked == true) 
						{ 		
						    num++;			
							if(times_Values[i].value==null || times_Values[i].value==""){

							code_Values[i].checked = false;
							num--;
							}
						} 
					}
					
				} 	
				if(num<=0){
				alert("您未勾选 或 勾选的订单都未超过15天没确认！");
				}else{				
			$("#idform").attr("action","updatesendOrder.do?os=${os}").submit();
			}
		}
	}
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">订单管理</a> &gt; <a href="#">用户订单</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="order.do?os=${os}&a=${a}" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">订单号：</td>
						<td><label><input type="text"
								name="order.orderNo" id="ticketRulePojo.ticketName"
								value="${order.orderNo }"></label>
						</td>
						<td align="right">商品ID：</td>
						<td><label><input type="text"
								name="order.productId" id="ticketRulePojo.ticketName"
								value="${order.productId }"></label>
					    </td>
					    <td align="right">收货人：</td>
						<td><label><input type="text"
								name="order.consignee" id="ticketRulePojo.ticketName"
								value="${order.consignee }"></label>
						</td>
					    <td align="right">收货人电话：</td>
						<td><label><input type="text"
								name="order.consigneePhone" id="ticketRulePojo.ticketName"
								value="${order.consigneePhone }"></label>
						</td>
					</tr>
					<tr>	
						<td align="right">支付状态：</td>
						<td><select name="order.payStatus" id="" class="floatLeft">
									<option value="">----请选择----</option>
									<option value="0" <c:if test="${order.payStatus == 0 }">selected=selected</c:if>>未支付</option>
									<option value="1" <c:if test="${order.payStatus == 1 }">selected=selected</c:if>>已支付</option>
							</select>
						</td>
					  	<td align="right">订单状态：</td>
						<td>
							<select name="order.orderStatus" id=""
								class="floatLeft">
									<option value="">----请选择----</option>
									<c:forEach items="${orderStatus}" var="orderStatus">
											<c:if test="${orderStatus.value == 2 or orderStatus.value == 3 or orderStatus.value == 4 }"><option value="${orderStatus.value}" <c:if test="${order.orderStatus == orderStatus.value }">selected=selected</c:if>>${orderStatus.name}</option></c:if>
									</c:forEach>
							</select>
						</td>	
						<c:if test="${os == '' || os == 3 || os == 8}">
					    <td align="right">售后状态：</td>
						<%-- <td><select name="order.isRefund" id="" class="floatLeft">
									<option value="">----请选择----</option>
									<option value="0" <c:if test="${order.isRefund == 0 }">selected=selected</c:if>>未退款</option>
									<option value="1" <c:if test="${order.isRefund == 1 }">selected=selected</c:if>>处理中</option>
									<option value="2" <c:if test="${order.isRefund == 2 }">selected=selected</c:if>>退款成功</option>
									<option value="3" <c:if test="${order.isRefund == 3 }">selected=selected</c:if>>退款失败</option>
							</select>
						</td> --%>
						<td><select name="order.refundStatus" id="" class="floatLeft">
									<option value="">----请选择----</option>
									<option value="0" <c:if test="${order.refundStatus == 0 }">selected=selected</c:if>>无售后/取消售后</option>
									<option value="1" <c:if test="${order.refundStatus == 1 }">selected=selected</c:if>>售后处理中</option>
									<option value="2" <c:if test="${order.refundStatus == 3 }">selected=selected</c:if>>退款中</option>
									<option value="3" <c:if test="${order.refundStatus == 4 }">selected=selected</c:if>>退款成功</option>
							</select>
						</td>
						</c:if>
						<td align="right">快递单号：</td>
						<td><label><input type="text"
								name="order.logisticsNo" id="ticketRulePojo.ticketName"
								value="${order.logisticsNo }"></label>
					    </td>
					</tr> 				
					<tr>	
						<td align="right">用户账号：</td>
						<td><label><input type="text"
								name="order.loginname" id="ticketRulePojo.ticketName"
								value="${order.loginname }"></label>
						</td>
						<td align="right">团编号：</td>
						<td><label><input type="text"
								name="order.attendId" id="ticketRulePojo.ticketName"
								value="${order.attendId }"></label>
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
	                    <s:if test="#request.os==3||#request.os==''||#request.os==8">
						<td align="right">发货超过15天：</td>
						<td>
								<select name="order.overdue" id="idAuto6" class="floatLeft"">
										<option value="">----请选择----</option>
										<option value="1" <c:if test="${order.overdue == 1 }">selected=selected</c:if>>是</option>
										<option value="0" <c:if test="${order.overdue == 0 }">selected=selected</c:if>>否</option>
										
								</select>
					    </td>     
					    </s:if> 
					</tr>
					<tr>
						<!-- 这里做了限制只能选择今天以前的日期(包括今天) -->		
						<td align="right">创建日期：</td>
						<td><input value="${order.beganday }" name="order.beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm', isShowToday: false, isShowClear: true })" id="create-time-start-selector"/></td>
						<td><input value="${order.endday }" name="order.endday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm', isShowToday: false, isShowClear: true })" id="create-time-end-selector"/></td> 
	                    <td>                      
	                                            <a href="javascript:setOrderTime(1);">昨天</a>
	                                            <a href="javascript:setOrderTime(7);">最近7天</a>
	                                            <a href="javascript:setOrderTime(30);">最近30天</a>
	                    </td>  
			    		<c:if test="${os!=1 && os!=6 && os!=7}">
	                    <td align="right">成团时间：</td>
						<td>
							<input value="${order.groupBeginDateStr }" name="order.groupBeginDateStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })" id="create-time-start-selector"/>
						</td>
						<td>
							<input value="${order.groupEndDateStr }" name="order.groupEndDateStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })" id="create-time-end-selector"/>
						</td>
						</c:if>
					</tr>
					<tr>
						<s:if test="#request.os==3||#request.os=='' ||#request.os==8">
						<td align="right">发货日期：</td>
						<td><input value="${order.beganSendDate }" name="order.beganSendDate" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })" id="create-time-start-selector"/></td>
						<td><input value="${order.endSendDate }" name="order.endSendDate" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })" id="create-time-end-selector"/></td>
					    </td>
						</s:if>	 
						<td align="right">24小时未发货：</td>
						<td>
							<select name="order.notShip" id="" class="floatLeft">
									<option value="">----请选择----</option>
									<option value="1" >是</option>
							</select>
					    </td>   
					    <%-- <td align="right">开团时间：</td>
						<td><input value="${order.beganday1 }" name="order.beganday1" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm', isShowToday: false, isShowClear: true })" id="create-time-start-selector"/></td>
						<td><input value="${order.endday1}" name="order.endday1" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm', isShowToday: false, isShowClear: true })" id="create-time-end-selector"/></td> --%> 
					</tr>
					<tr>
						<td align="right">拼得客账号：</td>
						<td>
							<input value="" name="order.pdkLoginname" type="text" />
						</td>
						<td align="right">商家：</td>
						<td>
							<select name="order.suserId" id="" class="floatLeft">
									<option value="">----请选择----</option>
									<c:forEach items="${suserList}" var="suser">
										<option value="${suser.id}">${suser.name}</option>
									</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<input type="hidden" name="order.os" value="${os}" id="order.orderStatus">
				<input type="hidden" name="testcount" value="${testcount}" id="testcount">
				<div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>

				<div class="Clear"></div>
			</div>
		</form>
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
		<s:if test="#session.role.roleId!=7"> 
		    <c:if test="${os==3}"><a class="Add_btn"  onclick="updatesendOrder()" >确认收货(批量)</a></c:if>
			<s:if test="#session.user.type!=4"><!--<a class="delAll_btn" onclick="deleteAll()">删除全部</a>-->
			<%--<s:if test="#request.orderType!=1"><a class="Add_btn"  onclick="checkOrderTypeAll()" >发货审核(批量)</a></s:if>--%>
			<input title="导出所有结果" type="button" value="导出所有"  id="excelAll" class="submit_btn" style="float: right;"  />
			<input title="导出查询结果（可以筛选）" type="button" value="导出查询结果"  id="excel" class="submit_btn" style="float: right;margin-left: 20px;margin-right: 20px;"  /></s:if></s:if>
			<form action="OrderDeleteId.do?os=${os}" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<!--<th>用户昵称</th> -->
						<!-- <th>店铺名称</th> -->
						<th>订单号</th>
						<!-- <th>快递单号</th>-->
						<!-- <th>来源渠道</th> -->
						<th>用户账号</th>
						<th>拼得客账号</th>
						<th>团编号</th>
						<th>商品</th>
						<th>名称</th>
						<th>订单金额</th>
						<th>开团时间</th>
						<th>参团时间</th>
						<c:if test="${os!=1 && os!=6 && os!=7}">
						<th>成团时间</th>
						</c:if>
						<th>收货人</th>
						<th>商品总数量</th>
						<th>实付金额</th>
						<th>订单来源</th>
						<th width=80px>状态</th>
						<!--<th>钱包支付金额</th> -->
						<!-- <th>订单状态</th>-->
						<!-- <th>支付方式</th>-->
						<!-- <th>支付状态</th>-->
						<!-- <th>收货人</th>-->
						<!-- <th>发货方式</th>-->
						<!-- <th>是否有退货</th>-->
						<!-- <th>订单商品图片</th>
						<!-- <th>商品总数量</th>
						<th>收货人电话</th>
						<th>创建时间</th>-->
						<s:if test="#request.os==3||#request.os==''">
						<th>发货时间</th>
						</s:if>
						<th>退货退款状态</th>
						<!-- <th>售后状态</th> -->
						<th>买家留言</th>
						<th>IP</th>
						<!-- <th>操作者</th>-->
						<!-- <th>批发商</th> -->
						<th>客服留言</th>
						<th width=130px>操作</th>
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
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		pageNumber = 1;
		var rand=Math.random() * ( 100000 + 1);
		queryData("getOrderCount.do?os=<s:property value='os'/>&a=<s:property value='a'/>&orderType=<s:property value='orderType'/>&order.userId=${order.userId}", "orderAllList.do?os=<s:property value='os'/>&a=<s:property value='a'/>&orderType=<s:property value='orderType'/>&order.userId=${order.userId}&randquery="+rand);
	}
}
   //当前时间
    var now= new Date();
    var nowTime=now.getTime();
	//分页展现页面函数 
	function installPage() {
	if(this.isHandle==0){
	 handleString = "<a class='edit_btn' href='updateOrderhandleStatus.do?order.id="+this.id +"&os=<s:property value='os'/>&order.isHandle="+this.isHandle+"' onclick='javascript:return window.confirm(\"确定移入待处理？\");'>移入待处理</a>";
	}else if(this.isHandle==1 && "<s:property value='os'/>"=="8"){
	 handleString = "<a class='edit_btn' href='updateOrderhandleStatus.do?order.id="+this.id +"&os=<s:property value='os'/>&order.isHandle="+this.isHandle+"' onclick='javascript:return window.confirm(\"确定处理完成？\");'>处理完成</a>";
	}else{
	 handleString = "<a class='edit_btn' onclick=goOrderDetail("+this.id+","+this.userId+",'<s:property value='os'/>')>查看处理进度</a>";
	}
	
	if(this.groupDateStr!=""){
	var groupDate=new Date(this.groupDateStr)
	//成团时间加上24小时
	groupDate.setTime(groupDate.getTime()+24*3600*1000);
	var groupDateLaterTime=groupDate.getTime();
	}else{
	var groupDateLaterTime="";
	}
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
	var isSuccessName="";
	if(this.isSuccess == 0){
		isSuccessName = "拼团中";
	}else if(this.isSuccess == 1){
	 isSuccessName = "拼团成功";
	}else if(this.isSuccess == 2){
	 isSuccessName = "拼团失败";
	}
	if(this.sourceName == "单独购"){
		isSuccessName = "";
	}
	if(this.channel == 0){
	var channel = "PC端";
	}
	if(this.channel == 1){
	var channel = "APP";
	}
	if(this.channel == 2){
	var channel = "微信";
	}
	var consigneeType = this.consigneeTypeName;
	var payStatusName = this.payStatusName;
	var orderStatus = this.orderStatus;
	var times=this.times;
	var isSuccess=this.isSuccess;
	var refundStatusName="";
	var vString="";
	var qrString = "";
	var sss="";
	var ddd="";
	var orderTypeStr = "";
	var isCancelOrder=this.isCancelOrder;
	if(this.refundStatus1==1){
	 refundStatusName="未审核";
	}else if(this.refundStatus1==2){
	 refundStatusName="请退货";
	}else if(this.refundStatus1==3){
	 refundStatusName="退货中";
	}else if(this.refundStatus1==4){
	 refundStatusName="退货成功";
	}else if(this.refundStatus1==5){
	 refundStatusName="退货失败";
	}else if(this.refundStatus1==6){
	 refundStatusName="审核不成功";
	}else if(this.refundStatus1==7){
	 refundStatusName="退款成功";
	}else{
	 refundStatusName="无退货退款";
	}
	
	if(this.isCancelOrder==0){
		ddd=this.orderNo;
	}else
	{
		ddd="<font color='red'>"+this.orderNo+"</font>";
	}
	if(consigneeType == "快递"){
		if(orderStatus==2 && isSuccess==1 && isCancelOrder!=1){
	 	 vString = "<a class='edit_btn' href='goOrderShipAdd.do?order.id="+this.id +"&os=<s:property value='os'/>&pageNoVal="+pageNoVal+"&formParam="+formParam+"'>发货</a>";
	 	}
	 	sss=this.sendTimes;
	}else{
		if(orderStatus==2 && isSuccess==1 && isCancelOrder!=1){
		 vString ="<a class='edit_btn' href='updatesendOrderStatus.do?order.id="+this.id +"&os=<s:property value='os'/>&order.orderStatus="+this.orderStatus+"&pageNoVal="+pageNoVal+"&formParam="+formParam+"' onclick='javascript:return window.confirm(\"确定发货？\");'>发货</a>";
		}
		sss=this.sendTimes;
	}
	 var reSession = "<%=request.getSession().getAttribute("loginPojoId")%>";//获取session
	 
	if(times!=null && times!="" && orderStatus==3){
		 qrString = "<a class='edit_btn' href='updatesendOrderStatus.do?order.id="+this.id +"&os=<s:property value='os'/>&order.orderStatus="+this.orderStatus+"' onclick='javascript:return window.confirm(\"确定订单？\");'>确定订单</a>";
	}else{
		 qrString = "";
	}
	if(payStatusName == "待支付"&&reSession == 1){
	
		var delString = "<a  class='del_btn' onclick=del('"+this.id+"')>删除</a>";
	}
	else{
		var delString = "";
	}
	/* 
	if(this.orderStatus == 2 && this.agencyUserId==0){
		var pushString = "<a class='edit_btn' href='pushOrderManage.do?order.id="+this.id+"'>推送</a>"+"<a class='edit_btn' href='updatePushName.do?order.id="+this.id+"'>直发</a>";
	}else{
		var pushString = "";
	}
	 */
	if(this.refundStatus > 0){
		var trStr = "<tr style=\"color:#2EB5F4;\">";
	}else if( groupDateLaterTime!="" && this.orderStatus == 2 && nowTime > groupDateLaterTime){
		var trStr = "<tr style=\"color:#CC3299;\">";
	} else {
		var trStr = "<tr>";
	}
	/* 
	if(this.orderType == 2) {
		orderTypeStr =  "<a  class='edit_btn' onclick=checkOrderType('"+this.id+"')>发货审核</a>";
	} else {
		orderTypeStr = "";
	}
	 */
	var trString= trStr+"<td><input  name='tids' type='checkbox' value="+this.id +"   /><input name='times2' value='"+this.times+"' type='hidden'/></td><td>"              	
		        //+ this.userName + 
		        //"</td><td>" + this.shopName+
		        + ddd+
		        "</td><td>" + this.loginname+	   
		        "</td><td>" + this.pdkLoginname+	   
		        "</td><td>" + this.attendId+	
		        "</td><td>" + this.productId+ "<p><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+this.productImages+"'  height='100px'></p></td>"+  
		        "</td><td>" + this.productNum+ "<p>"+this.productName+"</p></td>"+
		        "</td><td>" + this.allPrice+ 
		        "</td><td>" + this.headAttendTimeStr+ "</td>"+
		        "</td><td>" + this.attendTimeStr+ "</td>"+
		        "</td><c:if test="${os!=1 && os!=6 && os!=7}"><td>" + this.groupDateStr+ "</td></c:if>"+
		        "</td><td>" + this.consignee+
		        "</td><td>" + this.count+
		        "</td><td>" + this.factPrice+ 
		        
		        "</td><td>" + this.sourceName+ 
		        
		        "</td><td>" + this.orderStatusName+"<p>"+this.payStatusName+"</p>"+"<p>"+isSuccessName+"</p>"+
		        //"</td><td>" + this.logisticsNo+ 		         
		        //"</td><td>" + channel+ 
		        //"</td><td>" + this.walletPrice+ 
		        //"</td><td>" + this.orderStatusName+
		       // "</td><td>" + this.payMethodName+
		        //"</td><td>" + this.payStatusName+
		        //"</td><td>" + this.consignee+
		       // "</td><td>" + this.consigneeTypeName+
		       // "</td><td>" + this.refundStatusName+
		      //  "</td><td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/small/"+this.productImages+"'  height='100px'>"+
		      //  "</td><td>" + this.count+
		     //   "</td><td>" + this.consigneePhone+
		       // "</td><td>" + this.creatDateString+ "</td>"+
		        <s:if test="#request.os==3||#request.os==''">
		        "</td><td>" + sss+ "</td>"+
		        </s:if>
		        "</td><td>" + refundStatusName+ "<br/>"+isRefundName+"</td>"+
		        //"<td>"+isRefundName+"</td>"+
		        "<td>"+this.buyerMessage+"</td>"+
		        "<td width='100px'>"+this.ip+"</td>"+
		        //"</td><td>" + this.updateByName+ "</td>"+
                //"</td><td>" + this.pushName+ "</td>"+
		        "</td><td><textarea rows=5 id='code"+this.id+"'>"+this.remarks+"</textarea></td>"+
		        "<s:if test="#session.role.roleId!=7"><td width=80px><!--<a  class='del_btn' onclick=del('"+this.id+"')>删除</a>-->"+
		        "<a class='edit_btn' onclick=goOrderDetail("+this.id+","+this.userId+",'<s:property value='os'/>')>详情</a>"+
		        handleString+
		        // 该推送方法已失效
		        // "<a class='edit_btn' onclick=pushOrder('"+this.id+"')>推送</a>"+
		        //<s:if test="#request.os==2 or #request.os==''">
		        //"<a class='edit_btn' href='pushOrderManage.do?order.id="+this.id+"'>推送</a>"+
		        //pushString+
		        //</s:if>
		       
		        <s:if test="#session.role.roleId!=2">
		        vString+
		        "<a class='edit_btn' href='goFindOrderPrint.do?order.id="+this.id +"&order.userId="+this.userId+"&os=<s:property value='os'/>"+"'>打印</a>"+
				</s:if>
				"<a class='edit_btn' onclick='update("+this.id+")'>留言</a>"+
		        delString+ 
		        orderTypeStr+
		       <s:if test="#request.os==3 or #request.os==''">
		         qrString+
				</s:if>
				"</td></s:if>"+
				"<s:if test="#session.role.roleId==7"><td><a class='edit_btn' onclick='goOrderDetail("+this.id+","+this.userId+",'<s:property value='os'/>')'>详情</a></td></s:if>"+
				"</tr>";
		$("#body").append(trString);
	}
	//跳转详情
	var pageNoVal = 1;
	function goOrderDetail(id,uid,os){
		var formParam = escape($("#sysform").serialize() + "&" + $("#idform").serialize());
		pageNoVal = $("#pageNo").val();
		window.location.href="goFindOrder.do?order.id="+id+"&order.userId="+uid+"&os="+os+"&page.pageNo="+pageNoVal+"&formParam="+formParam;
	}
	
	$(function() {
		 pageNumber = '${pageNoVal == null ? 1 : pageNoVal}';
		 console.log(pageNumber);
		 //首次要初始化分页
		 var rand=Math.random() * ( 100000 + 1);
		 MAOWU.page.init(<s:property value="page.rowCount"/>,
				"orderAllList.do?os=<s:property value='os'/>&a=<s:property value='a'/>&orderType=<s:property value='orderType'/>&order.userId=${order.userId}&randIni="+rand);
		 $("#query_btn").click(query);
	});
	    //设置搜索日期
    function setOrderTime(num){
        var nowDate = new Date();
        var nYear = nowDate.getFullYear(),
            nMonth = nowDate.getMonth()+1,
            nDay = nowDate.getDate();
            time = (nowDate.getHours()) + ':' + (nowDate.getMinutes()) + ':' + (nowDate.getSeconds());

        var startTimeTemp = nowDate.getTime()-(86400000*num);
        var startTimeDate = new Date(startTimeTemp);
        console.log(startTimeTemp);
        var sYear = startTimeDate.getFullYear(),
            sMonth = startTimeDate.getMonth()+1,
            sDay = startTimeDate.getDate();

        var startTime = sYear+'-'+sMonth+'-'+sDay+' '+time,
            endTime = nYear+'-'+nMonth+'-'+nDay+' '+time;

        $("#create-time-start-selector").val(startTime);
        $("#create-time-end-selector").val(endTime);
    }
</script>