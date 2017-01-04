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
<title>淘竹马首页</title>
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
    	<div class="my_supplier_Product-R-title">密保问题设置</div>
    	<div class="accountSecurityQuestion-fm">
	    	<form action="setAccountSecurityQuestion.do" id="idform" method="post">
		        <table border="0" cellpadding="0" cellspacing="0" class="accountSecurityQuestion-fm-table">
		            <tr>
		                <td class="register-form-tableTxt">问题一：</td>
		                <td>
			                <select name="userSecurityQuestion1.quesion" class="register-form-tableInput">
						 		<option value="">-请选择-</option>
								<s:iterator value="sysSecurityQuestionList">
									<option value="<s:property value="quesion"/>">
										<s:property value="quesion" />
									</option>
								</s:iterator>
							</select>
		                </td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">答案：</td>
		                <td><input name="userSecurityQuestion1.answer" type="text" class="register-form-tableInput"/></td>
		            </tr>
		             <tr>
		                <td class="register-form-tableTxt">问题二：</td>
		                 <td>
			                <select name="userSecurityQuestion2.quesion" class="register-form-tableInput">
						 		<option value="">-请选择-</option>
								<s:iterator value="sysSecurityQuestionList">
									<option value="<s:property value="quesion"/>">
										<s:property value="quesion" />
									</option>
								</s:iterator>
							</select>
		                </td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">答案：</td>
		                <td><input name="userSecurityQuestion2.answer" type="text" class="register-form-tableInput"/></td>
		            </tr>
		             <tr>
		                <td class="register-form-tableTxt">问题三：</td>
		                 <td>
			                <select name="userSecurityQuestion3.quesion" class="register-form-tableInput">
						 		<option value="">-请选择-</option>
								<s:iterator value="sysSecurityQuestionList">
									<option value="<s:property value="quesion"/>">
										<s:property value="quesion" />
									</option>
								</s:iterator>
							</select>
		                </td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">答案：</td>
		                <td><input name="userSecurityQuestion3.answer" type="text" class="register-form-tableInput"/></td>
		            </tr>
		    	</table>
		    	<!--	<div align="left">   -->
		    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="accountSecurityQuestion-button">提交</button>
				<!--  </div>	 -->
	    	</form>
    	</div>
    </div>
</div>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
