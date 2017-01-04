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
	function checkAll(){
		$("#idform").attr("action","checkActivityPlaceAll.do?type=6&titleId=${titleId}");
		$("#idform").submit();
	}
	function del(val)
	{
		//alert(val);
		if(confirm("你真的想删除该记录么？"))
		{
			//alert(val);
			
			var url = "delActivityProduct.do?activityProductPojo.id="+val;	
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
			queryData("getActivityPlaceCount.do?types=6&titleId=${titleId}","getActivityProductList.do?type=6&titleIds=${titleId}&randdelete="+rand);
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
			queryData("getActivityPlaceCount.do?types=6&titleId=${titleId}","getActivityProductList.do?type=6&titleIds=${titleId}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	function uncheck(val)
	{
		if(confirm("确认要取消审核吗？"))
		{
			var url = "uncheckActivityProduct.do?activityProductPojo.id="+val;	
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
			queryData("getActivityPlaceCount.do?types=6&titleId=${titleId}","getActivityProductList.do?type=6&titleIds=${titleId}&randdelete="+rand);
		}else{
			alert("取消失败");
		}
	}	
	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">平台专题管理 </a>&gt;<a href="platformSpecialList.do">平台专题管理 </a>&gt;
<a href="goSettingPlatformSpecialPorduct.do?types=6&titleId=${titleId}">设置活动商品</a></div>
    <div class="Search_control">
	 <p>按条件查找</p>
	 <a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
	 </div>
		<!-- 查询开始 -->
    	<form action="goSettingPlatformSpecialPorduct.do?types=6&titleId=${titleId}" method="post" id="sysform">
   		 	<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">  
					<tr> 
					    <td align="right">商品名称：</td>
						<td><input type="text" name="activityProductPojo.productName" value=""></td>
					</tr>					    
				</table>
				<input type="hidden" name="page.pageNo"  id="pageNo" value=0>
				<div class="floatRight">
					<input id="query_btn" type="button"  class="submit_btn" value="查询" />
				</div>
					<div class="Clear"></div>
				</div>
		</form>
		<!-- 查询结束 -->
  <div class="h15"></div>
   <div>
   <a class="Add_btn" href='goAddPlatformSpecialPorduct.do?type=6&productPojo.productStatus=1&titleId=${titleId}'/>新增商品</a>
   <a class="Add_btn"  onclick="checkAll()" >批量审核</a>
<form action="" id="idform"  method="post" >
    <table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
    	<th>商品名称</th>
		<th>商品图片</th>
		<th>商品原价</th>
		<th>商品活动价</th>
		<th>排序</th>
		<th>审核状态</th>		
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
</div>
</body>
</html>

<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("getActivityPlaceCount.do?types=6&titleId=${titleId}", "getActivityProductList.do?type=6&titleIds=${titleId}&randquery="+rand);
	}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				"<td>"+ this.productName + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.image + "' height='100px' /></td>"+
				"<td>"+ this.sellingPrice + "</td>"+
				"<td>"+ this.distributionPrice + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td><a  class='edit_btn' onclick=check('"+this.id+"')>审核</a>"+
				"<a  class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a>"+
				"<a  class='del_btn' onclick=del('"+this.id+"')>删除</a></td>"+
				"</tr>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,"getActivityProductList.do?type=6&titleIds=${titleId}&randIni="+rand);
		$("#query_btn").click(query);
	});
</script>