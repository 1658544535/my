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
function deleteAll(){
		$("#idform").attr("action","delSpecialProductAll.do?specialProductPojo.specialId=${specialProductPojo.specialId}").submit();
	}
function checkAll(){
		$("#idform").attr("action","checkSpecialProductAll.do?specialProductPojo.specialId=${specialProductPojo.specialId}").submit();
	}
function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkSpecialProduct.do?specialProductPojo.specialId=${specialProductPojo.specialId}&specialProductPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}	
function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckSpecialProduct.do?specialProductPojo.specialId=${specialProductPojo.specialId}&specialProductPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
function del(val){
		if(confirm("确认要删除此条吗？")){
			var url = "delSpecialProduct.do?specialProductPojo.specialId=${specialProductPojo.specialId}&specialProductPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}		
</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="specialShowList.do">专场详情列表</a> &gt; <a href="#">专场商品详细列表</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="specialProductList.do" method="post" id="sysform" enctype="multipart/form-data">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
				        <tr>
						<td align="right">商品名称：</td>
						<td><input type="text" name="specialProductPojo.productName" id="specialProductPojo.productName" value=""></td> 
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
<s:if test="#session.role.roleId!=7"><a class="delAll_btn" onclick="deleteAll()">批量删除</a>
<a class="Add_btn" onclick="checkAll()">批量审核</a>
<a class="Add_btn" href="specialProductAddInfo.do?activityId=${activityId }&specialId=${specialProductPojo.specialId}&productPojo.productStatus=1&productPojo.userBrandId=${specialProductPojo.userBrandId}" >新增专场商品</a></s:if>
 <form action=".do" id="idform"  method="post" >
  <table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<!--<th>商品信息</th>
		<th>专场名称</th>
		<th>商品ID</th>
		<th>商品名称</th>
		<th>商品图片</th>
		<th>商品标题</th>
		<th>商品原价</th>
		<th>商品活动价</th>
		<th>折扣</th>
		<th>商品总量</th>
		<th>商品库存</th>
		<th>排序</th>
		<th>是否推荐</th>
		<th>审核状态</th>-->
		<th width="500px">商品信息</th>
		<th>专场名称</th>
		<th>商品ID</th>
		<th>商品原价</th>
		<th>商品活动价</th>
		<th>折扣</th>
		<th>商品总量</th>
		<th>商品库存</th>
		<th>排序</th>
		<!-- <th>是否推荐</th> -->
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
		queryData("specialProductListCount.do?specialProductPojo.specialId=${specialProductPojo.specialId}", "specialProductListAll.do?specialProductPojo.specialId=${specialProductPojo.specialId}&randquery="+rand);
	}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		/*$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"    /></td>"+
				"<td>"+ this.specialName + "</td>"+
				"<td>"+ this.productId + "</td>"+
				"<td>"+ this.productName + "</td>"+
				"<td><img src='/upfiles/product/small/"+ this.image + "' height='100px'/></td>"+
				//"<td>"+ this.title + "</td>"+
				"<td>"+ this.sellPrice + "</td>"+
				"<td>"+ this.specialPrice + "</td>"+
				"<td>"+ this.tips + "</td>"+
				"<td>"+ this.specialNum + "</td>"+
				"<td>"+ this.specialStock + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				//"<td>"+ this.isIntroduceName + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td width=20%><s:if test="#session.role.roleId!=7">"+
				"<a class='edit_btn' href='productSkuLinkManage.do?productSkuLinkPojo.type=1&productSkuLinkPojo.activityId=${activityId}&productSkuLinkPojo.productId="+this.productId +"'>设置SKU</a>"+
				"<a class='edit_btn' href='/goPreviewWeb.do?productPojo.id="+this.productId +"'>商品预览</a><br/>"+
				"<a class='edit_btn' onclick=check("+this.id+")>审核通过 </a>"+
				"<a class='edit_btn' onclick=uncheck("+this.id+")>取消审核</a>"+
				"<a class='edit_btn' href='goUpdateSpecialProduct.do?specialProductPojo.Id="+this.id +"'>编辑</a>"+
				"<a class='del_btn'  onclick=del("+this.id+")>删除</a></s:if></td>");*/
				
				
				
		$("#body").append("<tr><td><input  name='tids' type='checkbox' value="+this.id +"    /></td>"+
			"<td><table width='100%' border='0' class='inTable'>"+
	  			"<tr><td colspan='3' style='text-align:left;'>"+ this.productName +"</td></tr>"+
	  			"<tr><td rowspan='4'><a href='goFindProduct.do?productPojo.id="+this.productId+"' target='_blank'><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/small/"+ this.image +"' width='100px' height='100px'/></a></td>"+
	  			"<td style='text-align:left;'>序号：</td>"+
	  			"<td style='text-align:left;'>"+ this.productNo +"</td></tr>"+
	  			"<tr><td style='text-align:left;'>货号：</td>"+
	  			"<td style='text-align:left;'>"+ this.productNum +"</td></tr>"+
	  			"<tr><td style='text-align:left;'>品牌：</td>"+
	  			"<td style='text-align:left;'>"+ this.brandName +"</td></tr>"+
	  			"<tr><td style='text-align:left;'>状态：</td>"+
	  			"<td style='text-align:left;'>"+ this.statusName +"</td></tr></table></td>"+
				"<td>"+ this.specialName + "</td>"+
				"<td>"+ this.productId + "</td>"+
				"<td>"+ this.sellPrice + "</td>"+
				"<td>"+ this.specialPrice + "</td>"+
				"<td>"+ this.tips + "</td>"+
				"<td>"+ this.specialNum + "</td>"+
				"<td>"+ this.specialStock + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				//"<td>"+ this.statusName + "</td>"+
				"<td width=20%><s:if test="#session.role.roleId!=7">"+
				"<a class='edit_btn' href='productSkuLinkManage.do?productSkuLinkPojo.type=1&productSkuLinkPojo.activityId=${activityId}&productSkuLinkPojo.productId="+this.productId +"'>设置SKU</a>"+
				"<a class='edit_btn' target='_blank' href='/goPreviewWeb.do?productSkuLinkPojo.activityId=${activityId}&productPojo.id="+this.productId +"'>商品预览</a><br/>"+
				"<a class='edit_btn' onclick=check("+this.id+")>审核通过 </a>"+
				"<a class='edit_btn' onclick=uncheck("+this.id+")>取消审核</a>"+
				"<a class='edit_btn' href='goUpdateSpecialProduct.do?specialProductPojo.Id="+this.id +"'>编辑</a>"+
				"<a class='del_btn'  onclick=del("+this.id+")>删除</a>"+
				"<a class='edit_btn' href='walletGoodsAdd.do?op=2&sellPrice="+this.sellPrice+"&activityGoodsPojo.productId="+this.productId +"')>设置为钱包专区商品</a></s:if></td>"+
                "</tr>");
				
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"specialProductListAll.do?specialProductPojo.specialId=${specialProductPojo.specialId}&randIni="+rand);		
		$("#query_btn").click(query);
	});	
</script>