<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="#">0.1夺宝参团用户记录管理</a> &gt; <a href="#">0.1夺宝参团用户记录管理</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="20%" border="0" class="Search_table">
					<tr>
						<td align="left"><input type="text" name="keywords" placeholder="用户昵称或编号或订单号"/></td>
						<td align="left"><input id="query_btn" type="button" class="submit_btn" value="查询" /></td>
						<!-- <td align="left">价格区间:</td>		
						<td align="left"><input type="text" name="minPrice" placeholder="最低价格"/>&nbsp;&nbsp;<input type="text" name="maxPrice" placeholder="最高价格"/></td>
					 --></tr>
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				
				<div class="Clear"></div>
			</div>
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<%--
			<a class="Add_btn" href="goAddGrouponUserRecord.do" >新增</a>
			<a class="Add_btn" onclick="checkAll('checkGrouponUserRecordAll.do')" >审核选中</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckGrouponUserRecordAll.do')" >选中取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delGrouponUserRecordAll.do')" >选中删除</a>
			--%>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th>团编号</th>
						<th>用户ID</th>
						<th>用户昵称</th>
						<th>是否团长</th>
						<th>参与时间</th>
						<th>订单号</th>
						<th>是否中奖</th>
					</tr>
					<tbody id="body"></tbody>    
				</table>
			</form>
			<div class="page">
				<div class="floatleft">
					总共 <i id="rowcount">${page.rowCount}</i> 条/<i id="pagecount"></i>页
				</div>
				<div style="float: right" id="Pagination" class="pagination"></div>
				<div class="Clear"></div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
	/**
	 * 条件查询函数 
	 */
	function query() {
		if(tt.validate()){
			var rand=Math.random() * (100001);
			queryData("drawGroupGroUserRecCount.do?drawGrouponRecordPojo.attendId=${drawGrouponRecordPojo.attendId}", "drawGroupGroUserRecList.do?drawGrouponRecordPojo.attendId=${drawGrouponRecordPojo.attendId}&randquery="+rand+"&groActId="+"${id}");
		}
	}
	
	/**
	 * 分页展现页面函数 
	 */
	function installPage() {
		var isHeadStr="",prizeStr="";
		if(this.isHead==0){
			isHeadStr="否";
		} else if(this.isHead==1){
			isHeadStr="是";
		}
		if(this.prize==0){
			prizeStr="否";
		} else {
			prizeStr="是";
		}
		$("#body").append(
				"<tr>"+
				"<td>"+ this.attendId +"</td>"+
				"<td>"+ this.userId + "</td>"+
				"<td>"+ this.userName + "</td>"+
				"<td>"+ isHeadStr + "</td>"+
				"<td>"+ this.attendTimeStr + "</td>"+
				"<td>"+ this.orderNo + "</td>"+
				"<td>"+ prizeStr + "</td>"+
				
				"</tr>"
				);
	}
	/**
	 *  首次要初始化分页
	 */
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init("${page.rowCount}", "drawGroupGroUserRecList.do?drawGrouponRecordPojo.attendId=${drawGrouponRecordPojo.attendId}&randIni="+rand+"&groActId="+"${id}");	
		$("#query_btn").click(query);		
	});	
</script>
