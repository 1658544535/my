<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
</head>
<body>
<div class="sub_wrap">
	<div class="s_nav">
		<a>标签管理</a> &gt; <a href="labelList.do">标签库</a> &gt; <a href="#">标签编辑</a>
	</div>
	<div class="h15"></div>
	<div>
		<form action="updateLabel.do" method="post" id="from1" enctype="multipart/form-data">
			<input type="hidden" name="labelPojo.id" id="labelPojo.id" value="${labelPojo.id}" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table"> 
				<tr>
				<td align="right" class="grey" width="15%">标签名称：</td>
				<td><input type="text" name="labelPojo.name" id="labelPojo.name" style="float:left" value="<s:property value="labelPojo.name"/>" /><span id="name_mgId"></span></td>
				</tr>
			</table> 
		</form>
	</div>
	<div class="Btn_div">
		<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
		<input type="button"  class="ok_btn" value="提交" id="sbutton"/>
	</div>
</div>
</body>
<script>
	var v_name = new tt.Field("标签名称 ","labelPojo.name").setMsgId("name_mgId");	
	tt.vf.req.add(v_name);
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});
	});
</script>
</html>
