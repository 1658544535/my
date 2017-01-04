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
			queryData("getSysLoginAG.do?os=${os}", "sysLoginAllList.do?os=${os}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function pawd(val)
	{
		if(confirm("确认重置密码么？"))
		{
			var url = "updatePasswordlogin.do?sysLogin.id="+val;	
			doOpreator1(url,null);
		}else{
			return ;
		}
		
	}
	function doOpreator1(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage1);
	}
	 
	function goRefreshPage1(result){
		
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("重置成功!新密码：123456");
			queryData("getSysLoginCount.do?os=${os}", "sysLoginAllList.do?os=${os}&randdelete="+rand);
		}else{
			alert("重置失败");
		}
	}
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">系统管理</a> &gt; <c:if test="${os==2}"><a href="#">供应商管理</a></c:if> <c:if test="${os==3}"><a href="#">采购商管理</a></c:if><c:if test="${os==6}"><a href="#">批发商管理</a></c:if>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="sysLoginAG.do?os=${os}" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
				<tr>
				<td align="right">昵称：</td>
				<td><label><input type="text" name="sysLogin.name"
						id="ticketRulePojo.ticketName"
						value="<s:property value="sysLogin.name"/>"></label></td>
						<td align="right">账号：</td>
				<td><label><input type="text" name="sysLogin.loginname"
						id="ticketRulePojo.ticketName"
						value="<s:property value="sysLogin.loginname"/>"></label></td>
						<td align="right">QQ：</td>
				<td><label><input type="text" name="sysLogin.QQs"
						id="ticketRulePojo.QQs"
						value="<s:property value="sysLogin.QQs"/>"></label></td>
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
			<s:if test="#session.role.roleId!=2"><a class="delAll_btn" onclick="deleteAll()">批量审核</a>
			<a href="addSysLogin.do?os=${os}" class="Add_btn">新增用户</a>
			<a href="genExternalCode.do?os=${os}" class="Add_btn">生成邀请码</a></s:if></s:if>
			<form action="SysLoginDeleteId.do?os=${os}" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
				
						<th>帐号</th>
						<th>昵称</th>
						<th>QQ</th>
						<th>类型</th>
						<th>邀请码</th>
						<th>状态</th>
						<th>注册时间</th>
						<th>最后登录时间</th>
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
		queryData("getSysLoginCount.do?os=<s:property value='os'/>", "sysLoginAllList.do?os=<s:property value='os'/>&randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"      />"+
			    
		        "</td><td>" + this.loginname + 
		        "</td><td>" + this.name+ 
		        "</td><td>" + this.QQs+ 
		        "</td><td>" + this.typeName+
		        "</td><td>" + this.externalSignCode+
		        "</td><td>" + this.statusName
				+ "</td><td>" + this.agencydate
				+ "</td><td>" + this.login_date
				+ "</td>"+
				"<s:if test="#session.role.roleId!=7"><td>"
				<s:if test="#session.role.roleId!=2">							  			
				+ "<a class='edit_btn' href='goFindSysLogin.do?sysLogin.id="+this.id +"&os=<s:property value='os'/>"+"'>编辑</a>"
				</s:if>
//				+ "<a class='edit_btn' href='goFindUserInfo.do?userInfo.userId="+this.id +"&os=<s:property value='os'/>"+"'>用户信息</a>"
//				+ "<a class='edit_btn' href='goFindAgency.do?agency.userId="+this.id +"&os=<s:property value='os'/>"+"'>账户信息</a>"  
				+ <s:if test="#session.role.roleId!=2">
				"<a class='del_btn' href='updateStatus.do?sysLogin.id="+this.id +"&sysLogin.status="+this.status+"&os=<s:property value='os'/>"+"'>冻结/解冻</a>"
				+ "<a class='edit_btn' onclick=pawd('"+this.id+"')>密码重置</a>"
				+ "</td>"+
				</s:if>
				"</s:if></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"sysLoginAllList.do?os=<s:property value='os'/>&randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>