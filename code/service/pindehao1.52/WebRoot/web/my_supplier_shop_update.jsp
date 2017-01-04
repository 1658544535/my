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
<link type="text/css" rel="stylesheet" href="/js/testJSP/js/validate/css/validate.css" /> 
<script type="text/javascript" src="js/.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马首页</title>


<script src="/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script src="/js/showpicture.js" language="javascript"></script>
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
    	<div class="my_supplier_Product-R-title">店铺设置</div>
    	<form action="updateShopWeb.do" method="post" id="from1" enctype="multipart/form-data">
    		<div>
		        <table border="0" cellpadding="0" cellspacing="0" class="my_supplier_Upload-table">
			        <input type="hidden" name="shopWeb.userId" id="id"
							value="<s:property value="shopWeb.userId"/>" class="register-form-tableTxt">
					<input type="hidden" name="shopWeb.id" id="id"
							value="<s:property value="shopWeb.id"/>" class="register-form-tableTxt">
					<input type="hidden" name="shopWeb.images" id="shopWeb.images"
							value="<s:property value='shopWeb.images'/>" class="register-form-tableTxt">
		            <tr>
		                <td class="register-form-tableTxt">店铺名称：</td>
		                <td><input name="shopWeb.name" id="name" value="<s:property value="shopWeb.name"/>" type="text" class="register-form-tableInput" style="float:left"/><span id="sname"></span></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">店铺图片：</td>
		                <!--   图片预览   -->
		                <td><input name="upfile" type="file" class="register-form-tableInput" style="border:0;" onchange="PreviewImage(this,'imgphoto','Preview')"/><br/><font color="#ff0000">(支持jpg、png格式，图片像素大小：200x200)</font></td>
		                <td>
		                 	<div id="Preview"><img id="imgphoto" src="/upfiles/shop/<s:property value="shopWeb.images"/>" height="120px"></div>
		                </td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">主营品类:</td>
		                <td>
			                <select name="shopWeb.mainCategory" id="">
								 		<option value="">全部</option>
										<s:iterator value="mainCategory">
											<option value="<s:property value="value"/>"
											<s:if test="value == shopWeb.mainCategory">selected="selected"</s:if>><s:property
													value="name" />
											</option>
										</s:iterator>
							</select>
		                <!--  
		                <s:iterator value="mainCategory">
								<s:if test="value == shopWeb.mainCategory">
									 <input name="shopWeb.mainCategory" value="<s:property value="name" />" type="text" class="register-form-tableInput"/>
								</s:if>
							</s:iterator>
		                </td>
		                -->
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">主营产品:</td>
		                <td><input name="shopWeb.mainProduct" value="<s:property value="shopWeb.mainProduct"/>" type="text" class="register-form-tableInput" style="float:left"/><span id="mainProduct"></span></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">销售市场:</td>
		                <td><input name="shopWeb.salesArea" value="<s:property value="shopWeb.salesArea"/>" type="text" class="register-form-tableInput" style="float:left"/><span id="salesArea"></span></td>
		            </tr>
		            <!--
		            <tr>
		                <td class="register-form-tableTxt">纬度：</td>
		                <td><input name="shopWeb.lat" value="<s:property value="shopWeb.lat"/>" type="text" class="register-form-tableInput"/></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">经度：</td>
		                <td><input name="shopWeb.lng" value="<s:property value="shopWeb.lng"/>" type="text" class="register-form-tableInput"/></td>
		            </tr>-->
		            <tr>
		                <td class="register-form-tableTxt">地址:</td>
		                <td><input name="shopWeb.address" value="<s:property value="shopWeb.address"/>" type="text" class="register-form-tableInput" style="float:left"/><span id="address"></span></td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">联系号码：</td>
		                <td><input name="shopWeb.phone" value="<s:property value="shopWeb.phone"/>" type="text" class="register-form-tableInput" style="float:left"/><span id="phone"></span></td>
		            </tr>
		            <!--
		            <tr>
		                <td class="register-form-tableTxt">状态：</td>
		                <td>
			                <select name="shopWeb.status" id="">
							 		<option value="">全部</option>
									<s:iterator value="status">
										<option value="<s:property value="value"/>"
										<s:if test="value == shopWeb.status">selected="selected"</s:if>><s:property
												value="name" />
										</option>
									</s:iterator>
							</select>
							 
		                </td>
		            </tr>
		            -->
		            <tr>
		                <td class="register-form-tableTxt" valign="top">店铺介绍：</td>
		                <td><textarea name="shopWeb.content" cols="10" rows="80" class="apply-textarea" style="float:left"><s:property value="shopWeb.content"/></textarea><span id="content"></span></td>
		            </tr>
		            <tr align="right">
		           	 <!--  
			                <td align="right"><input name="" type="button" value="返回" class="my_supplier_Upload-table-button"
						style="cursor:pointer;" onclick="window.history.back()" /></td>
					 -->
					    <td></td>
		                <td align="left"><input id="submitId"
					name="submitId" type="button" class="my_supplier_Upload-table-button" style="cursor:pointer;" value="设置" /></td>
		            </tr>
		    	</table>
	    	</div>
	    	
	    	<div class="Btn_div">
			</div>
    	</form>
    </div>
</div>
<script type="text/javascript">
//$(document).ready(function() {
//	$("#submitId").click(function(){
//			document.getElementById("from1").submit();
//	});
	//var test = document.getElementById("name").value;
	//alert(test);
});
</script>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
<script>
var sname =new tt.Field(" 店铺名称 ","shopWeb.name").setMsgId("sname");
var mainProduct =new tt.Field(" 主营商品 ","shopWeb.mainProduct").setMsgId("mainProduct");
var salesArea =new tt.Field(" 销售市场","shopWeb.salesArea").setMsgId("salesArea");
var address =new tt.Field(" 地址 ","shopWeb.address").setMsgId("address");
var phone =new tt.Field(" 联系号码","shopWeb.phone").setMsgId("phone");
var content =new tt.Field(" 店铺介绍","shopWeb.content").setMsgId("content");


tt.vf.req.add(sname,mainProduct,salesArea,address,phone,content);
tt.vf.num.add(phone);

new tt.LV().set(0, 16).add(sname);
new tt.LV().set(0, 50).add(mainProduct);
new tt.LV().set(0, 50).add(salesArea);
new tt.LV().set(0, 50).add(address);
new tt.LV().set(0, 11).add(phone);
new tt.LV().set(0, 100).add(content);

	$(document).ready(function() {
		$("#submitId").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>