<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
function deleteAll(){
		$("#idform").attr("action","delNavigationAll.do").submit();
	}
function checkAll(){
		$("#idform").attr("action","checkNavigationAll.do").submit();
	}
function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkNavigation.do?navigationPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}	
function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckNavigation.do?navigationPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
function del(val){
		if(confirm("确认要删除此条吗？")){
			var url = "delNavigation.do?navigationPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}	
</script>	
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">玩具工厂管理</a> &gt; <a href="userPartsLog.do">用户部件记录</a></div>
     <div class="Search_control">
	 <p>按条件查找</p>
	 <a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
	 </div>
		<!-- 查询开始 -->
		<form action="userPartsLog.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">  
					<tr> 
						<td align="right">用户账号：</td>
						<td><input type="text" name="userPartsLogPojo.loginname" value=""></td>
					    <td align="right">玩具名称：</td>
						<td><input type="text" name="userPartsLogPojo.toyName" value=""></td>
					</tr>
					<tr>
						<td align="right">部件名称：</td>
						<td><input type="text" name="userPartsLogPojo.partsName" value=""></td>
						<td align="right">部件状态：</td>
						<td><select name="userPartsLogPojo.status" id="userPartsLogPojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未生成</option>
							<option value="1">生成中</option>
							<option value="2">已生成</option>
							<option value="3">已合成</option>
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
<!-- <s:if test="#session.role.roleId!=7"><a class="delAll_btn" onclick="deleteAll()">批量删除</a>
<a class="Add_btn" onclick="checkAll()">批量审核</a>
<a class="Add_btn" href="goAddNavigation.do">新增导航</a></s:if> -->
<form action="deletecouponAllById.do" id="idform"  method="post" >
<table width="100%" border="0" class="Info_list_table">
<tr>    
        <!-- <th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th> -->
		<th>用户账号</th>
		<th>玩具名称</th>
		<th>部件名称</th>
		<th>生成所需时间（分钟）</th>
		<th>状态</th>
		<th>生成开始时间</th>
		<th>生成结束时间</th>
		<th>抽取时间</th>
</tr>
<tbody id="body"></tbody>      
</table>
</form>
<div class="page">
		<div class="floatleft">
		总共 <i id="rowcount">${page.rowCount}</i> 条/<i id="pagecount"></i>页
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
		queryData("userPartsLogCount.do", "userPartsLogAll.do?randquery="+rand);
	}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr>"+
				//"<td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				"<td>"+ this.loginname + "</td>"+
				"<td>"+ this.toyName + "</td>"+				
				"<td>"+ this.partsName + "</td>"+
				"<td>"+ this.createTime + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ this.createBeginDateStr + "</td>"+
				"<td>"+ this.createEndDateStr + "</td>"+
				"<td>"+ this.createDateStr + "</td>"+
				"</td>");
				
	}	
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,"userPartsLogAll.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>