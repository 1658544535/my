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
			var url = "checkManufacturerWithdraw.do?manufacturerWithdrawPojo.id="+val;	
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
		if(confirm("确认要取消审核吗？（说明：用户需要再次发起申请！）")){
			var url = "uncheckManufacturerWithdraw.do?manufacturerWithdrawPojo.id="+val;	
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
	
	function finish(val){
		if(confirm("确认已经提现完成吗？")){
			var url = "finishManufacturerWithdraw.do?manufacturerWithdrawPojo.id="+val;	
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
			alert("操作成功");
		}else{
			alert("操作失败");
		}
		query();
	}
	
	function checkAll(){
		$("#idform").attr("action","checkActivityProductAll.do?op=1&type=${type}");
		$("#idform").submit();
	}
	</script>
</head>
<body>
<div class="sub_wrap">
    <div class="s_nav"><a href="#">商家中心管理</a> &gt;<a href="#">商家提现列表</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
				<tr><td align="right">提现时间：</td>
				<td>
				<input id="d5221" name="beginDateStr" class="Wdate" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},isShowToday:false,dateFmt:'yyyy-MM-dd ',maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
				至
				<input id="d5222" name="endDateStr" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd ',minDate:'#F{$dp.$D(\'d5221\')}'})"/>
				</td>
				<td align="right">提现状态：</td>
				<td>
					<select name="manufacturerWithdrawPojo.status" id="">
				 		<option value="">全部</option>
				 		<option value="0">待审核</option>
				 		<option value="1">申请已取消</option>
				 		<option value="2">待提现</option>
				 		<option value="3">审核不通过</option>
				 		<option value="4">已提现</option>
					</select>
				</td>
				<td align="right">商家ID：</td>
				<td>
					<input type="text" name="manufacturerWithdrawPojo.userId"  value="">
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
   <!--<s:if test="#session.role.roleId!=7">
   <a class="Add_btn" onclick="checkAll()" >审核全部</a><a class="Add_btn" href="generateSettleInfo.do" >结算生成</a></s:if>-->
   <form action="#" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th width='20px'><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>商家ID</th>
		<th>商家名称</th>
		<th>商家余额</th>
		<th>提现金额</th>
		<th>提现状态</th>
		<th>申请日期</th>
		<th>审核日期</th>
		<th>提现日期</th>
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
			queryData("manufacturerWithdrawCount.do?", "manufacturerWithdrawList.do?randquery="+rand);
		}
	}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var str = "";
		if(this.status == 0){
			str = "<a class='edit_btn' onclick=check('"+this.id+"')>审核</a><a  class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a>";
		} else if(this.status == 2){
			str = "<a class='edit_btn' onclick=finish('"+this.id+"')>提现</a><a  class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a>";
		} else {
			str = "";
		}
		$("#body").append(
				"<tr><td width='20px'><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.userId + "</td>"+
				"<td>"+ this.userName + "</td>"+
				"<td>"+ this.settleBalance + "</td>"+
				"<td>"+ this.withdrawAmount + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ this.createDateStr + "</td>"+
				"<td>"+ this.updateDateStr + "</td>"+
				"<td>"+ this.withdrawDateStr + "</td>"+
				"<s:if test='#session.role.roleId!=7'><td>"+str+"</td></s:if></tr>");
	}

	$(function() {
		/**
		  首次要初始化分页
		 **/
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>, "manufacturerWithdrawList.do");
		
		$("#query_btn").click(query);
	});
	
</script>