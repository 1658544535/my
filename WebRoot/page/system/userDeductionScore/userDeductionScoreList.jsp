<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">商家扣分管理</a> &gt; <a href="#">商家扣分详情列表</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
				    	<td align="right">状态：</td>
						<td><select name="userDeductionScorePojo.status" id=".status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">已审核</option>
							</select>
						</td>
						<td align="right">创建日期：</td>
						<td>
							<input id="c" name="userDeductionScorePojo.createDateStr" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd'})"/>
						</td>
						<td align="right">修改日期：</td>
						<td>
							<input id="u" name="userDeductionScorePojo.updateDateStr" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd'})"/>
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
   <s:if test="#session.role.roleId!=7">
   <a href="addUserDeductionScore.do?userDeductionScorePojo.suserId=${userDeductionScorePojo.suserId}" class="Add_btn">新增扣分详情</a>
   </s:if>
  <form action="" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>扣减分数</th>
		<th width="50%">扣减原因</th>
		<th>是否审核</th>
		<th>创建时间</th>
		<th>修改时间</th>
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
  
  
  
  <!---->


</div>
</body>
</html>


	


<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("userDeductionScoreCount.do?userDeductionScorePojo.suserId=${userDeductionScorePojo.suserId}", "userDeductionScoreList.do?userDeductionScorePojo.suserId=${userDeductionScorePojo.suserId}&randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				"<td>"+ this.deductScore + "</td>"+
				"<td>"+ this.remark + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ this.createDateStr + "</td>"+
				"<td>"+ this.updateDateStr + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td>"+
				"<a href='updateUserDeductionScore.do?userDeductionScorePojo.suserId="+this.suserId+"&userDeductionScorePojo.id="+this.id+"' class='edit_btn'>修改扣分详情</a>"+
				"<a  class='edit_btn' onclick=check('"+this.id+"')>审核</a>"+
				"<a  class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a>"+
				"<a  class='del_btn' onclick=del('"+this.id+"')>删除</a></td></s:if>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userDeductionScoreList.do?userDeductionScorePojo.suserId=${userDeductionScorePojo.suserId}&randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>
<script type="text/javascript">
$(function(){
	$("#excel").click(function() {
			if(tt.validate()){
				var formParam = $("#sysform").serialize();
				$(location).attr('href', 'getProductExcel.do?'+formParam);
			}
		});
});

function allcb(){
	var checkbox=document.getElementById("selectcb"); 
	if(checkbox.checked==true){
		var code_Values = document.getElementsByName("tids") ; 
		for(var i = 0;i < code_Values.length;i++){ 
		if(code_Values[i].type == "checkbox") 
		{ 
		code_Values[i].checked = true; 
		} 
		} 
	 
	}else{	
		var code_Values = document.getElementsByName("tids") ; 
		for(var i = 0;i < code_Values.length;i++){ 
		if(code_Values[i].type == "checkbox") 
		{ 
		code_Values[i].checked = false;
		} 
		} 
	}

}

	function deleteAll(){
		document.getElementById("idform").submit();
	}


	function del(val)
	{
		if(confirm("你真的想删除该记录么？"))
		{
			var url = "delUserDeductionScore.do?userDeductionScorePojo.id="+val;
			doOpreator(url,null);
		}else{
			return ;
		}
		
	}
	function doOpreator(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage);
	}
	 
	function goRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("删除成功");
			queryData("userDeductionScoreCount.do?userDeductionScorePojo.suserId=${userDeductionScorePojo.suserId}", "userDeductionScoreList.do?userDeductionScorePojo.suserId=${userDeductionScorePojo.suserId}&randquery="+rand);
		}else{
			alert("删除失败");
		}
	}
	
	function check(val)
	{
		if(confirm("确认要通过审核吗？"))
		{
			var url = "checkUserDeductionScore.do?userDeductionScorePojo.id="+val;
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
			queryData("userDeductionScoreCount.do?userDeductionScorePojo.suserId=${userDeductionScorePojo.suserId}", "userDeductionScoreList.do?userDeductionScorePojo.suserId=${userDeductionScorePojo.suserId}&randquery="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function uncheck(val)
	{
		if(confirm("确认要取消审核吗？"))
		{
			var url = "uncheckUserDeductionScore.do?userDeductionScorePojo.id="+val;
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
			queryData("userDeductionScoreCount.do?userDeductionScorePojo.suserId=${userDeductionScorePojo.suserId}", "userDeductionScoreList.do?userDeductionScorePojo.suserId=${userDeductionScorePojo.suserId}&randquery="+rand);
		}else{
			alert("取消审核失败");
		}
	}
</script>