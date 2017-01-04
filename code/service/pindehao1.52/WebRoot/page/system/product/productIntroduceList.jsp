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
			queryData("getCountProduct.do", "getProductAll.do?randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
    <div class="s_nav"><a href="productManage.do">商品管理</a> &gt; <a href="#">商品优先设置</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="productManage.do" method="post" id="sysform">

			<!-- <div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">商品名称：</td>
						<td><label><input type="text" name="productPojo.productName"
								id="productPojo.productName"
								value="<s:property value="productPojo.productName"/>"></label></td>
				</table>
				
				<div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>

				<div class="Clear"></div>
			</div>
			-->
			<input type="hidden" name="page.pageNo" value=0 id="pageNo">
		</form>
		<!-- 查询结束 -->
  <div class="h15"></div>
   <div>
  <form action="productDeleteId.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>所属用户</th>
		<th>商品图片</th>
		<th>商品名称</th>
		<th>商品类型</th>
		<th>是否推荐</th>
		<th>是否新品</th>
		<th>掌柜热卖</th>
		<th>推荐状态</th>
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
		queryData("getCountIndexProduct.do?productPojo.productTypeIds=<s:property value='productTypeIds'/>", "goSetIntroduceList.do?typeStr=<s:property value='typeStr'/>&oldId=<s:property value="oldId"/>&randquery="+rand);
	}
}

	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var recommendStr="";
		if(this.recommend==0){
			recommendStr="未推荐";
		}else if(this.recommend==1){
			recommendStr="首页新品推荐";
		}else if(this.recommend==2){
			recommendStr="首页楼层推荐";
		}
		$("#body").append(
				"<tr>"+
				"<td>"+ this.userName + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.image + "' height='100px' /></td>"+
				"<td>"+ this.productName + "</td>"+
				"<td>"+ this.productTypeName + "</td>"+
				"<td>"+ this.isIntroduceName + "</td>"+
				"<td>"+ this.isNewName + "</td>"+
				"<td>"+ this.isHotsaleName + "</td>"+
				"<td>"+ recommendStr + "</td>"+
				"</td><td><a class='edit_btn' href='updateTopFive.do?typeStr=<s:property value='typeStr'/>&oldId=<s:property value='oldId'/>&productPojo.id="+this.id +"'>替换</a></td></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"goSetIntroduceList.do?typeStr=<s:property value='typeStr'/>&oldId=<s:property value="oldId"/>&page.rowCount=<s:property value='page.rowCount'/>&randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>