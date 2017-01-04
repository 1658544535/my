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
  <a>专场商品设置</a> &gt; 
  <a href="#">专场商品列表</a> &gt; 
  <a href="#">编辑专场商品</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="updateSpecialProduct.do" method="post" id="from1" enctype="multipart/form-data">
  	<input type="hidden" name="specialProductPojo.id" value="${specialProductPojo.id}" class="floatLeft" id="ticketName" />
  	<input type="hidden" name="specialProductPojo.specialId" value="${specialProductPojo.specialId}" class="floatLeft" id="ticketName" />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
	    <td align="right" class="grey" width="15%">专场名称：</td>
		<td>
		<label>${specialProductPojo.specialName}</label>
		</td>
		<td><span id="specialName_mgId"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">商品名称：</td>
		<td>
			<label>${specialProductPojo.productName}</label>	
		</td>
		<td><span id="productName_mgId"></span></td>
	</tr>
    <!-- <tr>
	    <td align="right" class="grey" width="15%">商品标题：</td>
		<td>
		<input type="text" name="specialProductPojo.title" value="${specialProductPojo.title}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="title_mgId"></span></td>
	</tr> -->
	  <tr>
	    <td align="right" class="grey" width="15%">商品原价：</td>
	    <td>
		<input type="hidden" name="specialProductPojo.sellPrice" value="${specialProductPojo.sellPrice}"  />${specialProductPojo.sellPrice}	
		</td>
		<td><span id="sellPrice_mgId"></span>
		</td>
	</tr>
    <tr>
	    <td align="right" class="grey" width="15%">商品活动价：</td>
		<td>
		<input type="text" name="specialProductPojo.specialPrice" value="${specialProductPojo.specialPrice}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="specialPrice_mgId"></span>
		</td>
	</tr>
	<tr>
        <td align="right" class="grey" width="15%">折扣：</td>
	    <td><input type="hidden" name="specialProductPojo.tips">${specialProductPojo.tips}</td>
	    <td><span id="tips_mgId"></span></td>
	</tr>
	<tr>
    <tr>
	    <td align="right" class="grey" width="15%">商品活动总量：</td>
		<td>
		<input type="text" name="specialProductPojo.specialNum" value="${specialProductPojo.specialNum}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="specialNum_mgId"></span></td>
	</tr>
    <tr>
	    <td align="right" class="grey" width="15%">商品活动库存：</td>
		<td>
		<input type="text" name="specialProductPojo.specialStock" value="${specialProductPojo.specialStock}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="specialStock_mgId"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">排序：</td>
		<td>
		<input type="text" name="specialProductPojo.sorting" value="${specialProductPojo.sorting}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="sorting_mgId"></span></td>
	</tr>
	<!-- <tr>
    <td align="right" class="grey" width="15%">是否推荐：</td>
	<td><select name="specialProductPojo.isIntroduce" >
	            <option value="0" <s:if test="specialProductPojo.isIntroduce==0">selected="selected"</s:if>>否</option>
		 		<option value="1" <s:if test="specialProductPojo.isIntroduce==1">selected="selected"</s:if>>是</option>
			</select></td>
	<td></td>
	</tr> -->
	<tr>
	    <td align="right" class="grey" width="15%">状态：</td>
		<td><select name="specialProductPojo.status" id="specialProductPojo.status"  class="floatLeft">
							<option value="0" <s:if test="specialProductPojo.status==0">selected="selected"</s:if>>未审核</option>
							<option value="1" <s:if test="specialProductPojo.status==1">selected="selected"</s:if>>已审核</option>
				    		</select></td>
		<td><span id="status_mgId"></span></td>
	</tr>
	<%-- <tr>
	    <td align="right" class="grey" width="15%">备注：</td>
		<td>
		<input type="text" name="specialProductPojo.remarks" value="${specialProductPojo.remarks}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="remarks_mgId"></span></td>
	</tr> --%>
	  <%-- <tr>
	    <td align="right" class="grey" width="15%">活动商品图片：</td>
		<td>
			<img  class="floatLeft" src="/upfiles/specialShow/specialProduct/${specialProductPojo.image}" height="100px" />
						<input type="file" class="floatLeft" name="upfile" class="floatLeft" id="specialProductPic">
						<font color="#FF0000">图片建议尺寸：800*800</font>
		</td>
		<td><span id="image_mgId"></span></td>
	</tr> --%>
	<!-- <tr>
		<td align="right" class="grey" width="15%">推荐介绍：</td>
		<td width="35%">
			<textarea rows="10" cols="70" name="specialProductPojo.introduction" class="floatLeft">${specialProductPojo.introduction }</textarea>
		</td>
		<td><span id="introduction_mgId"></span></td>
	</tr> -->
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
	$(document).ready(function(){
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});
	});	
	//var title      =new tt.Field("商品标题", "specialProductPojo.title").setMsgId("title_mgId");
	var productName  =new tt.Field("商品名称", "specialProductPojo.productName").setMsgId("productName_mgId");
	var sellPrice    =new tt.Field("商品原价", "specialProductPojo.sellPrice").setMsgId("sellPrice_mgId");
	var specialPrice =new tt.Field("商品活动价","specialProductPojo.specialPrice").setMsgId("specialPrice_mgId");
	var tips         =new tt.Field("折扣", "specialProductPojo.tips").setMsgId("tips_mgId");
	var specialNum   =new tt.Field("商品总量", "specialProductPojo.specialNum").setMsgId("specialNum_mgId");
	var specialStock =new tt.Field("商品库存 ", "specialProductPojo.specialStock").setMsgId("specialStock_mgId");
	var sorting      =new tt.Field("排序 ","specialProductPojo.sorting").setMsgId("sorting_mgId");	
	tt.vf.req.add(productName,sellPrice,specialPrice,specialNum,specialStock,sorting);
	tt.vf.num.add(sorting);
	tt.vf.int.add(sorting);
	new tt.NRV().set(0, '++').add(sorting);
	
	
</script>