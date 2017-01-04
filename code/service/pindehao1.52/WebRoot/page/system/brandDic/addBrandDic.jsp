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
  <a>商家中心管理</a> &gt; 
  <a href="brandDicList.do">品牌字典管理</a> &gt; 
  <a href="addBrandDic.do">品牌字典新增</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addBrandDicOK.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
	    <td align="right" class="grey" width="15%">品牌名：</td>
		<td><input type="text" name="brandDicPojo.brand" id="brandDicPojo.brand" value="<s:property value="brandDicPojo.brand"/>" /></td>
		<td><span id="brand_mgId"></span></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">品牌LOGO：</td>
        <td width="35%">
	    <input type="file" name="upfile" class="floatLeft" id="logo" />		
      	</td>
		<td><span id="logo_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">排序：</td>
		<td><input type="text" name="brandDicPojo.sorting" id="brandDicPojo.sorting" value="<s:property value="brandDicPojo.sorting"/>" /></td>
		<td><span id="sorting_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">审核状态：</td>
        <td><select name="brandDicPojo.status" id="brandDicPojo.status"  class="floatLeft">
							<option value="0">未审核</option>
							<option value="1">已审核</option>
				    		</select></td>
		<td><span id="status_mgId"></span></td>
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
	var brand     =new tt.Field("品牌名 ","brandDicPojo.brand").setMsgId("brand_mgId");
	var logo      =new tt.Field("品牌LOGO","upfile").setMsgId("logo_mgId");
	var sorting   =new tt.Field("排序","brandDicPojo.sorting").setMsgId("sorting_mgId");
	var v_status    =new tt.Field("审核状态","brandDicPojo.status").setMsgId("status_mgId");	
	tt.vf.req.add(brand,sorting,v_status);
</script>