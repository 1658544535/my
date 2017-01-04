<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav">
  <a>推荐管理</a> &gt;<a href="#">热门话题管理</a> &gt;<a href="#">编辑</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="editHotPushSubmit.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input type="hidden" name="hotPushPojo.id" value="${hotPushPojo.id}" class="floatLeft" id="ticketName" />
	<tr>
	    <td align="right" class="grey" width="15%">话题：</td>
		<td><label>${hotPushPojo.specialName}</label></td>
		<td> </td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">话题类型：</td>
        <td>
        <s:if test="hotPushPojo.type == 1">
        <label>平台专题</label>
        </s:if>
        <s:if test="hotPushPojo.type == 2">
        <label>创客专题</label>
        </s:if>
        </td>
        <td> </td>
    </tr>
	<tr>
		<td align="right" class="grey" width="15%">话题简介：</td>
        <td><label>${hotPushPojo.sketch}</label></td>	
        <td> </td>				
    </tr>
	<tr>
	    <td align="right" class="grey" width="15%">排序：</td>
		<td><input type="text" name="hotPushPojo.sorting" value="${hotPushPojo.sorting}" class="floatLeft" id="sorting" /></td>
		<td><span id="sorting_mgId"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">Banner：</td>
		<td>
			<s:if test="hotPushPojo.banner != null && hotPushPojo.banner != ''">
				<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/hotpush/${hotPushPojo.banner}' height='100px' class="floatLeft" />
			</s:if>
			<input type="file" name="upfile" class="floatLeft" id="upfile1">
		</td>
		<td><span id="image_mgId"></span></td>
	</tr>
    </table> 
    </form>
 </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button" class="ok_btn" value="提交" id="sbutton"/>
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
	var sorting =new tt.Field("排序","hotPushPojo.sorting").setMsgId("sorting_mgId");			
	tt.vf.req.add(sorting);
	tt.vf.num.add(sorting);
</script>