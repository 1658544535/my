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
function delPrize(url){
	if(confirm("确定要移除该团吗？")){
		MAOWU.ajax.get(url, null, delRefreshPage1);
	}else{
		return ;
	}
}
function delRefreshPage1(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("移除成功");
		query();
	} else{
		alert("移除失败");
	}
}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="#">免费抽奖</a> &gt; <a href="#">中奖设置列表</a></div>
		<!--<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		 查询开始 
		<form action="" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td>开团时间：</td>
						
						<td align="left"><input name="endTime" id="beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })"/></td>
						<td align="left"><input type="text" name="grouponUserRecordPojo.keywords" placeholder="团编号"/></td>
						<td align="left">团状态:</td>
						<td align="left">
							<select name="grouponUserRecordPojo.groupStatus" id="grouponUserRecordPojo.groupStatus"  class="floatLeft">
								<option value="">全部</option>
								<option value="0">组团中</option>
								<option value="1">已成团</option>
								<option value="2">拼团失败</option>
				    		</select>
						</td>	-->
						<!--<td align="left">中奖状态:</td>	
						<td align="left">
							<select name="freeGrouponRecordPojo.activityStatus" id="freeGrouponRecordPojo.activityStatus"  class="floatLeft">
								<option value="">全部</option>
								<option value="1">已中奖</option>
								<option value="2">待开奖</option>
				    		</select>
						</td>	
						<td align="left"><input id="query_btn" type="button" class="submit_btn" value="查询" /></td>	-->
						<!-- <td align="left">价格区间:</td>		
						<td align="left"><input type="text" name="minPrice" placeholder="最低价格"/>&nbsp;&nbsp;<input type="text" name="maxPrice" placeholder="最高价格"/></td>
					 </tr>
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="Clear"></div>
			</div>
		</form>-->
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<%--
			<a class="Add_btn" href="goAddGrouponUserRecord.do" >新增</a>
			<a class="Add_btn" onclick="checkAll('checkGrouponUserRecordAll.do')" >审核选中</a>
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
						<!-- <th>目前人数</th> -->
						<th>团状态</th>
						<!--<th>中奖状态</th>-->
						<th>操作</th>
					</tr>
					<tbody id="body"></tbody>    
				</table>
			</form>
			<br><br>
			<div id="openWinStr">
				<div style="width: 100%"><a class="Add_btn"  style="margin-left: 40%;" id="openWin">开奖</a></div>
			</div>
			
			<!--<div class="page">
				<div class="floatleft">
					总共 <i id="rowcount">${page.rowCount}</i> 条/<i id="pagecount"></i>页
				</div>
				<div style="float: right" id="Pagination" class="pagination"></div>
				<div class="Clear"></div>
			</div>-->
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
			queryData("freeDrawGroRecCount.do?grouponUserRecordPojo.id=${grouponUserRecordPojo.id}&grouponUserRecordPojo.isPrize=1", "freeDrawGroRecList.do?grouponUserRecordPojo.id=${grouponUserRecordPojo.id}&grouponUserRecordPojo.isPrize=1&randquery="+rand+"");
		}
	}
	
	/**
	 * 分页展现页面函数 
	 */
	function installPage() {
		var statusStr = "",activityStatusStr = "";
		if(this.status==1){
			statusStr = "已成团";
			if(this.activityStatus==3){
				activityStatusStr = "已中奖";
			} else {
				activityStatusStr = "——";
			}
		} else if(this.status==0){
			if(this.num1 >= this.num){
				statusStr = "已成团";
			}else{
				statusStr = "组团中";
			}
			activityStatusStr = "——";
		} else if(this.status==2){
			statusStr = "拼团失败";
			if(this.activityStatus==3){
				activityStatusStr = "未中奖";
			} else {
				activityStatusStr = "——";
			}
		}
		$("#body").append(
				"<tr>"+
				"<input type='hidden' name='tids' value="+this.id+"><input type='hidden' name='tids' value='667'>"+
				"<td>"+ this.id +"</td>"+
				"<td>"+ this.userName + "</td>"+
				"<td>"+ this.beginTimeStr + "</td>"+
				//"<td>"+ this.num1 +"/"+ this.num + "</td>"+
				"<td>"+ statusStr + "</td>"+
				//"<td>"+ activityStatusStr + "</td>"+
				"<td><a class='del_btn' onclick='delPrize(\"delFreeDrawGroPrizeById.do?grouponUserRecordPojo.id="+ this.id +"&grouponUserRecordPojo.isPrize=0\")'>移除</a></td>"+
				"</tr>"
				);
	}
	/**
	 *  首次要初始化分页
	 */
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init("${page.rowCount}", "freeDrawGroRecList.do?grouponUserRecordPojo.id=${grouponUserRecordPojo.id}&grouponUserRecordPojo.isPrize=1&randIni="+rand+"&groActId="+"${id}");	
		$("#query_btn").click(query);	
		
		//检查是否已开奖
		$.ajax({
		  url: "checkOpenWin.do?activityId="+"${grouponUserRecordPojo.id}",
		  cache: false,
		  success: function(result){
		    if(result == "1"){
		    	$("#openWinStr").empty();
		    	$("#openWinStr").append("<div style='width: 100%'><a class='Add_btn'  style='margin-left: 40%;background:gray' id=''>活动还未结束!</a></div>");
		    } else if(result == "2"){
		    	$("#openWinStr").empty();
		    	$("#openWinStr").append("<div style='width: 100%'><a class='Add_btn'  style='margin-left: 40%;background:gray' id=''>已开奖</a></div>");
		    } else {
		    	console.log(result);
		    }
		  }
		});
	});	
	//开奖
	$("#openWin").click(function(){
		if(confirm("确认要开奖吗？")){
			var formParam = $("#idform").serialize();
			$.ajax({
			  url: "openFreeDrawHandle.do?activityId="+"${grouponUserRecordPojo.id}",
			  cache: false,
			  data:formParam,
			  success: function(result){
			    if(result == "1"){
			    	alert("开奖成功");
			    	window.history.back();
			    } else {
			    	alert(result);
			    }
			  }
		});
		}else{
			return ;
		}
	})
	
</script>