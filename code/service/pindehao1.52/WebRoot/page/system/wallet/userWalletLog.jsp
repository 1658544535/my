<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
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
$(function(){
		$("#excel").click(function() {			
			if(tt.validate()){
				var formParam = $("#sysform").serialize();
				var formParam1 = $("#idform").serialize();
				var allformParam = formParam+"&"+formParam1;
				$(location).attr('href', 'getWalletLogExcel.do?userWalletLogPojo.userId=${userWalletLogPojo.userId}&'+allformParam);
			}
		});
});
function deleteAll(){
		$("#idform").attr("action","delUserWalletLogAll.do").submit();
	}
function del(val)
	{
		if(confirm("你真的想删除该记录么？"))
		{
			var url = "delUserWalletLog.do?userWalletLogPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">用户钱包</a> &gt; <a href="userWalletLog.do">用户钱包使用记录</a></div>
    <div class="Search_control">
		<p>按条件查找</p>
		<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="userWalletLog.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">用户账号：</td>
						<td><label><input type="text" name="userWalletLogPojo.loginName" id="userWalletLogPojo.loginName" value=""></label></td>
						<td align="right">当前余额：</td>
						<td><label><input type="text" name="userWalletLogPojo.curBal" id="userWalletLogPojo.curBal" value=""></label></td>	
						<td align="right">交易方向：</td>
						<td><select name="userWalletLogPojo.type" id="userWalletLogPojo.type"  class="floatLeft">
						    <option value="">----请选择----</option>
							<option value="0">收入</option>
							<option value="1">支出</option>
							</select></td>
					</tr>
					<tr>
						<td align="right">记录来源账号：</td>
						<td><label><input type="text" name="userWalletLogPojo.sourceName" id="userWalletLogPojo.sourceName" value=""></label></td>
						<td align="right">机器码：</td>
						<td><label><input type="text" name="userWalletLogPojo.machineCode" id="userWalletLogPojo.machineCode" value=""></label></td>
						<td align="right">订单号：</td>
						<td><label><input type="text" name="userWalletLogPojo.orderNo" id="userWalletLogPojo.orderNo" value=""></label></td>	
					</tr>
					<tr>
						<td align="right">交易时间区间：</td><td width="80px"  style="padding: 0px 0px;">从<input style="min-width:60px;" name="userWalletLogPojo.createDateStartStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></td>
					    <td>到<input style="min-width:60px;" name="userWalletLogPojo.createDateEndStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></td>
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
<input type="button" value="导出所有结果"  id="excel" class="submit_btn" style="float: right;"  />
<!-- <s:if test="#session.role.roleId!=7"><a class="delAll_btn" onclick="deleteAll()">批量删除</a>
<a class="Add_btn" href="goAddUserWalletLog.do" >新增用户钱包使用记录</a></s:if> -->
<form action="deletecouponAllById.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>用户账号</th>
		<th>当前余额</th>
		<th>交易方向</th>	
		<th>交易金额</th>
		<th>记录来源账号</th>
		<th>机器码</th>
		<th>来源账号注册时间</th>
		<th>订单号</th>
		<th>交易时间</th>
		<!-- <s:if test="#session.role.roleId!=7"><th>操作</th></s:if>  -->
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
		queryData("userWalletLogCount.do", "userWalletLogAll.do?randquery="+rand);
	}
}
/**
*分页展现页面函数 
**/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td>"+ this.loginName + "</td>"+
				"<td>"+ this.curBal + "</td>"+
				"<td>"+ this.typeName + "</td>"+
				"<td>"+ this.tradeAmt + "</td>"+
				"<td>"+ this.sourceName + "</td>"+
				"<td>"+ this.machineCode + "</td>"+
				"<td>"+ this.registeredTime + "</td>"+
				"<td>"+ this.orderNo + "</td>"+
				"<td>"+ this.createDateStr + "</td>"
				<!-- "<s:if test="#session.role.roleId!=7"><td><a class='del_btn' onclick=del('"+this.id+"')>删除</a></td></s:if>" -->
				);
	}	
$(function() {
	/**
		  首次要初始化分页
		 **/
	 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userWalletLogAll.do?userWalletLogPojo.userId=${userWalletLogPojo.userId}&randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
<script type="text/javascript">
</script>