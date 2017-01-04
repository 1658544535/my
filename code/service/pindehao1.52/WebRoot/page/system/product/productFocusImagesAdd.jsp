<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav"><a href="productManage.do?productPojo.userId=${productFocusImagesPojo.userId}">商品管理</a> &gt; <a href="productFocusImages.do?productFocusImages.productId=${productFocusImagesPojo.productId }&productFocusImages.userId=${productFocusImagesPojo.userId}" >焦点图片管理</a> &gt; <a href="#">新增</a></div>
  <div class="h15"></div>
  <div>
  <form action="insertProductFocusImages.do?productFocusImages.productId=${productFocusImagesPojo.productId }&productFocusImages.userId=${productFocusImagesPojo.userId}" method="post" id="from1" enctype="multipart/form-data">
    <input type="hidden" name="productFocusImages2.productId" id="productFocusImages.productId"
						value="${productFocusImagesPojo.productId}">
    <input type="hidden" name="productFocusImages2.userId" id="productFocusImages.userId"
						value="${productFocusImagesPojo.userId}">
    
     <input type="hidden" name="productFocusImages3.productId" id="productFocusImages.productId"
						value="${productFocusImagesPojo.productId}">
    <input type="hidden" name="productFocusImages3.userId" id="productFocusImages.userId"
						value="${productFocusImagesPojo.userId}">
						
	<input type="hidden" name="productFocusImages4.productId" id="productFocusImages.productId"
						value="${productFocusImagesPojo.productId}">
    <input type="hidden" name="productFocusImages4.userId" id="productFocusImages.userId"
						value="${productFocusImagesPojo.userId}">
						
	<input type="hidden" name="productFocusImages5.productId" id="productFocusImages.productId"
						value="${productFocusImagesPojo.productId}">
    <input type="hidden" name="productFocusImages5.userId" id="productFocusImages.userId"
						value="${productFocusImagesPojo.userId}">
    
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
      				<tr>
						<td align="right" width="20%" class="grey">商品焦点图片:</td>
						<td>
						<input type="file" name="upfile" class="floatLeft" id="ticketName"> </td>
					</tr>
					<tr>
						<td align="right" width="20%" class="grey">排序:</td>
						<td><input class="floatLeft" type="text"
							name="productFocusImages.sorting"
							value="" id="sorting"> <span
							id="sorting_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%"><select name="productFocusImages.status"
							id="ticketType" class="floatLeft">
								<c:forEach items="${status}" var="status">
									<option value="${status.value}" <c:if test="${1==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
						</select></td>

					</tr>
					
					<tr>
						<td align="right" width="20%" class="grey">商品焦点图片:</td>
						<td>
						<input type="file" name="upfile2" class="floatLeft" id="ticketName"> </td>
					</tr>
					<tr>
						<td align="right" width="20%" class="grey">排序:</td>
						<td><input class="floatLeft" type="text"
							name="productFocusImages2.sorting"
							value="" id="sorting"> <span
							id="sorting_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%"><select name="productFocusImages2.status"
							id="ticketType" class="floatLeft">
								<c:forEach items="${status}" var="status">
									<option value="${status.value}" <c:if test="${1==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
						</select></td>

					</tr>
					
					<tr>
						<td align="right" width="20%" class="grey">商品焦点图片:</td>
						<td>
						<input type="file" name="upfile3" class="floatLeft" id="ticketName"> </td>
					</tr>
					<tr>
						<td align="right" width="20%" class="grey">排序:</td>
						<td><input class="floatLeft" type="text"
							name="productFocusImages3.sorting"
							value="" id="sorting"> <span
							id="sorting_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%"><select name="productFocusImages3.status"
							id="ticketType" class="floatLeft">
								<c:forEach items="${status}" var="status">
									<option value="${status.value}" <c:if test="${1==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
						</select></td>

					</tr>
					
					<tr>
						<td align="right" width="20%" class="grey">商品焦点图片:</td>
						<td>
						<input type="file" name="upfile4" class="floatLeft" id="ticketName"> </td>
					</tr>
					<tr>
						<td align="right" width="20%" class="grey">排序:</td>
						<td><input class="floatLeft" type="text"
							name="productFocusImages4.sorting"
							value="" id="sorting"> <span
							id="sorting_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%"><select name="productFocusImages4.status"
							id="ticketType" class="floatLeft">
								<c:forEach items="${status}" var="status">
									<option value="${status.value}" <c:if test="${1==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
						</select></td>

					</tr>
					
					<tr>
						<td align="right" width="20%" class="grey">商品焦点图片:</td>
						<td>
						<input type="file" name="upfile5" class="floatLeft" id="ticketName"> </td>
					</tr>
					<tr>
						<td align="right" width="20%" class="grey">排序:</td>
						<td><input class="floatLeft" type="text"
							name="productFocusImages5.sorting"
							value="" id="sorting"> <span
							id="sorting_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%"><select name="productFocusImages5.status"
							id="ticketType" class="floatLeft">
								<c:forEach items="${status}" var="status">
									<option value="${status.value}" <c:if test="${1==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
						</select></td>

					</tr>
    </table>
  
    </form>
  </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton"/>
  </div>
</div>

</body>
</html>


<script>
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>