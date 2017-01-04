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
		$("#idform").attr("action","delMessagesCenterAll.do").submit();
	}
function checkAll(){
		$("#idform").attr("action","checkMessagesCenterAll.do").submit();
	}
function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkMessagesCenter.do?messagesCenterPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}	
function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckMessagesCenter.do?messagesCenterPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
function del(val){
		if(confirm("确认要删除此条吗？")){
			var url = "delMessagesCenter.do?messagesCenterPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}	
</script>	
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">商家中心管理</a> &gt; <a href="messagesCenter.do">商家消息管理</a></div>
     <div class="Search_control">
	 <p>按条件查找</p>
	 <a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
	 </div>
		<!-- 查询开始 -->
		<form action="messagesCenter.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">  
					<tr>
					    <td align="right">已读标志：</td>
						<td><select name="messagesCenterPojo.isRead" id="messagesCenterPojo.isRead"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未读</option>
							<option value="1">已读</option>
				    		</select></td>
				    	<td align="right">删除标志：</td>
						<td><select name="messagesCenterPojo.isDelete" id="messagesCenterPojo.isDelete"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未删除</option>
							<option value="1">已删除</option>
				    		</select></td>
				    </tr>
				    <tr>
					    <td align="right">用户账号：</td>
						<td><input type="text" name="messagesCenterPojo.loginName" value=""></td>
						<td align="right">审核状态：</td>
						<td><select name="messagesCenterPojo.status" id="messagesCenterPojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">已审核</option>
				    		</select></td>
				    </tr>
				    <tr>
						<td align="right">开始日期：</td>
						<td><input name="messagesCenterPojo.beginTimeStr" id="beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })"/></td>
						<td align="right">结束日期：</td>
						<td><input name="messagesCenterPojo.endTimeStr" id="endday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })"/></td>
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
<s:if test="#session.role.roleId!=7"><a class="delAll_btn" onclick="deleteAll()">批量删除</a>
<a class="Add_btn" onclick="checkAll()">批量审核</a>
<a class="Add_btn" href="goAddMessagesCenter.do">新增消息</a></s:if>
<form action="deletecouponAllById.do" id="idform"  method="post" >
<table width="100%" border="0" class="Info_list_table">
<tr>    
        <th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>用户账号</th>
		<th>消息内容</th>
		<th>创建时间</th>
		<th>已读标志</th>
		<th>删除标志</th>
		<th>审核状态</th>
		<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>
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
		queryData("messagesCenterListCount.do", "messagesCenterListAll.do?randquery="+rand);
	}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				"<td>"+ this.loginName + "</td>"+
				"<td>"+ this.message + "</td>"+				
				"<td>"+ this.createDateStr + "</td>"+
				"<td>"+ this.isReadName + "</td>"+
				"<td>"+ this.isDeleteName + "</td>"+				
				"<td>"+ this.statusName + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td><a class='edit_btn' href='goUpdateMessagesCenter.do?messagesCenterPojo.id="+this.id +"'>编辑</a>"+
				"<a class='del_btn'  onclick=del("+this.id+")>删除</a>"+
				"<a class='edit_btn' onclick=check("+this.id+")>通过审核</a>"+
				"<a class='edit_btn' onclick=uncheck("+this.id+")>取消审核</a></td></s:if>");
				
	}	
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"messagesCenterListAll.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>