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
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">任务管理</a> &gt; <a href="taskSettingList1.do?age=${age}">
<s:if test="age == 1">
0~6个月
</s:if>
<s:elseif test="age == 2">
6~12个月
</s:elseif>
<s:elseif test="age == 3">
1~3岁
</s:elseif>
<s:elseif test="age == 4">
3~6岁
</s:elseif>
<s:elseif test="age == 5">
6~12岁
</s:elseif>
<s:elseif test="age == 6">
12~16岁
</s:elseif>
</a>&gt; 
<a href="goTaskSetting.do?age=${age}&taskDateStr=${taskDateStr}">${taskDateStr}任务设置</a>
</div>
<!-- 查询开始 -->
<form action="goTaskSetting.do?age=${age}" method="post" id="sysform">
<table width="100%" border="0" class="Search_table"> 
</table>
<input type="hidden" name="page.pageNo" value=0 id="pageNo">		
<!-- 查询结束 -->
<div>
<a class="Add_btn" href="goAddTaskSetting.do?age=${age}&taskDateStr=${taskDateStr}">添加任务</a>
<form action="deletecouponAllById.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>任务标题</th>	
		<th>任务简介</th>	
		<th>年龄</th>	
		<th>能力</th>	
		<th>类型</th>	
		<th>分值</th>	
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
		queryData("taskSettingListCount2.do?age=${age}&taskDateStr=${taskDateStr}", "taskSettingListAll2.do?age=${age}&taskDateStr=${taskDateStr}&randquery="+rand);
	}
}
/**
*分页展现页面函数 
**/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td>"+ this.taskTitle + "</td>"+	
				"<td>"+ this.taskContent + "</td>"+
				"<td>"+ this.taskAgeMessage + "</td>"+
				"<td>"+ this.abilityMessage + "</td>"+
				"<td>"+ this.taskTypeAllMessage + "</td>"+
				"<td>"+ this.taskScore + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td>"+
				"<a class='del_btn' href='delTaskSetting.do?age=${age}&taskDateStr=${taskDateStr}&taskSettingPojo.id="+this.id+"' onclick='javascript:return p_del()'>删除</a></td></s:if>");
	}	
$(function() {
	/**
		  首次要初始化分页
		 **/
	 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"taskSettingListAll2.do?age=${age}&taskDateStr=${taskDateStr}&randIni="+rand);	
		$("#query_btn").click(query);		
	});	
		function p_del() { 
			var msg = "您真的确定要删除吗？"; 
			if (confirm(msg)==true){ 
			return true; 
			}else{ 
			return false; 
			} 
			} 
</script>