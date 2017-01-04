<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">
function allcb(){
	var checkbox=document.getElementById("selectcb"); 
	if(checkbox.checked==true){
		var code_Values = document.getElementsByName("tids") ; 
		for(i = 0;i < code_Values.length;i++){ 
		if(code_Values[i].type == "checkbox") 
		{ 
		code_Values[i].checked = true; 
		} 
		} 
	 
	}else{	
		var code_Values = document.getElementsByName("tids") ; 
		for(i = 0;i < code_Values.length;i++){ 
		if(code_Values[i].type == "checkbox") 
		{ 
		code_Values[i].checked = false
		
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
			var url = "deletecouponById.do?couponPojo.couponId="+val;	
			MAOWU.ajax.get(url, null, delRefreshPage);
		}else{
			return ;
		}
	}
	
	function delRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("删除成功");
			query();
		}else{
			alert("删除失败");
		}
	}
	
	function checkAll(){
		$("#idform").attr("action","checkcouponAllById.do").submit();
	}
	function check(val)
	{
		if(confirm("确认要启用吗？"))
		{
			var url = "checkcouponById.do?couponPojo.couponId="+val;
			MAOWU.ajax.get(url, null, checkRefreshPage);
		}else{
			return ;
		}
		
	}
	function uncheck(val)
	{
		if(confirm("确认要禁用吗？"))
		{
			var url = "uncheckcouponById.do?couponPojo.couponId="+val;
			MAOWU.ajax.get(url, null, uncheckRefreshPage);
		}else{
			return ;
		}
		
	}
	function checkRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("启用成功");
			query();
		}else{
			alert("启用失败");
		}
	}
	function uncheckRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("禁用成功");
			query();
		}else{
			alert("禁用失败");
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">商品优惠管理</a> &gt; <a href="#">优惠券列表</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="couponList.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">优惠券名称：</td>
						<td><label><input type="text" name="couponPojo.name"
								id="couponPojo.name"
								value=""></label></td>
						<td align="right">类型：</td>
						<td><select name="couponPojo.type" id="couponPojo.type"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="1">满m减n金额</option>
							<option value="2">直减金额</option>
				    		</select><div id="scale_mgId"></div></td>
				    	<td align="right">状态：</td>
						<td><select name="couponPojo.status" id="couponPojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="1">启用</option>
							<option value="0">禁用</option>
							</select></td>
					</tr>
					<tr>
						<td align="right">有效期开始时间：</td>
						<td>
							<input id="s" name="couponPojo.validStimeDStr" value="" class="Wdate" type="text" 
							onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',
								maxDate:'#F{$dp.$D(\'e\')}'})"/>
								</td>
						<td align="right">有效期结束时间：</td>
						<td>
							<input id="e" name="couponPojo.validEtimeDStr" value="" class="Wdate" type="text" 
							onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',
								minDate:'#F{$dp.$D(\'s\')}'})"/>
								 </td>
						<td align="right">创建时间：</td>
						<td>
							<input id="c" name="couponPojo.createTimeDStr" value="" class="Wdate" type="text" 
							onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd'})"/>
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
  <s:if test="#session.role.roleId!=7"><a class="Add_btn" onclick="checkAll()">批量启用</a>
  <a class="delAll_btn" onclick="deleteAll()">批量删除</a>
  <a class="Add_btn" href="addcoupon.do?type=0">新增</a></s:if>
  <form action="deletecouponAllById.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>链接</th>
		<th>类型</th>
		<th>优惠券名称</th>
		<th>使用说明</th>
		<th>优惠券数量</th>
		<th>指定商品</th>
		<th>审核状态</th>
		<th>有效期开始时间</th>
		<th>有效期结束时间</th>
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
		queryData("couponListCount.do", "couponListAll.do?randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var ck = "";
		<s:if test="#session.role.roleId!=7">
		if(this.status == 0){
			ck = "<a style='text-decoration:none;color:blue;' onclick=check('"+this.couponId+"')>【设置启用】</a>";
		} else if(this.status == 1){
			ck = "<a style='text-decoration:none;color:blue;' onclick=uncheck('"+this.couponId+"')>【设置禁用】</a>";
		}
		</s:if>
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.couponId +"   /></td>"+
				"<td><a href='"+ this.url + "' target='_blank'>"+ this.url + "</a></td>"+
				"<td>"+ this.typeName + "</td>"+
				"<td>"+ this.name + "</td>"+
				"<td>"+ this.caption + "</td>"+
				"<td>"+ this.sheetNum + "</td>"+
				"<td>"+ this.productName + "</td>"+
				"<td>"+ this.statusName + ck + "</td>"+
				"<td>"+ this.validStimeDStr + "</td>"+
				"<td>"+ this.validEtimeDStr + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td>"+
				"<a class='edit_btn' href='updatecoupon.do?couponPojo.couponId="+this.couponId +"'>编辑</a>"+
				"<a class='del_btn' onclick=del('"+this.couponId+"')>删除</a></td></s:if>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"couponListAll.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>