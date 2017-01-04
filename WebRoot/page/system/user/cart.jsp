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

	function deleteAll(){
		document.getElementById("idform").submit();
	}


	function del(val)
	{
		//alert(val);
		if(confirm("你真的想删除该记录么？"))
		{
			//alert(val);
			
			var url = "deleCart.do?cart.id="+val;	
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
			queryData("getCartCount.do", "cartAllList.do?randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">订单管理</a> &gt; <a href="#">购物车管理</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="cart.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">商品名称：</td>
						<td><label><input type="text" name="cart.productName"
								value=""></label></td>
						<td align="right">来源渠道：</td>
						<td><select name="cart.channel" id="ticketType"
							class="floatLeft">
								<option value="">----请选择----</option>
								<c:forEach items="${channel}" var="channel">
										<option value="${channel.value}">${channel.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right">商品货号：</td>
						<td><label><input type="text" name="cart.productNum"
								value=""></label></td>
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
			<s:if test="#session.role.roleId!=7"><a class="delAll_btn" onclick="deleteAll()">批量删除</a></s:if>
			<form action="CartDeleteId.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>用户昵称</th>
						<!-- <th>店铺名称</th> -->
						<th>商品图片</th>
						<th>商品名称</th>
						<th>商品货号</th>
						<!--<th>商品规格</th> -->
						<!--<th>商品原价</th>-->
						<th>商品价格</th>
						<th>数量</th>
						<!--<th>来源渠道</th>-->
						<th>创建时间</th>
						<!--<th>状态</th> -->
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
		queryData("getCartCount.do", "cartAllList.do?randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td><td>" 
		        + this.userName + 
		        //"</td><td>" + this.shopName+"</td>
		        "<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.productImage + "' height='80px' />"+
		        "</td><td>" + this.productName+
		        "</td><td>" + this.productNum+
		  <!--	"</td><td>" + this.productModel+ -->
		 <!--   "</td><td>" + this.stockPriceOld+  -->
		        "</td><td>" + this.stockPrice+
		        "</td><td>" + this.num+
		        //"</td><td>" + this.channelName+
		        "</td><td>" + this.createDateStr+
		   <!-- "</td><td>" + this.statusName + --> 
				"</td><s:if test="#session.role.roleId!=7"><td><a  class='del_btn' onclick=del('"+this.id+"')>删除</a>"
				+ "<a class='edit_btn' href='goFindCart.do?cart.id="+this.id +"&cart.userId="+this.userId+"'>编辑</a></td></s:if></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"cartAllList.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>