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
				$(location).attr('href', 'getWalletExcel.do?userWalletPojo.paixu=4&'+allformParam);
			}
		});
});
function deleteAll(){
		$("#idform").attr("action","delUserWalletAll.do").submit();
	}
function del(val)
	{
		if(confirm("你真的想删除该记录么？"))
		{
			var url = "delUserWallet.do?userWalletPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">用户钱包</a> &gt; <a href="userWallet.do">用户钱包管理</a></div>
    <div class="Search_control">
		<p>按条件查找</p>
		<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="userWallet.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">用户账号：</td>
						<td><label><input type="text" name="userWalletPojo.loginName" id="userWalletPojo.loginName" value=""></label></td>
						<td align="right">用户昵称：</td>
						<td><label><input type="text" name="userWalletPojo.userName" id="userWalletPojo.userName" value=""></label></td>
						<td align="right">嫌疑对象：</td>
						<td><select name="userWalletPojo.blackFlag" id="userWalletPojo.blackFlag"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">否</option>
							<option value="1">是</option>
							</select>
						</td>
						<!-- <td align="right">用户id：</td>
						<td><label><input type="text" name="userWalletPojo.userId" id="userWalletPojo.userId" value=""></label></td>
						<td align="right">钱包余额：</td>
						<td><label><input type="text" name="userWalletPojo.balance" id="userWalletPojo.balance" value=""></label></td> -->
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
<div style="float:right">
<a class="submit_btn" href="userWallet.do?userWalletPojo.paixu=1">余额↑</a>
<a class="submit_btn" href="userWallet.do?userWalletPojo.paixu=2">余额↓</a>
<a class="submit_btn" href="userWallet.do?userWalletPojo.paixu=3">总额↑</a>
<a class="submit_btn" href="userWallet.do?userWalletPojo.paixu=4">总额↓</a><br>
<input type="button" value="导出所有结果"  id="excel" class="submit_btn" style="float: right;"  />
<!-- <s:if test="#session.role.roleId!=7"><a class="delAll_btn" onclick="deleteAll()">批量删除</a>
<a class="Add_btn" href="goAddUserWallet.do" >新增用户钱包</a></s:if> -->
</div>
<form action="deletecouponAllById.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>用户账号</th>
		<th>用户昵称</th>
		<th>钱包余额</th>
		<th>累计总额</th>
		<!-- <th>嫌疑对象</th> -->	
		<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>
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
		queryData("userWalletCount.do", "userWalletAll.do?randquery="+rand);
	}
}
/**
*分页展现页面函数 
**/
	function installPage() {
	if(this.blackFlag == 1){
		var trStr = "<tr style=\"color:#FF0000;\">";
	} else {
		var trStr = "<tr>";
	}
		$("#body").append(
				trStr+
				"<td>"+ this.loginName + "</td>"+
				"<td>"+ this.userName + "</td>"+
				"<td>"+ this.balance + "</td>"+
				"<td>"+ this.totalBalance + "</td>"+
				<!-- "<td>"+ this.blackFlagName + "</td>"+ -->
				"<s:if test="#session.role.roleId!=7"><td><a class='edit_btn' href='userWalletLog.do?userWalletLogPojo.userId="+this.userId+"'>钱包明细</a></td></s:if>"
				);
	}	
$(function() {
	/**
		  首次要初始化分页
		 **/
	 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userWalletAll.do?userWalletPojo.paixu=${userWalletPojo.paixu}&randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
<script type="text/javascript">
</script>