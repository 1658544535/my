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
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
function check(val){
		if(confirm("确认要审核成功吗？")){
			var url = "checkUserMakerBrand.do?userMakerBrandPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}	
function uncheck(val){
		if(confirm("确认要审核失败吗？")){
			var url = "uncheckUserMakerBrand.do?userMakerBrandPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
</script>
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav"><a href="#">创客管理</a> &gt; <a href="userMakeBrand.do">创客品牌管理</a> &gt;
  <a href="#">设置审核</a>
  </div>
  <div class="h15"></div>
  <form action="updateUserMakerBrand.do" method="post" id="from1" enctype="multipart/form-data">
  <div>
  <input type="hidden" name="userMakerBrandPojo.id" value="${userMakerBrandPojo.id}" class="floatLeft" id="ticketName" />
  <input type="hidden" name="userMakerBrandPojo.brandId" value="${userMakerBrandPojo.brandId}" class="floatLeft" id="ticketName" />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
	    <td align="right" class="grey" width="15%">品牌名称：</td>
	    <td><input type="text" name="userMakerBrandPojo.brandName" value="${userMakerBrandPojo.brandName}"> <div id="brandName_mgId"></div></td> 
	</tr>
	<tr>
	<td align="right" class="grey" width="15%">品牌LOGO：</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userMakerBrand/${userMakerBrandPojo.logo}" height="100px"  /></td></tr>
	<tr>
	<td> <input type="file" name="upfile" class="floatLeft" id="ticketName" /> <span id="picture_msgId"></span></td>
	</tr>
    </table>
	</tr>
	<tr>
	<td align="right" class="grey" width="15%">商标注册证明：</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userMakerBrand/${userMakerBrandPojo.registrationCertificate}" height="100px"  /></td></tr>
	<tr>
	<td> <input type="file" name="upfile1" class="floatLeft" id="ticketName" /> <span id="picture_msgId"></span></td>
	</tr>
    </table>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">图文说明：</td>
		<td>
			<textarea disabled rows="10" cols="70" name="" class="floatLeft" id="introduction">${userMakerBrandPojo.content}</textarea>
		</td>
		<script type="text/javascript">UE.getEditor("introduction",{readonly:true});
		</script>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">审核状态：</td>
        <td><select name="userMakerBrandPojo.status" id="userMakerBrandPojo.status"  class="floatLeft">
							<option value="0" <s:if test="userMakerBrandPojo.status==0">selected="selected"</s:if>>未审核</option>
							<option value="1" <s:if test="userMakerBrandPojo.status==1">selected="selected"</s:if>>审核成功</option>
							<option value="2" <s:if test="userMakerBrandPojo.status==2">selected="selected"</s:if>>审核失败</option>
							
				    		</select></td>
		<td><span id="status_mgId"></span></td>
    </tr>
    </table> 
  </div>
  <div class="Btn_div">
  		<!--<input type="button"  class="ok_btn" value="审核成功" onclick="check(${userMakerBrandPojo.id})"/>-->
  		<input type="button"  class="ok_btn" value="提交"  id="sbutton"/>
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
  </div>
  </form>
</div>
</body>
  <script>

    var brandName              = new tt.Field("品牌名称 ","userMakerBrandPojo.brandName").setMsgId("brandName_mgId");
     tt.vf.req.add(brandName);
      $("#sbutton").click(function(){
        	if(tt.validate()){
					document.getElementById("from1").submit();
			}
		  });	
    </script>
</html>