<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>

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
	if(confirm("确认要通过审核吗？"))
	{
		var url = "userDistributionInfoCheckById.do?userDistributionInfoPojo.id="+val;	
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
	if(result == "1"){
		alert("审核成功");
		queryData("userDistributionInfoCount.do", "userDistributionInfoAll.do?randdelete="+rand);
	}else{
		alert("审核失败");
	}
}
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">分销商账户管理</a> &gt; <a href="#">账户信息</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="userDistributionInfo.do" method="post" id="sysform">
		<div id="search_show" style="">
		<table width="100%" border="0" class="Search_table">
		<tr>
			<td align="right">帐号：</td>
			<td><label><input type="text" name="userDistributionInfoPojo.loginname"
				    value=""></label></td>
			<td align="right">昵称：</td>
			<td><label><input type="text" name="userDistributionInfoPojo.userName"
				    value=""></label></td>
			<td align="right">姓名：</td>
			<td><label><input type="text" name="userDistributionInfoPojo.name"
				    value=""></label></td>
		</tr>
		<tr>
			<td align="right">性别：</td>
			<td><select name="userDistributionInfoPojo.sex">
				<option value="">——请选择——</option>
				<option value="0">未填写</option>
				<option value="1">男</option>
				<option value="2">女</option>
			</select></td>
			<td align="right">身份证号：</td>
			<td><label><input type="text" name="userDistributionInfoPojo.personCode"
					value=""></label></td>
			<td align="right">状态：</td>
			<td><select name="userDistributionInfoPojo.status">
				<option value="">——请选择——</option>
				<option value="0">审核中</option>
				<option value="1">通过审核</option>
				<option value="-1">未通过</option>
			</select></td>
		</tr>
		<tr>
			<td align="right">创建时间：</td>
			<td><input id="c" name="userDistributionInfoPojo.createDateStr" value="" class="Wdate" type="text" 
							onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd'})"/></td>
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
		<s:if test="#session.role.roleId!=7"><a class="Add_btn" onclick="checkAll()">批量审核</a></s:if>
			<form action="userDistributionInfoChecks.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
							<th>账号</th>	
							<th>昵称</th>	
							<th>姓名</th>
							<th>性别</th>
							<th>身份证号</th>
							<th>邀请码</th>
							<th>创建时间</th>
							<th>状态</th>
							<s:if test="#session.role.roleId!=7"><th width="11%">操作</th></s:if>
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
		queryData("userDistributionInfoCount.do", "userDistributionInfoAll.do?randquery="+rand);
	}
}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +" />"+
						"</td><td>" + this.loginname +
						"</td><td>" + this.userName +
						"</td><td>" + this.name +
				        "</td><td>" + this.sexName + 
				        "</td><td>" + this.personCode + 
				        "</td><td>" + this.externalSignCode + 
				        "</td><td>" + this.createDateStr +
				        "</td><td>" + this.statusName +
				        "</td>"+
				        "<s:if test="#session.role.roleId!=7"><td>"+
				        "<a class='edit_btn' onclick=check('"+this.id+"')>审核</a>"+
						"<a class='edit_btn' href='userDistributionInfoUpdate.do?userDistributionInfoPojo.id="+this.id +"'>编辑</a>"+
						//"<a class='del_btn' href='' onclick='javascript:return window.confirm(\"确定删除？\");'>删除</a>"+
						"</td></s:if></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userDistributionInfoAll.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>