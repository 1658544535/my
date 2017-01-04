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

function addAll(){
	$("#idform").attr("action","agencyCollectAddAll.do?t=${t}&agency.agencyId=${agency.agencyId}&agency.manufacturerId=${agency.manufacturerId}").submit();
}
function insertAll(){
	$("#idform").attr("action","insertActivityGoodsAll.do?t=${t}&timeId=${timeId}&activityTime=${activityTime}&activityDate=${activityDate}&typeName=${typeName}");
	$("#idform").submit();
}
function appendAll(){
	$("#idform").attr("action","setActivityProductAll.do?t=${t}&activityProductPojo.type=${type}&activityProductPojo.remarks=${titleId}");
	$("#idform").submit();
}
</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav">
<c:if test="${timeId==null && titleId==null}"><a href="#">批发商管理</a> &gt; <a href="agency.do">账户管理</a> &gt; <a href="#">新增代理管理</a></c:if>
<c:if test="${timeId!=null}">
<c:if test="${t == 2}"><a href="thematicActivityManage.do?op=2">活动商品管理</a> &gt;<a href="#">添加钱包专区商品</a></c:if>
<c:if test="${t != 2}"><a href="activityTimeManage.do?op=1">活动商品管理</a> &gt;<a href="#">添加[${activityDate}]限时秒杀活动商品</a></c:if>
</c:if>
<c:if test="${titleId!=null}"><a href="#">活动商品管理</a> &gt; <a href="#">添加首页列表活动商品</a> &gt; <a href="#">活动标题：${title}</a></c:if>
</div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">商品序号：</td>
						<td><label><input type="text" name="productPojo.id"
								id="productPojo.id"
								value="<s:property value="productPojo.id"/>"></label></td>
					    <td align="right">公司名：</td>
						<td><label><input type="text" name="productPojo.shopName"
								id="productPojo.shopName"
								value="<s:property value="productPojo.shopName"/>"></label></td>
						<td align="right">商品名称：</td>
						<td><label><input type="text" name="productPojo.productName"
								id="productPojo.productName"
								value="<s:property value="productPojo.productName"/>"></label></td>
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
   <div><s:if test="#session.role.roleId!=7">
  <c:if test="${timeId==null && titleId==null}"><a class="Add_btn"  onclick="addAll()" >批量代理商品</a></c:if>
  <c:if test="${timeId!=null}"><a class="Add_btn"  onclick="insertAll()" >批量添加活动商品(${activityTime})</a></c:if>
  <c:if test="${titleId!=null}"><a class="Add_btn"  onclick="appendAll()" >批量添加首页活动商品</a></c:if></s:if>
  <form action="" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" >
		<!--<input type="hidden" name="shopPojo.userId"
								id="shopPojo.userId"
								value="${shopPojo.userId }">--></th>
		<th>商品序号</th>
		<!--<th>商品编号</th>-->
		<th>公司名</th>
		<th>商品货号</th>
		<th>商品图片</th>
		<th>商品类型</th>
		<th>商品名称</th>
		<th>商品价格</th>
		<th>状态</th>
		<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>
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
		//$("#sysform").attr("action","addAgencyCollect.do?agency.agencyId=${agency.agencyId}&agency.manufacturerId=${agency.manufacturerId}").submit();
		queryData("addAgencyCollectCount.do?agency.agencyId=${agency.agencyId}&agency.manufacturerId=${agency.manufacturerId}", "agencyCollectAddManage.do?shopPojo.userId=${shopPojo.userId}&titleId=${titleId}&randIni="+rand);
		
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value='"+this.id +"'   /></td>"+
				"<td>"+ this.productNo + "</td>"+
				"<td>"+ this.shopName + "</td>"+
				"<td>"+ this.productNum + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.image + "' height='100px' /></td>"+
				"<td>"+ this.productTypeName + "</td>"+
				"<td>"+ this.productName + "</td>"+
				"<td>"+ this.distributionPrice + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td>"+
				<c:if test="${timeId==null&&titleId==null}">
				"<a class='edit_btn' href='agencyCollectAddOne.do?productPojo.id="+this.id+"&agency.agencyId=${agency.agencyId}&agency.manufacturerId=${agency.manufacturerId}'>代理商品</a>"
				</c:if>
				<c:if test="${timeId!=null}">
				<c:if test="${t == 2}">
				"<a class='edit_btn' href='activityGoodsAdd.do?op=2&typeName=${typeName}&productId="+this.id +"&timeId=${timeId}&productName="+this.productName +"&sellPrice="+this.sellingPrice +"&activityTime=${activityTime}'>添加到活动</a>"
				</c:if>
				<c:if test="${t != 2}">
				"<a class='edit_btn' href='activityGoodsAdd.do?op=1&productId="+this.id +"&timeId=${timeId}&productName="+this.productName +"&sellPrice="+this.sellingPrice +"&activityTime=${activityTime}&activityDate=${activityDate}'>添加到活动</a>"
				</c:if>
				</c:if>
				<c:if test="${titleId!=null}">
				"<a class='edit_btn' href='setActivityProduct.do?activityProductPojo.productId="+this.id+"&activityProductPojo.remarks=${titleId}&activityProductPojo.type=4'>添加到首页活动</a>"
				</c:if>
				+"</td></s:if></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>, "agencyCollectAddManage.do?type=${type}&shopPojo.userId=${shopPojo.userId}&agency.agencyId=${agency.agencyId}&titleId=${titleId}&randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>