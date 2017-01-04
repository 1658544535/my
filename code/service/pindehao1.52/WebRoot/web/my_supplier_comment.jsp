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
/*
$(function() {
	var time = null;
	$('#zhaqsz').hover(function(){
		$('.drop').show();
	},function(){
		time = setTimeout(function(){
			$('.drop').hide();
		},50);
	});
	$(".drop").hover(function(){
		clearTimeout(time);
		$(this).show();
	},function(){
		time = setTimeout(function(){
			$('.drop').hide();
		},50);
	});
});
*/
/*
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
*/
//查询
function query() {
		document.getElementById("searchform").submit();
		var rand = Math.random() * (100000 + 1);
		//var Name = document.getElementById("proName").value;
		//alert(Name);
		queryData("getProductCommentCountWeb.do", "ProductCommentAllListWeb.do?randquery=" + rand);
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
    	<div class="my_supplier_Product-R-title">评价管理</div>
        <div class="my_supplier_Product-R-search">
	        <form action="productCommentManageWeb.do" id="searchform" method="post">
		                       产品名称： <input name="proName" id="proName" type="text" class="my_supplier-input" value="<s:property value="proName"/>">
		       <span class="my_supplier-input-txt"><a class="my_supplier_Product-R-search-A" onclick="query()" style="cursor:pointer;">搜 索</a></span>
	        </form>
        </div>

        <div class="my_supplier_Product-R-list">
	            <table border="0" cellpadding="0" cellspacing="0" width="100%" class="my_supplier_Product-R-list-table">
	            	<tr>
	                	<td class="my_supplier_Product-R-list-tableTit">评价人</td>
	                    <td class="my_supplier_Product-R-list-tableTit">评价内容</td>
	                 <!--   <td class="my_supplier_Product-R-list-tableTit">评价状态</td>-->
	                    <td class="my_supplier_Product-R-list-tableTit">产品名称</td>
	                    <td class="my_supplier_Product-R-list-tableTit">评价时间</td>
	                </tr>
	                <tbody id="body"></tbody>
	            </table>
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
                "<td>"+this.userName+"</td>"+
                "<td>"+this.comment+"</td>"+
              <!--  "<td>"+this.scoreName+"</td>"+-->
                "<td>"+this.productName+"</td>"+
                "<td>"+this.createDateStr+"</td>"+
			    "</td></tr>");
	}
	
	$(function() {
		 /** 首次要初始化分页**/
		 var pageSize = 5;
		 var rand=Math.random() * ( 100000 + 1);
		 var Name = document.getElementById("proName").value;
		 //alert(Name);Name传递搜索参数给action
		 MAOWU.page.init(<s:property value='page.rowCount'/>,
				 "ProductCommentAllListWeb.do?name="+Name+"&randIni="+rand,pageSize);
		
	});
</script>
<form action="ProductCommentAllListWeb.do" method="post" id="sysform">
	<input type="hidden" name="page.pageNo" value=0 id="pageNo">
</form>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
