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

</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">商品搜索管理</a> &gt; <a href="#">用户搜索关键字记录</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="searchKey.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">关键词：</td>
						<td><label><input type="text" name="searchKey.keyword"
								value=""></label></td>
						<%--<td align="right" >类型:</td>
						<td><select name="searchKey.type" id="ticketType"
							class="floatLeft">
								<option value="">----请选择----</option>
								<c:forEach items="${type}" var="type">
										<option value="${type.value}">${type.name}</option>
								</c:forEach>
						</select></td>
						--%>
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
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th>关键词</th>
						<th>次数</th>
						<%--<th>类型</th>--%>
					</tr>
					<tbody id="body"></tbody>

				</table>
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
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("getSearchKeyCount.do", "searchKeyAllList.do?randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td>" 
				+ this.keyword + 
		        "</td><td>" + this.hits+
		        //"</td><td>" + this.typeName
		        + "</td></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"searchKeyAllList.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>