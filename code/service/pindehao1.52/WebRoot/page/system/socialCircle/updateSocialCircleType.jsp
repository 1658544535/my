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
	<div class="s_nav"><a href="#">社圈管理</a> &gt; <a href="socialCircleType.do">社圈分类</a> &gt; <a href="#">分类编辑</a>
	</div>
	<div class="h15"></div>
	<div>
		<form action="updateSocialCircleType.do" method="post" id="from1" enctype="multipart/form-data">
			<input type="hidden" name="socialCircleTypePojo.id" id="socialCircleTypePojo.id" value="${socialCircleTypePojo.id}" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table"> 
				<tr>
				<td align="right" class="grey" width="15%">分类名称：</td>
				<td><input type="text" name="socialCircleTypePojo.name" id="socialCircleTypePojo.name" value="<s:property value="socialCircleTypePojo.name"/>" /></td>
				<td><span id="name_mgId"></span></td>
				</tr>
				<tr>
				<td align="right" class="grey" width="15%">排序：</td>
				<td><input type="text" name="socialCircleTypePojo.sorting" id="socialCircleTypePojo.sorting" value="<s:property value="socialCircleTypePojo.sorting"/>" /></td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">图标：</td>
					<td>
						<input type="file" class="floatLeft" name="upfile" class="floatLeft" id="banner">
						<img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/socialCircleType/${socialCircleTypePojo.images}" height="100px" />
						<span id="images_mgId"></span>
					</td>
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
	var v_name     = new tt.Field("分类名称 ","socialCircleTypePojo.name").setMsgId("name_mgId");	
	var v_images   = new tt.Field("图标 ","socialCircleTypePojo.images").setMsgId("images_mgId");
	tt.vf.req.add(v_name,v_images);
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				var id = $("input[name='socialCircleTypePojo.sorting']").val();
				var r = /^[1-9][0-9]*$/;
				if(id != "" && !r.test(id)){
					alert("排序必须为正整数！");
				}else{
					document.getElementById("from1").submit();	
				}				
			}
		});
	});
</script>
</html>
