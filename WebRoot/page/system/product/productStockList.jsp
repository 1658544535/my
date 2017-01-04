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
		document.getElementById("idform").submit();
	}


	function del(val)
	{
		//alert(val);
		if(confirm("你真的想删除该记录么？"))
		{
			//alert(val);
			
			var url = "deleProductStock.do?productStockPojo.id="+val;	
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
			queryData("getCountProductStock.do?productStockPojo.productId=${productStockPojo.productId}", "getProductStockAll.do?productStockPojo.productId=${productStockPojo.productId}&page.rowCount=${page.rowCount}&randdelete="+rand);
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
			
			var url = "checkProductStock.do?productStockPojo.id="+val;	
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
			queryData("getCountProductStock.do?productStockPojo.productId=${productStockPojo.productId}", "getProductStockAll.do?productStockPojo.productId=${productStockPojo.productId}&page.rowCount=${page.rowCount}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
    <div class="s_nav"><a href="#">商品管理</a> &gt; <a href="#">商品SKU管理</a></div>
    
  <div class="h15"></div>
   <div>
   <a href="goproductStockAdd.do?productStockPojo.productId=<s:property value='productStockPojo.productId'/>" class="Add_btn">新增商品SKU</a>
  <a class="delAll_btn"  onclick="deleteAll()" >删除全部</a>
  <form action="productStockDeleteId.do?productStockPojo.productId=${productStockPojo.productId}" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>商品</th>
		<th>SKU</th>
		<th>库存编号</th>
		<th>重量</th>
		<th>尺寸</th>
		<th>颜色</th>
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
		queryData("getCountProductStock.do", "getProductStockAll.do?randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				"<td>"+ this.productName + "</td>"+
				"<td>"+ this.sku + "</td>"+
				"<td>"+ this.stockNo + "</td>"+
				"<td>"+ this.weight + "</td>"+
				"<td>"+ this.size + "</td>"+
				"<td>"+ this.color + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"</td><td><a  class='edit_btn' onclick=check('"+this.id+"')>审核</a><a  class='del_btn' onclick=del('"+this.id+"')>删除</a>"+
				"<a class='edit_btn' href='goFindProductStock.do?productStockPojo.id="+this.id +"'>编辑</a></td></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"getProductStockAll.do?productStockPojo.productId=<s:property value='productStockPojo.productId'/>&page.rowCount=<s:property value='page.rowCount'/>&randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>