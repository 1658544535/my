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
		<div class="s_nav"><a href="#">拼得客交易记录管理</a> &gt; <a href="#">拼得客交易记录列表</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<input name="userDealLogPojo.userId" value="${userDealLogPojo.userId }" type="hidden">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">交易类型：</td>
						<td>
							<select name="userDealLogPojo.dealType" id=""  class="floatLeft">
								<option value="">全部</option>
								<option value="1">收入</option>
								<option value="2">支出</option>
				    		</select>
						</td>
						<td align="right">交易单号：</td>
						<td>
							<input name="userDealLogPojo.orderNo" id="" type="text" placeholder="" class="floatLeft" />
						</td>
						<td align="right">交易时间：</td>
						<td>
							<input id="s" name="userDealLogPojo.dealDateStr" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'e\')}'})"/>
							<input id="e" name="userDealLogPojo.dealDateEndStr" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'s\')}'})"/>
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
			<!-- <a class="Add_btn" href="goAddUserDealLog.do" >新增</a> -->
			
			<!-- <a class="Add_btn" onclick="checkAll('checkUserDealLogAll.do')" >批量审核</a> -->
			<!-- <a class="Add_btn" onclick="uncheckAll('uncheckUserDealLogAll.do')" >选中取消审核</a> -->
			<!-- <a class="delAll_btn" onclick="deleteAll('delUserDealLogAll.do')" >批量删除</a> -->
			
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th colspan="10">收入：${userPindekeInfoPojo.rebatePrice }　支出：${userPindekeInfoPojo.withdrawPrice }　剩余： ${userPindekeInfoPojo.surpluPrice }</th>
					</tr>
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>交易类型</th>
						<th>交易金额</th>
						<th>交易单号</th>
						<th>开团号</th>
						<th>交易时间</th>
						<th>完成时间</th>
						<th>备注</th>
						<th>审核状态</th>
						<th>审核说明</th>
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
			queryData("pindekeUserDealLogCnt.do", "pindekeUserDealLogList.do?randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
		var statusStr = "待审核",dealTypeStr = "收入",remarkStr = "返佣";
		
		if(this.status == 1){
			statusStr = "审核通过";
		}else if(this.status == 2){
			statusStr = "审核不通过";
		}else if(this.status == 3){
			statusStr = "转账完成";
		}
		if(this.dealType == 2){
			dealTypeStr = "支出";
		}
		if(this.remark == 2){
			remarkStr = "提现";
		}
		
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ dealTypeStr + "</td>"+
				"<td>"+ this.dealAmount + "</td>"+
				"<td>"+ this.orderNo + "</td>"+
				"<td>"+ this.groupId + "</td>"+
				"<td>"+ this.dealDateStr + "</td>"+
				"<td>"+ this.overTimeStr + "</td>"+
				"<td>"+ remarkStr + "</td>"+
				"<td>"+ statusStr + "</td>"+
				"<td>"+ this.returnMsg + "</td>"+
				"</tr>"
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "pindekeUserDealLogList.do?randIni="+rand);	
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
</script>
