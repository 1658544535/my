<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马首页</title>
<script>
//全选
function allCbTrue() {
	var checkbox = document.getElementById("selectcb");
	if (checkbox.checked == true) {
		var code_Values = document.getElementsByName("tids");
		for (i = 0; i < code_Values.length; i++) {
			if (code_Values[i].type == "checkbox") {
				code_Values[i].checked = true;
			}
		}

	} 
}
//全不选
function allCbFalse() {
	var checkbox = document.getElementById("selectcb");
	if (checkbox.checked == false) {
		var code_Values = document.getElementsByName("tids");
		for (i = 0; i < code_Values.length; i++) {
			if (code_Values[i].type == "checkbox") {
				code_Values[i].checked = false
			}
		}
	}
}
//删除全部
function deleteAll() {
	document.getElementById("idform").submit();
}
//删除
function del(val)
	{
		if(confirm("你真的想删除该记录么？"))
			{
				var url = "deleProductWeb.do?productPojo.id="+val;	
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
		window.location.href = "productManageWeb.do";
	}else{
		alert("删除失败");
	}
}
//查询
function query() {
		document.getElementById("searchform").submit();
		var rand = Math.random() * (100000 + 1);
		queryData("getProductCountWeb.do", "productAllListWeb.do?randquery=" + rand);
		
}

</script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="clear"></div>
<jsp:include page="my_supplier_head.jsp"></jsp:include>

<div class="faq-width">
<jsp:include page="my_supplier_left.jsp"></jsp:include>
    
    <div class="my_supplier_Product-R">
    	<div class="my_supplier_Product-R-title">产品信息管理</div>
        <div class="my_supplier_Product-R-search">
	        <form action="productManageWeb.do" id="searchform" method="post">
		                       名称： <input name="productPojo.productName" id="productPojo.productName" type="text" class="my_supplier-input" value="<s:property value="productName"/>">
		       <span class="my_supplier-input-txt">货号： <input name="productPojo.productNum" id="productPojo.productNum" type="text" class="my_supplier-input" value="<s:property value="productNum"/>"></span>
		       <span class="my_supplier-input-txt"><a class="my_supplier_Product-R-search-A" onclick="query()" style="cursor:pointer;">搜 索</a></span>
	        </form>
        </div>

        <div class="my_supplier_Product-R-list">
        	<!-- <div class="my_supplier_Product-R-list-A"><span><input type="checkbox" id="selectcb" name="selectcb" onclick="allCbTrue()">全选</span> | <span><input type="checkbox" id="selectcb" name="selectcb" onclick="allCbFalse()">全不选</span> | <a class="" onclick="deleteAll()">批量删除</a></div> -->
            <form action="productDeleteIdWeb.do" id="idform" method="post">
	            <table border="0" cellpadding="0" cellspacing="0" width="100%" class="my_supplier_Product-R-list-table">
	            	<tr>
	                	<!-- <td class="my_supplier_Product-R-list-tableTit">ID</td>
	                    <td class="my_supplier_Product-R-list-tableTit">状态</td> -->
	                    <td class="my_supplier_Product-R-list-tableTit">产品货号</td>
	                    <td class="my_supplier_Product-R-list-tableTit">产品名称</td>
	                    <td class="my_supplier_Product-R-list-tableTit">产品价格</td>
	                    <td class="my_supplier_Product-R-list-tableTit">所属分类</td>
	                    <td class="my_supplier_Product-R-list-tableTit">产品图片</td>
	                    <td class="my_supplier_Product-R-list-tableTit">产品图集</td>
	                   <!-- <td class="my_supplier_Product-R-list-tableTit">排序</td>
	                    <td class="my_supplier_Product-R-list-tableTit">操作</td> -->
	                </tr>
	                <tbody id="body"></tbody>
	            </table>
            </form>
            <div class="shop-page">
	        	<div class="digg" id="Pagination"></div>
				<i id="rowcount" style="display:none;"></i><i id="pagecount" style="display:none;"></i>
			</div>
        </div>
    </div>
</div>
<script type="text/javascript">
	/**
	 *分页展现页面函数 
	 **/ 
	function installPage() {
		$("#body").append(
			"<tr>"+
				//"<td><input name='tids' type='checkbox' value="+this.id+"></td>"+
                //"<td><font color='#0000f2'>"+this.statusName+"</font></td>"+
                "<td>"+this.productNum+"</td>"+
                   "<td><a href='goProductDetail.do?productPojo.id="+ this.id + "'><font color='#4c4c4c'>"+this.productName+"<br/></a>"+"</font></td>"+
                "<td>"+this.distributionPrice+"</td>"+
                "<td>"+this.productTypeName+"</td>"+
                "<td><img src='/upfiles/product/"+this.image+"' alt='' width='98' height='98' /></td>"+
                "<td><div class='my_supplier_Product-R-list-table-Update'>"+"<a class='' href='productImagesManageWeb.do?productImagesPojo.productId="+this.id+"'>图集管理</a>"+"</div>" +
               // "<td>"+this.sorting+"</td>"+
			    //"<td><div class='my_supplier_Product-R-list-table-Update'>"+"<a href='goFindProductWeb.do?productPojo.id="+ this.id + "'>更新</a>"+"</div>" +
			  //  "<td><div class='my_supplier_Product-R-list-table-Update'>"+"<a class='' onclick=del('"+ this.id + "')>删除</a>"+"</div>" +
			    "</td></tr>");
	}
	
	$(function() {
		 /** 首次要初始化分页**/
		 //alert("I am an alert box!!")
		 var pageSize = 4;
		 var rand=Math.random() * ( 100000 + 1);
		 MAOWU.page.init(<s:property value='page.rowCount'/>,
				 "getProductAllListWeb.do?randIni="+rand,pageSize);
		
	});
</script>
<form action="getProductAllListWeb.do" method="post" id="sysform">
	<input type="hidden" name="page.pageNo" value=0 id="pageNo">
	<!-- 隐含域用作搜索条件，传递参数给action -->
	<input type="hidden" name="productPojo.productName" value="<s:property value="productPojo.productName"/>" id="productName">
	<input type="hidden" name="productPojo.productNum" value="<s:property value="productPojo.productNum"/>" id="productNum">
</form>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
