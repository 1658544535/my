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
	
	function doOpreator(url, params, callBackFn) {
		MAOWU.ajax.get(url, params, callBackFn);
	}

	function deleteAll(){
		document.getElementById("idform").submit();
	}


	function del(val){
		if(confirm("你真的想删除该记录么？"))
		{
			var url = "delUserCoupon.do?userCouponPojo.couponNo="+val;	
			doOpreator(url,null,goRefreshPage1);
		}else{
			return ;
		}
	}
	/*刷新*/
	function goRefreshPage1(result) {
		var rand = Math.random() * (100000 + 1);
		if (result =="1") {
			alert("删除成功");//浏览器阻止创建更多的的多画框对话框
			queryData("userCouponListCount.do", "userCouponListAll.do?randquery="+rand);
		} else {
			alert("删除成功");
		}
	}
	
	function checkAll(){
		$("#idform").attr("action",".do").submit();
	}
	function check(val){
		if(confirm("确认要启用吗？")){
			var url = "checkUserCoupon.do?userCouponPojo.couponNo="+val;
			doOpreator(url,null,goRefreshPage2);
		}else{
			return ;
		}
	}

	/*刷新*/
	function goRefreshPage2(result) {
		var rand = Math.random() * (100000 + 1);
		if (result =="1") {
			alert("启用成功");//浏览器阻止创建更多的的多画框对话框
			queryData("userCouponListCount.do", "userCouponListAll.do?randquery="+rand);
		} else {
			alert("启用失败");
		}
	}
	function uncheck(val){
		if(confirm("确认要禁用吗？")){
			var url = "uncheckUserCoupon.do?userCouponPojo.couponNo="+val;
			doOpreator(url,null,goRefreshPage3);
		}else{
			return ;
		}
	}

	/*刷新*/
	function goRefreshPage3(result) {
		var rand = Math.random() * (100000 + 1);
		if (result =="1") {
			alert("禁用成功");//浏览器阻止创建更多的的多画框对话框
			queryData("userCouponListCount.do", "userCouponListAll.do?randquery="+rand);
		} else {
			alert("禁用失败");
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">商品优惠管理</a> &gt; <a href="#">用户优惠券列表</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="userCouponList.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">类型：</td>
						<td><select name="userCouponPojo.type" id="userCouponPojo.type"  class="floatLeft">
							<option value="">全部</option>
							<option value="0">兑换商品</option>
							<option value="1">满m减n金额</option>
							<option value="2">直减金额</option>
				    		</select></td>
				    	<td align="right">优惠券状态：</td>
						<td><select name="userCouponPojo.status" id="userCouponPojo.status"  class="floatLeft">
							<option value="">全部</option>
							<option value="0">禁用</option>
							<option value="1">启用</option>
							</select></td>
						<td align="right">使用状态：</td>
						<td><select name="userCouponPojo.used" id="userCouponPojo.used"  class="floatLeft">
							<option value="">全部</option>
							<option value="0">未使用</option>
							<option value="1">已使用</option>
							</select></td>
					</tr>
					<tr>
					    <td align="right">优惠券码：</td>
						<td><label><input type="text" name="userCouponPojo.couponNo"
								id="userCouponPojo.couponNo"
								value=""></label></td>
						<td align="right">生成时间：</td>
						<td>
								<input id="g" name="userCouponPojo.genTimeDStr" value="${userCouponPojo.genTimeDStr }" class="Wdate" type="text" 
								onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd'})"/>
								</td>
						<td align="right">使用时间：</td>
						<td>
								<input id="u" name="userCouponPojo.useTimeDStr" value="${userCouponPojo.useTimeDStr }" class="Wdate" type="text" 
								onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd'})"/>
						</td>
					</tr>
					<tr>
					    <td align="right">优惠券名：</td>
						<td><label><input type="text" name="userCouponPojo.couponName" id="userCouponPojo.couponName" value=""></label></td>
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
  <a href="goAddUserCoupon.do" class="Add_btn">新增优惠券</a>
  <a href="goAddUserCouponBatch.do" class="Add_btn">批量新增优惠券</a>
  </s:if>
  <form action=".do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>用户名称</th>
		<th>优惠券码</th>
		<th>优惠券名称</th>
		<th>类型</th>
		<th>优惠券状态</th>
		<th>使用状态</th>
		<th>有效开始时间</th>
		<th>有效结束时间</th>
		<th>生成时间</th>
		<th>使用时间</th>
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
		queryData("userCouponListCount.do", "userCouponListAll.do?randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		if(this.used == 0){
			var inner = "<td><a class='edit_btn' onclick=check('"+this.couponNo+"')>启用</a>"+
				"<a class='edit_btn' onclick=uncheck('"+this.couponNo+"')>禁用</a>"+
				"<a class='del_btn' onclick=del('"+this.couponNo+"')>删除</a>"+
				"<a class='edit_btn' href='goUpdateUserCoupon.do?userCouponPojo.couponNo="+this.couponNo +"'>编辑</a></td>";
		} else {
			var inner = "<td></td>";
		}
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value=''   /></td>"+
				"<td>"+ this.userName + "</td>"+
				"<td>"+ this.couponNo + "</td>"+
				"<td>"+ this.couponName + "</td>"+
				"<td>"+ this.typeName + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ this.usedName + "</td>"+
				"<td>"+ this.validstimeStr + "</td>"+
				"<td>"+ this.validetimeStr + "</td>"+
				"<td>"+ this.genTimeDStr + "</td>"+
				"<td>"+ this.useTimeDStr + "</td><s:if test="#session.role.roleId!=7">"+ inner +"</s:if>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userCouponListAll.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>