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
<div class="s_nav"><a href="#">商城管理</a> &gt;<a href="#">更多分类</a> &gt;<a href="#">更多分类编辑</a></div>
  <div>
  <form action="updateCategoryDetailSettingById.do?categoryDetailSettingPojo.id=${id}" method="post" id="from1" enctype="multipart/form-data">
  <input type="hidden" name="categoryDetailSettingPojo.id" value="${id}">
  <input type="hidden" name="categoryDetailSettingPojo.categoryId" value="${categoryDetailSettingPojo.categoryId}">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr><td align="right">名称：</td>
	<td>
	<label type="text" name="categoryDetailSettingPojo.name"  class="floatLeft">${categoryDetailSettingPojo.name}</label>
	<div id="name_msgId"></div>
	</td>
	</tr>
    <tr><td align="right">图片：</td>
	<td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/productType/${categoryDetailSettingPojo.image}" height="100px"  /></td></tr>
	</tr>
	<!--<tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（739*500）</font></p></td></tr>-->
    </tr>
	<tr><td align="right">排序：</td>
	<td>
	<input type="text" name="categoryDetailSettingPojo.sorting" value="${categoryDetailSettingPojo.sorting}" class="floatLeft" />
	<div id="sorting_msgId"></div>
	</td>
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
    var v_sorting = new tt.Field("排序 ", "categoryDetailSettingPojo.sorting").setMsgId("sorting_msgId");
	tt.vf.req.add(v_sorting);
	tt.vf.num.add(v_sorting);
	new tt.NRV().set(0, '++').add(v_sorting);
	tt.vf.int.add(v_sorting);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>