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
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav"><a>专题管理</a> &gt; <a href="">专题内容编辑</a>
  </div>
  <div class="h15"></div>
  <form action="updateUserMakerTheme.do" method="post" id="from1" enctype="multipart/form-data">
  <div>
  <input type="hidden" name="userMakerThemePojo.id" value="${userMakerThemePojo.id}" class="floatLeft" id="ticketName" />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
	<tr>
		<td align="right" class="grey" width="15%" height="350px">内容：</td>
		<td width="55%">
			<textarea rows="15" cols="40" name="userMakerThemePojo.content" class="floatLeft" id="introduction">${userMakerThemePojo.content} </textarea>
		</td>
		<script type="text/javascript">UE.getEditor("introduction");
		</script>
	</tr>
    </table> 
  </div>
  <div class="Btn_div">
  		<input type="submit"  class="ok_btn" value="提交"/>
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
  </div>
  </form>
</div>
</body>
</html>