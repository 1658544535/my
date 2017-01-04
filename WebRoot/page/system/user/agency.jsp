<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

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
		queryData("agencyCount.do", "agencyAllList.do?randdelete="+rand);
	}else{
		alert("审核失败");
	}
}
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">批发商账户管理</a> &gt; <a href="#">账户信息</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="agency.do" method="post" id="sysform">
		<div id="search_show" style="">
		<table width="100%" border="0" class="Search_table">
		<tr>
			<td align="right">公司名称：</td>
			<td><label><input type="text" name="agency.company"
					value="${agency.company}"></label></td>
			<td align="right">公司地址：</td>
			<td><label><input type="text" name="agency.addressCompany"
					value="${agency.addressCompany}"></label></td>
			<td align="right">帐号：</td>
			<td><label><input type="text" name="agency.loginname"
				    value="${agency.loginname}"></label></td>
		</tr>
		<tr>
		<td align="right">昵称：</td>
		<td><label><input type="text" name="agency.name"
			    value="${agency.name}"></label></td>
		<td align="right">手机号码：</td>
		<td><label><input type="text" name="agency.phone"
				value="${agency.phone}"></label></td>
		<td align="right">QQ：</td>
			<td><label><input type="text" name="agency.QQ"
					value="${agency.QQ}"></label></td>
			
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
		<s:if test="#session.role.roleId!=7">
		<s:if test="#session.role.roleId!=2"><a class="Add_btn" onclick="checkAll()">批量审核</a></s:if></s:if>
			<form action="agencyChecks.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
							<th>帐号</th>	
							<th>昵称</th>	
							<th>代理品牌</th>
							<th>公司名称</th>
							<th>公司地址</th>
							<th>联系人</th>
							<th>联系号码</th>
							<th>QQ</th>
							<th>创建时间</th>
							<s:if test="#session.role.roleId!=7"><th>状态</th></s:if>
							<th width="11%">操作</th>
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
		queryData("agencyCount.do", "agencyAllList.do?randquery="+rand);
	}
}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.agencyId +" />"+
						"</td><td>" + this.loginname +
						"</td><td>" + this.name +
						 "</td><td>" + this.shopName+
				        "</td><td>" + this.company + 
				        "</td><td>" + this.addressCompany+ 
				        "</td><td>" + this.contact+
				        "</td><td>" + this.phone+
				        "</td><td>" + this.QQ+
				        "</td><td>" + this.createDateString+
				        "</td><td>" + this.statusName
						+ "</td><s:if test="#session.role.roleId!=7"><td>"+
						<s:if test="#session.role.roleId!=2">
						"<a  class='edit_btn' onclick=check('"+this.agencyId+"')>审核</a>"+
						</s:if>
						"<a class='edit_btn' href='goFindAgency.do?agency.agencyId="+this.agencyId +"'>编辑</a>"+
						<s:if test="#session.role.roleId!=2">
						 "<a class='del_btn' href='delAgency.do?agency.agencyId="+this.agencyId +"'onclick='javascript:return window.confirm(\"确定删除？\");'>删除</a>"+
						</s:if>
						"<a class='edit_btn' href='agencyPushOrder.do?agency.agencyId="+this.agencyId +"'>查询推送订单</a>"+
						"<a class='edit_btn' href='addAgencyCollect.do?agency.agencyId="+this.agencyId+"&agency.manufacturerId="+this.manufacturerId+"'>新增代理商品</a>"+
						"<a class='edit_btn' href='goFindAgencyCollect.do?agencyCollectPojo.agency_id="+this.agencyId +"'>编辑代理商品</a>"+
						"</td></s:if></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"agencyAllList.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>