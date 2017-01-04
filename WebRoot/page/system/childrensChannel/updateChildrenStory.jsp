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
  <div class="s_nav">
  <a>少儿频道</a> &gt; 
  <a href="childrenStory.do">儿童故事管理</a> &gt; 
  <a>儿童故事编辑</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="updateChildrenStory.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input type="hidden" name="childrenStoryPojo.id" id="childrenStoryPojo.id" value="${childrenStoryPojo.id}" class="inputText" />
    <tr>
	    <td align="right" class="grey" width="15%">标题：</td>
		<td><input type="text" name="childrenStoryPojo.title" id="childrenStoryPojo.title" value="<s:property value="childrenStoryPojo.title"/>" /></td>
		<td><span id="title_mgId"></span></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">音频URL:</td>
		<td><input type="text" name="childrenStoryPojo.audioUrl" id="childrenStoryPojo.audioUrl" value="<s:property value="childrenStoryPojo.audioUrl"/>"></td>
		<td><span id="audioUrl_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">排序：</td>
		<td><input type="text" name="childrenStoryPojo.sorting" id="childrenStoryPojo.sorting" value="<s:property value="childrenStoryPojo.sorting"/>" /></td>
		<td><span id="sorting_mgId"></span></td>
    </tr>
    <tr>
		<td align="right" class="grey" width="15%">审核状态：</td>
        <td><select name="childrenStoryPojo.status" id="childrenStoryPojo.status"  class="floatLeft">
							<option value="0" <s:if test="childrenStoryPojo.status==0">selected="selected"</s:if>>未审核</option>
							<option value="1" <s:if test="childrenStoryPojo.status==1">selected="selected"</s:if>>已审核</option>
				    		</select></td>
		<td><span id="status_mgId"></span></td>
    </tr>
    <tr>
    	<td align="right" class="grey" width="15%">故事内容：</td>
		<td width="55%">
			<textarea rows="10" cols="70" name="childrenStoryPojo.content" class="floatLeft" id="introduction">${childrenStoryPojo.content}</textarea>
			<script type="text/javascript">UE.getEditor("introduction");</script>
		</td>
		<td><span id="content_mgId"></span></td>
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
				var id = $("input[name='childrenStoryPojo.sorting']").val();
				var r = /^[1-9][0-9]*$/;
				if(id != "" && !r.test(id)){
					alert("排序必须为正整数！");
				}else{
					document.getElementById("from1").submit();	
				}				
			}
		});
	});	
	var title        =new tt.Field("标题","childrenStoryPojo.title").setMsgId("title_mgId");
	var sorting      =new tt.Field("排序","childrenStoryPojo.sorting").setMsgId("sorting_mgId");
	var v_status     =new tt.Field("审核状态","childrenStoryPojo.status").setMsgId("status_mgId");
	var content      =new tt.Field("故事内容","childrenStoryPojo.content").setMsgId("content_mgId");
	var v_audioUrl   =new tt.Field("音频URL","childrenStoryPojo.audioUrl").setMsgId("audioUrl_mgId");
	tt.vf.req.add(title,sorting,v_status,content,v_audioUrl);
</script>