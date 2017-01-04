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


	function del(val)
	{
		//alert(val);
		if(confirm("你真的想删除该记录么？"))
		{
			//alert(val);
			
			var url = "deleSyntheticalDict.do?syntheticalDict.id="+val;	
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
			alert("删除成功");
			queryData("getSyntheticalDictCount.do", "syntheticalDictAllList.do?randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	
	/*审核全部*/
	function updateAll(){
	if(confirm("确认审核所选记录吗？"))
		{
			manySend("checkAllsyntheticalDict.do");            
            return true;
		}else{
			return ;
		}
	}
	function manySend(href){
		var form = document.getElementById("idform");
		form.action = href;//传想要跳转的路径
		form.submit();
		} 
		
		/*删除全部*/
	function deleteAll() {
	if (confirm("你真的想删除所选记录么？")) {
		manySend("SyntheticalDictDeleteId.do");
		//document.getElementById("idform").submit();
		} else {
			return;
		}
	}
	
	function update(val)
	{
		//alert(val);
		if(confirm("确认审核吗？"))
		{
			//alert(val);
			
			var url = "update.do?syntheticalDict.id="+val;	
			doupdateOpreator(url,null);
		}else{
			return ;
		}
		
	}
	function doupdateOpreator(url,params){
		MAOWU.ajax.get(url, params, goupdateRefreshPage);
	}
	 
	function goupdateRefreshPage(result){
		
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核成功");
			queryData("getSyntheticalDictCount.do", "syntheticalDictAllList.do?randquery=" + rand);
		}else{
			alert("审核失败");
		}
	}
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">系统管理</a> &gt; <a href="#">综合字典管理</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="syntheticalDict.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">字典名称：</td>
						<td><label><input type="text" name="syntheticalDict.name"
								value=""></label></td>
						<td align="right">字典英文名称：</td>
						<td><label><input type="text" name="syntheticalDict.nameEn"
								value=""></label></td>
						<td align="right">字典分类：</td>
						<td><label><input type="text" name="syntheticalDict.type"
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
		<s:if test="#session.role.roleId!=7"><a class="Add_btn" onclick="updateAll()">批量审核</a>
			<a class="delAll_btn" onclick="deleteAll()">批量删除</a><a href="addSyntheticalDict.do" class="Add_btn">新增字典</a></s:if>
			<form action="" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>字典分类</th>
						<th>字典取值</th>
						<th>字典名称</th>
						<th>字典英文名称</th>
						<th>排序</th>
						<th>状态</th>
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



		<!---->


	</div>
</body>
</html>





<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("getSyntheticalDictCount.do", "syntheticalDictAllList.do?randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td><td>" 
		        + this.type + 
		        "</td><td>" + this.value+
		        "</td><td>" + this.name+ 
		        "</td><td>" + this.nameEn+
		        "</td><td>" + this.sorting+
		        "</td><td>" + this.statusName
				+ "</td><s:if test="#session.role.roleId!=7"><td><a class='edit_btn' onclick=update('"+this.id+"')>审核</a><a  class='del_btn' onclick=del('"+this.id+"')>删除</a>"
				+ "<a class='edit_btn' href='goFindSyntheticalDict.do?syntheticalDict.id="+this.id +"'>编辑</a></td></s:if></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"syntheticalDictAllList.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>