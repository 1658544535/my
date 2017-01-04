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
		<div class="s_nav"><a href="#">团购用户记录管理</a> &gt; <a href="#">团购用户记录管理</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="left"><input name="keywords" size="30" placeholder="用户编号 或 用户昵称 或 订单号"></td>
						<!--<td align="left">组团来源:
							<select name="source">
								<option value="">全部</option>
								<option value="1">普通拼团</option>
								<option value="4">独立购</option>
								<option value="2">团免</option>
							</select>
						</td>	-->	
						<td align="left">团状态:
							<select name="status">
								<option value="">全部</option>
								<option value="1">成功</option>
								<option value="2">失败</option>
							</select>
						</td>		
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
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th>订单来源</th>
						<th>团编号</th>
						<th>用户ID</th>
						<th>用户昵称</th>
						<th>是否团长</th>
						<th>参与时间</th>
						<th>订单号</th>
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
	 ** 条件查询函数 
	 **/
	function query() {
		if(tt.validate()){
			var rand=Math.random() * (100001);
			queryData("groUserRecCount.do?grouponUserRecordPojo.attendId=${grouponUserRecordPojo.attendId}", "groUserRecList.do?grouponUserRecordPojo.attendId=${grouponUserRecordPojo.attendId}&randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	 var activityType="";
	 var isHead="";
	 var recordStatusStr="";
	 var orderStatusStr="";
	 var payStatusStr="";
	function installPage() {
		if(this.activityType == 1){
			activityType = "普通团";
		} else if(this.activityType == 2){
			activityType = "团免";
		} else if(this.activityType == 3){
			activityType = "猜价";
		}
	
		if(this.isHead == 1){
			isHead = "是";
		} else if(this.isHead == 0){
			isHead = "否";
		}
		
		if(this.recordStatus == 1){
			recordStatusStr = "成功";
		} else if(this.recordStatus == 2){
			recordStatusStr = "失败";
		}
		//1.待付款2.已付款3.已发货4.已确认5.已评论
		if(this.orderStatus == 1){
			orderStatusStr = "待付款";
		} else if(this.orderStatus == 2){
			orderStatusStr = "已付款";
		} else if(this.orderStatus == 3){
			orderStatusStr = "已发货";
		} else if(this.orderStatus == 4){
			orderStatusStr = "已确认";
		} else if(this.orderStatus == 5){
			orderStatusStr = "已评论";
		}
		//0待支付1支付成功
		if(this.payStatus == 0){
			payStatusStr = "待支付";
		} else if(this.payStatus == 1){
			payStatusStr = "支付成功";
		}
		
		$("#body").append(
				"<tr>"+
				"<td>"+ activityType + "</td>"+
				"<td>"+ this.attendId + "</td>"+
				"<td>"+ this.userId + "</td>"+
				"<td>"+this.userName+"</td>"+
				"<td>"+ isHead + "</td>"+
				"<td>"+ this.attendTimeStr + "</td>"+
				"<td>"+ this.orderNo + "</td></tr>"
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "groUserRecList.do?grouponUserRecordPojo.attendId=${grouponUserRecordPojo.attendId}&randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
