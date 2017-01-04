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
<title>Insert title here</title>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/common.js"></script>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/commonLoad.css" media="screen"
	rel="stylesheet">
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript">
	function del() {
		if (confirm("你真的想删除该记录么？")) {
			return true;
		}
		return false;
	}
</script>

</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">帮助/FAQ系统</a> &gt; <a href="">售后帮助</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>

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
							id="helpPojo.title" value="<s:property
								value="helpPojo.title" />"/>
						</td>
						<td width="25%"></td>
						<td width="25%"></td>
					</tr>
					<tr align="center">
						<td align="center" colspan="4"><div class="floatRight">
								<input type="hidden" name="page.pageNo" value=0 id="pageNo">
								<input type="hidden" name="type" value="<s:property value="type"/>" id="type">

								<input type="submit" id="submitId" name="submitId" value="查询"
									class="submit_btn" />

							</div></td>
					</tr>
				</table>
			</form>
			
		</div>
		<div class="h15"></div>
		<div >
			
			<table width="100%" border="0" class="Info_list_table">
				<tr>
					<th>标题</th>
					<th>内容</th>
					<th>所属类型</th>
					<th>添加人</th>
					<th>添加时间</th>
				</tr>
				<tbody id="body"></tbody>

			</table>
			<div class="page">
				<div class="floatleft">总共 <i id="rowcount">${page.rowCount }</i> 条/<i id="pagecount"></i>页 </div>
				<div style="float: right" id="Pagination" class="pagination"></div>
				<div class="Clear"></div>
			</div>
			</div>

		</div>
	
</body>

<script type="text/javascript">
	var ctx = "<s:property value="ctx" />";
	 var pagecount= "${page.rowCount }";

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
						"<tr><td>"
								+ helpTitlePage
								+ "</td><td>"
								+ helpContentPage
								+ "</td><td>"
								+ this.helpType
								+ "</td><td>"
								+ this.createBy
								+ "</td><td>"
								+ this.createDateStr
								+ "</td></tr>");
	}
	$(function() {
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"helpAllList.do?randIni=" + rand);
		$("#query_btn").click(query);

	});
</script>
</html>