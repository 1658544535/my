<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
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

	function checkAll(){
		document.getElementById("idform").submit();
	}


	function check(val)
	{
		//alert(val);
		if(confirm("确认要通过审核吗？"))
		{
			//alert(val);
			
			var url = "checkShop.do?shop.id="+val;	
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
			alert("审核成功");
			queryData("getShopCount.do", "shopAllList.do?randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
		function selfSupport(val)
	{
		//alert(val);
		if(confirm("确认要设为自营吗？"))
		{
			//alert(val);
			
			var url = "setSelfSupport.do?shop.id="+val;	
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
			alert("设置成功");
			queryData("getShopCount.do", "shopAllList.do?randdelete="+rand);
		}else{
			alert("设置失败");
		}
	}
	function unSelfSupport(val)
	{
		//alert(val);
		if(confirm("确认要取消自营吗？"))
		{
			//alert(val);
			
			var url = "setUnSelfSupport.do?shop.id="+val;	
			doOpreator1(url,null);
		}else{
			return ;
		}
		
	}
	function doOpreator1(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage1);
	}
	 
	function goRefreshPage1(result){
		
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("取消成功");
			queryData("getShopCount.do", "shopAllList.do?randdelete="+rand);
		}else{
			alert("取消失败");
		}
	}
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">品牌管理</a> &gt; <a href="#">店铺信息</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="shop.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
				<!--	<td align="right">用户昵称：</td>
						<td><label><input type="text"
								name="shop.userName" value=""></label></td>   -->
						<td align="right">地址：</td>
						<td><label><input type="text"
								name="shop.address" value=""></label></td>
						<td align="right">销售市场：</td>
						<td><label><input type="text"
								name="shop.salesArea" value=""></label></td>
					</tr>
					<tr>
						<td align="right">店铺名称：</td>
						<td><label><input type="text"
								name="shop.name" value=""></label></td>
						<td align="right">新品推荐：</td>
						<td><select name="shop.isNew" id="ticketType"  class="floatLeft">
							<option value="">----请选择----</option>
							<c:forEach items="${isNew}" var="isNew">
										<option value="${isNew.value}">${isNew.name}</option>
								</c:forEach>
				    		</select><div id="scale_mgId"></div></td>
						<td align="right">主营品类：</td>
						<td><select name="shop.mainCategory" id="ticketType"  class="floatLeft">
							<option value="">----请选择----</option>
							<!-- <option value="7">0-3岁婴幼儿玩具</option>
					 		<option value="8">3-6岁儿童玩具</option>
					 		<option value="9">6岁以上玩具</option> -->
							<c:forEach items="${mainCategory}" var="mainCategory">
										<option value="${mainCategory.id}">${mainCategory.name}</option>
								</c:forEach>
				    		</select><div id="scale_mgId"></div></td>
						
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
			<s:if test="#session.role.roleId!=7"><a class="Add_btn" onclick="checkAll()">批量审核</a></s:if>
		<!--	<a class="Add_btn" href="addShop.do"> 新增店铺  </a>   -->
			<form action="shopChecks.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>品牌LOGO</th>
						<!--<th>账户昵称</th>-->
						<th>店铺名称</th>
						<th>地址</th>
						<th>主营商品</th>
						<th>主营品类</th>
						<th>销售市场</th>
						<th>联系号码</th>
						<th>新品推荐</th>
						<th>排序</th>
						<s:if test="#session.role.roleId!=7"><th>状态</th></s:if>
						<th>是否自营</th>
						<th width="10%">操作</th>
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
		queryData("getShopCount.do", "shopAllList.do?randquery="+rand);
	}
}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +" /></td><td>" 
		        + "<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/shop/"+ this.images + "' height='80px' />" + 
		      <!--"</td><td>" + this.userName+ -->
		        "</td><td>" + this.name+ 
		        "</td><td>" + this.address+ 
		        "</td><td>" + this.mainProduct+
		        "</td><td>" + this.mainCategoryName+
		        "</td><td>" + this.salesArea+
		        "</td><td>" + this.phone+
		        "</td><td>" + this.isNewName+
		        "</td><td>" + this.sorting+
		        "</td><td>" + this.statusName+
		        "</td><td>" + this.selfSupportName
				+ "</td><s:if test="#session.role.roleId!=7"><td><a  class='edit_btn' onclick=check('"+this.id+"')>审核</a>"
				+ "<a class='edit_btn' href='goFindShop.do?shop.id="+this.id +"'>编辑</a>"
				+ "<a class='edit_btn' href='delShop.do?shop.id="+this.id +"' onclick='javascript:return window.confirm(\"确定删除？\");'>删除</a>"
				+ "<a class='edit_btn' href='undercarriageShop.do?shop.id="+this.id +"&shop.userId="+this.userId+"' onclick='javascript:return window.confirm(\"确定下架？\");'>店铺下架</a>"
				+ "<a class='edit_btn' onclick=selfSupport('"+this.id+"')>设为自营</a>"
				+ "<a class='edit_btn' onclick=unSelfSupport('"+this.id+"')>取消自营</a></td></s:if></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"shopAllList.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>