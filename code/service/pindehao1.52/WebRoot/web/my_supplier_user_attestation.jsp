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
<title>淘竹马首页</title>
<script src="/js/showpicture.js" language="javascript"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script>

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

</script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="clear"></div>
<jsp:include page="my_supplier_head.jsp"></jsp:include>

<div class="faq-width">
<jsp:include page="my_supplier_left.jsp"></jsp:include>
    
    <div class="my_supplier_Product-R">
    	<div class="my_supplier_Product-R-title">店铺认证</div>
    	<form action="updateShopWeb.do" method="post" id="from1" enctype="multipart/form-data">
    		<div>
		        <table border="0" cellpadding="0" cellspacing="0" class="my_supplier_Upload-table">
					<s:if test="userAttestationPojo!=null">
		            		<input type="hidden" name="userAttestationPojo.id" id="id"
							value="<s:property value="userAttestationPojo.id"/>" class="register-form-tableTxt">
							<input type="hidden" name="userAttestationPojo.userId" id="id"
							value="<s:property value="userAttestationPojo.userId"/>" class="register-form-tableTxt">
							<input type="hidden" name="userAttestationPojo.attestationImage" id="userAttestationPojo.attestationImage"
							value="<s:property value='userAttestationPojo.attestationImage'/>" class="register-form-tableTxt">
		            	</s:if>
		            <input name="url" id="url" value="" class="inputText" type="hidden" >
		            
		            <tr>
		                <td>原&nbsp;营&nbsp;业&nbsp;执&nbsp;照：<div id="Preview"><img id="imgphoto" src="/upfiles/attestation/<s:property value="userAttestationPojo.attestationImage"/>" height="120px"></div></td>
		            </tr>
		            <tr>
		                <td align="left">上传营业执照：<input name="upfile" type="file" class="register-form-tableInput" style="border:0;" onchange="PreviewImage(this,'imgphoto','Preview')"/><br/><font color="#ff0000">(支持jpg、png格式，图片像素大小：640x640)</font></td>
		             
		            </tr>
		            
		            <tr><td align="left">
		            	<s:if test="userAttestationPojo!=null">
		            		<input onclick="edBtn()" id="submitId" name="submitId" type="button" class="my_supplier_Upload-table-button" style="cursor:pointer;" value="确认" />
		            	</s:if>
		            	<s:else>
		            		<input onclick="addBtn()" id="submitId" name="submitId" type="button" class="my_supplier_Upload-table-button" style="cursor:pointer;" value="提交" />
		            	</s:else>
		             </td></tr>
		    	</table>
	    	</div>
	    	
	    	<div class="Btn_div">
			</div>
    	</form>
    </div>
</div>
<script type="text/javascript">
function edBtn(){
	$("#url").val("userAttestationWeb.do");//赋值
	$("#from1").attr("action","updateUserAttestationWeb.do");
	$("#from1").submit();
}
function addBtn(){
	$("#url").val("userAttestationWeb.do");//赋值
	$("#from1").attr("action","addUserAttestationWeb.do");
	$("#from1").submit();
}
</script>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
