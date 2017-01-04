<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<jsp:include page="../../common/top.jsp"></jsp:include>
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
function add(){
		$("#idform").attr("action","addPlatformSpecialPorductAll.do?titleId=${titleId}").submit();
	}
</script>
</head>
<body>
	<div class="sub_wrap">
	<c:if test="${productRecommendPojo.type == 1 }">
	<div class="s_nav"><a href="#">活动商品管理</a> &gt; <a href="goNewDaily.do?productRecommendPojo.type=1">每日上新</a></div>
	</c:if>
	<c:if test="${productRecommendPojo.type == 2 }">
	<div class="s_nav"><a href="#">活动商品管理</a> &gt; <a href="goSalesRanking.do?productRecommendPojo.type=2">销量排行</a></div>
	</c:if>
    <div class="Search_control">
	 <p>按条件查找</p>
	 <a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
	 </div>
		<!-- 查询开始 -->
    	<form action="goAddPlatformSpecialPorduct.do" method="post" id="sysform">
   		 	<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">  
					<tr> 
						<td align="right">公司名称：</td>
						<td><input type="text" name="productPojo.shopName" value=""></td>
					    <td align="right">商品序号：</td>
						<td><input type="text" name="productPojo.productNum" value=""></td>
					    <td align="right">商品名称：</td>
						<td><input type="text" name="productPojo.productName" value=""></td>
					</tr>	
					<tr> 
					    <td align="right">商品货号：</td>
						<td><input type="text" name="productPojo.productNo" value=""></td>
					    <td align="right">商品名称：</td>
						<td>
							<select name="productPojo.status">
								<option value="">---- 请选择 ----</option>
								<option value="0" >未审核</option>  
						        <option value="1" >已审核</option> 
							</select>
						</td>
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
<form action="" id="idform"  method="post" >
    <table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th>商品ID</th>
    	<th>公司名</th>
    	<th>商品序号</th>
		<th>商品货号</th>
		<th>商品图片</th>
		<th>商品类型</th>
    	<th>商品名称</th>
		<th>商品价格</th>
		<th>状态</th>		
		<th>是否包邮</th>	
		<th>重量</th>	
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
		queryData("getActProBucketCount.do", "getActProBucket.do?randquery="+rand);
		}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var postageTypeName = "";
		if(this.postageType == 0){
			postageTypeName = "不包邮";
		}else{
			postageTypeName = "包邮";
		}
		
		$("#body").append(
				"<tr><td>"+ this.id + "</td>"+
				"<td>"+ this.shopName + "</td>"+
				"<td>"+ this.productNo + "</td>"+
				"<td>"+ this.productNum + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.image + "' height='100px' /></td>"+
				"<td>"+ this.productTypeName + "</td>"+
				"<td>"+ this.productName + "</td>"+
				"<td>"+ this.distributionPrice +"</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ postageTypeName + "</td>"+
				"<td>"+ this.weight + "</td>"+
				"<td>"+
				"<a  class='del_btn' href='doNewDailyAdd.do?productRecommendPojo.type=${productRecommendPojo.type}&productRecommendPojo.productId="+this.id+"&productRecommendPojo.activityId="+this.activityId+"'>添加</a></td>"+
				"</tr>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>, "getActProBucket.do?randIni="+rand);
		$("#query_btn").click(query);
	});
</script>