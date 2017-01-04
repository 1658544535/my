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
  <div class="s_nav"><a href="productManage.do?productPojo.userId=${productPrimaryImagesPojo.userId}">商品管理</a> &gt; <a href="productPrimaryImages.do?productPrimaryImages.productId=${productPrimaryImagesPojo.productId }&productPrimaryImages.userId=${productPrimaryImagesPojo.userId}" >焦点图片管理</a> &gt; <a href="#">编辑</a></div>
  <div class="h15"></div>
  <div>
  <form action="updateProductPrimaryImages.do?productPrimaryImages.productId=${productPrimaryImagesPojo.productId }&productPrimaryImages.userId=${productPrimaryImagesPojo.userId }" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input name="productPrimaryImages.id" id="productPrimaryImagesId" value="${productPrimaryImagesPojo.id}" class="inputText" type="hidden" >
    <input name="productPrimaryImages.images" id="images" value="${productPrimaryImagesPojo.images}" class="inputText" type="hidden" >
					<tr>
						<td align="right" width="20%" class="grey">商品焦点图片:</td>
						<td><img class="floatLeft"
							src="/upfiles/productPrimaryImages/${productPrimaryImagesPojo.images}" height="100px" /> <br/>
							<input type="file" class="floatLeft" name="upfile"
							class="floatLeft" id="userPic"></td>
					</tr>
					<tr>
						<td align="right" width="20%" class="grey">排序:</td>
						<td><input class="floatLeft" type="text"
							name="productPrimaryImages.sorting"
							value="${productPrimaryImagesPojo.sorting}" id="sorting"> <span
							id="sorting_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">状态:</td>
						<td width="35%"><select name="productPrimaryImages.status"
							id="ticketType" class="floatLeft">
								<c:forEach items="${status}" var="status">
									<option value="${status.value}"
										<c:if test="${productPrimaryImagesPojo.status==status.value}">selected="selected" </c:if>>${status.name}</option>
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