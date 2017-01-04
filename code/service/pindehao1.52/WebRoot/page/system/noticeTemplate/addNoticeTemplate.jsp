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
			<a href="#">消息模板表管理</a> &gt; <a href="#">新增</a>
		</div>
		<div class="h15"></div>
		<div>
		<form action="addNoticeTemplate.do" method="post" id="from1" enctype="multipart/form-data">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
				<tr>
					<td align="right" class="grey" width="15%">模板类型1-支付成功2-开团成功3-拼团成功4-抽奖未中奖5-抽奖已中奖6-发货7-退款：</td>
					<td><input type="text" name="noticeTemplatePojo.type" id="noticeTemplatePojo.type" value="" /></td>
					<td><span id="type_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">模板标题：</td>
					<td><input type="text" name="noticeTemplatePojo.title" id="noticeTemplatePojo.title" value="" /></td>
					<td><span id="title_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">模板信息：</td>
					<td><input type="text" name="noticeTemplatePojo.template" id="noticeTemplatePojo.template" value="" /></td>
					<td><span id="template_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">删除标识（0-否1-是）：</td>
					<td><input type="text" name="noticeTemplatePojo.isDelete" id="noticeTemplatePojo.isDelete" value="" /></td>
					<td><span id="isDelete_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">状态：</td>
					<td><input type="text" name="noticeTemplatePojo.status" id="noticeTemplatePojo.status" value="" /></td>
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
</script>