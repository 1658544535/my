<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The Help Page</title>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/common.js"></script>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/commonLoad.css" media="screen" rel="stylesheet">
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript">
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
	//删除
	function del() {
		if (confirm("你真的想删除该记录么？")) {
			return true;
		}
		return false;
	}
	//批量审核
	function checkAll() {
		document.getElementById("idform").submit();
	}
</script>

</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">帮助/FAQ系统</a> &gt; <a href="helpManage.do">帮助信息管理</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 实现公共的加载中方法 -->
		<div id="fullbg"></div>
		<div id="sending" style="width: 100%; height: 40px">
			<p class="close"></p>
			<p align="center">
				<img alt="加载中..." src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/image/loading.gif" height="30px" width="30px">
			</p>
		</div>
		<div id="search_show" style="">
			<form action="helpManage.do" method="post" id="sysform">
				<table width="100%" border="0" class="Search_table">
					<tr align="center">
						<td align="right">标题</td>
						<td align="left"><input type="text" name="helpPojo.title"
							id="helpPojo.title"
							value="<s:property
								value="helpPojo.title" />" />
						</td>
						<!--  							
						<td align="right">所属类型</td>
						<td>
						 <select name="helpPojo.type" id="">
						 		<option value="">全部</option>
								<s:iterator value="helpTypeList">
									<option value="<s:property value="value"/>"
									<s:if test="value == helpPojo.type">selected="selected"</s:if>><s:property
											value="name" />
									</option>
								</s:iterator>
						</select>
						</td>
						-->	
					</tr>
					<tr align="right">
						<td align="right" colspan="4"><div class="floatRight">
								<input type="hidden" name="page.pageNo" value=0 id="pageNo">
								<input type="submit" id="submitId" name="submitId" value="查询"
									class="submit_btn" />
							</div></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="h15"></div>
		<div>
			<s:if test="#session.role.roleId!=7"><a class="Add_btn" onclick="checkAll()">批量审核</a>
			<!--<a href="goHelpAdd.do" class="Add_btn">新增帮助信息</a>--></s:if>
			<form action="checkAllHelpInfo.do" id="idform" method="post">
			<table width="100%" border="0" class="Info_list_table">
				<tr>
					<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()"></th>
					<th>标题</th>
					<th>所属类型</th>
					<th>状态</th>
					<th>添加时间</th>
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

<script type="text/javascript">
	var ctx = "<s:property value="ctx" />";
	var pagecount = "${page.rowCount }";

	function query() {
		if (tt.validate()) {
			var rand = Math.random() * (100000 + 1);
			queryData("getHelpCount.do", "helpAllList.do?randquery=" + rand);
		}
	}

	//分页展现页面函数 
	function installPage() {
		var helpTitlePage = this.title;
		if (this.title.length >= 10) {
			helpTitlePage = this.title.substring(0, 10) + "...";
		}
		var helpContentPage = this.content;
		if (this.content.length >= 20) {
			helpContentPage = this.content.substring(0, 20) + "...";
		}
		$("#body")
				.append(
						"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td><td>"
								+ helpTitlePage
								+ "</td><td>"
								+ this.helpTypeName
								+ "</td><td>"
								+ this.statusName
								+ "</td><td>"
								+ this.createDateStr
								+ "</td><s:if test="#session.role.roleId!=7"><td>"
								+ "<!--<a class='edit_btn' href='goCheckHelpInfo.do?helpPojo.id="
								+ this.id + "'>审核</a>-->"
								+ "<!--<a class='edit_btn' onclick='return del();' href='helpManageDelete.do?helpPojo.id="
								+ this.id
								+ "'>删除</a>-->"
								+ "<a class='edit_btn' href='goHelpUpdate.do?helpPojo.id="
								+ this.id + "'>编辑</a></td></s:if></tr>");
	}
	$(function() {
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"helpAllList.do?randIni=" + rand);

		$("#query_btn").click(query);
	});
</script>
</html>