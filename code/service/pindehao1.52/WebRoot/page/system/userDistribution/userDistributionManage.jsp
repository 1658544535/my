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

	function deleteAll(){
		document.getElementById("idform").submit();
	}

	function del(val)
	{
		if(confirm("确认审核吗？"))
		{
			
			var url = "deleSysLogin.do?sysLogin.id="+val;	
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
			queryData("", "randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">分销商账户管理</a> &gt; <a href="#">分销商推荐管理</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
				<tr>
				<td align="right">分销商账号：</td>
				<td><label><input type="text" name="sysLoginPojo.loginname"
						id=""
						value=""></label></td>
				<td align="right">分销商昵称：</td>
				<td><label><input type="text" name="sysLoginPojo.name"
						id=""
						value=""></label></td>
				<!-- <td align="right">分销商姓名：</td>
				<td><label><input type="text" name="sysLoginPojo.distribName"
						id=""
						value=""></label></td> -->
				</tr>
				<tr>
				<td align="right">被推荐账号：</td>
				<td><label><input type="text" name="sysLoginPojo.inviterAct"
						id=""
						value=""></label></td>
				<td align="right">被推荐昵称：</td>
				<td><label><input type="text" name="sysLoginPojo.inviterName"
						id=""
						value=""></label></td>
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
			<!-- <a class="delAll_btn" onclick="deleteAll()">审核全部</a> -->
			<!-- <a href="" class="Add_btn">新增用户</a> -->
			<form action="" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
				
						<th>分销商帐号</th>
						<th>分销商昵称</th>
						<th>分销商名称</th>
						<th>被推荐者账号</th>
						<th>被推荐者昵称</th>
						<th>被推荐者注册时间</th>
						<!-- <th>操作</th> -->
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
		queryData("userDistributionInfoManageCount.do", "userDistributionInfoManageAll.do?randquery="+rand);
	}
}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+ this.id +"/>"+
		        "</td><td>" + this.loginname + 
		        "</td><td>" + this.name + 
		        "</td><td>" + this.distribName + 
		        //"</td><td>" + this.sexName + 
		        //"</td><td>" + this.typeName + 
		        //"</td><td>" + this.statusName + 
				//"</td><td>" + this.login_date + 
				"</td><td><a href='order.do?os=&order.userId=" + this.inviterId + "'>" + this.inviterAct + 
				"</a></td><td>" + this.inviterName + 
				"</td><td>" + this.createDateStr + 
				"</td></tr>");
	}

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userDistributionInfoManageAll.do?randIni="+rand);
		
		$("#query_btn").click(query);
	});
</script>