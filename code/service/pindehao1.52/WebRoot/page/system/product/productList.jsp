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
		$("#idform").attr("action","checkProductAll.do").submit();
	}


	function del(val)
	{
		//alert(val);
		if(confirm("你真的想删除该记录么？"))
		{
			//alert(val);
			
			var url = "deleProduct.do?productPojo.id="+val;	
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
			queryData("getCountProduct.do?productPojo.userId=3", "getProductAll.do?productPojo.userId=3&randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	
	function check(val)
	{
		//alert(val);
		if(confirm("确认要通过审核吗？"))
		{
			//alert(val);
			
			var url = "checkProduct.do?productPojo.id="+val;	
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
			queryData("getCountProduct.do?productPojo.userId=3", "getProductAll.do?productPojo.userId=3&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function uncheck(val)
	{
		//alert(val);
		if(confirm("确认要取消审核吗？"))
		{
			//alert(val);
			
			var url = "uncheckProduct.do?productPojo.id="+val;	
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
			queryData("getCountProduct.do?productPojo.userId=3", "getProductAll.do?productPojo.userId=3&randdelete="+rand);
		}else{
			alert("取消审核失败");
		}
	}
	function setActivity(val1, val2)
	{	
		var url = "setActivityProduct.do?activityProductPojo.productId="+val1+"&activityProductPojo.type="+val2;	
		doActivityOpreator(url,null);
	}
	function doActivityOpreator(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage3);
	}
	 
	function goRefreshPage3(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("设置成功");
			queryData("getCountProduct.do?productPojo.userId=3", "getProductAll.do?productPojo.userId=3&randdelete="+rand);
		}else{
			alert("设置失败");
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><c:if test="${!empty productPojo}"><a href="#">品牌管理</a> &gt; <a href="#">账户信息</a> &gt;</c:if><a href="#">商品管理</a> &gt; <a href="#">商品信息管理</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="productManage.do?productPojo.userId=3" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<%--<td align="right">公司名：</td>
						<td><label><input type="text" name="productPojo.remarks"
								id="productPojo.remarks"
								value="<s:property value="productPojo.remarks"/>"></label></td>
						--%>
						<td align="right">商品序号：</td>
						<td><label><input type="text" name="productPojo.productNo"
								id="productPojo.productNo"  
								value="<s:property value="productPojo.productNo"/>"></label></td>
						<td align="right">商品名称：</td>
						<td><label><input type="text" name="productPojo.productName"
								id="productPojo.productName"
								value="<s:property value="productPojo.productName"/>"></label></td>
					</tr>
					<tr>
					<td align="right">商品货号：</td>
						<td><label><input type="text" name="productPojo.productNum"
								id="productPojo.productNum"
								value="<s:property value="productPojo.productNum"/>"></label></td>
					<td align="right">商品状态：</td>
						<td><select name="productPojo.productStatus" id="productPojo.productStatus"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="1">已审核</option>
							<option value="0">未审核</option>
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
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
  <div class="h15"></div>
   <div>
  <s:if test="#session.role.roleId!=7"><a href="goproductAdd.do?productPojo.userId=3" class="Add_btn">新增商品</a>
  <a class="Add_btn"  onclick="checkAll()" >批量审核</a>
  <input type="button" value="导出EXCEL"  id="excel" class="submit_btn" style="float: right;"  /></s:if>
  <form action="productDeleteId.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>商品图片</th>
		<th>商品ID</th>
	<!--<th>商品编号</th>-->
	<!--<th>公司名</th>-->
		<th>商品序号</th>
		<th>商品货号</th>
		<th>商品类型</th>
	<!--<th>年龄描述</th>-->
	<!--<th>标签描述</th>-->
		<th>商品名称</th>
		<th>商品价格</th>
	<!--<th>建议零售价</th>
		<th>最低零售价</th>
		<th>起订量</th> -->
		<th>状态</th>
		<!-- <th>是否包邮</th>
		<th>重量</th> -->
		<s:if test="#session.role.roleId!=7"><th width="10%">图片管理</th>
		<!-- <th>SKU管理</th> -->
		<th width="100px">操作</th></s:if>
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
		queryData("getCountProduct.do?productPojo.userId=3", "getProductAll.do?productPojo.productTypeId=${productPojo.productTypeId}&productPojo.userId=3&randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.image + "' height='100px' /></td>"+
				"<td>"+ this.id + "</td>"+
				<%--"<td>"+ this.productNo + "</td>"+--%>
				//"<td>"+ this.name + "</td>"+
				"<td>"+ this.productNo + "</td>"+
				"<td>"+ this.productNum + "</td>"+
				"<td>"+ this.productTypeName + "</td>"+
			  //"<td>"+ this.ageDesc + "</td>"+
			  //"<td>"+ this.tag + "</td>"+
				"<td>"+ this.productName + "</td>"+
				"<td>"+ this.distributionPrice + "</td>"+
			  //"<td>"+ this.sellingPrice + "</td>"+
			  //"<td>"+ this.lowestPrice + "</td>"+
			  //"<td>"+ this.minimum + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				//+"<td>"+this.postageTypeName+"</td>"+
				//"<td>"+ this.weight + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td>"+
				//"<a class='edit_btn' href='productImagesManage.do?productImagesPojo.productId="+this.id+"&productImagesPojo.userId="+this.userId+"'>商品图片</a>"+
				"<a class='edit_btn' href='productFocusImages.do?productFocusImages.productId="+this.id+"&productFocusImages.userId="+this.userId+"'>焦点图片</a>"+
				//"<a class='edit_btn' href='productPrimaryImages.do?productPrimaryImages.productId="+this.id+"&productPrimaryImages.userId="+this.userId+"'>原生图片</a>"+"</td>"+
				//"<td><a class='edit_btn' href='productSkuLinkManage.do?productSkuLinkPojo.type=0&productSkuLinkPojo.activityId=0&productSkuLinkPojo.productId="+this.id+"'>管理</a></td>"+
				// "</td><td><a  class='edit_btn' onclick=check('"+this.id+"')>审核</a><a  class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a><a  class='del_btn' onclick=del('"+this.id+"')>删除</a>"+
				"</td><td><a  class='edit_btn' onclick=check('"+this.id+"')>审核</a><a  class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a>"+
				"<a class='edit_btn' href='goFindProduct.do?productPojo.id="+this.id +"'>编辑</a>"+
				"<a class='edit_btn' href='goFindProductContent.do?id="+this.id +"'>详情编辑</a>"+
				//"<a class='edit_btn' onclick=setActivity('"+this.id +"',1)>每日十件</a>"+
				//"<a class='edit_btn' href='activityGoodsAdd.do?productId="+this.id +"&timeId=${timeId}&productName="+this.productName +"&sellPrice="+this.distributionPrice +"'>限时秒杀</a>"+
				//"<a class='edit_btn' onclick=setActivity('"+this.id +"',3)>大牌驾到</a>"+
				"</td></s:if></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"getProductAll.do?productPojo.productTypeId=${productPojo.productTypeId}&productPojo.userId=3&page.rowCount=<s:property value='page.rowCount'/>&randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>