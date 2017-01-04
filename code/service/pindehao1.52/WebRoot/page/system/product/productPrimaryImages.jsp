<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>>/js/system/help/helpCommon.js"></script>
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
			
			var url = "deleProductPrimaryImages.do?productPrimaryImages.productId=${productPrimaryImagesPojo.productId}&productPrimaryImages.id="+val;	
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
			queryData("getProductPrimaryImagesCount.do?productPrimaryImages.productId=${productPrimaryImagesPojo.productId }&productPrimaryImages.userId=${productPrimaryImagesPojo.userId }", "productPrimaryImagesAllList.do?productPrimaryImages.productId=${productPrimaryImagesPojo.productId}&productPrimaryImages.userId=${productPrimaryImagesPojo.userId }&randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="productManage.do?productPojo.userId=${productPrimaryImagesPojo.userId}">商品管理</a> &gt; <a href="#">商品原生图片管理</a>
		</div>
		<div class="Search_control" style="display: none">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="productPrimaryImages.do?productPrimaryImages.productId=${productPrimaryImagesPojo.productId }" method="post" id="sysform">

			<div id="search_show" style="display: none">
				<table width="100%" border="0" class="Search_table">
					<%-- <tr>
						<td align="right">角色编号：</td>
						<td><label><input type="text" name="productPrimaryImages.roleId"
								id="ticketRulePojo.ticketName"
								value="<s:property value="productPrimaryImages.roleId"/>"></label></td>

					</tr>
 --%>
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
			<a class="delAll_btn" onclick="deleteAll()">删除全部</a><a href="addProductPrimaryImages.do?productPrimaryImages.productId=${productPrimaryImagesPojo.productId }&productPrimaryImages.userId=${productPrimaryImagesPojo.userId }" class="Add_btn">新增</a>
			<form action="ProductPrimaryImagesDeleteId.do?productPrimaryImages.productId=${productPrimaryImagesPojo.productId }&productPrimaryImages.userId=${productPrimaryImagesPojo.userId }" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>焦点图片</th>
						<th>排序</th>
						<th>状态</th>
						<th>操作</th>
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
		queryData("getProductPrimaryImagesCount.do?productPrimaryImages.productId=${productPrimaryImagesPojo.productId }", "productPrimaryImagesAllList.do?productPrimaryImages.productId=${productPrimaryImagesPojo.productId }&randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td><td>" 
		        + "<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/productPrimaryImages/"+ this.images + "' height='100px' />" + 
		        "</td><td>" + this.sorting +
		        "</td><td>" + this.statusName
				+ "</td><td><a  class='del_btn' onclick=del('"+this.id+"')>删除</a>"
				+ "<a class='edit_btn' href='goFindProductPrimaryImages.do?productPrimaryImages.id="+this.id +"&productPrimaryImages.userId=${productPrimaryImagesPojo.userId }'>编辑</a></td></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"productPrimaryImagesAllList.do?productPrimaryImages.productId=${productPrimaryImagesPojo.productId }&randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>