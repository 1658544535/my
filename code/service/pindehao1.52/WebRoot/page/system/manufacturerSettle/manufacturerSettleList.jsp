<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript"
src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">
	function allcb(){
		var checkbox=document.getElementById("selectcb"); 
		if(checkbox.checked==true){
			var code_Values = document.getElementsByName("tids") ; 
			for(i = 0;i < code_Values.length;i++){ 
				if(code_Values[i].type == "checkbox") { 
					code_Values[i].checked = true; 
				} 
			} 
		}else{	
			var code_Values = document.getElementsByName("tids") ; 
			for(i = 0;i < code_Values.length;i++){ 
				if(code_Values[i].type == "checkbox"){ 
					code_Values[i].checked = false
				} 
			} 
		}
	}
	
	function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkManufacturerSettle.do?facturerSettlePojo.id="+val;	
			doOpreator2(url,null);
		}else{
			return ;
		}
	}
	
	function doOpreator2(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage2);
	}
	 
	function goRefreshPage2(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核成功");
		}else{
			alert("审核失败");
		}
		query();
	}
	
	function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckManufacturerSettle.do?facturerSettlePojo.id="+val;	
			undoOpreator(url,null);
		}else{
			return ;
		}
	}
	
	function undoOpreator(url,params){
		MAOWU.ajax.get(url, params, ungoRefreshPage);
	}
	 
	function ungoRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("取消审核成功");
		}else{
			alert("取消审核失败");
		}
		query();
	}
	
	function checkAll(){
		$("#idform").attr("action","checkActivityProductAll.do?op=1&type=${type}");
		$("#idform").submit();
	}
	
	function batchGen(){
		if(confirm("确认开始结算吗？")){
			$("#genBnt").attr("href","generateSettleInfo.do");
		}else{
			return ;
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
    <div class="s_nav"><a href="#">商家结算管理</a> &gt;<a href="#">商家结算列表</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="findActivityTimeList.do?op=1" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
				<tr><td align="right">查询时间：</td>
				<td>
				<input id="d5221" name="beginDateStr" class="Wdate" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},isShowToday:false,dateFmt:'yyyy-MM-dd ',maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
				至
				<input id="d5222" name="endDateStr" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd ',minDate:'#F{$dp.$D(\'d5221\')}'})"/>
				</td>
				<td align="right">结算状态：</td>
				<td>
					<select name="facturerSettlePojo.status" id="">
				 		<option value="">全部</option>
				 		<option value="0">待审核</option>
				 		<option value="1">待确认</option>
				 		<option value="2">已结算</option>
					</select>
				</td>
				<td align="right">商家ID：</td>
				<td>
					<input type="text" name="facturerSettlePojo.userId" id="activityGoodsPojo.productNum" value="">
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
   <s:if test="#session.role.roleId!=7"><a class="Add_btn" href="javascript:;" onclick="batchGen();" id="genBnt">结算生成</a></s:if>
   <form action="#" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th width='20px'><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>结算日期</th>
		<th>商家名称</th>
		<th>结算金额</th>
		<th>结算状态</th>
		<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>
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
	var ctx  ="<s:property value="ctx" />";
	var pagecount = "${page.rowCount}"; 
	function query() {
		if(tt.validate()){
			var rand=Math.random() * ( 100000 + 1);
			queryData("manufacturerSettleCount.do?", "manufacturerSettleList.do?randquery="+rand);
		}
	}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var str = "";
		if(this.status == 2){
			str = "";
		} else {
			str = "<a class='edit_btn' onclick=check('"+this.id+"')>审核</a><a  class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a>";
		}
		$("#body").append(
				"<tr><td width='20px'><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.settleDateStr + "</td>"+
				"<td>"+ this.company + "</td>"+
				"<td>"+ this.settleAmount + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td>" +
				str + "<a class='edit_btn' href='genOrderSetterExcel.do?facturerSettlePojo.id="+this.id+"')>对账单</a></td></s:if></tr>");
	}

	$(function() {
		/**
		  首次要初始化分页
		 **/
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>, "manufacturerSettleList.do");
		
		$("#query_btn").click(query);
	});
	
</script>