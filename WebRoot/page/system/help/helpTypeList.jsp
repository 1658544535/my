<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<html>
<head>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">
	function del(val)
	{
		if(confirm("你真的想删除该记录么？"))
		{
			var url = "deleHelpType.do?helpTypePojo.id="+val;	
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
			queryData("getCountHelpType.do", "getHelpTypeByPid.do?randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	
	function check(val)
	{
		if(confirm("确认要通过审核吗？"))
		{
			var url = "checkHelpType.do?helpTypePojo.id="+val;	
			doOpreator2(url,null);
		}else{
			return ;
		}
	}
	
	function doOpreator2(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage2);
	}
	 
	function goRefreshPage2(result){
		
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核成功");
			queryData("getCountHelpType.do", "getHelpTypeByPid.do?randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	/*审核全部*/
	function updateAll(){
	if(confirm("确认审核所选记录吗？"))
		{
			manySend("allCheckHelpType.do");            
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
		
		/*改变checkBox的状态*/
	function allcb() {
		var checkbox = document.getElementById("selectcb");
		if (checkbox.checked == true) {
			var code_Values = document.getElementsByName("tids");
			for (i = 0; i < code_Values.length; i++) {
				if (code_Values[i].type == "checkbox") {
					code_Values[i].checked = true;
				}
			}

		} else {
			var code_Values = document.getElementsByName("tids");
			for (i = 0; i < code_Values.length; i++) {
				if (code_Values[i].type == "checkbox") {
					code_Values[i].checked = false
				}
			}
		}
	}
	</script>
</head>
<body>
	<div class="sub_wrap">
	    <div class="s_nav"><a href="#">帮助/FAQ系统</a> &gt; <a href="#">帮助分类管理</a></div>
	  	<div class="h15"></div>
		   <div>
		   <s:if test="#session.role.roleId!=7"><a class="Add_btn" onclick="updateAll()">批量审核</a>	
		   	<s:if test="pid == 0 || pid == null"><a href="gohelpTypeAdd.do?helpTypePojo.pid=0" class="Add_btn">新增分类</a></s:if></s:if>
			  	<form action="" id="idform" method="post">
			  	<table width="100%" border="0" class="Info_list_table">
				    <tr>
				    <th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>名称</th>
						<th>状态</th>
						<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>
				    </tr>
				    <tbody id="body">
				    </tbody>
			    </table>
			    </form>
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
			queryData("getCountHelpType.do", "getHelpTypeByPid.do?randquery="+rand);
		}
	}

	//分页展现页面函数 
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				"<td><a href='helpTypeManage.do?pid="+this.id+"'>"+ this.name + "</a></td>"+
				"<td>"+ this.statusName + "</td>"+
				"</td><s:if test="#session.role.roleId!=7"><td><a class='edit_btn' href='helpTypeManage.do?pid="+this.id+"'>查看子类型</a>"+
				"<a class='edit_btn' href='gohelpTypeAdd.do?helpTypePojo.pid="+this.id+"'>添加子类型</a>" +
				"<a  class='edit_btn' onclick=check('"+this.id+"')>审核</a><a  class='del_btn' onclick=del('"+this.id+"')>删除</a>"+
				"<a class='edit_btn' href='goFindHelpType.do?helpTypePojo.id="+this.id +"'>编辑</a></td></s:if></tr>");
	}
	
	$(function() {
		//首次要初始化分页
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
					"getHelpTypeByPid.do?pid=${pid}&randIni="+rand);
		$("#query_btn").click(query);
	});
	
</script>