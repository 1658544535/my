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
				$(location).attr('href', 'getOrderDetailExcel2.do?'+allformParam);
			}
		});
	$("#excelAll").click(function() {
		if(tt.validate()){
			var formParam = $("#sysform").serialize();
			var formParam1 = $("#idform").serialize();
			var allformParam = formParam+"&"+formParam1;
			$(location).attr('href', 'getOrderDetailExcel2.do?isAll=1&'+allformParam);
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
			//manySend("OrderDetailDeleteId.do?os=${os}");
			document.getElementById("idform").submit();
			} else {
				return;
			}
	}

	function del(val)
	{
		if(confirm("你真的想删除该记录么？"))
			{
				var url = "isDelete.do?orderDetail.id="+val;	
				doOpreator(url,null);
			}else{
				return ;
			}
	}
	function update(id)
	{
		var d=document.getElementById('code'+id).value;
		d = window.encodeURI(window.encodeURI(d));  
		 $.ajax({
             type: "get",
             url: "code.do?id="+id+"&remarks="+d,
             success: function(data){
             	alert("成功");
             }
             });	
            
	}
	function doOpreator(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage);
	}
	 
	function goRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("删除成功");
			queryData("getOrderDetailCount.do?os=${os}&a=${a}&testcount=${testcount}&orderDetailType=${orderDetailType}", "orderDetailAllList.do?os=${os}&a=${a}&testcount=${testcount}&orderDetailType=${orderDetailType}&randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	//push
	function pushOrderDetail(val)
	{
		if(confirm("推送此订单信息么？"))
			{
				var url = "orderDetailPush.do?orderDetail.id="+val;	
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
	
	function checkOrderDetailType(val){
		if(confirm("确定给予发货么？")){
			var url = "checkOrderDetailType.do?orderDetail.id="+val;	
			doOpreator3(url,null);
		}
	}
	function doOpreator3(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage);
	}
	 
	function goRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核成功");
			queryData("getOrderDetailCount.do?os=${os}&a=${a}&testcount=${testcount}&orderDetailType=${orderDetailType}", "orderDetailAllList.do?os=${os}&a=${a}&testcount=${testcount}&orderDetailType=${orderDetailType}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function checkOrderDetailTypeAll(){
		if (confirm("确定给予发货么？")) {
			$("#idform").attr("action","checkOrderDetailTypeAll.do?os=${os}&testcount=${testcount}&orderDetailType=${orderDetailType}").submit();
		}
	}
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">内容导购</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="orderDetail.do?os=${os}&a=${a}" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" borderDetail="0" class="Search_table">
					<tr>
						<td align="right">商品名称：</td>
						<td><label><input type="text" name="orderDetail.productName"></label>
					    </td>
					    <td align="right">内容类型：</td>
						<td>
							<select name="orderDetail.source" id="channel"
								class="floatLeft">
									<option value="">----请选择----</option>
									<option value="1">平台专题</option>
									<option value="2">创客专题</option>
									<option value="3">笔记</option>
									<option value="4">微页面</option>
							</select>
						</td>		
						<td align="right">发表人类型：</td>
						<td>
							<select name="orderDetail.types" id="channel"
								class="floatLeft">
									<option value="">----请选择----</option>
									<option value="0">平台</option>
									<option value="11">达人</option>
									<option value="12">创客</option>
							</select>
						</td>			
					</tr>
					<tr>
					<td align="right">昵称：</td>
						<td><label><input type="text"
								name="orderDetail.sysLoginName" id="ticketRulePojo.ticketName"
								value=""></label>
					    </td>							
                        <td align="right">日期：</td><td><input name="orderDetail.beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm', isShowToday: false, isShowClear: true })"/>				
						-<input name="orderDetail.endday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm', isShowToday: false, isShowClear: true })"/></td> 
					</tr>
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>

				<div class="Clear"></div>
			</div>
		</form>
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<form action="OrderDetailDeleteId.do?os=${os}" id="idform" method="post">
				<table width="100%" borderDetail="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>商品名称</th>
						<th>内容标题</th>
						<th>内容类型</th>
						<th>发表人类型</th>
						<th>昵称</th>
						<th>价格</th>
						<th>订单日期</th>
						<th width=80px>操作</th>
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
		var rand=Math.random() * ( 100000 + 1);
		queryData("getOrderDetailCount.do?contentGuide=1", "orderDetailAllList.do?contentGuide=1&randquery="+rand);
	}
}

	//分页展现页面函数 
	function installPage() {
	var userType="";
	var sysLoginName="";
	var contentTitle="";
	if(this.source == 1){
	 userType = "平台";
	 sysLoginName="平台";
	 contentTitle=this.contentTitle;
	}else if(this.source == 2){
	 userType = this.userType;
	 sysLoginName=this.sysLoginName;
	 contentTitle=this.contentTitle1;
	}else if(this.source == 3){
	 userType = this.userType1;
	 sysLoginName=this.sysLoginName1;
	 contentTitle=this.contentTitle2;
	}else{
	 userType = "平台";
	 sysLoginName="平台";
	 contentTitle=this.contentTitle3;
	}
	var trString= "<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td><td>" 
		        + this.productName + 
		        "</td><td>" + contentTitle+	        
		        "</td><td>" + this.sourceName+
		        "</td><td>" + userType+
		        "</td><td>" + sysLoginName+ 		         
		        "</td><td>" + this.stockPrice+ 
		        "</td><td>" + this.createDateStr1+ "</td>"+
		       // <s:if test="#request.os==3||#request.os==''">
		      //  "</td><td>" + sss+ "</td>"+
		      //  </s:if>
				"<td><a class='edit_btn' href='goFindOrder.do?guide=1&order.id="+this.orderId +"&order.userId="+this.userId+"&os=<s:property value='os'/>"+"'>详情</a></td>"+
				"</tr>";
		$("#body").append(trString);
	}

	$(function() {
		 //首次要初始化分页
		 var rand=Math.random() * ( 100000 + 1);
		 MAOWU.page.init(<s:property value="page.rowCount"/>,
				"orderDetailAllList.do?contentGuide=1&randIni="+rand);
		
		 $("#query_btn").click(query);
	});
	
</script>