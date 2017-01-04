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
<div class="s_nav"><a href="#">活动商品管理</a> &gt;<c:if test="${type==5}"><a href="#">web活动页管理</a></c:if><c:if test="${type==4}"><a href="#">首页列表活动管理</a></c:if> &gt;<a href="#">编辑</a></div>
  <div>
  <form action="updateActivityTitle.do?type=${type}" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input name="activityTitlePojo.id" id="activityTitlePojoId" value="${activityTitlePojo.id}" class="inputText" type="hidden" >
    <tr><td align="right">活动标题：</td>
	<td>
	<input type="text" name="activityTitlePojo.title" value="${activityTitlePojo.title}" class="floatLeft">
	</td>
	</tr>
	<tr>
	<td align="right">Banner:</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/${activityTitlePojo.banner}" height="100px"  /></td></tr>
	<tr><td><input type="file" name="upfile" class="floatLeft" id="ticketName"></td></tr>
	<c:if test="${type==4}"><tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（739*500）</font></p></td></tr></c:if>
	<c:if test="${type==5}"><tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（1242*830）</font></p></td></tr></c:if>
	</table>
	<span id="banner_msgIddd"></span>
	</td>
	</tr>
	<c:if test="${type==5}">
	<tr>
	<td align="right">标题图片1:</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/${activityTitlePojo.titlePic}" height="100px"  /></td></tr>
	<tr><td><input type="file" name="picfile" class="floatLeft" id="ticket"></td></tr>
	<tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（1242*258）</font></p></td></tr>
	</table>
	</td>
	</tr>
	<tr>
	<td align="right">标题图片2:</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/${activityTitlePojo.titlePicture}" height="100px"  /></td></tr>
	<tr><td><input type="file" name="picturefile" class="floatLeft" id="ticke"></td></tr>
	<tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（1242*286）</font></p></td></tr>
	</table>
	</td>
	</tr>
	<tr>
	<td align="right">标题图片3:</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/notice/${activityTitlePojo.titlePhoto}" height="100px"  /></td></tr>
	<tr><td><input type="file" name="photofile" class="floatLeft" id="tick"></td></tr>
	<tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（1242*286）</font></p></td></tr>
	</table>
	</td>
	</tr>
	</c:if>
    <td align="right">审核状态：</td>
	<td><select name="activityTitlePojo.status" id="activityTitlePojo.status"  class="floatLeft">
	<option value="">全部</option>
	<s:iterator value="statusSysDictList">
		<option value="<s:property value="value"/>" <c:if test="${value == activityTitlePojo.status}">selected</c:if>><s:property value="name" />
		</option>
	</s:iterator>
	</select>
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
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>