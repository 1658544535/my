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

	function deleteAll() {
		document.getElementById("idform").submit();
	}
	
	function jies() {
		document.getElementById("jies").submit();
	}

	function del(val) {
		//alert(val);
		if (confirm("确定要结算吗？")) {
			//alert(val);

			var url = "deleUserOrderStatistics.do?userOrderStatistics.id="
					+ val;
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
			alert("结算成功");
			queryData("getUserOrderStatisticsCount.do",
					"userOrderStatisticsAllList.do?randdelete=" + rand);
		} else {
			alert("结算失败");
		}
	}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">财务管理</a> &gt; <a href="#">订单统计结算</a>
		</div>
		<div class="Search_control" >
			<p>用户订单统计</p>
		</div>
		<div style="">
			<form action="jiesUserOrderStatistics.do" method="post" id="jies">

				<div id="sss" style="">
					<table width="100%" border="0" class="Search_table">
						<tr>
							<td align="right">用户昵称：</td>
							<td><label><input type="text"
									name="userOrderStatistics.remarks" value=""></label></td>
							<td><label><input id="sss" type="button"
									class="submit_btn" value="上月订单统计" onclick="jies()"/></label></td>
						</tr>

					</table>
				</div>
			</form>
		</div>
		<div class="Clear"></div>
		
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="userOrderStatistics.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">用户昵称：</td>
						<td><label><input type="text"
								name="userOrderStatistics.remarks" value=""></label></td>
						<td align="right">年份：</td>
						<td><label><input type="text"
								name="userOrderStatistics.year" value=""></label></td>
						<td align="right">月份：</td>
						<td><label><input type="text"
								name="userOrderStatistics.month" value=""></label></td>
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
			<a class="Add_btn" onclick="deleteAll()">批量结算</a>
			<form action="UserOrderStatisticsDeleteId.do" id="idform"
				method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>用户昵称</th>
						<th>年份</th>
						<th>月份</th>
						<th>结算金额</th>
						<th>是否已结算</th>
						<th width="10%">操作</th>
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



		<!---->


	</div>
</body>
</html>





<script type="text/javascript">
	var ctx = "<s:property value="ctx" />";
	var pagecount = "${page.rowCount}";
	function query() {
		if (tt.validate()) {
			var rand = Math.random() * (100000 + 1);
			queryData("getUserOrderStatisticsCount.do",
					"userOrderStatisticsAllList.do?randquery=" + rand);
		}
	}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td><td>"
						+ this.userName + "</td><td>" + this.year + "</td><td>"
						+ this.month + "</td><td>" + this.money + "</td><td>"
						+ this.isSettleAccountsName
						+ "</td><td><a  class='edit_btn' onclick=del('"
						+ this.id + "')>结算</a>" + "</td></tr>");
	}

	$(function() {
		/**
		  首次要初始化分页
		 **/
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userOrderStatisticsAllList.do?randIni=" + rand);

		$("#query_btn").click(query);

	});
</script>