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
		if(confirm("确认要通过审核吗？"))
		{
			var url = "checkUserInfo.do?userInfoPojo.id="+val;	
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
			queryData("userInfoAllCount2.do", "userInfoAllList2.do?randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">用户管理</a> &gt; <a href="#">账户信息</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="manufacturer.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">账号：</td>
						<td><label><input type="text" name="userInfoPojo.loginName"
								id="userInfoPojo.loginName"
								value="<s:property value="userInfoPojo.loginName"/>"></label></td>
						<td align="right">昵称：</td>
						<td><label><input type="text" name="userInfoPojo.userName"
								id="userInfoPojo.userName"
								value="<s:property value="userInfoPojo.userName"/>"></label></td>
						<td align="right">QQ：</td>
						<td><label><input type="text" name="userInfoPojo.QQ"
								id="userInfoPojo.QQ"
								value="<s:property value="userInfoPojo.QQ"/>"></label></td>
						
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
			<form action="userInfocheckAll.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
				        <th>帐号</th>
						<th>账户昵称</th>
						<th>LOGO</th>
						<th>邮箱</th>
						<th>QQ</th>
						<th>手机</th>
						<th>地址</th>
						<th>介绍</th>
						<th>状态</th>
						<s:if test="#session.role.roleId!=7"><th width="10%">操作</th></s:if>
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
		queryData("userInfoAllCount2.do", "userInfoAllList2.do?randquery="+rand);
	}
}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +" />"+ 
			    "</td><td>" + this.loginName+
		        "</td><td>" + this.userName+
		        "</td><td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userlogo/"+ this.image + "' height='50px' />" + 
		        "</td><td>" + this.email+ 
		        "</td><td>" + this.QQ+ 
		        "</td><td>" + this.phone+ 
		        "</td><td>" + this.address+ 
		        "</td><td>" + this.content+
		        "</td><td>" + this.statusName+
				"</td><s:if test="#session.role.roleId!=7"><td><a class='edit_btn' onclick=check('"+this.id+"')>审核</a>"+
				"<a class='edit_btn' href='goFindUserInfo.do?userInfoPojo.id="+this.id +"'>编辑</a></td></s:if>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userInfoAllList2.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
	});
	
</script>