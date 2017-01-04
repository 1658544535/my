<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
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
	} else {
		alert("加入失败");
	}
}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="#">免费抽奖团列表管理</a> &gt; <a href="#">免费抽奖团列表管理</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td>开团时间：</td>
						
						<td align="left"><input name="beginTime" id="beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })"/>
						— <input name="endTime" id="beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })"/></td>
						<td align="left"><input type="text" name="grouponUserRecordPojo.keywords" placeholder="团编号或团长名称"/></td>
						<td align="left">团状态:</td>
						<td align="left">
							<select name="grouponUserRecordPojo.groupStatus" id="grouponUserRecordPojo.groupStatus"  class="floatLeft">
								<option value="">全部</option>
								<option value="0">组团中</option>
								<option value="1">已成团</option>
								<option value="2">拼团失败</option>
				    		</select>
						</td>	
						<td align="left">中奖状态:</td>	
						<td align="left">
							<select name="grouponUserRecordPojo.status" id="grouponUserRecordPojo.status"  class="floatLeft">
								<option value="">全部</option>
								<option value="1">已中奖</option>
								<option value="2">未中奖</option>
				    		</select>
						</td>
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
			
			<c:if test="${grouponUserRecordPojo.activityStatus==2}">
			<a class="Add_btn" href="goFreeDrawGroPrizeListWeb.do?grouponUserRecordPojo.id=${grouponUserRecordPojo.id}" >去中奖列表设置中奖</a>
			</c:if>
			<%--<a class="Add_btn" onclick="checkAll('checkGrouponUserRecordAll.do')" >审核选中</a>
			<a class="delAll_btn" onclick="uncheckAll('uncheckGrouponUserRecordAll.do')" >选中取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delGrouponUserRecordAll.do')" >选中删除</a>
			--%>
			<!--<a class="comment_btn" href="goActivityProductComment.do?activityProductCommentPojo.activityId=${freeGrouponRecordPojo.id}">评论审核</a>-->
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th>团编号</th>
						<th>团长</th>
						<th>开团时间</th>
						<th>目前人数</th>
						<th>团状态</th>
						<th>中奖状态</th>
						<th>操作</th>
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
			queryData("freeDrawGroRecCount.do?grouponUserRecordPojo.id=${grouponUserRecordPojo.id}", "freeDrawGroRecList.do?grouponUserRecordPojo.id=${grouponUserRecordPojo.id}&randquery="+rand+"");
		}
	}
	
	/**
	 * 分页展现页面函数 
	 */
	function installPage() {
		var statusStr = "",activityStatusStr = "";
		if(this.status==1 || this.num1 >= this.num){
			statusStr = "已成团";
		} else if(this.status==0 && this.num1 < this.num){
			statusStr = "组团中";
		} else if(this.status==2 && this.num1 < this.num){
			statusStr = "拼团失败";
		}
		if(this.activityStatus==3&&this.status==1){
			activityStatusStr = "已中奖";
		} else if(this.activityStatus==3&&this.status==2) {
			activityStatusStr = "未中奖";
		} else {
			activityStatusStr = "——";
		}
		var addPrizeStr="";
		if(this.isPrize==0 && this.activityStatus==2 && this.num1==this.num){
			addPrizeStr="<a class='edit_btn' onclick='addPrize(\"goFreeDrawGroPrizeList.do?grouponUserRecordPojo.id="+ this.id +"&grouponUserRecordPojo.activityId=${grouponUserRecordPojo.id}&grouponUserRecordPojo.isPrize=1\")'>加入中奖列表</a>";
		} else {
			addPrizeStr="";
		}
		$("#body").append(
				"<tr>"+
				"<td>"+ this.id +"</td>"+
				"<td>"+ this.userName + "</td>"+
				"<td>"+ this.beginTimeStr + "</td>"+
				"<td>"+ this.num1 +"/"+ this.num + "</td>"+
				"<td>"+ statusStr + "</td>"+
				"<td>"+ activityStatusStr + "</td>"+
				"<td><a class='edit_btn' href='goFreeDrawGroUserRec.do?grouponUserRecordPojo.attendId="+this.id+"&grouponUserRecordPojo.num="+this.num+"&grouponUserRecordPojo.num1="+this.num1+"&grouponUserRecordPojo.activityStatus="+this.activityStatus+"&grouponUserRecordPojo.isPrize="+this.isPrize+"&grouponUserRecordPojo.activityId=${grouponUserRecordPojo.id}'>查看</a>"+addPrizeStr+"</td>"+
				"</tr>"
				);
	}
	/**
	 *  首次要初始化分页
	 */
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init("${page.rowCount}", "freeDrawGroRecList.do?grouponUserRecordPojo.id=${grouponUserRecordPojo.id}&randIni="+rand+"&groActId="+"${id}");	
		$("#query_btn").click(query);		
	});	
</script>