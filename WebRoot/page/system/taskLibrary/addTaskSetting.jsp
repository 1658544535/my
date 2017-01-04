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
<script type="text/javascript">

	
</script>
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
function addAll(){
		$("#idform").attr("action","addTaskSettingAll.do?age=${age}&taskDateStr=${taskDateStr}").submit();
	}
</script>
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
&gt;<a href="goAddTaskSetting.do?age=${age}&taskDateStr=${taskDateStr}">${taskDateStr}任务添加</a>
</div>
<div class="Search_control" id="tip">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
<!-- 查询开始 -->
		<form action="goTaskSetting.do?age=${age}&taskDateStr=${taskDateStr}" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">  
					<tr> 
					    <td align="right">任务标题：</td>
						<td><input type="text" name="taskPojo.taskTitle" value=""></td>
						<td align="right">分值：</td>
						<td><input type="text" name="taskPojo.taskScore" value=""></td>
					</tr>
				    <tr>
						<td align="right">任务类型：</td>
						<td><select id="taskType" name="taskPojo.taskType" class="floatLeft"></select></td>
				    	<td><select id="taskTypeLink" name="taskPojo.taskTypeLink" class="floatLeft" ></select></td>
				    </tr>
				    <tr>
						<td align="right">能力：</td>
						<td><select id="ability" name="taskPojo.ability" class="floatLeft" ></select></td>
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
<s:if test="#session.role.roleId!=7"><a class="Add_btn" onclick="addAll()">选择勾选项</a>
</s:if>
  <form action="deletecouponAllById.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
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
		var rand=Math.random() * ( 100000 + 1);
		queryData("taskLibraryListCount.do?age=${age}&taskDateStr=${taskDateStr}" , "taskLibraryListAll.do?age=${age}&taskDateStr=${taskDateStr}&randquery="+rand);
	}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.taskTitle + "</td>"+
				"<td>"+ this.taskContent + "</td>"+
				"<td>"+ this.taskAgeMessage + "</td>"+
				"<td>"+ this.abilityMessage + "</td>"+
				"<td>"+ this.taskTypeMessage + " "+this.taskTypeLinkMessage + "</td>"+
				"<td>"+ this.taskScore + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td><a class='edit_btn' href='addTaskSetting.do?age=${age}&taskDateStr=${taskDateStr}&taskPojo.id="+this.id+"'>选择</a></td>"+
				"</s:if>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"taskLibraryListAll.do?age=${age}&taskDateStr=${taskDateStr}&randIni="+rand);
		$("#query_btn").click(query);	
		
		
	    select3();
		select1();
		$('#taskType').bind("change", select2);			
	});	
	
	function select1() {
		$("#taskType").append("<option value=''>----请选择 ----</option>");
		$.ajax(
		{
			type: "post",
			url: "getSysDictListByType.do?sysDict.type=task_type",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					$("#taskType").append("<option value=" + o_msg[i].value + ">" + o_msg[i].name + "</option>");
				}
				if("${taskPojo.taskType}"!=null && "${taskPojo.taskType}"!=""){
					select2();
				}
			}
		})
	};
	function select2() {
            $("#taskTypeLink").html("");
            $("#taskTypeLink").append("<option value=''>---请选择---</option>");
            $.ajax(
            {
                type: "post",
                url: "getTaskTypeLink.do?taskType="+$('#taskType').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
    				console.log(msg);
    				if($('#taskType').val() == 1){
                    for (var i = 0; i < o_msg.length; i++) {
                        $("#taskTypeLink").append("<option value=" + o_msg[i].taskTypeLinkValue + ">" + o_msg[i].taskTypeLinkName + "</option>");
                    }
                }else{
                	
                	}
                }
            })
        };
    function select3() {
		$("#ability").append("<option value=''>----请选择 ----</option>");
		$.ajax(
		{
			type: "post",
			url: "getSkillTypes.do?ageId=${session.ageId}",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					$("#ability").append("<option value=" + o_msg[i].skillValue + ">" + o_msg[i].skillName + "</option>");
				}
			}
		})
	};
</script>