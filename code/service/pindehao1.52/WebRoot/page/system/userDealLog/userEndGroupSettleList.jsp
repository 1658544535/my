<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="#">拼得客待结算</a> &gt; <a href="#">拼得客待结算列表</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">团状态：</td>
						<td>
							<select name="grouponUserRecordPojo.recordStatus" id=""  class="floatLeft">
								<option value="">全部</option>
								<option value="1">已成团</option>
								<option value="2">未成团</option>
				    		</select>
						</td>
						<td align="right">开团帐号：</td>
						<td>
							<input name="grouponUserRecordPojo.loginName" id="" type="text" placeholder="" class="floatLeft" />
						</td>
						<td align="right">团结束时间：</td>
						<td>
							<input id="s" name="grouponUserRecordPojo.beginTimeStr" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'e\')}'})"/>
							<input id="e" name="grouponUserRecordPojo.endTimeStr" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'s\')}'})"/>
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
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<a class="Add_btn" onclick="addAll('addUserDealLog.do')" >一键返佣</a>
			<%--
			<a class="Add_btn" onclick="checkAll('checkUserDealLogAll.do')" >审核选中</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckUserDealLogAll.do')" >选中取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delUserDealLogAll.do')" >选中删除</a>
			--%>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>开团帐号</th>
						<th>团编号</th>
						<th>团状态</th>
						<th>开团时间</th>
						<th>团结束时间</th>
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
			queryData("userEndGroupSettleCnt.do", "userEndGroupSettleList.do?randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
		var statusStr = "已成团";
		
		if(this.recordStatus == 2){
			statusStr = "组团失败";
		}
		
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.loginName + "</td>"+
				"<td>"+ this.id + "</td>"+
				"<td>"+ statusStr + "</td>"+
				"<td>"+ this.beginTimeStr + "</td>"+
				"<td>"+ this.endTimeStr + "</td>"+
				"</tr>"
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "userEndGroupSettleList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
	
	function check2(url){
		if(confirm("确认要审核不通过吗？")){
			var msg = prompt("请输入审核不通过原因！~", "");
	        if(msg != null && msg.trim() != ""){
	        	url += "&returnMsg="+msg;
				MAOWU.ajax.get(url, null, checkRefreshPage2);
	        }else{
	        	alert("请输入审核不通过原因！~");
	        }
		}else{
			return ;
		}
	}
	
	function checkRefreshPage2(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核不通过成功");
			query();
		} else{
			alert("审核不通过失败");
		}
	}
	
	function check3(url){
		if(confirm("确认要完成转账吗？")){
			var msg = prompt("请输入转账交易单号！~", "");
	        if(msg != null && msg.trim() != ""){
	        	url += "&orderNo="+msg;
				MAOWU.ajax.get(url, null, checkRefreshPage3);
	        }else{
	        	alert("请输入转账交易单号！~");
	        }
		}else{
			return ;
		}
	}
	
	function checkRefreshPage3(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("完成转账成功");
			query();
		} else{
			alert("完成转账失败");
		}
	}
	
	function addAll(url){
		if(confirm("确认要全部返佣吗？")){
			MAOWU.ajax.get(url, null, addAddRefreshPage);
		}else{
			return ;
		}
	}
	
	function addAddRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("全部返佣成功");
			query();
		} else{
			alert("全部返佣失败");
		}
	}
</script>
