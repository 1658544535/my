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
		<div class="s_nav">
		<a href="#">平台专题管理 </a>&gt;<a href="platformSpecialList.do">平台专题管理 </a>&gt;
		<a href="goSettingPlatformSpecialPorduct.do?types=6&titleId=${titleId}">设置活动商品</a>&gt;
		<a href="goAddPlatformSpecialPorduct.do?types=6&titleId=${titleId}">添加活动商品</a></div>
    <div class="Search_control">
	 <p>按条件查找</p>
	 <a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
	 </div>
		<!-- 查询开始 -->
    	<form action="goAddPlatformSpecialPorduct.do" method="post" id="sysform">
   		 	<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">  
					<tr> 
					    <td align="right">商品名称：</td>
						<td><input type="text" name="productPojo.productName" value=""></td>
						<td align="right">公司名称：</td>
						<td><input type="text" name="productPojo.shopName" value=""></td>
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
   <a class="Add_btn"  onclick="add()" >批量增加</a>
<form action="" id="idform"  method="post" >
    <table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
    	<th>商品名称</th>
		<th>商品图片</th>
		<th>商品价格</th>
		<th>商品货号</th>
		<th>商品类型</th>
		<th>公司名</th>
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
		queryData("getActProBucketCount.do", "getActProBucket.do?randquery="+rand);
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
				"<td>"+ this.distributionPrice +"</td>"+
				"<td>"+ this.productNum + "</td>"+
				"<td>"+ this.productTypeName + "</td>"+
				"<td>"+ this.shopName + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td>"+
				"<a  class='del_btn' href='addPlatformSpecialPorduct.do?titleId=${titleId}&productId="+this.id+"&activityId="+this.activityId+"'>添加</a></td>"+
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