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
<div class="s_nav"><a href="#">活动商品管理</a> &gt;<c:if test="${type==5}"><a href="#">web活动页管理</a></c:if><c:if test="${type==4}"><a href="#">首页列表活动管理</a></c:if> &gt;<a href="#">新增活动标题</a></div>
  <div>
  <form action="insertActivityTitle.do" method="post" id="from1" enctype="multipart/form-data">
  <input type="hidden" name="type" id="type" value="${type}">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr><td align="right">标题：</td>
	<td>
	<input type="text" name="activityTitlePojo.title" value="${activityTitlePojo.title}" class="floatLeft" />
	<div id="title_msgId"></div>
	</td>
	</tr>
    <tr><td align="right">Banner：</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr>
	<td> <input type="file" name="upfile" class="floatLeft" id="ticketName" /> <span id="banner_msgId"></span></td>
	</tr>
	<c:if test="${type==4}"><tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（739*500）</font></p></td></tr></c:if>
	<c:if test="${type==5}"><tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（1242*830）</font></p></td></tr></c:if>
	</table>
	
	</tr>
	<c:if test="${type==5}">
	<tr><td align="right">标题图片1：</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr>
	<td> <input type="file" name="picfile" class="floatLeft" id="ticket" /> <span id="titlePic_msgId"></span></td>
	</tr>
	<tr>
	<td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（1242*258）</font></p></td>
	</tr>
	</table>
	</tr>
	<tr><td align="right">标题图片2：</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr>
	<td> <input type="file" name="picturefile" class="floatLeft" id="ticke" /> <span id="titlePicture_msgId"></span></td>
	</tr>
	<tr>
	<td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（1242*286）</font></p></td>
	</tr>
	</table>
	</tr>
	<tr><td align="right">标题图片3：</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr>
	<td> <input type="file" name="photofile" class="floatLeft" id="tick" /> <span id="titlePhoto_msgId"></span></td>
	</tr>
	<tr>
	<td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（1242*286）</font></p></td>
	</tr>
	</table>
	</tr>
	</c:if>	
   <c:if test="${type!=5}"><td align="right">审核状态：</td>
	<td><select name="activityTitlePojo.status" id="activityTitlePojo.status"  class="floatLeft">
	<option value="">全部</option>
	<s:iterator value="statusSysDictList">
		<option value="<s:property value="value"/>" <c:if test="${value == activityTitlePojo.status}">selected</c:if>><s:property value="name" />
		</option>
	</s:iterator>
	</select>
    </td>
    </c:if>		
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
    var title = new tt.Field("标题 ", "activityTitlePojo.title").setMsgId("title_msgId");
    var banner = new tt.Field("Banner ", "activityTitlePojo.banner").setMsgId("banner_msgId");
    var titlePic = new tt.Field("标题图片 ", "activityTitlePojo.titlePic").setMsgId("titlePic_msgId");
	tt.vf.req.add(title,banner,titlePic);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>