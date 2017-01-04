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
<div class="s_nav">
<a href="specialProductList.do?specialProductPojo.specialId=${specialId}">专场商品设置</a> &gt;<a href="#">新增专场商品</a> 
</div>
  <div class="h15"></div>
  <div>
<form action="specialProductAddOk.do" method="post" id="form1" enctype="multipart/form-data">
  <input type="hidden" name="activityId" value="${activityId}" class="floatLeft" readonly="readonly" style="border: 0">
  <input type="hidden" name="specialProductPojo.userBrandId" value="${productPojo.userBrandId}" class="floatLeft" readonly="readonly" style="border: 0">
  <input type="hidden" name="specialProductPojo.specialId" value="${specialProductPojo.specialId}" class="floatLeft" readonly="readonly" style="border: 0">
  <input type="hidden" name="specialProductPojo.productId" value="${specialProductPojo.productId}" class="floatLeft" readonly="readonly" style="border: 0">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
	<c:if test="${specialProductPojo.specialName != null}">
	<tr>
	    <td align="right" class="grey" width="15%">专场名称：</td>
		<td>
		<label>${specialProductPojo.specialName}</label>
		</td>
		<td><span id="specialName_mgId"></span></td>
	</tr>
	</c:if>
	<tr>
    <td align="right" class="grey" width="15%">商品名称：</td>
	<td><label>${specialProductPojo.productName}</label></td>
	<td></td>
	</tr>
    <!-- <tr>
    <td align="right" class="grey" width="15%">商品标题：</td>
	<td><input type="text" name="specialProductPojo.title" value="${specialProductPojo.title}" class="floatLeft"></td>
	<td><span id="title_mgId"></span></td>
	</tr> -->
	<tr>
     <td align="right" class="grey" width="15%">商品原价：</td>
	<td><input type="text" name="specialProductPojo.sellPrice" value="${specialProductPojo.sellPrice}" class="floatLeft"></td>
	<td><span id="sellPrice_mgId"></span></td>
	</tr>
	<tr>
    <td align="right" class="grey" width="15%">商品活动价：</td>
	<td><input type="text" name="specialProductPojo.specialPrice" value="${specialProductPojo.specialPrice}" class="floatLeft"></td>
	<td><span id="specialPrice_mgId"></span></td>
	</tr>
	<tr>
    <td align="right" class="grey" width="15%">折扣：</td>
	<td><input type="hidden" name="specialProductPojo.tips">${specialProductPojo.tips}</td>
	<td><span id="tips_mgId"></span></td>
	</tr>
	<tr>
    <td align="right" class="grey" width="15%">商品活动总量：</td>
	<td><input type="text" name="specialProductPojo.specialNum" value="${specialProductPojo.specialNum}" class="floatLeft"></td>
	<td><span id="specialNum_mgId"></span></td>
	</tr>
	<tr>
    <td align="right" class="grey" width="15%">商品活动库存：</td>
	<td><input type="text" name="specialProductPojo.specialStock" value="${specialProductPojo.specialStock}" class="floatLeft"></td>
	<td><span id="specialStock_mgId"></span></td>
	</tr> 
	<tr>
    <td align="right" class="grey" width="15%">排序：</td>
	<td><input type="text" name="specialProductPojo.sorting" value="${specialProductPojo.sorting}" class="floatLeft"></td>
	<td><span id="sorting_mgId"></span></td>
	</tr> 
	<!-- <tr>
    <td align="right" class="grey" width="15%">是否推荐：</td>
	<td><select name="specialProductPojo.isIntroduce">
		 	<option value="0">否</option>
		 	<option value="1">是</option>
		</select></td>
	<td><span id="isIntroduce_mgId"></span></td>
	</tr> -->
   <%--	
	<tr>
		<td align="right" class="grey" width="15%">预览：</td>
		<td width="35%">
			<select name="sceneProductPojo.previewPro">
		 		<option value="1">有效预览</option>
		 		<option value="0">无效预览</option>
			</select>
		</td>
		<td></td>
	</tr>
	--%>
	<tr>
		<td align="right" class="grey" width="15%">状态：</td>
		<td width="35%">
			<select name="specialProductPojo.status">
		 		<option value="1">已审核</option>
		 		<option value="0">未审核</option>
			</select>
		</td>
		<td></td>
	</tr> 
	<%-- <tr>
		<td align="right" class="grey" width="15%">活动商品图片：</td>
		<td width="35%" height="100px">
	        <input type="file" name="upfile" class="floatLeft">
	        <font color="#FF0000">图片建议尺寸：800*800</font>
		</td>
		<td>
		    <img src='/upfiles/scene/${specialProductPojo.image}' height='100px' width="200px"/>
	        <span id="file_mgId"></span>
	    </td>
	</tr> --%>
	<%-- <tr>
		<td align="right" class="grey" width="15%">推荐介绍：</td>
		<td width="35%">
			<textarea rows="10" cols="70" name="specialProductPojo.introduction" class="floatLeft">${specialProductPojo.introduction }</textarea>
		</td>
		<td><span id="introduction_mgId"></span></td>
 	</tr> --%>
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
    //var title         =new tt.Field("商品标题","specialProductPojo.title").setMsgId("title_mgId");
	var specialPrice    =new tt.Field("商品活动价","specialProductPojo.specialPrice").setMsgId("specialPrice_mgId");
	var sellPrice       =new tt.Field("商品原价","specialProductPojo.sellPrice").setMsgId("sellPrice_mgId");
	var tips            =new tt.Field("商品标题","specialProductPojo.tips").setMsgId("tips_mgId");
	var specialNum      =new tt.Field("商品活动总量","specialProductPojo.specialNum").setMsgId("specialNum_mgId");
	var specialStock    =new tt.Field("商品活动库存","specialProductPojo.specialStock").setMsgId("specialStock_mgId");
	var sorting         =new tt.Field("排序","specialProductPojo.sorting").setMsgId("sorting_mgId");
	//var isIntroduce   =new tt.Field("是否推荐","specialProductPojo.isIntroduce").setMsgId("isIntroduce_mgId");
	//var file          =new tt.Field("商品图片","upfile").setMsgId("file_mgId");
    var introduction    =new tt.Field("推荐介绍","specialProductPojo.introduction").setMsgId("introduction_mgId");
tt.vf.req.add(specialPrice,sellPrice,specialNum,specialStock,sorting);
$(document).ready(function() {
	$("#sbutton").click(function(){
		if(tt.validate()){
			document.getElementById("form1").submit();
		}
	});
});	
</script>