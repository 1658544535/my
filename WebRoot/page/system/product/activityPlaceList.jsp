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
		$("#idform").attr("action","delActivityPlaceAll.do?type=${type}&titleId=${titleId}");
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
			queryData("getActivityPlaceCount.do?type=${type}&titleId=${titleId}","getActivityPlaceList.do?type=${type}&titleId=${titleId}&randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	
	// function checkAll(){
	//	document.getElementById("idform").submit();
	// }
	function checkAll(){
		$("#idform").attr("action","checkActivityPlaceAll.do?type=${type}&titleId=${titleId}");
		$("#idform").submit();
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
			queryData("getActivityPlaceCount.do?type=${type}&titleId=${titleId}","getActivityPlaceList.do?type=${type}&titleId=${titleId}&randdelete="+rand);
		}else{
			alert("审核失败，颜色与规格必须都填写");
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
			queryData("getActivityPlaceCount.do?type=${type}&titleId=${titleId}","getActivityPlaceList.do?type=${type}&titleId=${titleId}&randdelete="+rand);
		}else{
			alert("取消失败");
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
    <div class="s_nav"><a href="productManage.do">活动商品管理</a> &gt;<a href="activityTitleManage.do?type=5"> web活动页管理</a>&gt; 添加活动商品</a></div>
    <form hidden="true" action="getActivityPlaceList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<input type="hidden" name="page.pageNo"  id="pageNo" value=0>
				<div class="floatRight">
					<input id="query_btn" type="button"  class="submit_btn" value="查询" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>
  <div class="h15"></div>
   <div>
   <a class="Add_btn" href='activityPlaceAdd.do?type=${type}&titleId=${titleId}'/>新增活动商品</a>
   <a class="Add_btn"  onclick="checkAll()" >审核全部</a>
   <a class="delAll_btn"  onclick="deleteAll()" >删除全部</a>
  <form action="" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
    	<th>展位类型</th>
		<th>专场ID</th>
		<th>商品ID</th>
		<th>状态</th>
		<th>排序</th>
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

</body>
</html>


	


<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("getActivityPlaceCount.do", "getActivityPlaceList.do?randquery="+rand);
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
				"<td>"+ this.productTypeName + "</td>"+
				"<td>"+ this.specialId + "</td>"+
				"<td>"+ this.productId + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				//"<td><a  class='edit_btn' onclick=check('"+this.id+"')>审核</a>"+                 
				"<td>" + html + 
				"<a  class='edit_btn' onclick=uncheck('"+this.id+"')>取消审核</a>"+
				"<a  class='del_btn' onclick=del('"+this.id+"')>删除</a>"+
				"<a class='edit_btn' href='activityPlaceById.do?type=${type}&titleId=${titleId}&id="+this.id+"'>编辑</a></td>"+
				"</tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		 
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"getActivityPlaceList.do?type=${type}&titleId=${titleId}&randIni="+rand);
		
	});
	
</script>