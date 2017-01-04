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

	function checkAll() {
		document.getElementById("idform").submit();
	}

	function check(val) {
		if (confirm("你确定要审核该记录么？")) {
			var url = "verifyPushNotice.do?pagePushPojo.id=" + val;
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
		var noticeType = "${pagePushPojo.type}";
		if (result == "1") {
			alert("审核成功");
			queryData("getNoitceCount.do", "noticeAllList.do?randquery=" + rand
					+ "&pagePushPojo.type=" + noticeType);
		} else {
			alert("审核失败");
		}
	}
</script>
</head>
<body>
	<div class="sub_wrap">
		<!--<input type="hidden" name="pagePushPojo.type" value="${pagePushPojo.type}" class="floatLeft" id="merNamffe">-->
		<div class="s_nav">
			<a href="#">内页推送管理</a> &gt; <a href="#"></a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="queryInPageNotice.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">信息标题：</td>
						<td><label><input type="text"
								name="pagePushPojo.title" id="ticketRulePojo.ticketName"
								value="<s:property value="pagePushPojo.title"/>"> </label></td>
					</tr>
				</table>
				<!--  <input type="hidden" name="pagePushPojo.type" value="${pagePushPojo.type}">-->
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<input type="hidden" name="type" value="<s:property value="type"/>"
					id="type">
				<div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>

		<div class="h15"></div>
		<div>
			<!-- <input type="hidden" name="pagePushPojo.type" value="${pagePushPojo.type}" class="floatLeft" id="merNamffe">-->
			<s:if test="#session.role.roleId!=7"><a class="delAll_btn" onclick="checkAll()">批量审核</a></s:if>
			<form action="verifyAllPushNotice.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>信息标题</th>
						<th>推送图片</th>
						<th>信息类型</th>
						<th>信息状态</th>
						<th>发布时间</th>
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
			var noticeType = "${pagePushPojo.type}";
			queryData("getNoitceCount.do", "noticeAllList.do?randquery=" + rand
					+ "&pagePushPojo.type=" + noticeType);
		}
	}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td><td>"
						+ this.title + "</td><td>" 
						+ "<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/"+ this.images + "' height='50px' />"
						+ "</td><td>"
						+ this.typeName + "</td><td>"
						+ this.statusName  + "</td><td>" 
						+ this.createDateStr
						+ "</td><s:if test="#session.role.roleId!=7"><td><a class='del_btn' onclick=check('"
						+ this.id + "')>审核</a>" + "</td></s:if></tr>");
	}

	$(function() {
		/**
		  首次要初始化分页
		 **/
		var rand = Math.random() * (100000 + 1);
		var noticeType = "${pagePushPojo.type}";
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"noticeAllList.do?randIni=" + rand + "&pagePushPojo.type="
						+ noticeType);
		$("#query_btn").click(query);

	});

	
</script>