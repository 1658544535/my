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
			<a href="#">帮助/FAQ系统</a> &gt; <a href="verification.do">验证码查询</a>
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
			<form action="verification.do" method="post" id="sysform">
				<table width="100%" border="0" class="Search_table">
					<tr align="center">
						<td align="right">用户</td>
						<td align="left"><input type="text" name="verificationPojo.loginname"
							id="verificationPojo.loginname"
							value="<s:property
								value="verificationPojo.loginname" />" />
						</td>
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
			<form action="checkAllHelpInfo.do" id="idform" method="post">
			<table width="100%" border="0" class="Info_list_table">
				<tr>
					<th>用户</th>
					<th>验证码</th>
					<th>添加时间</th>
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
			queryData("getVerificationCount.do", "verificationAllList.do?randquery=" + rand);
		}
	}

	//分页展现页面函数 
	function installPage() {
		
		$("#body")
				.append(
						"<tr><td>"
								+ this.loginname
								+ "</td><td>"
								+ this.captcha
								+ "</td><td>"
								+ this.creatDateString
								+ "</td></tr>");
	}
	$(function() {
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"verificationAllList.do?randIni=" + rand);

		$("#query_btn").click(query);
	});
</script>
</html>