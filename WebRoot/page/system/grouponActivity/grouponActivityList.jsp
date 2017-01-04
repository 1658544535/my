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
		<div class="s_nav"><a href="#">团购活动表管理</a> &gt; <a href="#">团购活动表管理</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right"></td>
						<td></td>		
					</tr>
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
			<a class="Add_btn" href="goAddGrouponActivity.do" >新增</a>
			<%--
			<a class="Add_btn" onclick="checkAll('checkGrouponActivityAll.do')" >审核选中</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckGrouponActivityAll.do')" >选中取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delGrouponActivityAll.do')" >选中删除</a>
			--%>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>编号id</th>
						<th>商品ID</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>拼团人数</th>
						<th>拼团价格</th>
						<th>是否默认，0-否1-是</th>
						<th>猜价价格区间起</th>
						<th>猜价价格区间止</th>
						<th>状态</th>
						<th>活动状态，0-未开始1-活动中2-活动结束</th>
						<th>活动类型，1-普通拼团2-团免3-猜价</th>
						<th>排序</th>
						<th>标题</th>
						<th>banner</th>
						<th>删除状态，0-否1-是</th>
						<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>
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
			queryData("grouponActivityCnt.do", "grouponActivityList.do?randquery="+rand);
		}
	}
	
	/**
	 * 分页展现页面函数 
	 */
	function installPage() {
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.id + "</td>"+
				"<td>"+ this.productId + "</td>"+
				"<td>"+ this.beginTime + "</td>"+
				"<td>"+ this.endTime + "</td>"+
				"<td>"+ this.num + "</td>"+
				"<td>"+ this.price + "</td>"+
				"<td>"+ this.isDefault + "</td>"+
				"<td>"+ this.priceMin + "</td>"+
				"<td>"+ this.priceMax + "</td>"+
				"<td>"+ this.status + "</td>"+
				"<td>"+ this.activityStatus + "</td>"+
				"<td>"+ this.type + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td>"+ this.title + "</td>"+
				"<td>"+ this.banner + "</td>"+
				"<td>"+ this.isDelete + "</td>"+
				<s:if test="#session.role.roleId!=7">
				"<td><a class='edit_btn' onclick='check(\"checkGrouponActivity.do?id="+this.id +"\")'>审核通过</a>"+
				"<a class='edit_btn' onclick='uncheck(\"uncheckGrouponActivity.do?id="+this.id +"\")'>取消审核</a>"+
				"<a class='edit_btn' href='goEditGrouponActivity.do?id="+this.id +"'>编辑</a>"+
				"<a class='del_btn' onclick='del(\"delGrouponActivity.do?id="+this.id+"\")'>删除</a></td>"
				</s:if>
				);
	}
	/**
	 *  首次要初始化分页
	 */
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "grouponActivityList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
