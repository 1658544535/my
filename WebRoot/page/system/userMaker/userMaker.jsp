<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript">
function resetPassword(val)
	{
		if(confirm("你真的想重置该用户的密码么？"))
		{
			var url = "resetPassword.do?sysLoginPojo.id="+val;	
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
			alert("重置成功！新的密码是：123456");
			queryData("userMakerListCount.do", "userMakerListAll.do?randquery="+rand);
		}else{
			alert("重置失败");
		}
	}
</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">创客管理</a> &gt; <a href="userMaker.do">创客管理</a></div>
    <div class="Search_control">
		<p>按条件查找</p>	
		<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="userMaker.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
					    <td align="right">创客昵称：</td>
					    <td><input type="text" name="sysLoginPojo.name" value=""></td>	
					    <td align="right">创客账号：</td>
					    <td><input type="text" name="sysLoginPojo.loginname" value=""></td>
					    <td align="right">审核状态：</td>
						<td><select name="sysLoginPojo.status" id="sysLoginPojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">审核成功</option>
				    		</select></td>
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
<a class="Add_btn" href="goAddUserMaker.do" >新增</a>
<form action="" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>创客昵称</th>	
		<th>创客账号</th>
		<th>头像</th>
		<th>审核状态</th>
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
		queryData("userMakerListCount.do", "userMakerListAll.do?randquery="+rand);
	}
}
/**
*分页展现页面函数 
**/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td>"+ this.name + "</td>"+	
				"<td>"+ this.loginname + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userlogo/"+ this.image + "' height='100px' /></td>"+
				"<td>"+ this.statusName + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td>"+
				"<a class='edit_btn' href='goEditMaker.do?sysLoginPojo.id="+this.id +"'>编辑头像 </a>"+
				"<a class='edit_btn' onclick=resetPassword('"+this.id+"')>重置密码 </a>"+
				"<a class='edit_btn' href='checkUserMaker.do?sysLoginPojo.id="+this.id +"'>审核成功</a>"+
				"<a class='edit_btn' href='uncheckUserMaker.do?sysLoginPojo.id="+this.id +"'>取消审核 </a>"+
				"</td></s:if>");
	}	
$(function() {
	/**
		  首次要初始化分页
		 **/
	 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userMakerListAll.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>