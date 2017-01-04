<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/common.js"></script>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/commonLoad.css" media="screen"
	rel="stylesheet">
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<title>系统字典管理</title>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">系统管理</a> &gt; <a href="gomanagemead.do">系统字典管理</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<div id="fullbg"></div>
		<div id="sending" style="width: 100%; height: 40px">
			<p class="close"></p>
			<p align="center">
				<img alt="加载中..." src="image/loading.gif" height="30px" width="30px">
			</p>
		</div>
		<div id="search_show" style="">
			<form action="gomanagesysDict.do" method="post" id="sysform">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">名称</td>
						<td><input type="text" name="sysDictPojo.sysDictName"
							id="sysDictPojo.sysDictName"
							value="<s:property value="sysDictPojo.sysDictName"/>"></td>
						<td align="right">所属字典</td>
						<td><input type="text" name="sysDictPojo.sysDictType"
							id="sysDictPojo.sysDictType"
							value="<s:property value="sysDictPojo.sysDictType"/>"></td>
					</tr>
					<tr>
						<td align="right">层次</td>
						<td><select name="sysDictPojo.sysDictLevel"
							id="sysDictPojo.sysDictLevel">
								<option value="-1">全部</option>
								<option value="0"
									<s:if test="sysDictPojo.sysDictLevel == 0">selected="selected"</s:if>>0</option>
								<option value="1"
									<s:if test="sysDictPojo.sysDictLevel == 1">selected="selected"</s:if>>1</option>
						</select></td>
						<td align="right">是否启用</td>
						<td><input type="radio" name="sysDictPojo.sysDictState"
							value="2" checked="checked" />全部 <input type="radio"
							name="sysDictPojo.sysDictState" value="1"
							<c:if test="${sysDictPojo.sysDictState=='1' }">checked="checked"</c:if> />是
							<input type="radio" name="sysDictPojo.sysDictState" value="0"
							<c:if test="${sysDictPojo.sysDictState=='0' }">checked="checked"</c:if> />否
							
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4"><div class="floatRight">
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
			<a href="goSysDictAdd.do" class="Add_btn">新增主字典</a>

			<table width="100%" border="0" class="Info_list_table">
				<tr>
					<th>名称</th>
					<th>层次</th>
					<th>所属字典</th>
					<th>是否启用</th>
					<th>操作</th>
				</tr>
				<tbody id="body"></tbody>

			</table>
			<div class="page">
				
				<div class="floatleft">总共 <i id="rowcount">${page.rowCount }</i> 条/<i id="pagecount"></i>页 </div>
				<div style="float: right" id="Pagination" class="pagination"></div>
				<div class="Clear"></div>
			</div>
			<!-- <div class="center-div">
			<div class="center-main-div">
				<display:table name="sysDictList" class="simple" pagesize="10"
					requestURI="gomanagesysDict.do" id="row">
					<display:column property="sysDictName" title="名称" style="WIDTH:20%" />
					<display:column property="sysDictLevel" title="层次"
						style="WIDTH:20%" />
					<display:column title="所属字典" style="WIDTH:20%">
						<c:if test="${row.sysDictPojoBranch==null }">主菜单</c:if>
						<c:if test="${row.sysDictPojoBranch!='' }">${row.sysDictPojoBranch.sysDictName}</c:if>
					</display:column>
					<display:column title="是否启用" style="WIDTH:20%">
						<c:if test="${row.sysDictState=='1' }">已启用</c:if>
						<c:if test="${row.sysDictState=='0' }">未启用</c:if>
					</display:column>

					<display:column title="操作" style="WIDTH:20%">
						<a
							href="goSysDictBranchAdd.do?sysDictPojo.sysDictId=${row.sysDictId }">添加下属字典</a>
						<c:if test="${row.sysDictPojoBranch!=null }">
							<a
								href="goSysDictUpdate.do?sysDictPojo.sysDictId=${row.sysDictId }">修改</a>
						</c:if>
					</display:column>

				</display:table>
			</div>

		</div> -->
		</div>
	</div>
</body>

<script type="text/javascript">
	var ctx = "<s:property value="ctx" />";

	 var pagecount= "${page.rowCount }";
	function query() {
		if (tt.validate()) {
			var rand = Math.random() * (100000 + 1);
			queryData("getSysDictCount.do",
					"sysDictAllList.do?randquery=" + rand);
		}
	}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var businessBranchTest = this.sysDictPojoBranch;
		if (this.sysDictPojoBranch == null) {
			businessBranchTest = "主菜单";
		} else {
			businessBranchTest = this.sysDictPojoBranch.sysDictName;
		}
		var businessStateTest = "请修改";
		if (this.sysDictState == '1') {
			businessStateTest = "已启用";
		} else if (this.sysDictState  == '0') {
			businessStateTest = "未启用";
		}
		var businessBranchTest1 = "";
		if (this.sysDictPojoBranch != null) {
			businessBranchTest1 = "<a class='edit_btn' href='goSysDictUpdate.do?sysDictPojo.sysDictId="
					+ this.sysDictId + "'>编辑</a>";
		}
		$("#body")
				.append(
						"<tr><td>"
								+ this.sysDictName
								+ "</td><td>"
								+ this.sysDictLevel
								+ "</td><td>"
								+ businessBranchTest
								+ "</td><td>"
								+ businessStateTest
								+ "</td><td><a class='edit_btn' href='goSysDictBranchAdd.do?sysDictPojo.sysDictId="
								+ this.sysDictId + "'>添加下属字典</a>"
								+ businessBranchTest1 + "</td></tr>");
	}
	$(function() {
		/**
		  首次要初始化分页<a href="goHelpUpdate.do?helpPojo.helpid=${row.helpid}">修改</a>
			<a href="gomanageHelpDelete.do?helpPojo.helpid=${row.helpid}"
				onclick="return del();">删除</a>
		 **/
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"sysDictAllList.do?randIni=" + rand);

		$("#query_btn").click(query);

	});
</script>
</html>