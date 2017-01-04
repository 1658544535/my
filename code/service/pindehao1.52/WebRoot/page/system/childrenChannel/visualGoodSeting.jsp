<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<style>
.inTable{border-collapse:collapse}
.inTable td,.inTable tr{border:none!important;background:none!important;padding:5px 5px!important;}
</style>
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
function setAll(){
		$("#idform").attr("action","setSpecialProductAll.do").submit();
	}
function set(val){
		if(confirm("确认要设置此商品为视觉商品吗？")){
			var url = "setSpecialProduct.do?specialProductPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}	
function unset(val){
		if(confirm("确认要取消此商品的视觉商品资格吗？")){
			var url = "unsetSpecialProduct.do?specialProductPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}		
</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a>少儿频道</a> &gt; <a href="visualGoodSeting.do">视觉商品设置</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="visualGoodSeting.do" method="post" id="sysform" enctype="multipart/form-data">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
				        <tr>
						<td align="right">商品名称：</td>
						<td><input type="text" name="specialProductPojo.productName" id="specialProductPojo.productName" value=""></td> 
						<td align="right">专场标题：</td>
						<td><input type="text" name="specialProductPojo.title" id="specialProductPojo.title" value=""></td> 
						<td align="right">视觉商品：</td>
						<td><select name="specialProductPojo.visualGoods" id="specialProductPojo.visualGoods"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">否</option>
							<option value="1">是</option>
				    		</select></td>
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
<s:if test="#session.role.roleId!=7"><a class="Add_btn" onclick="setAll()">批量设置为视觉商品</a>
</s:if>
 <form action=".do" id="idform"  method="post" >
  <table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th width="500px">商品信息</th>
		<th>专场标题</th>
		<th>商品ID</th>
		<th>商品原价</th>
		<th>商品活动价</th>
		<th>折扣</th>
		<th>商品总量</th>
		<th>商品库存</th>
		<th>排序</th>
		<th>视觉商品</th>
		<s:if test="#session.role.roleId!=7"><th width=20%>操作</th></s:if>	
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
var pagecount = ${page.rowCount}; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("visualGoodSetingCount.do", "visualGoodSetingAll.do?randquery="+rand);
	}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {	
		var op ="";
		if(this.visualGoods == 0){
			op = "<a class='edit_btn' onclick=set("+this.id+")>设置为视觉商品 </a>";
			}else if(this.visualGoods == 1){
			op = "<a class='edit_btn' onclick=unset("+this.id+")>取消视觉商品资格</a>"
			}	
		$("#body").append("<tr><td><input  name='tids' type='checkbox' value="+this.id +"    /></td>"+
			"<td><table width='100%' border='0' class='inTable'>"+
	  			"<tr><td colspan='3' style='text-align:left;'>"+ this.productName +"</td></tr>"+
	  			"<tr><td rowspan='4'><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/small/"+ this.image +"' width='100px' height='100px'/></td>"+
	  			"<td style='text-align:left;'>序号：</td>"+
	  			"<td style='text-align:left;'>"+ this.productNo +"</td></tr>"+
	  			"<tr><td style='text-align:left;'>货号：</td>"+
	  			"<td style='text-align:left;'>"+ this.productNum +"</td></tr>"+
	  			"<tr><td style='text-align:left;'>品牌：</td>"+
	  			"<td style='text-align:left;'>"+ this.brandName +"</td></tr>"+
	  			"<tr><td style='text-align:left;'>状态：</td>"+
	  			"<td style='text-align:left;'>"+ this.statusName +"</td></tr></table></td>"+
				"<td>"+ this.title + "</td>"+
				"<td>"+ this.productId + "</td>"+
				"<td>"+ this.sellPrice + "</td>"+
				"<td>"+ this.specialPrice + "</td>"+
				"<td>"+ this.tips + "</td>"+
				"<td>"+ this.specialNum + "</td>"+
				"<td>"+ this.specialStock + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td>"+ this.visualGoodsName + "</td>"+
				"<td width=20%><s:if test="#session.role.roleId!=7">"+
				op+
				"</s:if></td>"+
                "</tr>");
				
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,"visualGoodSetingAll.do?randIni="+rand);		
		$("#query_btn").click(query);
	});	
</script>