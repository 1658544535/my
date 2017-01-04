<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
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

	//删除全部
	function deleteAll() {
		document.getElementById("idform").submit();
	}

	//删除单条
	function del(val) {
		if (confirm("你真的想删除该记录么？")) {
			var url = "delSecurityQuesion.do?accountSecurityPojo.userId=" + val;
			doOpreator(url, null);
		} else {
			return;
		}

	}

	function doOpreator(url, params) {
		MAOWU.ajax.get(url, params, goRefreshPage);
	}

	function goRefreshPage(result) {
		var rand = Math.random() * (100000 + 1);
		if (result == "1") {
			alert("删除成功");
			queryData("getSecurityQuesionRowCount.do",
					"securityQuesionAllList.do?randquery=" + rand);
		} else {
			alert("删除失败");
		}
	}
	
	//审核全部
	function checkAll() {
		document.getElementById("idform").submit();
	}
	
	//审核单条
	function check(val) {
		if (confirm("你确定要审核该记录么？")) {
			var url = "verifySecurityQuesion.do?accountSecurityPojo.userId=" + val;
			doCheckOpreator(url, null);
		} else {
			return;
		}

	}
	function doCheckOpreator(url, params) {
		MAOWU.ajax.get(url, params, goCheckRefreshPage);
	}

	function goCheckRefreshPage(result) {
		var rand = Math.random() * (100000 + 1);
		if (result == "1") {
			alert("审核成功");
			queryData("getSecurityQuesionRowCount.do",
					"securityQuesionAllList.do?randquery=" + rand);
		} else {
			alert("审核失败");
		}
	}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">采购商账户管理</a>&gt; <a href="#">账户安全</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<div id="search_show" style="">
			<form action=accountSecurityManage.do method="post" id="sysform">
				<table width="100%" border="0" class="Search_table">
					<tr align="center">
						<td align="right">用户昵称:</td>
						<td align="left"><input type="text"
							name="accountSecurityPojo.userName" id="accountSecurityPojo.userName"
							value="<s:property
								value="accountSecurityPojo.userName" />" /></td>
						<td align="right">安全问题关键字:</td>
						<td align="left"><input type="text"
							name="accountSecurityPojo.quesion" id="accountSecurityPojo.quesion"
							value="<s:property
								value="accountSecurityPojo.quesion" />" /></td>
					</tr>
					<tr align="right">
						<td align="right" colspan="4">
							<div class="floatRight">
								<input type="hidden" name="page.pageNo" value=0 id="pageNo">
								<input type="hidden" name="type" value="<s:property value="type"/>"
								id="type">
								<input type="submit" id="submitId" name="submitId" value="查询" class="submit_btn" />
								<!-- 
								<input id="query_btn" type="button" class="submit_btn" value="查询" />
								 -->
							</div>
						</td>
					</tr>
				</table>
				<div class="Clear"></div>
			</form>
		</div>

		<div class="h15"></div>
		<div>
		<a class="Add_btn" onclick="checkAll()">批量审核</a>
			<form action="verifyAllSecurityQuesion.do?type=${type}" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>用户昵称</th>
						<th>安全问题</th>
						<th>问题的答案</th>
						<th>状态</th>
						<th>创建时间</th>
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
	var ctx = "<s:property value="ctx" />";
	var pagecount = "${page.rowCount}";
	function query() {
		if (tt.validate()) {
			var rand = Math.random() * (100000 + 1);
			queryData("getSecurityQuesionCount.do",
					"securityQuesionAllList.do?randquery=" + rand);
		}
	}

	//分页展现页面函数 
	function installPage() {
		$("#body")
				.append(
						"<tr><td><input  name='tids' type='checkbox' value="+this.userId +"   /></td><td>"
								+ this.userName
								+ "</td><td>"
								+ this.quesion
								+ "</td><td>"
								+ this.answer
								+ "</td><td>"
								+ this.statusName
								+ "</td><td>"
								+ this.createDateStr
								+ "</td><s:if test="#session.role.roleId!=7"><td>"
								+ "<a class='del_btn' onclick=check('"
								+ this.userId + "')>审核</a>"
								+ "<a class='del_btn' onclick=del('" 
								+ this.userId + "')>删除</a>"
								+ "</td></s:if></tr>");
	}

	$(function() {
		//首次要初始化分页
		var rand = Math.random() * (100000 + 1);
		var Type = "${accountSecurityPojo.type}";
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"securityQuesionAllList.do?randIni=" + rand);
		$("#query_btn").click(query);

	});

</script>