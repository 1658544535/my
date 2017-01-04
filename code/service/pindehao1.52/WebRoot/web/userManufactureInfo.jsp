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
    	<div class="user-manufacturer-info-top-text"><span class="user-manufacturer-info-left" style="float: left;">供应商个人信息</span><span class="user-manufacturer-info-right" style="float: right;" ><a href="goUpdateManufacturerWeb.do">修改供应商个人信息</a></span></div>
    	<div class="accountSecurityQuestion-fm">
    	<s:if test=" goUpdateManufacturer == null ">
    		<div class="user-manufacturer-info-top-text-none">
    			<div  class="user-manufacturer-info-text-none"><span>您还没有设置个人信息!</span></div>
    	    </div>
	    </s:if>
	    <s:if test=" goUpdateManufacturer != null ">
	    	<table border="0" cellpadding="0" cellspacing="0" class="apply-form-table">
			    <tr>
		        	<td class="register-form-tableTxt">公司名称</td>
		            <td><input name="goUpdateManufacturer.company" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.company'/>" style="float: left;" readonly="readonly" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">主营类目 </td>
		            <td>
		            	<s:if test=" '主营遥控玩具' == goUpdateManufacturer.mainCategory ">
			            	<input name="goUpdateManufacturer.mainCategory" type="text" class="register-form-tableInput" value="主营遥控玩具" readonly="readonly" style="float: left;" />
			            </s:if> 
			            <s:if test=" '主营早教玩具' == goUpdateManufacturer.mainCategory ">
			            	<input name="goUpdateManufacturer.mainCategory" type="text" class="register-form-tableInput" value="主营早教玩具" readonly="readonly" style="float: left;" />
			            </s:if> 
			            <s:if test=" '主营过家家玩具' == goUpdateManufacturer.mainCategory ">
			            	<input name="goUpdateManufacturer.mainCategory" type="text" class="register-form-tableInput" value="主营过家家玩具" readonly="readonly" style="float: left;" />
			            </s:if> 
			            <s:if test=" '主营童车玩具' == goUpdateManufacturer.mainCategory ">
			            	<input name="goUpdateManufacturer.mainCategory" type="text" class="register-form-tableInput" value="主营童车玩具" readonly="readonly" style="float: left;" />
			            </s:if> 
			            <s:if test=" '主营益智玩具' == goUpdateManufacturer.mainCategory ">
			            	<input name="goUpdateManufacturer.mainCategory" type="text" class="register-form-tableInput" value="主营益智玩具" readonly="readonly" style="float: left;" />
			            </s:if> 
			           <s:if test=" '主营电动玩具' == goUpdateManufacturer.mainCategory ">
			            	<input name="goUpdateManufacturer.mainCategory" type="text" class="register-form-tableInput" value="主营电动玩具" readonly="readonly" style="float: left;" />
			            </s:if>
			           <s:if test=" null == goUpdateManufacturer.mainCategory ">
			            	<input name="goUpdateManufacturer.mainCategory" type="text" class="register-form-tableInput" value=" " readonly="readonly" style="float: left;" />
			            </s:if>
		            </td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">公司总人数 </td>
		            <td>
						<s:if test=" 1 == goUpdateManufacturer.scale ">
		            		<input name="goUpdateManufacturer.scale" type="text" class="register-form-tableInput" value="小于50人" readonly="readonly" style="float: left;" />
		            	</s:if>
		            	<s:if test=" 2 == goUpdateManufacturer.scale ">
		            		<input name="goUpdateManufacturer.scale" type="text" class="register-form-tableInput" value="50~100人" readonly="readonly" style="float: left;" />
		            	</s:if>
		            	<s:if test=" 3 == goUpdateManufacturer.scale ">
		            		<input name="goUpdateManufacturer.scale" type="text" class="register-form-tableInput" value="100~150人" readonly="readonly" style="float: left;" />
		            	</s:if>
		            	<s:if test=" 4 == goUpdateManufacturer.scale ">
		            		<input name="goUpdateManufacturer.scale" type="text" class="register-form-tableInput" value="150~200人" readonly="readonly" style="float: left;" />
		            	</s:if>
		            	<s:if test=" 5 == goUpdateManufacturer.scale ">
		            		<input name="goUpdateManufacturer.scale" type="text" class="register-form-tableInput" value="200~300人" readonly="readonly" style="float: left;" />
		            	</s:if>
		            	<s:if test=" 6 == goUpdateManufacturer.scale ">
		            		<input name="goUpdateManufacturer.scale" type="text" class="register-form-tableInput" value="大于300人" readonly="readonly" style="float: left;" />
		            	</s:if>
		            	<s:if test="goUpdateManufacturer.scale <=0 ">
		            		<input name="goUpdateManufacturer.scale" type="text" class="register-form-tableInput" value=" " readonly="readonly" style="float: left;" />
		            	</s:if>
		            </td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">自营品牌</td>
		            <td><input name="goUpdateManufacturer.brand" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.brand'/>" style="float: left;" readonly="readonly" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">销售市场</td>
		            <td><input name="goUpdateManufacturer.salesArea" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.salesArea'/>" style="float: left;" readonly="readonly" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">联系人</td>
		            <td><input name="goUpdateManufacturer.contact" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.contact'/>" style="float: left;" readonly="readonly" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">联系人职务</td>
		            <td><input name="goUpdateManufacturer.duty" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.duty'/>" style="float: left;" readonly="readonly" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">联系人Email</td>
		            <td><input name="goUpdateManufacturer.email" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.email'/>" style="float: left;" readonly="readonly" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">联系QQ</td>
		            <td><input name="goUpdateManufacturer.QQ" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.QQ'/>" style="float: left;" readonly="readonly" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">手机号码</td>
		            <td><input name="goUpdateManufacturer.phone" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.phone'/>" style="float: left;" readonly="readonly" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">传真号码</td>
		            <td><input name="goUpdateManufacturer.fax" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.fax'/>" style="float: left;" readonly="readonly" /></td>
		        </tr>
		        <tr>
		        	<td class="register-form-tableTxt">网店连接 </td>
		            <td><input name="goUpdateManufacturer.webSite" type="text" class="register-form-tableInput" value="<s:property value='goUpdateManufacturer.webSite'/>" style="float: left;" readonly="readonly" /></td>
		        </tr>
		    	<tr>
		    		<td><div class="register-form-tableTxt">公司地址</div></td>
		    		<td> <div class="apply-form-textarea"><textarea name="goUpdateManufacturer.address" cols="29" rows="3" style="float: left;" readonly="readonly" ><s:property value='goUpdateManufacturer.address' /></textarea></div></td>
		    	</tr>
		    	<tr>
		    		<td><div class="register-form-tableTxt">主营产品</div></td>
		    		<td><div class="apply-form-textarea"><textarea name="goUpdateManufacturer.mainProduct" cols="29" rows="3" style="float: left;" readonly="readonly"  ><s:property value='goUpdateManufacturer.mainProduct' /></textarea></div></td>
		    	</tr>
		   		<tr>
		    		<td><div class="register-form-tableTxt">公司简介</div></td>
		    		<td><div class="apply-form-textarea"><textarea name="goUpdateManufacturer.content" cols="29" rows="3"  style="float: left;" readonly="readonly" ><s:property value='goUpdateManufacturer.content' /></textarea></div></td>
		    	</tr>
	    	</table>
	    </s:if>
    	</div>
    </div>
</div>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
