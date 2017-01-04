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
<s:if test="type==1"><a href="sceneProductList.do?sceneProductPojo.sceneId=${sceneId}">场景商品设置</a> &gt;<a href="#">新增场景商品</a></s:if>
 <s:else><a href="packageProductManage.do?sceneId=${sceneId}">套餐商品设置</a> &gt;
<c:if test="${t == 1}"><a href="packageProductAddInfo.do?type=${type }&sceneId=${sceneId }&productPojo.productStatus=1">套餐商品详情列表</a> &gt; <a href="#">新增套餐商品</a></c:if>
<c:if test="${t == 2}"><a href="#">编辑套餐商品</a></c:if></s:else>
</div>
  <div class="h15"></div>
  <div>
  <s:if test="type==1"> <form action="packageProductAddOk.do?type=1&t=1" method="post" id="form1" enctype="multipart/form-data"></s:if>
  <s:else><form action="packageProductAddOk.do?t=1" method="post" id="form1" enctype="multipart/form-data"></s:else>
  <input type="hidden" name="sceneProductPojo.sceneId" value="${sceneProductPojo.sceneId}" class="floatLeft" readonly="readonly" style="border: 0">
  <input type="hidden" name="sceneProductPojo.productId" value="${sceneProductPojo.productId}" class="floatLeft" readonly="readonly" style="border: 0">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
	<c:if test="${sceneProductPojo.sceneName != null}">
	<tr>
	    <td align="right" class="grey" width="15%">套餐名称：</td>
		<td>
		<label>${sceneProductPojo.sceneName}</label>
		</td>
		<td><span id="sceneName_mgId"></span></td>
	</tr>
	</c:if>
	<tr>
    <td align="right" class="grey" width="15%">商品名称：</td>
	<td><label>${sceneProductPojo.productName}</label></td>
	<td></td>
	</tr>
    <tr>
    <td align="right" class="grey" width="15%">商品标题：</td>
	<td><input type="text" name="sceneProductPojo.title" value="${sceneProductPojo.title}" class="floatLeft"></td>
	<td><span id="title_mgId"></span></td>
	</tr>
	<tr>
	<s:if test="type==1">
     <td align="right" class="grey" width="15%">商品原价：</td>
	<td><input type="text" name="sceneProductPojo.sellPrice" value="${sceneProductPojo.sellPrice}" class="floatLeft"></td>
	<td><span id="sellPrice_mgId"></span></td>
	</tr>
	<tr>
    <td align="right" class="grey" width="15%">商品活动价：</td>
	<td><input type="text" name="sceneProductPojo.scenePrice" value="${sceneProductPojo.scenePrice}" class="floatLeft"></td>
	<td><span id="scenePrice_mgId"></span></td>
	</tr>
	<tr>
    <td align="right" class="grey" width="15%">商品活动总量：</td>
	<td><input type="text" name="sceneProductPojo.sceneNum" value="${sceneProductPojo.sceneNum}" class="floatLeft"></td>
	<td><span id="sceneNum_mgId"></span></td>
	</tr>
	<tr>
    <td align="right" class="grey" width="15%">商品活动库存：</td>
	<td><input type="text" name="sceneProductPojo.sceneStock" value="${sceneProductPojo.sceneStock}" class="floatLeft"></td>
	<td><span id="sceneStock_mgId"></span></td>
	</tr> 
	<tr>
    <td align="right" class="grey" width="15%">排序：</td>
	<td><input type="text" name="sceneProductPojo.sorting" value="${sceneProductPojo.sorting}" class="floatLeft"></td>
	<td><span id="sorting_mgId"></span></td>
	</tr> 
	<tr>
    <td align="right" class="grey" width="15%">是否推荐：</td>
	<td><select name="sceneProductPojo.isIntroduce">
		 	<option value="0">否</option>
		 	<option value="1">是</option>
		</select></td>
	<td><span id="isIntroduce_mgId"></span></td>
	</tr>
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
			<select name="sceneProductPojo.status">
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
	        <s:if test="type==1"><font color="#FF0000">图片建议尺寸：800*800</font></s:if>
	        <s:else> <font color="#FF0000">图片建议尺寸：380*380</font></s:else>
		</td>
		<td>
		    <s:if test="type==1">	<c:if test="${t == 2 }"><img src='/upfiles/scene/${sceneProductPojo.image }' height='100px' width="200px"/></c:if></s:if>
	        <s:else>	<c:if test="${t == 2 }"><img src='/upfiles/homePackage/packageProduct/${sceneProductPojo.image }' height='100px' width="200px"/></c:if></s:else>
	        <span id="file_mgId"></span>
	    </td>
	</tr> --%>
	<tr>
		<td align="right" class="grey" width="15%">推荐介绍：</td>
		<td width="35%">
			<textarea rows="10" cols="70" name="sceneProductPojo.introduction" class="floatLeft">${sceneProductPojo.introduction }</textarea>
		</td>
		<td><span id="introduction_mgId"></span></td>
 	</tr>
	</s:if>
    </table>
  </form>
  </div>
  <div class="Btn_div">
  		<c:if test="${t == 1 }">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton"/>
  		</c:if>
  		<c:if test="${t == 2 }">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton2"/>
  		</c:if>
  </div>
</div>

</body>
</html>


<script>
var title =new tt.Field("商品标题","sceneProductPojo.title").setMsgId("title_mgId");
<c:if test="${type==1}">
	var scenePrice =new tt.Field("商品活动价","sceneProductPojo.scenePrice").setMsgId("scenePrice_mgId");
	var sellPrice =new tt.Field("商品原价","sceneProductPojo.sellPrice").setMsgId("sellPrice_mgId");
	var sceneNum =new tt.Field("商品活动总量","sceneProductPojo.sceneNum").setMsgId("sceneNum_mgId");
	var sceneStock =new tt.Field("商品活动库存","sceneProductPojo.sceneStock").setMsgId("sceneStock_mgId");
	var sorting =new tt.Field("排序","sceneProductPojo.sorting").setMsgId("sorting_mgId");
	var isIntroduce =new tt.Field("是否推荐","sceneProductPojo.isIntroduce").setMsgId("isIntroduce_mgId");
	//var file =new tt.Field("商品图片","upfile").setMsgId("file_mgId");
</c:if>
var introduction =new tt.Field("推荐介绍","sceneProductPojo.introduction").setMsgId("introduction_mgId");
tt.vf.req.add(title,introduction);
<c:if test="${type==1}">
tt.vf.req.add(title ,scenePrice,sellPrice,sceneNum,sceneStock/*,file */,introduction,sorting,isIntroduce );
tt.vf.num.add(scenePrice,sellPrice,sceneNum,sceneStock,sorting);
</c:if>


$(document).ready(function() {
	$("#sbutton").click(function(){
		if(tt.validate()){
			document.getElementById("form1").submit();
		}
	});
	
	$("#sbutton2").click(function(){
		if(tt.validate()){
			$("#form1").attr("action","packageProductAddOk.do?t=2&sceneProductPojo.id=${sceneProductPojo.id}").submit();
		}
	});
	/* if("${sceneProductPojo.isIntroduce }" != ''){
		$("select[name='sceneProductPojo.isIntroduce']").val("${sceneProductPojo.isIntroduce }");
	} */
	/* if("${sceneProductPojo.previewPro }" != ''){
		$("select[name='sceneProductPojo.previewPro']").val("${sceneProductPojo.previewPro }");
	} */
});
</script>
<c:if test="${t == 1 || scenePojo.image == '' || scenePojo.image == null}">
<script>
//var file =new tt.Field("商品图片","upfile").setMsgId("file_mgId");
//tt.vf.req.add(file);
</script>
</c:if>