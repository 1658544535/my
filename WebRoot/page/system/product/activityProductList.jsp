<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
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
	function del(val){
		if(confirm("你真的想删除该记录么？")){
			var url = "deleProduct.do?activityProductPojo.id="+val;	
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
			queryData("getCountProduct.do?type=${type}&titleId=${titleId}&title=${title}", "getProductAll.do?type=${type}&titleId=${titleId}&title=${title}&randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}	
	function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkActivityProduct.do?activityProductPojo.id="+val;	
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
			queryData("getActivityProductCount.do?type=${type}&titleId=${titleId}&title=${title}", "getActivityProductList.do?type=${type}&titleId=${titleId}&title=${title}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}	
	function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckActivityProduct.do?activityProductPojo.id="+val;	
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
			queryData("getActivityProductCount.do?type=${type}&titleId=${titleId}&title=${title}", "getActivityProductList.do?type=${type}&titleId=${titleId}&title=${title}&randdelete="+rand);
		}else{
			alert("取消审核失败");
		}
	}
	/*删除*/
	function del(val1) {
		if (confirm("你真的想删除该记录么？")) {
			var url = "delActivityProduct.do?activityProductPojo.id=" + val1;
			doOpreator3(url, null);
		} else {
			return;
		}
	}
	function doOpreator3(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage3);
	}
	function goRefreshPage3(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("删除活动成功");
			queryData("getActivityProductCount.do?type=${type}&titleId=${titleId}&title=${title}", "getActivityProductList.do?type=${type}&titleId=${titleId}&title=${title}&randdelete="+rand);
		}else{
			alert("删除活动失败");
		}
	}
	function deleteAll(){
		$("#idform").attr("action","delActivityProductAll.do?type=${type}&titleId=${titleId}&title=${title}");
		$("#idform").submit();
	}
	
	function checkAll(){
		$("#idform").attr("action","checkActivityProductAll.do?type=${type}&titleId=${titleId}&title=${title}");
		$("#idform").submit();
	}
	</script>
</head>
<body>    
<div class="sub_wrap">
    <div class="s_nav"><a href="#">活动商品管理</a> &gt;<c:if test="${type==1}"><a href="#">每日10件活动设置</a></c:if><c:if test="${type==2}"><a href="#">限时秒杀活动设置</a></c:if><c:if test="${type==3}"><a href="#">大牌驾到活动设置</a></c:if><c:if test="${titleId!=null}"><a href="#">首页列表活动管理</a> &gt; <a href="">活动标题：${title}</a></c:if></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="getActivityProductList.do" method="post" id="sysform">
			<input type="hidden" name="activityProductPojo.type" value="${type}" id="activityProductPojo.type">
			<input type="hidden" name="activityProductPojo.titleId" value="${titleId}" id="activityProductPojo.titleId">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
					    <td align="right">商品ID：</td>
						<td><input type="text" name="activityProductPojo.productId"
								id="activityProductPojo.productId"
								value="<s:property value="activityProductPojo.productId"/>"></td>
						<td align="right">公司名：</td>
						<td><input type="text" name="activityProductPojo.name"
								id="activityProductPojo.name"
								value="<s:property value="activityProductPojo.name"/>"></td>					
						<td align="right">商品名称：</td>
						<td><input type="text" name="activityProductPojo.productName"
								id="activityProductPojo.productName"
								value="<s:property value="activityProductPojo.productName"/>"></td>
					</tr>
					<tr>
						<td align="right">商品状态：</td>
						<td><select name="activityProductPojo.proStatus" id="activityProductPojo.proStatus" class="floatLeft">
							<option value="">全部</option>
							<option value="0">下架</option>
							<option value="1">在售</option>
				    		</select>
				    	</td>
						<td align="right">审核状态：</td>
						<td><select name="activityProductPojo.status" id="activityProductPojo.status"  class="floatLeft">
							<option value="">全部</option>
							<s:iterator value="statusSysDictList">
								<option value="<s:property value="value"/>"><s:property value="name" />
								</option>
							</s:iterator>
				    		</select>
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
   <s:if test="#session.role.roleId!=7"><a class="delAll_btn" onclick="deleteAll()" >批量删除</a>
   <a class="Add_btn" onclick="checkAll()" >批量审核</a></s:if>
   <form action="getActivityProductList.do" id="idform"  method="post" >
   <table width="100%" border="0" class="Info_list_table">
   <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>商品ID</th>
		<th>公司名</th>
		<th>商品货号</th>
		<th>商品图片</th>
		<th>商品类型</th>
		<th>商品名称</th>
		<c:if test="${type==1}"><th>推荐语</th></c:if>
		<th>商品价格</th>
		<th>排序</th>
		<th>商品状态</th>
		<th>审核状态</th>
		<s:if test="#session.role.roleId!=7"><th width="10%">操作</th></s:if>
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
			queryData("getActivityProductCount.do?type=${type}&titleId=${titleId}&title=${title}", "getActivityProductList.do?type=${type}&titleId=${titleId}&title=${title}&randquery="+rand);
		}
	}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.productId + "</td>"+
				//"<td>"+ this.productNo + "</td>"+
				"<td>"+ this.name + "</td>"+
				"<td>"+ this.productNum + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.image + "' height='100px' /></td>"+
				"<td>"+ this.productTypeName + "</td>"+
				"<td>"+ this.productName + "</td>"+
				<c:if test="${type==1}">"<td>"+ this.recommendation + "</td>"+</c:if>
				"<td>"+ this.distributionPrice + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td>"+ this.proStatusName + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td><a class='edit_btn' onclick=check('"+this.id+"')>审核</a><a  class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核"+
				<c:if test="${type==2}">"<a class='edit_btn' href='goFindProduct.do?activityProductPojo.productId="+this.productId +"'>商品编辑</a>"+</c:if>
				"<a class='edit_btn' href='goFindActivityProductSet.do?activityProductPojo.productId="+this.productId +"&activityProductPojo.type=${type}&titleId=${titleId}'>活动设置</a>"+
				"<a class='del_btn' onclick=del('"+ this.id + "')>删除</a></td></s:if></tr>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>, "getActivityProductList.do?type=${type}&titleId=${titleId}&title=${title}");
		$("#query_btn").click(query);
	});
	
</script>