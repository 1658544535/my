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
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马玩具分销平台</title>
<script>
</script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="clear"></div>
<jsp:include page="my_supplier_head.jsp"></jsp:include>

<div class="faq-width">
<jsp:include page="my_supplier_left.jsp"></jsp:include>
    
    <div class="my_supplier_Product-R">
    	<div class="my_supplier_Product-R-title">新增产品图片</div>
    	<form action="productImagesAddWeb.do" method="post" id="from1" enctype="multipart/form-data">
    		<div>
    		 	<input type="hidden" name="productImagesPojo.productId" id="productImagesPojo.productId" value="${productImagesPojo.productId}"> 
		        <table border="0" cellpadding="0" cellspacing="0" class="my_supplier_Upload-table">
		            <tr>
		                <td class="register-form-tableTxt">产品图片：</td>
		                <td><input name="upfile" type="file" class="register-form-tableInput" style="border:0;"/></td>
		                <td><img src="/upfiles/shop/<s:property value="productImagesPojo.images"/>" height="120px"></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">排序</td>
		                <td><input name="productImagesPojo.sorting" value="<s:property value="productImagesPojo.sorting"/>" type="text" class="register-form-tableInput"/></td>
		            </tr>
		            <tr align="right">
					    <td></td>
		                <td align="left"><input id="submitId"
					name="submitId" type="button" class="my_supplier_Upload-table-button" style="cursor:pointer;" value="提交" /></td>
		            </tr>
		    	</table>
	    	</div>
	    	
	    	<div class="Btn_div">
			</div>
    	</form>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("#submitId").click(function(){
			document.getElementById("from1").submit();
	});
});
</script>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
