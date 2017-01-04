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
			<a href="#">团购记录管理</a> &gt; <a href="#">编辑</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="updateGrouponActivityRecord.do" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table"> 
					<input type="hidden" name="grouponActivityRecordPojo.id" id="grouponActivityRecordPojo.id" value="${grouponActivityRecordPojo.id}" class="inputText" />
					<tr>
						<td align="right" class="grey" width="15%">活动类型,1-普通团2-团免3-猜价：</td>
						<td><input type="text" name="grouponActivityRecordPojo.activityType" id="grouponActivityRecordPojo.activityType" value="${grouponActivityRecordPojo.activityType}" /></td>
						<td><span id="activityType_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">活动id：</td>
						<td><input type="text" name="grouponActivityRecordPojo.activityId" id="grouponActivityRecordPojo.activityId" value="${grouponActivityRecordPojo.activityId}" /></td>
						<td><span id="activityId_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">团长用户id：</td>
						<td><input type="text" name="grouponActivityRecordPojo.userId" id="grouponActivityRecordPojo.userId" value="${grouponActivityRecordPojo.userId}" /></td>
						<td><span id="userId_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">拼团人数：</td>
						<td><input type="text" name="grouponActivityRecordPojo.num" id="grouponActivityRecordPojo.num" value="${grouponActivityRecordPojo.num}" /></td>
						<td><span id="num_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">剩余人数：</td>
						<td><input type="text" name="grouponActivityRecordPojo.surplusNum" id="grouponActivityRecordPojo.surplusNum" value="${grouponActivityRecordPojo.surplusNum}" /></td>
						<td><span id="surplusNum_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">状态：</td>
						<td><input type="text" name="grouponActivityRecordPojo.status" id="grouponActivityRecordPojo.status" value="${grouponActivityRecordPojo.status}" /></td>
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