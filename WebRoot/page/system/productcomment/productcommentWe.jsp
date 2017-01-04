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
			<a href="#">用户评论管理</a> &gt; <a href="#">商品分值记录</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<div id="search_show" style="">
			<form action=productCommentWe.do method="post" id="sysform">
				<table width="100%" border="0" class="Search_table">
					<tr align="center">
						<td align="right">用户昵称:</td>
						<td align="left"><input type="text"
							name="productCommentPojo.userName" 
							value="" /></td>
						<td align="right">订单号:</td>
						<td align="left"><input type="text"
							name="productCommentPojo.orderNo" 
							value="" /></td>
						
					</tr>
					<tr align="right">
						<td align="right" colspan="4">
							<div class="floatRight">
								<input type="hidden" name="page.pageNo" value=0 id="pageNo">
								<input id="query_btn" type="button" class="submit_btn"
									value="查询" />
							</div>
						</td>
					</tr>
				</table>
				<div class="Clear"></div>
			</form>
		</div>

		<div class="h15"></div>
		<div>
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th>用户昵称</th>
						<th>商品名称</th>
						<th>订单号</th>
						<th>评价状态</th>
						<th>评价时间</th>
						<th>总分数</th>
						<th>宝贝与描述相符分数</th>
						<th>卖家服务态度分数</th>
						<th>卖家发货速度分数</th>
						<th>物流发货速度分数</th>
						<th>送件人员态度分数</th>
						
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
			queryData("getProductCommentCountWe.do",
					"productCommentAllListWe.do?randquery=" + rand);
		}
	}

	//分页展现页面函数 
	function installPage() {
		$("#body") 
				.append(
						"<tr><td>"
								+ this.userName  
								+ "</td><td>"
								+ this.productName 
								+ "</td><td>"
								+ this.orderNo
								+ "</td><td>"
								+ this.scoreName
								+ "</td><td>"
								+ this.create_date_str 
								+ "</td><td>"
								+ this.scoreTotal  
								+ "</td><td>"
								+ this.scoreProduct  
								+ "</td><td>"
								+ this.scoreService  
								+ "</td><td>"
								+ this.scoreSspeed  
								+ "</td><td>"
								+ this.scoreEspeed  
								+ "</td><td>"
								+ this.scoreEservice  
								+ "</td></tr>");
	}

	$(function() {
		//首次要初始化分页
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"productCommentAllListWe.do?randIni=" + rand);
		$("#query_btn").click(query);

	});

	
</script>