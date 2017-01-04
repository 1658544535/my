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
function checkAll(){
	document.getElementById("idform").submit();
}
function check(val)
{
	//alert(val);
	if(confirm("确认要通过审核吗？(成功：普通用户将升级为批发商)"))
	{
		//alert(val);
		
		var url = "checkAgency.do?agency.agencyId="+val;	
		doOpreator(url,null);
	}else{
		return ;
	}
	
}
function doOpreator(url,params){
	MAOWU.ajax.get(url, params, goRefreshPage);
}
function goRefreshPage(result){
	
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("审核成功");
		queryData("goFindPushCount.do", "goFindPushOrder.do?agencyId=<s:property value='agency.agencyId'/>&randdelete="+rand);
	}else{
		alert("审核失败");
	}
}
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">批发商账户管理</a> &gt; <a href="#">账户信息</a> &gt; <a href="#">查询推送信息</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="agencyPushOrder.do?agency.agencyId="+this.agencyId+" method="post" id="sysform">
		<div id="search_show" style="">
	   <table width="100%" border="0" class="Search_table"> 
		<!--<tr>
			<td align="right">订单号：</td>
			<td><label><input type="text" name="agencyPush.orderNo"
					value="${agencyPush.orderNo}"></label></td>
			<td align="right">推送状态：</td>
			<td><label><input type="text" name="agencyPush.pushStatus"
					value="${agencyPush.pushStatus}"></label></td>
		</tr>-->
		<!-- 这里做了限制只能选择今天以前的日期(包括今天) -->
		<tr>
		
			<td align="right">推送开始日期：</td><td><input name="agencyPush.beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
			
			<td align="right">推送结束日期：</td><td><input name="agencyPush.endday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
			
		</tr>
		<tr>
		
		<td align="right">确认开始日期：</td><td><input name="agencyPush.beganday2" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
		
		<td align="right">确认结束日期：</td><td><input name="agencyPush.endday2" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
		
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
		<!--	<s:if test="#session.role.roleId!=2"><a class="Add_btn" onclick="checkAll()">批量审核</a></s:if>  -->
			<form action="agencyChecks.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
							<th>批发商昵称</th>
							<th>订单号</th>
							<th>推送状态</th>
							<th>推送时间</th>
							<th>确认时间</th>
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
</body>
</html>


<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("goFindPushCount.do", "goFindPushOrder.do?agencyId=<s:property value='agency.agencyId'/>&randquery="+rand);
	}
}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.agencyId +" />"+
						"</td><td>" + this.agencyName +
				        "</td><td>" + this.orderNo + 
				        "</td><td>" + this.pushStatus+
				        "</td><td>" + this.createDateString+ 
				        "</td><td>" + this.updateDateString+
						"</td></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"goFindPushOrder.do?agencyId=<s:property value='agency.agencyId'/>&randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>