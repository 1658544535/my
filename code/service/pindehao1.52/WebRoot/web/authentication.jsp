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
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/g.base.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马玩具分销平台</title>
</head>
<body>

<div class="register-title">
	<div class="register-txt">淘竹马分销<span class="register-txt02">身份认证</span></div>
</div>

<div class="register-form">
	<form id="registerForm" action="addUserAttestation.do" method="post" enctype="multipart/form-data">
		<table border="0" cellpadding="0" cellspacing="0" class="register-form-table">
	        <tr>
	        	<td class="register-form-tableTxt">认证类型 <font color="#df434e">*</font></td>
	        	<td >
					<select name="userType" class="register-form-tableButton">
						<option value="">--请选择--</option>
						<option value="1">供应商</option>
						<option value="2">采购商</option>
					</select>
				</td>
	        </tr>
	        <tr>
				<td align="right" class="register-form-tableTxt">上传营业执照:<font color="#df434e">*</font></td>
				<td><input type="file" name="upfile"><span id=""></span></td>
			</tr>
	    </table>
	    <div align="center">
			<button type="input" class="" onclick="window.history.back()">返回</button>
			<input type="button" class="" value="立即认证" id="sbutton" />
		</div>
	</form>
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
<script>
	$(document).ready(function() {
		$("#sbutton").click(function() {
				document.getElementById("registerForm").submit();
		});
	});
</script>