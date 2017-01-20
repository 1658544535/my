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
		<div class="s_nav"><a href="#">拼得客排行榜</a> &gt; <a href="#">拼得客排行榜列表</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">申请人或手机号码：</td>
						<td>
							<input name="nameOrPhone" id="" type="text" placeholder="" class="floatLeft" />
						</td>
						<td align="right">申请人时间：</td>
						<td>
							<input id="s" name="beginTimeStr" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'e\')}'})"/>
							<input id="e" name="endTimeStr" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'s\')}'})"/>
						</td>
						<td align="right">查询月份：</td>
						<td>
							<input id="" name="yearStr" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy'})"/>
							<input id="" name="monthStr" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'MM'})"/>
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
			<a class="Add_btn" href="pindekeRankingTask.do" >更新排行榜</a> 
			<%--
			<a class="Add_btn" onclick="checkAll('checkPindekeMonthSaleAll.do')" >审核选中</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckPindekeMonthSaleAll.do')" >选中取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delPindekeMonthSaleAll.do')" >选中删除</a>
			--%>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>拼得客编号</th>
						<th>真实姓名</th>
						<th>手机号码</th>
						<th>申请时间</th>
						<!-- <th>返佣金额</th> -->
						<th>销售额</th>
						<th>返佣状态</th>
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
	 ** 条件查询函数 
	 **/
	function query() {
		if(tt.validate()){
			var rand=Math.random() * (100001);
			queryData("pindekeMonthSaleCnt.do", "pindekeMonthSaleList.do?randquery="+rand);
		}
	}
	
	function settle(url){
		if(confirm("确认要返佣吗？")){
			MAOWU.ajax.get(url, null, settleRefreshPage);
		}else{
			return ;
		}
	}
	
	function settleRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("返佣成功");
			query();
		} else if(result=="2"){
			alert("返佣失败");
			query();
		} else{
			alert("返佣失败");
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
		var isSellteName = "",option = "";
		if(this.isSettle == 0 && this.total > 3000 && (this.ranking == 1 || this.ranking == 2 || this.ranking == 3)){
			isSellteName = "未返佣";
			option = "<a class='edit_btn' onclick='settle(\"settlePindekeMonthSale.do?id="+this.id+"\")'>点击返佣</a>";
		}else if(this.isSettle == 1 && this.total > 3000 && (this.ranking == 1 || this.ranking == 2 || this.ranking == 3)){
			isSellteName = "已返佣";
		}else{
			isSellteName = "无返佣";
		}
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.pindekeNumber + "</td>"+
				"<td>"+ this.name + "</td>"+
				"<td>"+ this.phone + "</td>"+
				"<td>"+ this.applyDate + "</td>"+
				//"<td>"+ this.settleAmt + "</td>"+
				"<td>"+ this.total + "</td>"+
				"<td>"+ isSellteName + "</td>"+
				<s:if test="#session.role.roleId!=7">
				"<td>"+
				//"<a class='edit_btn' onclick='check(\"checkPindekeMonthSale.do?id="+this.id +"\")'>审核通过</a>"+
				//"<a class='edit_btn' onclick='uncheck(\"uncheckPindekeMonthSale.do?id="+this.id +"\")'>取消审核</a>"+
				//"<a class='edit_btn' href='goEditPindekeMonthSale.do?id="+this.id +"'>编辑</a>"+
				//"<a class='del_btn' onclick='del(\"delPindekeMonthSale.do?id="+this.id+"\")'>删除</a>"+
				option+
				"</td>"
				</s:if>
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "pindekeMonthSaleList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
