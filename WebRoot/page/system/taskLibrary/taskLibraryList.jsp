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
<script>
$(function(){
	$("#end").click(function() {
		query();

	});
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
</head>
<body>

<div class="sub_wrap">
<div class="s_nav"><a href="#">任务管理</a> &gt; <a href="taskLibraryList.do">任务库</a></div>
	<div class="Search_control">
	 <p>按条件查找</p>
	 <a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
	 </div>
<!-- 查询开始 -->
		<form action="taskLibraryList.do" method="post" id="sysform">
			<div id="search_show" style="">
			<table width="100%" border="0" class="Search_table">  
				<tr> 
				    <td align="right">任务标题：</td>
					<td><input type="text" name="taskPojo.taskTitle" value=""></td>
					<td align="right">分值：</td>
					<td><input type="text" name="taskPojo.taskScore" value=""></td>
					<td align="right">任务类型：</td>
					<td><select id="taskType" name="taskPojo.taskType"  class="floatLeft"></select></td>&nbsp;&nbsp;&nbsp;
				    <td><select id="taskTypeLink" name="taskPojo.taskTypeLink"  class="floatLeft"></select></td>
				</tr>
				<tr>
					<td align="right">年龄：</td>
					<td><select id="taskAge" name="taskPojo.taskAge"  class="floatLeft"></select></td>
					<td align="right">能力：</td>
					<td><select id="ability" name="taskPojo.ability"  class="floatLeft"></select></td>
				</tr>					    
			</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
			<div class="floatRight">
					   <input id="query_btn" type="button" class="submit_btn" value="查询" />
			</div>
			<div class="Clear"></div>
			</div>
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
  <div class="h15"></div>
  <div>

<a class="Add_btn" href="goAddTaskLibraryList.do" id="btn4">添加任务</a>

  <form action="deletecouponAllById.do" id="idform"  method="post" >
  
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>任务标题 </th>
		<th>任务简介 </th>
		<th>年龄</th>
		<th>能力</th>
		<th>任务类型</th>
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
	//var id = $("input[name='taskPojo.id']").val();
	//var r = /^[1-9][0-9]*$/;
	//if(id != "" && !r.test(id)){
		//alert("任务ID必须为正整数！");
//	}else{
		var rand=Math.random() * ( 100000 + 1);
		queryData("taskLibraryListCount.do?" , "taskLibraryListAll.do?randquery="+rand);
	//}
		
	
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
				"<td>"+ this.taskTypeMessage + " "+this.taskTypeLinkMessage+"</td>"+ 
				"<td>"+ this.taskScore + "</td>"+
				"<td><a class='edit_btn' href='goUpdateTaskLibrary.do?taskPojo.id="+this.id+"'>编辑</a><a class='edit_btn' href='delTaskLibrary.do?taskPojo.id="+this.id + "' onclick='javascript:return p_del()'>删除</a></td>"+
				"</tr>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"taskLibraryListAll.do?randIni="+rand);
		$("#query_btn").click(query);	
		
		select3();
		$('#taskAge').bind("change", select4);
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
                  }
                }
            })
        };
    function select3() {
		$("#taskAge").append("<option value=''>----请选择 ----</option>");
		$.ajax(
		{
			type: "post",
			url: "getSysDictListByType.do?sysDict.type=user_age",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					$("#taskAge").append("<option value=" + o_msg[i].value + ">" + o_msg[i].name + "</option>");
				}
				if("${taskPojo.taskAge}"!=null && "${taskPojo.taskAge}"!=""){
					select4();
				}
			}
		})
	};
	function select4() {
		$("#ability").html("");
		$("#ability").append("<option value=''>----请选择 ----</option>");
		$.ajax(
		{
			type: "post",
			url: "getSkillTypes.do?ageId="+$('#taskAge').val(),
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