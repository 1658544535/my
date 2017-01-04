<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">拼得客-用户信息表管理</a> &gt; <a href="#">审核不通过原因</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="updateUserPindekeInfo.do" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table"> 
					<input type="hidden" name="userPindekeInfoPojo.id" id="userPindekeInfoPojo.id" value="${userPindekeInfoPojo.id}" class="inputText" />
					<tr>
						<td align="right" class="grey" width="15%">审核不通过原因：</td>
						<td><input type="text" name="userPindekeInfoPojo.returnMsg" id="userPindekeInfoPojo.returnMsg" value="" /></td>
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