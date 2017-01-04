<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" href='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" href='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	href='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" href='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script href='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>
<script type="text/javascript" href='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<script type="text/javascript" href='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"
	href='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" href='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script href='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>
<link type="text/css" rel="stylesheet"
	href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
		 <a href="#">活动商品管理</a> &gt;<a href="activityTitleManage.do?type=1">web活动页管理</a>&gt;<a>编辑活动商品</a></div>
		<div class="h15"></div>
		<div>
			<form action="updateActivityProduct.do?type=${type}&titleId=${titleId}&id=${id}" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="info_table">
				   <!--<input type="hidden" name="activityGoodsPojo.timeId" value="${timeId}" id="activityGoodsPojo.timeId">
				   <input type="hidden" name="activityGoodsPojo.productId" value="${productId}" id="activityGoodsPojo.productId">
				   <input type="hidden" name="activityGoodsPojo.sellPrice" value="${sellPrice}" id="activityGoodsPojo.sellPrice">-->
				     <tr>
	                    <td align="right" class="grey" width="15%">展位类型:</td>
			            <td><select name="activityProductPojo.productType" id="activityProductPojo.productType" class="floatLeft">
							<option value="1"<s:if test="activityProductPojo.productType==1">selected="selected"</s:if>>活动单品</option>
							<option value="2"<s:if test="activityProductPojo.productType==2">selected="selected"</s:if>>单品连接专场</option>
				    		</select>
				    	</td>
				    </tr>
	                <tr>					
						<td align="right" class="grey" width="15%">专场ID:</td>	
						<td width="35%"><input type="text" name="activityProductPojo.specialId" value="${activityProductPojo.specialId}" class="floatLeft" >
						<span id="specialId_msgId"></span>
						</td>				
						<td align="right" class="grey" width="15%">商品ID:</td>	
						<td width="35%"><input type="text" name="activityProductPojo.productId" value="${activityProductPojo.productId}" class="floatLeft" >
						<span id="productId_msgId"></span>
						</td>
				    </tr>
					<tr>
				    		<td align="right" class="grey" width="15%">审核状态：</td>
                            <td><select name="activityProductPojo.status" id="activityProductPojo.status"  class="floatLeft">
							<option value="0" <s:if test="activityProductPojo.status==0">selected="selected"</s:if>>未审核</option>
							<option value="1" <s:if test="activityProductPojo.status==1">selected="selected"</s:if>>已审核</option>
				    		</select></td>
				    	<td align="right" class="grey" width="15%">排序:</td>
						<td width="35%"><input type="text" name="activityProductPojo.sorting" value="${activityProductPojo.sorting}" class="floatLeft" >
						<div id="sorting_msgId"></div></td>
					</tr>
					</table>
			</form>
		</div>
		<div class="Btn_div">
			<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
			<input type="button" class="ok_btn" value="提交" id="sbutton" />
		</div>
	</div>
</body>
</html>

<script>
	var specialId = new tt.Field(" 专场ID ", "activityProductPojo.specialId").setMsgId("specialId_msgId");
	var productId  = new tt.Field(" 商品ID ", "activityProductPojo.productId").setMsgId("productId_msgId");
	var sorting	   = new tt.Field("排序","activityProductPojo.sorting").setMsgId("sorting_msgId");
	tt.vf.req.add(specialId,productId,sorting);
	new tt.NRV().set(0, '++').add(specialId,productId,sorting);
	tt.vf.int.add(specialId,productId,sorting);
	$(document).ready(function() {
		$("#sbutton").click(function() {
			if (tt.validate()) {
				document.getElementById("from1").submit();
			}
		});
	});
	
	
</script>