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
		$("#idform").attr("action","productSkuLinkDeleteId.do?productSkuLinkPojo.type=${productSkuLinkPojo.type}&productSkuLinkPojo.activityId=${productSkuLinkPojo.activityId}&productSkuLinkPojo.productId=<s:property value='productSkuLinkPojo.productId'/>");
		$("#idform").submit();
	}


	function del(val)
	{
		//alert(val);
		if(confirm("你真的想删除该记录么？"))
		{
			//alert(val);
			
			var url = "deleProductSkuLink.do?productSkuLinkPojo.id="+val;	
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
			queryData("getProductSkuLinkCount.do?productSkuLinkPojo.type=${productSkuLinkPojo.type}&productSkuLinkPojo.activityId=${productSkuLinkPojo.activityId}&productSkuLinkPojo.productId=${productSkuLinkPojo.productId}", "getProductSkuLinkAll.do?productSkuLinkPojo.type=${productSkuLinkPojo.type}&productSkuLinkPojo.activityId=${productSkuLinkPojo.activityId}&productSkuLinkPojo.productId=${productSkuLinkPojo.productId}&page.rowCount=${page.rowCount}&randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	
	// function checkAll(){
	//	document.getElementById("idform").submit();
	// }
	function checkAll(){
		$("#idform").attr("action","productSkuLinkCheckId.do?productSkuLinkPojo.type=${productSkuLinkPojo.type}&productSkuLinkPojo.activityId=${productSkuLinkPojo.activityId}&productSkuLinkPojo.productId=<s:property value='productSkuLinkPojo.productId'/>");
		$("#idform").submit();
	}
	
	function check(val)
	{
		//alert(val);
		if(confirm("确认要通过审核吗？"))
		{
			//alert(val);
			
			var url = "checkProductSkuLink.do?productSkuLinkPojo.id="+val;	
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
			queryData("getProductSkuLinkCount.do?productSkuLinkPojo.type=${productSkuLinkPojo.type}&productSkuLinkPojo.activityId=${productSkuLinkPojo.activityId}&productSkuLinkPojo.productId=${productSkuLinkPojo.productId}", "getProductSkuLinkAll.do?productSkuLinkPojo.type=${productSkuLinkPojo.type}&productSkuLinkPojo.activityId=${productSkuLinkPojo.activityId}&productSkuLinkPojo.productId=${productSkuLinkPojo.productId}&page.rowCount=${page.rowCount}&randdelete="+rand);
		}else{
			alert("审核失败，颜色与规格必须都填写");
		}
	}
	
	function uncheck(val)
	{
		if(confirm("确认要取消审核吗？"))
		{
			var url = "uncheckProductSkuLink.do?productSkuLinkPojo.id="+val;	
			doOpreator3(url,null);
		}else{
			return ;
		}
	}
	function doOpreator3(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage3);
	}
	 
	function goRefreshPage3(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("取消成功");
			queryData("getProductSkuLinkCount.do?productSkuLinkPojo.type=${productSkuLinkPojo.type}&productSkuLinkPojo.activityId=${productSkuLinkPojo.activityId}&productSkuLinkPojo.productId=${productSkuLinkPojo.productId}", "getProductSkuLinkAll.do?productSkuLinkPojo.type=${productSkuLinkPojo.type}&productSkuLinkPojo.activityId=${productSkuLinkPojo.activityId}&productSkuLinkPojo.productId=${productSkuLinkPojo.productId}&page.rowCount=${page.rowCount}&randdelete="+rand);
		}else{
			alert("取消失败");
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
    <div class="s_nav"><a href="productManage.do">商品管理</a> &gt; <a href="#"><c:if test="${productSkuLinkPojo.activityId > 0}">活动</c:if>商品SKU管理</a></div>
    <form hidden="true" action="getProductSkuLinkAll.do?productSkuLinkPojo.productId=<s:property value='productSkuLinkPojo.productId'/>" method="post" id="sysform">
			<div id="search_show" style="">
				<input type="hidden" name="page.pageNo"  id="pageNo">
				<div class="floatRight">
					<input id="query_btn" type="button"  class="submit_btn" value="查询" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>
  <div class="h15"></div>
   <div>
   <a href="goproductSkuLinkAdd.do?productSkuLinkPojo.type=<s:property value='productSkuLinkPojo.type'/>&productSkuLinkPojo.activityId=<s:property value='productSkuLinkPojo.activityId'/>&productSkuLinkPojo.productId=<s:property value='productSkuLinkPojo.productId'/>" class="Add_btn">新增SKU</a>
   <a class="Add_btn" href="goAddSkuColor.do?productSkuLinkPojo.type=<s:property value='productSkuLinkPojo.type'/>&productSkuLinkPojo.activityId=<s:property value='productSkuLinkPojo.activityId'/>&productSkuLinkPojo.productId=${productSkuLinkPojo.productId}" >新增颜色/规格</a>
   <a class="Add_btn" href="goSkuAttributeUpdate.do?productSkuLinkPojo.type=<s:property value='productSkuLinkPojo.type'/>&productSkuLinkPojo.activityId=<s:property value='productSkuLinkPojo.activityId'/>&productSkuLinkPojo.productId=${productSkuLinkPojo.productId}" >修改颜色/规格</a>
   <a class="Add_btn"  onclick="checkAll()" >审核全部</a>
   <a class="delAll_btn"  onclick="deleteAll()" >删除全部</a>
  <form action="" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
    	<!--<th>商品编号</th>-->
    	<th>商品名称</th>
		<th>颜色</th>
		<th>规格</th>
		<th>库存总量</th>
		<th>剩余库存</th>
		<th>价格</th>
		<th>是否活动</th>
		<th>商家编码</th>
		<!--<th>排序</th>-->
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
		queryData("getProductSkuLinkCount.do", "getProductSkuLinkAll.do?randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		if(this.colorValue != '' && this.formatValue != ''){
			var html = "<a  class='edit_btn' onclick=check('"+this.id+"')>审核</a>";
		} else {
			var html = "";
		}
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				//"<td>"+ this.productId + "</td>"+
				"<td>"+ this.productName + "</td>"+
				"<td>"+ this.colorValue + "</td>"+
				"<td>"+ this.formatValue + "</td>"+
				"<td>"+ this.stockNum + "</td>"+
				"<td>"+ this.stock + "</td>"+
				"<td>"+ this.price + "</td>"+
				"<td>"+ this.typeName + "</td>"+
				"<td>"+ this.businessCode + "</td>"+
				//"<td>"+ this.sorting + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				//"<td><a  class='edit_btn' onclick=check('"+this.id+"')>审核</a>"+
				"<td>" + html + 
				"<a  class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a>"+
				"<a  class='del_btn' onclick=del('"+this.id+"')>删除</a>"+
				"<a class='edit_btn' href='goFindProductSkuLink.do?productSkuLinkPojo.id="+this.id +"&productSkuLinkPojo.productId="+this.productId +"'>编辑</a></td>"+
				"</tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		 document.getElementById("pageNo").value = pageNo;
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"getProductSkuLinkAll.do?productSkuLinkPojo.type=<s:property value='productSkuLinkPojo.type'/>&productSkuLinkPojo.activityId=<s:property value='productSkuLinkPojo.activityId'/>&productSkuLinkPojo.productId=<s:property value='productSkuLinkPojo.productId'/>&page.rowCount=<s:property value='page.rowCount'/>&randIni="+rand);
		
	});
	
</script>