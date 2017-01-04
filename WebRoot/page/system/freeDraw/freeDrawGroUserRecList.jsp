<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
<script>
function addPrize(url){
	if(confirm("确定要将该团加入中奖列表吗？")){
		MAOWU.ajax.get(url, null, addPrizeRefreshPage);
	}else{
		return ;
	}
}

function addPrizeRefreshPage(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		//window.location.href="goFreeDrawGroPrizeListWeb.do?grouponUserRecordPojo.id=${grouponUserRecordPojo.id}";
		alert("加入成功");
		query();
	} else if(result=="2"){
		alert("加入中奖团数量已满！");
	} else{
		alert("加入失败");
	}
}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="#">免费抽奖参团用户列表</a> &gt; <a href="#">免费抽奖参团用户列表管理</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="left"><input type="text" name="keywords" placeholder="用户昵称或编号或订单号"/></td>
						<!-- <td align="left">价格区间:</td>		
						<td align="left"><input type="text" name="minPrice" placeholder="最低价格"/>&nbsp;&nbsp;<input type="text" name="maxPrice" placeholder="最高价格"/></td>
					 --></tr>
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<c:if test="${grouponUserRecordPojo.activityStatus ==2 && grouponUserRecordPojo.isPrize == 0 && grouponUserRecordPojo.num == grouponUserRecordPojo.num1}">
			<a class="Add_btn" onclick="addPrize('goFreeDrawGroPrizeList.do?grouponUserRecordPojo.id=${grouponUserRecordPojo.attendId}&grouponUserRecordPojo.activityId=${grouponUserRecordPojo.activityId}&grouponUserRecordPojo.isPrize=1')" >将该团加入中奖列表</a>
			</c:if>
			<%--<a class="Add_btn" onclick="checkAll('checkGrouponUserRecordAll.do')" >审核选中</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckGrouponUserRecordAll.do')" >选中取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delGrouponUserRecordAll.do')" >选中删除</a>
			--%>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th>团编号</th>
						<th>用户编号</th>
						<th>用户昵称</th>
						<th>是否团长</th>
						<th>订单号</th>
						<th>参团时间</th>
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
			queryData("freeGroupGroUserRecCount.do?grouponUserRecordPojo.attendId=${grouponUserRecordPojo.attendId}", "freeGroupGroUserRecList.do?grouponUserRecordPojo.attendId=${grouponUserRecordPojo.attendId}&randquery="+rand+"");
		}
	}
	
	/**
	 * 分页展现页面函数 
	 */

	function installPage() {
		var isHeadStr="";
		if(this.isHead==1){
			isHeadStr="是";
		} else {
			isHeadStr="否";
		}
		$("#body").append(
				"<tr>"+
				"<td>"+ this.attendId +"</td>"+
				"<td>"+ this.userId +"</td>"+
				"<td>"+ this.userName + "</td>"+
				"<td>"+ isHeadStr + "</td>"+
				"<td>"+ this.orderNo + "</td>"+
				"<td>"+ this.attendTimeStr + "</td>"+
				"</tr>"
				);
	}
	
	
	/**
	 *  首次要初始化分页
	 */
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init("${page.rowCount}", "freeGroupGroUserRecList.do?grouponUserRecordPojo.attendId=${grouponUserRecordPojo.attendId}&randIni="+rand+"");	
		$("#query_btn").click(query);		
	});	
</script>
