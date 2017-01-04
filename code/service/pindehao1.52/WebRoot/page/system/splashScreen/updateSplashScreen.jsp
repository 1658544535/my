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
  <a>推送综合管理</a> &gt; 
  <a href="splashScreen.do">闪屏设置管理</a> &gt; 
  <a href="#">闪屏编辑</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="updateSplashScreen.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input type="hidden" name="splashScreenPojo.id" id="shopLatitude" value="${splashScreenPojo.id}" class="inputText" />
    <tr>
		<td align="right" class="grey" width="15%">图片IOS4：</td>
        <td width="35%">
        <s:if test="splashScreenPojo.image1 != null"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/splashScreen/${splashScreenPojo.image1}" height='100px'/></s:if>
	    <input type="file" name="upfile1" class="floatLeft" id="image1" />		
      	</td>
		<td><span id="image1_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">图片IOS5：</td>
        <td width="35%">
        <s:if test="splashScreenPojo.image2 != null"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/splashScreen/${splashScreenPojo.image2}" height='100px'/></s:if>
	    <input type="file" name="upfile2" class="floatLeft" id="image2" />		
      	</td>
		<td><span id="image2_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">图片ANDROID：</td>
        <td width="35%">
        <s:if test="splashScreenPojo.image3 != null"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/splashScreen/${splashScreenPojo.image3}" height='100px'/></s:if>
	    <input type="file" name="upfile3" class="floatLeft" id="image3" />		
      	</td>
		<td><span id="image3_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">标题：</td>
		<td><input type="text" name="splashScreenPojo.title" id="splashScreenPojo.title" value="<s:property value="splashScreenPojo.title"/>" /></td>
		<td><span id="title_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">链接：</td>
		<td><input type="text" name="splashScreenPojo.url" id="splashScreenPojo.url" value="<s:property value="splashScreenPojo.url"/>" /></td>
		<td><span id="url_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">排序：</td>
		<td><input type="text" name="splashScreenPojo.sorting" id="splashScreenPojo.sorting" value="<s:property value="splashScreenPojo.sorting"/>" /></td>
		<td><span id="sorting_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">审核状态：</td>
        <td><select name="splashScreenPojo.status" id="splashScreenPojo.status"  class="floatLeft">
							<option value="0" <s:if test="splashScreenPojo.status==0">selected="selected"</s:if>>未审核</option>
							<option value="1" <s:if test="splashScreenPojo.status==1">selected="selected"</s:if>>已审核</option>
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
	var image1      =new tt.Field("图片IOS4","upfile1").setMsgId("image1_mgId");
	var image2      =new tt.Field("图片IOS5","upfile2").setMsgId("image2_mgId");
	var image3      =new tt.Field("图片ANDROID","upfile3").setMsgId("image3_mgId");
	var title       =new tt.Field("标题","splashScreenPojo.title").setMsgId("title_mgId");
    var url         =new tt.Field("链接","splashScreenPojo.url").setMsgId("url_mgId");
    var sorting     =new tt.Field("排序","splashScreenPojo.sorting").setMsgId("sorting_mgId");
	var v_status      =new tt.Field("审核状态","splashScreenPojo.status").setMsgId("status_mgId");	
	tt.vf.req.add(title,url,sorting,v_status);
</script>